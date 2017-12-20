package com.demo.brave;

import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;

import brave.SpanCustomizer;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/20 <br/>
 * <B>版本：</B><br/>
 */
public class DubboServerParser extends AbstractParser {
  @Override
  public void request(DubboAdapter adapter, RpcContext rpcContext, SpanCustomizer customizer) {
    customizer.name(spanName(adapter, rpcContext));
    String path = adapter.getRemoteAddress(rpcContext);
    if (path != null) {
      customizer.tag("consumer.address", path);
    }
  }

  @Override
  public void response(DubboAdapter adapter, Result rpcResult, SpanCustomizer customizer) {
    if (!rpcResult.hasException()) {
      customizer.tag("provider.result", "true");
    } else {
      customizer.tag("provider.exception", rpcResult.getException().getMessage());
    }
  }
}
