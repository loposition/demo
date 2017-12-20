package com.ysz.demo;

import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.alibaba.dubbo.config.spring.ServiceBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/18 <br/>
 * <B>版本：</B><br/>import com.ysz.demo.DubboConfig;
 import com.ysz.demo.PropertyConfig;
 */
@Configuration
public class DubboConfig {

  @Bean
  public RegistryConfig registry() {
    RegistryConfig registryConfig = new RegistryConfig();
    registryConfig.setAddress("127.0.0.1:2181");
    registryConfig.setProtocol("zookeeper");
    registryConfig.setRegister(true);
    return registryConfig;
  }

  /*引用服务*/
  public static <T> ReferenceBean<T> configReference(Class<T> serviceReference) {
    ReferenceBean<T> ref = new ReferenceBean<>();
    ref.setInterface(serviceReference);
    ref.setCheck(false);
    ref.setTimeout(2000);
//    ref.setFilter("traceConsumerFilter");
    return ref;
  }

  /*暴露服务*/
  public static <T> ServiceBean<T> configService(T userService) {
    ServiceBean<T> serviceBean = new ServiceBean<>();
    serviceBean.setProxy("javassist");
    serviceBean.setInterface(userService.getClass().getInterfaces()[0].getName());
    serviceBean.setRef(userService);
//    serviceBean.setFilter("traceProviderFilter");
    serviceBean.setTimeout(2000);
    return serviceBean;
  }
}
