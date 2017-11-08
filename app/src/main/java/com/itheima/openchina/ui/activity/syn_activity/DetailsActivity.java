package com.itheima.openchina.ui.activity.syn_activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
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

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseActivity;
import com.itheima.openchina.wedigt.KeyboardChangeListener;

/**
 * Created by 佘本民
 * When:  --- 2017/11/8---
 * Time:  --- 0:39---
 * Function:
 */

public class DetailsActivity extends BaseActivity implements View.OnClickListener{

    private String href;
    private TextView commendText;
    private WebView webView;
    private ScrollView synScrollView;
    private EditText edit;
    private LinearLayout synLinear;

    @Override
    protected int getLayoutRs() {
        return R.layout.activity_syn_consult_detail;
    }

    @SuppressLint("NewApi")
    @Override
    protected void init() {
        super.init();
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String commend = intent.getStringExtra("commend");
        href = intent.getStringExtra("href");
        //头布局
        ImageView imageBack = findViewById(R.id.image_back);
        ImageView imageBg = findViewById(R.id.imageBg);
        TextView titleText = findViewById(R.id.textTitle);
        TextView commendText = findViewById(R.id.textCommend);
        LinearLayout total_linear = findViewById(R.id.total_linear);
        titleText.setText(title);
        commendText.setText(commend);
        imageBack.setOnClickListener(this);
        commendText.setOnClickListener(this);


        //Scroll里面的网页
        webView = findViewById(R.id.synWebView);
        webViewSetting();

        synScrollView = findViewById(R.id.synScrollView);
        edit = findViewById(R.id.synEditText);
        synLinear = findViewById(R.id.synLinear);
        //底部布局
        TextView fiveStar = findViewById(R.id.synFiveStar);
        TextView share = findViewById(R.id.synShare);
        TextView synTitleShare = findViewById(R.id.synTitleShare);

        //头部share展示
        if (title.contains("活动")) {
            synTitleShare.setVisibility(View.VISIBLE);
            imageBg.setVisibility(View.GONE);
            commendText.setVisibility(View.GONE);
        }

        //隐藏键盘
        inputHit();


    }

    private void inputHit() {
        KeyboardChangeListener listener=new KeyboardChangeListener(this);
        listener.setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                if(isShow){

                }
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
        WebViewClient web=new WebViewClient();
        webView.setWebViewClient(web);
//        web.onPageFinished(webView,href);

    }


    //点击事件设置
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;
            case R.id.textCommend:
                int height = synScrollView.getHeight();
                synScrollView.smoothScrollBy(0,height*100);
                break;
            case R.id.synFiveStar:
                inputHit();
                break;
        }
    }
}
