package com.itheima.openchina.bases;

import android.view.View;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/3 <p/>
 * Time: 16:13 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.bases <p/>
 * Desc:
 */

public abstract class BasePagerFragment extends BaseFragment {
    @Override
    protected void dataOnRefresh() {

    }

    @Override
    protected void onStartLoadData() {
        loadSuccess();
    }

    @Override
    protected abstract View onCreateContentView();
}
