package com.leyijf.base;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

import com.leyijf.App;
import com.leyijf.R;
import com.leyijf.auto.layout.AutoLayoutActivity;
import com.leyijf.manager.AppManager;
import com.leyijf.util.DialogUtils;
import com.leyijf.util.MyExit;
import com.leyijf.util.NetUtil;
import com.leyijf.view.activity.DrawPsdActivity;
import com.leyijf.weight.grsture.Contants;
import com.leyijf.weight.grsture.LockMode;
import com.leyijf.weight.grsture.PasswordUtil;
import com.leyijf.weight.grsture.StringUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/12/26.
 * Activity基类
 */

public abstract class BaseActivity extends AutoLayoutActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    private Context mContext;
    private App myApp;
    private static final int REQUEST_CAMERA = 0;


    private final int mRequestCode = 1024;
    private RequestPermissionCallBack mRequestPermissionCallBack;

    private static Handler handler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        myApp = App.getInstance();
        AppManager.getInstance().addActivity(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));
        }
        setStatusBar();
        setContentView(getContentViewId());
        initId();


        initData();


    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//
//    }

    /**
     * 线程调度
     */
    protected <T> ObservableTransformer<T, T> compose(final LifecycleTransformer<T> lifecycle) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                // 可添加网络连接判断等
                                if (!NetUtil.isNetConnected(BaseActivity.this)) {
                                    Toast.makeText(BaseActivity.this, "　暂无网络！！！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(lifecycle);
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().finishActivity(this);

    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        long durTime = System.currentTimeMillis() - myApp.getLockTime();
//        if (durTime > 10 * 1000) {
//            if (myApp.getSettings() != null) {
//                if (myApp.getSettings().getGesture() != null
//                        && !myApp.getSettings().getGesture().isEmpty()) {
//                    actionSecondActivity(LockMode.VERIFY_PASSWORD);
//                }
//            }
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        myApp.setLockTime(System.currentTimeMillis());
//    }




    protected abstract void initData();

    protected abstract void initId();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int getContentViewId();
    protected void setStatusBar() {
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        //只对api19以上版本有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            initWindow();
        }else {
            //为状态栏着色
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.progress_color_1);
        }






    }

