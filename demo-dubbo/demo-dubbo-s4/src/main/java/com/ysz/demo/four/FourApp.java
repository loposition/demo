package com.ysz.demo.four;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/19 <br/>
 * <B>版本：</B><br/>
 */
public class FourApp {
  public static void main(String[] args) throws Exception {
    AnnotationConfigApplicationContext
        ctx = new AnnotationConfigApplicationContext(FourConfig.class);
    System.err.println("four app start");
    System.in.read();
  }
}
