package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/18 <br/>
 * <B>版本：</B><br/>
 */
@Configuration
@Import(DemoConfig.class)
public class Config {

//  @Bean
//  public DemoService demoService() {
//    return new AnotherDemoServiceImpl();
//  }
}
