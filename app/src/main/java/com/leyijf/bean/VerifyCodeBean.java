package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/4/27.
 */

public class VerifyCodeBean {

    public static VerifyCodeBean objectFromData(String str) {

        return new Gson().fromJson(str, VerifyCodeBean.class);
    }


        /**
         * token : h8nHnTkukU9JU5hZ4cekMbMd5QaQp1AN
         */

        private String token;



        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

    @Override
    public String toString() {
        return "VerifyCodeBean{" +
                "token='" + token + '\'' +
                '}';
    }
}
