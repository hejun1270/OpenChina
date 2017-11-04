package com.itheima.openchina.bases;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.openchina.R;
import com.itheima.openchina.interfaces.ItemBodyType;
import com.itheima.openchina.interfaces.ItemFootType;
import com.itheima.openchina.interfaces.ItemHeadType;

import java.util.List;
import java.util.Random;

/**
 * Created by 佘本民
 * When:  --- 2017/10/16---
 * Time:  --- 22:06---
 * Function:
 */

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter {
    private  Context context;
    private List<View> list;

    public static final int HeadType = 0;
    public static final int bodyType = 1;
    public static final int footType = 2;

    public BaseRecyclerAdapter(Context context,List<View> list) {
        this.context=context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO layout布局
        View view=null;
        switch (viewType) {
            case HeadType:
                view=createItemHeadLayout();
                break;
            case bodyType:
                view= createItemBodyLayout();
                break;
            case footType:
                view=createItemFootLayout();
                break;
           default:
               view= createItemBodyLayout();
               break;

        }

        return new ViewHolder(view);
    }

    //添加item布局
    protected abstract View createItemBodyLayout();

    private View createItemFootLayout() {
        return null;
    }

    private View createItemHeadLayout() {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case HeadType:
                createViewHeadItem(holder,position);
                break;
            case bodyType:
                createViewBodyItem(holder,position);
                break;
            case footType:
                createViewFootItem(holder,position);
                break;
            default:
                createViewBodyItem(holder,position);
                break;
        }


    }

    //绑定身体控件
    protected abstract void createViewBodyItem(RecyclerView.ViewHolder holder, int position);


    private void createViewFootItem(RecyclerView.ViewHolder holder, int position) {
    }

    private void createViewHeadItem(RecyclerView.ViewHolder holder, int position) {
    }


    @Override
    public int getItemCount() {
       return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

         ViewHolder(View itemView) {
            super(itemView);
        }
    }

    //判断是否是首尾
    @Override
    public int getItemViewType(int position) {
        if (list.get(position) instanceof ItemHeadType) {
            return HeadType;
        }
        if (list.get(position) instanceof ItemBodyType) {
            return bodyType;
        }
        if (list.get(position) instanceof ItemFootType) {
            return footType;
        }
        return bodyType;
    }


}
