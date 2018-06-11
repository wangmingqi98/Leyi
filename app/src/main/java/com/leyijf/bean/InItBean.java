package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/3/14.
 */

public class InItBean {


    public static InItBean objectFromData(String str) {

        return new Gson().fromJson(str, InItBean.class);
    }

        /**
         * recommand_xinyong : [{"deal_id":"49","name":"货抵贷15号","sub_name":"货抵贷15号","cate_id":"1","rate":"10.58","repay_time":"3","repay_time_type":"月","min_loan_money":"100","max_loan_money":"0","portion":"2000","max_portion":"0","borrow_amount":"200000","buy_count":"8","load_money":"102866","remain_money":"97134","transfer_day":"0","loantype":"1","uloadtype":"0","enddate":"10","start_time":1523879150,"deal_status":"1","user_loan_manage_fee":"0","user_loan_interest_manage_fee":"0","user_bid_rebate":"0","use_ecv":"1","use_rate":"1","deal_labels":[],"sales_label":"0","is_new":"0","extra_rate":"0.00","icon":"","repay_start_time":0,"progress_point":"51","current_time":1524576793,"date_of_value":"T(成交日+1)","loantype_format":"付息还本","end_time":1524743150,"remain_time":166357,"profit":5290,"bdxq_url":"http://m.leyibank.com#/underlyingDetailsApp?deal_id=49"}]
         * recommand_gongying : [{"deal_id":"61","name":"产业链16号","sub_name":"产业链16号","cate_id":"2","rate":"9.08","repay_time":"1","repay_time_type":"月","min_loan_money":"100","max_loan_money":"0","portion":"500","max_portion":"0","borrow_amount":"50000","buy_count":"9","load_money":"37732","remain_money":"12268","transfer_day":"0","loantype":"1","uloadtype":"0","enddate":"3","start_time":1524391680,"deal_status":"1","user_loan_manage_fee":"0","user_loan_interest_manage_fee":"0","user_bid_rebate":"0","use_ecv":"1","use_rate":"1","deal_labels":[],"sales_label":"0","is_new":"0","extra_rate":"0.00","icon":"","repay_start_time":0,"progress_point":"75","current_time":1524576793,"date_of_value":"T(成交日+1)","loantype_format":"付息还本","end_time":1524650880,"remain_time":74087,"profit":378.33,"bdxq_url":"http://m.leyibank.com#/underlyingDetailsApp?deal_id=61"}]
         * newer : [{"deal_id":"73","name":"新手专享12号","sub_name":"新手专享12号","cate_id":"1","rate":"15.00","repay_time":"15","repay_time_type":"天","min_loan_money":"100","max_loan_money":"0","portion":"500","max_portion":"0","borrow_amount":"50000","buy_count":"3","load_money":"50000","remain_money":"0","transfer_day":"0","loantype":"2","uloadtype":"0","enddate":"3","start_time":1524535940,"deal_status":"4","user_loan_manage_fee":"0","user_loan_interest_manage_fee":"0","user_bid_rebate":"0","use_ecv":"0","use_rate":"0","deal_labels":[],"sales_label":"0","is_new":"1","extra_rate":"0.00","icon":"","repay_start_time":1524499200,"progress_point":"100","current_time":1524576793,"date_of_value":"T(成交日+1)","loantype_format":"到期还本息","end_time":1525795200,"remain_time":1218407,"profit":312.5,"bdxq_url":"http://m.leyibank.com#/underlyingDetailsApp?deal_id=73"}]
         * virtual : {"VIRTUAL_YEST_EXCHANGE":"510665.56","VIRTUAL_MONEY_1":"96032998.00","VIRTUAL_USERS":4950,"YESTERDAY":"04月23日"}
         */

        private VirtualBean virtual;
        private List<RecommandXinyongBean> recommand_xinyong;
        private List<RecommandGongyingBean> recommand_gongying;
        private List<NewerBean> newer;


        public VirtualBean getVirtual() {
            return virtual;
        }

        public void setVirtual(VirtualBean virtual) {
            this.virtual = virtual;
        }

