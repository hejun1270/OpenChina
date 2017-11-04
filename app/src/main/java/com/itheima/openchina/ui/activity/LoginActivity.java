package com.itheima.openchina.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.openchina.R;
import com.itheima.openchina.appcontrol.Constant;
import com.itheima.openchina.appcontrol.NetDataApi;
import com.itheima.openchina.bases.BaseActivity;
import com.itheima.openchina.network_request.PostManager;
import com.itheima.openchina.utils.GsonUtil;
import com.itheima.openchina.utils.SpUtil;
import com.jaeger.library.StatusBarUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
        String mapToJson = GsonUtil.parseMapToJson(map);
        Log.i(TAG, "startLogin: ---<>" + mapToJson);
        PostManager.getInstance().jsonPost(mapToJson, NetDataApi.HOST_URL + NetDataApi.LOGIN_URL,
                new
                        Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws
                                    IOException {
                                Log.i(TAG, "onResponse: --->" + response.body().toString());
                            }
                        });
    }
}
