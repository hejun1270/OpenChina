package com.itheima.openchina.wedigt;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.itheima.openchina.R;
import com.itheima.openchina.appcontrol.Constant;
import com.itheima.openchina.ui.activity.PhotoActivity;
import com.itheima.openchina.utils.SpUtil;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/6 <p/>
 * Time: 13:15 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.wedigt <p/>
 * Desc: 点击头像时弹出的底部菜单
 */

public class HeadBottomMenu implements View.OnClickListener {
    private Context mContext;
    private Dialog mMdialog;

    /**
     * 打开照相机
     */
    private Button photoGraph;
    /**
     * 查看大头像
     */
    private Button lookPhoto;
    /**
     * 相册
     */
    private Button photoPicture;
    /**
     * 取消
     */
    private Button cancle;

    private ItemClickListener mItemClickListener;

    public HeadBottomMenu(Context context) {
        mContext = context;
        init();
    }

    private void init() {
        mMdialog = new Dialog(mContext, R.style.popoDialog);
        mMdialog.setContentView(View.inflate(mContext, R.layout.layout_bottom_menu, null));
        // 弹出对话框
        Window window = mMdialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        window.setContentView(R.layout.layout_bottom_menu);
        photoGraph = window.findViewById(R.id.btn_photograph);
        lookPhoto = window.findViewById(R.id.btn_look);
        photoPicture = window.findViewById(R.id.btn_photopictur);
        cancle = window.findViewById(R.id.btn_cancel);

        photoGraph.setOnClickListener(this);
        lookPhoto.setOnClickListener(this);
        photoPicture.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                mMdialog.dismiss();
                break;
            case R.id.btn_photograph:
                if (mItemClickListener != null) {
                    mItemClickListener.photograph();
                }
                disniss();
                break;
            case R.id.btn_photopictur:
                if (mItemClickListener != null) {
                    mItemClickListener.photopictur();
                }
                disniss();
                break;
            case R.id.btn_look:
                disniss();
                setLookPhoto();
                break;
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        /**
         * 拍照
         */
        void photograph();

        /**
         * 相册
         */
        void photopictur();
    }

    /**
     * 浏览图片
     */
    private void setLookPhoto() {
        String headUrl = SpUtil.getString(Constant.USER_HEAD_URL, "");
        Intent intent = new Intent(mContext, PhotoActivity.class);
        intent.putExtra("url", headUrl);
        mContext.startActivity(intent);
    }

    /**
     * 显示对话框
     */
    public void show() {
        mMdialog.show();
    }

    /**
     * 关闭对话框
     */
    public void disniss() {
        mMdialog.dismiss();
    }
}
