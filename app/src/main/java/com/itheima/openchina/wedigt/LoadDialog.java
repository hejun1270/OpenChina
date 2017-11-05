package com.itheima.openchina.wedigt;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.itheima.openchina.R;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/5 <p/>
 * Time: 19:20 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.wedigt <p/>
 * Desc:自定义加载对话框
 */

public class LoadDialog {
    private Context mContext;
    private TextView mTextView;
    private Dialog mDialog;

    public LoadDialog(Context context) {
        mContext = context;
        initDialog();
    }

    /**
     * 初始化 LoadDialog
     */
    private void initDialog() {
        mDialog = new Dialog(mContext, R.style.Theme_Dialog_NoTitle_Translucent);
        //加载自定义布局
        View view = View.inflate(mContext, R.layout.loading_dialog, null);
        mDialog.setContentView(view);
        mTextView = view.findViewById(R.id.tv_load_msg);
    }

    /**
     * 设置点击屏幕是否退出对话框
     *
     * @param isOutSide
     */
    public void setCanceledOnTouchOutside(boolean isOutSide) {
        mDialog.setCanceledOnTouchOutside(isOutSide);
    }

    /**
     * 设置按返回键是否退出对话框
     *
     * @param cancelable
     */
    public void setCancelable(boolean cancelable) {
        mDialog.setCancelable(cancelable);
    }

    /**
     * 设置提示信息
     *
     * @param msg
     */
    public void setMsg(String msg) {
        mTextView.setText(msg);
    }

    /**
     * 显示对话框
     */
    public void show() {
        mDialog.show();
    }

    /**
     * 关闭对话框
     */
    public void dismiss() {
        mDialog.dismiss();
    }
}
