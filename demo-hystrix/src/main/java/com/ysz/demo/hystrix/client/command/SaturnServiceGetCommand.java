package com.ysz.demo.hystrix.client.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.ysz.demo.hystrix.client.SaturnClientImpl;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/10/10 <br/>
 * <B>版本：</B><br/>
 */

public class SaturnServiceGetCommand extends HystrixCommand<String> {

  private static final String GROUP_KEY = "SATURN_CORE_GROUP";
  private static final String THREAD_POOL_KEY = "SATURN_CORE_THREAD_POOL";

  public SaturnServiceGetCommand() {
    super(setter(GROUP_KEY, THREAD_POOL_KEY));
  }

  @Override
  protected String run() throws Exception {
    System.out.println("执行run方法的线程是:" + Thread.currentThread().getName());
    return SaturnClientImpl.getInstance().get();
  }

  @Override
  protected String getFallback() {
    System.out.println("执行fallback方法的线程是:" + Thread.currentThread().getName());
    return "fallbacks";
  }

  private static HystrixCommand.Setter setter(String groupKey, String threadPoolKey) {

    //2. 设置线程池相关配置
    HystrixThreadPoolProperties.Setter threadProperties =
        HystrixThreadPoolProperties.defaultSetter()
            .withCoreSize(10)
            .withMaximumSize(10)
            .withAllowMaximumSizeToDivergeFromCoreSize(false)
            .withKeepAliveTimeMinutes(1)
            .withMaxQueueSize(5)
            .withQueueSizeRejectionThreshold(1);

    //3. 设置 command 参数
    HystrixCommandProperties.Setter commandProperties =
        HystrixCommandProperties.Setter()
            /*execution*/
            .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
            .withExecutionTimeoutInMilliseconds(100)
            .withExecutionTimeoutEnabled(true)
            .withExecutionIsolationThreadInterruptOnTimeout(true)
            /*fallback*/
            .withFallbackEnabled(true)
            .withFallbackIsolationSemaphoreMaxConcurrentRequests(10)
            /*circuit breaker*/
            .withCircuitBreakerEnabled(true)
            .withCircuitBreakerRequestVolumeThreshold(20)
            .withCircuitBreakerSleepWindowInMilliseconds(5000)
            .withCircuitBreakerErrorThresholdPercentage(50);

    return HystrixCommand.Setter
        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(threadPoolKey))
        .andThreadPoolPropertiesDefaults(threadProperties)
        .andCommandPropertiesDefaults(commandProperties)
        ;
  }
}
