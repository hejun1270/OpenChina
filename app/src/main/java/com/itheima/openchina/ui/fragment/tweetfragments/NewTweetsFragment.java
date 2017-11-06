package com.itheima.openchina.ui.fragment.tweetfragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.tweetAdapter.TweetAdapter;
import com.itheima.openchina.appcontrol.NetDataApi;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.bases.BaseRecyclerAdapter;
import com.itheima.openchina.beans.FootBean;
import com.itheima.openchina.beans.TweetInfoBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.interfaces.ItemType;
import com.itheima.openchina.ui.activity.tweet_activity.TweetDetailActivity;
import com.itheima.openchina.utils.ToastUtil;
import com.itheima.openchina.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:  张慧强
 * Version:  1.0
 * Date:    2017/11/4 0004
 * Modify:
 * Description: //TODO
 * Copyright notice:
 */
public class NewTweetsFragment extends BaseFragment implements BaseRecyclerAdapter.RecycleViewItemOnClickListener {

    private RecyclerView recyclerView;
    private List<ItemType> tweetItems=new ArrayList<>();
    private TweetAdapter recyclerViewAdapter;
    private View view;
    private String nextPageToken;
    private List<TweetInfoBean.ResultBean.TweetItem> tweetItemList;
    private String newUrl;
    private String url;
    private int currentItem;
    private int visibleItemPosition;
    private String prevPageToken;


    //下拉刷新
    @Override
    protected void dataOnRefresh() {
        currentState=DOWNDROPREFRESH;
        onStartLoadData();
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected View onCreateContentView() {
        view = View.inflate(getContext(), R.layout.recycleview_view, null);
        recyclerView = (RecyclerView) view;


           init();
        return view;
    }
    //初始化
    private void init() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        //设置RecyclerView的适配器
        recyclerViewAdapter = new TweetAdapter(getContext(),tweetItems);
        recyclerView.setAdapter(recyclerViewAdapter);
        //最新动弹条目点击事件
        recyclerViewAdapter.setRecycleViewItemOnClickListener(this);
        dataOnRefresh();

        //添加条目动画
        LayoutAnimationController lac=new LayoutAnimationController(AnimationUtils.loadAnimation(getActivity(),R.anim.list_zoom));
        lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
        recyclerView.setLayoutAnimation(lac);
        recyclerView.startLayoutAnimation();


        //上拉加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                visibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if(visibleItemPosition ==tweetItems.size()-1 && newState==RecyclerView.SCROLL_STATE_IDLE){
                    currentState=UPDROPREMORE;
                    onStartLoadData();
                    recyclerViewAdapter.notifyDataSetChanged();

                }
            }
        });
    }

    public static final int NOREFRESH=101;//空闲
    public static final int DOWNDROPREFRESH=102;//下拉刷新
    public static final int UPDROPREMORE=102;//上拉加载
    private int currentState=NOREFRESH;
    //请求网络数据
    @Override
    protected void onStartLoadData() {
        if(currentState==UPDROPREMORE){
            url=NetDataApi.NEW_TWEET_URL+prevPageToken;
        }
        if(currentState==DOWNDROPREFRESH){
            url=NetDataApi.NEW_TWEET_URL+nextPageToken;
        }
        if(currentState==NOREFRESH){
            url=NetDataApi.NEW_TWEET_URL;
        }
        /*Thread thread = Thread.currentThread();
        Log.d("------------",thread+"");*/
           new Thread(new Runnable() {
               @Override
               public void run() {
                   TweetInfoBean beanData = LoadData.getInstance().getBeanData(url,TweetInfoBean.class);
                   tweetItemList = beanData.getResult().getItems();
                   nextPageToken = beanData.getResult().getNextPageToken();
                   prevPageToken = beanData.getResult().getPrevPageToken();


                   tweetItems.addAll(tweetItemList);
                   tweetItems.add(new FootBean());
                  //加载成功后
                   Utils.runOnUIThread(new Runnable() {
                       @Override
                       public void run() {
                           loadSuccess();
                           //删除中间的头和脚
                           recyclerViewAdapter.updateData();
                           //recyclerViewAdapter.notifyDataSetChanged();
                       }
                   });

               }
           }).start();


    }



    //ben提示:清除缓存的list,否则会导致内容重复

    @Override
    public void onPause() {
        super.onPause();
        tweetItems.removeAll(tweetItems);

    }
    //条目点击处理
    @Override
    public void onItemOnClick(View view, int position) {
        ToastUtil.showToast("当前条目"+position);
        //判断用户是否登录
        //跳转到详情页面
        Intent intent = new Intent(getContext(), TweetDetailActivity.class);
        intent.putExtra("userData", (Serializable) tweetItemList);
        getContext().startActivity(intent);

    }
}
