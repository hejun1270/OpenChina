package com.itheima.openchina.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * User:Administrator <p/>
 * Date: 2017/10/10 15:45 <p/>
 * Project:NewsClient <p/>
 * Package:com.adminhj.newsclient.Utils <p/>
 * Desc:  SharePreferences 工具类  <p/>
 */

public class SpUtil {
    private static String FILE_NAME = "";
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    private static final String TAG = "SpUtil";

    private SpUtil() {
    }

    public static void init(Context context) {
        String packageName = context.getPackageName();
        FILE_NAME = packageName + ".config";
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        Log.i(TAG, "init: >" + FILE_NAME);
    }

    public static void init(Context context, String fileName) {
        if (fileName != null && !fileName.equals("")) {
            FILE_NAME = fileName;
        } else {
            String packageName = context.getPackageName();
            FILE_NAME = packageName + ".config";
        }
        Log.i(TAG, "init: >" + FILE_NAME);
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBoolean(String key, boolean def) {
        return preferences.getBoolean(key, def);
    }

    public static void saveString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(String key, String def) {
        return preferences.getString(key, def);
    }

    public static void saveInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInt(String key, int def) {
        return preferences.getInt(key, def);
    }
}
