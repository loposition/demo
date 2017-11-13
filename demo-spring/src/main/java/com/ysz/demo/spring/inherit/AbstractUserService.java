package com.ysz.demo.spring.inherit;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/11/13 <br/>
 * <B>版本：</B><br/>
 */
public abstract class AbstractUserService implements UserService, InitializingBean {

  @Resource
  protected CoreService coreService;

  @Resource
  protected UserServiceFactory factory;

  @Override
  public void afterPropertiesSet() throws Exception {
    factory.put(getKey(), this);
  }

  public abstract Integer getKey();

}
