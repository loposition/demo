package com.ysz.demo.jdk8.image;


import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/10/12 <br/>
 * <B>版本：</B><br/>
 */
public class ImageIoDemo {

  public static void main(String[] args) throws Exception {
    File file = new File("/Users/carl.yu/IdeaProjects/src_code/demo/demo-jdk8/src/main/resources/pics/prev_photo_upload.png");
    FileInputStream inputStream = new FileInputStream(file);
    Tika tika = new Tika();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    IOUtils.copy(inputStream, outputStream);
    IOUtils.closeQuietly(inputStream);
  }

}
