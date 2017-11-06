package com.itheima.openchina.ui.fragment.tweetfragments;

import android.view.View;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseFragment;

/**
 * Author:  张慧强
 * Version:  1.0
 * Date:    2017/11/4 0004
 * Modify:
 * Description: //TODO
 * Copyright notice:
 */
public class MyTweetsFragment extends BaseFragment {



    @Override
    protected void dataOnRefresh() {
        onStartLoadData();
    }

    @Override
    protected View onCreateContentView() {
        View view = View.inflate(getContext(), R.layout.view_please_login, null);
        return view;

        //添加条目动画
//        LayoutAnimationController lac=new LayoutAnimationController(AnimationUtils.loadAnimation(getActivity(),R.anim.list_zoom));
//        lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
//        recyclerView.setLayoutAnimation(lac);
//        recyclerView.startLayoutAnimation();

    }
    @Override
    protected void onStartLoadData() {
    }

    //ben提示:清除缓存的list,否则会导致内容重复

    @Override
    public void onPause() {
        super.onPause();

    }


}
