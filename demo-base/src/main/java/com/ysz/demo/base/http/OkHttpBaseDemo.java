package com.ysz.demo.base.http;

import lombok.extern.slf4j.Slf4j;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/25 <br/>
 * <B>版本：</B><br/>
 */
@Slf4j
public class OkHttpBaseDemo {

  public static final MediaType JSON
      = MediaType.parse("application/json; charset=utf-8");

  private static void login(OkHttpClient client) {

    //构造请求参数
    RequestBody body = new FormBody.Builder()
        .add("ccode", "")
        .add("login_name", "雪糕")
        .add("next", "/")
        .add("pswd", "123456")
        .add("remember", "")
        .build();

    Request request = new Request.Builder()
        .url("https://www-t000.duitang.net/login/")
        .post(body)
        .addHeader("cookie", "__dtac=\"\"; username=%E9%9B%AA%E7%B3%95; _auth_user_id=10379401; Hm_lvt_d8276dcc8bdfef6bb9d5bc9e3bcfcaf4=1504497258,1505199273,1505383829,1505383858; Hm_lpvt_d8276dcc8bdfef6bb9d5bc9e3bcfcaf4=1506312350; js=1; sessionid=f856df6c-de89-4567-8189-f471b6599509")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .addHeader("X-Requested-With", "XMLHttpRequest")
        .addHeader("Origin", "https://www-t000.duitang.net")
        .build();

    Call call = client.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {

      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        System.err.println("至少成功了");

      }
    });
  }


  public static void main(String[] args) throws Exception {
    ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();
    OkHttpClient client = new OkHttpClient.Builder().build();
    login(client);
  }


}
