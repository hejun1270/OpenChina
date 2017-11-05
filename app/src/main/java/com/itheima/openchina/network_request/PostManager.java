package com.itheima.openchina.network_request;

import java.io.IOException;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/4 <p/>
 * Time: 20:59 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.network_request <p/>
 * Desc:post网络请求管理类
 */

public class PostManager {
    private static PostManager instance = new PostManager();

    private PostManager() {
    }

    public static PostManager getInstance() {
        return instance;
    }

    /**
     * json字符串提交post请求
     */
    public void jsonPost(String json, String url, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * 表单请求
     */
    public void formPost(Map<String, String> params, String url, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            formBuilder.add(key, value);
        }
        Request request = new Request.Builder()
                .url(url)
                .post(formBuilder.build())
                .build();
        client.newCall(request).enqueue(callback);
    }
}
