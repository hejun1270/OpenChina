package com.itheima.openchina.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.itheima.openchina.R;
import com.itheima.openchina.wedigt.PictureMenu;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * User:user <p/>
 * Date: 2017/10/21 13:13 <p/>
 * Project:GooglePlay <p/>
 * Package:com.adminhj.googleplay.activity <p/>
 * Desc:   浏览多张图片的activity
 * 通过Intent
 * 传入图片地址数组 key :urls （网络地址）
 * 默认显示的图片位置 key :position
 * <p>
 * <p/>
 */

public class PagerPhotoActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.iv_more)
    ImageView mIvMore;

    private Animation enter;
    private Animation exit;
    private ArrayList<String> urls;
    private int position;
    private Adapter adapter;
    private PictureMenu mPictureMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagerphoto);
        ButterKnife.bind(this);

        enter = AnimationUtils.loadAnimation(this, R.anim.photo_enter);
        exit = AnimationUtils.loadAnimation(this, R.anim.photo_exit);

        setViewpager();
        viewpager.startAnimation(enter);
        mPictureMenu = new PictureMenu(this);
    }

    private void setViewpager() {
        Intent intent = getIntent();
        urls = intent.getStringArrayListExtra("urls");
        position = intent.getIntExtra("position", 0);
        adapter = new Adapter();
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(position);

        tvCount.setText((position + 1) + "/" + urls.size());

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvCount.setText((position + 1) + "/" + urls.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.iv_more)
    public void onViewClicked() {
        mPictureMenu.show();
    }

    class Adapter extends PagerAdapter {

        @Override
        public int getCount() {
            return urls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(PagerPhotoActivity.this);
            photoView.enable();
            photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exitActivity();
                }
            });
            photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Glide.with(PagerPhotoActivity.this)
                    .load(urls.get(position))
                    .crossFade(300)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_default_image_error)
                    .into(photoView);

            container.addView(photoView);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private void exitActivity() {
        viewpager.startAnimation(exit);
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
}
