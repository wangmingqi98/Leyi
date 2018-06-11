package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/4/27.
 */

public class SetPasswordCodeBean {

    /**
     * response_code : 1
     * show_err : 验证短信已经发送，请注意查收
     * objects : {"token":"cDV1zoClrgaL5UPG4G3m7isdzrKwpdae","show_err":"验证短信已经发送，请注意查收"}
     */


    public static SetPasswordCodeBean objectFromData(String str) {

        return new Gson().fromJson(str, SetPasswordCodeBean.class);
    }


        /**
         * token : cDV1zoClrgaL5UPG4G3m7isdzrKwpdae
         * show_err : 验证短信已经发送，请注意查收
         */

        private String token;
        private String show_err;



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
        return "SetPasswordCodeBean{" +
                "token='" + token + '\'' +
                ", show_err='" + show_err + '\'' +
                '}';
    }
}
