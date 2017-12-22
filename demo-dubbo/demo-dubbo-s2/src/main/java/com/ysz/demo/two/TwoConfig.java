package com.ysz.demo.two;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.demo.brave.DubboBraveContants;
import com.ysz.demo.DubboConfig;
import com.ysz.demo.FourService;
import com.ysz.demo.PropertyConfig;
import com.ysz.demo.TwoService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/18 <br/>
 * <B>版本：</B><br/>
 */
@Configuration
@ComponentScan(basePackages = "com.ysz.demo.two")
@Import({
    PropertyConfig.class,
    DubboConfig.class
})
public class TwoConfig {

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
  public ProtocolConfig protocol() {
    ProtocolConfig protocolConfig = new ProtocolConfig();
    protocolConfig.setPort(Integer.valueOf(applicationPort));
    protocolConfig.setSerialization("fastjson");
    return protocolConfig;
  }

  @Bean
  public ProviderConfig provider() {
    return new ProviderConfig();
  }

  @Bean
  public ReferenceBean<FourService> fourService() {
    ReferenceBean<FourService> ref = DubboConfig.configReference(FourService.class);
    ref.setFilter(DubboBraveContants.CONSUMER_FILTER);
    return ref;
  }

  @Bean
  public ServiceBean<TwoService> dubboTwoService(TwoService twoService) {
    ServiceBean<TwoService> ref =
        DubboConfig.configService(twoService);
    ref.setFilter(DubboBraveContants.PROVIDER_FILTER);
    return ref;
  }
}
