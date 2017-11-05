package com.itheima.openchina.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.openchina.R;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.bases.BasePagerFragment;

import org.w3c.dom.Text;

/**
 * Created by jiang on 2017/11/4.
 */

public class CategoryFragment extends BaseFragment {




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
    public class Myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 15;
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
            }else{
                view=convertView;
            }
            return view;
        }
    }
    @Override
    protected void onStartLoadData() {
        loadSuccess();
    }
}
