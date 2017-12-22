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
   * 如何为 span name 指定名字，一个service 服务的唯一调用，不仅仅是名字s
   */
  public String getSpanName(RpcContext rpcContext) {
    StringBuilder sb = new StringBuilder();
    sb.append(rpcContext.getUrl().getParameter("interface")).append(":")
        .append(rpcContext.getMethodName()).append(":");
    String version = rpcContext.getUrl().getParameter("version");
    if (version != null) {
      sb.append(version);
    }
//    sb.append("(").append(rpcContext.getUrl().getHost()).append(")");
    return sb.toString();
  }

  /**
   * Returns the ip address and port. <p>If provider invoke this method then remote address is
   * consumer address</p>
   */
  public String getRemoteAddress(RpcContext rpcContext) {
    return rpcContext.getRemoteAddressString();
  }

}
