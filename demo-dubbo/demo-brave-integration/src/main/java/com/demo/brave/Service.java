package com.demo.brave;

import java.util.Map;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/19 <br/>
 * <B>版本：</B><br/>
 */
public class Service {
  /**
   * 服务名，规范: 系统名+业务名+方法名,用"."隔开，如  "account.user.info"
   */
  private String name;
  /**
   * 调用参数
   */
  private Map data;

  public Service() {
  }

  public Service(String name, Map data) {
    super();
    this.name = name;
    this.data = data;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Map getData() {
    return data;
  }

  public void setData(Map data) {
    this.data = data;
  }
}
