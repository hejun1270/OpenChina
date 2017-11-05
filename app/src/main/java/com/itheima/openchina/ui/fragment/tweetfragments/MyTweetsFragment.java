package com.itheima.openchina.ui.fragment.tweetfragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.tweetAdapter.TweetAdapter;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.TweetInfoBean;
import com.itheima.openchina.cacheadmin.LoadData;
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
public class MyTweetsFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<TweetInfoBean.ResultBean.TweetItem> tweetItems=new ArrayList<>();

    @Override
    protected void dataOnRefresh() {

    }

    @Override
    protected View onCreateContentView() {
        View view = View.inflate(getContext(), R.layout.recycleview_view,null);
        recyclerView = (RecyclerView) view;
        init();
        return view;

    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        TweetAdapter<TweetInfoBean.ResultBean.TweetItem> tweetAdapter = new TweetAdapter<>(getContext(), tweetItems);
        recyclerView.setAdapter(tweetAdapter);
    }

    @Override
    protected void onStartLoadData() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TweetInfoBean beanData = LoadData.getInstance().getBeanData("http://www.oschina.net/action/apiv2/tweets?type=2", TweetInfoBean.class);
                    tweetItems.addAll(beanData.getResult().getItems());
                    doLoadData();
                }
            }).start();
    }

    private void doLoadData() {
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                setRefreshEnable(false);
                loadSuccess();
            }
        });
    }
}
