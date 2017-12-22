package com.ysz.log2.maker;

import com.google.common.collect.Maps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.Map;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/12/21 <br/>
 * <B>版本：</B><br/>
 */
public class MyApp {
  private Logger logger = LogManager.getLogger(MyApp.class.getName());
  private static final Marker SQL_MARKER = MarkerManager.getMarker("SQL");
  private static final Marker UPDATE_MARKER = MarkerManager.getMarker("SQL_UPDATE").setParents(SQL_MARKER);
  private static final Marker QUERY_MARKER = MarkerManager.getMarker("SQL_QUERY").setParents(SQL_MARKER);

  public String doQuery(String table) {
    logger.traceEntry();

    logger.debug(QUERY_MARKER, "SELECT * FROM {}", table);

    String result = "";

    return logger.traceExit(result);
  }

  public String doUpdate(String table, Map<String, String> params) {
    logger.traceEntry();

    if (logger.isDebugEnabled()) {
      logger.debug(UPDATE_MARKER, "UPDATE {} SET {}", table, formatCols(Maps.newHashMap()));
    }

    String result = "result";

    return logger.traceExit(result);
  }

  private String formatCols(Map<String, String> cols) {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (Map.Entry<String, String> entry : cols.entrySet()) {
      if (!first) {
        sb.append(", ");
      }
      sb.append(entry.getKey()).append("=").append(entry.getValue());
      first = false;
    }
    return sb.toString();
  }

}
