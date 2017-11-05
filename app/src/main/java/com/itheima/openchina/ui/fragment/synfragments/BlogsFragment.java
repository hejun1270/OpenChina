package com.itheima.openchina.ui.fragment.synfragments;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.SynthesizeAdapter.SynBlogsAdapter;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 14:05---
 * Function:
 */

public class BlogsFragment<T> extends BaseFragment {

    private SynBlogsAdapter<String> adapter;
    private RecyclerView recyclerView;

    @Override
    protected View onCreateContentView() {
        View view=View.inflate(getContext(),R.layout.recycleview_view,null);
        recyclerView = (RecyclerView) view;
        List<String>list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        adapter = new SynBlogsAdapter<>(getContext(),list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        return view;
    }


    @Override
    protected void dataOnRefresh() {
        
    }

    @Override
    protected void onStartLoadData() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadSuccess();
                setRefreshEnable(false);
            }
        });

    }




}
