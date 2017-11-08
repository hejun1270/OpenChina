package com.itheima.openchina.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

import com.itheima.openchina.appcontrol.App;


/**
 * user:doctorhe
 * 北京传智播客android黑马程序员104期
 */

public class Utils {
    /**
     * 这个是在主线程去更新ui,在没有上下文的环境,
     *
     * @param runnable
     */
    public static void runOnUIThread(Runnable runnable) {
        App.getMainHandler().post(runnable);
    }

    /**
     * 得到资源管理的类
     *
     * @return
     */
    public static Resources getResources() {
        return App.context.getResources();
    }

    /**
     * 在屏幕适配时候使用,让代码中使用dip属性
     *
     * @param resId
     * @return
     */
    public static int getDimens(int resId) {

        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 得到颜色
     *
     * @param resId
     * @return
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 得到字符串数组信息
     *
     * @param resId
     * @return
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    public static void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) App.context.
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
