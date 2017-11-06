package com.itheima.openchina.appcontrol;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/3 <p/>
 * Time: 17:17 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.appcontrol <p/>
 * Desc: 网络数据接口 地址
 */

public class NetDataApi {
    /**
     * 网络接口主机地址
     */
    public static final String HOST_URL = "http://www.oschina.net";

    /**
     * 登录地址
     */
    public static final String LOGIN_URL = HOST_URL + "/action/api/login_validate";
    /**
     * 我的信息
     */
    public static final String MY_INFO = HOST_URL + "/action/api/my_information";
    /**
     * 上传头像
     */
    public static final String PORTRAIT_UPDATE = HOST_URL + "/action/api/portrait_update";
    /**
     * 动弹界面url
     */
    public static final String HOT_TWEET_URL="http://www.oschina.net/action/apiv2/tweets?type=2";
    public static final String NEW_TWEET_URL="http://www.oschina.net/action/apiv2/tweets?type=1";
    public static final String MY_TWEET_URL="http://www.oschina.net/action/apiv2/tweets?type=3";
}
