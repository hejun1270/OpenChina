package com.itheima.openchina.bases;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.AppFragmentPagerAdapter;
import com.itheima.openchina.beans.FragmentInfo;

import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 8:19---
 * Function:
 */

public abstract class BaseNewsFragment extends BaseListPagerFragment {


    private TabLayout tab;
    private ViewPager viewPager;


    @Override
    protected View onCreateContentView() {
        View view = View.inflate(getContext(), R.layout.view_syn_and_tween, null);
        tab = view.findViewById(R.id.tabPagerLayout);
        viewPager = view.findViewById(R.id.synViewPager);
        init();
        return view;
    }


    private void init() {

        List<FragmentInfo> list=listViewPagerFragmentData();
        viewPager.setAdapter(new AppFragmentPagerAdapter(getChildFragmentManager(),list));
        tab.setupWithViewPager(viewPager);


        tab.setSelectedTabIndicatorColor(Color.parseColor("#32D67C"));
        tab.setTabTextColors(Color.parseColor("#9c9c9c"),Color.parseColor("#32D67C"));
    }

    protected abstract List<FragmentInfo> listViewPagerFragmentData();

}
