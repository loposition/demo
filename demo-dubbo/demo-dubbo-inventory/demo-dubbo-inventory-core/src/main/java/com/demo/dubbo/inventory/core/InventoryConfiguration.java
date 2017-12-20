package com.demo.dubbo.inventory.core;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.demo.dubbo.integration.ZipkinConfiguration;
import com.dubbo.user.api.UserApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/17 <br/>
 * <B>版本：</B><br/>
 */
@Configuration
@ComponentScan("com.demo.dubbo.inventory.core")
@EnableWebMvc
@Import(ZipkinConfiguration.class)
public class InventoryConfiguration {


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
    applicationConfig.setName("consumer");
    applicationConfig.setOwner("carl");
    return applicationConfig;
  }

  @Bean
  public ReferenceBean<UserApi> userApi() {
    return configReference(UserApi.class);
  }

  /**
   * 基本配置类
   *
   * @param serviceReference 接口
   */
  private <T> ReferenceBean<T> configReference(Class<T> serviceReference) {
    ReferenceBean<T> ref = new ReferenceBean<>();
    ref.setInterface(serviceReference);
    ref.setCheck(false);
    ref.setTimeout(2000);
    return ref;
  }
}
