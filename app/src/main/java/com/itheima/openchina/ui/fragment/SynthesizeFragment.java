package com.itheima.openchina.ui.fragment;

import com.itheima.openchina.bases.BaseNewsFragment;
import com.itheima.openchina.beans.FragmentInfo;
import com.itheima.openchina.ui.fragment.synfragments.ActionFragment;
import com.itheima.openchina.ui.fragment.synfragments.BlogsFragment;
import com.itheima.openchina.ui.fragment.synfragments.ConsultFragment;
import com.itheima.openchina.ui.fragment.synfragments.QuestionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/3 <p/>
 * Time: 10:38 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.ui.fragment <p/>
 * Desc:综合
 */

public class SynthesizeFragment extends BaseNewsFragment{


    @Override
    protected List<FragmentInfo> listViewPagerFragmentData() {
        List<FragmentInfo> list=new ArrayList<>();
        list.add(new FragmentInfo("咨询",new ConsultFragment()));
        list.add(new FragmentInfo("博客",new BlogsFragment()));
        list.add(new FragmentInfo("问答",new QuestionFragment()));
        list.add(new FragmentInfo("活动",new ActionFragment()));
        return list;
    }


}
