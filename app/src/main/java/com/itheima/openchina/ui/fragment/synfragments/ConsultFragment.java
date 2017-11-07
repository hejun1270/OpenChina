package com.itheima.openchina.ui.fragment.synfragments;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.SynthesizeAdapter.SynConsultAdapter;
import com.itheima.openchina.appcontrol.NetDataApi;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.BlogBean;
import com.itheima.openchina.beans.ConsultBodyBean;
import com.itheima.openchina.beans.FootBean;
import com.itheima.openchina.beans.ConsultHeadBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.interfaces.ItemType;
import java.util.ArrayList;
import java.util.List;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

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
    private ConsultBodyBean bodyData;
    private ConsultHeadBean headData;

    @Override
    protected View onCreateContentView() {
        View view=View.inflate(getContext(),R.layout.recycleview_view,null);

        recyclerView= view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new SynConsultAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);

        //        添加条目动画
        LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(getActivity(), R.anim.list_zoom));
        lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
        recyclerView.setLayoutAnimation(lac);
        recyclerView.startLayoutAnimation();
        adapter.updateData();

        // 上拉加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == SCROLL_STATE_IDLE) {
                    recyclerView.scrollBy(0,-xp2dp(60));
                    loadMore(bodyData.getResult().getNextPageToken());
                }
            }
        });
        return view;
    }




    //xp转为dp
    public int xp2dp(int dp){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,
                getActivity().getResources().getDisplayMetrics());
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
                bodyData = LoadData.getInstance().getBeanData(url
                        ,
                        ConsultBodyBean.class);

                list.removeAll(list);
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

    //上拉加载更多
    public void loadMore(String nextPageToken){
        final String urlMore="http://www.oschina.net/action/apiv2/news?pageToken="+nextPageToken;
        new Thread(new Runnable() {
            @Override
            public void run() {

                headData = LoadData.getInstance().getBeanData(
                        NetDataApi.CONSULT_HEAD,
                        ConsultHeadBean.class);
                bodyData = LoadData.getInstance().getBeanData(url
                        ,
                        ConsultBodyBean.class);

                //添加了头
                list.add(headData.getResult());
                //添加了内容
                list.addAll(bodyData.getResult().getItems());
                list.add(new FootBean());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setmList(list);
                        adapter.updateData();
                    }
                });
            }
        }).start();
    }


    //下拉刷新
    @Override
    protected void dataOnRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                headData = LoadData.getInstance().getBeanData(
                        NetDataApi.CONSULT_HEAD,
                        ConsultHeadBean.class);
                bodyData = LoadData.getInstance().getBeanData(url
                        , ConsultBodyBean.class);

                list.removeAll(list);
                //添加了头
                list.add(headData.getResult());
                //添加了内容
                list.addAll(bodyData.getResult().getItems());
                //添加了尾
                list.add(new FootBean());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setmList(list);
                        onFInishRefresh();
                    }
                });
            }
        }).start();
    }

}
