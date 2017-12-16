package com.itheima.openchina.ui.fragment.synfragments;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.SynthesizeAdapter.SynQuestionAdapter;
import com.itheima.openchina.bases.BaseFragment;
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
 * Function:问答界面
 */

public class QuestionFragment extends BaseFragment {

    private SynQuestionAdapter adapter;
    private RecyclerView recyclerView;
    private List<ItemType> list=new ArrayList<>();
    private String url;
    private QuestionBean questionBean;
    private int size;
    private String urlMore;

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

        // 上拉加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition=layoutManager.findLastVisibleItemPosition();
                if (newState == SCROLL_STATE_IDLE&&lastVisibleItemPosition==list.size()-1) {
                    if(list.size()==size){
                        Toast.makeText(getActivity().getApplication(), "数据正在赶来的途中..", Toast.LENGTH_SHORT).show();
                    }
                    recyclerView.scrollBy(0, -xp2dp(60));
                    loadMore(questionBean.getResult().getNextPageToken());
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

    private void notifyChange(int position) {
         url = "http://www.oschina.net/action/apiv2/question?catalog=" +
                position + "&nextPageToken=";

        new Thread(new Runnable() {
            @Override
            public void run() {

                questionBean = LoadData.getInstance().getBeanData(url, QuestionBean.class);
                list.removeAll(list);
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

        url = "http://www.oschina.net/action/apiv2/question?catalog=1&nextPageToken=";
        new Thread(new Runnable() {
            @Override
            public void run() {

                questionBean = LoadData.getInstance().getBeanData(url, QuestionBean.class);
                list.remove(list);
                list.add(new HeadBean());
                list.addAll(questionBean.getResult().getItems());
                list.add(new FootBean());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setBean(list);
                        onFInishRefresh();
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onStartLoadData() {
        url = "http://www.oschina.net/action/apiv2/question?catalog=1&nextPageToken=";
        new Thread(new Runnable() {
            @Override
            public void run() {

                questionBean = LoadData.getInstance().getBeanData(url, QuestionBean.class);
                list.remove(list);
                list.add(new HeadBean());
                list.addAll(questionBean.getResult().getItems());
                list.add(new FootBean());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadSuccess();
                        adapter.notifyItemRangeChanged(1,list.size()-1);
                    }
                });
            }
        }).start();

    }

    //上拉加载更多
    public void loadMore(String nextPageToken){
        urlMore = url+nextPageToken;
        size = list.size();
        new Thread(new Runnable() {
            @Override
            public void run() {

                questionBean = LoadData.getInstance().getBeanData(urlMore, QuestionBean.class);
                list.add(new HeadBean());
                list.addAll(questionBean.getResult().getItems());
                list.add(new FootBean());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setBean(list);
                        adapter.updateData();
                    }
                });
            }
        }).start();
    }



}
