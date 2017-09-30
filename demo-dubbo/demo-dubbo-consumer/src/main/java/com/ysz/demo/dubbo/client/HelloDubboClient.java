package com.ysz.demo.dubbo.client;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/18 <br/>
 * <B>版本：</B><br/>
 */
public class HelloDubboClient {
  private static final String[] productionIPRanges = new String[]{"^192\\.168\\.172\\.(1[2-9]|[2-9][0-9]|1([0-9][0-9])|2([0-4][0-9]|5[0-4]))$", "^10\\.0|1\\.1\\.([1-9][0-9]|1([0-9][0-9])|2([0-4][0-9]|5[0-5]))$", "^10\\.0|1\\.2\\.([1-9][0-9]|1([0-9][0-9])|2([0-4][0-9]|5[0-5]))$", "^10\\.0|1\\.3\\.([1-9][0-9]|1([0-9][0-9])|2([0-4][0-9]|5[0-5]))$"};

  public static boolean isProduction(String ip) {
    String[] arr$ = productionIPRanges;
    int len$ = arr$.length;

    for(int i$ = 0; i$ < len$; ++i$) {
      String ipr = arr$[i$];
      Pattern r = Pattern.compile(ipr);
      Matcher m = r.matcher(ip);
      if(m.find()) {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) throws Exception {
    System.err.println(isProduction("10.1.1.84"));
  }
}
