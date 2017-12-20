package com.ysz.demo.base.exception;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/7 <br/>
 * <B>版本：</B><br/>
 */
public class OutterClass {
  private InnerClass innerClass = new InnerClass();

  public void out() {
    int b = 1;
    try {
      innerClass.call();
    } catch (Exception e) {
      throw new OutterException("OUT", e);
    }
  }

  public static void main(String[] args) {
    new OutterClass().out();
  }
}
