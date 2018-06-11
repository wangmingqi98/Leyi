package com.leyijf.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class BackMoney {


    /**
     * response_code : 1
     * objects : {"loan_repay_list":[{"date":"2018-04-22","month_has_repay_money":"￥0.00","month_repay_money":"￥1,763.33","status":"未偿还"},{"date":"2018-05-22","month_has_repay_money":"￥0.00","month_repay_money":"￥1,763.33","status":"未偿还"},{"date":"2018-06-21","month_has_repay_money":"￥0.00","month_repay_money":"￥201,763.34","status":"未偿还"}]}
     */

    private int response_code;
    private ObjectsBean objects;

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public ObjectsBean getObjects() {
        return objects;
    }

    public void setObjects(ObjectsBean objects) {
        this.objects = objects;
    }

    public static class ObjectsBean {
        private List<LoanRepayListBean> loan_repay_list;

        public List<LoanRepayListBean> getLoan_repay_list() {
            return loan_repay_list;
        }

        public void setLoan_repay_list(List<LoanRepayListBean> loan_repay_list) {
            this.loan_repay_list = loan_repay_list;
        }

        public static class LoanRepayListBean {
            /**
             * date : 2018-04-22
             * month_has_repay_money : ￥0.00
             * month_repay_money : ￥1,763.33
             * status : 未偿还
             */

            private String date;
            private String month_has_repay_money;
            private String month_repay_money;
            private String status;

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
}
