package com.demo.dubbo.integration;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanExtractor;
import org.springframework.cloud.sleuth.SpanInjector;
import org.springframework.cloud.sleuth.SpanReporter;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.sampler.NeverSampler;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Map;

/**
 * <p>
 * 参考代码: {@link OncePerRequestFilter}
 *
 * </p>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/15 <br/>
 * <B>版本：</B><br/>
 */
//@Activate(group = {Constants.PROVIDER}, order = -9000)
public class TraceProviderFilter implements Filter {

  private final Tracer tracer;
  private final SpanReporter spanReporter;
  private final SpanExtractor<RpcContext> spanExtractor;
  private final SpanInjector<RpcContext> spanInjector;

  public TraceProviderFilter() {
    this.tracer = SpringContextHolder.getTracer();
    this.spanReporter = SpringContextHolder.getBean(SpanReporter.class);
    this.spanExtractor = SpringContextHolder.getBean(DubboSpanExtractor.class);
    this.spanInjector = SpringContextHolder.getBean(DubboSpanInjector.class);
  }

  @Override
  public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
    //1. 如果没有被追踪
    boolean isTraceDubbo = SpringContextHolder.isTrace();
    Map<String, String> attachments = RpcContext.getContext().getAttachments();
    if (!isTraceDubbo) {
      return invoker.invoke(invocation);
    } else {
      boolean skip = Span.SPAN_NOT_SAMPLED.equals(attachments.get(Span.SAMPLED_NAME));
      String spanName = DubboSpanUtils.spanName(invoker, invocation) + "-provider";
      Span span = createSpan(skip, invoker, invocation, spanName);
      try {
        addTags(invoker, invocation);
        spanInjector.inject(span, RpcContext.getContext());
        return invoker.invoke(invocation);
      } catch (Throwable e) {
        throw e;
      } finally {
        if (span != null) {
          if (span.hasSavedSpan()) {
            Span parent = span.getSavedSpan();
            if (parent.isRemote()) {
              parent.logEvent(Span.SERVER_SEND);
              parent.stop();
              this.spanReporter.report(parent);
            }
          } else {
            span.logEvent(Span.SERVER_SEND);
          }
          this.tracer.close(span);
        }
      }
    }


  }

  private void addTags(Invoker<?> invoker, Invocation invocation) {
    //TODO 增加 tags
//    this.tracer.addTag("servi");
  }


  private Span createSpan(boolean skip, Invoker<?> invoker, Invocation invocation, String name) {
    Span parent = spanExtractor
        .joinTrace(RpcContext.getContext());
    Span span;
    if (parent != null) {
      span = this.tracer.createSpan(name, parent);
      if (parent.isRemote()) {
        parent.logEvent(Span.SERVER_RECV);
      }
    } else {
      if (skip) {
        span = this.tracer.createSpan(name, NeverSampler.INSTANCE);
      } else {
        span = this.tracer.createSpan(name);
      }
      span.logEvent(Span.SERVER_RECV);
    }
    return span;
  }
}
