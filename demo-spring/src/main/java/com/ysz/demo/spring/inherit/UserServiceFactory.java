package com.ysz.demo.spring.inherit;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/11/13 <br/>
 * <B>版本：</B><br/>
 */
@Service
public class UserServiceFactory {

  private ConcurrentHashMap<Integer, UserService> table = new ConcurrentHashMap<>();

  public void put(Integer key, UserService service) {
    table.putIfAbsent(key, service);
  }

  public UserService get(Integer key) {
    return table.get(key);
  }


}
