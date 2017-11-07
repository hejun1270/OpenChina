package com.itheima.openchina.ui.fragment.synfragments;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.SynthesizeAdapter.SynQuestionAdapter;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.FootBean;
import com.itheima.openchina.beans.HeadBean;
import com.itheima.openchina.beans.QuestionBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.interfaces.ItemType;
import com.itheima.openchina.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 14:05---
 * Function:
 */

public class QuestionFragment extends BaseFragment {

    private SynQuestionAdapter adapter;
    private RecyclerView recyclerView;
    private List<ItemType> list=new ArrayList<>();
    private String url;

    @Override
    protected View onCreateContentView() {
        View view=View.inflate(getContext(),R.layout.recycleview_view,null);
        recyclerView = (RecyclerView) view;

        adapter = new SynQuestionAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //添加条目动画
        LayoutAnimationController lac=new LayoutAnimationController(AnimationUtils.loadAnimation(getActivity(),R.anim.list_zoom));
        lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
        recyclerView.setLayoutAnimation(lac);
        recyclerView.startLayoutAnimation();

        //头条目点击事件
        adapter.setQustionListener(new SynQuestionAdapter.QuestionListener() {
            @Override
            public void onListener(int position) {
                notifyChange(position);
            }
        });
        return view;
    }

    private void notifyChange(int position) {
         url = "http://www.oschina.net/action/apiv2/question?catalog=" +
                position + "&nextPageToken=";
        new Thread(new Runnable() {
            @Override
            public void run() {

                QuestionBean questionBean = LoadData.getInstance().getBeanData(url, QuestionBean.class);
                list.removeAll(list);
                LogUtils.i(list.size()+"=============");
                list.add(new HeadBean());
                list.addAll(questionBean.getResult().getItems());
                list.add(new FootBean());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       adapter.setBean(list);
                    }
                });
            }
        }).start();
    }


    @Override
    protected void dataOnRefresh() {
        onStartLoadData();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStartLoadData() {
        url = "http://www.oschina.net/action/apiv2/question?catalog=1&nextPageToken=";
        new Thread(new Runnable() {
            @Override
            public void run() {

                QuestionBean questionBean = LoadData.getInstance().getBeanData(url, QuestionBean.class);
                list.remove(list);
                list.add(new HeadBean());
                list.addAll(questionBean.getResult().getItems());
                list.add(new FootBean());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadSuccess();
                        adapter.notifyItemRangeChanged(1,list.size()-2);
                    }
                });
            }
        }).start();

    }





}
