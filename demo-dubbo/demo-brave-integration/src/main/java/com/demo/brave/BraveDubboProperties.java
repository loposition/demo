package com.demo.brave;

import com.alibaba.fastjson.util.IOUtils;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/20 <br/>
 * <B>版本：</B><br/>
 */
@Getter
public class BraveDubboProperties {
  private String enabled = "false";

  public String baseUrl;

  private String rate;

  private String compressionEnabled;

  private String applicationName;

  private String applicationPort;


  public static BraveDubboProperties newInstance() {
    return SingletonHolder.instance;
  }

  private static class SingletonHolder {
    private static BraveDubboProperties instance;

    static {
      instance = new BraveDubboProperties();
      Properties properties = new Properties();
      InputStream stream = null;
      try {
        stream = Thread.currentThread()
            .getContextClassLoader().getResourceAsStream("application.properties");
        properties.load(stream);
      } catch (IOException e) {
      } finally {
        IOUtils.close(stream);
      }
      instance.baseUrl = Objects.toString(
          properties.getOrDefault("dubbo.zipkin.baseUrl", "http://127.0.0.1:9411/api/v2/spans")
      );
      instance.rate = Objects.toString(
          properties.getOrDefault("dubbo.zipkin.rate", "1")
      );

      instance.compressionEnabled = Objects.toString(
          properties.getOrDefault("dubbo.zipkin.compressionEnabled:false", "true")
      );

      instance.applicationName = Objects.toString(
          properties.getOrDefault("application.name", "unknown")
      );

      instance.applicationPort = Objects.toString(
          properties.getOrDefault("application.port:55555", "-1")
      );


    }

  }

}

