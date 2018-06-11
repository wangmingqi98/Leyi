package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/5/3.
 */

public class RateCouponBean {

    public static RateCouponBean objectFromData(String str) {

        return new Gson().fromJson(str, RateCouponBean.class);
    }


        private List<RatesBean> rates;



        public List<RatesBean> getRates() {
            return rates;
        }

        public void setRates(List<RatesBean> rates) {
            this.rates = rates;
        }

        public static class RatesBean {
            /**
             * rate_id : 1474
             * end_time : 0
             * use_time : 0
             * money : 1.00%
             * name : 新手专享
             * can_use_limit : 0
             * invest_period_limit : 0
             * invest_period_type : 月
             * remain_day : -17645
             * status : 1
             */

            private String rate_id;
            private int end_time;
            private int use_time;
            private String money;
            private String name;
            private String can_use_limit;
            private String invest_period_limit;
            private String invest_period_type;
            private int use_limit;

            public int getUse_count() {
                return use_count;
            }

            public void setUse_count(int use_count) {
                this.use_count = use_count;
            }

            private int use_count;

            public int getUse_limit() {


                return use_limit;
            }

            public void setUse_limit(int use_limit) {
                this.use_limit = use_limit;
            }

            private int remain_day;
            private int status;
            private int too_time_status;

            public static RatesBean objectFromData(String str) {

                return new Gson().fromJson(str, RatesBean.class);
            }

            public String getRate_id() {
                return rate_id;
            }

            public void setRate_id(String rate_id) {
                this.rate_id = rate_id;
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

            public int getToo_time_status() {
                return too_time_status;
            }

            public void setToo_time_status(int too_time_status) {
                this.too_time_status = too_time_status;
            }
        }

}
