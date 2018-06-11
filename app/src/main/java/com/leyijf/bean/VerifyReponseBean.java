package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/4/25.
 */

public class VerifyReponseBean {


    public static VerifyReponseBean objectFromData(String str) {

        return new Gson().fromJson(str, VerifyReponseBean.class);
    }


        /**
         * show_err : 身份验证通过
         */

        private String show_err;



        public String getShow_err() {
            return show_err;
        }

        public void setShow_err(String show_err) {
            this.show_err = show_err;
        }

}
