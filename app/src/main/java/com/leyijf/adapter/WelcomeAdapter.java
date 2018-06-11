package com.leyijf.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.leyijf.R;
import com.leyijf.view.MainTabActivity;

import java.util.List;


public class WelcomeAdapter extends PagerAdapter {
    private Activity activity;
    private Button btn_exper;
    // 界面列表
    private List<View> views;

    public WelcomeAdapter(List<View> views, Activity activity) {
        this.views = views;
        this.activity = activity;
    }

    // 销毁arg1位置的界面
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(views.get(arg1));
    }

    @Override
    public void finishUpdate(View arg0) {
        // TODO Auto-generated method stub

    }

    // 获得当前界面数
    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }

        return 0;
    }

    // 初始化arg1位置的界面
    @Override
    public Object instantiateItem(View arg0, int arg1) {

        ((ViewPager) arg0).addView(views.get(arg1), 0);
        if (arg1 == views.size() - 1) {
            btn_exper = (Button) arg0.findViewById(R.id.what_new_img);
            btn_exper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goHome();
                }
            });

        }
        return views.get(arg1);
    }

    private void goHome() {

        Intent intent2 = new Intent();
        intent2.setClass(activity, MainTabActivity.class);
        activity.startActivity(intent2);
        activity.finish();


    }


    // 判断是否由对象生成界面
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public Parcelable saveState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void startUpdate(View arg0) {
        // TODO Auto-generated method stub

    }

}
