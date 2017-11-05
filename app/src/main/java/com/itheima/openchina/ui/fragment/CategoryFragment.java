package com.itheima.openchina.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.bases.BasePagerFragment;
import com.itheima.openchina.beans.CategoryBean;
import com.itheima.openchina.cacheadmin.CacheManger;
import com.itheima.openchina.cacheadmin.HttpManager;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.utils.XmlUtils;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jiang on 2017/11/4.
 */

public class CategoryFragment extends BaseFragment {


    private CategoryBean categoryBean;
    private int size;
    private TextView viewcontent;

    @Override
    protected void dataOnRefresh() {


    }



    @Override
    protected View onCreateContentView() {
        View view = View.inflate(getContext(), R.layout.category_fragment, null);
        ListView listView = view.findViewById(R.id.category_list);
        listView.setAdapter(new Myadapter());
        return view;
    }

    @Override
    protected void onStartLoadData() {
//        new Thread(){
//
//            @Override
//            public void run() {
//                String stringData = LoadData.getInstance().getStringData("http://www.oschina.net/action/api/softwarecatalog_list?tag=0");
//                CategoryBean categoryBean = XmlUtils.toBean(CategoryBean.class, stringData.getBytes());
//                size = categoryBean.typeBean.list.size();
//            }
//        }.start();
        //1. 去网络获取数据

        new Thread(){
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient.Builder().build();
                Request builder = new Request.Builder()
                        .url("http://www.oschina.net/action/api/softwarecatalog_list?tag=0")
                        .build();
                try {
                    Response response = okHttpClient.newCall(builder).execute();
                    categoryBean = XmlUtils.toBean(CategoryBean.class,response.body().bytes());
                    size = categoryBean.typeBean.list.size();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        loadSuccess();
    }
    public class Myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return size;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if(convertView==null){
                view= View.inflate(getContext(), R.layout.category_item, null);
                viewcontent = view.findViewById(R.id.tv_content);

            }else{
                view=convertView;
            }
            viewcontent.setText(categoryBean.typeBean.list.get(position).name);
            return view;
        }
    }


}
