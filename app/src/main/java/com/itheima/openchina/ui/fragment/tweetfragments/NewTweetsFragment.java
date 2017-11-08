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
import com.itheima.openchina.beans.FootBean;
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
 * Description: //TODO
 * Copyright notice:
 */
public class NewTweetsFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<ItemType> tweetItems = new ArrayList<>();
    private TweetAdapter recyclerViewAdapter;
    private View view;
    private String nextPageToken;
    private List<TweetInfoBean.ResultBean.TweetItem> tweetItemList;
    private String newUrl;
    private String url;
    private int currentItem;
    private int visibleItemPosition;
    private String prevPageToken;
    public static final int NOREFRESH = 101;//空闲
    public static final int DOWNDROPREFRESH = 102;//下拉刷新
    public static final int UPDROPREMORE = 103;//上拉加载
    private int currentState = NOREFRESH;
    private TweetInfoBean beanData;


    //加载布局
    @Override
    protected View onCreateContentView() {
        view = View.inflate(getContext(), R.layout.recycleview_view, null);
        recyclerView = (RecyclerView) view;
        init();
        return view;
    }

    //初始化
    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //设置RecyclerView的适配器
        recyclerViewAdapter = new TweetAdapter(getContext(), tweetItems);
        currentState = NOREFRESH;
        //条目的动画
        itemAnimation();
        //上拉加载更多
        upLoadMoreData();
        recyclerView.setAdapter(recyclerViewAdapter);


    }

    //下拉刷新
    @Override
    protected void dataOnRefresh() {
        currentState = DOWNDROPREFRESH;
        onStartLoadData();
        if (tweetItemList != null) {
            ToastUtil.showToast("暂无新数据");
        }
    }

    //条目的动画
    private void itemAnimation() {
        //添加条目动画

        LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(getActivity(), R.anim.list_zoom));
        lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
        recyclerView.setLayoutAnimation(lac);
        recyclerView.startLayoutAnimation();
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

    //请求网络数据
    //http://www.oschina.net/action/apiv2/tweets?pageToken=56374BD0797A25134AD6659CDD5740BA&type=1
    @Override
    protected void onStartLoadData() {
        if (currentState == UPDROPREMORE) {
            Log.e("aaaa22222", "onStartLoadData: ====UPDROPREMORE");
            url = "http://www.oschina.net/action/apiv2/tweets?pageToken=" + nextPageToken + "&type=1";
        }
        if (currentState == DOWNDROPREFRESH) {
            Log.e("aaaa22222", "onStartLoadData: ====DOWNDROPREFRESH");
            url = "http://www.oschina.net/action/apiv2/tweets?pageToken=" + prevPageToken + "&type=1";
        }
        if (currentState == NOREFRESH) {
            Log.e("aaaa22222", "onStartLoadData: ====NOREFRESH");
            url = "http://www.oschina.net/action/apiv2/tweets?type=1";
        }
        //tweetItemList.clear();
        /*Thread thread = Thread.currentThread();
        Log.d("------------",thread+"");*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (currentState == DOWNDROPREFRESH) {

                    Utils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            // tweetItemList.clear();

                            recyclerViewAdapter.notifyDataSetChanged();
                        }
                    });
                } else if (currentState == UPDROPREMORE) {
                    tweetItems.add(new FootBean());
                    Utils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerViewAdapter.notifyDataSetChanged();
                        }
                    });
                }

                beanData = LoadData.getInstance().getBeanData(url, TweetInfoBean.class);
                tweetItemList = beanData.getResult().getItems();
                nextPageToken = beanData.getResult().getNextPageToken();
                prevPageToken = beanData.getResult().getPrevPageToken();
                tweetItems.addAll(tweetItemList);

                LogUtils.i("run:   tweetItemList的个数====  " + tweetItemList.size() + "=====tweetItems的个数====" + tweetItems.size());
                //加载成功后
                Utils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        //删除中间的头和脚
                        loadSuccess();
                        if(recyclerViewAdapter!=null){

                        recyclerViewAdapter.updateData();
                        }
                        LogUtils.i("tweetItems-------------<>" + tweetItems.size());
                    }
                });
            }
        }).start();
    }

    @Override
    public void onPause() {
        super.onPause();
        //tweetItems.clear();

    }


}
