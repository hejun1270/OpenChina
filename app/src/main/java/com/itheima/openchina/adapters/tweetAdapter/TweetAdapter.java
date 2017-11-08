package com.itheima.openchina.adapters.tweetAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseRecyclerAdapter;
import com.itheima.openchina.beans.TweetInfoBean;
import com.itheima.openchina.interfaces.ItemType;
import com.itheima.openchina.ui.activity.LoadImage;
import com.itheima.openchina.ui.activity.tweet_activity.TweetDetailActivity;
import com.itheima.openchina.utils.StringUtils;

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
public class TweetAdapter extends BaseRecyclerAdapter implements BaseRecyclerAdapter.RecycleViewItemOnClickListener {

    private List<ItemType> list;
    private TweetInfoBean.ResultBean.TweetItem bean;
    private String name;
    private String content;
    private String pubTime;
    private int[] like;
    private String commentnum;
    private String portrait;


    public TweetAdapter(Context context, List list) {
        super(context, list);
        this.list=list;
        Log.e("aaaa22222", "TweetAdapter:   数据的条数 =====" + list.size() );

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

        bean = null;
       this.setRecycleViewItemOnClickListener(this);

        if(position<list.size()-1){
            bean =(TweetInfoBean.ResultBean.TweetItem)list.get(position);
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
        portrait = bean.getAuthor().getPortrait();
        Glide.with(getContext()).load(portrait).into(userImg);
         //头像的点击事件
        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //用户名
        TextView userName = holder.itemView.findViewById(R.id.tv_user_name);
        name = bean.getAuthor().getName();
        userName.setText(name);
        //动弹内容
        TextView tweetContent = holder.itemView.findViewById(R.id.tv_tweet_content);

        content = bean.getContent();

        /*if(content.contains("[:/]")){

        }*/
         //tweetContent.setText(content);
        Spanned spanned = Html.fromHtml(content);
        tweetContent.setText(spanned);
        tweetContent.setMovementMethod(new LinkMovementMethod());
        //内容图片
       final ImageView tlContentImage = holder.itemView.findViewById(R.id.tl_content_image);

        if(bean.getImages()!=null) {
            Glide.with(getContext()).load(bean.getImages().get(0).getThumb()).into(tlContentImage);
            tlContentImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), LoadImage.class);
                    intent.putExtra("contentImage",bean.getImages().get(0).getThumb());
                    getContext().startActivity(intent);
                }
            });
        }

        //Glide.with(getContext()).load(bean.getImages().get(position).getThumb()).into(contentImage);
        /*String thumb = bean.getImages().get(position).getThumb();
        if(thumb!=null){

        Glide.with(getContext()).load(thumb).into(contentImage);
        }*/



        //发送时间
        TextView sendTime = holder.itemView.findViewById(R.id.tv_send_time);
        pubTime = StringUtils.friendly_time(bean.getPubDate());
        sendTime.setText(pubTime);
        //点赞数量
        final TextView thunbupNum = holder.itemView.findViewById(R.id.tv_thumbup_num);
        like = new int[]{bean.getStatistics().getLike()};
        thunbupNum.setText(like[0] +"");
        //点赞图片
        final ImageView icThumbupImg = holder.itemView.findViewById(R.id.ic_thumbup);
        icThumbupImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icThumbupImg.setImageResource(R.mipmap.ic_thumbup_actived);
                  like[0]+=1;

            }
        });
        //评论数量
        TextView commentNum = holder.itemView.findViewById(R.id.tv_comment_num);
        commentnum = bean.getStatistics().getComment() + "";
        commentNum.setText(commentnum);
         //评论图片
        ImageView icComment = holder.itemView.findViewById(R.id.ic_comment);
    }

    @Override
    public void onItemOnClick(View view, int position) {
        //ToastUtil.showToast("当前条目" + position);
        //判断用户是否登录
        //跳转到详情页面
        Intent intent = new Intent(getContext(), TweetDetailActivity.class);
        intent.putExtra("userName",name);
        intent.putExtra("userImage",portrait);
        intent.putExtra("tweetContent",content);
        intent.putExtra("sendTime",pubTime);
        intent.putExtra("thunbupNum",like);
        intent.putExtra("comment",commentnum);

        getContext().startActivity(intent);

    }


    /*public View loadContentImage(RecyclerView view , int position){

        //创建一个流式布局
        FlowLayout flowLayout = new FlowLayout(getContext());
        //给流式布局设置padding
        int padding = getResources().getDimensionPixelSize(R.dimen.padding);
        flowLayout.setPadding(padding,padding,padding,padding);
        //数据加载
        if(bean.getImages()!=null){
        for (TweetInfoBean.ResultBean.TweetItem.ImagesBean imagesBean : bean.getImages()) {


                  String thumb = bean.getImages().get(position).getThumb();
                  Glide.with(getContext()).load(thumb).into(view);
                  //将数据加到流式布局当中
                  flowLayout.addView(contentImage);
              }
        }

         return flowLayout;
    }*/
    /*//创建一个接口
    public interface OnItemClickListener{
        void onItemClick(int position);
    }*/
      /*private OnItemClickListener onItemClickListener;
   public void setOnItemClickListener(OnItemClickListener onItemClickListener){
            this.onItemClickListener=onItemClickListener;
    }*/


}
