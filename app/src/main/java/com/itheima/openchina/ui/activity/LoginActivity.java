package com.itheima.openchina.ui.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseActivity;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/4 <p/>
 * Time: 17:32 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.ui.activity <p/>
 * Desc:
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.pager_title)
    TextView mPagerTitle;

    @Override
    protected int getLayoutRs() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        super.init();
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
        mPagerTitle.setText(getString(R.string.login));
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
