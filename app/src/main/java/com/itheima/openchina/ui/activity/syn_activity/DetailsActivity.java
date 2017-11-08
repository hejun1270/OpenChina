package com.itheima.openchina.ui.activity.syn_activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.openchina.R;
import com.itheima.openchina.appcontrol.Constant;
import com.itheima.openchina.bases.BaseActivity;
import com.itheima.openchina.ui.activity.LoginActivity;
import com.itheima.openchina.utils.LogUtils;
import com.itheima.openchina.utils.SpUtil;
import com.itheima.openchina.utils.ToastUtil;
import com.itheima.openchina.wedigt.KeyboardChangeListener;
import com.jaeger.library.StatusBarUtil;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.itheima.openchina.appcontrol.Constant.COOKIE;

/**
 * Created by 佘本民
 * When:  --- 2017/11/8---
 * Time:  --- 0:39---
 * Function:
 */

public class DetailsActivity extends BaseActivity implements View.OnClickListener {

    private String href;
    private TextView commendText;
    private WebView webView;
    private ScrollView synScrollView;
    private EditText edit;
    String uid;
    private LinearLayout synLinear;
    private int height;
    private int y;
    private String id;
    private String cookie;
    private AlertDialog.Builder alertDialog;
    private String commend;

    @Override
    protected int getLayoutRs() {
        return R.layout.activity_syn_consult_detail;
    }

    @SuppressLint("NewApi")
    @Override
    protected void init() {
        super.init();
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent));
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        commend = intent.getStringExtra("commend");
        id = intent.getStringExtra("id");
        href = intent.getStringExtra("href");
        //头布局
        ImageView imageBack = findViewById(R.id.image_back);
        ImageView imageBg = findViewById(R.id.imageBg);
        TextView titleText = findViewById(R.id.textTitle);
        commendText = findViewById(R.id.textCommend);
        titleText.setText(title);
        commendText.setText(commend);
        imageBack.setOnClickListener(this);
        commendText.setOnClickListener(this);
        //Scroll里面的网页
        webView = findViewById(R.id.synWebView);
        webViewSetting();

        LogUtils.i(id);
        synScrollView = findViewById(R.id.synScrollView);
        edit = findViewById(R.id.synEditText);
        edit.setImeOptions(EditorInfo.IME_ACTION_SEND);


        synLinear = findViewById(R.id.edit_linear);
        synLinear.setVisibility(View.GONE);
        //底部布局
        TextView send = findViewById(R.id.send);
        TextView synTitleShare = findViewById(R.id.synTitleShare);


        //头部share展示
        if (title.contains("活动")) {
            synTitleShare.setVisibility(View.VISIBLE);
            imageBg.setVisibility(View.GONE);
            commendText.setVisibility(View.GONE);
        }

        //隐藏键盘
        inputHit();


        //软键盘发送的操作
        send.setOnClickListener(this);


    }

    @SuppressLint("ClickableViewAccessibility")
    private void inputHit() {

        //上下滑动时控制底部条目的显隐
        //可用重写synScrollView类,暴露其中的方法,一下写会影响性能
        synScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        y = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int moveY = (int) event.getY();
                        if (y - moveY >= height/5) {
                            synLinear.setVisibility(View.VISIBLE);
                        } else if (moveY - y >= height/5) {
                            synLinear.setVisibility(View.GONE);
                        }
                        break;
                }
                return false;
            }
        });


    }


    //网页浏览器设置
    @SuppressLint("SetJavaScriptEnabled")
    private void webViewSetting() {
        WebSettings settings = webView.getSettings();
        webView.loadUrl(href);
        settings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        WebViewClient web = new WebViewClient();
        webView.setWebViewClient(web);
//        web.onPageFinished(webView,href);

    }


    //点击事件设置
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.textCommend:
                height = synScrollView.getHeight();
                synScrollView.smoothScrollBy(0, height * 100);
                synLinear.setVisibility(View.VISIBLE);
                break;
            case R.id.send:
                sendMessage();
                break;
        }
    }

    private void sendMessage() {
        String msg = edit.getText().toString().trim();
        if(TextUtils.isEmpty(msg)){
            Toast.makeText(getApplicationContext(), "输入内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        isLogin();

    }
    /**
     * 判断是否登录
     */
    private void isLogin() {
        uid = SpUtil.getString(Constant.UID, "");
        cookie = SpUtil.getString(COOKIE, "");
        if (!TextUtils.equals("", uid) && !TextUtils.equals("", cookie)) {
            //加载数据
            LogUtils.i("用户已经登录.....");
            LogUtils.i("id:"+id+"href:"+href);
            sendMsgRight();
        } else {//提示登录
            LogUtils.i("用户未登录.....");
            showLoginDialog();
        }
    }


    //发送数据
    private void sendMsgRight() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okhttp=new OkHttpClient.Builder()
                        .build();
                RequestBody body=new FormBody.Builder()
                        .add("sourceId",id)
                        .add("type","6")
                        .add("content",edit.getText().toString().trim()+"")
                        .build();

                Request rq=new Request.Builder()
                        .addHeader("cookie",SpUtil.getString(COOKIE,"")+"")
                        .url("http://www.oschina.net/action/apiv2/comment_pub")
                        .post(body)
                        .build();

                try {
                    Response execute = okhttp.newCall(rq).execute();
                    LogUtils.i(execute.message()+"++++++");
                    LogUtils.i("ben++++");

                } catch (IOException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        edit.setText("");
                        webView.loadUrl(href);
                        int i = Integer.parseInt(commend) + 1;
                        commendText.setText(i+"");
                    }
                });
            }
        }).start();
    }


    //提示用户登录
    private void showLoginDialog() {
        alertDialog = new AlertDialog.Builder(DetailsActivity.this)
                .setTitle("登录提示:")
                .setMessage("亲，登录后才能查看消息哦")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(DetailsActivity.this, LoginActivity.class);
                        startActivityForResult(intent, 1001);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alertDialog.show();
    }
}
