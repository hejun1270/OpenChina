package com.itheima.openchina.ui.fragment.synfragments;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.SynthesizeAdapter.SynConsultAdapter;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.ConsultHeadBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 14:05---
 * Function:
 */

public class ConsultFragment<T> extends BaseFragment {

    List<ConsultHeadBean.ResultBean.ItemsBean> itemsHead=new ArrayList<>();
    private SynConsultAdapter<String> adapter;
    private RecyclerView recyclerView;

    @Override
    protected View onCreateContentView() {
        View view=View.inflate(getContext(),R.layout.recycleview_view,null);
        recyclerView = (RecyclerView) view;
        List<String>list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        adapter = new SynConsultAdapter<>(getContext(),list, itemsHead);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }


    @Override
    protected void dataOnRefresh() {
        
    }

    @Override
    protected void onStartLoadData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ConsultHeadBean beanData = LoadData.getInstance().getBeanData(
                        "http://www.oschina.net/action/apiv2/banner?catalog=1",
                        ConsultHeadBean.class);
                itemsHead= beanData.getResult().getItems();

                toDoinUI();
            }
        }).start();

    }

    private void toDoinUI() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(itemsHead.size()+"");
                loadSuccess();
                setRefreshEnable(false);
            }
        });

    }

    @Override
    protected void onFInishRefresh() {
        super.onFInishRefresh();
    }
}
