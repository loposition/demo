package com.demo.dubbo.integration;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/19 <br/>
 * <B>版本：</B><br/>
 */
public class DubboSpanUtils {
  public static String spanName(Invoker<?> invoker, Invocation invocation) {
    StringBuilder sb = new StringBuilder();
    String version = invoker.getUrl().getParameter("version");
    sb.append(invoker.getUrl().getParameter("interface")).append(":")
        .append(invocation.getMethodName()).append(":");
    if (version != null) {
      sb.append(version);
    }
    sb.append("(")
        .append(invoker.getUrl().getHost())
        .append(")");
    return sb.toString();
  }
}
