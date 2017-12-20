package com.dubbo.user.core;

import com.dubbo.user.api.UserApi;

import org.springframework.stereotype.Service;

/**
 * <B>描述：</B><br/> <B>作者：</B> carl.yu <br/> <B>时间：</B> 2017/12/4 <br/> <B>版本：</B><br/>
 */
@Service
public class UserApiImpl implements UserApi {
  @Override
  public String getUserById(Long userId) {
    return "carl" + userId;
  }
}
