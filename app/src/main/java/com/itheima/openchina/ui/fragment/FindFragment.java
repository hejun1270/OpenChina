package com.itheima.openchina.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.activity.CaptureActivity;
import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.bases.BasePagerFragment;
import com.itheima.openchina.ui.activity.OpenChinaActivity;
import com.itheima.openchina.utils.ToastUtil;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/3 <p/>
 * Time: 10:38 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.ui.fragment <p/>
 * Desc:
 */

public class FindFragment extends BasePagerFragment implements View.OnClickListener{

    private View view;
    private View open;
    private View find;
    private View scan;
    private View shake;

    @Override
    protected View onCreateContentView() {
        setRefreshEnable(false);//设置禁止下拉刷新
        view = View.inflate(getContext(), R.layout.find_page, null);
        open = view.findViewById(R.id.item_open);
        find = view.findViewById(R.id.item_find);
        scan = view.findViewById(R.id.item_scan);
        shake = view.findViewById(R.id.item_shake);
        open.setOnClickListener(this);
        find.setOnClickListener(this);
        scan.setOnClickListener(this);
        shake.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_open:
                Intent intent = new Intent(getContext(), OpenChinaActivity.class);
                startActivity(intent);
                break;
            case R.id.item_find:
                break;
            case R.id.item_scan:
                Intent intent1 = new Intent(getContext(), CaptureActivity.class);
//                startActivityForResult(intent1,1);//隐式意图处理扫描结果
                startActivity(intent1);
                break;
            case R.id.item_shake:
                break;
        }
    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==1){
//            String result = data.getStringExtra("qrcode_result");
//            ToastUtil.showToast(result);
//            if(result.contains("http://")){
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                intent.addCategory("android.intent.category.DEFAULT");
//                intent.addCategory("android.intent.category.BROWSABLE");
//                intent.setData(Uri.parse("http:" + result));
//                startActivity(intent);
//            }
//        }
//    }
}
