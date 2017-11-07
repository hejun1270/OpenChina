package com.itheima.openchina.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import com.itheima.openchina.ui.fragment.CategoryFragment;
import com.itheima.openchina.ui.fragment.CountryFragment;
import com.itheima.openchina.ui.fragment.HotFragment;
import com.itheima.openchina.ui.fragment.NewsMessage;
import com.itheima.openchina.ui.fragment.RecommondFragment;

/**
 * Created by jiang on 2017/10/17.
 */

public class FindAdapter extends FragmentPagerAdapter {
    private final String[] mtitles;
    private static final String TAG = "MainAdapter";
    public FindAdapter(String titles[], FragmentManager fm) {
        super(fm);
        mtitles = titles;
    }

    @Override
    //
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: "+position);
        switch (position) {
            case 0:
                return new CategoryFragment();
            case 1:
                return new RecommondFragment();
            case 2:
                return new NewsMessage();
            case 3:
                return new HotFragment();
            case 4:
                return new CountryFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return mtitles.length;
    }

    @Override
    //给指示器设置内容
    public CharSequence getPageTitle(int position) {
        return mtitles[position];
    }
}
