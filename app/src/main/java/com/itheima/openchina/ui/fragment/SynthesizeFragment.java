package com.itheima.openchina.ui.fragment;

import com.itheima.openchina.bases.BaseNewsFragment;
import com.itheima.openchina.beans.FragmentInfo;
import com.itheima.openchina.ui.fragment.synfragments.ConsultFragment;

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
        list.add(new FragmentInfo("开始",new ConsultFragment()));
        list.add(new FragmentInfo("中间",new ConsultFragment()));
        list.add(new FragmentInfo("结束",new ConsultFragment()));
        list.add(new FragmentInfo("完成",new ConsultFragment()));
        return list;
    }


}
