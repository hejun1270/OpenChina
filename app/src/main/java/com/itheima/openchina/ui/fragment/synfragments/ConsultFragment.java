package com.itheima.openchina.ui.fragment.synfragments;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.SynthesizeAdapter;
import com.itheima.openchina.bases.BaseFragment;
import com.itheima.openchina.beans.FragmentInfo;
import com.itheima.openchina.cacheadmin.HttpManager;
import com.itheima.openchina.utils.LogUtils;
import com.itheima.openchina.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 14:05---
 * Function:
 */

public class ConsultFragment extends BaseFragment {


    @Override
    protected View onCreateContentView() {
        View view=View.inflate(getContext(),R.layout.recycleview_view,null);
        RecyclerView recyclerView= (RecyclerView) view;
        List<String>list=new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add("");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new SynthesizeAdapter<>(getContext(),list));
        return view;
    }


    @Override
    protected void dataOnRefresh() {
        
    }

    @Override
    protected void onStartLoadData() {

    }
}
