package com.ysz.demo.jdk8.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/7 <br/>
 * <B>版本：</B><br/>
 */
public class TimeDemo {
  private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static void main(String[] args) {
    LocalDate now = LocalDate.now();
    TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
    System.err.println(now.with(fieldISO, 7).plusDays(2));
    System.err.println(now.with(fieldISO, 1).plusDays(1));
  }

}
