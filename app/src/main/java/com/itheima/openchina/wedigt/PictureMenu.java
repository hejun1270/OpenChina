package com.itheima.openchina.wedigt;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.itheima.openchina.R;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/6 <p/>
 * Time: 16:53 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.wedigt <p/>
 * Desc:浏览图片点击更多时弹出的menu
 */

public class PictureMenu implements View.OnClickListener {
    private Context mContext;
    private Dialog mMdialog;

    /**
     * 保存
     */
    private Button mSave;
    /**
     * 发送到动弹
     */
    private Button sendTwee;
    /**
     * 复制图片link
     */
    private Button copyLink;

    public PictureMenu(Context context) {
        mContext = context;
        init();
    }

    private void init() {
        mMdialog = new Dialog(mContext, R.style.popoDialog);
        mMdialog.setContentView(View.inflate(mContext, R.layout.layout_picture_menu, null));
        // 弹出对话框
        Window window = mMdialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        window.setContentView(R.layout.layout_picture_menu);
        mSave = window.findViewById(R.id.btn_save);
        sendTwee = window.findViewById(R.id.btn_send_twee);
        copyLink = window.findViewById(R.id.copy_link);

        mSave.setOnClickListener(this);
        sendTwee.setOnClickListener(this);
        copyLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                disniss();
                break;
            case R.id.btn_send_twee:
                disniss();
                break;
            case R.id.copy_link:
                disniss();
                break;
        }
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
