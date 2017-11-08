package com.itheima.openchina.adapters.SynthesizeAdapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.openchina.interfaces.BodyType;
import com.itheima.openchina.ui.activity.syn_activity.DetailsActivity;
import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseRecyclerAdapter;
import com.itheima.openchina.beans.ActionContentBean;
import com.itheima.openchina.beans.ActionHeadBean;
import com.itheima.openchina.interfaces.ItemType;
import com.itheima.openchina.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 10:49---
 * Function: 活动的适配器
 *
 */



public class SynActionAdapter extends BaseRecyclerAdapter implements BaseRecyclerAdapter.RecycleViewItemOnClickListener{



    List<ItemType> mList=new ArrayList<>();
    ActionHeadBean.ResultBean.ActionItems head=new ActionHeadBean.ResultBean.ActionItems();



    public SynActionAdapter(Context context, List list) {
        super(context,list);
         mList.addAll(list);
         head= (ActionHeadBean.ResultBean.ActionItems) list.get(0);
    }

    public void setmList(List<ItemType> mList) {
        this.mList = mList;
        notifyItemRangeChanged(1,mList.size()-1);
    }


    @Override
    protected View createItemBodyLayout() {
        View view = View.inflate(getContext(), R.layout.view_item_consult_syn2, null);
        return view;
    }



    @SuppressLint("SetTextI18n")
    @Override
    protected void createViewBodyItem(RecyclerView.ViewHolder holder, int position) {
        if(position<mList.size()-2){
            ActionContentBean.ResultBean.ItemsBean body = (ActionContentBean.ResultBean.ItemsBean) mList.get(position);

            TextView title = holder.itemView.findViewById(R.id.consult_title);
            title.setText(body.getTitle());
            /**
             * 报名状态没有写,可以根据state来判别,但是在这里都一样就没有判别了
             */
            ImageView image = holder.itemView.findViewById(R.id.imageItemConsult);
            image.setVisibility(View.VISIBLE);
            image.setScaleType(ImageView.ScaleType.MATRIX);
            Glide.with(getContext()).load(body.getImg()).into(image);




            //活动介绍
            TextView content = holder.itemView.findViewById(R.id.consult_content);
            if(body.getType()==3){
                content.setText("其他");
            }else if(body.getType()==4){
                content.setText("站外活动");
            }else if(body.getType()==1){
                content.setText("原创会");
            }
            TextView time = holder.itemView.findViewById(R.id.consult_time);
            time.setText(body.getPubDate());


            TextView message = holder.itemView.findViewById(R.id.consult_message);
            String s=body.getApplyCount()+"人参与";
            message.setText(s);

            //title点击判断灰色
            boolean isClick = SpUtil.getBoolean(body.getTitle(), false);
            if(isClick){
                title.setTextColor(Color.parseColor("#7F878585"));
            }else{
                title.setTextColor(Color.parseColor("#0d0d0d"));
            }
            setRecycleViewItemOnClickListener(this);
        }
    }


    @Override
    protected View createItemHeadLayout() {
        ImageView imageView = (ImageView) View.inflate(getContext(), R.layout.view_head_image_action_syn, null);

        String img = head.getImg();
        Glide.with(getContext()).load(img).into(imageView);
        return imageView;
    }


    @Override
    public void onItemOnClick(View view, int position) {
        if(mList.get(position+1) instanceof BodyType){
            ActionContentBean.ResultBean.ItemsBean body = (ActionContentBean.ResultBean.ItemsBean) mList.get(position+1);
            String title = body.getTitle();
            SpUtil.saveBoolean(title,true);
            notifyItemRangeChanged(1,mList.size()-1);
            Intent intent = new Intent(getContext(),DetailsActivity.class);
            intent.putExtra("href",body.getHref());
            intent.putExtra("id",body.getId()+"");
            intent.putExtra("title","活动详情");
            getContext().startActivity(intent);


        }
    }
}
