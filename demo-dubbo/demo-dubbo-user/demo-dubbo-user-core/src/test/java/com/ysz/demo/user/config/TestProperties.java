package com.ysz.demo.user.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/18 <br/>
 * <B>版本：</B><br/>
 */

@Getter
public class TestProperties {

  @Value("${username}")
  private String username;
}
