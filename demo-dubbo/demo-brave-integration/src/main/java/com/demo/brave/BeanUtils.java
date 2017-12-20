package com.demo.brave;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import brave.Tracing;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/19 <br/>
 * <B>版本：</B><br/>
 */
public class BeanUtils implements ApplicationContextAware {

  private static ApplicationContext ctx;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ctx = applicationContext;
  }

  public static <T> T getBean(Class<T> clz) {
    return ctx.getBean(clz);
  }


  public static Tracing getTracing() {
    return (Tracing) ctx.getBean("tracing");
  }
}
