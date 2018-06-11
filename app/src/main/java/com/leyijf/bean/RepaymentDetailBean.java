package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/5/7.
 */

public class RepaymentDetailBean {


    public static RepaymentDetailBean objectFromData(String str) {

        return new Gson().fromJson(str, RepaymentDetailBean.class);
    }

        /**
         * session_id : 27cnufd0nikaoc6b7akv2ihbl5
         * user_login_status : 1
         * loan_list : [{"get_manage":"0","l_key":"0","l_key_index":"1","repay_id":"219","impose_day":0,"repay_day":"1528185600","month_repay_money":0,"true_repay_time":"1525634735","month_manage_money":"0.00","mortgage_fee":0,"manage_money_rebate":0,"has_repay":"1","month_has_repay_money":"339228.32","impose_all_money":0,"status":2,"impose_money":"0.00","manage_impose_money":"0.00","month_has_repay_money_all":339228.32,"month_need_all_repay_money":0,"repay_day_format":"2018-06-06","month_has_repay_money_all_format":"￥339,228.32","month_need_all_repay_money_format":"￥0.00","month_repay_money_format":"￥0.00","month_manage_money_format":"￥0.00","mortgage_fee_format":"￥0.00","manage_money_rebate_format":"￥0.00","manage_money_impose_format":"￥0.00","impose_money_format":"￥0.00","impose_all_money_format":"￥0.00","status_format":"正常还款","total_principal_interest ":339157.08,"total_impose_money":0,"total_expected_earnings":8814.82,"total_self_money":330342,"total_actual_money":339156.82},{"get_manage":"0","l_key":"1","l_key_index":"2","repay_id":"220","impose_day":0,"repay_day":"1530777600","month_repay_money":"339228.31","true_repay_time":"0","month_manage_money":0,"mortgage_fee":0,"manage_money_rebate":0,"has_repay":"0","month_has_repay_money":0,"impose_all_money":0,"status":2,"month_has_repay_money_all":"10.00","month_need_all_repay_money":339228.31,"repay_day_format":"2018-07-06","month_has_repay_money_all_format":"￥10.00","month_need_all_repay_money_format":"￥339,228.31","month_repay_money_format":"￥339,228.31","month_manage_money_format":"￥0.00","mortgage_fee_format":"￥0.00","manage_money_rebate_format":"￥0.00","manage_money_impose_format":"￥0.00","impose_money_format":"￥0.00","impose_all_money_format":"￥0.00","status_format":"待还","total_principal_interest ":339157.08,"total_impose_money":10,"total_expected_earnings":5912.29,"total_self_money":333254,"total_actual_money":339166.29,"flag":1},{"get_manage":"0","l_key":"2","l_key_index":"3","repay_id":"221","impose_day":0,"repay_day":"1533369600","month_repay_money":"339228.31","true_repay_time":"0","month_manage_money":0,"mortgage_fee":0,"manage_money_rebate":0,"has_repay":"0","month_has_repay_money":0,"impose_all_money":0,"status":2,"month_has_repay_money_all":"0.00","month_need_all_repay_money":339228.31,"repay_day_format":"2018-08-05","month_has_repay_money_all_format":"￥0.00","month_need_all_repay_money_format":"￥339,228.31","month_repay_money_format":"￥339,228.31","month_manage_money_format":"￥0.00","mortgage_fee_format":"￥0.00","manage_money_rebate_format":"￥0.00","manage_money_impose_format":"￥0.00","impose_money_format":"￥0.00","impose_all_money_format":"￥0.00","status_format":"待还","total_principal_interest ":339157.05,"total_impose_money":0,"total_expected_earnings":2964.08,"total_self_money":336192,"total_actual_money":339156.08}]
         * current_l_key_index : {"get_manage":"0","l_key":"1","l_key_index":"2","repay_id":"220","impose_day":0,"repay_day":"1530777600","month_repay_money":"339228.31","true_repay_time":"0","month_manage_money":0,"mortgage_fee":0,"manage_money_rebate":0,"has_repay":"0","month_has_repay_money":0,"impose_all_money":0,"status":2,"month_has_repay_money_all":"10.00","month_need_all_repay_money":339228.31,"repay_day_format":"2018-07-06","month_has_repay_money_all_format":"￥10.00","month_need_all_repay_money_format":"￥339,228.31","month_repay_money_format":"￥339,228.31","month_manage_money_format":"￥0.00","mortgage_fee_format":"￥0.00","manage_money_rebate_format":"￥0.00","manage_money_impose_format":"￥0.00","impose_money_format":"￥0.00","impose_all_money_format":"￥0.00","status_format":"待还","total_principal_interest ":339157.08,"total_impose_money":10,"total_expected_earnings":5912.29,"total_self_money":333254,"total_actual_money":339166.29,"flag":1}
         * agree_url : http://localhost/index.php?ctl=deal_contract&act=contract&is_sj=1&id=76
         * show_err :
         * program_title : 我的投资---还款详情
         */

