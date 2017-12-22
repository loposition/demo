package com.demo.brave;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

import brave.Span;
import brave.Tracer;
import brave.propagation.TraceContext;

import java.util.Map;


/**
 * <p>
 * 参考代码: {@link brave.spring.webmvc.TracingHandlerInterceptor}
 * </p>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/20 <br/>
 * <B>版本：</B><br/>
 */
public class DubboBraveProviderFilter implements Filter {

  private final DubboServerHandler handler;

  private final Tracer tracer;
  private final TraceContext.Extractor<Map<String, String>> extractor;

  public DubboBraveProviderFilter() {
    BeanHolder instance = BeanHolder.getInstance();
    this.handler = instance.getDubboServerHandler();
    this.tracer = instance.getTracing().tracer();
    this.extractor = instance.getTracing().propagation().extractor(Map::get);
  }


  @Override
  public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
    // todo dubbo是否有异步的情况..
    preHandle();
    Result rpcResult = null;
    try {
      rpcResult = invoker.invoke(invocation);
    } catch (Throwable e) {
      throw e;
    } finally {
      afterCompletion(rpcResult);
    }
    return rpcResult;
  }

  private void afterCompletion(Result rpcResult) {
    Span span = tracer.currentSpan();
    if (span == null) return;
    handler.handleSend(rpcResult, span);
  }

  private void preHandle() {
    Span span = handler.handleReceive(extractor);
    tracer.withSpanInScope(span);
  }
}
