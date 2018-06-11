package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/5/24.
 */

public class ComitGoodsBean {

    public static ComitGoodsBean objectFromData(String str) {

        return new Gson().fromJson(str, ComitGoodsBean.class);
    }

        /**
         * load_id : 589
         * deal_info : {"name":"小测试36","rate":"10.00","repay_time":"3","repay_time_type":"月","loantype":"1","loantype_format":"付息还本"}
         * pay_info : {"deal_name":"小测试36","pay_method":"余额支付","pay_money":680}
         */

        private int load_id;
        private DealInfoBean deal_info;
        private PayInfoBean pay_info;


        public int getLoad_id() {
            return load_id;
        }

        public void setLoad_id(int load_id) {
            this.load_id = load_id;
        }

        public DealInfoBean getDeal_info() {
            return deal_info;
        }

        public void setDeal_info(DealInfoBean deal_info) {
            this.deal_info = deal_info;
        }

        public PayInfoBean getPay_info() {
            return pay_info;
        }

        public void setPay_info(PayInfoBean pay_info) {
            this.pay_info = pay_info;
        }

        public static class DealInfoBean {
            /**
             * name : 小测试36
             * rate : 10.00
             * repay_time : 3
             * repay_time_type : 月
             * loantype : 1
             * loantype_format : 付息还本
             */

            private String name;
            private String rate;
            private String repay_time;
            private String repay_time_type;
            private String loantype;
            private String loantype_format;

            public static DealInfoBean objectFromData(String str) {

                return new Gson().fromJson(str, DealInfoBean.class);
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public String getRepay_time() {
                return repay_time;
            }

            public void setRepay_time(String repay_time) {
                this.repay_time = repay_time;
            }

            public String getRepay_time_type() {
                return repay_time_type;
            }

            public void setRepay_time_type(String repay_time_type) {
                this.repay_time_type = repay_time_type;
            }

            public String getLoantype() {
                return loantype;
            }

            public void setLoantype(String loantype) {
                this.loantype = loantype;
            }

            public String getLoantype_format() {
                return loantype_format;
            }

            public void setLoantype_format(String loantype_format) {
                this.loantype_format = loantype_format;
            }
        }

        public static class PayInfoBean {
            /**
             * deal_name : 小测试36
             * pay_method : 余额支付
             * pay_money : 680
             */

            private String deal_name;
            private String pay_method;
            private int pay_money;

            public static PayInfoBean objectFromData(String str) {

                return new Gson().fromJson(str, PayInfoBean.class);
            }

            public String getDeal_name() {
                return deal_name;
            }

            public void setDeal_name(String deal_name) {
                this.deal_name = deal_name;
            }

            public String getPay_method() {
                return pay_method;
            }

            public void setPay_method(String pay_method) {
                this.pay_method = pay_method;
            }

            public int getPay_money() {
                return pay_money;
            }

            public void setPay_money(int pay_money) {
                this.pay_money = pay_money;
            }
        }

}
