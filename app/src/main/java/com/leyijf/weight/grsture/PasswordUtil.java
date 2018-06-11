package com.leyijf.weight.grsture;

import android.content.Context;

/**
 * Created by leo on 16/4/5.
 */
public class PasswordUtil {

    /**
     * 获取设置过的密码
     */
    public static String getPin(Context context) {
        String password = ConfigUtil.getInstance(context).getString(Contants.PASS_KEY);
        return password;
    }

    /**
     * 获取设置过的密码
     */
    public  static  void  delectPin(Context context) {
      ConfigUtil.getInstance(context).remove(Contants.PASS_KEY);

    }
}
