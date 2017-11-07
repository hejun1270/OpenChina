package com.itheima.openchina.adapters.SynthesizeAdapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseRecyclerAdapter;
import com.itheima.openchina.beans.QuestionBean;
import com.itheima.openchina.interfaces.ItemType;
import com.itheima.openchina.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 10:49---
 * Function:
 */

public class SynQuestionAdapter extends BaseRecyclerAdapter {

    List<ItemType> bean=new ArrayList<>();

    private QuestionListener listener;

    public void setBean(List<ItemType> list) {
        this.bean = list;
        notifyItemRangeChanged(1,bean.size()-1);
    }
    public SynQuestionAdapter(Context context, List list) {
        super(context,list);
        bean.addAll(list);
    }


    @Override
    protected View createItemBodyLayout() {
        View view = View.inflate(getContext(), R.layout.view_item_consult_syn_center, null);
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void createViewBodyItem(RecyclerView.ViewHolder holder, int position) {
        if(position>0&&position<bean.size()-1){
            QuestionBean.ResultBean.QuestiontemsBean que=(QuestionBean.ResultBean.QuestiontemsBean) bean.get(position);
            CircleImageView imageView = holder.itemView.findViewById(R.id.imageItemConsult);
            imageView.setVisibility(View.VISIBLE);
            imageView.setMaxWidth(xp2dp(45));

            Glide.with(getContext()).load(que.getAuthorPortrait()).into(imageView);

            TextView title=holder.itemView.findViewById(R.id.consult_title);
            title.setText(que.getTitle());

            TextView content=holder.itemView.findViewById(R.id.consult_content);
            content.setText(que.getBody());

            TextView viewCount=holder.itemView.findViewById(R.id.consult_message1);
            viewCount.setText(que.getViewCount()+"");

            TextView recommendCount=holder.itemView.findViewById(R.id.consult_message2);
            recommendCount.setText(que.getCommentCount()+"");

            TextView time=holder.itemView.findViewById(R.id.consult_time);
            time.setText(que.getAuthor()+"\b\b"+ StringUtils.friendly_time(que.getPubDate()));

        }
    }

    public int xp2dp(int num){
        int value=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                num,getContext().getResources().getDisplayMetrics());
        return value;
    }


    @Override
    protected View createItemHeadLayout() {
        View view = View.inflate(getContext(), R.layout.view_head_question_syn, null);

        RadioGroup group=view.findViewById(R.id.question_group);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_but1:
                        listener.onListener(1);
                        break;
                    case R.id.radio_but2:
                        listener.onListener(2);
                        break;
                    case R.id.radio_but3:
                        listener.onListener(3);
                        break;
                    case R.id.radio_but4:
                        listener.onListener(4);
                        break;
                    case R.id.radio_but5:
                        listener.onListener(5);
                        break;


                }
            }
        });
        return view;
    }



    public interface QuestionListener{
        void onListener(int position);
    }

    public void setQustionListener(QuestionListener listener){
        if(listener!=null){
            this.listener=listener;
        }
    }
}
