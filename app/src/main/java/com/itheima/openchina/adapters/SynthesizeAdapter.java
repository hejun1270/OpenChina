package com.itheima.openchina.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.itheima.openchina.bases.BaseRecyclerAdapter;

import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 10:49---
 * Function:
 */

public class SynthesizeAdapter extends BaseRecyclerAdapter {

    private Context context;
    private List<View> list;
    public SynthesizeAdapter(Context context, List<View> list) {
        super(context,list);
        this.context=context;
        this.list=list;
    }


    @Override
    protected View createItemBodyLayout() {
        TextView textView = new TextView(context);
        textView.setText("SynthesizeAdapter");
        return textView;
    }

    @Override
    protected void createViewBodyItem(RecyclerView.ViewHolder holder, int position) {
        list.get(position);
    }
}
