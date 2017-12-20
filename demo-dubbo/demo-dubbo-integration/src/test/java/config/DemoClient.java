package config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/18 <br/>
 * <B>版本：</B><br/>
 */
public class DemoClient {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
    DemoService demoService = ctx.getBean(DemoService.class);
    demoService.show();
  }
}
