package com.leyi.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by Administrator on 2018/3/8.
 */

public class WaveView2 extends View {

    private Paint mPaint;
    private Path mPath;
    private int mWaveLength = 1;
    private int dx;
    private int dy;

    public WaveView2(Context context) {
        super(context);
        init();
    }

    public WaveView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPath = new Path();
    }

    int s = 50;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        int originY = -30;
        int mWaveHalfLength = mWaveLength / 2;
        mPath.moveTo(-mWaveLength + dx, originY);

        int max = 6;
        int min = 4;
        int maxW = 12;
        int minW = 8;
        Random random = new Random();
        try {
            Thread.sleep(500);
            s = random.nextInt(max) % (max - min + 1) + min;
            mWaveLength = random.nextInt(maxW) % (maxW - minW + 1) + minW;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //屏幕的宽度里面放多少个波长
        for (int i = -mWaveLength; i < getWidth() + mWaveLength; i += mWaveLength) {
            //相对绘制二阶贝塞尔曲线(相对于自己的起始点--也即是上一个曲线的终点  的距离dx1)
            mPath.rQuadTo(mWaveHalfLength / 2, -s, mWaveHalfLength, 0);
            mPath.rQuadTo(mWaveHalfLength / 2, s, mWaveHalfLength, 0);
            //          path.quadTo(x1, y1, x2, y2)
        }
        //颜色填充
        //画一个封闭的空间
//        mPath.lineTo(getWidth(), getHeight());
//        mPath.lineTo(0, getHeight());
//        mPath.close();

        canvas.drawPath(mPath, mPaint);
    }

//    public void startAnimation() {
//        ValueAnimator animator = ValueAnimator.ofInt(0, mWaveLength);
//        animator.setDuration(1000);
//        animator.setInterpolator(new LinearInterpolator());
//        //无限循环
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                dx = (int) animation.getAnimatedValue();
//                postInvalidate();
//            }
//        });
//        animator.start();
//    }
}