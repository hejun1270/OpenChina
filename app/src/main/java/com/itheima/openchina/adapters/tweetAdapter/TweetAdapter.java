package com.itheima.openchina.adapters.tweetAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseRecyclerAdapter;
import com.itheima.openchina.beans.TweetInfoBean;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Author:  张慧强
 * Version:  1.0
 * Date:    2017/11/4 0004
 * Modify:
 * Description: //TODO
 * Copyright notice:
 */
public class TweetAdapter<T> extends BaseRecyclerAdapter {

    private List<TweetInfoBean.ResultBean.TweetItem> list=new ArrayList<>();
    public TweetAdapter(Context context, List<TweetInfoBean.ResultBean.TweetItem> list) {
        super(context, list);
        this.list=list;
    }




    @Override
    protected View createItemBodyLayout() {

        View view =View.inflate(getContext(), R.layout.item_tweet_new_view,null);
        return view;
    }

    @Override
    protected void createViewBodyItem(RecyclerView.ViewHolder holder, int position) {
        //用户头像
        CircleImageView userImg = holder.itemView.findViewById(R.id.profile_image);
        Glide.with(getContext()).load(list.get(position).getAuthor().getPortrait()).into(userImg);
        //用户名
        TextView userName = holder.itemView.findViewById(R.id.tv_user_name);
           userName.setText(list.get(position).getAuthor().getName());
           //动弹内容
        TextView tweetContent = holder.itemView.findViewById(R.id.tv_tweet_content);
        tweetContent.setText(list.get(position).getContent());
        //发送时间
        TextView sendTime = holder.itemView.findViewById(R.id.tv_send_time);
        sendTime.setText(list.get(position).getPubDate());
        //点赞数量
        TextView thunbupNum = holder.itemView.findViewById(R.id.tv_thumbup_num);
        thunbupNum.setText(list.get(position).getLikeCount());
        //评论数量


    }
}
