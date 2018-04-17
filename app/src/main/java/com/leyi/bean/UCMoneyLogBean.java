package com.leyi.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/23.
 */

public class UCMoneyLogBean {


    /**
     * response_code : 1
     * objects : {"session_id":"giq8qhtng0i2c1okj37vf2p8r0","user_login_status":1,"item":[{"id":"10169","log_info":"[货抵贷1号],第5期,回报本息","log_time":"1521581140","log_admin_id":"35","log_user_id":"0","money":"10096.50","score":"0","point":"0","quota":"0.00","lock_money":"0.00","user_id":"1","log_time_format":"2018-03-21 13:25:40","money_format":"￥10,096.50","lock_money_format":"￥0.00"},{"id":"10160","log_info":"[货抵贷1号],第5期,回报本息","log_time":"1521581136","log_admin_id":"35","log_user_id":"0","money":"96.50","score":"0","point":"0","quota":"0.00","lock_money":"0.00","user_id":"1","log_time_format":"2018-03-21 13:25:36","money_format":"￥96.50","lock_money_format":"￥0.00"},{"id":"10151","log_info":"[货抵贷1号],第5期,回报本息","log_time":"1521581132","log_admin_id":"35","log_user_id":"0","money":"96.50","score":"0","point":"0","quota":"0.00","lock_money":"0.00","user_id":"1","log_time_format":"2018-03-21 13:25:32","money_format":"￥96.50","lock_money_format":"￥0.00"},{"id":"9670","log_info":"开户冻结资金返回","log_time":"1521083611","log_admin_id":"1","log_user_id":"0","money":"200.00","score":"0","point":"0","quota":"0.00","lock_money":"-200.00","user_id":"1","log_time_format":"2018-03-15 19:13:31","money_format":"￥200.00","lock_money_format":"￥-200.00"},{"id":"9650","log_info":"[保单贷1号]的投标,付款单号17","log_time":"1521083431","log_admin_id":"0","log_user_id":"1","money":"-10000.00","score":"0","point":"0","quota":"0.00","lock_money":"10000.00","user_id":"1","log_time_format":"2018-03-15 19:10:31","money_format":"￥-10,000.00","lock_money_format":"￥10,000.00"},{"id":"9640","log_info":"[货抵贷1号]的投标,付款单号10","log_time":"1521083020","log_admin_id":"0","log_user_id":"1","money":"-10000.00","score":"0","point":"0","quota":"0.00","lock_money":"10000.00","user_id":"1","log_time_format":"2018-03-15 19:03:40","money_format":"￥-10,000.00","lock_money_format":"￥10,000.00"},{"id":"9623","log_info":"[新手专享1号]的投标,付款单号4","log_time":"1521081480","log_admin_id":"0","log_user_id":"1","money":"-90000.00","score":"0","point":"0","quota":"0.00","lock_money":"90000.00","user_id":"1","log_time_format":"2018-03-15 18:38:00","money_format":"￥-90,000.00","lock_money_format":"￥90,000.00"},{"id":"9617","log_info":"管理员编辑帐户","log_time":"1521080665","log_admin_id":"35","log_user_id":"0","money":"100000.00","score":"0","point":"0","quota":"0.00","lock_money":"0.00","user_id":"1","log_time_format":"2018-03-15 18:24:25","money_format":"￥100,000.00","lock_money_format":"￥0.00"},{"id":"9611","log_info":"管理员编辑帐户","log_time":"1521080548","log_admin_id":"35","log_user_id":"0","money":"10000.00","score":"0","point":"0","quota":"0.00","lock_money":"0.00","user_id":"1","log_time_format":"2018-03-15 18:22:28","money_format":"￥10,000.00","lock_money_format":"￥0.00"},{"id":"9589","log_info":"提现失败退还","log_time":"1520970830","log_admin_id":"35","log_user_id":"0","money":"5.00","score":"0","point":"0","quota":"0.00","lock_money":"-5.00","user_id":"1","log_time_format":"2018-03-14 11:53:50","money_format":"￥5.00","lock_money_format":"￥-5.00"}],"page":{"page":1,"page_total":4,"page_size":"10"},"program_title":"操作日志"}
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
        /**
         * session_id : giq8qhtng0i2c1okj37vf2p8r0
         * user_login_status : 1
         * item : [{"id":"10169","log_info":"[货抵贷1号],第5期,回报本息","log_time":"1521581140","log_admin_id":"35","log_user_id":"0","money":"10096.50","score":"0","point":"0","quota":"0.00","lock_money":"0.00","user_id":"1","log_time_format":"2018-03-21 13:25:40","money_format":"￥10,096.50","lock_money_format":"￥0.00"},{"id":"10160","log_info":"[货抵贷1号],第5期,回报本息","log_time":"1521581136","log_admin_id":"35","log_user_id":"0","money":"96.50","score":"0","point":"0","quota":"0.00","lock_money":"0.00","user_id":"1","log_time_format":"2018-03-21 13:25:36","money_format":"￥96.50","lock_money_format":"￥0.00"},{"id":"10151","log_info":"[货抵贷1号],第5期,回报本息","log_time":"1521581132","log_admin_id":"35","log_user_id":"0","money":"96.50","score":"0","point":"0","quota":"0.00","lock_money":"0.00","user_id":"1","log_time_format":"2018-03-21 13:25:32","money_format":"￥96.50","lock_money_format":"￥0.00"},{"id":"9670","log_info":"开户冻结资金返回","log_time":"1521083611","log_admin_id":"1","log_user_id":"0","money":"200.00","score":"0","point":"0","quota":"0.00","lock_money":"-200.00","user_id":"1","log_time_format":"2018-03-15 19:13:31","money_format":"￥200.00","lock_money_format":"￥-200.00"},{"id":"9650","log_info":"[保单贷1号]的投标,付款单号17","log_time":"1521083431","log_admin_id":"0","log_user_id":"1","money":"-10000.00","score":"0","point":"0","quota":"0.00","lock_money":"10000.00","user_id":"1","log_time_format":"2018-03-15 19:10:31","money_format":"￥-10,000.00","lock_money_format":"￥10,000.00"},{"id":"9640","log_info":"[货抵贷1号]的投标,付款单号10","log_time":"1521083020","log_admin_id":"0","log_user_id":"1","money":"-10000.00","score":"0","point":"0","quota":"0.00","lock_money":"10000.00","user_id":"1","log_time_format":"2018-03-15 19:03:40","money_format":"￥-10,000.00","lock_money_format":"￥10,000.00"},{"id":"9623","log_info":"[新手专享1号]的投标,付款单号4","log_time":"1521081480","log_admin_id":"0","log_user_id":"1","money":"-90000.00","score":"0","point":"0","quota":"0.00","lock_money":"90000.00","user_id":"1","log_time_format":"2018-03-15 18:38:00","money_format":"￥-90,000.00","lock_money_format":"￥90,000.00"},{"id":"9617","log_info":"管理员编辑帐户","log_time":"1521080665","log_admin_id":"35","log_user_id":"0","money":"100000.00","score":"0","point":"0","quota":"0.00","lock_money":"0.00","user_id":"1","log_time_format":"2018-03-15 18:24:25","money_format":"￥100,000.00","lock_money_format":"￥0.00"},{"id":"9611","log_info":"管理员编辑帐户","log_time":"1521080548","log_admin_id":"35","log_user_id":"0","money":"10000.00","score":"0","point":"0","quota":"0.00","lock_money":"0.00","user_id":"1","log_time_format":"2018-03-15 18:22:28","money_format":"￥10,000.00","lock_money_format":"￥0.00"},{"id":"9589","log_info":"提现失败退还","log_time":"1520970830","log_admin_id":"35","log_user_id":"0","money":"5.00","score":"0","point":"0","quota":"0.00","lock_money":"-5.00","user_id":"1","log_time_format":"2018-03-14 11:53:50","money_format":"￥5.00","lock_money_format":"￥-5.00"}]
         * page : {"page":1,"page_total":4,"page_size":"10"}
         * program_title : 操作日志
         */

