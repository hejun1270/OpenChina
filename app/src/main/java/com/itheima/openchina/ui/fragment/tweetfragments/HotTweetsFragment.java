package com.itheima.openchina.ui.fragment.tweetfragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.tweetAdapter.TweetAdapter;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.bases.FootBean;
import com.itheima.openchina.beans.TweetInfoBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.interfaces.ItemType;
import com.itheima.openchina.utils.LogUtils;
import com.itheima.openchina.utils.ToastUtil;
import com.itheima.openchina.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  张慧强
 * Version:  1.0
 * Date:    2017/11/4 0004
 * Modify:
 * Description:
 * Copyright notice:
 */
public class HotTweetsFragment extends BaseFragment implements TweetAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private List<ItemType> tweetItems=new ArrayList<>();
    private TweetAdapter tweetAdapter;

    @Override
    protected void dataOnRefresh() {
        onStartLoadData();
        tweetAdapter.notifyDataSetChanged();

    }
     //创建热门动弹页面布局
    @Override
    protected View onCreateContentView() {
        View view = View.inflate(getContext(), R.layout.recycleview_view,null);
        recyclerView = (RecyclerView) view;
        init();
        return view;
    }
     //初始化方法
    private void init() {
        //RecyclerView布局模式
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        //设置RecyclerView的适配器
        tweetAdapter = new TweetAdapter(getContext(), tweetItems);
        recyclerView.setAdapter(tweetAdapter);
        //动弹条目的点击事件
        tweetAdapter.setOnItemClickListener(this);

        //添加条目动画
        LayoutAnimationController lac=new LayoutAnimationController(AnimationUtils.loadAnimation(getActivity(),R.anim.list_zoom));
        lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
        recyclerView.setLayoutAnimation(lac);
        recyclerView.startLayoutAnimation();
    }
    //加载数据
    @Override
    protected void onStartLoadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                TweetInfoBean beanData = LoadData.getInstance().getBeanData("http://www.oschina.net/action/apiv2/tweets?type=2", TweetInfoBean.class);
                tweetItems.addAll(beanData.getResult().getItems());
                tweetItems.add(new FootBean());
                Utils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {

                        setRefreshEnable(false);
                        loadSuccess();
                        tweetAdapter.notifyDataSetChanged();

                    }
                });
            }
        }).start();
    }

    //ben:需要清除list的内容缓存,


    @Override
    public void onPause() {
        super.onPause();
        tweetItems.removeAll(tweetItems);
    }

    @Override
    public void onItemClick(int position) {
        ToastUtil.showToast("当前点击的条目"+position);
    }
}
