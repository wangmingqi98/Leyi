package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/5/4.
 */

public class MyInvestBean {

    public static MyInvestBean objectFromData(String str) {

        return new Gson().fromJson(str, MyInvestBean.class);
    }

        /**
         * session_id : ua7fbg1c7fsv7aaajl7hgge147
         * user_login_status : 1
         * status : 0
         * item : [{"id":"61","name":"产业链16号","user_id":"44","create_time":1524561643,"repay_time":"1","rate":"9.08","borrow_amount":"50000","repay_time_type":"1","repay_start_time":0,"deal_status":"1","uloadtype":"0","loantype":"1","user_loan_manage_fee":"0","user_loan_interest_manage_fee":"0","end_time":0,"true_repay_time":0,"user_name":"xvshanxiang","load_id":"596","money":"102","rebate_money":"0.00","is_auto":"0","jxq":null,"repay_money":null,"yuqi_money":"0.77"}]
         * page : {"page":1,"page_total":2,"page_size":5}
         */

        private String session_id;
        private int user_login_status;
        private int status;
        private PageBean page;
        private List<ItemBean> item;


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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public static class PageBean {
            /**
             * page : 1
             * page_total : 2
             * page_size : 5
             */

            private int page;
            private int page_total;
            private int page_size;

            public static PageBean objectFromData(String str) {

                return new Gson().fromJson(str, PageBean.class);
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getPage_total() {
                return page_total;
            }

            public void setPage_total(int page_total) {
                this.page_total = page_total;
            }

            public int getPage_size() {
                return page_size;
            }

            public void setPage_size(int page_size) {
                this.page_size = page_size;
            }
        }

        public static class ItemBean {
            /**
             * id : 61
             * name : 产业链16号
             * user_id : 44
             * create_time : 1524561643
             * repay_time : 1
             * rate : 9.08
             * borrow_amount : 50000
             * repay_time_type : 1
             * repay_start_time : 0
             * deal_status : 1
             * uloadtype : 0
             * loantype : 1
             * user_loan_manage_fee : 0
             * user_loan_interest_manage_fee : 0
             * end_time : 0
             * true_repay_time : 0
             * user_name : xvshanxiang
             * load_id : 596
             * money : 102
             * rebate_money : 0.00
             * is_auto : 0
             * jxq : null
             * repay_money : null
             * yuqi_money : 0.77
             */

            private String id;
            private String name;
            private String user_id;
            private int create_time;
            private String repay_time;
            private String rate;
            private String borrow_amount;
            private String repay_time_type;
            private int repay_start_time;
            private String deal_status;
            private String uloadtype;
            private String loantype;
            private String user_loan_manage_fee;
            private String user_loan_interest_manage_fee;
            private int end_time;
            private int true_repay_time;
            private String user_name;
            private String load_id;
            private String money;
            private String rebate_money;
            private String is_auto;
            private Object jxq;
            private Object repay_money;
            private String yuqi_money;
            private String loantype_format;

            public static ItemBean objectFromData(String str) {

                return new Gson().fromJson(str, ItemBean.class);
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public String getRepay_time() {
                return repay_time;
            }

            public void setRepay_time(String repay_time) {
                this.repay_time = repay_time;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public String getBorrow_amount() {
                return borrow_amount;
            }

            public void setBorrow_amount(String borrow_amount) {
                this.borrow_amount = borrow_amount;
            }

            public String getRepay_time_type() {
                return repay_time_type;
            }

            public void setRepay_time_type(String repay_time_type) {
                this.repay_time_type = repay_time_type;
            }

            public int getRepay_start_time() {
                return repay_start_time;
            }

            public void setRepay_start_time(int repay_start_time) {
                this.repay_start_time = repay_start_time;
            }

            public String getDeal_status() {
                return deal_status;
            }

            public void setDeal_status(String deal_status) {
                this.deal_status = deal_status;
            }

            public String getUloadtype() {
                return uloadtype;
            }

            public void setUloadtype(String uloadtype) {
                this.uloadtype = uloadtype;
            }

            public String getLoantype() {
                return loantype;
            }

            public void setLoantype(String loantype) {
                this.loantype = loantype;
            }

            public String getUser_loan_manage_fee() {
                return user_loan_manage_fee;
            }

            public void setUser_loan_manage_fee(String user_loan_manage_fee) {
                this.user_loan_manage_fee = user_loan_manage_fee;
            }

            public String getUser_loan_interest_manage_fee() {
                return user_loan_interest_manage_fee;
            }

            public void setUser_loan_interest_manage_fee(String user_loan_interest_manage_fee) {
                this.user_loan_interest_manage_fee = user_loan_interest_manage_fee;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public int getTrue_repay_time() {
                return true_repay_time;
            }

            public void setTrue_repay_time(int true_repay_time) {
                this.true_repay_time = true_repay_time;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getLoad_id() {
                return load_id;
            }

            public void setLoad_id(String load_id) {
                this.load_id = load_id;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getRebate_money() {
                return rebate_money;
            }

            public void setRebate_money(String rebate_money) {
                this.rebate_money = rebate_money;
            }

            public String getIs_auto() {
                return is_auto;
            }

            public void setIs_auto(String is_auto) {
                this.is_auto = is_auto;
            }

            public Object getJxq() {
                return jxq;
            }

            public void setJxq(Object jxq) {
                this.jxq = jxq;
            }

            public Object getRepay_money() {
                return repay_money;
            }

            public void setRepay_money(Object repay_money) {
                this.repay_money = repay_money;
            }

            public String getYuqi_money() {
                return yuqi_money;
            }

            public void setYuqi_money(String yuqi_money) {
                this.yuqi_money = yuqi_money;
            }

            public String getLoantype_format() {
                return loantype_format;
            }

            public void setLoantype_format(String loantype_format) {
                this.loantype_format = loantype_format;
            }
        }

}