        private String session_id;
        private int user_login_status;
        private CurrentLKeyIndexBean current_l_key_index;
        private String agree_url;
        private String show_err;
        private String program_title;
        private List<LoanListBean> loan_list;


        public String getSession_id() {
            return session_id;
        }

        public void setSession_id(String session_id) {
            this.session_id = session_id;
        }

        public int getUser_login_status() {
            return user_login_status;
        }

        public void setUser_login_status(int user_login_status) {
            this.user_login_status = user_login_status;
        }

        public CurrentLKeyIndexBean getCurrent_l_key_index() {
            return current_l_key_index;
        }

        public void setCurrent_l_key_index(CurrentLKeyIndexBean current_l_key_index) {
            this.current_l_key_index = current_l_key_index;
        }

        public String getAgree_url() {
            return agree_url;
        }

        public void setAgree_url(String agree_url) {
            this.agree_url = agree_url;
        }

        public String getShow_err() {
            return show_err;
        }

        public void setShow_err(String show_err) {
            this.show_err = show_err;
        }

        public String getProgram_title() {
            return program_title;
        }

        public void setProgram_title(String program_title) {
            this.program_title = program_title;
        }

        public List<LoanListBean> getLoan_list() {
            return loan_list;
        }

        public void setLoan_list(List<LoanListBean> loan_list) {
            this.loan_list = loan_list;
        }

        public static class CurrentLKeyIndexBean {
            /**
             * get_manage : 0
             * l_key : 1
             * l_key_index : 2
             * repay_id : 220
             * impose_day : 0
             * repay_day : 1530777600
             * month_repay_money : 339228.31
             * true_repay_time : 0
             * month_manage_money : 0
             * mortgage_fee : 0
             * manage_money_rebate : 0
             * has_repay : 0
             * month_has_repay_money : 0
             * impose_all_money : 0
             * status : 2
             * month_has_repay_money_all : 10.00
             * month_need_all_repay_money : 339228.31
             * repay_day_format : 2018-07-06
             * month_has_repay_money_all_format : ￥10.00
             * month_need_all_repay_money_format : ￥339,228.31
             * month_repay_money_format : ￥339,228.31
             * month_manage_money_format : ￥0.00
             * mortgage_fee_format : ￥0.00
             * manage_money_rebate_format : ￥0.00
             * manage_money_impose_format : ￥0.00
             * impose_money_format : ￥0.00
             * impose_all_money_format : ￥0.00
             * status_format : 待还
             * total_principal_interest  : 339157.08
             * total_impose_money : 10
             * total_expected_earnings : 5912.29
             * total_self_money : 333254
             * total_actual_money : 339166.29
             * flag : 1
             */

