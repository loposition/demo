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
 * 参考 {@link brave.http.HttpClientHandler}
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


  /**
   * 客户端 handler 标记 client send
   *
   * @param injector  注入器
   * @param extractor 解析器
   */
  public Span handleSend(
      TraceContext.Injector<Map<String, String>> injector,
      TraceContext.Extractor<Map<String, String>> extractor
  ) {
    //1. 解析上下文，判断是否有 parent 并创建 span
    Span span = nextSpan(extractor.extract(RpcContext.getContext().getAttachments()));
    //2. 将 span 内容 inject -> dubbo 的附件，供下个生命周期调用
    injector.inject(span.context(), RpcContext.getContext().getAttachments());
    if (span.isNoop()) {
      return span;
    }
    // 3. 记录 client send 事件
    span.kind(Span.Kind.CLIENT);
    // 4. 将当前 span 植入当前上下文
    Tracer.SpanInScope ws = tracer.withSpanInScope(span);
    try {
      // 5. 适配一下 span ，注入一些信息
      parser.request(adapter, RpcContext.getContext(), span);
    } finally {
      ws.close();
    }
    return span.start();
  }


  /**
   * 客户端收到 响应处理
   */
  public void handleReceive(@Nullable Result rpcResult, Span span) {
    // 1. 如果 span 不需要被发送到 zipkin ,返回
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
  private Span nextSpan(TraceContextOrSamplingFlags extracted) {
    TraceContext parent = currentTraceContext.get();
    if (parent != null) return tracer.newChild(parent); // inherit the sampling decision
    return tracer.newTrace();
  }

}
