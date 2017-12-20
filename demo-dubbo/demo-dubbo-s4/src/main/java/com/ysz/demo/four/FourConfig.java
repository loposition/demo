package com.ysz.demo.four;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.demo.dubbo.integration.ZipkinConfiguration;
import com.ysz.demo.DubboConfig;
import com.ysz.demo.FourService;
import com.ysz.demo.PropertyConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/19 <br/>
 * <B>版本：</B><br/>
 */
@Configuration
@ComponentScan(basePackages = "com.ysz.demo.four")
@Import({
    PropertyConfig.class,
    DubboConfig.class,
    ZipkinConfiguration.class
})
public class FourConfig {
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
  public ServiceBean<FourService> dubboFourService(FourService fourService) {
    return DubboConfig.configService(fourService);
  }
}
