package com.itheima.openchina.config;

import android.content.Context;


import com.itheima.openchina.utils.NetWorkUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * User:user <p/>
 * Date: 2017/10/19 14:29 <p/>
 * Project:GooglePlay <p/>
 * Package:com.adminhj.googleplay.config <p/>
 * Desc:    <p/>
 */
@Deprecated
public class NetDataCacheConfig {
    private static OkHttpClient client;
    private Context context;

    public NetDataCacheConfig(Context context) {
        this.context = context;
    }

    public OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        File file = new File(context.getCacheDir(), "response_data");
        if (!file.exists()) {
            file.mkdirs();
        }
        //设置缓存 的大小
        Cache cache = new Cache(file, 1024 * 1024 * 20);
        builder.cache(cache);
        builder.addInterceptor(new CacheInterceptor());

        client = builder.build();
        return client;
    }

    private class CacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            boolean networkAvailable = NetWorkUtil.isNetworkAvailable(context);
            if (networkAvailable) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_NETWORK)//网络可用强制从网络获取数据
                        .build();
            } else {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)//网络不可用从缓存获取
                        .build();
            }
            Response response = chain.proceed(request);
            if (networkAvailable) {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        //有网络时设置超时为10分钟
                        .header("Cache-Control", "public, max-age=" + 60 * 10)
                        .build();

            } else {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        //无网络设置超时为一周
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + 7 * 24 *
                                60 * 60)
                        .build();
            }
            return response;
        }
    }
}
