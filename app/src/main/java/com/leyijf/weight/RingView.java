package com.leyijf.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.leyijf.bean.UserInfo;

import java.math.BigDecimal;
import java.util.ArrayList;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * 自定义圆环统计图
 * wmq
 */
public class RingView extends View {
    private static final int CIRCLE_ANGLE = 360;//圆环的角度
    private static final int RING_STROKE_WIDTH = 15;//默认圆环的宽度为20dp
    private Paint mNoAssetsPaint, mInnerCirclePaint;
    private ArrayList<Paint> mPaints;
    private int mRingStrokeWidth;//圆环的宽度
    private int mCanvasWidth, mCanvasHeight;
    private RectF mRingRect, mInnerRect;
    private int mDensity;//手机屏幕密度
    private int mNoDataPaintColor = Color.parseColor("#cccccc");//没有数据的paint的颜色
    private int mInnerCirclePaintColor = Color.parseColor("#ffffff");//内圆的paint的颜色
    private ArrayList<Integer> mAngles;//传入的数据
    private boolean mHasData = false;
    private ArrayList<Integer> mLevelStartAngles;//每段圆弧的起始角度值
    private int mMoveAngle;//圆弧移动的角度
    private int mRingStartAngle = -90;//圆环的起始角度
    private RingAnimation mRingAnim;

    public RingView(Context context) {
        super(context);
        init(context);
    }

