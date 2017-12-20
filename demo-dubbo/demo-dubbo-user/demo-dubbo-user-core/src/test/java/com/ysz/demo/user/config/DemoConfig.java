package com.ysz.demo.user.config;

import com.dubbo.user.core.PropertyConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/18 <br/>
 * <B>版本：</B><br/>
 */
@Configuration

public class DemoConfig {

  @Bean
  public TestProperties testProperties() {
    return new TestProperties();
  }


  @Bean
  public TestService testService() {
    System.err.println("username:" + testProperties().getUsername());
    return new TestServiceImpl();
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext
        (PropertyConfig.class, DemoConfig
            .class);
    TestService testService = ctx.getBean(TestService.class);
    testService.show();
  }
}
