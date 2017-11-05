package com.itheima.openchina.ui.fragment.synfragments;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.SynthesizeAdapter.SynBlogsAdapter;
import com.itheima.openchina.adapters.SynthesizeAdapter.SynQuestionAdapter;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.bases.FootBean;
import com.itheima.openchina.beans.HeadBean;
import com.itheima.openchina.interfaces.ItemType;

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
        return view;
    }


    @Override
    protected void dataOnRefresh() {
        onStartLoadData();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStartLoadData() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                list.add(new HeadBean());
                list.add(new FootBean());
                loadSuccess();
                adapter.updateData();
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void loadSuccess() {
        super.loadSuccess();
    }



}
