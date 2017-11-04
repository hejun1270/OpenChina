package com.itheima.openchina.bases;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.openchina.R;
import com.itheima.openchina.utils.LogUtils;

import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/10/16---
 * Time:  --- 22:06---
 * Function:
 */

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter {
    private  Context context;
    private List<Object> list;

    public static final int HeadType = 0;
    public static final int bodyType = 1;
    public static final int footType = 2;

    public BaseRecyclerAdapter(Context context,List<Object> list) {
        this.context=context;
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public List<Object> getList() {
        return list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO layout布局
        View view = null;
        LogUtils.i(viewType+"");
        switch (viewType) {
            case HeadType:
                if(createItemHeadLayout()!=null){
                    view=createItemHeadLayout();
                }
                break;
            case bodyType:

                view= createItemBodyLayout();
                break;
            case footType:
                
                view=LayoutInflater.from(context).inflate(R.layout.recycleview_foot_load,parent,false);
                break;
           default:
               view= createItemBodyLayout();
               break;
        }

        if(view==null)view=new TextView(context);
        return new ViewHolder(view);
    }

    //添加item布局
    protected abstract View createItemBodyLayout();

    protected View createItemHeadLayout() {
        return null;
    }


    //写控件
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        createViewBodyItem(holder,position);

    }

    //绑定身体控件
    protected abstract void createViewBodyItem(RecyclerView.ViewHolder holder, int position);




    @Override
    public int getItemCount() {
        if(list!=null){
            if(createItemHeadLayout()!=null){
                return list.size()+2;
            }else{
                return list.size()+1;
            }

        }
        return 0;
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

         ViewHolder(View itemView) {
            super(itemView);
        }
    }

    //判断是否是首尾
    @Override
    public int getItemViewType(int position) {
        if (position==0) {
            if(createItemHeadLayout()!=null){
                return HeadType;
            }else{
                return bodyType;
            }
        }
        if (position==getItemCount()-1) {
            return footType;
        }
        return bodyType;
    }


}
