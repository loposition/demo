package com.ysz.demo.one;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.ysz.demo.DubboConfig;
import com.ysz.demo.PropertyConfig;
import com.ysz.demo.TwoService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/18 <br/>
 * <B>版本：</B><br/>
 */
@Configuration
@ComponentScan(basePackages = "com.ysz.demo.one")
@Import({
    PropertyConfig.class,
    DubboConfig.class
})
@EnableWebMvc
public class OneConfig {
  @Value(value = "${application.name}")
  private String applicationName;

  @Value(value = "${application.port:'55555'}")
  private String applicationPort;


  @Bean
  public ApplicationConfig applicationConfig() {
    ApplicationConfig applicationConfig = new ApplicationConfig();
    applicationConfig.setName(applicationName);
    applicationConfig.setOwner("carl");
    return applicationConfig;
  }

  @Bean
  public ReferenceBean<TwoService> twoService() {
    ReferenceBean<TwoService> ref = DubboConfig.configReference(TwoService.class);
    ref.setFilter("traceConsumerFilter");
    return ref;
  }

}
