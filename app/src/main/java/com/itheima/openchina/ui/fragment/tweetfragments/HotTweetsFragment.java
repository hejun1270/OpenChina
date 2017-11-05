package com.itheima.openchina.ui.fragment.tweetfragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.tweetAdapter.TweetAdapter;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.TweetInfoBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.utils.ToastUtil;
import com.itheima.openchina.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  张慧强
 * Version:  1.0
 * Date:    2017/11/4 0004
 * Modify:
 * Description: //TODO
 * Copyright notice:
 */
public class HotTweetsFragment extends BaseFragment implements TweetAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private List<TweetInfoBean.ResultBean.TweetItem> tweetItems=new ArrayList<>();
    private TweetAdapter<TweetInfoBean.ResultBean.TweetItem> tweetAdapter;

    @Override
    protected void dataOnRefresh() {

    }
     //创建热门动弹页面布局
    @Override
    protected View onCreateContentView() {
        View view = View.inflate(getContext(), R.layout.recycleview_view,null);
        recyclerView = (RecyclerView) view;
        init();
        return view;
    }
     //初始化方法
    private void init() {
        //RecyclerView布局模式
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        //设置RecyclerView的适配器
        tweetAdapter = new TweetAdapter<>(getContext(), tweetItems);
        recyclerView.setAdapter(tweetAdapter);

        //动弹条目的点击事件
        tweetAdapter.setOnItemClickListener(this);
    }
    //加载数据
    @Override
    protected void onStartLoadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                TweetInfoBean beanData = LoadData.getInstance().getBeanData("http://www.oschina.net/action/apiv2/tweets?type=2", TweetInfoBean.class);
                tweetItems.addAll(beanData.getResult().getItems());
                Utils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {

                        setRefreshEnable(false);
                        loadSuccess();
                        tweetAdapter.notifyDataSetChanged();

                    }
                });
            }
        }).start();
    }



    @Override
    public void onItemClick(int position) {
        ToastUtil.showToast("当前点击的条目"+position);
    }
}
