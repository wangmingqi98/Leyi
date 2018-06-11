package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/5/28.
 */

public class PayBean {

    public static PayBean objectFromData(String str) {

        return new Gson().fromJson(str, PayBean.class);
    }

        /**
         * data : {"user_id":"5","user_real_name":"李会锋","user_idno":"412723181230241656","user_bankcard":"6217000010035076796","recharge_money":10000,"mchnt_orderid":"20180428662538241","back_url":"http://www.leyibank.com/mapi/callback.php"}
         */

        private DataBean data;


        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * user_id : 5
             * user_real_name : 李会锋
             * user_idno : 412723181230241656
             * user_bankcard : 6217000010035076796
             * recharge_money : 10000
             * mchnt_orderid : 20180428662538241
             * back_url : http://www.leyibank.com/mapi/callback.php
             */

            private String user_id;
            private String user_real_name;
            private String user_idno;
            private String user_bankcard;
            private int recharge_money;
            private String mchnt_orderid;
            private String back_url;

            public static DataBean objectFromData(String str) {

                return new Gson().fromJson(str, DataBean.class);
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_real_name() {
                return user_real_name;
            }

            public void setUser_real_name(String user_real_name) {
                this.user_real_name = user_real_name;
            }

            public String getUser_idno() {
                return user_idno;
            }

            public void setUser_idno(String user_idno) {
                this.user_idno = user_idno;
            }

            public String getUser_bankcard() {
                return user_bankcard;
            }

            public void setUser_bankcard(String user_bankcard) {
                this.user_bankcard = user_bankcard;
            }

            public int getRecharge_money() {
                return recharge_money;
            }

            public void setRecharge_money(int recharge_money) {
                this.recharge_money = recharge_money;
            }

            public String getMchnt_orderid() {
                return mchnt_orderid;
            }

            public void setMchnt_orderid(String mchnt_orderid) {
                this.mchnt_orderid = mchnt_orderid;
            }

            public String getBack_url() {
                return back_url;
            }

            public void setBack_url(String back_url) {
                this.back_url = back_url;
            }
        }

}
