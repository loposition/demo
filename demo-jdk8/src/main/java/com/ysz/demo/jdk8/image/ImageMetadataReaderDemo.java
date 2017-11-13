package com.ysz.demo.jdk8.image;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;

import java.io.File;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/10/13 <br/>
 * <B>版本：</B><br/>
 */
public class ImageMetadataReaderDemo {

  public static void main(String[] args) throws Exception {
    Metadata metadata =
        ImageMetadataReader
            .readMetadata(new File("/Users/carl.yu/IdeaProjects/src_code/demo/demo-jdk8/src/main/resources/pics/prev_photo_upload.png"));
    System.err.println(metadata);

  }
}
