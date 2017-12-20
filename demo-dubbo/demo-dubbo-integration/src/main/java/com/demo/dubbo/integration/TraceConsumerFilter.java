package com.demo.dubbo.integration;


import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanInjector;
import org.springframework.cloud.sleuth.Tracer;

/**
 * <p>
 * 参考代码: {@link org.springframework.cloud.sleuth.instrument.web.client.TraceRestTemplateInterceptor}
 * </p>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/15 <br/>
 * <B>版本：</B><br/>
 */
//@Activate(group = {Constants.CONSUMER}, order = -9000)
@Slf4j
public class TraceConsumerFilter implements Filter {

  private final Tracer tracer;
  private final SpanInjector<RpcContext> spanInjector;

  public TraceConsumerFilter() {
    this.tracer = SpringContextHolder.getTracer();
    this.spanInjector = SpringContextHolder.getBean(DubboSpanInjector.class);
  }

  @Override
  public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
    if (SpringContextHolder.isTrace()) {
      publishStartEvent(invoker, invocation);
      try {
        Result result = invoker.invoke(invocation);
        finish();
        return result;
      } catch (Exception e) {
        this.tracer.close(currentSpan());
        throw e;
      }
    } else {
      return invoker.invoke(invocation);
    }
  }

  private void finish() {
    if (!isTracing()) {
      return;
    }
    currentSpan().logEvent(Span.CLIENT_RECV);
    this.tracer.close(this.currentSpan());
  }

  private Span currentSpan() {
    return this.tracer.getCurrentSpan();
  }

  private boolean isTracing() {
    return this.tracer.isTracing();
  }


  /**
   * 客户端记录 start event ，即cr事件
   * the client sent event
   */
  private void publishStartEvent(Invoker<?> invoker, Invocation invocation) {

    String spanName = DubboSpanUtils.spanName(invoker, invocation) + "-consumer";
    Span newSpan = this.tracer.createSpan(spanName);
    this.spanInjector.inject(newSpan, RpcContext.getContext());
    newSpan.logEvent(Span.CLIENT_SEND);
  }

}
