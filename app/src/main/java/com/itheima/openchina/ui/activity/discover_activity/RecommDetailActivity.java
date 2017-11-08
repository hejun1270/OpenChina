package com.itheima.openchina.ui.activity.discover_activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseActivity;
import com.jaeger.library.StatusBarUtil;

public class RecommDetailActivity extends BaseActivity {

    private WebView webView;
    private Toolbar tb;

    @Override
    protected int getLayoutRs() {
        return R.layout.activity_recomm_detail;
    }
    @Override
    protected void init() {
        super.init();
        StatusBarUtil.setColor(this,getResources().getColor(R.color.colorPrimary));
        tb = findViewById(R.id.tool_bar);
        webView = findViewById(R.id.wv);
        initActionBar();
        Intent intent = getIntent();
        String url = intent.getStringExtra("detailUrl");
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());
    }

    private void initActionBar() {
        setSupportActionBar(tb);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle("软件详情");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