        private String session_id;
        private int user_login_status;
        private PageBean page;
        private String program_title;
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

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public String getProgram_title() {
            return program_title;
        }

        public void setProgram_title(String program_title) {
            this.program_title = program_title;
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
             * page_total : 4
             * page_size : 10
             */

            private int page;
            private int page_total;
            private String page_size;

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

            public String getPage_size() {
                return page_size;
            }

            public void setPage_size(String page_size) {
                this.page_size = page_size;
            }
        }

        public static class ItemBean {
            /**
             * id : 10169
             * log_info : [货抵贷1号],第5期,回报本息
             * log_time : 1521581140
             * log_admin_id : 35
             * log_user_id : 0
             * money : 10096.50
             * score : 0
             * point : 0
             * quota : 0.00
             * lock_money : 0.00
             * user_id : 1
             * log_time_format : 2018-03-21 13:25:40
             * money_format : ￥10,096.50
             * lock_money_format : ￥0.00
             */

            private String id;
            private String log_info;
            private String log_time;
            private String log_admin_id;
            private String log_user_id;
            private String money;
            private String score;
            private String point;
            private String quota;
            private String lock_money;
            private String user_id;
            private String log_time_format;
            private String money_format;
            private String lock_money_format;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLog_info() {
                return log_info;
            }

            public void setLog_info(String log_info) {
                this.log_info = log_info;
            }

            public String getLog_time() {
                return log_time;
            }

            public void setLog_time(String log_time) {
                this.log_time = log_time;
            }

            public String getLog_admin_id() {
                return log_admin_id;
            }

            public void setLog_admin_id(String log_admin_id) {
                this.log_admin_id = log_admin_id;
            }

            public String getLog_user_id() {
                return log_user_id;
            }

            public void setLog_user_id(String log_user_id) {
                this.log_user_id = log_user_id;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getPoint() {
                return point;
            }

            public void setPoint(String point) {
                this.point = point;
            }

            public String getQuota() {
                return quota;
            }

            public void setQuota(String quota) {
                this.quota = quota;
            }

            public String getLock_money() {
                return lock_money;
            }

            public void setLock_money(String lock_money) {
                this.lock_money = lock_money;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getLog_time_format() {
                return log_time_format;
            }

            public void setLog_time_format(String log_time_format) {
                this.log_time_format = log_time_format;
            }

            public String getMoney_format() {
                return money_format;
            }

            public void setMoney_format(String money_format) {
                this.money_format = money_format;
            }

            public String getLock_money_format() {
                return lock_money_format;
            }

            public void setLock_money_format(String lock_money_format) {
                this.lock_money_format = lock_money_format;
            }
        }
    }
}
