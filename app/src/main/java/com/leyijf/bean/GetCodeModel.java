package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/4/27.
 */

public class GetCodeModel {

    public static GetCodeModel objectFromData(String str) {

        return new Gson().fromJson(str, GetCodeModel.class);
    }


        /**
         * mobile : 13763846098
         * token : AckUzgdGv6elJRH6UXSP7ArFGi9H7GxP
         * show_err : 验证短信已经发送，请注意查收
         */

        private String mobile;
        private String token;
        private String show_err;



        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getShow_err() {
            return show_err;
        }

        public void setShow_err(String show_err) {
            this.show_err = show_err;
        }

    @Override
    public String toString() {
        return "GetCodeModel{" +
                "mobile='" + mobile + '\'' +
                ", token='" + token + '\'' +
                ", show_err='" + show_err + '\'' +
                '}';
    }
}
