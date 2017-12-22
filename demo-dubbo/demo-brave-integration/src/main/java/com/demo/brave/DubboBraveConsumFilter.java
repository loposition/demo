package com.demo.brave;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

import brave.Span;
import brave.Tracer;
import brave.Tracing;
import brave.propagation.TraceContext;

import java.util.Map;

/**
 * <p>
 * {@link brave.spring.web.TracingClientHttpRequestInterceptor}
 * </p>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/19 <br/>
 * <B>版本：</B><br/>
 */
public class DubboBraveConsumFilter implements Filter {

  private final Tracer tracer;
  private final DubboClientHandler handler;
  private final TraceContext.Injector<Map<String, String>> injector;
  private final TraceContext.Extractor<Map<String, String>> extractor;

  public DubboBraveConsumFilter() {
    BeanHolder instance = BeanHolder.getInstance();
    Tracing tracing = instance.getTracing();
    this.tracer = tracing.tracer();
    this.handler = instance.getDubboClientHandler();
    this.injector = tracing.propagation().injector(Map::put);
    this.extractor = tracing.propagation().extractor(Map::get);
  }


  @Override
  public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
    //1. 客户端调用前 span 处理
    Span span = handler.handleSend(injector, extractor);
    Result rpcResult = null;
    //2. 将span 植入 current context , ws 不关闭
    try (Tracer.SpanInScope ws = tracer.withSpanInScope(span)) {
      return rpcResult = invoker.invoke(invocation);
    } catch (RuntimeException | Error e) {
      throw e;
    } finally {
      // 3. 处理上面的  span
      handler.handleReceive(rpcResult, span);
    }
  }
}
