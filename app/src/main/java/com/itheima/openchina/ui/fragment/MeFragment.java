package com.itheima.openchina.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.ui.activity.LoginActivity;

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
 */

public class MeFragment extends BaseFragment {

    CircleImageView mUnloginHead;
    private View childView;

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
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStartLoadData() {
        loadSuccess();
    }
}
