package com.leyijf.weight;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.leyijf.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/23.
 */

public class SelfStatistics extends View {
    private Paint paint;
    //对外提供注入数据的变量
    private float[] datas;
    private String total;
    //自定义view内部存储数据信息
    private List<Infos> listDatas = new ArrayList<>();
    //默认统计图的颜色配置 如果数据大于4 则颜色轮询
    private String colorRes[] = new String[]{"#FD3B18", "#FEAC2C", "#FDDF1B", "#7EFC40","#33FFCC","#42abfc"};
    private int mPanelWidth;

    public SelfStatistics(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
    }

    public SelfStatistics(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SelfStatistics(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SelfStatistics(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public float[] getDatas() {
        return datas;
    }

    public void startDraw() {
        invalidate();
    }

    public void setDatas(float[] datas) {
        this.datas = datas;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (datas != null && datas.length > 0) {
            calculateDatas();
            //获取圆心的x坐标
            int center = mPanelWidth / 2;
            //圆环的半径
            int radius = center/2-40;
            //消除锯齿
            paint.setAntiAlias(true);
            //给外圈设置样式
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            //设置StrokeWidth
            paint.setStrokeWidth(20);
            //给外圈设置颜色
            paint.setColor(Color.WHITE);
            //画最外层的圈
            canvas.drawCircle(center, center/2, radius, paint);
            //设置进度的颜色
            for (Infos infos : listDatas) {
                //定义一个RectF类
                paint.setColor(Color.parseColor(infos.getColor()));
                RectF rectF = new RectF(center - radius, center/2- radius, center + radius, center/2 + radius);
                //绘制扇形
                canvas.drawArc(rectF, infos.getStartAngle(), infos.getEndAngle(), true, paint);
            }
          //画最外层的圈
            paint.setColor(Color.WHITE);
            canvas.drawCircle(center, center/2, radius-20, paint);

            drawTexts(canvas, center);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = Math.min(widthSize, heightSize);
        if (widthMode == MeasureSpec.UNSPECIFIED) {
            width = heightSize;
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            width = widthSize;
        }
        setMeasuredDimension(width, width/2);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPanelWidth = Math.max(w,h);
    }

    private void drawTexts(Canvas canvas, int center) {
        float totals = 0;
        Log.d("keyi ", "drawTexts: "+UserInfo.getInstance().getTotalMoney());

        totals=  Float.parseFloat(total.substring(0,total.length()-1));
        String totalStr = totals + "元";
        paint.setStrokeWidth(2);
        //设置进度扇形的样式
        paint.setStyle(Paint.Style.FILL);
        //设置文字的大小
        paint.setTextSize(25);
        int widthStr1 = (int) paint.measureText("总资产(元)");
        int widthStr2 = (int) paint.measureText(totalStr);
        paint.setColor(Color.parseColor("#666666"));
        float baseX = center - widthStr1 / 2;
        float baseY = center;
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;
        float offY = fontTotalHeight / 2 - fontMetrics.bottom - 30;
        float newY = (baseY + offY)/2;
        canvas.drawText("总资产(元)", baseX, newY, paint);

        paint.setColor(Color.parseColor("#222222"));
        paint.setTextSize(35);
        float baseX1 = center - widthStr2 / 2-20;
        float baseY1 = center + 20;
        Paint.FontMetrics fontMetrics1 = paint.getFontMetrics();
        float fontTotalHeight1 = fontMetrics1.bottom - fontMetrics1.top;
        float offY1 = fontTotalHeight1 / 2 - fontMetrics1.bottom + 30;
        float newY1 = (baseY1 + offY1)/2;
        canvas.drawText(totalStr, baseX1, newY1, paint);
    }

    //为绘画做基本的计算
    private void calculateDatas() {
        float totals = 0;
        float tempAngle = 360;
        //计算出总数
        for (int i = 0; i < datas.length; i++) {
            totals += datas[i];
        }

        if(totals==0.00){
          Infos infos=new Infos();
            float startAngle = tempAngle;
            float endAngle = (float) (360 * 100 / 360 * 3.6);
            infos.setStartAngle(startAngle);
            infos.setEndAngle(endAngle);
            infos.setColor("#999999");
            tempAngle = endAngle + tempAngle;
            listDatas.add(infos);
        }else {
            //创建不同的Infos对象
            Infos infos;
            for (int i = 0; i < datas.length; i++) {
                infos = new Infos();

                float currData = datas[i];
                float startAngle = tempAngle;
                float endAngle = (float) (currData * 100 / totals * 3.6);
                infos.setStartAngle(startAngle);
                infos.setEndAngle(endAngle);
                infos.setColor(colorRes[i % colorRes.length]);
                tempAngle = endAngle + tempAngle;
                listDatas.add(infos);
            }
        }

    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    class Infos {
        private float startAngle;
        private float endAngle;
        private String color;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public float getStartAngle() {
            return startAngle;
        }

        public void setStartAngle(float startAngle) {
            this.startAngle = startAngle;
        }

        public float getEndAngle() {
            return endAngle;
        }

        public void setEndAngle(float endAngle) {
            this.endAngle = endAngle;
        }
    }
}

