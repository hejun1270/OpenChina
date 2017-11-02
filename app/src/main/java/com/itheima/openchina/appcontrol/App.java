package com.itheima.openchina.appcontrol;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.itheima.openchina.utils.SpUtil;

/**
 * 作者: DoctorHe
 * 描述:
 * 北京传智播客android黑马程序员104期
 **/

public class App extends Application {
    public static Context context;
    public static Handler mainHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        init();
    }

    /**
     * 项目初始化
     */
    private void init() {
        SpUtil.init(this);//初始化shareprefarences工具类
    }

    public static Handler getMainHandler() {
        if (mainHandler == null) {
            mainHandler = new Handler();
        }
        return mainHandler;
    }
}
