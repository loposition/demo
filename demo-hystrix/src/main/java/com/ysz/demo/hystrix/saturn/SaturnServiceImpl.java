package com.ysz.demo.hystrix.saturn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/10/10 <br/>
 * <B>版本：</B><br/>
 */
@RestController
@RequestMapping("/saturn")
public class SaturnServiceImpl implements SaturnService {

  @RequestMapping("/get/")
  @Override
  public String get() {
    try {
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "ok";
  }
}