            private String get_manage;
            private String l_key;
            private String l_key_index;
            private String repay_id;
            private int impose_day;
            private String repay_day;
            private String month_repay_money;
            private String true_repay_time;
            private int month_manage_money;
            private int mortgage_fee;
            private int manage_money_rebate;
            private String has_repay;
            private int month_has_repay_money;
            private int impose_all_money;
            private int status;
            private String month_has_repay_money_all;
            private double month_need_all_repay_money;
            private String repay_day_format;
            private String month_has_repay_money_all_format;
            private String month_need_all_repay_money_format;
            private String month_repay_money_format;
            private String month_manage_money_format;
            private String mortgage_fee_format;
            private String manage_money_rebate_format;
            private String manage_money_impose_format;
            private String impose_money_format;
            private String impose_all_money_format;
            private String status_format;
            private double total_principal_interest;
            private int total_impose_money;
            private double total_expected_earnings;
            private int total_self_money;
            private double total_actual_money;
            private int flag;

            public static CurrentLKeyIndexBean objectFromData(String str) {

                return new Gson().fromJson(str, CurrentLKeyIndexBean.class);
            }

            public String getGet_manage() {
                return get_manage;
            }

            public void setGet_manage(String get_manage) {
                this.get_manage = get_manage;
            }

            public String getL_key() {
                return l_key;
            }

            public void setL_key(String l_key) {
                this.l_key = l_key;
            }

            public String getL_key_index() {
                return l_key_index;
            }

            public void setL_key_index(String l_key_index) {
                this.l_key_index = l_key_index;
            }

            public String getRepay_id() {
                return repay_id;
            }

            public void setRepay_id(String repay_id) {
                this.repay_id = repay_id;
            }

            public int getImpose_day() {
                return impose_day;
            }

            public void setImpose_day(int impose_day) {
                this.impose_day = impose_day;
            }

            public String getRepay_day() {
                return repay_day;
            }

            public void setRepay_day(String repay_day) {
                this.repay_day = repay_day;
            }

            public String getMonth_repay_money() {
                return month_repay_money;
            }

            public void setMonth_repay_money(String month_repay_money) {
                this.month_repay_money = month_repay_money;
            }

            public String getTrue_repay_time() {
                return true_repay_time;
            }

            public void setTrue_repay_time(String true_repay_time) {
                this.true_repay_time = true_repay_time;
            }

            public int getMonth_manage_money() {
                return month_manage_money;
            }

            public void setMonth_manage_money(int month_manage_money) {
                this.month_manage_money = month_manage_money;
            }

            public int getMortgage_fee() {
                return mortgage_fee;
            }

            public void setMortgage_fee(int mortgage_fee) {
                this.mortgage_fee = mortgage_fee;
            }

            public int getManage_money_rebate() {
                return manage_money_rebate;
            }

            public void setManage_money_rebate(int manage_money_rebate) {
                this.manage_money_rebate = manage_money_rebate;
            }

            public String getHas_repay() {
                return has_repay;
            }

            public void setHas_repay(String has_repay) {
                this.has_repay = has_repay;
            }

            public int getMonth_has_repay_money() {
                return month_has_repay_money;
            }

            public void setMonth_has_repay_money(int month_has_repay_money) {
                this.month_has_repay_money = month_has_repay_money;
            }

            public int getImpose_all_money() {
                return impose_all_money;
            }

