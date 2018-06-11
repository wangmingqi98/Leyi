package com.leyijf.bean;

/**
 * Created by Administrator on 2018/3/21.
 */

public class SystemBean {

//   String virtual_money_1:累计成交额
//    virtual_money_2:累计创造收益
//    virtual_money_3:本息保障金
//    program_title:程序标题（如：）
//    site_domain：网址
String virtual_money_1;
String virtual_money_2;
String virtual_money_3;
String program_title;
String site_domain;
String kf_phone;
String kf_email;
String version;

    public SystemBean(String virtual_money_1, String virtual_money_2, String virtual_money_3, String program_title, String site_domain, String kf_phone, String kf_email, String version) {
        this.virtual_money_1 = virtual_money_1;
        this.virtual_money_2 = virtual_money_2;
        this.virtual_money_3 = virtual_money_3;
        this.program_title = program_title;
        this.site_domain = site_domain;
        this.kf_phone = kf_phone;
        this.kf_email = kf_email;
        this.version = version;
    }


    @Override
    public String toString() {
        return "SystemBean{" +
                "virtual_money_1='" + virtual_money_1 + '\'' +
                ", virtual_money_2='" + virtual_money_2 + '\'' +
                ", virtual_money_3='" + virtual_money_3 + '\'' +
                ", program_title='" + program_title + '\'' +
                ", site_domain='" + site_domain + '\'' +
                ", kf_phone='" + kf_phone + '\'' +
                ", kf_email='" + kf_email + '\'' +
                ", version='" + version + '\'' +
                '}';
    }


    public String getVirtual_money_1() {
        return virtual_money_1;
    }

    public void setVirtual_money_1(String virtual_money_1) {
        this.virtual_money_1 = virtual_money_1;
    }

    public String getVirtual_money_2() {
        return virtual_money_2;
    }

    public void setVirtual_money_2(String virtual_money_2) {
        this.virtual_money_2 = virtual_money_2;
    }

    public String getVirtual_money_3() {
        return virtual_money_3;
    }

    public void setVirtual_money_3(String virtual_money_3) {
        this.virtual_money_3 = virtual_money_3;
    }

    public String getProgram_title() {
        return program_title;
    }

    public void setProgram_title(String program_title) {
        this.program_title = program_title;
    }

    public String getSite_domain() {
        return site_domain;
    }

    public void setSite_domain(String site_domain) {
        this.site_domain = site_domain;
    }

    public String getKf_phone() {
        return kf_phone;
    }

    public void setKf_phone(String kf_phone) {
        this.kf_phone = kf_phone;
    }

    public String getKf_email() {
        return kf_email;
    }

    public void setKf_email(String kf_email) {
        this.kf_email = kf_email;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
