package com.itheima.openchina.emoji;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.itheima.openchina.R;

import java.util.ArrayList;
import java.util.List;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/8 <p/>
 * Time: 14:53 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.emoji <p/>
 * Desc: emoji表情适配器
 */

public class EmojiGridAdapter extends BaseAdapter {
    private List<EmojiIcon> datas;
    private Context cxt;

    public EmojiGridAdapter(List<EmojiIcon> datas, Context cxt) {
        this.cxt = cxt;
        if (datas == null) {
            datas = new ArrayList<>(0);
        }
        this.datas = datas;
    }

    public void refresh(List<EmojiIcon> emojiIcons) {
        if (datas == null) {
            datas = new ArrayList<>(0);
        }
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public EmojiIcon getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = new ImageView(cxt);
            int bound = (int) cxt.getResources().getDimension(R.dimen.emoji_size);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(bound, bound);
            convertView.setLayoutParams(params);
            int padding = (int) cxt.getResources().getDimension(R.dimen.normal_padding);
            convertView.setPadding(padding, padding, padding, padding);
        }
        holder = getHolder(convertView);
        holder.mImageView.setImageResource(datas.get(position).getResId());
        return convertView;
    }

    /**
     * 得到holder
     *
     * @param view
     * @return
     */
    private ViewHolder getHolder(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        return holder;
    }

    /**
     * 复用条目holder
     */
    private static class ViewHolder {
        ImageView mImageView;

        public ViewHolder(View view) {
            mImageView = (ImageView) view;
        }
    }

}
