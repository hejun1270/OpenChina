package com.itheima.openchina.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itheima.openchina.bases.BaseRecyclerAdapter;

import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 10:49---
 * Function:
 */

public class SynthesizeAdapter<T> extends BaseRecyclerAdapter {

    public SynthesizeAdapter(Context context, List<T> list) {
        super(context,list);
    }


    @Override
    protected View createItemBodyLayout() {

        return null;
    }

    @Override
    protected void createViewBodyItem(RecyclerView.ViewHolder holder, int position) {

    }
/*
    @Override
    protected View createItemHeadLayout() {
        FunBanner.Builder builder = new FunBanner.Builder(getContext());
        FunBanner build = builder.setEnableAutoLoop(true)
                .setDotSelectedColor(Color.RED)
                .setHeightWidthRatio(0.5f)
                .setIndicatorBarHeight(20)
                .setIndicatorBackgroundColor(Color.parseColor("#7D6B6363"))
                .build();
        return build;
    }*/
}
