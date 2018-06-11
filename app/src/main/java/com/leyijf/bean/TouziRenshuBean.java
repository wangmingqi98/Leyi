package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class TouziRenshuBean {


    public static TouziRenshuBean objectFromData(String str) {

        return new Gson().fromJson(str, TouziRenshuBean.class);
    }

        private List<BuyRecordBean> buy_record;


        public List<BuyRecordBean> getBuy_record() {
            return buy_record;
        }

        public void setBuy_record(List<BuyRecordBean> buy_record) {
            this.buy_record = buy_record;
        }

        public static class BuyRecordBean {
            /**
             * deal_id : 73
             * user_id : 792
             * user_name : m***n
             * money : 10000
             * create_time : 1524536472
             * ecv_money : 0
             * rate_money : 0.00
             * time_his : 2018-04-24
             */

            private String deal_id;
            private String user_id;
            private String user_name;
            private String money;
            private int create_time;
            private String ecv_money;
            private String rate_money;
            private String time_his;

            public static BuyRecordBean objectFromData(String str) {

                return new Gson().fromJson(str, BuyRecordBean.class);
            }

            public String getDeal_id() {
                return deal_id;
            }

            public void setDeal_id(String deal_id) {
                this.deal_id = deal_id;
            }

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

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public String getEcv_money() {
                return ecv_money;
            }

            public void setEcv_money(String ecv_money) {
                this.ecv_money = ecv_money;
            }

            public String getRate_money() {
                return rate_money;
            }

            public void setRate_money(String rate_money) {
                this.rate_money = rate_money;
            }

            public String getTime_his() {
                return time_his;
            }

            public void setTime_his(String time_his) {
                this.time_his = time_his;
            }
        }

}
