package com.itheima.openchina.emoji;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/8 <p/>
 * Time: 9:58 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.emoji <p/>
 * Desc: 显示表情的fragment
 */
@SuppressLint("ValidFragment")
public class EmojiPageFragment extends Fragment {
    private OnEmojiClickListener mOnEmojiClickListener;
    private List<EmojiIcon> mDatas;
    private GridView mGridView;
    private EmojiGridAdapter mAdapter;

    public EmojiPageFragment(int type, OnEmojiClickListener listener) {
        mOnEmojiClickListener = listener;
        initData(type);
    }

    /**
     * 初始化数据
     *
     * @param type
     */
    private void initData(int type) {
        mDatas = new ArrayList<>();
        mDatas.addAll(EmojiRules.getAllByType(type));
    }

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        mGridView = new GridView(getActivity());
        mGridView.setNumColumns(EmojiConfig.COLUMNS);
        mAdapter = new EmojiGridAdapter(mDatas, getActivity());
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mOnEmojiClickListener != null) {
                    mOnEmojiClickListener.onEmojiClick((EmojiIcon) parent.getAdapter().getItem
                            (position));
                }
            }
        });
        mGridView.setSelector(new ColorDrawable(android.R.color.transparent));
        return mGridView;
    }
}
