package com.demo.ysz.zipkin.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <B>描述：</B><br/> <B>作者：</B> carl.yu <br/> <B>时间：</B> 2017/12/2 <br/> <B>版本：</B><br/>
 */
@RestController
public class UserApi {

  @RequestMapping("user/load")
  public String loadUser(){
    return "carl";
  }
}
