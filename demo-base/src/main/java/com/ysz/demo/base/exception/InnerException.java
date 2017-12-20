package com.ysz.demo.base.exception;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/7 <br/>
 * <B>版本：</B><br/>
 */
public class InnerException extends RuntimeException {
  public InnerException() {
    super();
  }

  public InnerException(String message) {
    super(message);
  }

  public InnerException(String message, Throwable cause) {
    super(message, cause);
  }

  public InnerException(Throwable cause) {
    super(cause);
  }

  protected InnerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
