package com.ysz.demo.two;

import com.ysz.demo.FourService;
import com.ysz.demo.TwoService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/18 <br/>
 * <B>版本：</B><br/>
 */
@Service
public class TwoServiceImpl implements TwoService {

  @Resource
  private FourService fourService;


  @Override
  public String two() {
    System.err.println("有一次请求:two");
    try {
      Thread.sleep(100L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "Response from 2:" + fourService.four();
  }
}
