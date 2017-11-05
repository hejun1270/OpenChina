package com.itheima.openchina.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseRecyclerAdapter;

import java.util.List;

import butterknife.ButterKnife;


/**
 * Author:  张慧强
 * Version:  1.0
 * Date:    2017/11/4 0004
 * Modify:
 * Description: //TODO
 * Copyright notice:
 */
public class TweetAdapter<T> extends BaseRecyclerAdapter {


    public TweetAdapter(Context context, List<T> list) {
        super(context, list);
    }




    @Override
    protected View createItemBodyLayout() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet_new_view,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    protected void createViewBodyItem(RecyclerView.ViewHolder holder, int position) {

    }
}