        public List<RecommandXinyongBean> getRecommand_xinyong() {
            return recommand_xinyong;
        }

        public void setRecommand_xinyong(List<RecommandXinyongBean> recommand_xinyong) {
            this.recommand_xinyong = recommand_xinyong;
        }

        public List<RecommandGongyingBean> getRecommand_gongying() {
            return recommand_gongying;
        }

        public void setRecommand_gongying(List<RecommandGongyingBean> recommand_gongying) {
            this.recommand_gongying = recommand_gongying;
        }

        public List<NewerBean> getNewer() {
            return newer;
        }

        public void setNewer(List<NewerBean> newer) {
            this.newer = newer;
        }

        public static class VirtualBean {
            /**
             * VIRTUAL_YEST_EXCHANGE : 510665.56
             * VIRTUAL_MONEY_1 : 96032998.00
             * VIRTUAL_USERS : 4950
             * YESTERDAY : 04月23日
             */

            private String VIRTUAL_YEST_EXCHANGE;
            private String VIRTUAL_MONEY_1;
            private int VIRTUAL_USERS;
            private String YESTERDAY;

            public static VirtualBean objectFromData(String str) {

                return new Gson().fromJson(str, VirtualBean.class);
            }

            public String getVIRTUAL_YEST_EXCHANGE() {
                return VIRTUAL_YEST_EXCHANGE;
            }

            public void setVIRTUAL_YEST_EXCHANGE(String VIRTUAL_YEST_EXCHANGE) {
                this.VIRTUAL_YEST_EXCHANGE = VIRTUAL_YEST_EXCHANGE;
            }

            public String getVIRTUAL_MONEY_1() {
                return VIRTUAL_MONEY_1;
            }

            public void setVIRTUAL_MONEY_1(String VIRTUAL_MONEY_1) {
                this.VIRTUAL_MONEY_1 = VIRTUAL_MONEY_1;
            }

            public int getVIRTUAL_USERS() {
                return VIRTUAL_USERS;
            }

            public void setVIRTUAL_USERS(int VIRTUAL_USERS) {
                this.VIRTUAL_USERS = VIRTUAL_USERS;
            }

            public String getYESTERDAY() {
                return YESTERDAY;
            }

            public void setYESTERDAY(String YESTERDAY) {
                this.YESTERDAY = YESTERDAY;
            }
        }

        public static class RecommandXinyongBean {
            /**
             * deal_id : 49
             * name : 货抵贷15号
             * sub_name : 货抵贷15号
             * cate_id : 1
             * rate : 10.58
             * repay_time : 3
             * repay_time_type : 月
             * min_loan_money : 100
             * max_loan_money : 0
             * portion : 2000
             * max_portion : 0
             * borrow_amount : 200000
             * buy_count : 8
             * load_money : 102866
             * remain_money : 97134
             * transfer_day : 0
             * loantype : 1
             * uloadtype : 0
             * enddate : 10
             * start_time : 1523879150
             * deal_status : 1
             * user_loan_manage_fee : 0
             * user_loan_interest_manage_fee : 0
             * user_bid_rebate : 0
             * use_ecv : 1
             * use_rate : 1
             * deal_labels : []
             * sales_label : 0
             * is_new : 0
             * extra_rate : 0.00
             * icon :
             * repay_start_time : 0
             * progress_point : 51
             * current_time : 1524576793
             * date_of_value : T(成交日+1)
             * loantype_format : 付息还本
             * end_time : 1524743150
             * remain_time : 166357
             * profit : 5290
             * bdxq_url : http://m.leyibank.com#/underlyingDetailsApp?deal_id=49
             */

