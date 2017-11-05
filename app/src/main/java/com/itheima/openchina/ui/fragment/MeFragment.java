package com.itheima.openchina.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.LoginInfo;
import com.itheima.openchina.ui.activity.LoginActivity;
import com.itheima.openchina.utils.LogUtils;
import com.itheima.openchina.utils.SpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
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

public class MeFragment extends BaseFragment {

    private CircleImageView mUnloginHead;
    private View childView;

    private LoginInfo mLoginInfo;

    @Override
    protected void dataOnRefresh() {
    }

    @Override
    protected View onCreateContentView() {
        childView = View.inflate(getContext(), R.layout.fragment_me, null);
        initView();
        return childView;
    }

    private void initView() {
        mUnloginHead = childView.findViewById(R.id.unlogin_head);


        mUnloginHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 1000);
            }
        });
    }

    @Override
    protected void onStartLoadData() {
        //"?uid=3197930" 获取个人信息
        loadSuccess();
    }

    /**
     * 当fragment节面可触摸时设置信息
     */
    @Override
    public void onResume() {
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
            mLoginInfo = (LoginInfo) data.getSerializableExtra("loginfo");
        }
    }
}
