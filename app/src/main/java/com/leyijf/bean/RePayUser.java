package com.leyijf.bean;

/**
 * Created by Administrator on 2018/3/20.
 */

public class RePayUser {
    String id;
    String user_name;
    String real_name;
    String sex;
    String mobile;
    String byear;
    String graduation;
    String marriage;
    String hashouse;
    String houseloan;
    String hascar;
    String carloan;
    String address;

    @Override
    public String toString() {
        return "RePayUser{" +
                "id='" + id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", real_name='" + real_name + '\'' +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                ", byear='" + byear + '\'' +
                ", graduation='" + graduation + '\'' +
                ", marriage='" + marriage + '\'' +
                ", hashouse='" + hashouse + '\'' +
                ", houseloan='" + houseloan + '\'' +
                ", hascar='" + hascar + '\'' +
                ", carloan='" + carloan + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public RePayUser(String id, String user_name, String real_name, String sex, String mobile, String byear, String graduation, String marriage, String hashouse, String houseloan, String hascar, String carloan, String address) {
        this.id = id;
        this.user_name = user_name;
        this.real_name = real_name;
        this.sex = sex;
        this.mobile = mobile;
        this.byear = byear;
        this.graduation = graduation;
        this.marriage = marriage;
        this.hashouse = hashouse;
        this.houseloan = houseloan;
        this.hascar = hascar;
        this.carloan = carloan;
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getByear() {
        return byear;
    }

    public void setByear(String byear) {
        this.byear = byear;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getHashouse() {
        return hashouse;
    }

    public void setHashouse(String hashouse) {
        this.hashouse = hashouse;
    }

    public String getHouseloan() {
        return houseloan;
    }

    public void setHouseloan(String houseloan) {
        this.houseloan = houseloan;
    }

    public String getHascar() {
        return hascar;
    }

    public void setHascar(String hascar) {
        this.hascar = hascar;
    }

    public String getCarloan() {
        return carloan;
    }

    public void setCarloan(String carloan) {
        this.carloan = carloan;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}