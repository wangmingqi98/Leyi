package com.leyijf;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;


import com.fuiou.mobile.FyPay;
import com.leyijf.greendao.DaoMaster;
import com.leyijf.greendao.DaoSession;
import com.leyijf.lock.LockPatternUtils;
import com.leyijf.manager.ConfigManager;
import com.leyijf.util.AppUtils;
import com.leyijf.util.ProcessUtil;
import com.leyijf.util.SharePreferenceUtil;
import com.leyijf.util.lockutil.Constant;
import com.leyijf.util.lockutil.Util;
import com.leyijf.utils.NetworkUtil;
import com.leyijf.view.MainTabActivity;
import com.leyijf.view.activity.DrawPsdActivity;
import com.leyijf.weight.grsture.Contants;
import com.leyijf.weight.grsture.LockMode;
import com.leyijf.weight.grsture.PasswordUtil;
import com.leyijf.weight.grsture.StringUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

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
    public static MainTabActivity mainTabActivity;
    private static NetworkUtil.NetworkTypeEnum mCurrentNetworkStatus;
    public static final String TAG = "App";


    private SharedPreferences mSP;
    private LockPatternUtils mLockPatternUtils;
    private int countActivedActivity = -1;
    private boolean mBackgroundEver = false;

    static {
        mCurrentNetworkStatus = NetworkUtil.NetworkTypeEnum.UNKNOWN;
    }


    //数据库
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;



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

    public  static App getInstance() {
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
        FyPay.setDev(false);//线下
//        FyPay.setDev(true);//线上
        FyPay.init(this);
        setDatabase();
        AppUtils.setAppContext(this);
        if (ProcessUtil.isCurMainProcess(getApplicationContext())) {
            // CrashHandler.getInstance().init(this);
            setCurrentNetworkStatus(NetworkUtil.getInstance().getNetWorkType(this));
            ConfigManager.init(this);
        }
        countActivedActivity = 0;
        initShare();

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



    }

    private void initShare() {

        UMConfigure.init(this,"5b0647b9a40fa34c0b000160"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true);
        //微信
        PlatformConfig.setWeixin("wx8717ab960b43d5a8", "ccc7a0517c0074a33e7b05ce217d2e7c");
//        QQ
        PlatformConfig.setQQZone("1106850798", "JLv7jrRdIrUnTnjB");

    }

    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "user-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public static DaoSession getDaoSession() {
        if(mDaoSession == null){

            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }


    public static boolean isFirstLunch() {
        return ConfigManager.getConfigAsBoolean(ConfigManager.ConfigKeyEnum.IS_FIRST_LUNCH);
    }
    public static boolean isNetworkAvailable() {
        return !(NetworkUtil.NetworkTypeEnum.UNKNOWN
                .equals(mCurrentNetworkStatus) || NetworkUtil.NetworkTypeEnum.INVALID
                .equals(mCurrentNetworkStatus));
    }
    public static NetworkUtil.NetworkTypeEnum getCurrentNetworkStatus() {
        return mCurrentNetworkStatus;
    }

    public static String getCurrentNetworkStatusDesc() {
        return NetworkUtil.getInstance().getNetworkStateDesc(
                mCurrentNetworkStatus);
    }
    public static void setCurrentNetworkStatus(
            NetworkUtil.NetworkTypeEnum paramNetworkClassEnum) {
        mCurrentNetworkStatus = paramNetworkClassEnum;
    }

    public SQLiteDatabase getDb() {
        return db;
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
//                Util.toast(this, "超时了,请重新验证");
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