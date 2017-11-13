package com.ysz.demo.oss;

import com.aliyun.oss.OSSClient;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/10/12 <br/>
 * <B>版本：</B><br/>
 */
public class ClientUtils {
  private static final String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
  private static final String accessKeyId = "LTAIYxquZ3dYc4dt";
  private static final String accessKeySecret = "Uy8QqEeZbYn7sM93rSIXXSMrhU3Weh";


  private static ThreadLocal<OSSClient> clientHolder = new ThreadLocal<>();

  public static OSSClient getClient() {
    OSSClient client = clientHolder.get();
    if (client == null) {
      client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
      clientHolder.set(client);
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        OSSClient ossClient = clientHolder.get();
        if (ossClient != null) {
          System.err.println("关闭ossclient");
          ossClient.shutdown();
        }
      }));
    }

    return client;
  }
}
