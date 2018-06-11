package com.leyijf.bean;

import com.google.gson.Gson;

/**
 * Created by wmq on 2018/5/30.
 */

public class CurrentMoneyBean {

    public static CurrentMoneyBean objectFromData(String str) {

        return new Gson().fromJson(str, CurrentMoneyBean.class);
    }

        /**
         * current_amount : 1.026671245E7
         * show_err : 提交提现申请成功，请等待耐心等待！
         */

        private double current_amount;
        private String show_err;


        public double getCurrent_amount() {
            return current_amount;
        }

        public void setCurrent_amount(double current_amount) {
            this.current_amount = current_amount;
        }

        public String getShow_err() {
            return show_err;
        }

        public void setShow_err(String show_err) {
            this.show_err = show_err;
        }

}
