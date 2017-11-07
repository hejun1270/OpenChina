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
     * 咨询头条目
     */
    public static final String CONSULT_HEAD=HOST_URL+"/action/apiv2/banner?catalog=1";

    /**
     * 资讯内容
     */
    public static final String CONSULT_BODY=HOST_URL+"/action/apiv2/news?pageToken=";

    /**
     * 博客1:最新推荐
     */
    public static final String BLOG_RECOMMONED=HOST_URL+"/action/apiv2/blog?catalog=3&nextPageToken=";

    /**
     * 博客2:本周热门
     */
    public static final String BOLG_WEEK_HOT=HOST_URL+"/action/apiv2/blog?catalog=2&nextPageToken=";

    /**
     * 博客3:最新热门
     */
    public static final String BLOG_NEWEST = HOST_URL + "/action/apiv2/blog?catalog=1&nextPageToken=";

    /**
     * 当前页
     */
    public static final String PAGER_CURRENT ="DBA816934CD0AA59";


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
