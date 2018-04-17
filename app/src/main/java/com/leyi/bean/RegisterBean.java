package com.leyi.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 */

public class RegisterBean {


    /**
     * response_code : 1
     * objects : {"user_info":{"user_id":"4","user_name":"u176ZSXV3146","user_img":"","user_mobile":"176****3146","id_passed":0,"has_paypassword":0,"binded_card":[]}}
     */

    private int response_code;
    private ObjectsBean objects;

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public ObjectsBean getObjects() {
        return objects;
    }

    public void setObjects(ObjectsBean objects) {
        this.objects = objects;
    }

    public static class ObjectsBean {
        /**
         * user_info : {"user_id":"4","user_name":"u176ZSXV3146","user_img":"","user_mobile":"176****3146","id_passed":0,"has_paypassword":0,"binded_card":[]}
         */

        private UserInfoBean user_info;

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public static class UserInfoBean {
            /**
             * user_id : 4
             * user_name : u176ZSXV3146
             * user_img :
             * user_mobile : 176****3146
             * id_passed : 0
             * has_paypassword : 0
             * binded_card : []
             */

            private String user_id;
            private String user_name;
            private String user_img;
            private String user_mobile;
            private int id_passed;
            private int has_paypassword;
            private List<?> binded_card;

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

            public List<?> getBinded_card() {
                return binded_card;
            }

            public void setBinded_card(List<?> binded_card) {
                this.binded_card = binded_card;
            }
        }
    }
}
