package com.leyi.base;

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
import android.view.WindowManager;

import com.leyi.App;
import com.leyi.R;
import com.leyi.auto.layout.AutoLayoutActivity;
import com.leyi.util.DialogUtils;
import com.leyi.util.NetUtil;
import com.leyi.util.lockutil.ActivityCollector;
import com.leyi.view.activity.DrawPsdActivity;
import com.leyi.weight.grsture.Contants;
import com.leyi.weight.grsture.LockMode;
import com.leyi.weight.grsture.PasswordUtil;
import com.leyi.weight.grsture.StringUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/12/26.
 */

public abstract class BaseActivity extends AutoLayoutActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    private Context mContext;
    private App myApp;


    private final int mRequestCode = 1024;
    private RequestPermissionCallBack mRequestPermissionCallBack;

    private static Handler handler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        mContext = this;
        myApp = App.getInstance();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));
        }
        setStatusBar();
        setContentView(getContentViewId());
        initId();


        initData();



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);


    }


    //
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
     * 权限请求结果回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasAllGranted = true;
        StringBuilder permissionName = new StringBuilder();
        for (String s : permissions) {
            permissionName = permissionName.append(s + "\r\n");
        }
        switch (requestCode) {
            case mRequestCode: {
                for (int i = 0; i < grantResults.length; ++i) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        hasAllGranted = false;
                        //在用户已经拒绝授权的情况下，如果shouldShowRequestPermissionRationale返回false则
                        // 可以推断出用户选择了“不在提示”选项，在这种情况下需要引导用户至设置页手动授权
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                            new AlertDialog.Builder(BaseActivity.this).setTitle("PermissionTest")//设置对话框标题
                                    .setMessage("【用户选择了不再提示按钮，或者系统默认不在提示（如MIUI）。" +
                                            "引导用户到应用设置页去手动授权,注意提示用户具体需要哪些权限】" +
                                            "获取相关权限失败:" + permissionName +
                                            "将导致部分功能无法正常使用，需要到设置页面手动授权")//设置显示的内容
                                    .setPositiveButton("去授权", new DialogInterface.OnClickListener() {//添加确定按钮
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                            //TODO Auto-generated method stub
                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                                            intent.setData(uri);
                                            startActivity(intent);
                                            dialog.dismiss();
                                        }
                                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加返回按钮
                                @Override
                                public void onClick(DialogInterface dialog, int which) {//响应事件
                                    // TODO Auto-generated method stub
                                    dialog.dismiss();
                                }
                            }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    mRequestPermissionCallBack.denied();
                                }
                            }).show();//在按键响应事件中显示此对话框
                        } else {
                            //用户拒绝权限请求，但未选中“不再提示”选项
                            mRequestPermissionCallBack.denied();
                        }
                        break;
                    }
                }
                if (hasAllGranted) {
                    mRequestPermissionCallBack.granted();
                }
            }
        }
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



public void  withColor(){
    SystemBarTintManager tintManager = new SystemBarTintManager(this);
    tintManager.setStatusBarTintEnabled(true);
    tintManager.setStatusBarTintResource(R.color.progress_color_1);
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
