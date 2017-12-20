package com.ysz.demo.base.exception;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/7 <br/>
 * <B>版本：</B><br/>
 */
public class InnerClass {

  public void inner() {
    call();
  }

  public void call() {
    int a = 1;
    a++;
    if (a == 2) {
      throw new InnerException("inner");
    }
  }

}
