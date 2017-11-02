package com.itheima.openchina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.itheima.openchina.appcontrol.App;
import com.itheima.openchina.bases.BaseActivity;
import com.itheima.openchina.ui.activity.MainActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutRs() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        super.init();
        App.getMainHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startMActivity(MainActivity.class);
                finish();
            }
        }, 2000);
    }
}
