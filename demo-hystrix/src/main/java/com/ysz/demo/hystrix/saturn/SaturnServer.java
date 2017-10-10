package com.ysz.demo.hystrix.saturn;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/10/10 <br/>
 * <B>版本：</B><br/>
 */
public class SaturnServer {
  public static void main(String[] args) throws Exception {
    Server server = new Server(8080);

    ServletContextHandler springMvcHanlder = new ServletContextHandler();
    springMvcHanlder.setContextPath("/");
    XmlWebApplicationContext ctx = new XmlWebApplicationContext();
    ctx.setConfigLocation("classpath:spring/saturn-server-web.xml");
    springMvcHanlder.addEventListener(new ContextLoaderListener(ctx));
    springMvcHanlder.addServlet(new ServletHolder(new DispatcherServlet(ctx)), "/*");

    server.setHandler(springMvcHanlder);
    server.start();
    server.join();
  }
}
