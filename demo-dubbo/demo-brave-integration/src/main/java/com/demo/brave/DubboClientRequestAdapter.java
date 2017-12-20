package com.demo.brave;

import com.alibaba.dubbo.rpc.Invocation;

import brave.SpanCustomizer;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/19 <br/>
 * <B>版本：</B><br/>
 */
public class DubboClientRequestAdapter implements SpanCustomizer {
  private Invocation invocation;

  private String name;

  @Override
  public SpanCustomizer name(String name) {
    Service service = (Service) invocation.getArguments()[0];
    this.name = service == null ? "unknown" : service.getName();
    return this;
  }

  @Override
  public SpanCustomizer tag(String key, String value) {
    return null;
  }

  @Override
  public SpanCustomizer annotate(String value) {
    return null;
  }

  @Override
  public SpanCustomizer annotate(long timestamp, String value) {
    return null;
  }
}
