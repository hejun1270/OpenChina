package com.itheima.openchina.adapters.SynthesizeAdapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseRecyclerAdapter;
import com.itheima.openchina.beans.ConsultHeadBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 10:49---
 * Function:
 */

public class SynQuestionAdapter extends BaseRecyclerAdapter {

    List<ConsultHeadBean.ResultBean.ItemsBean> beanHead=new ArrayList<>();
    public SynQuestionAdapter(Context context, List list) {
        super(context,list);
    }


    @Override
    protected View createItemBodyLayout() {
        View view = View.inflate(getContext(), R.layout.view_item_consult_syn, null);
        return view;
    }

    @Override
    protected void createViewBodyItem(RecyclerView.ViewHolder holder, int position) {

    }


    @Override
    protected View createItemHeadLayout() {
        View view = View.inflate(getContext(), R.layout.view_head_question_syn, null);

        return view;
    }


}
