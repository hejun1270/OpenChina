package com.itheima.openchina.bases;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.itheima.openchina.R;
import com.itheima.openchina.receivers.NetWorkStateReciver;
import com.itheima.openchina.utils.NetWorkUtil;
import com.trycatch.mysnackbar.Prompt;
import com.trycatch.mysnackbar.TSnackbar;

import butterknife.ButterKnife;

/**
 * User:user <p/>
 * Date: 2017/10/17 17:10 <p/>
 * Project:GooglePlay <p/>
 * Package:com.adminhj.googleplay.base <p/>
 * Desc:    <p/>
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Intent mIntent;

    protected TSnackbar snackbar;
    private ViewGroup rootView;
    private NetWorkStateReciver netWorkStateReciver;


    private NetWorkStateListener listener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRs());
        setWindowAnimation(R.style.activity_anim);
        //overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
        //overridePendingTransition(R.anim.activity_enter, android.R.anim.slide_out_right);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build
                .VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager
                    .LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ButterKnife.bind(this);
        initSnackBar();
        setNetReciverListener();
        init();
    }

    protected void setWindowAnimation(int anmStyle) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.windowAnimations = anmStyle;
        getWindow().setAttributes(params);
    }

    /**
     * 顶部弹出SnackBar
     */
    private void initSnackBar() {
        //注意getRootView()最为重要，直接关系到TSnackBar的位置
        rootView = (ViewGroup) findViewById(android.R.id.content)
                .getRootView();
    }

    private void setNetReciverListener() {
        netWorkStateReciver = new NetWorkStateReciver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        registerReceiver(netWorkStateReciver, filter);
        netWorkStateReciver.setNetWorkLinstener(new NetWorkStateReciver.NetWorkLinstener() {
            @Override
            public void onConnect(String msg, boolean isConnect) {
                if (listener != null) {
                    listener.netStateChange(msg, isConnect);
                }
                netWorkState(msg, isConnect);
            }
        });
    }

    /**
     * 继承至BaseActivity的类调用
     *
     * @param msg
     * @param isConnect
     */
    protected void netWorkState(String msg, boolean isConnect) {

    }

    public void setListener(NetWorkStateListener listener) {
        this.listener = listener;
    }

    public void setNetErrorMsg(String msg, boolean state) {
        snackbar = TSnackbar.make(rootView, msg, TSnackbar.LENGTH_LONG, TSnackbar
                .APPEAR_FROM_TOP_TO_DOWN);
        if (state) {
            snackbar.setPromptThemBackground(Prompt.SUCCESS);
        } else {
            snackbar.setPromptThemBackground(Prompt.ERROR);
            snackbar.setAction("设置", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NetWorkUtil.setNetworkMethod(BaseActivity.this);
                }
            });
        }
        snackbar.show();
    }

    @Override
    protected void onDestroy() {
        if (netWorkStateReciver != null) {
            unregisterReceiver(netWorkStateReciver);
        }
        super.onDestroy();
    }

    /**
     * 初始化
     */
    protected void init() {
        mIntent = new Intent();
    }

    protected abstract int getLayoutRs();

    protected void startMActivity(Class tagClass) {
        mIntent.setClass(this, tagClass);
        startActivity(mIntent);
    }

    public interface NetWorkStateListener {
        /**
         * 网络链接状态
         *
         * @param msg
         * @param isConnect
         */
        void netStateChange(String msg, boolean isConnect);
    }
}
