package com.itheima.openchina.ui.activity.discover_activity;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.openchina.R;
import com.itheima.openchina.beans.FindPeopleBean;
import com.itheima.openchina.utils.XmlUtils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AskActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private SearchView searchView;


    private FindManListAdapter adapter;
    private LinearLayout findmanlayoutloading;
    private LinearLayout findmanlayoutloderror;
    private ListView listView;
    private TextView textView;
    private ImageView imageView;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private FindPeopleBean  beanlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        toolbar = findViewById(R.id.findman_toolbar);
        progressBar = findViewById(R.id.pb_loadingdata_findman);
        imageView = findViewById(R.id.iv_loaddata_erro_findman);
        textView = findViewById(R.id.tv_loaddata_erro_findman);
        listView = findViewById(R.id.listview_findman);
        findmanlayoutloading = findViewById(R.id.findman_layout_loading);
        findmanlayoutloderror = findViewById(R.id.findman_layout_loaderror);


        //设置替换toolbar
        setSupportActionBar(toolbar);
        //获取已替换的toolbar 去进行设置
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("开源中国");
        adapter = new FindManListAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }


    /**
     * 重写父类方法  对控件进行监听
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }


        return super.onOptionsItemSelected(item);//需要保留
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //获取菜单填充者
        getMenuInflater().inflate(R.menu.find_people, menu);

        //进行输入搜索框的设置
        MenuItem item = menu.findItem(R.id.abc_find);

        //设置searView v7的方式
        View view = MenuItemCompat.getActionView(item);
        if (view != null) {
            searchView = (SearchView) view;
            searchView.setQueryHint("请输入用户昵称");
            searchView.setOnQueryTextListener(this);//设置查询回掉接口

        }


        // return super.onCreateOptionsMenu(menu); 抛出了一个异常 要返回true
        return true;
    }


    //------------SearchView监听重写的三个方法
    @Override
    public boolean onQueryTextSubmit(String query) {//查询的回调
        return true;//设置为true 响应本事件
    }

    //http://www.oschina.net/action/api/find_user?name=123
    @Override
    public boolean onQueryTextChange(final String newText) {//输入过程中 文本改变的回掉方法
        //System.out.println("change" + newText+  TextUtils.isEmpty(newText));
        //为空的话   不要请求
        if (!TextUtils.isEmpty(newText)) {
            //1.只要一有文本变动   就改变三个ui的状态
            findmanlayoutloading.setVisibility(View.VISIBLE);//先让加载条显示  加载成功后  显示加载界面
            listView.setVisibility(View.GONE);//查询之前 先重置  lv以及所有lv相关的数据



            //请求网络
            new Thread(new Runnable() {


                @Override
                public void run() {
                    OkHttpClient okHttpClient = new OkHttpClient();//创建okhttp监控端  客户端
                    Request request = new Request.Builder()
                            .url("http://www.oschina.net/action/api/find_user?name=" + newText)
                            .build();

                    Response response = null;
                    try {
                        response = okHttpClient.newCall(request).execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (response.isSuccessful()) {
                        System.out.println("请求成功");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listView.setVisibility(View.VISIBLE);
                                findmanlayoutloading.setVisibility(View.GONE);
                            }
                        });


                        //进行xml解析

                        try {
                            beanlist = XmlUtils.toBean(FindPeopleBean.class, response.body().bytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //System.out.println(beanlist.users.get(0).userBeen.get(0).name);
                        //已获取数据 填充数据即可


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });


                    }
                }
            }).start();


        }else{
            //为空的话  给用户一个提示   让他输入
            listView.setVisibility(View.GONE);
        }


        return true;//设置为true 响应本事件
    }





    class FindManListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (beanlist == null) {
                return 0;
            }
            return beanlist.users.list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            VViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new VViewHolder();
                convertView = View.inflate(getApplicationContext(), R.layout.ask_item, null);
                viewHolder.FindmanlistviewitemTvName = (TextView) convertView.findViewById(R.id.findmanlistviewitem_tv_name);
                viewHolder.FindmanlistviewitemTvAddress = (TextView) convertView.findViewById(R.id.findmanlistviewitem_tv_address);
                viewHolder.FindmanlistviewitemIvAvatar = (ImageView) convertView.findViewById(R.id.findmanlistviewitem_iv_avatar);

                convertView.setTag(viewHolder);
            }
            viewHolder = (VViewHolder) convertView.getTag();

            viewHolder.FindmanlistviewitemTvName.setText(beanlist.users.list.get(position).name);
            viewHolder.FindmanlistviewitemTvAddress.setText(beanlist.users.list.get(position).from);
            Glide.with(getApplicationContext()).load(beanlist.users.list.get(position).portrait).into(viewHolder.FindmanlistviewitemIvAvatar);
            return convertView;
        }

        class VViewHolder {
            TextView FindmanlistviewitemTvName;
            ImageView FindmanlistviewitemIvAvatar;
            TextView FindmanlistviewitemTvAddress;
        }
    }

}
