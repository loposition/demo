package com.demo.ysz.zipkin.user;

import com.google.common.collect.Lists;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import brave.Tracing;
import brave.context.log4j2.ThreadContextCurrentTraceContext;
import brave.http.HttpTracing;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import brave.spring.web.TracingClientHttpRequestInterceptor;
import brave.spring.webmvc.TracingHandlerInterceptor;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

import java.util.List;

/**
 * <B>描述：</B><br/> <B>作者：</B> carl.yu <br/> <B>时间：</B> 2017/12/2 <br/> <B>版本：</B><br/>
 */
@EnableWebMvc
@ComponentScan("com.demo.ysz.zipkin.user")
public class UserConfiguration extends WebMvcConfigurerAdapter {

  // Configuration for how to send spans to Zipkin
  @Bean
  public Sender sender() {
    return OkHttpSender.create(
        "http://127.0.0.1:9411/api/v2/spans"
    );
  }

  /**
   * Configuration for how to buffer spans into message for zipkin
   */
  @Bean
  public AsyncReporter<Span> spanAsyncReporter() {
    return AsyncReporter.create(sender());
  }

  /* Controls aspects of tracing such as the name that show up in the UI */
  @Bean
  public Tracing tracing() {
    return Tracing.newBuilder()
        .localServiceName("user")
        .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user-name"))
        .currentTraceContext(ThreadContextCurrentTraceContext.create())
        .spanReporter(spanAsyncReporter())
        .build();
  }

  @Bean
  public HttpTracing httpTracing() {
    return HttpTracing.create(tracing());
  }


  @Bean
  private RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    List<ClientHttpRequestInterceptor> interceptors =
        Lists.newArrayList(restTemplate.getInterceptors());
    interceptors.add(TracingClientHttpRequestInterceptor.create(httpTracing()));
    restTemplate.setInterceptors(interceptors);
    return restTemplate;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    super.addInterceptors(registry);
    registry.addInterceptor(
        TracingHandlerInterceptor.create(httpTracing())
    );
    System.err.println("服务器端加载了拦截器");
  }
}
