package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/5/11.
 */

public class AlsoMoneyBean {

    public static AlsoMoneyBean objectFromData(String str) {

        return new Gson().fromJson(str, AlsoMoneyBean.class);
    }

        /**
         * session_id : ooqa4mnkte98c1pcv3g27keir3
         * user_login_status : 1
         * status : 1
         * show_err : 操作成功!
         */

        private String session_id;
        private int user_login_status;
        private int status;
        private String show_err;


        public String getSession_id() {
            return session_id;
        }

        public void setSession_id(String session_id) {
            this.session_id = session_id;
        }

        public int getUser_login_status() {
            return user_login_status;
        }

        public void setUser_login_status(int user_login_status) {
            this.user_login_status = user_login_status;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getShow_err() {
            return show_err;
        }

        public void setShow_err(String show_err) {
            this.show_err = show_err;
        }

}