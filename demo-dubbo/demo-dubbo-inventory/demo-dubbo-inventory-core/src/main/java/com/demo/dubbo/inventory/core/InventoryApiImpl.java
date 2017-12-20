package com.demo.dubbo.inventory.core;

import com.demo.dubbo.inventory.api.InventoryApi;
import com.dubbo.user.api.UserApi;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <B>描述：</B><br/> <B>作者：</B> carl.yu <br/> <B>时间：</B> 2017/12/4 <br/> <B>版本：</B><br/>
 */
@Service
public class InventoryApiImpl implements InventoryApi {

  @Resource
  private UserApi userApi;

  @Override
  public String getInventory(Long userId) {
    String user = userApi.getUserById(userId);
    return user + "-inventory";
  }
}
