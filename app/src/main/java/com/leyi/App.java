package com.leyi;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;


import com.leyi.lock.LockPatternUtils;
import com.leyi.util.AppUtils;
import com.leyi.util.SharePreferenceUtil;
import com.leyi.util.lockutil.Constant;
import com.leyi.util.lockutil.Util;
import com.leyi.view.activity.DrawPsdActivity;
import com.leyi.weight.grsture.Contants;
import com.leyi.weight.grsture.LockMode;
import com.leyi.weight.grsture.PasswordUtil;
import com.leyi.weight.grsture.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/5.
 *
 */

public class App  extends Application {

    private List<Activity> activityList=new ArrayList<>();
    private List<Activity> resumeActivity=new ArrayList<>();
    private static App mApplication;
    public static final String SP_FILE_NAME = "SP_FILE";
    private SharePreferenceUtil mSpUtil;
    private int activityAount = 0;
    private long timeStart;
    private long timeEnd;
    private long end=0;
    private long start;
    private boolean isAppForeground = true;


    public static final String TAG = "App";


    private SharedPreferences mSP;
    private LockPatternUtils mLockPatternUtils;
    private int countActivedActivity = -1;
    private boolean mBackgroundEver = false;


    public LockPatternUtils getLockPatternUtils() {
        return mLockPatternUtils;
    }

    /**
     * 获取是否开启手势，默认true开启，如果异常，则不开启手势
     *
     * @return
     */
    private boolean isAlpSwitchOn() {
        boolean result = false;
        try {
            result = mSP.getBoolean(Constant.ALP_SWITCH_ON, true);
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

    public synchronized static App getInstance() {
        return mApplication;

    }

    public synchronized SharePreferenceUtil getSpUtil() {
        if (mSpUtil == null)
            mSpUtil = new SharePreferenceUtil(this, SP_FILE_NAME);
        return mSpUtil;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        initData();
        mApplication = this;

        AppUtils.setAppContext(this);
        countActivedActivity = 0;

        mSP = getSharedPreferences(Constant.CONFIG_NAME, MODE_PRIVATE);
        mLockPatternUtils = new LockPatternUtils(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (countActivedActivity == 0 && mBackgroundEver == true) {
                    Log.v(TAG, "切到前台  lifecycle");
                    timeOutCheck(activity);
                }
                countActivedActivity++;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                countActivedActivity--;
                if (countActivedActivity == 0) {
                    Log.v(TAG, "切到后台  lifecycle");
                    mBackgroundEver = true;

                    if (isAlpSwitchOn() == true) {
                        saveStartTime();
                    }
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });


 ;
    }


    private void initData() {
        mSpUtil = new SharePreferenceUtil(this, SP_FILE_NAME);

    }
    public void timeOutCheck(Activity activity) {
        long endTime = System.currentTimeMillis();
        if (endTime - getStartTime() >= Constant.TIMEOUT_ALP * 1000) {

            String alp = mSP.getString(Constant.ALP, null);
//            if (TextUtils.isEmpty(alp) == false) {
//                Intent intent = new Intent(this, UnlockGestureActivity.class);
//                intent.putExtra("pattern", alp);
//                intent.putExtra("login", false); //手势验证，不进行登录验证
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                // 打开新的Activity
//                activity.startActivityForResult(intent, Constant.REQ_COMPARE_PATTERN_TIMEOUT_CHECK);
//            }
            if(!StringUtils.isEmpty(PasswordUtil.getPin(this))){
                Util.toast(this, "超时了,请重新验证");
                Intent intent = new Intent(this, DrawPsdActivity.class);
                intent.putExtra(Contants.INTENT_SECONDACTIVITY_KEY, LockMode.VERIFY_PASSWORD);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity.startActivityForResult(intent,111);

            }


        }
    }

    public void saveStartTime() {
        mSP.edit().putLong(Constant.START_TIME, System.currentTimeMillis()).commit();
    }

    public long getStartTime() {
        long startTime = 0;
        try {
            startTime = mSP.getLong(Constant.START_TIME, 0);
        } catch (Exception e) {
            startTime = 0;
        }
        return startTime;
    }



}