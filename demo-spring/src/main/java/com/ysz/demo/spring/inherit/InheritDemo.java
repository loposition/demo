package com.ysz.demo.spring.inherit;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/11/13 <br/>
 */

public class InheritDemo {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(InheritConfig.class);
    UserServiceFactory userServiceFactory = ctx.getBean(UserServiceFactory.class);
    UserService first = userServiceFactory.get(1);
    UserService second = userServiceFactory.get(2);
    first.hello();
    second.hello();

  }
}
