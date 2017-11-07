package com.itheima.openchina.ui.fragment.tweetfragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.tweetAdapter.TweetAdapter;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.bases.BaseRecyclerAdapter;
import com.itheima.openchina.beans.FootBean;
import com.itheima.openchina.beans.TweetInfoBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.interfaces.ItemType;
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
public class HotTweetsFragment extends BaseFragment implements BaseRecyclerAdapter.RecycleViewItemOnClickListener {

    private RecyclerView recyclerView;
    private List<ItemType> tweetItems=new ArrayList<>();
    private TweetAdapter tweetAdapter;
    public static final int NOREFRESH = 101;//空闲
    public static final int DOWNDROPREFRESH = 102;//下拉刷新
    public static final int UPDROPREMORE = 103;//上拉加载
    private int currentState = NOREFRESH;
    private int visibleItemPosition;
    private List<TweetInfoBean.ResultBean.TweetItem> tweetItemList;
    private String nextPageToken;
    private String prevPageToken;
    private String url;


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
        currentState = NOREFRESH;
        //条目动画
        itemAnimation();
        //上拉加载更多
        upLoadMoreData();
        recyclerView.setAdapter(tweetAdapter);
        //动弹条目的点击事件
        tweetAdapter.setRecycleViewItemOnClickListener(this);



    }
    //条目动画
    private void itemAnimation() {
        //添加条目动画
        LayoutAnimationController lac=new LayoutAnimationController(AnimationUtils.loadAnimation(getActivity(), R.anim.list_zoom));
        lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
        recyclerView.setLayoutAnimation(lac);
        recyclerView.startLayoutAnimation();
    }

    //下拉刷新
    @Override
    protected void dataOnRefresh() {
        currentState = DOWNDROPREFRESH;
        onStartLoadData();


    }
    //上拉加载更多
    private void upLoadMoreData() {
        //上拉加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                visibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (visibleItemPosition == tweetItems.size() - 1 && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    currentState = UPDROPREMORE;
                    onStartLoadData();

                }
            }
        });
    }
    //加载数据
    @Override
    protected void onStartLoadData() {
        //根据不同状态加载不同数据
        if (currentState == UPDROPREMORE) {
            Log.e("aaaa22222", "onStartLoadData: ====UPDROPREMORE");
            url = "http://www.oschina.net/action/apiv2/tweets?pageToken=" + nextPageToken + "&type=2";
        }
        if (currentState == DOWNDROPREFRESH) {
            Log.e("aaaa22222", "onStartLoadData: ====DOWNDROPREFRESH");
            url = "http://www.oschina.net/action/apiv2/tweets?pageToken=" + prevPageToken + "&type=2";
        }
        if (currentState == NOREFRESH) {
            Log.e("aaaa22222", "onStartLoadData: ====NOREFRESH");
            url = "http://www.oschina.net/action/apiv2/tweets?type=2";
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (currentState == DOWNDROPREFRESH) {

                    Utils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            tweetItemList.clear();

                            tweetAdapter.notifyDataSetChanged();
                        }
                    });
                } else if (currentState == UPDROPREMORE) {

                    tweetItems.add(new FootBean());
                    Utils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            tweetAdapter.notifyDataSetChanged();
                        }
                    });
                }

                TweetInfoBean beanData = LoadData.getInstance().getBeanData(url, TweetInfoBean.class);
                tweetItemList = beanData.getResult().getItems();
                nextPageToken = beanData.getResult().getNextPageToken();
                prevPageToken = beanData.getResult().getPrevPageToken();
                tweetItems.addAll(tweetItemList);

                Utils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        //是否需要下拉刷新的开关
                        //setRefreshEnable(false);
                        loadSuccess();
                        tweetAdapter.updateData();
                        //tweetAdapter.notifyDataSetChanged();

                    }
                });
            }
        }).start();
    }

    //ben:需要清除list的内容缓存,
    @Override
    public void onPause() {
        super.onPause();
        //tweetItems.removeAll(tweetItems);
    }


    //条目点击事件处理
    @Override
    public void onItemOnClick(View view, int position) {
        ToastUtil.showToast("当前点击的条目"+position);
    }
}
