package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/4/23.
 */

public class TrurhNameBean {

    public static TrurhNameBean objectFromData(String str) {

        return new Gson().fromJson(str, TrurhNameBean.class);
    }

    @Override
    public String toString() {
        return "TrurhNameBean{" +
                "show_err='" + show_err + '\'' +
                ", user_info=" + user_info +
                '}';
    }

    /**
         * show_err : 提交成功
         * user_info : {"user_id":756,"user_name":"安慕希","user_img":"","user_mobile":"135****2668","id_passed":1,"has_paypassword":0,"binded_card":[]}
         */

        private String show_err;
        private UserInfoBean user_info;



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
            /**
             * user_id : 756
             * user_name : 安慕希
             * user_img :
             * user_mobile : 135****2668
             * id_passed : 1
             * has_paypassword : 0
             * binded_card : []
             */

            private int user_id;
            private String user_name;
            private String user_img;
            private String user_mobile;
            private int id_passed;
            private int has_paypassword;
            private List<?> binded_card;

            @Override
            public String toString() {
                return "UserInfoBean{" +
                        "user_id=" + user_id +
                        ", user_name='" + user_name + '\'' +
                        ", user_img='" + user_img + '\'' +
                        ", user_mobile='" + user_mobile + '\'' +
                        ", id_passed=" + id_passed +
                        ", has_paypassword=" + has_paypassword +
                        ", binded_card=" + binded_card +
                        '}';
            }

            public static UserInfoBean objectFromData(String str) {

                return new Gson().fromJson(str, UserInfoBean.class);
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
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

            public List<?> getBinded_card() {
                return binded_card;
            }

            public void setBinded_card(List<?> binded_card) {
                this.binded_card = binded_card;
            }
        }

}
