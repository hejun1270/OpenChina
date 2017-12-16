package com.itheima.openchina.cacheadmin;

import android.text.TextUtils;


import com.itheima.openchina.utils.GsonUtil;

import java.lang.reflect.Type;
import java.util.List;

/**
 * user:doctorhe
 * 北京传智播客android黑马程序员104期
 */
//可以看做一个工具类
public class LoadData {

    private LoadData() {

    }

    private static LoadData sLoadData = new LoadData();


    public static LoadData getInstance() {
        return sLoadData;
    }

    /**
     * 从网络获取string字符串
     *
     * @param url
     * @return
     */
    public String getStringData(String url) {

        //1. 去网络获取数据
        String content = HttpManager.getInstance().dataGet(url);
        //2. 判断当前的数据是否为空
        if (TextUtils.isEmpty(content)) {
            //如果是空
            //去缓存类中去取数据
            content = CacheManger.getInstance().getCacheData(url);

//            System.out.println("当前缓存数据:"+content.length());
        } else {
            //不为空
            //保存数据,刷新缓存数据
            CacheManger.getInstance().saveData(content, url);
        }

        //到这一步为止,我们已经得到数据了
        //解析一把
        return content;
        //走到这一步就可以去解析了
        //        return null;
    }

    /**
     * 从网络获取string字符串
     * 需要传入cookie
     *
     * @param url
     * @param cookie
     * @return
     */
    public String  getStringData(String url, String cookie) {

        //1. 去网络获取数据
        String content = HttpManager.getInstance().dataGet(url, cookie);
        //2. 判断当前的数据是否为空
        if (TextUtils.isEmpty(content)) {
            //如果是空
            //去缓存类中去取数据
            content = CacheManger.getInstance().getCacheData(url);

//            System.out.println("当前缓存数据:"+content.length());
        } else {
            //不为空
            //保存数据,刷新缓存数据
            CacheManger.getInstance().saveData(content, url);
        }

        //到这一步为止,我们已经得到数据了
        //解析一把
        return content;
        //走到这一步就可以去解析了
        //        return null;
    }

    //获取json对象的方法
    public <T> T  getBeanData(String url, Class<T> clazz) {

        //1. 去网络获取数据
        String content = HttpManager.getInstance().dataGet(url);
        System.out.println("网络获取的数据:" + content);
        //2. 判断当前的数据是否为空
        if (TextUtils.isEmpty(content)) {
            //如果是空
            //去缓存类中去取数据
            content = CacheManger.getInstance().getCacheData(url);

//            System.out.println("当前缓存数据:"+content.length());
        } else {
            //不为空
            //保存数据,刷新缓存数据
            CacheManger.getInstance().saveData(content, url);
        }

        //到这一步为止,我们已经得到数据了
        //解析一把
        return parseJson(content, clazz);


        //走到这一步就可以去解析了
//        return null;
    }

    //解析json数据
    private <T> T parseJson(String content, Class<T> clazz) {

        return GsonUtil.parseJsonToBean(content, clazz);
//        return null;
    }

    //获取json对象的方法
    public <T> List<T> getListData(String url, Type type) {

        //1. 去网络获取数据
        String content = HttpManager.getInstance().dataGet(url);
        //2. 判断当前的数据是否为空
        if (TextUtils.isEmpty(content)) {
            //如果是空
            //去缓存类中去取数据
            content = CacheManger.getInstance().getCacheData(url);

//            System.out.println("当前缓存数据:"+content.length());
        } else {
            //不为空
            //保存数据,刷新缓存数据
            CacheManger.getInstance().saveData(content, url);
        }

        //到这一步为止,我们已经得到数据了
        //解析一把
        return parseJsonList(content, type);
        //走到这一步就可以去解析了
        //        return null;
    }

    private <T> List<T> parseJsonList(String content, Type type) {
        return GsonUtil.parseJsonToList(content, type);
    }


}
