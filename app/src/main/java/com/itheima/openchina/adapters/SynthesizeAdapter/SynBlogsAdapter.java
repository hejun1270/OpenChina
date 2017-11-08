package com.itheima.openchina.adapters.SynthesizeAdapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.itheima.openchina.interfaces.BodyType;
import com.itheima.openchina.ui.activity.syn_activity.DetailsActivity;
import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseRecyclerAdapter;
import com.itheima.openchina.beans.BlogBean;
import com.itheima.openchina.interfaces.ItemType;
import com.itheima.openchina.utils.LogUtils;
import com.itheima.openchina.utils.SpUtil;
import com.itheima.openchina.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 10:49---
 * Function:
 */

public class SynBlogsAdapter extends BaseRecyclerAdapter implements BaseRecyclerAdapter.RecycleViewItemOnClickListener{

    private BlogTitleListener listener;
    List<ItemType> body=new ArrayList<>();

    public void setBody(List<ItemType> body) {
        this.body = body;
        notifyItemRangeChanged(1,body.size()-1);
    }
    public SynBlogsAdapter(Context context, List list) {
        super(context,list);
        body.addAll(list);
    }


    @Override
    protected View createItemBodyLayout() {
        View view = View.inflate(getContext(), R.layout.view_item_consult_syn_center, null);
        return view;
    }



    @SuppressLint("SetTextI18n")
    @Override
    protected void createViewBodyItem(RecyclerView.ViewHolder holder, int position) {
        LogUtils.i("ben");
        BlogBean.ResultBean.BlogItemsBean bean=new BlogBean.ResultBean.BlogItemsBean();
        if(position>0&&position<body.size()-1){
            bean=(BlogBean.ResultBean.BlogItemsBean)body.get(position);
        }
        TextView title=holder.itemView.findViewById(R.id.consult_title);
        if(bean.isOriginal()&&bean.isRecommend()){
            SpannableString san=new SpannableString("*\b*\b"+bean.getTitle());
            Drawable drawable1=getContext().getResources().getDrawable(R.mipmap.ic_label_originate);
            ImageSpan image1=new ImageSpan(drawable1);
            drawable1.setBounds(0,0, (int) title.getTextSize(), (int) title.getTextSize());
            san.setSpan(image1,0,1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

            Drawable drawable2=getContext().getResources().getDrawable(R.mipmap.ic_label_recommend);
            ImageSpan image2=new ImageSpan(drawable2);
            drawable2.setBounds(0,0, (int) title.getTextSize(), (int) title.getTextSize());
            san.setSpan(image2,2,3, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

            title.setText(san);
        }
        if(bean.isOriginal()&&!bean.isRecommend()){
            SpannableString san=new SpannableString("*\b"+bean.getTitle());
            Drawable drawable1=getContext().getResources().getDrawable(R.mipmap.ic_label_originate);
            ImageSpan image1=new ImageSpan(drawable1);
            drawable1.setBounds(0,0, (int) title.getTextSize(), (int) title.getTextSize());
            san.setSpan(image1,0,1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            title.setText(san);
        }
        if(bean.isRecommend()&&!bean.isOriginal()){
            SpannableString san=new SpannableString("*\b"+bean.getTitle());
            Drawable drawable1=getContext().getResources().getDrawable(R.mipmap.ic_label_recommend);
            ImageSpan image1=new ImageSpan(drawable1);
            drawable1.setBounds(0,0, (int) title.getTextSize(), (int) title.getTextSize());
            san.setSpan(image1,0,1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            title.setText(san);

        }
        if(!bean.isOriginal()&&!bean.isRecommend()){
            title.setText(bean.getTitle());
        }



        TextView content=holder.itemView.findViewById(R.id.consult_content);
        content.setText(bean.getBody());

        TextView viewCount=holder.itemView.findViewById(R.id.consult_message1);
        viewCount.setText(bean.getViewCount()+"");

        TextView recommendCount=holder.itemView.findViewById(R.id.consult_message2);
        recommendCount.setText(bean.getCommentCount()+"");

        TextView time=holder.itemView.findViewById(R.id.consult_time);
        String s=bean.getAuthor()+"\b\b"+ StringUtils.friendly_time(bean.getPubDate());
        time.setText(s);

        //title点击判断灰色
        boolean isClick = SpUtil.getBoolean(bean.getTitle(), false);
        if(isClick){
            title.setTextColor(Color.parseColor("#7F878585"));
        }else{
            title.setTextColor(Color.parseColor("#0d0d0d"));
        }
        setRecycleViewItemOnClickListener(this);
    }


    @Override
    protected View createItemHeadLayout() {
        View view = View.inflate(getContext(), R.layout.view_head_blogs_syn, null);
        RadioGroup group = view.findViewById(R.id.blogs_group);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.blog_but1:
                        listener.onClickRadioPosition(1);
                        break;
                    case R.id.blog_but2:
                        listener.onClickRadioPosition(2);
                        break;
                    case R.id.blog_but3:
                        listener.onClickRadioPosition(3);
                        break;
                }
            }
        });
        return view;
    }

    public interface BlogTitleListener{


        void onClickRadioPosition(int position);
    }
    public void setBlogTitleListener(BlogTitleListener listener){
        if(listener!=null){
            this.listener=listener;
        }
    }

    @Override
    public void onItemOnClick(View view, int position) {
        if(body.get(position+1) instanceof BodyType){
            BlogBean.ResultBean.BlogItemsBean bean= (BlogBean.ResultBean.BlogItemsBean) body.get(position+1);
            String title = bean.getTitle();
            SpUtil.saveBoolean(title,true);
            notifyItemRangeChanged(1,body.size()-1);
            Intent intent = new Intent(getContext(),DetailsActivity.class);
            intent.putExtra("href",bean.getHref());
            intent.putExtra("title","博客详情");
            intent.putExtra("commend",bean.getCommentCount()+"");
            getContext().startActivity(intent);
        }
    }
}
