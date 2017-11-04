package com.itheima.openchina.ui.fragment.synfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.openchina.R;
import com.itheima.openchina.adapters.SynthesizeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/4---
 * Time:  --- 14:05---
 * Function:
 */

public class ConsultFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.recycleview_view,container,false);
        RecyclerView recyclerView= (RecyclerView) view;
        List<Object>list=new ArrayList<>();
//        for (int i = 0; i < 1; i++) {
//            list.add("");
//        }
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(new SynthesizeAdapter(container.getContext(),list));
        return view;
    }
}
