package com.ysz.demo.log;

import com.google.common.base.Splitter;

import org.slf4j.helpers.MessageFormatter;

import java.util.Collections;
import java.util.Map;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/13 <br/>
 * <B>版本：</B><br/>
 */
public class MessageFormatterDemo {

  private static String format(String format, Object... params) {
    return MessageFormatter.arrayFormat(format, params).getMessage();
  }

  public static void main(String[] args) {
    System.err.println(format(null, "A", "B"));
//    Splitter.on(",").split("1,2,A").forEach(x -> System.err.println(Long.valueOf(x)));
    Map<String, String>  map = Collections.emptyMap();
    System.err.println(map.get("1"));
  }

}
