package com.ysz.demo.base.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/25 <br/>
 * <B>版本：</B><br/>
 */
public class Log4jDemo {

  private static Logger logger = LoggerFactory.getLogger(Log4jDemo.class);

  public static void main(String[] args) {
    logger.error("你好啊:{}","carl");
  }
}
