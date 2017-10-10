package com.ysz.demo.oss.hello;

import com.aliyun.oss.OSSClient;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/10/9 <br/>
 * <B>版本：</B><br/>
 */
public class BucketCrudDemo {

  private static final String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
  private static final String accessKeyId = "LTAIYxquZ3dYc4dt";
  private static final String accessKeySecret = "Uy8QqEeZbYn7sM93rSIXXSMrhU3Weh";

  public static void main(String[] args) throws Exception {
    OSSClient client = null;
    try {
      client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
      createBucket(client, "test-dev-bucket-001");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (client != null) {
        client.shutdown();
      }
    }
  }


  public static void createBucket(OSSClient client, String bucketName) throws Exception {
    client.createBucket(bucketName);
    client.shutdown();
  }
}
