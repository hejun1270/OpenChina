package com.itheima.openchina.utils;

import android.util.Log;

/**
 * 作者: DoctorHe
 * 描述:
 * 北京传智播客android黑马程序员104期
 **/


public class LogUtils {

    //日志开关
    public static final boolean isShowLog = true;

    public static final String tag = "oschina";

    //日志打印i级别
    public static void i(String message) {
        if (isShowLog) {
            Log.i(tag, message);
        }
    }

}
