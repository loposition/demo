package com.demo.dubbo.integration;

import org.springframework.beans.BeansException;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/17 <br/>
 * <B>版本：</B><br/>
 */
public class SpringContextHolder implements ApplicationContextAware {

  private static ApplicationContext ctx = null;


  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ctx = applicationContext;
  }

  public static Tracer getTracer() {
    return ctx.getBean("sleuthTracer", Tracer.class);
  }


  public static boolean isTrace() {
    return ctx != null && ctx.containsBean("sleuthTracer");
  }

  public static boolean constainsBean(String beanName) {
    return ctx.containsBean(beanName);
  }

  public static <T> T getBean(String name, Class<T> clz) {
    return ctx.getBean(name, clz);
  }

  public static <T> T getBean(Class<T> clz) {
    return ctx.getBean(clz);
  }

}
