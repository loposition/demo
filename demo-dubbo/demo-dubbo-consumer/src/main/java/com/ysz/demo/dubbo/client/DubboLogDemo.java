package com.ysz.demo.dubbo.client;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/20 <br/>
 * <B>版本：</B><br/>
 */
public class DubboLogDemo {
  public static void main(String[] args) {
    int SHOW_LOG_LENGTH = 30000;

    long size = 2844723309L;
    System.err.println(size);
    System.err.println(size > 30000);
    int pos = (int) (size - SHOW_LOG_LENGTH);
    System.err.println("pos:" + pos);
  }
}
