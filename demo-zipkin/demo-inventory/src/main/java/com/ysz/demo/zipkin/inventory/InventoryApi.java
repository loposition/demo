package com.ysz.demo.zipkin.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <B>描述：</B><br/> <B>作者：</B> carl.yu <br/> <B>时间：</B> 2017/12/2 <br/> <B>版本：</B><br/>
 */
@RestController
public class InventoryApi {
  
  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping("inventory/list")
  public String showInventory() {
    return restTemplate.getForObject("http://127.0.0.1:8082/user/load", String.class);
  }
}
