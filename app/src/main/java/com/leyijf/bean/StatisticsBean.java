package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/5/14.
 */

public class StatisticsBean {

    public static StatisticsBean objectFromData(String str) {

        return new Gson().fromJson(str, StatisticsBean.class);
    }

        /**
         * last_month_profit : 635.58
         * load_earnings : 1846.70
         * load_list : [{"dlr_id":"1","deal_id":"2","true_repay_time":"2018-04-12","load_per_profit":"31.25","name":"新手专享1号","label":"本期收益发放"}]
         */

        private String last_month_profit;
        private String load_earnings;
        private List<LoadListBean> load_list;


        public String getLast_month_profit() {
            return last_month_profit;
        }

        public void setLast_month_profit(String last_month_profit) {
            this.last_month_profit = last_month_profit;
        }

        public String getLoad_earnings() {
            return load_earnings;
        }

        public void setLoad_earnings(String load_earnings) {
            this.load_earnings = load_earnings;
        }

        public List<LoadListBean> getLoad_list() {
            return load_list;
        }

        public void setLoad_list(List<LoadListBean> load_list) {
            this.load_list = load_list;
        }

        public static class LoadListBean {
            /**
             * dlr_id : 1
             * deal_id : 2
             * true_repay_time : 2018-04-12
             * load_per_profit : 31.25
             * name : 新手专享1号
             * label : 本期收益发放
             */

            private String dlr_id;
            private String deal_id;
            private String true_repay_time;
            private String load_per_profit;
            private String name;
            private String label;

            public static LoadListBean objectFromData(String str) {

                return new Gson().fromJson(str, LoadListBean.class);
            }

            public String getDlr_id() {
                return dlr_id;
            }

            public void setDlr_id(String dlr_id) {
                this.dlr_id = dlr_id;
            }

            public String getDeal_id() {
                return deal_id;
            }

            public void setDeal_id(String deal_id) {
                this.deal_id = deal_id;
            }

            public String getTrue_repay_time() {
                return true_repay_time;
            }

            public void setTrue_repay_time(String true_repay_time) {
                this.true_repay_time = true_repay_time;
            }

            public String getLoad_per_profit() {
                return load_per_profit;
            }

            public void setLoad_per_profit(String load_per_profit) {
                this.load_per_profit = load_per_profit;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }
        }

}
