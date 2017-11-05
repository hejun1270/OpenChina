package com.itheima.openchina.ui.fragment;

import com.itheima.openchina.bases.BaseNewsFragment;
import com.itheima.openchina.beans.FragmentInfo;
import com.itheima.openchina.ui.fragment.tweetfragments.HotTweetsFragment;
import com.itheima.openchina.ui.fragment.tweetfragments.MyTweetsFragment;
import com.itheima.openchina.ui.fragment.tweetfragments.NewTweetsFragment;

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
        list.add(new FragmentInfo("最新动弹",new NewTweetsFragment()));
        list.add(new FragmentInfo("热门动弹",new HotTweetsFragment()));
        list.add(new FragmentInfo("我的动弹",new MyTweetsFragment()));

        return list;
    }




}
