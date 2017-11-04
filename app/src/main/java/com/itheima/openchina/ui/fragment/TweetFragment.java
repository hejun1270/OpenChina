package com.itheima.openchina.ui.fragment;

import com.itheima.openchina.bases.BaseNewsFragment;
import com.itheima.openchina.beans.FragmentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/3 <p/>
 * Time: 10:38 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.ui.fragment <p/>
 * Desc:
 */

public class TweetFragment extends BaseNewsFragment {



    @Override
    protected List<FragmentInfo> listViewPagerFragmentData() {
        List<FragmentInfo> list=new ArrayList<>();
        list.add(new FragmentInfo("开始",new BaseRecyclerFragment()));
        list.add(new FragmentInfo("开始",new BaseRecyclerFragment()));
        list.add(new FragmentInfo("开始",new BaseRecyclerFragment()));
        return list;
    }
}
