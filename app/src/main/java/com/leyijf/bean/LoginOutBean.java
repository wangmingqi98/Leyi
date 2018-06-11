package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/5/3.
 */

public class LoginOutBean {

    /**
     * response_code : 1
     * show_err : 退出登录成功
     * objects : {"show_err":"退出登录成功"}
     */

    private int response_code;

    public static LoginOutBean objectFromData(String str) {

        return new Gson().fromJson(str, LoginOutBean.class);
    }

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }


        /**
         * show_err : 退出登录成功
         */

        private String show_err;



        public String getShow_err() {
            return show_err;
        }

        public void setShow_err(String show_err) {
            this.show_err = show_err;
        }

}
