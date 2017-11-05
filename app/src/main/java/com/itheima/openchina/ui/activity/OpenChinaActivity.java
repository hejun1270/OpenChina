package com.itheima.openchina.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.FindAdapter;
import com.itheima.openchina.bases.BaseActivity;
import com.itheima.openchina.ui.fragment.CategoryFragment;

public class OpenChinaActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar tb;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] titles;

    @Override
    protected void init() {
        super.init();
        titles = new String[]{"分类","推荐","最新","热门","国产"};
        tb = findViewById(R.id.tool_bar);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        initActionBar();
        initViewpager();
    }

    @Override
    protected int getLayoutRs() {
        return R.layout.activity_open_china;
    }



    private void initViewpager() {
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new FindAdapter(titles,getSupportFragmentManager()));
    }

    private void initActionBar() {
        setSupportActionBar(tb);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle("开源软件");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

//    private PagerAdapter pagerAdapter=new PagerAdapter() {
//        @Override
//        public int getCount() {
//            return titles.length;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view==object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//
//            TextView textView = new TextView(OpenChinaActivity.this);
//            textView.setText(titles[position]);
//            container.addView(textView);
//            return textView;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return titles[position];
//        }
//    };
}
