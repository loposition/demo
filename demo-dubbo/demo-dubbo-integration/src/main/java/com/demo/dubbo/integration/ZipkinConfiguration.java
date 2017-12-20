package com.demo.dubbo.integration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.sleuth.DefaultSpanNamer;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.SpanNamer;
import org.springframework.cloud.sleuth.SpanReporter;
import org.springframework.cloud.sleuth.log.Slf4jSpanLogger;
import org.springframework.cloud.sleuth.log.SpanLogger;
import org.springframework.cloud.sleuth.metric.NoOpSpanMetricReporter;
import org.springframework.cloud.sleuth.metric.SpanMetricReporter;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.sleuth.trace.DefaultTracer;
import org.springframework.cloud.sleuth.zipkin.EndpointLocator;
import org.springframework.cloud.sleuth.zipkin.HttpZipkinSpanReporter;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanListener;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import zipkin.Endpoint;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/17 <br/>
 * <B>版本：</B><br/>
 */
@Configuration
public class ZipkinConfiguration {

  @Bean
  public DubboZipkinProperties dubboZipkinProperties() {
    return new DubboZipkinProperties();
  }

  @Bean
  public SpringContextHolder contextHolder() {
    return new SpringContextHolder();
  }

  @Bean
  @ConditionalOnMissingBean
  public DubboSpanExtractor dubboSpanExtractor() {
    return new DubboSpanExtractor(randomForSpanIds());
  }

  @Bean
  @ConditionalOnMissingBean
  public DubboSpanInjector dubboSpanInjector() {
    return new DubboSpanInjector();
  }

  @Bean
  public Random randomForSpanIds() {
    return new Random();
  }

  @Bean
  @ConditionalOnMissingBean
  public Sampler defaultTraceSampler() {
    return new AlwaysSampler();
  }

  @Bean
  @ConditionalOnMissingBean
  public SpanNamer spanNamer() {
    return new DefaultSpanNamer();
  }

  @Bean
  @ConditionalOnMissingBean
  public SpanMetricReporter noOpSpanReporterCounterService() {
    return new NoOpSpanMetricReporter();
  }


  @Bean
  @ConditionalOnMissingBean
  public ZipkinSpanReporter reporter() {
    DubboZipkinProperties dubboZipkinProperties = dubboZipkinProperties();
    return new HttpZipkinSpanReporter(dubboZipkinProperties.getBaseUrl(),
        Integer.valueOf(dubboZipkinProperties.getFlushInverval()),
        Boolean.valueOf(dubboZipkinProperties.getCompressionEnabled()),
        noOpSpanReporterCounterService());
  }

  @Bean
  @ConditionalOnMissingBean
  public SpanLogger spanLogger() {
    return new Slf4jSpanLogger("");
  }

  @Bean
  @ConditionalOnMissingBean
  public DefaultTracer sleuthTracer() {
    return new DefaultTracer(
        defaultTraceSampler(), randomForSpanIds(), spanNamer(), spanLogger(), zipkinSpanListener()
    );
  }


  private int getLocalAddress() {
    int result = 0;
    try {
      for (byte b : Inet4Address.getLocalHost().getAddress()) {
        result = result << 8 | (b & 0xFF);
      }
    } catch (UnknownHostException e) {
      throw new RuntimeException("解析本地地址失败", e);
    }
    return result;
  }

  @Bean
  public EndpointLocator endpointLocator() {
    DubboZipkinProperties dubboZipkinProperties = dubboZipkinProperties();
    return new EndpointLocator() {
      @Override
      public Endpoint local() {
        return Endpoint.create(
            dubboZipkinProperties.getApplicationName(),
            getLocalAddress(),
            Integer.valueOf(dubboZipkinProperties.getApplicationPort())
        );
      }
    };
  }

  @Bean
  public SpanReporter zipkinSpanListener() {
    return new ZipkinSpanListener(reporter(), endpointLocator());
  }
}
