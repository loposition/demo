package com.demo.brave;

import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;

import lombok.Setter;

import brave.Span;
import brave.Tracer;
import brave.propagation.TraceContext;
import brave.propagation.TraceContextOrSamplingFlags;

import java.util.Map;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/20 <br/>
 * <B>版本：</B><br/>
 */
public class DubboServerHandler {
  @Setter
  private Tracer tracer;

  @Setter
  private DubboServerParser parser;

  @Setter
  private DubboServerAdapter adapter;

  /**
   * Conditional joins  a span, or starts a new trace, depending on if a trace context was
   * extracted from the request, Tags are added before the span is started.
   *
   * <p>
   * This is typically called before the request is processed by the actual library.
   * </p>
   */
  public Span handleReceive(TraceContext.Extractor<Map<String, String>> extractor) {
    TraceContextOrSamplingFlags extracted = extractor.extract(RpcContext.getContext().getAttachments());
    Span span = nextSpan(extracted);
    if (span.isNoop()) return span;

    // all of the parsing here occur before a timestamp is recorded on the span
    span.kind(Span.Kind.SERVER);

// Ensure user-code can read the current trace context
    Tracer.SpanInScope ws = tracer.withSpanInScope(span);
    try {
      parser.request(adapter, RpcContext.getContext(), span);
    } finally {
      ws.close();
    }
    // To do something with the endpoint

    return span.start();
  }

  /**
   * creates a potentially noop span representing the request.
   */
  private Span nextSpan(TraceContextOrSamplingFlags extracted) {
    if (extracted.sampled() == null) { // Otherwise, try to make a new decision
      extracted = extracted.sampled(false);
    }

    return extracted.context() != null
        ? tracer.joinSpan(extracted.context())
        : tracer.nextSpan(extracted);
  }


  /**
   * Finishes the server span after assigning it tags according to the response or error
   */
  public void handleSend(Result rpcResult, Span span) {
    if (span.isNoop()) return;
    Tracer.SpanInScope ws = tracer.withSpanInScope(span);
    try {
      parser.response(adapter, rpcResult, span);
    } finally {
      ws.close();
      span.finish();
    }
  }
}
