package com.leyijf.bean;

/**
 * Created by Administrator on 2018/4/2.
 */

public class HelpBean {

    String tirle;
    String content;

    public HelpBean(String tirle, String content) {
        this.tirle = tirle;
        this.content = content;
    }

    public String getTirle() {
        return tirle;
    }

    public void setTirle(String tirle) {
        this.tirle = tirle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
