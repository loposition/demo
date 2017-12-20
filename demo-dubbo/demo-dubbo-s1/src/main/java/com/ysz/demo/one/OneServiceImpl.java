package com.ysz.demo.one;

import com.ysz.demo.OneService;
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
public class OneServiceImpl implements OneService {

  @Resource
  private TwoService twoService;

  @Override
  public String one() {
    return "resp from one:[" + twoService.two() + "]";
  }
}
