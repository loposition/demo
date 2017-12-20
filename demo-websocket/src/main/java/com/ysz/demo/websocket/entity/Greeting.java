package com.ysz.demo.websocket.entity;

/**
 * <B>描述：</B><br/> <B>作者：</B> carl.yu <br/> <B>时间：</B> 2017/11/28 <br/> <B>版本：</B><br/>
 */
public class Greeting {
  private String content;

  public Greeting() {
  }

  public Greeting(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }
}
