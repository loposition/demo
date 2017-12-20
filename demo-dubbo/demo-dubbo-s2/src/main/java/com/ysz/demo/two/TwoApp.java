package com.ysz.demo.two;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/18 <br/>
 * <B>版本：</B><br/>
 */
public class TwoApp {

  public static void main(String[] args) throws Exception {
    AnnotationConfigApplicationContext ctx
        = new AnnotationConfigApplicationContext(TwoConfig.class);
    System.err.println("two app start");
    System.in.read();
  }
}
