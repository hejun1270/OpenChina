package com.itheima.openchina.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.bases.BasePagerFragment;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/3 <p/>
 * Time: 10:38 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.ui.fragment <p/>
 * Desc:综合
 */

public class SynthesizeFragment extends BasePagerFragment {

    @Override
    protected View onCreateContentView() {
        setRefreshEnable(false);//设置禁止下拉刷新
        TextView textView = new TextView(getContext());
        textView.setText("综合");
        return textView;
    }
}
