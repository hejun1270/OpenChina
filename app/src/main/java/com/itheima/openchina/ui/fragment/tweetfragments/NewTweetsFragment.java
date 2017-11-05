package com.itheima.openchina.ui.fragment.tweetfragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.TweetAdapter;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.TweetInfoBean;
import com.itheima.openchina.cacheadmin.LoadData;

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
public class NewTweetsFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<TweetInfoBean.ResultBean.TweetItem> tweetItems=new ArrayList<>();
    private TweetAdapter recyclerViewAdapter;



    @Override
    protected void dataOnRefresh() {

    }

    @Override
    protected View onCreateContentView() {
        View view = View.inflate(getContext(), R.layout.view_fragment_item_list, null);
         recyclerView = view.findViewById(R.id.rv_item_recycler_view);
        init();
        return view;
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerViewAdapter = new TweetAdapter<TweetInfoBean.ResultBean.TweetItem>(getContext(),tweetItems);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onStartLoadData() {
        loadSuccess();

        /*Thread thread = Thread.currentThread();
        Log.d("------------",thread+"");*/
           new Thread(new Runnable() {
               @Override
               public void run() {
                   TweetInfoBean beanData = LoadData.getInstance().getBeanData("http://www.oschina.net/action/apiv2/tweets?type=1", TweetInfoBean.class);
                   tweetItems.addAll(beanData.getResult().getItems());
               }
           }).start();
/*
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });*/



    }
}
