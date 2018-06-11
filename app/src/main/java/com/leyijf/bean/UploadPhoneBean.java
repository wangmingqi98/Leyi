package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/4/27.
 */

public class UploadPhoneBean {

    public static UploadPhoneBean objectFromData(String str) {

        return new Gson().fromJson(str, UploadPhoneBean.class);
    }


        /**
         * show_err : 修改成功
         * user_info : {"user_id":5,"user_name":"阿发几份","user_img":"https://www.baidu.com/img/bd_logo1.png?e=1524586462&token=Wlzx_o-SAmn38Hp43BgOrw1YGrci8oNIo7GHGIzK:Pz7vRaBpJoWnYb4wzI9obTvt8w0=","user_mobile":"136****9864","id_passed":1,"has_paypassword":1,"binded_card":[]}
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
             * user_id : 5
             * user_name : 阿发几份
             * user_img : https://www.baidu.com/img/bd_logo1.png?e=1524586462&token=Wlzx_o-SAmn38Hp43BgOrw1YGrci8oNIo7GHGIzK:Pz7vRaBpJoWnYb4wzI9obTvt8w0=
             * user_mobile : 136****9864
             * id_passed : 1
             * has_paypassword : 1
             * binded_card : []
             */

            private int user_id;
            private String user_name;
            private String user_img;
            private String user_mobile;
            private int id_passed;
            private int has_paypassword;
            private List<?> binded_card;

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
