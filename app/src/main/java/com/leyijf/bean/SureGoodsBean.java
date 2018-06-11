package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by WMQ on 2018/5/23.
 */

public class SureGoodsBean {

    public static SureGoodsBean objectFromData(String str) {

        return new Gson().fromJson(str, SureGoodsBean.class);
    }

        /**
         * deal_info : {"name":"测试二","rate":"12.90","repay_time":"3","repay_time_type":"月","loantype":"0","loantype_format":"等额本息","use_ecv":"0","user_vouchers":0,"user_vouchers_count":0,"use_rate":"0","user_has_rates":0,"user_rates_count":0}
         */

        private DealInfoBean deal_info;


        public DealInfoBean getDeal_info() {
            return deal_info;
        }

        public void setDeal_info(DealInfoBean deal_info) {
            this.deal_info = deal_info;
        }

        public static class DealInfoBean {
            /**
             * name : 测试二
             * rate : 12.90
             * repay_time : 3
             * repay_time_type : 月
             * loantype : 0
             * loantype_format : 等额本息
             * use_ecv : 0
             * user_vouchers : 0
             * user_vouchers_count : 0
             * use_rate : 0
             * user_has_rates : 0
             * user_rates_count : 0
             */

            private String name;
            private String rate;
            private String repay_time;
            private String repay_time_type;
            private String loantype;
            private String loantype_format;
            private String use_ecv;
            private int user_vouchers;
            private int user_vouchers_count;
            private String use_rate;
            private int user_has_rates;
            private int user_rates_count;

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

            public String getUse_ecv() {
                return use_ecv;
            }

            public void setUse_ecv(String use_ecv) {
                this.use_ecv = use_ecv;
            }

            public int getUser_vouchers() {
                return user_vouchers;
            }

            public void setUser_vouchers(int user_vouchers) {
                this.user_vouchers = user_vouchers;
            }

            public int getUser_vouchers_count() {
                return user_vouchers_count;
            }

            public void setUser_vouchers_count(int user_vouchers_count) {
                this.user_vouchers_count = user_vouchers_count;
            }

            public String getUse_rate() {
                return use_rate;
            }

            public void setUse_rate(String use_rate) {
                this.use_rate = use_rate;
            }

            public int getUser_has_rates() {
                return user_has_rates;
            }

            public void setUser_has_rates(int user_has_rates) {
                this.user_has_rates = user_has_rates;
            }

            public int getUser_rates_count() {
                return user_rates_count;
            }

            public void setUser_rates_count(int user_rates_count) {
                this.user_rates_count = user_rates_count;
            }
        }

}
