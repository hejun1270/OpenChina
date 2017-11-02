package com.itheima.openchina.config;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * User:user <p/>
 * Date: 2017/10/19 10:43 <p/>
 * Project:GooglePlay <p/>
 * Package:com.adminhj.googleplay.config <p/>
 * Desc:    <p/>
 */

public class ImageDiskChaceModle implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        /*设置Glide的内存缓存大小*/
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //设置图片占用缓存大小
        int imageMemorySize = maxMemory / 4;
        //设置内存缓存大小
        builder.setMemoryCache(new LruResourceCache(imageMemorySize));
        //获取默认的内存使用
        //MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        //int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        //int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        //设置BitmapPool缓存内存大小
        builder.setBitmapPool(new LruBitmapPool(imageMemorySize));


        /*设置Glide的磁盘缓存大小*/
        File cacheDir = context.getExternalCacheDir();
        int diskChaceSize = 1024 * 1024 * 30;//设置可缓存30M
        builder.setDiskCache(new DiskLruCacheFactory(cacheDir.getPath(), "imageChace",
                diskChaceSize));//设置磁盘缓存大小
/*
        //存放在data/data/xxxx/cache/
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "imageChace",
        diskChaceSize));
        //存放在外置文件浏览器
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, "imageChace",
        diskChaceSize));*/
        //设置图片解码格式
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);

    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
