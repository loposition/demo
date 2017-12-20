package config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
  @ConditionalOnMissingBean
  public DemoService demoService() {
    return new DemoServiceImpl();
  }
}
