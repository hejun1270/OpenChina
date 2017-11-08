package com.itheima.openchina.ui.activity;

import android.content.Intent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:  张慧强
 * Version:  1.0
 * Date:    2017/11/7 0007
 * Modify:
 * Description: //TODO
 * Copyright notice:
 */
public class LoadImage extends BaseActivity {


    @BindView(R.id.iv_load_img)
    ImageView ivLoadImg;
    @BindView(R.id.btn_back)
    ImageView btnBack;

    @Override
    protected int getLayoutRs() {
        return R.layout.activity_load_image;
    }

    @Override
    protected void init() {

        super.init();
        Intent intent = getIntent();
        String contentImage = intent.getStringExtra("contentImage");
        Glide.with(getApplicationContext()).load(contentImage).into(ivLoadImg);
    }



    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }
}
