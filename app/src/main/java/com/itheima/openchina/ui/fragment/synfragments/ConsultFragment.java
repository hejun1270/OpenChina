package com.itheima.openchina.ui.fragment.synfragments;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.SynthesizeAdapter.SynConsultAdapter;
import com.itheima.openchina.appcontrol.NetDataApi;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.ConsultBodyBean;
import com.itheima.openchina.beans.FootBean;
import com.itheima.openchina.beans.ConsultHeadBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.interfaces.ItemType;

import java.util.ArrayList;
import java.util.List;

import static com.itheima.openchina.appcontrol.NetDataApi.CONSULT_BODY;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 14:05---
 * Function:咨询页面
 */

public class ConsultFragment extends BaseFragment {

    ConsultHeadBean.ResultBean itemsHead=new ConsultHeadBean.ResultBean();
    private SynConsultAdapter adapter;
    private RecyclerView recyclerView;
    String url="http://www.oschina.net/action/apiv2/news?pageToken=";
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



    //加载数据
    @Override
    protected void onStartLoadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ConsultHeadBean headData = LoadData.getInstance().getBeanData(
                        NetDataApi.CONSULT_HEAD,
                        ConsultHeadBean.class);
                ConsultBodyBean bodyData = LoadData.getInstance().getBeanData(url
                        ,
                        ConsultBodyBean.class);

                //添加了头
                list.add(headData.getResult());
                //添加了内容
                list.addAll(bodyData.getResult().getItems());
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

    //加载更多
    public void loadMore(String BaseUrl, final ConsultBodyBean data){
        final String urlMore=BaseUrl+data.getResult().getNextPageToken();
        new Thread(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onStartLoadData();
                    }
                });
            }
        }).start();
    }


    //下拉刷新
    @Override
    protected void dataOnRefresh() {

        list.remove(list);
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
