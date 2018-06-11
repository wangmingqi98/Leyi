package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/5/29.
 */

public class GetUseMoneyBean {

    @Override
    public String toString() {
        return "GetUseMoneyBean{" +
                "useful_money='" + useful_money + '\'' +
                ", fee_config=" + fee_config +
                '}';
    }

    public static GetUseMoneyBean objectFromData(String str) {

        return new Gson().fromJson(str, GetUseMoneyBean.class);
    }

        /**
         * useful_money : 1045461.68
         * fee_config : [{"fee_id":"10","name":"提现费","min_price":"50","max_price":"50000","fee":"0.00","fee_type":"0","vip_id":"3"},{"fee_id":"11","name":"提现手续费2","min_price":"50000","max_price":"100000","fee":"10.00","fee_type":"0","vip_id":"3"}]
         */

        private String useful_money;
        private List<FeeConfigBean> fee_config;


        public String getUseful_money() {
            return useful_money;
        }

        public void setUseful_money(String useful_money) {
            this.useful_money = useful_money;
        }

        public List<FeeConfigBean> getFee_config() {
            return fee_config;
        }

        public void setFee_config(List<FeeConfigBean> fee_config) {
            this.fee_config = fee_config;
        }

        public static class FeeConfigBean {
            /**
             * fee_id : 10
             * name : 提现费
             * min_price : 50
             * max_price : 50000
             * fee : 0.00
             * fee_type : 0
             * vip_id : 3
             */

            private String fee_id;
            private String name;
            private double min_price;
            private double max_price;
            private double fee;
            private String fee_type;
            private String vip_id;

            public static FeeConfigBean objectFromData(String str) {

                return new Gson().fromJson(str, FeeConfigBean.class);
            }

            public String getFee_id() {
                return fee_id;
            }

            public void setFee_id(String fee_id) {
                this.fee_id = fee_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getMin_price() {
                return min_price;
            }

            public void setMin_price(double min_price) {
                this.min_price = min_price;
            }

            public double getMax_price() {
                return max_price;
            }

            public void setMax_price(double max_price) {
                this.max_price = max_price;
            }

            public double getFee() {
                return fee;
            }

            public void setFee(double fee) {
                this.fee = fee;
            }

            public String getFee_type() {
                return fee_type;
            }

            public void setFee_type(String fee_type) {
                this.fee_type = fee_type;
            }

            public String getVip_id() {
                return vip_id;
            }

            public void setVip_id(String vip_id) {
                this.vip_id = vip_id;
            }

            @Override
            public String toString() {
                return "FeeConfigBean{" +
                        "fee_id='" + fee_id + '\'' +
                        ", name='" + name + '\'' +
                        ", min_price=" + min_price +
                        ", max_price=" + max_price +
                        ", fee=" + fee +
                        ", fee_type='" + fee_type + '\'' +
                        ", vip_id='" + vip_id + '\'' +
                        '}';
            }
        }

}
