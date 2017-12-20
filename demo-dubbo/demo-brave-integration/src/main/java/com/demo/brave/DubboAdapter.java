package com.demo.brave;

import com.alibaba.dubbo.rpc.RpcContext;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/19 <br/>
 * <B>版本：</B><br/>
 */
public class DubboAdapter {

  /**
   * Returns the span name of the rpcContext.
   */
  public String getSpanName(RpcContext rpcContext) {
    String className = rpcContext.getUrl().getPath();
    String simpleName = className.substring(className.lastIndexOf(".") + 1);
    return simpleName + "." + RpcContext.getContext().getMethodName();
  }

  /**
   * Returns the ip address and port. <p>If provider invoke this method then remote address is
   * consumer address</p>
   */
  public String getRemoteAddress(RpcContext rpcContext) {
    return rpcContext.getRemoteAddressString();
  }
}
