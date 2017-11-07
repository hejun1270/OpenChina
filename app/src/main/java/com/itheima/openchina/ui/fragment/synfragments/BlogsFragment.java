package com.itheima.openchina.ui.fragment.synfragments;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.SynthesizeAdapter.SynBlogsAdapter;
import com.itheima.openchina.appcontrol.NetDataApi;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.bases.BaseRecyclerAdapter;
import com.itheima.openchina.beans.BlogBean;
import com.itheima.openchina.beans.FootBean;
import com.itheima.openchina.beans.HeadBean;
import com.itheima.openchina.beans.QuestionBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.interfaces.ItemType;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 14:05---
 * Function:微博页面适配器
 */

public class BlogsFragment extends BaseFragment {


    private RecyclerView recyclerView;
    List<ItemType> list = new ArrayList<>();
    private String url = NetDataApi.BLOG_RECOMMONED + NetDataApi.PAGER_CURRENT;
    private int i = 0;
    private String TAG = "BlogsFragment";
    private SynBlogsAdapter adapter;
    private BlogBean bodyData;

    @Override
    protected View onCreateContentView() {
        View view = View.inflate(getContext(), R.layout.recycleview_view, null);
        recyclerView = (RecyclerView) view;
        adapter = new SynBlogsAdapter(getContext(), list);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

//        添加条目动画
        LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(getActivity(), R.anim.list_zoom));
        lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
        recyclerView.setLayoutAnimation(lac);
        recyclerView.startLayoutAnimation();

        adapter.setRecycleViewItemOnClickListener(new BaseRecyclerAdapter.RecycleViewItemOnClickListener() {
            @Override
            public void onItemOnClick(View view, int postion) {

            }
        });
        //头条目的点击事件
        adapter.setBlogTitleListener(new SynBlogsAdapter.BlogTitleListener() {
            @Override
            public void onClickRadioPosition(int position) {
                urlChangeLoader(position);
            }
        });

        // 上拉加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCROLL_STATE_IDLE) {
                    recyclerView.scrollBy(0, -xp2dp(60));
                    loadMore(bodyData.getResult().getNextPageToken());
                }
            }
        });
        return view;
    }


    //xp转为dp
    public int xp2dp(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getActivity().getResources().getDisplayMetrics());
    }

    //头条目点击处理
    private void titleChange() {


        new Thread() {
            @Override
            public void run() {
                super.run();

                BlogBean bodyData = LoadData.getInstance().getBeanData(url
                        , BlogBean.class);
                list.removeAll(list);
                //添加了头
                list.add(new HeadBean());
                //添加了内容
                list.addAll(bodyData.getResult().getItems());

                //添加了尾
                list.add(new FootBean());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setBody(list);
                    }
                });

            }
        }.start();
    }


    //下拉刷新

    @Override
    protected void dataOnRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                bodyData = LoadData.getInstance().getBeanData(url
                        , BlogBean.class);
                list.removeAll(list);
                //添加了头
                list.add(new HeadBean());
                //添加了内容
                list.addAll(bodyData.getResult().getItems());
                //添加了尾
                list.add(new FootBean());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setBody(list);
                        onFInishRefresh();
                    }
                });
            }
        }).start();
    }


    //url选择器
    public void urlChangeLoader(int index) {
        index = 4 - index;
        url = "http://www.oschina.net/action/apiv2/blog?catalog=" + index+ "&%20pageToken=";
        titleChange();
    }

    //数据的加载
    @Override
    protected void onStartLoadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                bodyData = LoadData.getInstance().getBeanData(url
                        , BlogBean.class);
                list.removeAll(list);
                //添加了头
                list.add(new HeadBean());
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

    //上拉加载更多
    public void loadMore(String nextPageToken){
        url=url+nextPageToken;
        new Thread(new Runnable() {
            @Override
            public void run() {

                bodyData = LoadData.getInstance().getBeanData(url
                        , BlogBean.class);

                //添加了头
                list.add(new HeadBean());
                //添加了内容
                list.addAll(bodyData.getResult().getItems());
                //添加了尾
                list.add(new FootBean());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setBody(list);
                        adapter.updateData();
                    }
                });
            }
        }).start();
    }


}
