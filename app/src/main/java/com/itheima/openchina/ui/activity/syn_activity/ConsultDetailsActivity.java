package com.itheima.openchina.ui.activity.syn_activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseActivity;

/**
 * Created by 佘本民
 * When:  --- 2017/11/8---
 * Time:  --- 0:39---
 * Function:
 */

public class ConsultDetailsActivity extends BaseActivity implements View.OnClickListener{

    private String href;
    private TextView commendText;

    @Override
    protected int getLayoutRs() {
        return R.layout.activity_syn_detail;
    }

    @Override
    protected void init() {
        super.init();
        Intent intent = getIntent();
        String title=intent.getStringExtra("title");
        String commend=intent.getStringExtra("commend");
        href = intent.getStringExtra("href");
        //头布局
        ImageView imageBack = findViewById(R.id.image_back);
        TextView titleText = findViewById(R.id.textTitle);
        TextView commendText = findViewById(R.id.textCommend);
        titleText.setText(title);
        commendText.setText(commend);
        imageBack.setOnClickListener(this);
        commendText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;
            case R.id.textCommend:
                break;
        }
    }
}
