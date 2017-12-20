package com.ysz.demo.four;

import com.ysz.demo.FourService;

import org.springframework.stereotype.Service;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/19 <br/>
 * <B>版本：</B><br/>
 */
@Service
public class FourServiceImpl implements FourService {

  @Override
  public String four() {
    return "resp from four";
  }
}
