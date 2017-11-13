package com.ysz.demo.jdk8.image;

import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/10/16 <br/>
 * <B>版本：</B><br/>
 */
public class ApacheImagingDemo {

  private static byte[] readFully(String fileName) {
    FileInputStream inputStream = null;
    try {
      inputStream = new FileInputStream(fileName);
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      IOUtils.copy(inputStream, outputStream);
      return outputStream.toByteArray();
    } catch (Exception e) {
      return null;
    } finally {
      org.apache.commons.io.IOUtils.closeQuietly(inputStream);
    }
  }

  public static void main(String[] args) throws Exception {
    byte[] bytes = readFully("/Users/carl.yu/Downloads/demo.ico");
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
    BufferedImage image = ImageIO.read(byteArrayInputStream);
    System.err.println(image.getWidth());
    byteArrayInputStream.close();
  }
}
