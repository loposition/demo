package com.ysz.demo.dubbo.api.impl;

import com.demo.ysz.dubbo.api.HelloService;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/18 <br/>
 * <B>版本：</B><br/>
 */
public class HelloServiceImpl implements HelloService {
  @Override
  public int add(int i, int j) {
    return i + j;
  }
}
