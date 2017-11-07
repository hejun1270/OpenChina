package com.itheima.openchina.ui.fragment.synfragments;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.SynthesizeAdapter.SynActionAdapter;
import com.itheima.openchina.adapters.SynthesizeAdapter.SynQuestionAdapter;
import com.itheima.openchina.appcontrol.NetDataApi;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.ActionContentBean;
import com.itheima.openchina.beans.ActionHeadBean;
import com.itheima.openchina.beans.ConsultBodyBean;
import com.itheima.openchina.beans.ConsultHeadBean;
import com.itheima.openchina.beans.FootBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.interfaces.HeadType;
import com.itheima.openchina.interfaces.ItemType;
import com.itheima.openchina.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 14:05---
 * Function:
 */

public class ActionFragment extends BaseFragment {

    private SynActionAdapter adapter;
    private RecyclerView recyclerView;
    private String url="http://www.oschina.net/action/apiv2/event?nextPageToken=";
    private List<ItemType> list=new ArrayList<>();
    private ActionContentBean bodyData;
    String urlHead = "http://www.oschina.net/action/apiv2/banner?catalog=3&nextPageToken=226B2C51A4EC6281";
    private ActionHeadBean beanData;

    @Override
    protected View onCreateContentView() {
        View view=View.inflate(getContext(),R.layout.recycleview_view,null);
        recyclerView = (RecyclerView) view;
        adapter = new SynActionAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //添加条目动画
        LayoutAnimationController lac=new LayoutAnimationController(AnimationUtils.loadAnimation(getActivity(),R.anim.list_zoom));
        lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
        recyclerView.setLayoutAnimation(lac);
        recyclerView.startLayoutAnimation();

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
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,
                getActivity().getResources().getDisplayMetrics());
    }

    @Override
    protected void dataOnRefresh() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                beanData = LoadData.getInstance().getBeanData(
                        urlHead, ActionHeadBean.class);
                bodyData = LoadData.getInstance().getBeanData(
                        url, ActionContentBean.class);
                list.removeAll(list);
                //添加了头
                list.addAll(beanData.getResult().getItems());
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

    @Override
    protected void onStartLoadData() {
        ;
        url = "http://www.oschina.net/action/apiv2/event?nextPageToken=DBA816934CD0AA59";
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        beanData = LoadData.getInstance().getBeanData(
                                urlHead, ActionHeadBean.class);
                        bodyData = LoadData.getInstance().getBeanData(
                                url, ActionContentBean.class);
                        list.removeAll(list);
                        //添加了头
                        list.addAll(beanData.getResult().getItems());
                        //添加了内容
                        list.addAll(bodyData.getResult().getItems());
                        //添加了尾
                        list.add(new FootBean());

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                             loadSuccess();
                            }
                        });
                    }
                }).start();


            }
        });

    }
    //上拉加载更多
    public void loadMore(String nextPageToken){
        final String urlMore=url+nextPageToken;
        new Thread(new Runnable() {
            @Override
            public void run() {

                beanData = LoadData.getInstance().getBeanData(
                        urlHead, ActionHeadBean.class);
                bodyData = LoadData.getInstance().getBeanData(
                        urlMore, ActionContentBean.class);
                list.removeAll(list);
                //添加了头
                list.addAll(beanData.getResult().getItems());
                //添加了内容
                list.addAll(bodyData.getResult().getItems());
                //添加了尾
                list.add(new FootBean());
                LogUtils.i(list.size()+"=========");
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




}
