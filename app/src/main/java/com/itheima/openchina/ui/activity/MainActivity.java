package com.itheima.openchina.ui.activity;

import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseActivity;
import com.itheima.openchina.ui.fragment.FindFragment;
import com.itheima.openchina.ui.fragment.MeFragment;
import com.itheima.openchina.ui.fragment.SynthesizeFragment;
import com.itheima.openchina.ui.fragment.TweetFragment;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/2 <p/>
 * Time: 23:18 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.ui.activity <p/>
 * Desc:
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.home_title)
    TextView homeTitle;
    @BindView(R.id.home_search)
    ImageView homeSearch;
    @BindView(R.id.home_content)
    FrameLayout homeContent;
    @BindView(R.id.ftab_main_bottom_layout)
    FragmentTabHost tabhost;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    private int mTabItemBg[] = {R.drawable.tab_synthesize, R.drawable.tab_tweet, R.drawable
            .tab_add, R.drawable.tab_find, R.drawable.tab_me};
    private String tabDesc[] = {"综合", "动弹", "", "发现", "我的"};
    private Class mFragment[] = {SynthesizeFragment.class, TweetFragment.class, TweetFragment
            .class, FindFragment.class, MeFragment.class};


    @Override
    protected int getLayoutRs() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent));
        homeTitle.setText(tabDesc[0]);
        initBottomTab();
    }

    //--------------使用onKeyUp()干掉他--------------
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT)
                            .show();
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    public void initBottomTab() {

        for (int i = 0; i < mTabItemBg.length; i++) {
         tabhost.setup(getApplicationContext(), getSupportFragmentManager(), R.id.home_content);
            // 给每个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = tabhost.newTabSpec("tab" + i)
                    .setIndicator(getTabItemView(i));
            //
            // 将Tab按钮添加进Tab选项卡中
            tabhost.addTab(tabSpec, mFragment[i], null);
        }
        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (tabId) {
                    case "tab0":
                        homeTitle.setText(tabDesc[0]);
                        break;
                    case "tab1":
                        homeTitle.setText(tabDesc[1]);
                        break;
                    case "tab3":
                        homeTitle.setText(tabDesc[3]);
                        break;
                    case "tab4":
                        homeTitle.setText(tabDesc[4]);
                        break;
                }
            }
        });
    }

    /**
     * 给每个Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = View.inflate(this, R.layout.tab_item, null);
        TextView textView = view.findViewById(R.id.tv_desc);
        ImageView img = view.findViewById(R.id.iv_icon);
        textView.setText(tabDesc[index]);
        img.setImageResource(mTabItemBg[index]);
        if (index == 2) {
            view.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    @OnClick({R.id.home_search, R.id.iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_search:
                break;
            case R.id.iv_add:
                startMActivity(AddActivity.class);
                break;
        }
    }
}
