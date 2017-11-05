package com.itheima.openchina.bases;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.itheima.openchina.R;
import com.trycatch.mysnackbar.TSnackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * User:user <p/>
 * Date: 2017/10/17 20:37 <p/>
 * Project:GooglePlay <p/>
 * Package:com.adminhj.googleplay.base <p/>
 * Desc:    <p/>
 */

public abstract class BaseFragment extends Fragment {

    @BindView(R.id.btn_try)
    Button btnTry;
    @BindView(R.id.ll_load_failed)
    LinearLayout llLoadFailed;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout refreshLayout;
    private Unbinder unbinder;

    /**
     * 是否是刷新
     */
    protected boolean isRefresh;

    protected TSnackbar snackbar;
    private ViewGroup rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build
                .VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager
                    .LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initSnackBar();
    }

    private void initSnackBar() {
        //注意getRootView()最为重要，直接关系到TSnackBar的位置
        rootView = (ViewGroup) getActivity().findViewById(android.R.id.content)
                .getRootView();
        ((BaseActivity) getActivity()).setListener(new BaseActivity.NetWorkStateListener() {
            @Override
            public void netStateChange(String msg, boolean isConnect) {
                ((BaseActivity) getActivity()).setNetErrorMsg(msg, isConnect);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_base, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    /**
     * 设置下拉刷新是否可用
     * @param isEnable
     */
    protected void setRefreshEnable(boolean isEnable) {
        refreshLayout.setEnabled(isEnable);
    }

    /**
     * 数据刷新
     */
    protected abstract void dataOnRefresh();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshLayout.setColorSchemeResources(R.color.swiperefresh_color1, R.color
                .swiperefresh_color2, R.color.swiperefresh_color3, R.color.swiperefresh_color4);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                dataOnRefresh();
            }
        });
        onStartLoadData();
    }

    protected void loadSuccess() {
        onFInishRefresh();
        if(llLoadFailed!=null&&progressBar!=null&&refreshLayout!=null){
            llLoadFailed.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            refreshLayout.addView(onCreateContentView());
            refreshLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置子页面的内容
     *
     * @return
     */
    protected abstract View onCreateContentView();

    protected void onFInishRefresh() {
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(false);
        }
    }

    protected void loadFailed() {
        onFInishRefresh();
        progressBar.setVisibility(View.GONE);
        llLoadFailed.setVisibility(View.VISIBLE);
        refreshLayout.setVisibility(View.GONE);
    }

    protected void refreshFailed() {
        onFInishRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        isRefresh = false;
    }

    /**
     * 开始加载数据
     */
    protected abstract void onStartLoadData();

    @OnClick(R.id.btn_try)
    public void onViewClicked() {
        progressBar.setVisibility(View.VISIBLE);
        llLoadFailed.setVisibility(View.GONE);
        refreshLayout.setVisibility(View.GONE);
        onStartLoadData();
    }

}
