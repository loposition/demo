package com.demo.dubbo.inventory.core;

import com.demo.dubbo.inventory.api.InventoryApi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <B>描述：</B><br/> <B>作者：</B> carl.yu <br/> <B>时间：</B> 2017/12/4 <br/> <B>版本：</B><br/>
 */
@Controller
public class InventoryController {

  @Resource
  private InventoryApi inventoryApi;

  @RequestMapping("inventory/test")
  @ResponseBody
  public String test() {
    return "ok";
  }

  @RequestMapping("inventory/list")
  @ResponseBody
  public String getInventory() {
    Long userId = 100L;
    return inventoryApi.getInventory(userId);
  }

}