//    @TargetApi(19)
//    private void setTranslucentStatus(boolean on) {
//        Window win = getWindow();
//        WindowManager.LayoutParams winParams = win.getAttributes();
//        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//        if (on) {
//            winParams.flags |= bits;
//        } else {
//            winParams.flags &= ~bits;
//        }
//        win.setAttributes(winParams);
//    }
    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(android.R.color.transparent));
            tintManager.setStatusBarTintEnabled(true);
        }
    }



    /**
     * android6.0授权
     *
     * @param competence
     * @return
     */
    public Boolean showCompetence(String competence) {
        String competences = competence;
        if (ActivityCompat.checkSelfPermission(this, competence)
                != PackageManager.PERMISSION_GRANTED) {
            // Camera permission has not been granted.
            requestCameraPermission(competence);
            return false;
        } else {

            return true;
        }
    }

    private void requestCameraPermission(String competence) {
        // BEGIN_INCLUDE(camera_permission_request)
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, competence)) {
            ActivityCompat.requestPermissions(this, new String[]{competence}, REQUEST_CAMERA);
        } else {
            // Camera permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(this, new String[]{competence}, REQUEST_CAMERA);
        }
        // END_INCLUDE(camera_permission_request)
    }


    /**


     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取导航栏高度
     * @param context
     * @return
     */
    public static int getDaoHangHeight(Context context) {
        int result = 0;
        int resourceId=0;
        int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid!=0){
            resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            Log.e("高度===","高度："+resourceId);
            Log.e("=========","高度："+context.getResources().getDimensionPixelSize(resourceId) +"");
            return context.getResources().getDimensionPixelSize(resourceId);
        }else
            return 0;
    }



    /**
     * 发起权限请求
     *
     * @param context
     * @param permissions
     * @param callback
     */
    public void requestPermissions(final Context context, final String[] permissions,
                                   RequestPermissionCallBack callback) {
        mRequestPermissionCallBack = callback;
        StringBuilder permissionNames = new StringBuilder();
        for (String s : permissions) {
            permissionNames = permissionNames.append(s + "\r\n");
        }
        //如果所有权限都已授权，则直接返回授权成功,只要有一项未授权，则发起权限请求
        boolean isAllGranted = true;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                isAllGranted = false;
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
                    new AlertDialog.Builder(BaseActivity.this).setTitle("PermissionTest")//设置对话框标题
                            .setMessage("【用户曾经拒绝过你的请求，所以这次发起请求时解释一下】" +
                                    "您好，需要如下权限：" + permissionNames +
                                    " 请允许，否则将影响部分功能的正常使用。")//设置显示的内容
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                                @Override
                                public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                    //TODO Auto-generated method stub
                                    ActivityCompat.requestPermissions(((Activity) context), permissions, mRequestCode);
                                }
                            }).show();//在按键响应事件中显示此对话框
                } else {
                    ActivityCompat.requestPermissions(((Activity) context), permissions, mRequestCode);
                }
                break;
            }
        }
        if (isAllGranted) {
            mRequestPermissionCallBack.granted();
            return;
        }
    }

    /**
     * 权限请求结果回调接口
     */
    public   interface RequestPermissionCallBack {
        /**
         * 同意授权
         */
        void granted();

        /**
         * 取消授权
         */
        void denied();
    }


    public static void finishLoading() {

        DialogUtils.Loading.finishLoading();
    }

    public static void showLoading(String text, Context context) {
        // marginTop 0dp 背景透明
        DialogUtils.Loading.showLoading(text, context, R.dimen.dp0,
                Color.TRANSPARENT);
        return;
    }


    /**
     * 判断手机是否有网络
     *
     * @return true 有网络
     */
    public boolean isConnected() {
        return NetUtil.isNetConnected(this);
    }



    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }


    //获取屏幕原始尺寸高度，包括虚拟功能键高度
    public static int getDpi(Context context){
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics",DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi=displayMetrics.heightPixels;
        }catch(Exception e){
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 获取 虚拟按键的高度
     * @param context
     * @return
     */
    public static  int getBottomStatusHeight(Context context){
        int totalHeight = getDpi(context);

        int contentHeight = getScreenHeight(context);

        return totalHeight  - contentHeight;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
        }
        return false;
//        if (keyCode == event.KEYCODE_BACK) {
//            MyExit exit = new MyExit();
//            if (exit.getExit()) {
//                System.exit(0);
//                return true;
//            } else {
//                Toast.makeText(this, "连续点击两次退出程序", Toast.LENGTH_SHORT).show();
//                exit.clickTwoExit();
////                System.exit(0);
//                return false;
//            }
//
//        }
//        return super.onKeyDown(keyCode, event);
    }
//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        // TODO Auto-generated method stub
//        if (event.getAction() == KeyEvent.ACTION_DOWN
//                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//            exitBy2Click();
//        }
//        return false;
//
//    }


public void  withColor(){
    SystemBarTintManager tintManager = new SystemBarTintManager(this);
    tintManager.setStatusBarTintEnabled(true);
    tintManager.setStatusBarTintResource(R.color.progress_color_1);
}


    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
        }
    }

    /**
     * 跳转到密码处理界面
     */
    private void actionSecondActivity(LockMode mode) {

        if (!StringUtils.isEmpty(PasswordUtil.getPin(this))) {
            Intent intent = new Intent(this, DrawPsdActivity.class);
            intent.putExtra(Contants.INTENT_SECONDACTIVITY_KEY, mode);
            startActivity(intent);

        }


    }

    protected final String[] neededPermissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };
}
