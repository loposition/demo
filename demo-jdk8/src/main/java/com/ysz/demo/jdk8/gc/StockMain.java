package com.ysz.demo.jdk8.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/11/6 <br/>
 * <B>版本：</B><br/>
 */
public class StockMain {

  static final int SIZE = 100 * 1024;
  static final int MB = 1024 * 1024;

  // -Xms1024m -Xmx1024m -XX:+HeapDumpOnOutOfMemoryError -XX:-OmitStackTraceInFastThrow -Xverify:none -XX:HeapDumpPath=/Users/carl.yu/java_error_in_idea.hprof -Xloggc:/Users/carl.yu/IdeaProjects/src_code/demo/demo-jdk8/src/main/resources/logs/gc.log -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=80 -XX:-PrintGCDetails
  // -Xms1024m -Xmx1024m -XX:+HeapDumpOnOutOfMemoryError -XX:-OmitStackTraceInFastThrow -Xverify:none -XX:HeapDumpPath=/Users/carl.yu/java_error_in_idea.hprof -Xloggc:/Users/carl.yu/IdeaProjects/src_code/demo/demo-jdk8/src/main/resources/logs/gc.log -XX:-PrintGCDetails -XX:+UseG1GC
  public static void main(String[] args) throws Exception {
    List<Byte[]> table = new ArrayList<>();
    List<Byte[]> tmp = new ArrayList<>();
    while (true) {
      table.add(new Byte[MB]);
      // 如果有
      if (table.size() > 100) {
        for (int i = 0; i < 5; i++) {
          tmp.add(table.get(i));
        }
        table.clear();
      }
      if (tmp.size() == 100) {
        tmp.clear();
      }
      Thread.sleep(1);
    }

  }
}