    public RingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context ctx) {
        mDensity = (int) ctx.getResources().getDisplayMetrics().density;
        mRingStrokeWidth = RING_STROKE_WIDTH * mDensity;
        mPaints = new ArrayList<Paint>();
        mAngles = new ArrayList<Integer>();
        mLevelStartAngles = new ArrayList<Integer>();
        mNoAssetsPaint = new Paint();
        mNoAssetsPaint.setAntiAlias(true);
        mNoAssetsPaint.setStyle(Paint.Style.FILL);
        mNoAssetsPaint.setColor(mNoDataPaintColor);
        mInnerCirclePaint = new Paint();
        mInnerCirclePaint.setAntiAlias(true);
        mInnerCirclePaint.setStyle(Paint.Style.FILL);
        mInnerCirclePaint.setColor(mInnerCirclePaintColor);

        mRingAnim = new RingAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mCanvasWidth == 0) {
            initRect();
        }
        if (!mHasData) {//没有数据
            mMoveAngle = CIRCLE_ANGLE;
            drawRingView(canvas, mRingStartAngle, mMoveAngle, mNoAssetsPaint);
        } else {
            int _level = 0;//圆弧的段数
            for (int _i = 0; _i < mAngles.size(); _i++) {//计算需要画几段圆弧
                if (mMoveAngle < mLevelStartAngles.get(1)) {
                    _level = 1;
                } else if (mMoveAngle > mLevelStartAngles.get(_i) && mMoveAngle <= mLevelStartAngles.get(_i + 1)) {
                    _level = _i + 1;
                }
            }
            drawRing(_level, canvas);
        }
        canvas.drawArc(mInnerRect, mRingStartAngle, CIRCLE_ANGLE, true, mInnerCirclePaint);//画内部的圆
    }

    /**
     *
     * @param level 圆环的段数
     * @param canvas
     */
    private void drawRing(int level, Canvas canvas) {
        if (level <= 0) {
            drawRingView(canvas, mRingStartAngle, CIRCLE_ANGLE, mNoAssetsPaint);
            return;
        }
        if (mAngles.size() > mPaints.size()) {
            int _temp = mAngles.size() - mPaints.size();
            for (int _i = 0; _i < _temp; _i++) {
                mPaints.add(mNoAssetsPaint);
            }
        }
        for (int _i = 0; _i < level; _i++) {
            if (_i == level - 1) {
                drawRingView(canvas, mRingStartAngle + mLevelStartAngles.get(_i),
                        mMoveAngle - mLevelStartAngles.get(_i), mPaints.get(_i));
            } else {
                drawRingView(canvas, mRingStartAngle + mLevelStartAngles.get(_i), mAngles.get(_i), mPaints.get(_i));
            }
        }
    }
    private void drawTexts(Canvas canvas, int center) {
        Log.d("keyi ", "drawTexts: "+UserInfo.getInstance().getTotalMoney());
        String totalStr = UserInfo.getInstance().getTotalMoney() + "元";
        mInnerCirclePaint.setStrokeWidth(2);
        //设置进度扇形的样式
        mInnerCirclePaint.setStyle(Paint.Style.FILL);
        //设置文字的大小
        mInnerCirclePaint.setTextSize(25);
        int widthStr1 = (int) mInnerCirclePaint.measureText("总资产(元)");
        int widthStr2 = (int) mInnerCirclePaint.measureText(totalStr);
        mInnerCirclePaint.setColor(Color.parseColor("#666666"));
        float baseX = center - widthStr1 / 2;
        float baseY = center;
        Paint.FontMetrics fontMetrics = mInnerCirclePaint.getFontMetrics();
        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;
        float offY = fontTotalHeight / 2 - fontMetrics.bottom - 30;
        float newY = (baseY + offY)/2;
        canvas.drawText("总资产(元)", baseX, newY, mInnerCirclePaint);

        mInnerCirclePaint.setColor(Color.parseColor("#222222"));
        mInnerCirclePaint.setTextSize(35);
        float baseX1 = center - widthStr2 / 2-20;
        float baseY1 = center + 20;
        Paint.FontMetrics fontMetrics1 = mInnerCirclePaint.getFontMetrics();
        float fontTotalHeight1 = fontMetrics1.bottom - fontMetrics1.top;
        float offY1 = fontTotalHeight1 / 2 - fontMetrics1.bottom + 30;
        float newY1 = (baseY1 + offY1)/2;
        canvas.drawText(totalStr, baseX1, newY1, mInnerCirclePaint);
    }

    /**
     *
     * @param canvas
     * @param startAngle 开始的角度
     * @param sweepAngle 旋转的角度
     * @param paint 画笔
     */
    private void drawRingView(Canvas canvas, int startAngle, int sweepAngle, Paint paint) {
        if (sweepAngle != 0) {
            canvas.drawArc(mRingRect, startAngle, sweepAngle, true, paint);
        }
    }

    public void setNoDataPaintColor(int color) {
        mNoAssetsPaint.setColor(getResources().getColor(color));
    }

    public void setNoDataPaintColor(String color) {
        mNoAssetsPaint.setColor(Color.parseColor(color));
    }

    public void setInnerCirclePaintColor(int colorId) {
        mInnerCirclePaint.setColor(getResources().getColor(colorId));
    }

    public void setInnerCirclePaintColor(String color){
        mInnerCirclePaint.setColor(Color.parseColor(color));
    }

    public void initPaint(ArrayList<Integer> colors) {
        mPaints.clear();
        for (int _i = 0; _i < colors.size(); _i++) {
            Paint _paint = new Paint();
            _paint.setAntiAlias(true);
            _paint.setStyle(Paint.Style.FILL);
            _paint.setColor(colors.get(_i));
            mPaints.add(_paint);
        }
    }

    public void initPaint(String... colors) {
        ArrayList<Integer> _colors = new ArrayList<Integer>();
        for (int _i = 0; _i < colors.length; _i++) {
            _colors.add(Color.parseColor(colors[_i]));
        }
        initPaint(_colors);
    }

    public void initPaint(int... colorIds) {
        ArrayList<Integer> _colors = new ArrayList<Integer>();
        for (int _i = 0; _i < colorIds.length; _i++) {
            _colors.add(getResources().getColor(colorIds[_i]));
        }
        initPaint(_colors);
    }

    private void initRect() {
        mCanvasWidth = getWidth();
        mCanvasHeight = getHeight();
        mInnerRect = new RectF(mRingStrokeWidth, mRingStrokeWidth, mCanvasWidth - mRingStrokeWidth, mCanvasHeight - mRingStrokeWidth);
        mRingRect = new RectF(0, 0, mCanvasWidth, mCanvasHeight);
    }

    /**
     * 设置圆环起始的角度
     * @param angle
     */

    public void setRingStartAngle(int angle){
        mRingStartAngle = angle;
    }

    /**
     * 设置圆环的环宽
     *
     * @param width
     */
    public void setRingStrokeWidth(int width) {
        mRingStrokeWidth = width * mDensity;
        invalidate();
    }

    /**
     * 所需要显示的数据的角度
     *
     * @param angles
     */
    public void setAngles(int... angles) {
        ArrayList<Integer> _angles = new ArrayList<Integer>();
        for (int _i = 0; _i < angles.length; _i++) {
            _angles.add(angles[_i]);
        }
        setAngles(_angles);
    }

    /**
     * 所需要显示的数据的角度
     *
     * @param angles
     */

    public void setAngles(ArrayList<Integer> angles) {
        mAngles.clear();
        mAngles.addAll(angles);
        mLevelStartAngles.clear();
        mLevelStartAngles.add(0);
        int _angle = 0;
        for (int _i = 0; _i < mAngles.size(); _i++) {
            _angle += mAngles.get(_i);
            mLevelStartAngles.add(_angle);
            if (mAngles.get(_i) > 0) {
                mHasData = true;
            }
        }
    }

    /**
     * 设置数据来计算角度并绘制圆环
     *
     * @param data
     */
    public void setAnglesData(BigDecimal... data) {
        BigDecimal _total = new BigDecimal("0.00");
        for (int _i = 0; _i < data.length; _i++) {
            _total = _total.add(data[_i]);
        }

        if (_total.compareTo(BigDecimal.valueOf(0)) == 0) {
            mHasData = false;
            return;
        }

        BigDecimal[] _dbData = new BigDecimal[data.length];
        for (int _i = 0; _i < data.length; _i++) {
            _dbData[_i] = data[_i].divide(_total, 10, ROUND_HALF_UP).multiply(BigDecimal.valueOf(360));
        }

        int[] _intData = new int[data.length];
        for (int _i = 0; _i < data.length; _i++) {
            //数值小于1且大于0的，就直接定1，否则转int类型，确保小数据也能出现在圆环上
            _intData[_i] = _dbData[_i].compareTo(BigDecimal.valueOf(1.0)) < 0 && _dbData[_i].compareTo(BigDecimal.valueOf(0)) > 0 ?
                    1 : _dbData[_i].intValue();
        }

        //所有数据加起来可能会不满360也可能会超出360，由于精度的问题
        //处理方案是把缺少的度数（有正也有负）加在最大的值上，这样图形出现的误差会不明显
        int _remind = 360;//剩余的角度
        int _maxPosition = -1, _max = _intData[0];
        for (int _i = 0; _i < _intData.length; _i++) {
            _remind = _remind - _intData[_i];
            if (_max <= _intData[_i]) {
                _maxPosition = _i;
            }
        }
        _intData[_maxPosition] += _remind;//将缺少的度数加载最大值上

        //将最终的数据设置到圆环上
        setAngles(_intData);
    }

    public void setAnglesData(String... data) {
        BigDecimal[] _bdData = new BigDecimal[data.length];
        for (int _i = 0; _i < data.length; _i++) {
            _bdData[_i] = new BigDecimal(TextUtils.isEmpty(data[_i]) ? "0" : data[_i]);
        }
        setAnglesData(_bdData);
    }

    public void setAnglesData(double... data) {
        BigDecimal[] _bdData = new BigDecimal[data.length];
        for (int _i = 0; _i < data.length; _i++) {
            _bdData[_i] = BigDecimal.valueOf(data[_i]);
        }
        setAnglesData(_bdData);
    }

    /**
     * 自定义动画时间的圆环
     *
     * @param animTime
     */
    public void showViewWithAnimation(int animTime) {
        startAnimation(animTime);
    }

    /**
     * 默认时间（2000）的圆环
     */
    public void showViewWithAnimation() {
        startAnimation(-1);
    }

    /**
     * 不带动画的圆环
     */
    public void showViewWithoutAnimation() {
        mMoveAngle = CIRCLE_ANGLE;
        invalidate();
    }

    private void startAnimation(int animTime) {
        mRingAnim.setDuration(animTime <= 0 ? 2000 : animTime);
        startAnimation(mRingAnim);
    }

    private class RingAnimation extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            mMoveAngle = (int) (interpolatedTime * CIRCLE_ANGLE);
            invalidate();
        }
    }
}


