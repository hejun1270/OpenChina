package com.itheima.openchina.ui.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.CategoryBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.utils.XmlUtils;

/**
 * Created by jiang on 2017/11/4.
 */

public class CategoryFragment extends BaseFragment {


    private CategoryBean categoryBean;
    private int size;
    private TextView viewcontent;
    private ListView listView;
    private Myadapter myadapter;

    @Override
    protected void dataOnRefresh() {
    }



    @Override
    protected View onCreateContentView() {
        View view = View.inflate(getContext(), R.layout.category_fragment, null);
        listView = view.findViewById(R.id.category_list);
        myadapter = new Myadapter();
        listView.setAdapter(myadapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(final AdapterView<?> parent, final View view, final int position, long id) {
//
//                new Thread(){
//                    @Override
//                    public void run() {
//                        String stringData = LoadData.getInstance().getStringData("http://www.oschina.net/action/api/softwarecatalog_list?tag=1");
//                        categoryBean = XmlUtils.toBean(CategoryBean.class, stringData.getBytes());
//                        ToastUtil.showToast(categoryBean.typeBean.list.get(position).name);
//                        Utils.runOnUIThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                myadapter.notifyDataSetChanged();
//                            }
//                        });
//                    }
//                }.start();
//
//            }
//        });
        return view;
    }



    @Override
    protected void onStartLoadData() {
        //1. 去网络获取数据
        new Thread(){
            @Override
            public void run() {
                String stringData = LoadData.getInstance().getStringData("http://www.oschina.net/action/api/softwarecatalog_list?tag=0");

                categoryBean = XmlUtils.toBean(CategoryBean.class, stringData.getBytes());
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
