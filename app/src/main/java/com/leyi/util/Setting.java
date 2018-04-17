package com.leyi.util;

/**
 * Created by Administrator on 2018/3/13.
 */

/**
 * 保存手势密码相关设置
 */
public class Setting {
    private String gesture; // 手势密码
    private String showPath;// 是否显示轨迹

    public Setting(String gesture, String showPath) {
        this.gesture = gesture;
        this.showPath = showPath;
    }

    // get()和set()

    public String getGesture() {
        return gesture;
    }

    public void setGesture(String gesture) {
        this.gesture = gesture;
    }

    public String getShowPath() {
        return showPath;
    }

    public void setShowPath(String showPath) {
        this.showPath = showPath;
    }
}