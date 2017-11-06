package com.itheima.openchina.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.FindAdapter;
import com.itheima.openchina.bases.BaseActivity;

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
}
