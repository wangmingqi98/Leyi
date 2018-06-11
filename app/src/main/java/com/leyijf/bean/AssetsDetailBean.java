package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/5/14.
 */

public class AssetsDetailBean {


    public static AssetsDetailBean objectFromData(String str) {

        return new Gson().fromJson(str, AssetsDetailBean.class);
    }

        /**
         * useful_money : 995045.00
         * load_already_earnings : 833.32
         * total_load_money : 74264
         * total_success_carry_money : 1000.00
         * load_wait_self_money : 60000.00
         * load_wait_earnings : 741.68
         * lock_money : 14010.00
         * carry_total_money : 3000.00
         * total_money : 1071462.68
         * open_account_lock_money : 0.00
         */

        private String useful_money;
        private String load_already_earnings;
        private String total_load_money;
        private String total_success_carry_money;
        private String load_wait_self_money;
        private String load_wait_earnings;
        private String lock_money;
        private String carry_total_money;
        private String total_money;
        private String open_account_lock_money;


        public String getUseful_money() {
            return useful_money;
        }

        public void setUseful_money(String useful_money) {
            this.useful_money = useful_money;
        }

        public String getLoad_already_earnings() {
            return load_already_earnings;
        }

        public void setLoad_already_earnings(String load_already_earnings) {
            this.load_already_earnings = load_already_earnings;
        }

        public String getTotal_load_money() {
            return total_load_money;
        }

        public void setTotal_load_money(String total_load_money) {
            this.total_load_money = total_load_money;
        }

        public String getTotal_success_carry_money() {
            return total_success_carry_money;
        }

        public void setTotal_success_carry_money(String total_success_carry_money) {
            this.total_success_carry_money = total_success_carry_money;
        }

        public String getLoad_wait_self_money() {
            return load_wait_self_money;
        }

        public void setLoad_wait_self_money(String load_wait_self_money) {
            this.load_wait_self_money = load_wait_self_money;
        }

        public String getLoad_wait_earnings() {
            return load_wait_earnings;
        }

        public void setLoad_wait_earnings(String load_wait_earnings) {
            this.load_wait_earnings = load_wait_earnings;
        }

        public String getLock_money() {
            return lock_money;
        }

        public void setLock_money(String lock_money) {
            this.lock_money = lock_money;
        }

        public String getCarry_total_money() {
            return carry_total_money;
        }

        public void setCarry_total_money(String carry_total_money) {
            this.carry_total_money = carry_total_money;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public String getOpen_account_lock_money() {
            return open_account_lock_money;
        }

        public void setOpen_account_lock_money(String open_account_lock_money) {
            this.open_account_lock_money = open_account_lock_money;
        }

}
