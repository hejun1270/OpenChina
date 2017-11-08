package com.itheima.openchina.ui.activity.tweet_activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseActivity;
import com.itheima.openchina.beans.TweetInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author:  张慧强
 * Version:  1.0
 * Date:    2017/11/6 0006
 * Modify:
 * Description: //TODO
 * Copyright notice:
 */
public class TweetDetailActivity extends BaseActivity {

    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.tv_pub_time)
    TextView tvPubTime;
    @BindView(R.id.ic_thumbup)
    ImageView icThumbup;
    @BindView(R.id.tv_thumbup_num)
    TextView tvThumbupNum;
    @BindView(R.id.ic_comment)
    ImageView icComment;
    @BindView(R.id.tv_comment_num)
    TextView tvCommentNum;
    @BindView(R.id.btn_nice)
    Button btnNice;
    @BindView(R.id.btn_comment)
    Button btnComment;
    private List<TweetInfoBean.ResultBean.TweetItem> tweetItemList;

    @Override
    protected int getLayoutRs() {
        return R.layout.activity_tweet_detail;
    }

    @Override
    protected void init() {
        super.init();
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String userImage = intent.getStringExtra("userImage");
        String tweetContent = intent.getStringExtra("tweetContent");
        String sendTime = intent.getStringExtra("sendTime");
        tvUserName.setText(userName);
        Glide.with(getApplicationContext()).load(userImage).into(profileImage);
        content.setText(tweetContent);
        tvPubTime.setText(sendTime);



    }


    @OnClick({R.id.ic_thumbup, R.id.ic_comment, R.id.btn_nice, R.id.btn_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_thumbup:
                break;
            case R.id.ic_comment:
                break;
            case R.id.btn_nice:
                break;
            case R.id.btn_comment:
                break;
        }
    }
}
