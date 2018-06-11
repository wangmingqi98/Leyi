package com.leyijf.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.inputmethod.InputMethodManager;

import com.leyijf.bean.UserInfo;


/**
 * Created by Administrator on 2017/12/26.
 * 用户状态
 */

public class AppUtils {


    // 全局上下文
    public static Context appContext;
    public static boolean isOpen=false;


    // 当前用户
    public static UserInfo userInfo;

    public static UserInfo getUser() {
        return userInfo;
    }

    /**
     * 保存当前用户
     *
     * @param userInfo
     */
    public static void setUser(UserInfo userInfo) {
        if (userInfo == null) {
            return;
        }
        AppUtils.userInfo = userInfo;
        FileUtil.saveUser(userInfo);
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static void setAppContext(Context appContext) {
        AppUtils.appContext = appContext;
    }

    /**
     * 重置用户  退出登录
     */
    public static void resetUser() {
        AppUtils.userInfo = null;
        FileUtil.saveUser(null);


    }

    /**
     * 关闭软键�?
     *
     * @param activity
     */
    public static void closeFoldUpTheKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) appContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView()
                    .getWindowToken(), 0);
        }
    }

    /**
     * 判断应用是否赋予某个权限
     *
     * @param permisstion
     */
    public static Boolean isCheckPermisstion(String permisstion) {
        PackageManager pm = appContext.getPackageManager();
        Boolean isPermisstion = (PackageManager.PERMISSION_GRANTED == pm
                .checkPermission(permisstion, appContext.getPackageName()));
        if (isPermisstion) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 保存当前用户是否存储了手势密码
     *

     */



    public static void setStates(boolean state) {
       isOpen = state;
    }

    public static  boolean getStates() {
        return isOpen;
    }


}
