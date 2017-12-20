package com.ysz.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/18 <br/>
 * <B>版本：</B><br/>
 */
@Configuration
public class PropertyConfig {

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigurer() throws Exception {
    PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
    // 加载外部配置, 否则使用包内自带的配置
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    pspc.setLocations(resolver.getResources("classpath:application.properties"));
    pspc.setIgnoreUnresolvablePlaceholders(true);
    return pspc;
  }
}
