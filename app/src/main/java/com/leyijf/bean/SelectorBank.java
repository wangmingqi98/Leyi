package com.leyijf.bean;

/**
 * Created by Administrator on 2018/3/30.
 */

public class SelectorBank {


    /**
     * id : 41
     * name : 上海浦东发展银行
     * is_rec : 1
     * day : 3
     * sort : 0
     * icon : ./public/bank/SPDB.png
     * code : SPDB
     */

    private String id;
    private String name;
    private String is_rec;
    private String day;
    private String sort;
    private String icon;
    private String code;

    public SelectorBank(String id, String name, String is_rec, String day, String sort, String icon, String code) {
        this.id = id;
        this.name = name;
        this.is_rec = is_rec;
        this.day = day;
        this.sort = sort;
        this.icon = icon;
        this.code = code;
    }

    @Override
    public String toString() {
        return "SelectorBank{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", is_rec='" + is_rec + '\'' +
                ", day='" + day + '\'' +
                ", sort='" + sort + '\'' +
                ", icon='" + icon + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIs_rec() {
        return is_rec;
    }

    public void setIs_rec(String is_rec) {
        this.is_rec = is_rec;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
