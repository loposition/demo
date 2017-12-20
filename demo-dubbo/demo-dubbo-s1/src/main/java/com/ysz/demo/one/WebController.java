package com.ysz.demo.one;

import com.ysz.demo.OneService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/18 <br/>
 * <B>版本：</B><br/>
 */
@RestController
public class WebController {

  @Resource
  private OneService oneService;

  @RequestMapping(value = "/test/ok")
  public String ok() {
    return oneService.one();
  }
}
