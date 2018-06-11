package com.leyijf.bean;

/**
 * Created by Administrator on 2018/3/29.
 */

public class BankName {

String name;
    String content;
    int img;

    public BankName(String name, String content, int img) {
        this.name = name;
        this.content = content;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
