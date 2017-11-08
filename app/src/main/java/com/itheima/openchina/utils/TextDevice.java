package com.itheima.openchina.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.itheima.openchina.appcontrol.App;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/8 <p/>
 * Time: 15:55 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.utils <p/>
 * Desc: 字符操作工具类
 */

public class TextDevice {

    public static float dpToPixel(float dp) {
        return dp * (getDisplayMetrics().densityDpi / 160F);
    }

    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) App.context.getSystemService(
                Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(
                displaymetrics);
        return displaymetrics;
    }
}
