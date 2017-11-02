package com.itheima.openchina.cacheadmin;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sy_heima on 2016/8/15.
 */
public class HttpManager {

    private HttpManager() {

    }

    private static HttpManager sHttpManager = new HttpManager();

    public static HttpManager getInstance() {
        return sHttpManager;
    }

    //去网络获取数据
    public String dataGet(String url){



        try {
            OkHttpClient okHttpClient = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = okHttpClient.newCall(request).execute();

            //获取头
            Headers headers = response.headers();
            for (int i = 0; i < headers.size(); i++) {
                String name = headers.name(i);
                String value = headers.get(name);
                System.out.println("name:"+name+"---->value"+value);
                //Content-Type---->valuetext/html;charset=UTF-8
                //判断是否网页
            }

            return  response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
    }




}
