package com.ysz.demo.hystrix.client;

import com.ysz.demo.hystrix.saturn.SaturnService;

import org.springframework.web.client.RestTemplate;

/**
 * <B>描述：</B>客户端实现类，其中封装 http 网络调用<br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/10/10 <br/>
 * <B>版本：</B><br/>
 */
public class SaturnClientImpl implements SaturnService {

  private final RestTemplate restTemplate = new RestTemplate();

  @Override
  public String get() {
    String result = restTemplate.getForObject("http://localhost:8080/saturn/get/", String.class);
    System.err.println("客户端获取:" + result);
    return result;
  }

  public static SaturnClientImpl getInstance() {
    return SingletonHolder.instance;
  }

  private static class SingletonHolder {
    private static SaturnClientImpl instance;

    static {
      instance = new SaturnClientImpl();
    }
  }
}
