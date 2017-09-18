package com.ysz.demo.dubbo.client;

import com.duitang.result.ShortUrlResult;
import com.duitang.service.biz.IShortUrlService;

import com.demo.ysz.dubbo.api.HelloService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/18 <br/>
 * <B>版本：</B><br/>
 */
public class HelloDubboClient {

  public static void main(String[] args) throws Exception {
    String configPath = "classpath:dubbo-demo-consumer.xml";
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configPath.split("[,\\s]+"));
    context.start();
    System.err.println("consumer start ....");
    HelloService helloService = context.getBean(HelloService.class);
    helloService.add(1,2);
//    IShortUrlService shortUrlService = context.getBean(IShortUrlService.class);
//    ShortUrlResult huahua = shortUrlService.addShortUrlByDefaultLength("http://localhost:8080.zzz/a.tsgfdg", "huahua");
//    System.err.println("111:"+huahua);
//    System.in.read();
  }
}
