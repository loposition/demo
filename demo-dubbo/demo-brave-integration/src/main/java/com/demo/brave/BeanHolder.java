package com.demo.brave;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import brave.Tracing;
import brave.sampler.Sampler;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

/**
 * 1. 由于不能保证在 dubbo filter 初始化的时候 spring 容器初始化
 * 2. 这里使用一种简单的单例对象保存所有相关的 trace 实例
 *
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/20 <br/>
 * <B>版本：</B><br/>
 */
@Getter
@Builder
@Data
public class BeanHolder {

  private Tracing tracing;
  private Sender sender;
  private AsyncReporter<Span> asyncReporter;
  private DubboClientAdapter dubboClientAdapter;
  private DubboServerAdapter dubboServerAdapter;
  private DubboClientParser dubboClientParser;
  private DubboServerParser dubboServerParser;
  private DubboClientHandler dubboClientHandler;
  private DubboServerHandler dubboServerHandler;


  public static BeanHolder getInstance() {
    return Singleton.instance;
  }

  private static class Singleton {
    private static BeanHolder instance;

    static {
      Sender sender = OkHttpSender.create(
          BraveDubboProperties.newInstance().baseUrl
      );
      AsyncReporter<Span> spanAsyncReporter = AsyncReporter.create(sender);
      Tracing tracing = Tracing.newBuilder()
          .localServiceName(BraveDubboProperties.newInstance().getApplicationName())
          .spanReporter(spanAsyncReporter)
          .sampler(Sampler.create(
              Float.valueOf(BraveDubboProperties.newInstance().getRate())
          )).build();

      DubboClientAdapter clientAdapter = new DubboClientAdapter();
      DubboServerAdapter serverAdapter = new DubboServerAdapter();
      DubboClientParser clientParser = new DubboClientParser();
      DubboServerParser serverParser = new DubboServerParser();
      DubboClientHandler clientHandler = new DubboClientHandler();
      clientHandler.setTracer(tracing.tracer());
      clientHandler.setAdapter(clientAdapter);
      clientHandler.setParser(clientParser);
      clientHandler.setCurrentTraceContext(tracing.currentTraceContext());

      DubboServerHandler serverHandler = new DubboServerHandler();
      serverHandler.setTracer(tracing.tracer());
      serverHandler.setAdapter(serverAdapter);
      serverHandler.setParser(serverParser);

      instance = BeanHolder.builder()
          .tracing(tracing)
          .sender(sender)
          .asyncReporter(spanAsyncReporter)
          .dubboClientAdapter(clientAdapter)
          .dubboServerAdapter(serverAdapter)
          .dubboClientParser(clientParser)
          .dubboServerParser(serverParser)
          .dubboClientHandler(clientHandler)
          .dubboServerHandler(serverHandler)
          .build();
    }
  }
}
