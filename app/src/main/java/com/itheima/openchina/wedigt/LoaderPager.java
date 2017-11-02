package com.itheima.openchina.wedigt;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


import com.itheima.openchina.R;
import com.itheima.openchina.utils.Utils;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * user:DoctorHe
 * 加载页面
 */
public abstract class LoaderPager extends FrameLayout
{
    
    //@Bind(R.id.page_iv)
    //ImageView mPageIv;
    
    //@Bind(R.id.btn_reload)
    //Button mBtnReload;
    
    private View mLoadingView;
    
    private View mErrorView;
    
    private View mSuccessView;
    
    public LoaderPager(Context context)
    {
        this(context, null);
    }
    
    public LoaderPager(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public LoaderPager(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initLoaderPager();

    }

    private void initLoaderPager()
    {
        // 现在的思想:把三个界面都加载进来,然后根据不同的状态去自动切换页面
        // 创建一个加载中界面
        if (mLoadingView == null)
        {
            mLoadingView = View.inflate(getContext(), R.layout.page_loading, null);
        }
        addView(mLoadingView);

        // 加载错误界面
        if (mErrorView == null)
        {
            mErrorView = View.inflate(getContext(), R.layout.page_error, null);

            Button btn_reload = (Button)mErrorView.findViewById(R.id.btn_reload);

            btn_reload.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    // 切换到加载状态
                    currentState = state_load;

                    // 切换页面
                    showPager();

                    Executors.newSingleThreadExecutor().execute(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            SystemClock.sleep(500);
                            // 重新加载数据
                            loadData();

                        }
                    });

                }
            });

        }
        addView(mErrorView);

        // 加载 成功
        if (mSuccessView == null)
        {
            mSuccessView = createSuccessView();
        }
        addView(mSuccessView);

        // 切换界面的方法
        showPager();

        // 根据加载的json数据切换页面
        loadData();

    }

    public void loadData()
    {
        Executors.newSingleThreadExecutor().execute(new Runnable()
        // 创建一个线程的线程池
        {
            @Override
            public void run()
            {

                // 得到网络数据,并解析完成的json对象
                Object obj = getNetData();

                // 检查对象,根据对象切换状态
                currentState = check(obj);

                // 切换界面
                Utils.runOnUIThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        showPager();
                    }
                });

            }
        });
    }

    // 根据我们的返回对象来检查,返回状态
    private int check(Object obj)
    {
        if (obj == null)
        {
            // 出错
            return state_error;
        }
        else
        {
            // 成功状态,不一定,集合,如果为0个数据,那么也是错的
            if (obj instanceof List)
            {
                
                List list = (List)obj;
                if (list.size() > 0)
                {
                    return state_success;
                }
                else
                {
                    return state_error;
                }
            }
            else
            {
                return state_success;
            }
        }
        
    }
    
    // 得到网络数据,json 有几种形式,对象,集合
    protected abstract Object getNetData();
    
    // 定义状态
    public static final int state_load = 101;
    
    public static final int state_success = 102;
    
    public static final int state_error = 103;
    
    // 当前的状态
    private int currentState = state_load;
    
    // 切换界面
    private void showPager()
    {
        
        // 全部隐藏
        mLoadingView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mSuccessView.setVisibility(View.GONE);
        
        switch (currentState)
        {
            case state_load:
                mLoadingView.setVisibility(View.VISIBLE);
                break;
            case state_success:
                mSuccessView.setVisibility(View.VISIBLE);
                
                break;
            case state_error:
                mErrorView.setVisibility(View.VISIBLE);
                
                break;
                
            default:
                break;
                
        }
        
    }
    
    protected abstract View createSuccessView();
}
