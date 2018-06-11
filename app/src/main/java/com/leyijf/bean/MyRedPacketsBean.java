package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/5/2.
 */

public class MyRedPacketsBean {

    public static MyRedPacketsBean objectFromData(String str) {

        return new Gson().fromJson(str, MyRedPacketsBean.class);
    }


        private List<VouchersBean> vouchers;



        public List<VouchersBean> getVouchers() {
            return vouchers;
        }

        public void setVouchers(List<VouchersBean> vouchers) {
            this.vouchers = vouchers;
        }

        public static class VouchersBean {
            /**
             * name : 邀请奖励
             * can_use_limit : 100
             * invest_period_limit : 0
             * invest_period_type : 月
             * send_type : 4
             * exchange_score : 0
             * exchange_limit : 0
             * exchange_sn : null
             * use_limit : 35
             * use_count : 4
             * end_time : 0
             * use_time : 0
             * remain_day : -17648
             * status : 0
             * voucher_id : 6675
             * money : 300
             */

            private String name;
            private String can_use_limit;
            private String invest_period_limit;
            private String invest_period_type;
            private String send_type;
            private String exchange_score;
            private String exchange_limit;
            private Object exchange_sn;
            private String use_limit;
            private String use_count;
            private int end_time;
            private int use_time;
            private int remain_day;
            private int status;
            private int too_time_status;
            private String voucher_id;
            private String money;

            public static VouchersBean objectFromData(String str) {

                return new Gson().fromJson(str, VouchersBean.class);
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCan_use_limit() {
                return can_use_limit;
            }

            public void setCan_use_limit(String can_use_limit) {
                this.can_use_limit = can_use_limit;
            }

            public String getInvest_period_limit() {
                return invest_period_limit;
            }

            public void setInvest_period_limit(String invest_period_limit) {
                this.invest_period_limit = invest_period_limit;
            }

            public String getInvest_period_type() {
                return invest_period_type;
            }

            public void setInvest_period_type(String invest_period_type) {
                this.invest_period_type = invest_period_type;
            }

            public String getSend_type() {
                return send_type;
            }

            public void setSend_type(String send_type) {
                this.send_type = send_type;
            }

            public String getExchange_score() {
                return exchange_score;
            }

            public void setExchange_score(String exchange_score) {
                this.exchange_score = exchange_score;
            }

            public String getExchange_limit() {
                return exchange_limit;
            }

            public void setExchange_limit(String exchange_limit) {
                this.exchange_limit = exchange_limit;
            }

            public Object getExchange_sn() {
                return exchange_sn;
            }

            public void setExchange_sn(Object exchange_sn) {
                this.exchange_sn = exchange_sn;
            }

            public String getUse_limit() {
                return use_limit;
            }

            public void setUse_limit(String use_limit) {
                this.use_limit = use_limit;
            }

            public String getUse_count() {
                return use_count;
            }

            public void setUse_count(String use_count) {
                this.use_count = use_count;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public int getUse_time() {
                return use_time;
            }

            public void setUse_time(int use_time) {
                this.use_time = use_time;
            }

            public int getRemain_day() {
                return remain_day;
            }

            public void setRemain_day(int remain_day) {
                this.remain_day = remain_day;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getVoucher_id() {
                return voucher_id;
            }

            public void setVoucher_id(String voucher_id) {
                this.voucher_id = voucher_id;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public int getToo_time_status() {
                return too_time_status;
            }

            public void setToo_time_status(int too_time_status) {
                this.too_time_status = too_time_status;
            }
        }

}
