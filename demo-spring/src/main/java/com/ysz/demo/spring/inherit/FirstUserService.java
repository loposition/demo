package com.ysz.demo.spring.inherit;

import org.springframework.stereotype.Service;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/11/13 <br/>
 * <B>版本：</B><br/>
 */
@Service
public class FirstUserService extends AbstractUserService {
  @Override
  public void hello() {
    coreService.hello();
  }


  @Override
  public Integer getKey() {
    return 1;
  }
}
