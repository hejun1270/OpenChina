package com.itheima.openchina.adapters.tweetAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseRecyclerAdapter;
import com.itheima.openchina.beans.TweetInfoBean;
import com.itheima.openchina.interfaces.ItemType;
import com.itheima.openchina.ui.activity.tweet_activity.TweetDetailActivity;
import com.itheima.openchina.utils.PlatfromUtil;
import com.itheima.openchina.utils.StringUtils;
import com.itheima.openchina.utils.ToastUtil;
import com.itheima.openchina.wedigt.FlowLayout;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.itheima.openchina.utils.Utils.getResources;


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
    private FlowLayout flowLayout;
    private ImageView imageView;

    private String thumb;


    public TweetAdapter(Context context, List list) {
        super(context, list);
        this.list = list;
        //Log.e("aaaa22222", "TweetAdapter:   数据的条数 =====" + list.size());

    }


    //加载动弹条目的布局
    @Override
    protected View createItemBodyLayout() {
        View view = View.inflate(getContext(), R.layout.item_tweet_new_view, null);
        this.setRecycleViewItemOnClickListener(this);
        return view;
    }

    //条目布局数据刷新
    @Override
    protected void createViewBodyItem(RecyclerView.ViewHolder holder, final int position) {

        bean = null;

        if (position < list.size() - 1) {
            bean = (TweetInfoBean.ResultBean.TweetItem) list.get(position);
        } else {
            return;
            //ben:暂时性防止下拉崩盘
        }




        //用户头像
        CircleImageView userImg = holder.itemView.findViewById(R.id.profile_image);
        portrait = bean.getAuthor().getPortrait();
        Glide.with(getContext()).load(portrait).into(userImg);
        //头像的点击事件
        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast("用户信息"+position);
            }
        });

        //用户名
        TextView userName = holder.itemView.findViewById(R.id.tv_user_name);
        name = bean.getAuthor().getName();
        userName.setText(name);

        //动弹内容
        TextView tweetContent = holder.itemView.findViewById(R.id.tv_tweet_content);
        content = bean.getContent();
        /*if(content!=null){
            char[] chars = content.toCharArray();
            for (int i=0;i<chars.length;i++) {
                if(i+3<chars.length){

                if(chars[i]=='['&&chars[i+3]==']'){
                    ImageSpan imageSpan = new ImageSpan;
                }
                }
            }
        }*/


        //tweetContent.setText(content);
        Spanned spanned = Html.fromHtml(content);
        tweetContent.setText(spanned);
        tweetContent.setMovementMethod(new LinkMovementMethod());

         flowLayout = holder.itemView.findViewById(R.id.flowlayout);
        flowLayout.removeAllViews();
        //内容图片
        if (bean.getImages()!=null) {
            //创建一个流式布局
            //给流式布局设置padding
            int padding = getResources().getDimensionPixelSize(R.dimen.padding);
            flowLayout.setPadding(padding, padding, padding, padding);
            for (int i = 0; i < bean.getImages().size(); i++) {
                String thumb = bean.getImages().get(i).getThumb();
                //数据加载
                imageView = new ImageView(getContext());
                Glide.with(getContext()).load(thumb).into(imageView);
               flowLayout.setSpace(5,5);

               //flowLayout.setAnimation(new ScaleAnimation(0.5f,1,0.5f,1, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f));
                //将数据加到流式布局当中
                flowLayout.addView(imageView);

            }
            //imageToLarge(i);

        }


        //发送时间
        TextView sendTime = holder.itemView.findViewById(R.id.tv_send_time);
        pubTime = StringUtils.friendly_time(bean.getPubDate());
        sendTime.setText(pubTime);

        TextView tvPlatfrom = holder.itemView.findViewById(R.id.tv_platfrom);
        PlatfromUtil.setPlatFromString(tvPlatfrom,bean.getAppClient());
        //点赞数量
        final TextView thunbupNum = holder.itemView.findViewById(R.id.tv_thumbup_num);
        like = new int[]{bean.getStatistics().getLike()};
        thunbupNum.setText(like[0] + "");
        //点赞图片
        final ImageView icThumbupImg = holder.itemView.findViewById(R.id.ic_thumbup);
        //点赞点击事件
        icThumbupImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icThumbupImg.setImageResource(R.mipmap.ic_thumbup_actived);
                like[0] += 1;
               thunbupNum.setText(like[0]+"");
            }
        });
        //评论数量
        TextView commentNum = holder.itemView.findViewById(R.id.tv_comment_num);
        commentnum = bean.getStatistics().getComment() + "";
        commentNum.setText(commentnum);

    }

   /* private void imageToLarge(final int i) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TweetInfoBean.ResultBean.TweetItem itemType = (TweetInfoBean.ResultBean.TweetItem) list.get(position);
                ToastUtil.showToast("图片"+itemType.getImages().get(i).getHref());

                if(itemType.getImages().size()!=0&&imageView!=null){
                    Intent intent = new Intent(getContext(), LoadImage.class);
                    String href = itemType.getImages().get(position).getHref();
                    intent.putExtra("contentImage",href );
                    getContext().startActivity(intent);
                }


            }
        });
    }*/

    @Override
    public void onItemOnClick(View view, int position) {

        TweetInfoBean.ResultBean.TweetItem itemType = (TweetInfoBean.ResultBean.TweetItem) list.get(position);
       // ToastUtil.showToast(position+"当前条目" + itemType);
        //判断用户是否登录
        //跳转到详情页面
        Intent intent = new Intent(getContext(), TweetDetailActivity.class);
        String name1 = itemType.getAuthor().getName();
        intent.putExtra("userName", name1);
        String portrait1 = itemType.getAuthor().getPortrait();
        intent.putExtra("userImage", portrait1);
        String content1 = itemType.getContent();
        intent.putExtra("tweetContent", content1);

        flowLayout.removeAllViews();
        //内容图片
        if (itemType.getImages() != null) {
            //创建一个流式布局
            //给流式布局设置padding
            int padding = getResources().getDimensionPixelSize(R.dimen.padding);
            flowLayout.setPadding(padding, padding, padding, padding);
            for (int i = 0; i < itemType.getImages().size(); i++) {
                thumb = itemType.getImages().get(i).getThumb();
                //数据加载
                imageView = new ImageView(getContext());
                Glide.with(getContext()).load(thumb).into(imageView);

                //将数据加到流式布局当中
                flowLayout.addView(imageView);

            }
            intent.putExtra("contentImage",thumb);
        }

        String pubDate = itemType.getPubDate();
        intent.putExtra("sendTime", pubDate);
        String likeCount = itemType.getStatistics().getLike()+"";
        intent.putExtra("thunbupNum", likeCount);
        String commentCount = itemType.getStatistics().getComment()+"";
        intent.putExtra("comment", commentCount);

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