            private String deal_id;
            private String name;
            private String sub_name;
            private String cate_id;
            private String rate;
            private String repay_time;
            private String repay_time_type;
            private String min_loan_money;
            private String max_loan_money;
            private String portion;
            private String max_portion;
            private String borrow_amount;
            private String buy_count;
            private String load_money;
            private String remain_money;
            private String transfer_day;
            private String loantype;
            private String uloadtype;
            private String enddate;
            private int start_time;
            private String deal_status;
            private String user_loan_manage_fee;
            private String user_loan_interest_manage_fee;
            private String user_bid_rebate;
            private String use_ecv;
            private String use_rate;
            private String sales_label;
            private String is_new;
            private String extra_rate;
            private String icon;
            private int repay_start_time;
            private String progress_point;
            private int current_time;
            private String date_of_value;
            private String loantype_format;
            private int end_time;
            private int remain_time;
            private double profit;
            private String bdxq_url;
            private List<?> deal_labels;

            public static RecommandXinyongBean objectFromData(String str) {

                return new Gson().fromJson(str, RecommandXinyongBean.class);
            }

            public String getDeal_id() {
                return deal_id;
            }

            public void setDeal_id(String deal_id) {
                this.deal_id = deal_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSub_name() {
                return sub_name;
            }

            public void setSub_name(String sub_name) {
                this.sub_name = sub_name;
            }

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public String getRepay_time() {
                return repay_time;
            }

            public void setRepay_time(String repay_time) {
                this.repay_time = repay_time;
            }

            public String getRepay_time_type() {
                return repay_time_type;
            }

            public void setRepay_time_type(String repay_time_type) {
                this.repay_time_type = repay_time_type;
            }

            public String getMin_loan_money() {
                return min_loan_money;
            }

            public void setMin_loan_money(String min_loan_money) {
                this.min_loan_money = min_loan_money;
            }

            public String getMax_loan_money() {
                return max_loan_money;
            }

            public void setMax_loan_money(String max_loan_money) {
                this.max_loan_money = max_loan_money;
            }

            public String getPortion() {
                return portion;
            }

            public void setPortion(String portion) {
                this.portion = portion;
            }

            public String getMax_portion() {
                return max_portion;
            }

            public void setMax_portion(String max_portion) {
                this.max_portion = max_portion;
            }

            public String getBorrow_amount() {
                return borrow_amount;
            }

            public void setBorrow_amount(String borrow_amount) {
                this.borrow_amount = borrow_amount;
            }

            public String getBuy_count() {
                return buy_count;
            }

            public void setBuy_count(String buy_count) {
                this.buy_count = buy_count;
            }

            public String getLoad_money() {
                return load_money;
            }

            public void setLoad_money(String load_money) {
                this.load_money = load_money;
            }

            public String getRemain_money() {
                return remain_money;
            }

            public void setRemain_money(String remain_money) {
                this.remain_money = remain_money;
            }

            public String getTransfer_day() {
                return transfer_day;
            }

            public void setTransfer_day(String transfer_day) {
                this.transfer_day = transfer_day;
            }

            public String getLoantype() {
                return loantype;
            }

            public void setLoantype(String loantype) {
                this.loantype = loantype;
            }

            public String getUloadtype() {
                return uloadtype;
            }

            public void setUloadtype(String uloadtype) {
                this.uloadtype = uloadtype;
            }

            public String getEnddate() {
                return enddate;
            }

            public void setEnddate(String enddate) {
                this.enddate = enddate;
            }

            public int getStart_time() {
                return start_time;
            }

            public void setStart_time(int start_time) {
                this.start_time = start_time;
            }

            public String getDeal_status() {
                return deal_status;
            }

            public void setDeal_status(String deal_status) {
                this.deal_status = deal_status;
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

            public String getUser_bid_rebate() {
                return user_bid_rebate;
            }

            public void setUser_bid_rebate(String user_bid_rebate) {
                this.user_bid_rebate = user_bid_rebate;
            }

            public String getUse_ecv() {
                return use_ecv;
            }

            public void setUse_ecv(String use_ecv) {
                this.use_ecv = use_ecv;
            }

            public String getUse_rate() {
                return use_rate;
            }

            public void setUse_rate(String use_rate) {
                this.use_rate = use_rate;
            }

            public String getSales_label() {
                return sales_label;
            }

            public void setSales_label(String sales_label) {
                this.sales_label = sales_label;
            }

            public String getIs_new() {
                return is_new;
            }

            public void setIs_new(String is_new) {
                this.is_new = is_new;
            }

            public String getExtra_rate() {
                return extra_rate;
            }

            public void setExtra_rate(String extra_rate) {
                this.extra_rate = extra_rate;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getRepay_start_time() {
                return repay_start_time;
            }

            public void setRepay_start_time(int repay_start_time) {
                this.repay_start_time = repay_start_time;
            }

            public String getProgress_point() {
                return progress_point;
            }

            public void setProgress_point(String progress_point) {
                this.progress_point = progress_point;
            }

            public int getCurrent_time() {
                return current_time;
            }

            public void setCurrent_time(int current_time) {
                this.current_time = current_time;
            }

            public String getDate_of_value() {
                return date_of_value;
            }

            public void setDate_of_value(String date_of_value) {
                this.date_of_value = date_of_value;
            }

            public String getLoantype_format() {
                return loantype_format;
            }

            public void setLoantype_format(String loantype_format) {
                this.loantype_format = loantype_format;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public int getRemain_time() {
                return remain_time;
            }

            public void setRemain_time(int remain_time) {
                this.remain_time = remain_time;
            }

            public double getProfit() {
                return profit;
            }

            public void setProfit(double profit) {
                this.profit = profit;
            }

            public String getBdxq_url() {
                return bdxq_url;
            }

            public void setBdxq_url(String bdxq_url) {
                this.bdxq_url = bdxq_url;
            }

            public List<?> getDeal_labels() {
                return deal_labels;
            }

            public void setDeal_labels(List<?> deal_labels) {
                this.deal_labels = deal_labels;
            }
        }

        public static class RecommandGongyingBean {
            /**
             * deal_id : 61
             * name : 产业链16号
             * sub_name : 产业链16号
             * cate_id : 2
             * rate : 9.08
             * repay_time : 1
             * repay_time_type : 月
             * min_loan_money : 100
             * max_loan_money : 0
             * portion : 500
             * max_portion : 0
             * borrow_amount : 50000
             * buy_count : 9
             * load_money : 37732
             * remain_money : 12268
             * transfer_day : 0
             * loantype : 1
             * uloadtype : 0
             * enddate : 3
             * start_time : 1524391680
             * deal_status : 1
             * user_loan_manage_fee : 0
             * user_loan_interest_manage_fee : 0
             * user_bid_rebate : 0
             * use_ecv : 1
             * use_rate : 1
             * deal_labels : []
             * sales_label : 0
             * is_new : 0
             * extra_rate : 0.00
             * icon :
             * repay_start_time : 0
             * progress_point : 75
             * current_time : 1524576793
             * date_of_value : T(成交日+1)
             * loantype_format : 付息还本
             * end_time : 1524650880
             * remain_time : 74087
             * profit : 378.33
             * bdxq_url : http://m.leyibank.com#/underlyingDetailsApp?deal_id=61
             */

            private String deal_id;
            private String name;
            private String sub_name;
            private String cate_id;
            private String rate;
            private String repay_time;
            private String repay_time_type;
            private String min_loan_money;
            private String max_loan_money;
            private String portion;
            private String max_portion;
            private String borrow_amount;
            private String buy_count;
            private String load_money;
            private String remain_money;
            private String transfer_day;
            private String loantype;
            private String uloadtype;
            private String enddate;
            private int start_time;
            private String deal_status;
            private String user_loan_manage_fee;
            private String user_loan_interest_manage_fee;
            private String user_bid_rebate;
            private String use_ecv;
            private String use_rate;
            private String sales_label;
            private String is_new;
            private String extra_rate;
            private String icon;
            private int repay_start_time;
            private String progress_point;
            private int current_time;
            private String date_of_value;
            private String loantype_format;
            private int end_time;
            private int remain_time;
            private double profit;
            private String bdxq_url;
            private List<?> deal_labels;

            public static RecommandGongyingBean objectFromData(String str) {

                return new Gson().fromJson(str, RecommandGongyingBean.class);
            }

            public String getDeal_id() {
                return deal_id;
            }

            public void setDeal_id(String deal_id) {
                this.deal_id = deal_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSub_name() {
                return sub_name;
            }

            public void setSub_name(String sub_name) {
                this.sub_name = sub_name;
            }

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public String getRepay_time() {
                return repay_time;
            }

            public void setRepay_time(String repay_time) {
                this.repay_time = repay_time;
            }

            public String getRepay_time_type() {
                return repay_time_type;
            }

            public void setRepay_time_type(String repay_time_type) {
                this.repay_time_type = repay_time_type;
            }

            public String getMin_loan_money() {
                return min_loan_money;
            }

            public void setMin_loan_money(String min_loan_money) {
                this.min_loan_money = min_loan_money;
            }

            public String getMax_loan_money() {
                return max_loan_money;
            }

            public void setMax_loan_money(String max_loan_money) {
                this.max_loan_money = max_loan_money;
            }

            public String getPortion() {
                return portion;
            }

            public void setPortion(String portion) {
                this.portion = portion;
            }

            public String getMax_portion() {
                return max_portion;
            }

            public void setMax_portion(String max_portion) {
                this.max_portion = max_portion;
            }

            public String getBorrow_amount() {
                return borrow_amount;
            }

            public void setBorrow_amount(String borrow_amount) {
                this.borrow_amount = borrow_amount;
            }

            public String getBuy_count() {
                return buy_count;
            }

            public void setBuy_count(String buy_count) {
                this.buy_count = buy_count;
            }

            public String getLoad_money() {
                return load_money;
            }

            public void setLoad_money(String load_money) {
                this.load_money = load_money;
            }

            public String getRemain_money() {
                return remain_money;
            }

            public void setRemain_money(String remain_money) {
                this.remain_money = remain_money;
            }

            public String getTransfer_day() {
                return transfer_day;
            }

            public void setTransfer_day(String transfer_day) {
                this.transfer_day = transfer_day;
            }

            public String getLoantype() {
                return loantype;
            }

            public void setLoantype(String loantype) {
                this.loantype = loantype;
            }

            public String getUloadtype() {
                return uloadtype;
            }

            public void setUloadtype(String uloadtype) {
                this.uloadtype = uloadtype;
            }

            public String getEnddate() {
                return enddate;
            }

            public void setEnddate(String enddate) {
                this.enddate = enddate;
            }

            public int getStart_time() {
                return start_time;
            }

            public void setStart_time(int start_time) {
                this.start_time = start_time;
            }

            public String getDeal_status() {
                return deal_status;
            }

            public void setDeal_status(String deal_status) {
                this.deal_status = deal_status;
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

            public String getUser_bid_rebate() {
                return user_bid_rebate;
            }

            public void setUser_bid_rebate(String user_bid_rebate) {
                this.user_bid_rebate = user_bid_rebate;
            }

            public String getUse_ecv() {
                return use_ecv;
            }

            public void setUse_ecv(String use_ecv) {
                this.use_ecv = use_ecv;
            }

            public String getUse_rate() {
                return use_rate;
            }

            public void setUse_rate(String use_rate) {
                this.use_rate = use_rate;
            }

            public String getSales_label() {
                return sales_label;
            }

            public void setSales_label(String sales_label) {
                this.sales_label = sales_label;
            }

            public String getIs_new() {
                return is_new;
            }

            public void setIs_new(String is_new) {
                this.is_new = is_new;
            }

            public String getExtra_rate() {
                return extra_rate;
            }

            public void setExtra_rate(String extra_rate) {
                this.extra_rate = extra_rate;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getRepay_start_time() {
                return repay_start_time;
            }

            public void setRepay_start_time(int repay_start_time) {
                this.repay_start_time = repay_start_time;
            }

            public String getProgress_point() {
                return progress_point;
            }

            public void setProgress_point(String progress_point) {
                this.progress_point = progress_point;
            }

            public int getCurrent_time() {
                return current_time;
            }

            public void setCurrent_time(int current_time) {
                this.current_time = current_time;
            }

            public String getDate_of_value() {
                return date_of_value;
            }

            public void setDate_of_value(String date_of_value) {
                this.date_of_value = date_of_value;
            }

            public String getLoantype_format() {
                return loantype_format;
            }

            public void setLoantype_format(String loantype_format) {
                this.loantype_format = loantype_format;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public int getRemain_time() {
                return remain_time;
            }

            public void setRemain_time(int remain_time) {
                this.remain_time = remain_time;
            }

            public double getProfit() {
                return profit;
            }

            public void setProfit(double profit) {
                this.profit = profit;
            }

            public String getBdxq_url() {
                return bdxq_url;
            }

            public void setBdxq_url(String bdxq_url) {
                this.bdxq_url = bdxq_url;
            }

            public List<?> getDeal_labels() {
                return deal_labels;
            }

            public void setDeal_labels(List<?> deal_labels) {
                this.deal_labels = deal_labels;
            }
        }

        public static class NewerBean {
            /**
             * deal_id : 73
             * name : 新手专享12号
             * sub_name : 新手专享12号
             * cate_id : 1
             * rate : 15.00
             * repay_time : 15
             * repay_time_type : 天
             * min_loan_money : 100
             * max_loan_money : 0
             * portion : 500
             * max_portion : 0
             * borrow_amount : 50000
             * buy_count : 3
             * load_money : 50000
             * remain_money : 0
             * transfer_day : 0
             * loantype : 2
             * uloadtype : 0
             * enddate : 3
             * start_time : 1524535940
             * deal_status : 4
             * user_loan_manage_fee : 0
             * user_loan_interest_manage_fee : 0
             * user_bid_rebate : 0
             * use_ecv : 0
             * use_rate : 0
             * deal_labels : []
             * sales_label : 0
             * is_new : 1
             * extra_rate : 0.00
             * icon :
             * repay_start_time : 1524499200
             * progress_point : 100
             * current_time : 1524576793
             * date_of_value : T(成交日+1)
             * loantype_format : 到期还本息
             * end_time : 1525795200
             * remain_time : 1218407
             * profit : 312.5
             * bdxq_url : http://m.leyibank.com#/underlyingDetailsApp?deal_id=73
             */

            private String deal_id;
            private String name;
            private String sub_name;
            private String cate_id;
            private String rate;
            private String repay_time;
            private String repay_time_type;
            private String min_loan_money;
            private String max_loan_money;
            private String portion;
            private String max_portion;
            private String borrow_amount;
            private String buy_count;
            private String load_money;
            private String remain_money;
            private String transfer_day;
            private String loantype;
            private String uloadtype;
            private String enddate;
            private int start_time;
            private String deal_status;
            private String user_loan_manage_fee;
            private String user_loan_interest_manage_fee;
            private String user_bid_rebate;
            private String use_ecv;
            private String use_rate;
            private String sales_label;
            private String is_new;
            private String extra_rate;
            private String icon;
            private int repay_start_time;
            private String progress_point;
            private int current_time;
            private String date_of_value;
            private String loantype_format;
            private int end_time;
            private int remain_time;
            private double profit;
            private String bdxq_url;
            private List<?> deal_labels;

            public static NewerBean objectFromData(String str) {

                return new Gson().fromJson(str, NewerBean.class);
            }

            public String getDeal_id() {
                return deal_id;
            }

            public void setDeal_id(String deal_id) {
                this.deal_id = deal_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSub_name() {
                return sub_name;
            }

            public void setSub_name(String sub_name) {
                this.sub_name = sub_name;
            }

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public String getRepay_time() {
                return repay_time;
            }

            public void setRepay_time(String repay_time) {
                this.repay_time = repay_time;
            }

            public String getRepay_time_type() {
                return repay_time_type;
            }

            public void setRepay_time_type(String repay_time_type) {
                this.repay_time_type = repay_time_type;
            }

            public String getMin_loan_money() {
                return min_loan_money;
            }

            public void setMin_loan_money(String min_loan_money) {
                this.min_loan_money = min_loan_money;
            }

            public String getMax_loan_money() {
                return max_loan_money;
            }

            public void setMax_loan_money(String max_loan_money) {
                this.max_loan_money = max_loan_money;
            }

            public String getPortion() {
                return portion;
            }

            public void setPortion(String portion) {
                this.portion = portion;
            }

            public String getMax_portion() {
                return max_portion;
            }

            public void setMax_portion(String max_portion) {
                this.max_portion = max_portion;
            }

            public String getBorrow_amount() {
                return borrow_amount;
            }

            public void setBorrow_amount(String borrow_amount) {
                this.borrow_amount = borrow_amount;
            }

            public String getBuy_count() {
                return buy_count;
            }

            public void setBuy_count(String buy_count) {
                this.buy_count = buy_count;
            }

            public String getLoad_money() {
                return load_money;
            }

            public void setLoad_money(String load_money) {
                this.load_money = load_money;
            }

            public String getRemain_money() {
                return remain_money;
            }

            public void setRemain_money(String remain_money) {
                this.remain_money = remain_money;
            }

            public String getTransfer_day() {
                return transfer_day;
            }

            public void setTransfer_day(String transfer_day) {
                this.transfer_day = transfer_day;
            }

            public String getLoantype() {
                return loantype;
            }

            public void setLoantype(String loantype) {
                this.loantype = loantype;
            }

            public String getUloadtype() {
                return uloadtype;
            }

            public void setUloadtype(String uloadtype) {
                this.uloadtype = uloadtype;
            }

            public String getEnddate() {
                return enddate;
            }

            public void setEnddate(String enddate) {
                this.enddate = enddate;
            }

            public int getStart_time() {
                return start_time;
            }

            public void setStart_time(int start_time) {
                this.start_time = start_time;
            }

            public String getDeal_status() {
                return deal_status;
            }

            public void setDeal_status(String deal_status) {
                this.deal_status = deal_status;
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

            public String getUser_bid_rebate() {
                return user_bid_rebate;
            }

            public void setUser_bid_rebate(String user_bid_rebate) {
                this.user_bid_rebate = user_bid_rebate;
            }

            public String getUse_ecv() {
                return use_ecv;
            }

            public void setUse_ecv(String use_ecv) {
                this.use_ecv = use_ecv;
            }

            public String getUse_rate() {
                return use_rate;
            }

            public void setUse_rate(String use_rate) {
                this.use_rate = use_rate;
            }

            public String getSales_label() {
                return sales_label;
            }

            public void setSales_label(String sales_label) {
                this.sales_label = sales_label;
            }

            public String getIs_new() {
                return is_new;
            }

            public void setIs_new(String is_new) {
                this.is_new = is_new;
            }

            public String getExtra_rate() {
                return extra_rate;
            }

            public void setExtra_rate(String extra_rate) {
                this.extra_rate = extra_rate;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getRepay_start_time() {
                return repay_start_time;
            }

            public void setRepay_start_time(int repay_start_time) {
                this.repay_start_time = repay_start_time;
            }

            public String getProgress_point() {
                return progress_point;
            }

            public void setProgress_point(String progress_point) {
                this.progress_point = progress_point;
            }

            public int getCurrent_time() {
                return current_time;
            }

            public void setCurrent_time(int current_time) {
                this.current_time = current_time;
            }

            public String getDate_of_value() {
                return date_of_value;
            }

            public void setDate_of_value(String date_of_value) {
                this.date_of_value = date_of_value;
            }

            public String getLoantype_format() {
                return loantype_format;
            }

            public void setLoantype_format(String loantype_format) {
                this.loantype_format = loantype_format;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public int getRemain_time() {
                return remain_time;
            }

            public void setRemain_time(int remain_time) {
                this.remain_time = remain_time;
            }

            public double getProfit() {
                return profit;
            }

            public void setProfit(double profit) {
                this.profit = profit;
            }

            public String getBdxq_url() {
                return bdxq_url;
            }

            public void setBdxq_url(String bdxq_url) {
                this.bdxq_url = bdxq_url;
            }

            public List<?> getDeal_labels() {
                return deal_labels;
            }

            public void setDeal_labels(List<?> deal_labels) {
                this.deal_labels = deal_labels;
            }
        }

}
