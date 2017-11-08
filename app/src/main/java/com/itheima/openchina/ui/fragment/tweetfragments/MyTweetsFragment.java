package com.itheima.openchina.ui.fragment.tweetfragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.tweetAdapter.TweetAdapter;
import com.itheima.openchina.appcontrol.Constant;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.FootBean;
import com.itheima.openchina.beans.LoginInfo;
import com.itheima.openchina.beans.TweetInfoBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.interfaces.ItemType;
import com.itheima.openchina.ui.activity.LoginActivity;
import com.itheima.openchina.utils.SpUtil;
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
public class MyTweetsFragment extends BaseFragment {


    private LinearLayout notLoginOrNet;
    private ImageView ivLoadImg;
    private int visibleItemPosition;
    private RecyclerView recyclerView;
    private List<ItemType> itemLists = new ArrayList<>();
    private TweetAdapter tweetAdapter;
    private LoginInfo loginfo;
    private String uid;
    private String cookie;
    private AlertDialog.Builder aletDialog;
    private List<TweetInfoBean.ResultBean.TweetItem> tweetItemList;
    private List<ItemType> tweetItems = new ArrayList<>();
    private String nextPageToken;
    private String prevPageToken;
    private String url;


    @Override
    protected void dataOnRefresh() {
        currentState = DOWNDROPREFRESH;
        onStartLoadData();
        if (tweetItemList != null) {
            ToastUtil.showToast("暂无新数据");
        }
    }

    @Override
    protected View onCreateContentView() {
        View view = View.inflate(getContext(), R.layout.view_please_login, null);
        recyclerView = view.findViewById(R.id.recycler_view);
        notLoginOrNet = view.findViewById(R.id.not_login_or_net);
       ivLoadImg = view.findViewById(R.id.iv_load_img);
        notLoginOrNet.setVisibility(View.GONE);
        init();
        return view;

    }

    private void init() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        tweetAdapter = new TweetAdapter(getContext(), itemLists);
        //上拉加载更多
        upLoadMoreData();
        recyclerView.setAdapter(tweetAdapter);
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



    @Override
    protected void onStartLoadData() {
       /* //加载时动画
        ivLoadImg.setBackgroundResource(R.drawable.animal_bird);
        AnimationDrawable background = (AnimationDrawable) ivLoadImg.getBackground();
        background.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                getNetData();

            }
        });*/
        isLogin();
    }




    /**
     * 判断是否登录
     */
    private void isLogin() {
        uid = SpUtil.getString(Constant.UID, "");
        cookie = SpUtil.getString(Constant.COOKIE, "");
        if (!TextUtils.equals("", uid) && !TextUtils.equals("", cookie)) {
            //加载数据
            getNetData();
            //LogUtils.i("用户已经登录.....");
        } else {//提示登录
            //LogUtils.i("用户未登录.....");


            loadFailed();
            showLoginDialog();
        }
    }
    public static final int NOREFRESH = 101;//空闲
    public static final int DOWNDROPREFRESH = 102;//下拉刷新
    public static final int UPDROPREMORE = 103;//上拉加载
    private int currentState = NOREFRESH;
    /**
     * 加载数据
     */
    private void getNetData() {
        if (currentState == UPDROPREMORE) {
            //Log.e("aaaa22222", "onStartLoadData: ====UPDROPREMORE");
            url = "http://www.oschina.net/action/apiv2/tweets?pageToken=" + nextPageToken + "&authorId="+uid;
        }
        if (currentState == DOWNDROPREFRESH) {
            //Log.e("aaaa22222", "onStartLoadData: ====DOWNDROPREFRESH");
            url = "http://www.oschina.net/action/apiv2/tweets?pageToken=" + prevPageToken + "&authorId="+uid;
        }
        if (currentState == NOREFRESH) {
            //Log.e("aaaa22222", "onStartLoadData: ====NOREFRESH");
            url = "http://www.oschina.net/action/apiv2/tweets?authorId="+uid;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (currentState == DOWNDROPREFRESH) {

                    Utils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            // tweetItemList.clear();

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
                List<TweetInfoBean.ResultBean.TweetItem> items = beanData.getResult().getItems();
                nextPageToken = beanData.getResult().getNextPageToken();
                prevPageToken = beanData.getResult().getPrevPageToken();
                //LogUtils.i("------------------" + String.valueOf(items));

                itemLists.addAll(items);


                //加载成功后
                Utils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        loadSuccess();
                        //删除中间的头和脚
                        tweetAdapter.updateData();
                        //recyclerViewAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1001) {
            boolean login = data.getBooleanExtra("login", false);
            if (login) {
                uid = SpUtil.getString(Constant.UID, "");
                cookie = SpUtil.getString(Constant.COOKIE, "");
            } else {
                //LogUtils.i("--------<<>>>>>----" + "重新加载");

            getNetData();
            }
        }
    }

    private void showLoginDialog() {
        aletDialog = new AlertDialog.Builder(getContext())
                .setTitle("登录提示:")
                .setMessage("亲，登录后才能查看消息哦 -_-")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivityForResult(intent, 1001);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loadFailed();
                    }
                });
        aletDialog.show();
    }


}
