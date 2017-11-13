package com.ysz.demo.oss;

import com.aliyun.oss.OSSClient;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/10/12 <br/>
 * <B>版本：</B><br/>
 */
public class UploadFileDemo {
  public static void main(String[] args) throws Exception {
    OSSClient ossClient = ClientUtils.getClient();
    String filePath = "/Users/carl.yu/work/duitang/pics/prev_photo_upload.png";
    String key = genKey("hello.png");
    System.err.println(key);
    ossClient.putObject("carl-pic-warehouse", key, new File(filePath));
  }

  private static String genKey(String fileName) {
    StringBuilder key = new StringBuilder();
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/");
    key.append(format.format(new Date())).append(fileName);
    return key.toString();
  }
}
