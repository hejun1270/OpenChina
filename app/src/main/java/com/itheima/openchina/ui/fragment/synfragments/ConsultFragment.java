package com.itheima.openchina.ui.fragment.synfragments;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.SynthesizeAdapter.SynConsultAdapter;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.FootBean;
import com.itheima.openchina.beans.ConsultHeadBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.interfaces.ItemType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 14:05---
 * Function:
 */

public class ConsultFragment extends BaseFragment {

    ConsultHeadBean.ResultBean itemsHead=new ConsultHeadBean.ResultBean();
    private SynConsultAdapter adapter;
    private RecyclerView recyclerView;
    List<ItemType>list=new ArrayList<>();

    @Override
    protected View onCreateContentView() {
        View view=View.inflate(getContext(),R.layout.recycleview_view,null);

        recyclerView= view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new SynConsultAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        adapter.updateData();
        return view;
    }



    @Override
    protected void onStartLoadData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ConsultHeadBean beanData = LoadData.getInstance().getBeanData(
                        "http://www.oschina.net/action/apiv2/banner?catalog=1",
                        ConsultHeadBean.class);
                //添加了头
                list.add(beanData.getResult());
                //添加了内容.........
                //添加了尾
                list.add(new FootBean());
                toDoinUI();
            }
        }).start();

    }

    private void toDoinUI() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadSuccess();
            }
        });
    }

    @Override
    protected void dataOnRefresh() {
        onStartLoadData();
        adapter.notifyDataSetChanged();
    }



    //清除缓存
    @Override
    public void onPause() {
        super.onPause();
        list.removeAll(list);
    }
}
