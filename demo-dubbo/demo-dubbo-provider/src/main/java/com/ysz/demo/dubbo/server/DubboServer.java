package com.ysz.demo.dubbo.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/18 <br/>
 * <B>版本：</B><br/>
 */
public class DubboServer {
  public static void main(String[] args) throws Exception{
    String configPath = "classpath:dubbo-demo-provider.xml";
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configPath.split("[,\\s]+"));
    context.start();
    System.err.println("server start ....");
    System.in.read();
  }

}
