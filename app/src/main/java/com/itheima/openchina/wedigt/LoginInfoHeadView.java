package com.itheima.openchina.wedigt;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.openchina.R;
import com.itheima.openchina.appcontrol.Constant;
import com.itheima.openchina.beans.UserInfo;
import com.itheima.openchina.ui.activity.ProfileActivity;
import com.itheima.openchina.utils.SpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/6 <p/>
 * Time: 9:16 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.wedigt <p/>
 * Desc:用户登录后我的界面显示的头部信息
 */

public class LoginInfoHeadView extends LinearLayout {
    /**
     * 头像
     */
    @BindView(R.id.login_head)
    CircleImageView mLoginHead;
    /**
     * 二维码
     */
    @BindView(R.id.iv_qrcode)
    ImageView mIvQrcode;
    /**
     * 积分
     */
    @BindView(R.id.tv_integral)
    TextView mTvIntegral;
    /**
     * 收藏
     */
    @BindView(R.id.tv_collect)
    TextView mTvCollect;
    /**
     * 关注
     */
    @BindView(R.id.tv_attention)
    TextView mTvAttention;
    /**
     * 粉丝
     */
    @BindView(R.id.tv_fans)
    TextView mTvFans;
    /**
     * 显示登录后用户的名称
     */
    @BindView(R.id.tv_name)
    TextView mTvName;
    private UserInfo mInfo;

    /**
     * 设置头像点击事件
     */
    private OnClickHeadListener mOnClickHeadListener;
    private MyQrodeDialog mMyQrodeDialog;

    public LoginInfoHeadView(Context context) {
        this(context, null);
    }

    public LoginInfoHeadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoginInfoHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.login_info_head, this);
        ButterKnife.bind(this);
    }

    /**
     * 绑定数据
     *
     * @param userInfo
     */
    public void onBindView(UserInfo userInfo) {
        mInfo = userInfo;
        String portrait = userInfo.getPortrait();
        SpUtil.saveString(Constant.USER_HEAD_URL, portrait);//保存用户头像
//        int lastIndexOf = portrait.lastIndexOf("?");
//        String headUrl = portrait.substring(0, lastIndexOf);
//        LogUtils.i("----LLL" + headUrl);
        Glide.with(getContext())
                .load(portrait)
                .error(R.mipmap.widget_dface)
                .into(mLoginHead);

        mTvName.setText(userInfo.getName());
        mTvIntegral.setText(userInfo.getScore());
        mTvCollect.setText(userInfo.getFavoritecount());
        mTvAttention.setText(userInfo.getAtmeCount());
        mTvFans.setText(userInfo.getFans());
    }

    @OnClick({R.id.login_head, R.id.iv_qrcode, R.id.tv_integral, R.id.tv_collect, R.id
            .tv_attention, R.id.tv_fans})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_head:
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                intent.putExtra("userinfo", mInfo);
                if (mOnClickHeadListener != null) {
                    mOnClickHeadListener.onClickHead(intent);
                }
                break;
            case R.id.iv_qrcode:
                mMyQrodeDialog = new MyQrodeDialog(getContext());
                mMyQrodeDialog.show();
                break;
            case R.id.tv_integral:
                break;
            case R.id.tv_collect:
                break;
            case R.id.tv_attention:
                break;
            case R.id.tv_fans:
                break;
        }
    }

    public void setOnClickHeadListener(OnClickHeadListener onClickHeadListener) {
        mOnClickHeadListener = onClickHeadListener;
    }

    public interface OnClickHeadListener {
        void onClickHead(Intent intent);
    }
}
