package com.itheima.openchina.adapters.SynthesizeAdapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import com.itheima.loopviewpager.LoopViewPager;
import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseRecyclerAdapter;
import com.itheima.openchina.beans.ConsultBodyBean;
import com.itheima.openchina.beans.ConsultHeadBean;
import com.itheima.openchina.interfaces.ItemType;
import com.itheima.openchina.utils.SpUtil;
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

public class SynConsultAdapter extends BaseRecyclerAdapter implements BaseRecyclerAdapter.RecycleViewItemOnClickListener {

    public void setmList(List<ItemType> mList) {
        this.mList = mList;
        notifyItemRangeChanged(1,mList.size()-1);
    }

    List<ItemType> mList=new ArrayList<>();
    ConsultHeadBean.ResultBean bean=new ConsultHeadBean.ResultBean();




    public SynConsultAdapter(Context context, List list) {
        super(context,list);
        mList.addAll(list);
        //判断是头部分
        bean= (ConsultHeadBean.ResultBean) list.get(0);

    }


    @Override
    protected View createItemBodyLayout() {
        View view = View.inflate(getContext(), R.layout.view_item_consult_syn, null);
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void createViewBodyItem(RecyclerView.ViewHolder holder, int position) {
        ConsultBodyBean.ConsultBodyResultBean.ItemsBean body;
        if(position<mList.size()-1&&position>0) {
            body = (ConsultBodyBean.ConsultBodyResultBean.ItemsBean) mList.get(position);
            TextView title = holder.itemView.findViewById(R.id.consult_title);

            //title点击判断灰色
            boolean isClick = SpUtil.getBoolean(body.getTitle(), false);
            if(isClick){
                title.setTextColor(Color.parseColor("#7F878585"));
            }else{
                title.setTextColor(Color.parseColor("#0d0d0d"));
            }

            String s=StringUtils.friendly_time(body.getPubDate());
            TextView content = holder.itemView.findViewById(R.id.consult_content);
            content.setText(body.getBody());

            TextView message = holder.itemView.findViewById(R.id.consult_message);
            message.setText(body.getCommentCount()+"");

            TextView time = holder.itemView.findViewById(R.id.consult_time);
            time.setText(s);
            if(!s.contains("天")){
                SpannableString san=new SpannableString("*\b"+body.getTitle());
                Drawable drawable=getContext().getResources().getDrawable(R.mipmap.ic_label_today);
                ImageSpan image=new ImageSpan(drawable);
                drawable.setBounds(0,0, (int) title.getTextSize(), (int) title.getTextSize());
                san.setSpan(image,0,1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                title.setText(san);
            }else{
                title.setText(body.getTitle());
            }


        }else{
            return;
        }

        setRecycleViewItemOnClickListener(this);
    }


    @Override
    protected View createItemHeadLayout() {


        List<String> urlImage = new ArrayList<>();
        List<String> urlText = new ArrayList<>();
        for (int i = 0; i <bean.getItems().size(); i++) {
            urlImage.add(bean.getItems().get(i).getImg());
            urlText.add(bean.getItems().get(i).getName());
        }

        View view = View.inflate(getContext(), R.layout.view_loopviewpager, null);
        LoopViewPager loopView = view.findViewById(R.id.lvp_pager);
        loopView.setImgData(urlImage);
        loopView.setTitleData(urlText);

        loopView.start();
        return view;
    }

    //条目点击事件
    @Override
    public void onItemOnClick(View view, int position) {
        if(position>0||position<mList.size()-1){
            ConsultBodyBean.ConsultBodyResultBean.ItemsBean itemsBean
                    =  (ConsultBodyBean.ConsultBodyResultBean.ItemsBean) mList.get(position+1);
            String title = itemsBean.getTitle();
            SpUtil.saveBoolean(title,true);
            notifyItemRangeChanged(1,mList.size()-1);
        }

    }





}
