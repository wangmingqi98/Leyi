package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/5/24.
 */

public class RedPaketsBean {

    public static RedPaketsBean objectFromData(String str) {

        return new Gson().fromJson(str, RedPaketsBean.class);
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
             * count : 25
             * voucher_id : 5810
             * user_id : 756
             * end_time : 0
             * use_time : 0
             * money : 2
             * name : 邀请奖励
             * can_use_limit : 100
             * invest_period_limit : 0
             * invest_period_type : 1
             * send_type : 4
             * use_limit : 6
             * remain_day : -17648
             */

            private String count;
            private String voucher_id;
            private String user_id;
            private int end_time;
            private int use_time;
            private String money;
            private String name;
            private String can_use_limit;
            private String invest_period_limit;
            private String invest_period_type;
            private String send_type;
            private int use_limit;
            private int remain_day;

            public static VouchersBean objectFromData(String str) {

                return new Gson().fromJson(str, VouchersBean.class);
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getVoucher_id() {
                return voucher_id;
            }

            public void setVoucher_id(String voucher_id) {
                this.voucher_id = voucher_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
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

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
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

            public int getUse_limit() {
                return use_limit;
            }

            public void setUse_limit(int use_limit) {
                this.use_limit = use_limit;
            }

            public int getRemain_day() {
                return remain_day;
            }

            public void setRemain_day(int remain_day) {
                this.remain_day = remain_day;
            }
        }

}
