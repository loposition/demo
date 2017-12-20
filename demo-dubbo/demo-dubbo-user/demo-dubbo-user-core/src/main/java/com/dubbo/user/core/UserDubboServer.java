package com.dubbo.user.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <B>描述：</B><br/> <B>作者：</B> carl.yu <br/> <B>时间：</B> 2017/12/4 <br/> <B>版本：</B><br/>
 */
public class UserDubboServer {

  public static void main(String[] args) throws Exception {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.register(UserConfiguration.class);
    ctx.refresh();
    System.err.println("user service 启动");
    System.in.read();
  }
}
