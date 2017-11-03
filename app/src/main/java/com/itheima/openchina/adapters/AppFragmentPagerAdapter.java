package com.itheima.openchina.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.itheima.openchina.beans.FragmentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/3 <p/>
 * Time: 17:19 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.adapters <p/>
 * Desc:fragment在viewpager中显示的适配器
 */

public class AppFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private List<FragmentInfo> mShowItems = new ArrayList<>();

    public AppFragmentPagerAdapter(FragmentManager fm, List<FragmentInfo> showItems) {
        super(fm);
        mShowItems = showItems;
    }

    @Override
    public Fragment getItem(int position) {
        return mShowItems.get(position).fragment;
    }

    @Override
    public int getCount() {
        return mShowItems.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return mShowItems.get(position).title;
    }
}
