package com.leyijf.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by WMQ on 2018/4/19.
 */

public class LoginBean {
    /**
     * user_login_status : 1
     * show_err : 用户登陆成功
     * user_info : {"user_id":"4","user_name":"u176ZSXV3146","user_img":"","user_mobile":"176****3146","id_passed":0,"has_paypassword":0,"binded_card":[]}
     */
    @SerializedName("user_login_status")
    private int user_login_status;
    @SerializedName("show_err")
    private String show_err;
    @SerializedName("user_info")
    private UserInfoBean user_info;
    @SerializedName("session_id")
    private String session_id;

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

    public String getShow_err() {
        return show_err;
    }

    public void setShow_err(String show_err) {
        this.show_err = show_err;
    }

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public static class UserInfoBean {
        @Override
        public String toString() {
            return "UserInfoBean{" +
                    "user_id='" + user_id + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", user_img='" + user_img + '\'' +
                    ", user_mobile='" + user_mobile + '\'' +
                    ", id_passed=" + id_passed +
                    ", has_paypassword=" + has_paypassword +
                    ", binded_card=" + binded_card +
                    '}';
        }

        /**
         * user_id : 4
         * user_name : u176ZSXV3146
         * user_img :
         * user_mobile : 176****3146
         * id_passed : 0
         * has_paypassword : 0
         * binded_card : []
         * user_mobile_referee
         */

        private String user_id;
        private String user_name;
        private String user_img;

        public String getUser_mobile_referee() {
            return user_mobile_referee;
        }

        public void setUser_mobile_referee(String user_mobile_referee) {
            this.user_mobile_referee = user_mobile_referee;
        }

        private String user_mobile_referee;
        private String user_mobile;
        private int id_passed;
        private int has_paypassword;
        private List<BindedCardBean> binded_card;

        public List<FeeConfigBean> getFee_config() {
            return fee_config;
        }

        public void setFee_config(List<FeeConfigBean> fee_config) {
            this.fee_config = fee_config;
        }

        private List<FeeConfigBean> fee_config;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_img() {
            return user_img;
        }

        public void setUser_img(String user_img) {
            this.user_img = user_img;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
        }

        public int getId_passed() {
            return id_passed;
        }

        public void setId_passed(int id_passed) {
            this.id_passed = id_passed;
        }

        public int getHas_paypassword() {
            return has_paypassword;
        }

        public void setHas_paypassword(int has_paypassword) {
            this.has_paypassword = has_paypassword;
        }

        public List<BindedCardBean> getBinded_card() {
            return binded_card;
        }

        public void setBinded_card(List<BindedCardBean> binded_card) {
            this.binded_card = binded_card;
        }
    }

}
