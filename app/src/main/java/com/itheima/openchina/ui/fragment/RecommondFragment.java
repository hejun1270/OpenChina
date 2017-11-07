package com.itheima.openchina.ui.fragment;


import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.RecommondBean;
import com.itheima.openchina.cacheadmin.LoadData;
import com.itheima.openchina.ui.activity.RecommDetailActivity;
import com.itheima.openchina.utils.Utils;
import com.itheima.openchina.utils.XmlUtils;

/**
 * Created by jiang on 2017/11/4.
 */

public class RecommondFragment extends BaseFragment {


    private RecommondBean recommondBean;
    private int size;
    private Myadapter myadapter;
    private ListView listView;

    @Override
    protected void dataOnRefresh() {
        onStartLoadData();
    }

    @Override
    //加载推荐页面的布局
    protected View onCreateContentView() {
        View view = View.inflate(getContext(), R.layout.recommond_fragment, null);
        listView = view.findViewById(R.id.recommond_listview);
        //关联适配器
        myadapter = new Myadapter();
        listView.setAdapter(myadapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //最后一个可见的条目
                int lastVisiblePosition = view.getLastVisiblePosition();
                if (lastVisiblePosition == size - 1 && scrollState == SCROLL_STATE_IDLE) {
                    //上拉加载更多
                    new Thread() {
                        @Override
                        public void run() {
                            //从网络获取推荐页面的数据
                            size = size + 10;
                            String stringData = LoadData.getInstance().getStringData("http://www.oschina.net/action/api/software_list?pageIndex=1&searchTag=recommend&pageSize=" + size);
                            recommondBean = XmlUtils.toBean(RecommondBean.class, stringData.getBytes());
                            //获取条目的数量的大小
                            size = recommondBean.typeBean.list.size();
                            Utils.runOnUIThread(new Runnable() {
                                @Override
                                public void run() {
                                    myadapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }.start();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = recommondBean.typeBean.list.get(position).url;
                Intent intent = new Intent(getContext(), RecommDetailActivity.class);
                intent.putExtra("detailUrl", url);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    protected void onStartLoadData() {
        new Thread() {
            @Override
            public void run() {
                //从网络获取推荐页面的数据
                String stringData = LoadData.getInstance().getStringData("http://www.oschina.net/action/api/software_list?pageIndex=1&searchTag=recommend&pageSize=20");
                recommondBean = XmlUtils.toBean(RecommondBean.class, stringData.getBytes());
                //获取条目的数量的大小
                size = recommondBean.typeBean.list.size();
                //在主线程中更新UI
                Utils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        loadSuccess();
//                        onFInishRefresh();
                    }
                });
            }
        }.start();
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
            ViewHolder viewHolder = null;
            if (convertView == null) {
                //把推荐页面中listView中的子条目布局加入进来
                convertView = View.inflate(getContext(), R.layout.recommond_item, null);
                //绑定控件
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //给控件设置数据
            viewHolder.viewcontent.setText(recommondBean.typeBean.list.get(position).name);
            viewHolder.viewdesc.setText(recommondBean.typeBean.list.get(position).description);
            return convertView;
        }
    }

    //设置ViewHolder
    public class ViewHolder {
        private TextView viewcontent;
        private TextView viewdesc;

        public ViewHolder(View view) {
            viewcontent = view.findViewById(R.id.tv_content);
            viewdesc = view.findViewById(R.id.tv_description);
        }
    }
}
