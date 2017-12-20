package com.demo.brave;

import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;

import brave.SpanCustomizer;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/19 <br/>
 * <B>版本：</B><br/>
 */
public class DubboClientParser extends AbstractParser {
  /**
   * Customizes the span based on the request that will be sent to the server.
   */
  public void request(DubboAdapter adapter, RpcContext context, SpanCustomizer customizer) {
    customizer.name(spanName(adapter, context));
    String path = adapter.getRemoteAddress(context);
    if (path != null) customizer.tag("provider.address", path);
  }

  public void response(DubboAdapter adapter, Result rpcResult, SpanCustomizer customizer) {
    if (!rpcResult.hasException()) {
      customizer.tag("consumer.result", "true");
    } else {
      customizer.tag("consumer.exception", rpcResult.getException().getMessage());
    }
  }

}
