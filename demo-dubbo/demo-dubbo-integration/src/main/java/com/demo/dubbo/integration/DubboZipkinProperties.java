package com.demo.dubbo.integration;


import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/18 <br/>
 * <B>版本：</B><br/>
 */
@Getter
@Component
public class DubboZipkinProperties {
  @Value(value = "${dubbo.zipkin.baseUrl:http://localhost:9411/}")
  private String baseUrl;

  @Value(value = "${dubbo.zipkin.flushInverval:1}")
  private String flushInverval;

  @Value(value = "${dubbo.zipkin.compressionEnabled:false}")
  private String compressionEnabled;

  @Value(value = "${application.name}")
  private String applicationName;

  @Value(value = "${application.port:55555}")
  private String applicationPort;
}
