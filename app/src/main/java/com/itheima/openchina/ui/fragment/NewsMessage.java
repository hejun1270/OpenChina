package com.itheima.openchina.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
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

public class NewsMessage extends BaseFragment {

    private RecommondBean newsMessageBean;
    private int size;

    @Override
    protected void dataOnRefresh() {
        onStartLoadData();
    }

    @Override
    protected View onCreateContentView() {
        //因为两个页面的listView中的条目布局相同，可以通用
        View view = View.inflate(getContext(), R.layout.recommond_fragment, null);
        ListView listView = view.findViewById(R.id.recommond_listview);
        Myadapter myadapter = new Myadapter();
        listView.setAdapter(myadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = newsMessageBean.typeBean.list.get(position).url;
                Intent intent = new Intent(getContext(), RecommDetailActivity.class);
                intent.putExtra("detailUrl",url);
                startActivity(intent);
            }
        });
        return view;
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
            ViewHolder viewHolder=null;
            if(convertView==null){
                //把推荐页面中listView中的子条目布局加入进来
                convertView= View.inflate(getContext(), R.layout.recommond_item, null);
                //绑定控件
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //给控件设置数据
            viewHolder.viewcontent.setText(newsMessageBean.typeBean.list.get(position).name);
            viewHolder.viewdesc.setText(newsMessageBean.typeBean.list.get(position).description);
            return convertView;
        }
    }
    //设置ViewHolder
    public class ViewHolder{
        private TextView viewcontent;
        private TextView viewdesc;
        public ViewHolder(View view){
            viewcontent = view.findViewById(R.id.tv_content);
            viewdesc = view.findViewById(R.id.tv_description);
        }
    }

    @Override
    protected void onStartLoadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://www.oschina.net/action/api/software_list?pageIndex=0&searchTag=time&pageSize=20";
                String stringData = LoadData.getInstance().getStringData(url);
                newsMessageBean = XmlUtils.toBean(RecommondBean.class, stringData.getBytes());
                size = newsMessageBean.typeBean.list.size();
                Utils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        loadSuccess();
                        onFInishRefresh();
                    }
                });
            }
        }).start();
    }
}
