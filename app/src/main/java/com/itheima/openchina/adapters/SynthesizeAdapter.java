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

public class SynthesizeAdapter extends BaseRecyclerAdapter {

    public SynthesizeAdapter(Context context, List<Object> list) {
        super(context,list);
    }


    @Override
    protected View createItemBodyLayout() {

        return null;
    }

    @Override
    protected void createViewBodyItem(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    protected View createItemHeadLayout() {


        return null;
    }
}
