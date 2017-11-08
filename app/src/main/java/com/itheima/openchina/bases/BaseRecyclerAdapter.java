package com.itheima.openchina.bases;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.openchina.R;
import com.itheima.openchina.interfaces.BodyType;
import com.itheima.openchina.interfaces.FootType;
import com.itheima.openchina.interfaces.HeadType;
import com.itheima.openchina.interfaces.ItemType;

import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/10/16---
 * Time:  --- 22:06---
 * Function:
 */

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter {
    private  Context context;
    private List<ItemType> list;


    //多条目
    public static final int HEADTYPE = 101;
    public static final int BODYTYPE = 102;
    public static final int FOOTTYPE = 103;
     RecycleViewItemOnClickListener mListener;


    public BaseRecyclerAdapter(Context context,List<ItemType> list) {
        this.context=context;
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public List<ItemType> getList() {
        return list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = null;
        switch (viewType) {
            case HEADTYPE:
                view=createItemHeadLayout(parent);
                break;
            case BODYTYPE:
                view= createItemBodyLayout();
                break;
            case FOOTTYPE:
                view=LayoutInflater.from(context).inflate(R.layout.recycleview_foot_load,parent,false);
                break;
           default:
               break;
        }
//        if(view!=null){
           ViewHolder fmListHolder = new ViewHolder(view,mListener);
//        }
        return fmListHolder;
    }

    //添加item布局
    protected abstract View createItemBodyLayout();

    protected View createItemHeadLayout(ViewGroup parent) {
        return null;
    }


    //写控件
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {

            case HEADTYPE:
                break;
            case BODYTYPE:
                createViewBodyItem(holder,position);
                //LogUtils.i("body"+position);
                break;
            case FOOTTYPE:
                //LogUtils.i("foot"+position);
                break;
            default:
                break;
        }

    }

    //绑定身体控件
    protected abstract void createViewBodyItem(RecyclerView.ViewHolder holder, int position);




    @Override
    public int getItemCount() {
        if(list==null){
            return 0;
        }
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        ViewHolder(View itemView, RecycleViewItemOnClickListener mListener) {
             super(itemView);
             BaseRecyclerAdapter.this.mListener=mListener;
             //条目的点击事件
             itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
             if(mListener!=null){
                /* if(createItemHeadLayout(parent)!=null){
                     mListener.onItemOnClick(view,getPosition()-1);
                 }else{
                     mListener.onItemOnClick(view,getPosition());
                 }*/
                 mListener.onItemOnClick(view,getPosition());
             }
        }
    }

    //判断是否是首尾
    @Override
    public int getItemViewType(int position) {
        if (list.get(position) instanceof HeadType) {
            return HEADTYPE;
        }
        if (list.get(position) instanceof BodyType) {
            return BODYTYPE;
        }
        if (list.get(position) instanceof FootType) {
            //LogUtils.i("footSuccess"+position);
            return FOOTTYPE;
        }
       // LogUtils.i("footFail"+position);
        return BODYTYPE;
    }

    //自动删除脚跟头的方法
    public void updateData() {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) instanceof HeadType) {
                list.remove(i);
            }
        }

        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i) instanceof FootType) {
                list.remove(i);
            }
        }

        notifyDataSetChanged();

    }

    public interface RecycleViewItemOnClickListener {
        public void onItemOnClick(View view,int position);

    }
    public void setRecycleViewItemOnClickListener(RecycleViewItemOnClickListener mListener){
        if(mListener!=null){
            this.mListener=mListener;
        }
    }


}
