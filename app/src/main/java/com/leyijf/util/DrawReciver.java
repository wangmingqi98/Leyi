package com.leyijf.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2018/3/14.
 */



public class DrawReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

    }
//
//    private Context context;
//    private ActivityLifecycleCallbackss activityLifecycleCallbacks;
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        this.context = context;
//        App application = (App) context.getApplicationContext();
//        activityLifecycleCallbacks = (ActivityLifecycleCallbackss) application.activityLifecycleCallbacks;
////            如果应用处于前台做某些操作（一般此时，收到通知和点击通知栏做相同的操作）
//        if (activityLifecycleCallbacks.isFront()) {
//            if (activityLifecycleCallbacks.current() instanceof DrawPsdActivity) {
////                    如果当前Activity是要跳转的Activity，做某些操作，一般不跳转，可用EventBus
//
//            } else {
////                    否则执行跳转或其他相关操作等
//
//
//
//            }
//
//        }else  if ("cn.jpush.android.intent.NOTIFICATION_OPENED".equals(intent.getAction())){
////                应用处于不可见状态，一般点击通知栏是跳转到某个页面
//
//        }
//    }
}

