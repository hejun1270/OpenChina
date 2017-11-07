package com.itheima.openchina.adapters.SynthesizeAdapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseRecyclerAdapter;
import com.itheima.openchina.beans.BlogBean;
import com.itheima.openchina.interfaces.ItemType;
import com.itheima.openchina.utils.LogUtils;
import com.itheima.openchina.utils.StringUtils;
import com.itheima.openchina.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 10:49---
 * Function:
 */

public class SynBlogsAdapter extends BaseRecyclerAdapter {

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
        View view = View.inflate(getContext(), R.layout.view_item_consult_syn, null);
        return view;
    }



    @Override
    protected void createViewBodyItem(RecyclerView.ViewHolder holder, int position) {
        LogUtils.i("ben");
        BlogBean.ResultBean.BlogItemsBean bean=new BlogBean.ResultBean.BlogItemsBean();
        if(position>0&&position<body.size()-1){
            bean=(BlogBean.ResultBean.BlogItemsBean)body.get(position);
        }
        ToastUtil.showToast(bean.getTitle());
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
        TextView time=holder.itemView.findViewById(R.id.consult_time);
        String s=bean.getAuthor()+"\b\b"+ StringUtils.friendly_time(bean.getPubDate());
        time.setText(s);


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
}
