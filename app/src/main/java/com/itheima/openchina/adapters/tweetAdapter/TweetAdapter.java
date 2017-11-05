package com.itheima.openchina.adapters.tweetAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseRecyclerAdapter;
import com.itheima.openchina.beans.TweetInfoBean;
import com.itheima.openchina.interfaces.ItemType;
import com.itheima.openchina.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Author:  张慧强
 * Version:  1.0
 * Date:    2017/11/4 0004
 * Modify:
 * Description: //TODO 这是动弹模块的适配器（创建条目布局、刷新条目数据、设置接口实现条目点击事件）
 * Copyright notice:
 */
public class TweetAdapter extends BaseRecyclerAdapter {

    private List<ItemType> list=new ArrayList<>();
    public TweetAdapter(Context context, List list) {
        super(context, list);
        this.list.addAll(list);
    }



     //加载动弹条目的布局
    @Override
    protected View createItemBodyLayout() {
        View view =View.inflate(getContext(), R.layout.item_tweet_new_view,null);
        return view;
    }

    //条目布局数据刷新
    @Override
    protected void createViewBodyItem(RecyclerView.ViewHolder holder, final int position) {

        TweetInfoBean.ResultBean.TweetItem bean=null;


        if(position<list.size()-1){
            bean=(TweetInfoBean.ResultBean.TweetItem)list.get(position);
        }else{
            return;
            //ben:暂时性防止下拉崩盘
        }

        //ben:点击事件我封装了,用recycleView实现,set....方法
        //设置条目点击事件
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                   onItemClickListener.onItemClick(position);
//            }
//        });


        //用户头像
        CircleImageView userImg = holder.itemView.findViewById(R.id.profile_image);
        Glide.with(getContext()).load(bean.getAuthor().getPortrait()).into(userImg);
       /* userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        //用户名
        TextView userName = holder.itemView.findViewById(R.id.tv_user_name);
           userName.setText(bean.getAuthor().getName());
           //动弹内容
        TextView tweetContent = holder.itemView.findViewById(R.id.tv_tweet_content);
        tweetContent.setText(bean.getContent());
        //发送时间
        TextView sendTime = holder.itemView.findViewById(R.id.tv_send_time);
        sendTime.setText(bean.getPubDate());
        //点赞数量
        /*TextView thunbupNum = holder.itemView.findViewById(R.id.tv_thumbup_num);
        thunbupNum.setText(list.get(position).getLikeCount());*/
        //评论数量
        /*TextView commentNum = holder.itemView.findViewById(R.id.tv_comment_num);
         commentNum.setText(list.get(position).getCommentCount());*/
    }
    //创建一个接口
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
      private OnItemClickListener onItemClickListener;
   public void setOnItemClickListener(OnItemClickListener onItemClickListener){
            this.onItemClickListener=onItemClickListener;
    }


}
