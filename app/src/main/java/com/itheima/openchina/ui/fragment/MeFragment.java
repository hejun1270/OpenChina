package com.itheima.openchina.ui.fragment;

import android.content.Intent;
import android.util.Xml;
import android.view.View;
import android.widget.LinearLayout;

import com.itheima.openchina.R;
import com.itheima.openchina.appcontrol.Constant;
import com.itheima.openchina.appcontrol.NetDataApi;
import com.itheima.openchina.bases.BasePagerFragment;
import com.itheima.openchina.beans.LoginInfo;
import com.itheima.openchina.beans.UserInfo;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.ui.activity.LoginActivity;
import com.itheima.openchina.utils.LogUtils;
import com.itheima.openchina.utils.SpUtil;
import com.itheima.openchina.utils.Utils;
import com.itheima.openchina.wedigt.LoginInfoHeadView;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/3 <p/>
 * Time: 10:38 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.ui.fragment <p/>
 * Desc:
 * 我的页面
 */

public class MeFragment extends BasePagerFragment {

    private CircleImageView mUnloginHead;
    private LinearLayout mUnLogin;
    private LoginInfoHeadView mLogin;
    private View childView;
    private String uid;
    private String cookie;
    private LoginInfo mLoginInfo;
    private boolean mSignout;

    @Override
    protected View onCreateContentView() {
        setRefreshEnable(false);
        childView = View.inflate(getContext(), R.layout.fragment_me, null);
        initView();
        getUserData();
        return childView;
    }

    /**
     * 得到存储的用户id和cookie
     */
    private void getUserData() {
        uid = SpUtil.getString(Constant.UID, "");
        cookie = SpUtil.getString(Constant.COOKIE, "");
    }

    /**
     * 初始化布局控件
     */
    private void initView() {
        mUnloginHead = childView.findViewById(R.id.unlogin_head);
        mUnLogin = childView.findViewById(R.id.ll_noLogin);
        mLogin = childView.findViewById(R.id.loginheadview);

        mUnloginHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 1000);
            }
        });
        mLogin.setOnClickHeadListener(new LoginInfoHeadView.OnClickHeadListener() {
            @Override
            public void onClickHead(Intent intent) {
                startActivityForResult(intent, 2000);
            }
        });
    }

    @Override
    protected void dataOnRefresh() {
        super.dataOnRefresh();
        getMinInfo();
    }

    /**
     * 加载数据
     */
    @Override
    protected void onStartLoadData() {
        super.onStartLoadData();
        if (!uid.equals("") && uid != null && !cookie.equals("") && cookie != null) {
            setRefreshing(true);
            mUnLogin.setVisibility(View.GONE);
            setRefreshEnable(true);
            LogUtils.i("以前已登录过");
            getMinInfo();
        } else {
            setRefreshEnable(false);
            mUnLogin.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 当fragment节面可触摸时设置信息
     */
    @Override
    public void onResume() {
        getUserData();
        if (mSignout) {
            setRefreshEnable(false);
            mUnLogin.setVisibility(View.VISIBLE);
        }
        super.onResume();

    }

    /**
     * 接收上一个activity返回的数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1001) {
            mSignout = false;
            mLoginInfo = (LoginInfo) data.getSerializableExtra("loginfo");
            uid = mLoginInfo.getUid();
            cookie = mLoginInfo.getCookie();
            mUnLogin.setVisibility(View.GONE);
            getMinInfo();
        } else if (resultCode == 2001) {
            mSignout = data.getBooleanExtra("signout", false);
        }
    }

    /**
     * 获取个人信息
     */
    private void getMinInfo() {
        LogUtils.i("-----<<>>" + uid + "<---->" + cookie);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String stringData = LoadData.getInstance().getStringData(NetDataApi.MY_INFO +
                        "?uid=" + uid, cookie);
                ByteArrayInputStream stream = new ByteArrayInputStream(stringData.getBytes());
                final UserInfo userInfo = xmlPull(stream);
                Utils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        mLogin.onBindView(userInfo);
                        setRefreshing(false);
                    }
                });
                LogUtils.i("???????>>>" + userInfo.toString());
            }
        }).start();
    }

    /**
     * 解析加载下来的用户信息
     *
     * @param inputStream
     * @return
     */
    private UserInfo xmlPull(InputStream inputStream) {
        UserInfo userinfo = new UserInfo();
        XmlPullParser xmlPullParser = Xml.newPullParser();
        try {
            xmlPullParser.setInput(inputStream, "utf-8");
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("name".equals(xmlPullParser.getName())) {
                            userinfo.setName(xmlPullParser.nextText());
                        } else if ("portrait".equals(xmlPullParser.getName())) {
                            userinfo.setPortrait(xmlPullParser.nextText());
                        } else if ("jointime".equals(xmlPullParser.getName())) {
                            userinfo.setJointime(xmlPullParser.nextText());
                        } else if ("gender".equals(xmlPullParser.getName())) {
                            userinfo.setGender(xmlPullParser.nextText());
                        } else if ("from".equals(xmlPullParser.getName())) {
                            userinfo.setFrom(xmlPullParser.nextText());
                        } else if ("devplatform".equals(xmlPullParser.getName())) {
                            userinfo.setDevplatform(xmlPullParser.nextText());
                        } else if ("expertise".equals(xmlPullParser.getName())) {
                            userinfo.setExpertise(xmlPullParser.nextText());
                        } else if ("followers".equals(xmlPullParser.getName())) {
                            userinfo.setFollowers(xmlPullParser.nextText());
                        } else if ("fans".equals(xmlPullParser.getName())) {
                            userinfo.setFans(xmlPullParser.nextText());
                        } else if ("score".equals(xmlPullParser.getName())) {
                            userinfo.setScore(xmlPullParser.nextText());
                        } else if ("favoritecount".equals(xmlPullParser.getName())) {
                            userinfo.setFavoritecount(xmlPullParser.nextText());
                        } else if ("atmeCount".equals(xmlPullParser.getName())) {
                            userinfo.setAtmeCount(xmlPullParser.nextText());
                        } else if ("msgCount".equals(xmlPullParser.getName())) {
                            userinfo.setMsgCount(xmlPullParser.nextText());
                        } else if ("reviewCount".equals(xmlPullParser.getName())) {
                            userinfo.setReviewCount(xmlPullParser.nextText());
                        } else if ("newFansCount".equals(xmlPullParser.getName())) {
                            userinfo.setNewFansCount(xmlPullParser.nextText());
                        } else if ("newLikeCount".equals(xmlPullParser.getName())) {
                            userinfo.setNewLikeCount(xmlPullParser.nextText());
                        }
                        break;
                }
                eventType = xmlPullParser.next();
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userinfo;
    }
}
