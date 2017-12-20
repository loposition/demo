package com.demo.ysz.zipkin.user;

import brave.propagation.TraceContext;
import brave.propagation.TraceContextOrSamplingFlags;

/**
 * <B>描述：</B><br/> <B>作者：</B> carl.yu <br/> <B>时间：</B> 2017/12/4 <br/> <B>版本：</B><br/>
 */
public class DubboSpanExtractor implements TraceContext.Extractor{
  @Override
  public TraceContextOrSamplingFlags extract(Object carrier) {
    return null;
  }
}
