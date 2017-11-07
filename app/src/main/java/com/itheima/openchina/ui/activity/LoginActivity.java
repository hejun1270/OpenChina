package com.itheima.openchina.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.openchina.R;
import com.itheima.openchina.appcontrol.Constant;
import com.itheima.openchina.bases.BaseActivity;
import com.itheima.openchina.beans.LoginInfo;
import com.itheima.openchina.utils.LogUtils;
import com.itheima.openchina.utils.LoginUtils;
import com.itheima.openchina.utils.SpUtil;
import com.itheima.openchina.utils.ToastUtil;
import com.itheima.openchina.utils.Utils;
import com.itheima.openchina.wedigt.LoadDialog;
import com.jaeger.library.StatusBarUtil;

import java.util.HashMap;
import java.util.Map;

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
    private static final String TAG = "LoginActivity";
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.pager_title)
    TextView mPagerTitle;
    @BindView(R.id.et_count)
    EditText mEtCount;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    private LoadDialog mLoadDialog;

    @Override
    protected int getLayoutRs() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        super.init();
        String username = SpUtil.getString(Constant.USER_NAME, "");
        String pwd = SpUtil.getString(Constant.USER_PWD, "");
        mEtCount.setText(username);
        mEtCount.setSelection(username.length());
        mEtPwd.setText(pwd);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
        mPagerTitle.setText(getString(R.string.login));
    }

    @OnClick({R.id.iv_back, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_login:
                showLoading();
                startLogin();
                break;
        }
    }

    private void startLogin() {
        String count = mEtCount.getText().toString().trim();
        String pwd = mEtPwd.getText().toString().trim();
        SpUtil.saveString(Constant.USER_NAME, count);
        SpUtil.saveString(Constant.USER_PWD, pwd);
        Map<String, String> map = new HashMap<>();
        map.put("keep_login", "1");
        map.put("username", count);
        map.put("pwd", pwd);
        LoginUtils.login(map, new LoginUtils.LoginListener() {
            @Override
            public void success(final LoginInfo loginInfo) {
                if (loginInfo.getErrorCode().equals("1")) {//登录成功
                    SpUtil.saveString(Constant.UID, loginInfo.getUid());//保存用户id
                    SpUtil.saveString(Constant.COOKIE, loginInfo.getCookie());//保存用户cookie
                    ToastUtil.showToast(loginInfo.getErrorMessage());
                    mLoadDialog.dismiss();
                    //将登录信息返回给启动此登录界面的activity
                    Intent intent = getIntent();
                    intent.putExtra("login", true);
                    intent.putExtra("loginfo", loginInfo);
                    setResult(1001, intent);//返回登录信息
                    finish();
                } else {//登录失败
                    Utils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showToast(loginInfo.getErrorMessage());
                            mLoadDialog.dismiss();
                        }
                    });
                }
            }

            @Override
            public void failed(String errorInfo) {
                LogUtils.i(errorInfo);
                mLoadDialog.dismiss();
            }
        });
    }


    private void showLoading() {
        mLoadDialog = new LoadDialog(this);
        mLoadDialog.setMsg("正在登录,请稍后...");
        mLoadDialog.setCanceledOnTouchOutside(false);
        mLoadDialog.setCancelable(false);
        mLoadDialog.show();
    }
}
