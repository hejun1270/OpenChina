package com.itheima.openchina.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.itheima.openchina.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:  张慧强
 * Version:  1.0
 * Date:    2017/11/7 0007
 * Modify:
 * Description: //TODO
 * Copyright notice:
 */
public class LoadImage extends AppCompatActivity {
    @BindView(R.id.iv_load_img)
    ImageView ivLoadImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String contentImage = intent.getStringExtra("contentImage");
        Glide.with(getApplicationContext()).load(contentImage).into(ivLoadImg);

    }
}
