package com.leyijf.util;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.leyijf.view.activity.DrawPsdActivity;
import com.leyijf.weight.grsture.Contants;
import com.leyijf.weight.grsture.LockMode;
import com.leyijf.weight.grsture.PasswordUtil;
import com.leyijf.weight.grsture.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/13.
 */

public class ActivityLifecycleCallbackss implements Application.ActivityLifecycleCallbacks, ActivityState {

    private List<Activity> activityList=new ArrayList<>();
    private List<Activity> resumeActivity=new ArrayList<>();
    private int activityAount = 0;
    private boolean isAppForeground = true;
    private long behind;
    private long front;
    private long timeStart;

    @Override
    public int count() {
        return activityList.size();
    }
    @Override
    public boolean isFront() {
        return resumeActivity.size() > 0;
    }
    @Override
    public Activity current() {
        return activityList.size()>0 ? activityList.get(0): null;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activityList.add(0, activity);
    }
    @Override
    public void onActivityStarted(Activity activity) {
        activityAount++;
        if(activityAount>0){
            isAppForeground = true;

            Log.e("切到前台-->", "切到前台-->");

           long timeEnd = new Date().getTime();

            Log.e("时间差", timeEnd -timeStart+"");

            //切换到前台和切换到后台的时间差大于等于10s
            if(!StringUtils.isEmpty(PasswordUtil.getPin(activity.getBaseContext()))&&timeStart!=0&& timeEnd -timeStart>=1000*5){
                Intent intent=new Intent(activity.getBaseContext(), DrawPsdActivity.class);

                intent.putExtra(Contants.INTENT_SECONDACTIVITY_KEY, LockMode.VERIFY_PASSWORD);
                 activity.startActivity(intent);
            }

            Log.e("---","onActivityStarted");
    }
        Log.e("---","onActivityStartedonActivityStarted");
        Log.e("num","activity"+activityAount);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (!resumeActivity.contains(activity)) {
            resumeActivity.add(activity);
            Log.e("---","onActivityResumed");
        }
        Log.e("---","onActivityResumedonActivityResumed");
        Log.e("num","activity"+activityAount);
    }

    @Override
    public void onActivityPaused(Activity activity) {

        Log.e("---","onActivityPaused");
        Log.e("num","activity"+activityAount);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        resumeActivity.remove(activity);
        activityAount--;
        if (activityAount == 0 ) {
            // 此时表明应用在后台
            isAppForeground = false;
            timeStart = new Date().getTime();
           Log.e("切到后台-->", timeStart +"");

        }

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        Log.e("---","onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityList.remove(activity);
        Log.e("---","onActivityDestroyed");
        Log.e("num","activity"+activityAount);
    }

}
