package com.itheima.openchina.adapters.SynthesizeAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import com.itheima.loopviewpager.LoopViewPager;
import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseRecyclerAdapter;
import com.itheima.openchina.beans.ConsultHeadBean;
import com.itheima.openchina.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 10:49---
 * Function:
 */

public class SynConsultAdapter<T> extends BaseRecyclerAdapter {

    List<ConsultHeadBean.ResultBean.ItemsBean> beanHead=new ArrayList<>();
    public SynConsultAdapter(Context context, List<T> list, List<ConsultHeadBean.ResultBean.ItemsBean> headList) {
        super(context,list);
        beanHead=headList;
    }


    @Override
    protected View createItemBodyLayout() {
        View view = View.inflate(getContext(), R.layout.view_item_consult_syn, null);
        return view;
    }

    @Override
    protected void createViewBodyItem(RecyclerView.ViewHolder holder, int position) {

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected View createItemHeadLayout() {


        List<String> urlImage = new ArrayList<>();
        List<String> urlText = new ArrayList<>();

        for (int i = 0; i < beanHead.size(); i++) {
            urlImage.add(beanHead.get(i).getImg());
            urlText.add(beanHead.get(i).getName());
        }
        View view = View.inflate(getContext(), R.layout.view_loopviewpager, null);
        LoopViewPager loopView = view.findViewById(R.id.lvp_pager);
        loopView.setImgData(urlImage);
        loopView.setTitleData(urlText);

        loopView.start();
        return view;
    }


}
