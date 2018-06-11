package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/5/17.
 */

public class DealsLoanRepayBean {

    public static DealsLoanRepayBean objectFromData(String str) {

        return new Gson().fromJson(str, DealsLoanRepayBean.class);
    }

        private List<LoanRepayListBean> loan_repay_list;


        public List<LoanRepayListBean> getLoan_repay_list() {
            return loan_repay_list;
        }

        public void setLoan_repay_list(List<LoanRepayListBean> loan_repay_list) {
            this.loan_repay_list = loan_repay_list;
        }

        public static class LoanRepayListBean {
            /**
             * date : 2018-05-09
             * month_has_repay_money : ￥0.00
             * month_repay_money : ￥50,312.50
             * status : 未偿还
             */

            private String date;
            private String month_has_repay_money;
            private String month_repay_money;
            private String status;

            public static LoanRepayListBean objectFromData(String str) {

                return new Gson().fromJson(str, LoanRepayListBean.class);
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getMonth_has_repay_money() {
                return month_has_repay_money;
            }

            public void setMonth_has_repay_money(String month_has_repay_money) {
                this.month_has_repay_money = month_has_repay_money;
            }

            public String getMonth_repay_money() {
                return month_repay_money;
            }

            public void setMonth_repay_money(String month_repay_money) {
                this.month_repay_money = month_repay_money;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }

}
