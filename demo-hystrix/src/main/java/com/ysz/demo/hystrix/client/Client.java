package com.ysz.demo.hystrix.client;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/10/10 <br/>
 * <B>版本：</B><br/>
 */
public class Client {

  public static void main(String[] args) {
    SaturnClientImpl.getInstance().get();
  }
}
