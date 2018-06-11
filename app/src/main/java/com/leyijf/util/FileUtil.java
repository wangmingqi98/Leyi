package com.leyijf.util;

import android.content.Context;

import com.leyijf.bean.UserInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator on 2017/12/26.
 */

public class FileUtil {
   public static final String LOGIN = "login";
    /**
     * 保存用户
     *
     * @param user
     */
    public static void saveUser(UserInfo user) {
        // 对象输出�??
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(AppUtils.getAppContext()
                    .openFileOutput(FileUtil.LOGIN, Context.MODE_PRIVATE));
            // 写入数据
            oos.writeObject(user);
            // 刷新
            oos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
