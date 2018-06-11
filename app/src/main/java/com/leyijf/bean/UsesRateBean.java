package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/5/24.
 */

public class UsesRateBean {

    public static UsesRateBean objectFromData(String str) {

        return new Gson().fromJson(str, UsesRateBean.class);
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
             * rate_id : 2276
             * end_time : 0
             * use_time : 0
             * money : 1.00
             * name : 新手专享
             * can_use_limit : 1000
             * invest_period_limit : 1
             * invest_period_type : 1
             * remain_day : -17674
             */

            private String rate_id;
            private int end_time;
            private int use_time;
            private String money;
            private String name;
            private String can_use_limit;
            private String invest_period_limit;
            private String invest_period_type;
            private int remain_day;

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
        }

}
