package com.leyijf.http.retrofit_rxjava_ok;

import com.google.gson.annotations.SerializedName;

/**
 * 服务器通用返回数据格式
 * Created by jaycee on 2017/6/23.
 */
public class BaseEntity<E> {


    @SerializedName("response_code")
    private int response_code;

    @SerializedName("objects")
    private E objects;

    public String getShow_err() {
        return show_err;
    }

    @SerializedName("show_err")
    private String show_err;

    public boolean isSuccess() {
        return response_code == 1;
    }

    public int getResponse_code() {
        return response_code;
    }



    public E getData() {
        return objects;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "response_code=" + response_code +
                ", objects=" + objects +
                ", show_err='" + show_err + '\'' +
                '}';
    }
}
