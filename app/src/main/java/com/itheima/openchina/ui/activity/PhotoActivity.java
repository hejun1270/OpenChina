package com.itheima.openchina.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.itheima.openchina.R;
import com.itheima.openchina.wedigt.PictureMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * User:user <p/>
 * Date: 2017/10/21 13:13 <p/>
 * Project:GooglePlay <p/>
 * Package:com.adminhj.googleplay.activity <p/>
 * Desc:  浏览单张图片
 * 调用此activity时传入图片地址（网络地址） key :url
 * <p/>
 */

public class PhotoActivity extends AppCompatActivity {
    @BindView(R.id.photoview)
    PhotoView photoview;
    @BindView(R.id.iv_more)
    ImageView mIvMore;
    private Animation enter;
    private Animation exit;
    private PictureMenu mPictureMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        photoview.enable();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        String imgUrl = getIntent().getStringExtra("url");
        Glide.with(this)
                .load(imgUrl)
                .crossFade()
                .override(displayMetrics.widthPixels / 2, displayMetrics.heightPixels / 2)
                .placeholder(R.mipmap.ic_default_image_error)
                .into(photoview);
        enter = AnimationUtils.loadAnimation(this, R.anim.photo_enter);
        exit = AnimationUtils.loadAnimation(this, R.anim.photo_exit);
        mPictureMenu = new PictureMenu(PhotoActivity.this);
        photoview.startAnimation(enter);
    }


    private void exitActivity() {
        photoview.startAnimation(exit);
        exit.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @OnClick({R.id.photoview, R.id.iv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.photoview:
                exitActivity();
                break;
            case R.id.iv_more:
                mPictureMenu.show();
                break;
        }
    }

}
