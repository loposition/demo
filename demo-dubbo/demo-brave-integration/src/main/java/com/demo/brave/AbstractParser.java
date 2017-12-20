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
public abstract class AbstractParser {

  public abstract void request(DubboAdapter adapter, RpcContext context, SpanCustomizer customizer);

  public abstract void response(DubboAdapter adapter, Result rpcResult, SpanCustomizer customizer);

  protected String spanName(DubboAdapter adapter, RpcContext rpcContext) {
    return adapter.getSpanName(rpcContext);
  }
}
