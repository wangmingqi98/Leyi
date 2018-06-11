package com.leyijf.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.leyijf.App;
import com.leyijf.R;
import com.leyijf.utils.DataCleanManager;
import com.leyijf.view.MainTabActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 欢迎页面
 * wmq
 */
    public class WelcomeActivity extends Activity {
        private static final String TAG = "WelcomeActivity";
        private Timer timer;

        private static final int GO_HOME = 1000;
        private static final int GO_GUIDE = 1001;
        // 延迟1秒
        private static final long SPLASH_DELAY_MILLIS = 1000;
        private int recLen = 3;
        private TextView tv_time;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_welcome);
            if (!isTaskRoot()) {

                finish();

                return;

            }
            tv_time = (TextView) findViewById(R.id.welcome_time);
//        handler.postDelayed(runnable, 1000);

            time();
        }

        public void time() {
            timer = new Timer(true);
            timer.schedule(task, 1000); // 延时1000ms后执行，1000ms执行一次
            // //timer.cancel();

        }

        TimerTask task = new TimerTask() {
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };
        final Handler handler = new Handler() {

            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        if (App.isNetworkAvailable()) {
                            init();
                        } else {
                            showNoNetWorkDlg(WelcomeActivity.this, "无法链接到服务器，请检查网络");
                        }

                        break;
                }

            }
        };

        void init() {
            // 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
            if (App.getInstance().isFirstLunch()) {
                mnHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
            } else {
                mnHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
            }

        }

        /**
         * 当判断当前手机没有网络时选择是否打开网络设置
         *
         * @param context
         */
        public void showNoNetWorkDlg(final Context context, String msg) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(R.drawable.logo)         //
                    .setTitle("乐毅金服")            //
                    .setMessage(msg).setPositiveButton("设置", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 跳转到系统的网络设置界面
                    Intent intent = null;
                    // 先判断当前系统版本
                    if (android.os.Build.VERSION.SDK_INT > 10) {  // 3.0以上
                        intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                    } else {
                        intent = new Intent();
                        intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
                    }
                    context.startActivity(intent);

                }
            }).setNegativeButton("知道了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    goHome();
                }
            }).show();
        }

//    Handler handler = new Handler();
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            recLen--;
//            if (recLen > 0) {
//                tv_time.setText(recLen + "s" + "跳过");
//                handler.postDelayed(this, 1000);
//            } else {
//                getToken();
//            }
//        }
//    };
        /**
         * Handler:跳转到不同界面
         */
        @SuppressLint("HandlerLeak")
        private Handler mnHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case GO_HOME:
                        goHome();
                        break;
                    case GO_GUIDE:
                        goGuide();
                        break;
                }
                super.handleMessage(msg);
            }
        };


        private void goHome() {
            Intent intent = new Intent(WelcomeActivity.this, MainTabActivity.class);
            WelcomeActivity.this.startActivity(intent);
            WelcomeActivity.this.finish();
        }

        private void goGuide() {
            DataCleanManager.cleanInternalCache(this);
            DataCleanManager.cleanExternalCache(this);
            Intent intent = new Intent(WelcomeActivity.this,
                    NavigationPageActivity.class);
            WelcomeActivity.this.startActivity(intent);
            WelcomeActivity.this.finish();
        }

    }
