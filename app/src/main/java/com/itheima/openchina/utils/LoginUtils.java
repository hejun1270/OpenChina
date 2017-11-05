package com.itheima.openchina.utils;

import android.util.Xml;

import com.itheima.openchina.appcontrol.NetDataApi;
import com.itheima.openchina.beans.LoginInfo;
import com.itheima.openchina.network_request.PostManager;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/5 <p/>
 * Time: 10:52 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.utils <p/>
 * Desc: 登录工具类
 */

public class LoginUtils {
    public static void login(Map<String, String> params, final LoginListener loginListener) {
        PostManager.getInstance().formPost(params, NetDataApi.LOGIN_URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                loginListener.failed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, final Response response) throws
                    IOException {
                LoginInfo loginInfo = xmlPull(response.body().byteStream());
                String cookie = response.header("set-Cookie");
                loginInfo.setCookie(cookie);
                LogUtils.i(loginInfo.toString());
                loginListener.success(loginInfo);
            }
        });
    }

    private static LoginInfo xmlPull(InputStream inputStream) {
        LoginInfo loginInfo = new LoginInfo();
        XmlPullParser xmlPullParser = Xml.newPullParser();
        try {
            xmlPullParser.setInput(inputStream, "utf-8");
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("errorCode".equals(xmlPullParser.getName())) {
                            loginInfo.setErrorCode(xmlPullParser.nextText());
                        } else if ("errorMessage".equals(xmlPullParser.getName())) {
                            loginInfo.setErrorMessage(xmlPullParser.nextText());
                        } else if ("uid".equals(xmlPullParser.getName())) {
                            loginInfo.setUid(xmlPullParser.nextText());
                        } else if ("location".equals(xmlPullParser.getName())) {
                            loginInfo.setLocation(xmlPullParser.nextText());
                        } else if ("name".equals(xmlPullParser.getName())) {
                            loginInfo.setName(xmlPullParser.nextText());
                        } else if ("followers".equals(xmlPullParser.getName())) {
                            loginInfo.setFollowers(xmlPullParser.nextText());
                        } else if ("fans".equals(xmlPullParser.getName())) {
                            loginInfo.setFans(xmlPullParser.nextText());
                        } else if ("score".equals(xmlPullParser.getName())) {
                            loginInfo.setScore(xmlPullParser.nextText());
                        } else if ("favoritecount".equals(xmlPullParser.getName())) {
                            loginInfo.setFavoritecount(xmlPullParser.nextText());
                        } else if ("gender".equals(xmlPullParser.getName())) {
                            loginInfo.setGender(xmlPullParser.nextText());
                        } else if ("atmeCount".equals(xmlPullParser.getName())) {
                            loginInfo.setAtmeCount(xmlPullParser.nextText());
                        } else if ("msgCount".equals(xmlPullParser.getName())) {
                            loginInfo.setMsgCount(xmlPullParser.nextText());
                        } else if ("reviewCount".equals(xmlPullParser.getName())) {
                            loginInfo.setReviewCount(xmlPullParser.nextText());
                        } else if ("newFansCount".equals(xmlPullParser.getName())) {
                            loginInfo.setNewFansCount(xmlPullParser.nextText());
                        } else if ("newLikeCount".equals(xmlPullParser.getName())) {
                            loginInfo.setNewLikeCount(xmlPullParser.nextText());
                        }
                        break;
                }
                eventType = xmlPullParser.next();
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginInfo;
    }

    public interface LoginListener {
        void success(LoginInfo loginInfo);

        void failed(String errorInfo);
    }
}
