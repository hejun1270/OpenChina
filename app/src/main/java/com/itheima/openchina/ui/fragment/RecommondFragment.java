package com.itheima.openchina.ui.fragment;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.RecommondBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.utils.XmlUtils;

/**
 * Created by jiang on 2017/11/4.
 */

public class RecommondFragment extends BaseFragment{

    private TextView viewcontent;
    private TextView viewdesc;
    private RecommondBean recommondBean;
    private int size;

    @Override
    protected void dataOnRefresh() {

    }

    @Override
    //加载推荐页面的布局
    protected View onCreateContentView() {
        View view = View.inflate(getContext(), R.layout.recommond_fragment, null);
        ListView listView = view.findViewById(R.id.recommond_listview);
        //关联适配器
        listView.setAdapter(new Myadapter());
        return view;
    }

    @Override
    protected void onStartLoadData() {
        new Thread(){
            @Override
            public void run() {
                //从网络获取推荐页面的数据
                String stringData = LoadData.getInstance().getStringData("http://www.oschina.net/action/api/software_list?pageIndex=1&searchTag=recommend&pageSize=20");
                recommondBean = XmlUtils.toBean(RecommondBean.class, stringData.getBytes());
                //获取条目的数量的大小
                size = recommondBean.typeBean.list.size();
            }
        }.start();
//        new Thread(){
//            @Override
//            public void run() {
//                OkHttpClient okHttpClient=new OkHttpClient.Builder().build();
//                Request builder = new Request.Builder()
//                        .url("http://www.oschina.net/action/api/software_list?pageIndex=1&searchTag=recommend&pageSize=20")
//                        .build();
//                try {
//                    Response response = okHttpClient.newCall(builder).execute();
//                    recommondBean = XmlUtils.toBean(RecommondBean.class, response.body().bytes());
//                    size = recommondBean.typeBean.list.size();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
        loadSuccess();

    }
    public class Myadapter extends BaseAdapter {

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
                //把推荐页面中listView中的子条目布局加入进来
                view= View.inflate(getContext(), R.layout.recommond_item, null);
                //绑定控件
                viewcontent = view.findViewById(R.id.tv_content);
                viewdesc = view.findViewById(R.id.tv_description);

            }else{
                view=convertView;
            }
            //给控件设置数据
            viewcontent.setText(recommondBean.typeBean.list.get(position).name);

            viewdesc.setText(recommondBean.typeBean.list.get(position).description);
            return view;
        }
    }
}
