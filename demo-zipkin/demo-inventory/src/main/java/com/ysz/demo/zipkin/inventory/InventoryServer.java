package com.ysz.demo.zipkin.inventory;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * <B>描述：</B><br/> <B>作者：</B> carl.yu <br/> <B>时间：</B> 2017/12/2 <br/> <B>版本：</B><br/>
 */
public class InventoryServer {

  public static void main(String[] args) throws Exception {
    Server server = new Server(8080);
    ServletContextHandler handler = new ServletContextHandler();
    handler.setContextPath("/");
    AnnotationConfigWebApplicationContext ctx
        = new AnnotationConfigWebApplicationContext();
    ctx.register(InventoryConfiguration.class);
    handler.addEventListener(new ContextLoaderListener(ctx));
    handler.addServlet(new ServletHolder(new DispatcherServlet(ctx)), "/*");

    server.setHandler(handler);
    server.start();
    server.join();
  }
}
