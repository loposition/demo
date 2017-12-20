package com.demo.brave;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import brave.Tracing;
import brave.sampler.Sampler;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/19 <br/>
 * <B>版本：</B><br/>
 */
@Configuration
public class BraveDubboConfiguration {

  @Bean
  public BeanUtils beanUtils() {
    return new BeanUtils();
  }

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


  @Bean
  public Tracing tracing() {
    return Tracing.newBuilder()
        .localServiceName("")
        .spanReporter(spanAsyncReporter())
        .sampler(Sampler.create(1.0f)).build();
  }

  @Bean
  public DubboClientAdapter dubboClientAdapter() {
    return new DubboClientAdapter();
  }

  @Bean
  public DubboClientParser dubboClientParser() {
    return new DubboClientParser();
  }

  @Bean
  public DubboClientHandler dubboClientHandler() {
    DubboClientHandler dubboClientHandler = new DubboClientHandler();
    dubboClientHandler.setTracer(tracing().tracer());
    dubboClientHandler.setAdapter(dubboClientAdapter());
    dubboClientHandler.setParser(dubboClientParser());
    dubboClientHandler.setCurrentTraceContext(tracing().currentTraceContext());
    return dubboClientHandler;
  }
}
