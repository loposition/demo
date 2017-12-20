package com.dubbo.user.core;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.demo.dubbo.integration.ZipkinConfiguration;
import com.dubbo.user.api.UserApi;

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
@ComponentScan(basePackages = "com.dubbo.user.core")
@Import({PropertyConfig.class, ZipkinConfiguration.class})
public class UserConfiguration {
  @Bean
  public RegistryConfig registry() {
    RegistryConfig registryConfig = new RegistryConfig();
    registryConfig.setAddress("127.0.0.1:2181");
    registryConfig.setProtocol("zookeeper");
    registryConfig.setRegister(true);
    return registryConfig;
  }

  @Bean
  public ApplicationConfig applicationConfig() {
    ApplicationConfig applicationConfig = new ApplicationConfig();
    applicationConfig.setName("user");
    applicationConfig.setOwner("carl");
    return applicationConfig;
  }


  @Bean
  public ProtocolConfig protocol() {
    ProtocolConfig protocolConfig = new ProtocolConfig();
    protocolConfig.setPort(31015);
    protocolConfig.setSerialization("fastjson");
    return protocolConfig;
  }

  @Bean
  public ProviderConfig provider() {
    return new ProviderConfig();
  }

  @Bean
  public ServiceBean<UserApi> userApi(UserApi userApi) {
    return configService(userApi);
  }

  private <T> ServiceBean<T> configService(T userService) {
    ServiceBean<T> serviceBean = new ServiceBean<>();
    serviceBean.setProxy("javassist");
    serviceBean.setInterface(userService.getClass().getInterfaces()[0].getName());
    serviceBean.setRef(userService);
    serviceBean.setTimeout(2000);
    return serviceBean;
  }
}
