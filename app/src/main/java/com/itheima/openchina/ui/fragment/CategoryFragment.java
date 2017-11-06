package com.itheima.openchina.ui.fragment;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.CategoryBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.utils.Utils;
import com.itheima.openchina.utils.XmlUtils;

/**
 * Created by jiang on 2017/11/4.
 */

public class CategoryFragment extends BaseFragment {


    private static final String TAG ="CategoryFragment" ;
    private CategoryBean categoryBean;
    private int size;
    private TextView viewcontent;
    private ListView listView;
    private Myadapter myadapter;
    private View view;
    private int MODE;
    @Override
    protected void dataOnRefresh() {

    }
    @Override
    protected View onCreateContentView() {
        setRefreshEnable(false);
        view = View.inflate(getContext(), R.layout.category_fragment, null);
        listView = view.findViewById(R.id.category_list);
        myadapter = new Myadapter();
        listView.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();//明天做
        if(MODE==1){
            //设置listView条目的点击事件
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(final AdapterView<?> parent, final View view, final int position, long id) {
                    new Thread(){
                        @Override
                        public void run() {
                            int tag = categoryBean.typeBean.list.get(position).tag;
                            //点击条目之后重新请求数据
                            String stringData = LoadData.getInstance().getStringData("http://www.oschina.net/action/api/softwarecatalog_list?tag="+tag);
                            //重置bean中的集合
                            categoryBean = XmlUtils.toBean(CategoryBean.class, stringData.getBytes());
                            //设置当前是第几层目录
                            //更新适配器
                            Utils.runOnUIThread(new Runnable() {
                                @Override
                                public void run() {
                                    myadapter.notifyDataSetChanged();
                                    MODE=2;
                                    Log.d(TAG, "run: "+3333);
                                }
                            });
                        }
                    }.start();
                }
            });

        }
        if(MODE==2){

        }
        return view;

    }
    @Override
    protected void onStartLoadData() {
        //1. 去网络获取数据
        new Thread(){
            @Override
            public void run() {
                String stringData = LoadData.getInstance().getStringData("http://www.oschina.net/action/api/softwarecatalog_list?tag=0");
                //解析从网络中获取的Xml文件
                categoryBean = XmlUtils.toBean(CategoryBean.class, stringData.getBytes());
                //设置是第几层目录
                MODE=1;
                //获取文件中条目的类型
                size = categoryBean.typeBean.list.size();
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
                //给个条目的布局
                view= View.inflate(getContext(), R.layout.category_item, null);
            }else{
                view=convertView;
            }
            viewcontent = view.findViewById(R.id.tv_content);
            //设置数据给文本控件
            viewcontent.setText(categoryBean.typeBean.list.get(position).name);
            return view;
        }
    }
}
