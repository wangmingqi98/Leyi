package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/5/23.
 */

public class SureInvestGoods {

    public static SureInvestGoods objectFromData(String str) {

        return new Gson().fromJson(str, SureInvestGoods.class);
    }

        /**
         * available_money : 71980
         * voucher_id : 0
         * money_is_enough : 1
         * has_paypassword : 0
         */

        private double available_money;
        private int voucher_id;
        private int money_is_enough;
        private int has_paypassword;


        public double getAvailable_money() {
            return available_money;
        }

        public void setAvailable_money(double available_money) {
            this.available_money = available_money;
        }

        public int getVoucher_id() {
            return voucher_id;
        }

        public void setVoucher_id(int voucher_id) {
            this.voucher_id = voucher_id;
        }

        public int getMoney_is_enough() {
            return money_is_enough;
        }

        public void setMoney_is_enough(int money_is_enough) {
            this.money_is_enough = money_is_enough;
        }

        public int getHas_paypassword() {
            return has_paypassword;
        }

        public void setHas_paypassword(int has_paypassword) {
            this.has_paypassword = has_paypassword;
        }

}
