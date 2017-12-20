package com.demo.brave;

import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;

import lombok.Setter;

import brave.Span;
import brave.Tracer;
import brave.internal.Nullable;
import brave.propagation.CurrentTraceContext;
import brave.propagation.TraceContext;
import brave.propagation.TraceContextOrSamplingFlags;

import java.util.Map;

/**
 * <p>
 * 参考 {@link org.springframework.http.client.ClientHttpRequestInterceptor}
 *
 * </p>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/19 <br/>
 * <B>版本：</B><br/>
 */
public class DubboClientHandler {

  @Setter
  private Tracer tracer;
  @Setter
  private CurrentTraceContext currentTraceContext;
  @Setter
  private DubboClientParser parser;
  @Setter
  private DubboClientAdapter adapter;

  public DubboClientHandler() {

  }


  public Span handleSend(
      TraceContext.Injector<Map<String, String>> injector,
      TraceContext.Extractor<Map<String, String>> extractor
  ) {
    Span span = nextSpan(null);
    injector.inject(span.context(), RpcContext.getContext().getAttachments());
    if (span.isNoop()) {
      return span;
    }
    // all of the parsing here occur before a timestamp is recorded on the span
    span.kind(Span.Kind.CLIENT);
    // Ensure user-code can read the current trace context
    Tracer.SpanInScope ws = tracer.withSpanInScope(span);
    try {
      parser.request(adapter, RpcContext.getContext(), span);
    } finally {
      ws.close();
    }
    // todo endpoint

    return span.start();
  }


  public void handleReceive(@Nullable Result rpcResult, Span span) {
    if (span.isNoop()) return;
    Tracer.SpanInScope ws = tracer.withSpanInScope(span);
    try {
      parser.response(adapter, rpcResult, span);
    } finally {
      ws.close();
      span.finish();
    }

  }

  /**
   * Create a potentially noop span representing this request.
   * This is used when you need to
   * provision a span in a different scope than where the request is executed.
   */
  private Span nextSpan(TraceContextOrSamplingFlags flags) {
    //1. 看当前上下文中是否拥有 parent span
    TraceContext parent = currentTraceContext.get();
    if (parent != null) return tracer.newChild(parent);
    return tracer.newTrace();
  }

}