            public void setImpose_all_money(int impose_all_money) {
                this.impose_all_money = impose_all_money;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getMonth_has_repay_money_all() {
                return month_has_repay_money_all;
            }

            public void setMonth_has_repay_money_all(String month_has_repay_money_all) {
                this.month_has_repay_money_all = month_has_repay_money_all;
            }

            public double getMonth_need_all_repay_money() {
                return month_need_all_repay_money;
            }

            public void setMonth_need_all_repay_money(double month_need_all_repay_money) {
                this.month_need_all_repay_money = month_need_all_repay_money;
            }

            public String getRepay_day_format() {
                return repay_day_format;
            }

            public void setRepay_day_format(String repay_day_format) {
                this.repay_day_format = repay_day_format;
            }

            public String getMonth_has_repay_money_all_format() {
                return month_has_repay_money_all_format;
            }

            public void setMonth_has_repay_money_all_format(String month_has_repay_money_all_format) {
                this.month_has_repay_money_all_format = month_has_repay_money_all_format;
            }

            public String getMonth_need_all_repay_money_format() {
                return month_need_all_repay_money_format;
            }

            public void setMonth_need_all_repay_money_format(String month_need_all_repay_money_format) {
                this.month_need_all_repay_money_format = month_need_all_repay_money_format;
            }

            public String getMonth_repay_money_format() {
                return month_repay_money_format;
            }

            public void setMonth_repay_money_format(String month_repay_money_format) {
                this.month_repay_money_format = month_repay_money_format;
            }

            public String getMonth_manage_money_format() {
                return month_manage_money_format;
            }

            public void setMonth_manage_money_format(String month_manage_money_format) {
                this.month_manage_money_format = month_manage_money_format;
            }

            public String getMortgage_fee_format() {
                return mortgage_fee_format;
            }

            public void setMortgage_fee_format(String mortgage_fee_format) {
                this.mortgage_fee_format = mortgage_fee_format;
            }

            public String getManage_money_rebate_format() {
                return manage_money_rebate_format;
            }

            public void setManage_money_rebate_format(String manage_money_rebate_format) {
                this.manage_money_rebate_format = manage_money_rebate_format;
            }

            public String getManage_money_impose_format() {
                return manage_money_impose_format;
            }

            public void setManage_money_impose_format(String manage_money_impose_format) {
                this.manage_money_impose_format = manage_money_impose_format;
            }

            public String getImpose_money_format() {
                return impose_money_format;
            }

            public void setImpose_money_format(String impose_money_format) {
                this.impose_money_format = impose_money_format;
            }

            public String getImpose_all_money_format() {
                return impose_all_money_format;
            }

            public void setImpose_all_money_format(String impose_all_money_format) {
                this.impose_all_money_format = impose_all_money_format;
            }

            public String getStatus_format() {
                return status_format;
            }

            public void setStatus_format(String status_format) {
                this.status_format = status_format;
            }

            public double getTotal_principal_interest() {
                return total_principal_interest;
            }

            public void setTotal_principal_interest(double total_principal_interest) {
                this.total_principal_interest = total_principal_interest;
            }

            public int getTotal_impose_money() {
                return total_impose_money;
            }

            public void setTotal_impose_money(int total_impose_money) {
                this.total_impose_money = total_impose_money;
            }

            public double getTotal_expected_earnings() {
                return total_expected_earnings;
            }

            public void setTotal_expected_earnings(double total_expected_earnings) {
                this.total_expected_earnings = total_expected_earnings;
            }

            public int getTotal_self_money() {
                return total_self_money;
            }

            public void setTotal_self_money(int total_self_money) {
                this.total_self_money = total_self_money;
            }

            public double getTotal_actual_money() {
                return total_actual_money;
            }

            public void setTotal_actual_money(double total_actual_money) {
                this.total_actual_money = total_actual_money;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }
        }

        public static class LoanListBean {
            /**
             * get_manage : 0
             * l_key : 0
             * l_key_index : 1
             * repay_id : 219
             * impose_day : 0
             * repay_day : 1528185600
             * month_repay_money : 0
             * true_repay_time : 1525634735
             * month_manage_money : 0.00
             * mortgage_fee : 0
             * manage_money_rebate : 0
             * has_repay : 1
             * month_has_repay_money : 339228.32
             * impose_all_money : 0
             * status : 2
             * impose_money : 0.00
             * manage_impose_money : 0.00
             * month_has_repay_money_all : 339228.32
             * month_need_all_repay_money : 0
             * repay_day_format : 2018-06-06
             * month_has_repay_money_all_format : ￥339,228.32
             * month_need_all_repay_money_format : ￥0.00
             * month_repay_money_format : ￥0.00
             * month_manage_money_format : ￥0.00
             * mortgage_fee_format : ￥0.00
             * manage_money_rebate_format : ￥0.00
             * manage_money_impose_format : ￥0.00
             * impose_money_format : ￥0.00
             * impose_all_money_format : ￥0.00
             * status_format : 正常还款
             * total_principal_interest  : 339157.08
             * total_impose_money : 0
             * total_expected_earnings : 8814.82
             * total_self_money : 330342
             * total_actual_money : 339156.82
             * flag : 1
             */

