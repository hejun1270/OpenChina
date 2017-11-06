package com.itheima.openchina.ui.fragment.tweetfragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.tweetAdapter.TweetAdapter;
import com.itheima.openchina.appcontrol.NetDataApi;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.FootBean;
import com.itheima.openchina.beans.LoginInfo;
import com.itheima.openchina.beans.TweetInfoBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.interfaces.ItemType;
import com.itheima.openchina.utils.LogUtils;
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
    private RecyclerView recyclerView;
    private List<ItemType> itemLists=new ArrayList<>();
    private TweetAdapter tweetAdapter;
    private LoginInfo loginfo;


    @Override
    protected void dataOnRefresh() {
        onStartLoadData();
        tweetAdapter.notifyDataSetChanged();
    }

    @Override
    protected View onCreateContentView() {
        View view = View.inflate(getContext(), R.layout.view_please_login, null);
        recyclerView = view.findViewById(R.id.recycler_view);
        notLoginOrNet = view.findViewById(R.id.not_login_or_net);
        //ivLoadImg = view.findViewById(R.id.iv_load_img);
        notLoginOrNet.setVisibility(View.GONE);
        init();
        return view;

    }

    private void init() {

            /*//加载时动画
            ivLoadImg.setBackgroundResource(R.drawable.animal_bird);
            AnimationDrawable background = (AnimationDrawable) ivLoadImg.getBackground();
            background.start();*/

            /*if(SpUtil.getString(Constant.COOKIE,"")==""){
                notLoginOrNet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // notLoginOrNet.setVisibility(View.GONE);
                        getView().setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivityForResult(intent,100);
                    }
                });
            }else{
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

                tweetAdapter = new TweetAdapter(getContext(), itemLists);

                recyclerView.setAdapter(tweetAdapter);
            }*/
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        tweetAdapter = new TweetAdapter(getContext(), itemLists);

        recyclerView.setAdapter(tweetAdapter);
    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
          if(requestCode==1001){
              loginfo = (LoginInfo) data.getSerializableExtra("loginfo");
                loginfo.
          }
    }*/

    @Override
    protected void onStartLoadData() {
        //http://www.oschina.net/action/apiv2/tweets?pageToken=DBA816934CD0AA59&authorId=3722341
          new Thread(new Runnable() {
              @Override
              public void run() {
                  TweetInfoBean beanData = LoadData.getInstance().getBeanData(NetDataApi.NEW_TWEET_URL, TweetInfoBean.class);
                  List<TweetInfoBean.ResultBean.TweetItem> items = beanData.getResult().getItems();
                  LogUtils.i("------------------"+String.valueOf(items));
                  itemLists.addAll(items);
                     itemLists.add(new FootBean());

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

    //ben提示:清除缓存的list,否则会导致内容重复

    @Override
    public void onPause() {
        super.onPause();

    }


}
