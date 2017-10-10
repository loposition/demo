package com.ysz.demo.metrics.hello.service.impl;

import com.ysz.demo.metrics.hello.service.IUserService;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/10/2 <br/>
 * <B>版本：</B><br/>
 */
public class UserServiceImpl implements IUserService {

  @Override
  public String findUsernameById(Long userId) {
    return "hello_" + userId;
  }
}