            private String get_manage;
            private String l_key;
            private String l_key_index;
            private String repay_id;
            private int impose_day;
            private String repay_day;
            private int month_repay_money;
            private String true_repay_time;
            private String month_manage_money;
            private int mortgage_fee;
            private int manage_money_rebate;
            private String has_repay;
            private String month_has_repay_money;
            private int impose_all_money;
            private int status;
            private String impose_money;
            private String manage_impose_money;
            private double month_has_repay_money_all;
            private int month_need_all_repay_money;
            private String repay_day_format;
            private String month_has_repay_money_all_format;
            private String month_need_all_repay_money_format;
            private String month_repay_money_format;
            private String month_manage_money_format;
            private String mortgage_fee_format;
            private String manage_money_rebate_format;
            private String manage_money_impose_format;
            private String impose_money_format;
            private String impose_all_money_format;
            private String status_format;
            private double total_principal_interest;
            private int total_impose_money;
            private double total_expected_earnings;
            private int total_self_money;
            private double total_actual_money;
            private int flag;

            public static LoanListBean objectFromData(String str) {

                return new Gson().fromJson(str, LoanListBean.class);
            }

            public String getGet_manage() {
                return get_manage;
            }

            public void setGet_manage(String get_manage) {
                this.get_manage = get_manage;
            }

            public String getL_key() {
                return l_key;
            }

            public void setL_key(String l_key) {
                this.l_key = l_key;
            }

            public String getL_key_index() {
                return l_key_index;
            }

            public void setL_key_index(String l_key_index) {
                this.l_key_index = l_key_index;
            }

            public String getRepay_id() {
                return repay_id;
            }

            public void setRepay_id(String repay_id) {
                this.repay_id = repay_id;
            }

            public int getImpose_day() {
                return impose_day;
            }

            public void setImpose_day(int impose_day) {
                this.impose_day = impose_day;
            }

            public String getRepay_day() {
                return repay_day;
            }

            public void setRepay_day(String repay_day) {
                this.repay_day = repay_day;
            }

            public int getMonth_repay_money() {
                return month_repay_money;
            }

            public void setMonth_repay_money(int month_repay_money) {
                this.month_repay_money = month_repay_money;
            }

            public String getTrue_repay_time() {
                return true_repay_time;
            }

            public void setTrue_repay_time(String true_repay_time) {
                this.true_repay_time = true_repay_time;
            }

            public String getMonth_manage_money() {
                return month_manage_money;
            }

            public void setMonth_manage_money(String month_manage_money) {
                this.month_manage_money = month_manage_money;
            }

            public int getMortgage_fee() {
                return mortgage_fee;
            }

            public void setMortgage_fee(int mortgage_fee) {
                this.mortgage_fee = mortgage_fee;
            }

            public int getManage_money_rebate() {
                return manage_money_rebate;
            }

            public void setManage_money_rebate(int manage_money_rebate) {
                this.manage_money_rebate = manage_money_rebate;
            }

            public String getHas_repay() {
                return has_repay;
            }

            public void setHas_repay(String has_repay) {
                this.has_repay = has_repay;
            }

            public String getMonth_has_repay_money() {
                return month_has_repay_money;
            }

            public void setMonth_has_repay_money(String month_has_repay_money) {
                this.month_has_repay_money = month_has_repay_money;
            }

            public int getImpose_all_money() {
                return impose_all_money;
            }

