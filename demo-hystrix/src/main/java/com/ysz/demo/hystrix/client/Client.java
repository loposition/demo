package com.ysz.demo.hystrix.client;

import com.ysz.demo.hystrix.client.command.SaturnServiceGetCommand;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/10/10 <br/>
 * <B>版本：</B><br/>
 */
public class Client {

  public static void main(String[] args) throws Exception {
    System.err.println("main方法线程:" + Thread.currentThread().getName());
    SaturnServiceGetCommand command = new SaturnServiceGetCommand();
    String result = command.queue().get();
    System.err.println(
        String.format(
            "[command_key:%s,线程:%s,结果:%s,是否超时:%s,是否失败:%s,异常类型:%s,是否执行降级:%s]",
            command.getCommandKey().name(),
            Thread.currentThread().getName(),
            result,
            command.isResponseTimedOut() + "",
            command.isFailedExecution() + "",
            command.getFailedExecutionException(),
            command.isResponseFromFallback() + ""
        ));
  }
}
