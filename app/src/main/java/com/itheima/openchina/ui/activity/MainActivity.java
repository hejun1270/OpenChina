package com.itheima.openchina.ui.activity;

import android.view.KeyEvent;
import android.widget.Toast;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseActivity;
import com.jaeger.library.StatusBarUtil;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/2 <p/>
 * Time: 23:18 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.ui.activity <p/>
 * Desc:
 */

public class MainActivity extends BaseActivity {
    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    protected int getLayoutRs() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent));
    }

    //--------------使用onKeyUp()干掉他--------------

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT)
                            .show();
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
}