            public void setImpose_all_money(int impose_all_money) {
                this.impose_all_money = impose_all_money;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getImpose_money() {
                return impose_money;
            }

            public void setImpose_money(String impose_money) {
                this.impose_money = impose_money;
            }

            public String getManage_impose_money() {
                return manage_impose_money;
            }

            public void setManage_impose_money(String manage_impose_money) {
                this.manage_impose_money = manage_impose_money;
            }

            public double getMonth_has_repay_money_all() {
                return month_has_repay_money_all;
            }

            public void setMonth_has_repay_money_all(double month_has_repay_money_all) {
                this.month_has_repay_money_all = month_has_repay_money_all;
            }

            public int getMonth_need_all_repay_money() {
                return month_need_all_repay_money;
            }

            public void setMonth_need_all_repay_money(int month_need_all_repay_money) {
                this.month_need_all_repay_money = month_need_all_repay_money;
            }

            public String getRepay_day_format() {
                return repay_day_format;
            }

            public void setRepay_day_format(String repay_day_format) {
                this.repay_day_format = repay_day_format;
            }

            public String getMonth_has_repay_money_all_format() {
                return month_has_repay_money_all_format;
            }

            public void setMonth_has_repay_money_all_format(String month_has_repay_money_all_format) {
                this.month_has_repay_money_all_format = month_has_repay_money_all_format;
            }

            public String getMonth_need_all_repay_money_format() {
                return month_need_all_repay_money_format;
            }

            public void setMonth_need_all_repay_money_format(String month_need_all_repay_money_format) {
                this.month_need_all_repay_money_format = month_need_all_repay_money_format;
            }

            public String getMonth_repay_money_format() {
                return month_repay_money_format;
            }

            public void setMonth_repay_money_format(String month_repay_money_format) {
                this.month_repay_money_format = month_repay_money_format;
            }

            public String getMonth_manage_money_format() {
                return month_manage_money_format;
            }

            public void setMonth_manage_money_format(String month_manage_money_format) {
                this.month_manage_money_format = month_manage_money_format;
            }

            public String getMortgage_fee_format() {
                return mortgage_fee_format;
            }

            public void setMortgage_fee_format(String mortgage_fee_format) {
                this.mortgage_fee_format = mortgage_fee_format;
            }

            public String getManage_money_rebate_format() {
                return manage_money_rebate_format;
            }

            public void setManage_money_rebate_format(String manage_money_rebate_format) {
                this.manage_money_rebate_format = manage_money_rebate_format;
            }

            public String getManage_money_impose_format() {
                return manage_money_impose_format;
            }

            public void setManage_money_impose_format(String manage_money_impose_format) {
                this.manage_money_impose_format = manage_money_impose_format;
            }

            public String getImpose_money_format() {
                return impose_money_format;
            }

            public void setImpose_money_format(String impose_money_format) {
                this.impose_money_format = impose_money_format;
            }

            public String getImpose_all_money_format() {
                return impose_all_money_format;
            }

            public void setImpose_all_money_format(String impose_all_money_format) {
                this.impose_all_money_format = impose_all_money_format;
            }

            public String getStatus_format() {
                return status_format;
            }

            public void setStatus_format(String status_format) {
                this.status_format = status_format;
            }

            public double getTotal_principal_interest() {
                return total_principal_interest;
            }

            public void setTotal_principal_interest(double total_principal_interest) {
                this.total_principal_interest = total_principal_interest;
            }

            public int getTotal_impose_money() {
                return total_impose_money;
            }

            public void setTotal_impose_money(int total_impose_money) {
                this.total_impose_money = total_impose_money;
            }

            public double getTotal_expected_earnings() {
                return total_expected_earnings;
            }

            public void setTotal_expected_earnings(double total_expected_earnings) {
                this.total_expected_earnings = total_expected_earnings;
            }

            public int getTotal_self_money() {
                return total_self_money;
            }

            public void setTotal_self_money(int total_self_money) {
                this.total_self_money = total_self_money;
            }

            public double getTotal_actual_money() {
                return total_actual_money;
            }

            public void setTotal_actual_money(double total_actual_money) {
                this.total_actual_money = total_actual_money;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }
        }

}
