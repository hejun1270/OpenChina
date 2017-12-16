package com.itheima.openchina;

import android.Manifest;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itheima.openchina.anim.WeatherView;
import com.itheima.openchina.appcontrol.App;
import com.itheima.openchina.bases.BaseActivity;
import com.itheima.openchina.ui.activity.MainActivity;
import com.itheima.openchina.utils.ToastUtil;

public class SplashActivity extends BaseActivity {

    private WeatherView aninView;
    private LinearLayout image;

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
                ObjectAnimator ob = ObjectAnimator.ofFloat(image, "alpha", 0, 1);
                ob.setDuration(2000);
                ob.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        checkPermission();
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

    /**
     * 权限检查
     */
    private void checkPermission() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            startMActivity(MainActivity.class);
            finish();
            return;
        }
        int result = ActivityCompat.checkSelfPermission(this, Manifest.permission
                .WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            startMActivity(MainActivity.class);
            finish();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                    .WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            startMActivity(MainActivity.class);
            finish();
        } else {
            finish();
            ToastUtil.showToast("请求权限拒绝!");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (aninView != null) {
            aninView.onDetachedFromWindow();
        }
    }
}
