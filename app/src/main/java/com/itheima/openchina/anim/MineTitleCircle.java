package com.itheima.openchina.anim;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

/**
 * Created by 佘本民
 * When:  --- 2017/11/2---
 * Time:  --- 12:41---
 * Function:
 */

public class MineTitleCircle extends RelativeLayout {


    private int width,height,maxR,radiu1,radiu2;
    private Paint paintCircle,paint;
    private Path path1,path2,path3;
    private float move1,move2,move3;
    private ValueAnimator value1;
    private ValueAnimator value2;
    private ValueAnimator value3;
    private PathMeasure pathMeasure1,pathMeasure2,pathMeasure3;
    private Path pathCircle1,pathCircle2,pathCircle3;
    private float[] mPan,mTan;
    private int cirlength1;
    private int cirlength2;
    private int cirlength3;

    public MineTitleCircle(Context context) {
        this(context,null);
    }

    public MineTitleCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        paintCircle=new Paint(Paint.ANTI_ALIAS_FLAG);

        paintCircle.setColor(Color.parseColor("#fb7dea5c"));
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setStrokeWidth(6);

        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#fb7dea5c"));
        paint.setStyle(Paint.Style.FILL);

        mPan=new float[2];
        mTan=new float[2];

        moveAnim1();
        moveAnim2();
        moveAnim3();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width=w;
        height=h;
        maxR=Math.max(width,height)/40*19;
        radiu1=maxR/3;
        radiu2=radiu1*2;

        path1=new Path();
        path2=new Path();
        path3=new Path();
        path1.addCircle(width/2,height,radiu1, Path.Direction.CW);
        path2.addCircle(width/2,height,radiu2, Path.Direction.CW);
        path3.addCircle(width/2,height,maxR, Path.Direction.CW);

        pathMeasure1=new PathMeasure(path1,true);
        pathMeasure2=new PathMeasure(path2,true);
        pathMeasure3=new PathMeasure(path3,true);

        pathCircle1=new Path();
        pathCircle2=new Path();
        pathCircle3=new Path();
        pathCircle1.addCircle(0,0,10, Path.Direction.CW);
        pathCircle2.addCircle(0,0,10, Path.Direction.CW);
        pathCircle3.addCircle(0,0,10, Path.Direction.CW);

        //运动轨迹长度
        cirlength1 = (int) pathMeasure1.getLength();
        cirlength2 = (int) pathMeasure2.getLength();
        cirlength3 = (int) pathMeasure3.getLength();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#FB65C448"));
        canvas.drawPath(path1,paintCircle);
        canvas.drawPath(path2,paintCircle);
        canvas.drawPath(path3,paintCircle);

        //获得坐标点，和对应的切点
        pathMeasure1.getPosTan(cirlength1*move1,mPan,mTan);
       //将切点作为圆心点坐标。
        canvas.drawCircle(mPan[0],mPan[1],15,paint);


        pathMeasure2.getPosTan(cirlength2*move2,mPan,mTan);
        canvas.drawCircle(mPan[0],mPan[1],15,paint);

        pathMeasure3.getPosTan(cirlength3*move3,mPan,mTan);
        canvas.drawCircle(mPan[0],mPan[1],15,paint);
    }


    //3个小球的动画
    public void moveAnim1(){
        value1=ValueAnimator.ofFloat(0,1);
        value1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                move1=v;
                invalidate();
            }
        });
        value1.setRepeatCount(ValueAnimator.INFINITE);
        value1.setRepeatMode(ValueAnimator.RESTART);
        value1.setDuration(5000);
        value1.setInterpolator(new AccelerateDecelerateInterpolator());
        value1.start();
    }

    public void moveAnim2(){
        value2=ValueAnimator.ofFloat(1,0);
        value2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                move2=1-v;
                invalidate();
            }
        });
        value2.setRepeatCount(ValueAnimator.INFINITE);
        value2.setRepeatMode(ValueAnimator.RESTART);
        value2.setDuration(6000);
        value2.setInterpolator(new AccelerateDecelerateInterpolator());
        value2.start();
    }

    public void moveAnim3(){
        value3=ValueAnimator.ofFloat(0,1);
        value3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                move3=v;
                invalidate();
            }
        });
        value3.setRepeatCount(ValueAnimator.INFINITE);
        value3.setRepeatMode(ValueAnimator.RESTART);
        value3.setDuration(7000);
        value3.setInterpolator(new AccelerateDecelerateInterpolator());
        value3.start();
    }



    //结束所有动画的监听
    public void animDestroy() {
        if (value1 != null) {
            value1.removeAllUpdateListeners();
        }
        if (value2 != null) {
            value2.removeAllUpdateListeners();
        }
        if (value3 != null) {
            value3.removeAllUpdateListeners();
        }

    }

}
