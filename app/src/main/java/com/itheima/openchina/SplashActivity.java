package com.itheima.openchina;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.widget.ImageView;

import com.itheima.openchina.anim.WeatherView;
import com.itheima.openchina.appcontrol.App;
import com.itheima.openchina.bases.BaseActivity;
import com.itheima.openchina.ui.activity.MainActivity;

public class SplashActivity extends BaseActivity {

    private WeatherView aninView;
    private ImageView image;

    @Override
    protected int getLayoutRs() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        super.init();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                image = findViewById(R.id.imageSplash);
                image.setScaleX(0);
                image.setScaleY(0);
            }
        });
        App.getMainHandler().postDelayed(new Runnable() {
            @Override
            public void run() {

                aninView = findViewById(R.id.animSplashView);
                //缩小背景图

                aninView.startAnim();
                //放大背景图
                image.animate().scaleX(1).setDuration(1500);
                image.animate().scaleY(1).setDuration(1500);
                ObjectAnimator ob=ObjectAnimator.ofFloat(image,"alpha",0,1);
                ob.setDuration(2000);
                ob.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startMActivity(MainActivity.class);
                        finish();
                    }
                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }
                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
                ob.start();

            }
        }, 800);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(aninView!=null){
            aninView.onDetachedFromWindow();
        }
    }
}
