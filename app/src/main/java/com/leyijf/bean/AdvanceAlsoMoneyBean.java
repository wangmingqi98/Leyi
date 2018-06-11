package com.leyijf.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wmq on 2018/5/9.
 */

public class AdvanceAlsoMoneyBean {

    public static AdvanceAlsoMoneyBean objectFromData(String str) {

        return new Gson().fromJson(str, AdvanceAlsoMoneyBean.class);
    }

        /**
         * session_id : ooqa4mnkte98c1pcv3g27keir3
         * user_login_status : 1
         * deal : {"id":"73","name":"小测试36","sub_name":"36","cate_id":"1","agency_id":"0","agency_status":"0","user_id":"757","sales_label":"0","deal_labels":"","description":"1111111111111","is_effect":"1","is_delete":"0","sort":"68","type_id":"2","icon_type":"0","icon":"","seo_title":"","seo_keyword":"","seo_description":"","is_hot":"0","is_new":"0","is_best":"0","borrow_amount":"50000","apart_borrow_amount":"0","min_loan_money":"0","repay_time":"3","rate":"10.00","day":"0","create_time":"1524438606","update_time":"1524785045","name_match":"ux27979ux35797,ux23567ux27979ux35797ux51ux54","name_match_row":"测试,小测试36","deal_cate_match":"ux36135ux25269ux36151","deal_cate_match_row":"货抵贷","tag_match":"","tag_match_row":"","type_match":"ux36141ux25151ux20511ux27454","type_match_row":"购房借款","is_recommend":"0","buy_count":"19","load_money":50000,"repay_money":"833.32","start_time":"1524697800","success_time":"1525389540","repay_start_time":"1525334400","last_repay_time":"1530518400","next_repay_time":1533110400,"bad_time":"1524784881","deal_status":4,"enddate":"365","voffice":"0","vposition":"0","services_fee":"0","publish_wait":"0","is_send_bad_msg":"0","bad_msg":"","send_half_msg_time":"0","send_three_msg_time":"0","is_send_half_msg":"1","is_has_loans":"1","loantype":"1","warrant":"0","titlecolor":"","is_send_contract":"1","repay_time_type":"1","max_loan_money":"0","risk_rank":"0","risk_security":"11111111111111","is_send_success_msg":"0","ips_bill_no":"","guarantees_money":"0.00","ips_over":"0","deal_sn":"MER20180000073","is_has_received":"0","manage_fee":"0","user_loan_manage_fee":"0","manage_impose_fee_day1":"0.5","manage_impose_fee_day2":"1","impose_fee_day1":"0.3","impose_fee_day2":"1","user_load_transfer_fee":"3","transfer_day":"0","compensate_fee":"0","ips_do_transfer":"0","delete_msg":"","user_bid_rebate":"0","guarantees_amt":"0.00","real_freezen_amt":"0.00","un_real_freezen_amt":"0.00","guarantor_amt":"0.00","guarantor_margin_amt":"0.00","guarantor_real_freezen_amt":"0.00","un_guarantor_real_freezen_amt":"0.00","guarantor_pro_fit_amt":"0.00","guarantor_real_fit_amt":"0.00","mer_bill_no":"","ips_guarantor_bill_no":"","mer_guarantor_bill_no":"","view_info":"a:0:{}","generation_position":"100.00","uloadtype":"0","portion":"0","max_portion":"0","start_date":"2018-04-26","repay_start_date":"2018-05-04","bad_date":"2018-04-27","contract_id":"5","tcontract_id":"5","is_advance":"0","is_hidden":"0","peizi_order_ids":"","score":"0","user_bid_score_fee":"0","user_loan_interest_manage_fee":"0","attachment":"","tattachment":"","publish_memo":"","is_index_show":"0","loans_pic":"","admin_id":"0","customers_id":"0","use_rate":"0","use_ecv":"1","is_mortgage":"0","mortgage_desc":"","mortgage_infos":"a:0:{}","mortgage_contract":"a:0:{}","mortgage_fee":0,"autobid_handle":"0","is_users":"0","sealstep":"1","extra_rate":"1.00","is_wait":0,"remain_time":30839811,"loantype_format":"付息还本","color_name":"小测试36","borrow_amount_format":"￥5.00万","load_money_format":"￥5.00万","rate_foramt":"10.00","create_time_format":"2018-04-23","rate_foramt_w":"10.00%","month_repay_money":416.66666666667,"last_month_repay_money":50416.66,"month_repay_money_format":"￥416.67","month_manage_money":0,"month_manage_money_format":"￥0.00","all_manage_money":0,"true_month_repay_money":416.66666666667,"true_last_month_repay_money":50416.66,"true_month_repay_money_format":"￥416.67","need_money":"￥0.00","progress_point":1.6259902439024,"user":{"id":"757","user_name":"安慕希2号","short_name":null,"brief":null,"header":null,"user_img_url":null,"company_brief":null,"history":null,"content":null,"sort":null,"acct_type":null,"user_pwd":"756f566af74f123732bf2d4b099d9e2e","create_time":"1524419214","update_time":"1524510302","login_ip":"127.0.0.1","group_id":"1","is_effect":"1","is_delete":"0","is_new_user":"0","email":"13552032668@163.com","idno":"412723194106161565","idcardpassed":"0","idcardpassed_time":"0","real_name":"李会锋","mobile":"13685214563","mobilepassed":"1","score":"0","money":"79166.68","quota":"500","lock_money":"0.00","verify":"","code":"","pid":"0","referer_memo":"13552032668","login_time":"1525389554","referral_count":"0","password_verify":"","integrate_id":"0","sina_id":"","renren_id":"0","kaixin_id":"0","sohu_id":"0","bind_verify":"","verify_create_time":"0","tencent_id":"","referer":"","login_pay_time":"0","focus_count":"0","focused_count":"0","n_province_id":"2","n_city_id":"52","province_id":"11","city_id":"149","sex":"1","step":"0","byear":"1991","bmonth":"10","bday":"24","graduation":"本科","graduatedyear":"2018","university":"","edu_validcode":"","has_send_video":"0","marriage":"未婚","haschild":"0","hashouse":"0","houseloan":"0","hascar":"0","carloan":"0","car_brand":"","car_year":"0","car_number":"","address":"北京市海淀区友谊路","phone":"","postcode":"","locate_time":"1525389491","xpoint":"0.000000","ypoint":"0.000000","topic_count":"0","fav_count":"0","faved_count":"0","insite_count":"0","outsite_count":"0","level_id":"1","point":"21","sina_app_key":"","sina_app_secret":"","is_syn_sina":"","tencent_app_key":"","tencent_app_secret":"","is_syn_tencent":"0","t_access_token":"","t_openkey":"","t_openid":"","sina_token":"","is_borrow_out":"0","is_borrow_int":"0","creditpassed":"0","creditpassed_time":"0","workpassed":"0","workpassed_time":"0","incomepassed":"0","incomepassed_time":"0","housepassed":"0","housepassed_time":"0","carpassed":"0","carpassed_time":"0","marrypassed":"0","marrypassed_time":"0","edupassed":"0","edupassed_time":"0","skillpassed":"0","skillpassed_time":"0","videopassed":"0","videopassed_time":"0","mobiletruepassed":"0","mobiletruepassed_time":"0","residencepassed":"0","residencepassed_time":"0","alipay_id":"","qq_id":"","taobao_id":"","info_down":"","sealpassed":"0","paypassword":"4607e782c4d86fd5364d7e4508bb10d9","apns_code":null,"ips_acct_no":null,"emailpassed":"0","tmp_email":"","view_info":"","referral_rate":"0.0000","user_type":"0","create_date":"2018-04-23","register_ip":"127.0.0.1","admin_id":"0","customer_id":"0","is_black":"0","vip_id":"2","vip_state":"1","nmc_amount":"0.00","ips_mer_code":null,"enterpriseName":"","bankLicense":"","orgNo":"","businessLicense":"","taxNo":"","u_year":null,"u_special":"","u_alipay":"","wx_openid":"","total_invite_borrow_money":"0.00","total_invite_invest_money":"0.00","vip_end_time":"0","platform":"","platform_is_cash":"0","platform_is_ecv":"0","platform_is_rate":"0","bid_start":"10000","reg_type":"1","is_virtual":"0","point_level":"HR","url":"/index.php?ctl=space&id=757","region_city":"郑州","region":"郑州","region_province":"河南","workinfo":false},"cate_info":{"id":"1","name":"货抵贷","brief":"货物抵押借款","uname":"","icon":"./public/attachment/201803/16/13/5aab5c235d619.png"},"type_info":{"id":"2","name":"购房借款","brief":"","pid":"0","is_delete":"0","is_effect":"1","sort":"9","uname":"","icon":"./public/images/dealtype/gf.png","applyto":"","condition":"22-55周岁的中国公民\r\n在现单位工作满3个月\r\n月收入2000以上","credits":"N;","is_quota":"1","content":""},"all_mortgage_fee":0,"mortgage_contract_list":[],"remain_time_format":"0天0时0分","min_loan_money_format":"￥0.00","remain_repay_money":51250,"need_remain_repay_money":50416.68,"repay_progress_point":1.6259902439024,"end_repay_time":1533110400,"next_repay_time_format":"2018-08-02","publish_time_format":"2018-04-23 15:10","share_url":"http://www.leyi.com/mapi/index.php?ctl=deal&id=73&r=NzU3","url":"/index.php?ctl=deal&id=73","app_url":"http://www.leyi.com/index.php?ctl=deal&act=mobile&is_sj=1&id=73"}
         * true_all_manage_money : 0
         * impose_money : 0
         * total_repay_money : 50416.666666667
         * true_total_repay_money : 50416.666666667
         * true_all_manage_money_format : ￥0.00
         * impose_money_format : ￥0.00
         * total_repay_money_format : ￥50,416.67
         * true_total_repay_money_format : ￥50,416.67
         * program_title : 提前还款
         */

        private String session_id;
        private int user_login_status;
        private DealBean deal;
        private int true_all_manage_money;
        private int impose_money;
        private double total_repay_money;
        private double true_total_repay_money;
        private String true_all_manage_money_format;
        private String impose_money_format;
        private String total_repay_money_format;
        private String true_total_repay_money_format;
        private String program_title;



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

        public DealBean getDeal() {
            return deal;
        }

        public void setDeal(DealBean deal) {
            this.deal = deal;
        }

        public int getTrue_all_manage_money() {
            return true_all_manage_money;
        }

        public void setTrue_all_manage_money(int true_all_manage_money) {
            this.true_all_manage_money = true_all_manage_money;
        }

        public int getImpose_money() {
            return impose_money;
        }

        public void setImpose_money(int impose_money) {
            this.impose_money = impose_money;
        }

        public double getTotal_repay_money() {
            return total_repay_money;
        }

        public void setTotal_repay_money(double total_repay_money) {
            this.total_repay_money = total_repay_money;
        }

        public double getTrue_total_repay_money() {
            return true_total_repay_money;
        }

        public void setTrue_total_repay_money(double true_total_repay_money) {
            this.true_total_repay_money = true_total_repay_money;
        }

        public String getTrue_all_manage_money_format() {
            return true_all_manage_money_format;
        }

        public void setTrue_all_manage_money_format(String true_all_manage_money_format) {
            this.true_all_manage_money_format = true_all_manage_money_format;
        }

        public String getImpose_money_format() {
            return impose_money_format;
        }

        public void setImpose_money_format(String impose_money_format) {
            this.impose_money_format = impose_money_format;
        }

        public String getTotal_repay_money_format() {
            return total_repay_money_format;
        }

        public void setTotal_repay_money_format(String total_repay_money_format) {
            this.total_repay_money_format = total_repay_money_format;
        }

        public String getTrue_total_repay_money_format() {
            return true_total_repay_money_format;
        }

        public void setTrue_total_repay_money_format(String true_total_repay_money_format) {
            this.true_total_repay_money_format = true_total_repay_money_format;
        }

        public String getProgram_title() {
            return program_title;
        }

        public void setProgram_title(String program_title) {
            this.program_title = program_title;
        }

        public static class DealBean {
            /**
             * id : 73
             * name : 小测试36
             * sub_name : 36
             * cate_id : 1
             * agency_id : 0
             * agency_status : 0
             * user_id : 757
             * sales_label : 0
             * deal_labels :
             * description : 1111111111111
             * is_effect : 1
             * is_delete : 0
             * sort : 68
             * type_id : 2
             * icon_type : 0
             * icon :
             * seo_title :
             * seo_keyword :
             * seo_description :
             * is_hot : 0
             * is_new : 0
             * is_best : 0
             * borrow_amount : 50000
             * apart_borrow_amount : 0
             * min_loan_money : 0
             * repay_time : 3
             * rate : 10.00
             * day : 0
             * create_time : 1524438606
             * update_time : 1524785045
             * name_match : ux27979ux35797,ux23567ux27979ux35797ux51ux54
             * name_match_row : 测试,小测试36
             * deal_cate_match : ux36135ux25269ux36151
             * deal_cate_match_row : 货抵贷
             * tag_match :
             * tag_match_row :
             * type_match : ux36141ux25151ux20511ux27454
             * type_match_row : 购房借款
             * is_recommend : 0
             * buy_count : 19
             * load_money : 50000
             * repay_money : 833.32
             * start_time : 1524697800
             * success_time : 1525389540
             * repay_start_time : 1525334400
             * last_repay_time : 1530518400
             * next_repay_time : 1533110400
             * bad_time : 1524784881
             * deal_status : 4
             * enddate : 365
             * voffice : 0
             * vposition : 0
             * services_fee : 0
             * publish_wait : 0
             * is_send_bad_msg : 0
             * bad_msg :
             * send_half_msg_time : 0
             * send_three_msg_time : 0
             * is_send_half_msg : 1
             * is_has_loans : 1
             * loantype : 1
             * warrant : 0
             * titlecolor :
             * is_send_contract : 1
             * repay_time_type : 1
             * max_loan_money : 0
             * risk_rank : 0
             * risk_security : 11111111111111
             * is_send_success_msg : 0
             * ips_bill_no :
             * guarantees_money : 0.00
             * ips_over : 0
             * deal_sn : MER20180000073
             * is_has_received : 0
             * manage_fee : 0
             * user_loan_manage_fee : 0
             * manage_impose_fee_day1 : 0.5
             * manage_impose_fee_day2 : 1
             * impose_fee_day1 : 0.3
             * impose_fee_day2 : 1
             * user_load_transfer_fee : 3
             * transfer_day : 0
             * compensate_fee : 0
             * ips_do_transfer : 0
             * delete_msg :
             * user_bid_rebate : 0
             * guarantees_amt : 0.00
             * real_freezen_amt : 0.00
             * un_real_freezen_amt : 0.00
             * guarantor_amt : 0.00
             * guarantor_margin_amt : 0.00
             * guarantor_real_freezen_amt : 0.00
             * un_guarantor_real_freezen_amt : 0.00
             * guarantor_pro_fit_amt : 0.00
             * guarantor_real_fit_amt : 0.00
             * mer_bill_no :
             * ips_guarantor_bill_no :
             * mer_guarantor_bill_no :
             * view_info : a:0:{}
             * generation_position : 100.00
             * uloadtype : 0
             * portion : 0
             * max_portion : 0
             * start_date : 2018-04-26
             * repay_start_date : 2018-05-04
             * bad_date : 2018-04-27
             * contract_id : 5
             * tcontract_id : 5
             * is_advance : 0
             * is_hidden : 0
             * peizi_order_ids :
             * score : 0
             * user_bid_score_fee : 0
             * user_loan_interest_manage_fee : 0
             * attachment :
             * tattachment :
             * publish_memo :
             * is_index_show : 0
             * loans_pic :
             * admin_id : 0
             * customers_id : 0
             * use_rate : 0
             * use_ecv : 1
             * is_mortgage : 0
             * mortgage_desc :
             * mortgage_infos : a:0:{}
             * mortgage_contract : a:0:{}
             * mortgage_fee : 0
             * autobid_handle : 0
             * is_users : 0
             * sealstep : 1
             * extra_rate : 1.00
             * is_wait : 0
             * remain_time : 30839811
             * loantype_format : 付息还本
             * color_name : 小测试36
             * borrow_amount_format : ￥5.00万
             * load_money_format : ￥5.00万
             * rate_foramt : 10.00
             * create_time_format : 2018-04-23
             * rate_foramt_w : 10.00%
             * month_repay_money : 416.66666666667
             * last_month_repay_money : 50416.66
             * month_repay_money_format : ￥416.67
             * month_manage_money : 0
             * month_manage_money_format : ￥0.00
             * all_manage_money : 0
             * true_month_repay_money : 416.66666666667
             * true_last_month_repay_money : 50416.66
             * true_month_repay_money_format : ￥416.67
             * need_money : ￥0.00
             * progress_point : 1.6259902439024
             * user : {"id":"757","user_name":"安慕希2号","short_name":null,"brief":null,"header":null,"user_img_url":null,"company_brief":null,"history":null,"content":null,"sort":null,"acct_type":null,"user_pwd":"756f566af74f123732bf2d4b099d9e2e","create_time":"1524419214","update_time":"1524510302","login_ip":"127.0.0.1","group_id":"1","is_effect":"1","is_delete":"0","is_new_user":"0","email":"13552032668@163.com","idno":"412723194106161565","idcardpassed":"0","idcardpassed_time":"0","real_name":"李会锋","mobile":"13685214563","mobilepassed":"1","score":"0","money":"79166.68","quota":"500","lock_money":"0.00","verify":"","code":"","pid":"0","referer_memo":"13552032668","login_time":"1525389554","referral_count":"0","password_verify":"","integrate_id":"0","sina_id":"","renren_id":"0","kaixin_id":"0","sohu_id":"0","bind_verify":"","verify_create_time":"0","tencent_id":"","referer":"","login_pay_time":"0","focus_count":"0","focused_count":"0","n_province_id":"2","n_city_id":"52","province_id":"11","city_id":"149","sex":"1","step":"0","byear":"1991","bmonth":"10","bday":"24","graduation":"本科","graduatedyear":"2018","university":"","edu_validcode":"","has_send_video":"0","marriage":"未婚","haschild":"0","hashouse":"0","houseloan":"0","hascar":"0","carloan":"0","car_brand":"","car_year":"0","car_number":"","address":"北京市海淀区友谊路","phone":"","postcode":"","locate_time":"1525389491","xpoint":"0.000000","ypoint":"0.000000","topic_count":"0","fav_count":"0","faved_count":"0","insite_count":"0","outsite_count":"0","level_id":"1","point":"21","sina_app_key":"","sina_app_secret":"","is_syn_sina":"","tencent_app_key":"","tencent_app_secret":"","is_syn_tencent":"0","t_access_token":"","t_openkey":"","t_openid":"","sina_token":"","is_borrow_out":"0","is_borrow_int":"0","creditpassed":"0","creditpassed_time":"0","workpassed":"0","workpassed_time":"0","incomepassed":"0","incomepassed_time":"0","housepassed":"0","housepassed_time":"0","carpassed":"0","carpassed_time":"0","marrypassed":"0","marrypassed_time":"0","edupassed":"0","edupassed_time":"0","skillpassed":"0","skillpassed_time":"0","videopassed":"0","videopassed_time":"0","mobiletruepassed":"0","mobiletruepassed_time":"0","residencepassed":"0","residencepassed_time":"0","alipay_id":"","qq_id":"","taobao_id":"","info_down":"","sealpassed":"0","paypassword":"4607e782c4d86fd5364d7e4508bb10d9","apns_code":null,"ips_acct_no":null,"emailpassed":"0","tmp_email":"","view_info":"","referral_rate":"0.0000","user_type":"0","create_date":"2018-04-23","register_ip":"127.0.0.1","admin_id":"0","customer_id":"0","is_black":"0","vip_id":"2","vip_state":"1","nmc_amount":"0.00","ips_mer_code":null,"enterpriseName":"","bankLicense":"","orgNo":"","businessLicense":"","taxNo":"","u_year":null,"u_special":"","u_alipay":"","wx_openid":"","total_invite_borrow_money":"0.00","total_invite_invest_money":"0.00","vip_end_time":"0","platform":"","platform_is_cash":"0","platform_is_ecv":"0","platform_is_rate":"0","bid_start":"10000","reg_type":"1","is_virtual":"0","point_level":"HR","url":"/index.php?ctl=space&id=757","region_city":"郑州","region":"郑州","region_province":"河南","workinfo":false}
             * cate_info : {"id":"1","name":"货抵贷","brief":"货物抵押借款","uname":"","icon":"./public/attachment/201803/16/13/5aab5c235d619.png"}
             * type_info : {"id":"2","name":"购房借款","brief":"","pid":"0","is_delete":"0","is_effect":"1","sort":"9","uname":"","icon":"./public/images/dealtype/gf.png","applyto":"","condition":"22-55周岁的中国公民\r\n在现单位工作满3个月\r\n月收入2000以上","credits":"N;","is_quota":"1","content":""}
             * all_mortgage_fee : 0
             * mortgage_contract_list : []
             * remain_time_format : 0天0时0分
             * min_loan_money_format : ￥0.00
             * remain_repay_money : 51250
             * need_remain_repay_money : 50416.68
             * repay_progress_point : 1.6259902439024
             * end_repay_time : 1533110400
             * next_repay_time_format : 2018-08-02
             * publish_time_format : 2018-04-23 15:10
             * share_url : http://www.leyi.com/mapi/index.php?ctl=deal&id=73&r=NzU3
             * url : /index.php?ctl=deal&id=73
             * app_url : http://www.leyi.com/index.php?ctl=deal&act=mobile&is_sj=1&id=73
             */

            private String id;
            private String name;
            private String sub_name;
            private String cate_id;
            private String agency_id;
            private String agency_status;
            private String user_id;
            private String sales_label;
            private String deal_labels;
            private String description;
            private String is_effect;
            private String is_delete;
            private String sort;
            private String type_id;
            private String icon_type;
            private String icon;
            private String seo_title;
            private String seo_keyword;
            private String seo_description;
            private String is_hot;
            private String is_new;
            private String is_best;
            private String borrow_amount;
            private String apart_borrow_amount;
            private String min_loan_money;
            private String repay_time;
            private String rate;
            private String day;
            private String create_time;
            private String update_time;
            private String name_match;
            private String name_match_row;
            private String deal_cate_match;
            private String deal_cate_match_row;
            private String tag_match;
            private String tag_match_row;
            private String type_match;
            private String type_match_row;
            private String is_recommend;
            private String buy_count;
            private int load_money;
            private String repay_money;
            private String start_time;
            private String success_time;
            private String repay_start_time;
            private String last_repay_time;
            private int next_repay_time;
            private String bad_time;
            private int deal_status;
            private String enddate;
            private String voffice;
            private String vposition;
            private String services_fee;
            private String publish_wait;
            private String is_send_bad_msg;
            private String bad_msg;
            private String send_half_msg_time;
            private String send_three_msg_time;
            private String is_send_half_msg;
            private String is_has_loans;
            private String loantype;
            private String warrant;
            private String titlecolor;
            private String is_send_contract;
            private String repay_time_type;
            private String max_loan_money;
            private String risk_rank;
            private String risk_security;
            private String is_send_success_msg;
            private String ips_bill_no;
            private String guarantees_money;
            private String ips_over;
            private String deal_sn;
            private String is_has_received;
            private String manage_fee;
            private String user_loan_manage_fee;
            private String manage_impose_fee_day1;
            private String manage_impose_fee_day2;
            private String impose_fee_day1;
            private String impose_fee_day2;
            private String user_load_transfer_fee;
            private String transfer_day;
            private String compensate_fee;
            private String ips_do_transfer;
            private String delete_msg;
            private String user_bid_rebate;
            private String guarantees_amt;
            private String real_freezen_amt;
            private String un_real_freezen_amt;
            private String guarantor_amt;
            private String guarantor_margin_amt;
            private String guarantor_real_freezen_amt;
            private String un_guarantor_real_freezen_amt;
            private String guarantor_pro_fit_amt;
            private String guarantor_real_fit_amt;
            private String mer_bill_no;
            private String ips_guarantor_bill_no;
            private String mer_guarantor_bill_no;
            private String view_info;
            private String generation_position;
            private String uloadtype;
            private String portion;
            private String max_portion;
            private String start_date;
            private String repay_start_date;
            private String bad_date;
            private String contract_id;
            private String tcontract_id;
            private String is_advance;
            private String is_hidden;
            private String peizi_order_ids;
            private String score;
            private String user_bid_score_fee;
            private String user_loan_interest_manage_fee;
            private String attachment;
            private String tattachment;
            private String publish_memo;
            private String is_index_show;
            private String loans_pic;
            private String admin_id;
            private String customers_id;
            private String use_rate;
            private String use_ecv;
            private String is_mortgage;
            private String mortgage_desc;
            private String mortgage_infos;
            private String mortgage_contract;
            private int mortgage_fee;
            private String autobid_handle;
            private String is_users;
            private String sealstep;
            private String extra_rate;
            private int is_wait;
            private int remain_time;
            private String loantype_format;
            private String color_name;
            private String borrow_amount_format;
            private String load_money_format;
            private String rate_foramt;
            private String create_time_format;
            private String rate_foramt_w;
            private double month_repay_money;
            private double last_month_repay_money;
            private String month_repay_money_format;
            private int month_manage_money;
            private String month_manage_money_format;
            private int all_manage_money;
            private double true_month_repay_money;
            private double true_last_month_repay_money;
            private String true_month_repay_money_format;
            private String need_money;
            private double progress_point;
            private UserBean user;
            private CateInfoBean cate_info;
            private TypeInfoBean type_info;
            private int all_mortgage_fee;
            private String remain_time_format;
            private String min_loan_money_format;
            private double remain_repay_money;
            private double need_remain_repay_money;
            private double repay_progress_point;
            private int end_repay_time;
            private String next_repay_time_format;
            private String publish_time_format;
            private String share_url;
            private String url;
            private String app_url;
            private List<?> mortgage_contract_list;

            public static DealBean objectFromData(String str) {

                return new Gson().fromJson(str, DealBean.class);
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

            public String getAgency_id() {
                return agency_id;
            }

            public void setAgency_id(String agency_id) {
                this.agency_id = agency_id;
            }

            public String getAgency_status() {
                return agency_status;
            }

            public void setAgency_status(String agency_status) {
                this.agency_status = agency_status;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getSales_label() {
                return sales_label;
            }

            public void setSales_label(String sales_label) {
                this.sales_label = sales_label;
            }

            public String getDeal_labels() {
                return deal_labels;
            }

            public void setDeal_labels(String deal_labels) {
                this.deal_labels = deal_labels;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getIs_effect() {
                return is_effect;
            }

            public void setIs_effect(String is_effect) {
                this.is_effect = is_effect;
            }

            public String getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(String is_delete) {
                this.is_delete = is_delete;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getType_id() {
                return type_id;
            }

            public void setType_id(String type_id) {
                this.type_id = type_id;
            }

            public String getIcon_type() {
                return icon_type;
            }

            public void setIcon_type(String icon_type) {
                this.icon_type = icon_type;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getSeo_title() {
                return seo_title;
            }

            public void setSeo_title(String seo_title) {
                this.seo_title = seo_title;
            }

            public String getSeo_keyword() {
                return seo_keyword;
            }

            public void setSeo_keyword(String seo_keyword) {
                this.seo_keyword = seo_keyword;
            }

            public String getSeo_description() {
                return seo_description;
            }

            public void setSeo_description(String seo_description) {
                this.seo_description = seo_description;
            }

            public String getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(String is_hot) {
                this.is_hot = is_hot;
            }

            public String getIs_new() {
                return is_new;
            }

            public void setIs_new(String is_new) {
                this.is_new = is_new;
            }

            public String getIs_best() {
                return is_best;
            }

            public void setIs_best(String is_best) {
                this.is_best = is_best;
            }

            public String getBorrow_amount() {
                return borrow_amount;
            }

            public void setBorrow_amount(String borrow_amount) {
                this.borrow_amount = borrow_amount;
            }

            public String getApart_borrow_amount() {
                return apart_borrow_amount;
            }

            public void setApart_borrow_amount(String apart_borrow_amount) {
                this.apart_borrow_amount = apart_borrow_amount;
            }

            public String getMin_loan_money() {
                return min_loan_money;
            }

            public void setMin_loan_money(String min_loan_money) {
                this.min_loan_money = min_loan_money;
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

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getName_match() {
                return name_match;
            }

            public void setName_match(String name_match) {
                this.name_match = name_match;
            }

            public String getName_match_row() {
                return name_match_row;
            }

            public void setName_match_row(String name_match_row) {
                this.name_match_row = name_match_row;
            }

            public String getDeal_cate_match() {
                return deal_cate_match;
            }

            public void setDeal_cate_match(String deal_cate_match) {
                this.deal_cate_match = deal_cate_match;
            }

            public String getDeal_cate_match_row() {
                return deal_cate_match_row;
            }

            public void setDeal_cate_match_row(String deal_cate_match_row) {
                this.deal_cate_match_row = deal_cate_match_row;
            }

            public String getTag_match() {
                return tag_match;
            }

            public void setTag_match(String tag_match) {
                this.tag_match = tag_match;
            }

            public String getTag_match_row() {
                return tag_match_row;
            }

            public void setTag_match_row(String tag_match_row) {
                this.tag_match_row = tag_match_row;
            }

            public String getType_match() {
                return type_match;
            }

            public void setType_match(String type_match) {
                this.type_match = type_match;
            }

            public String getType_match_row() {
                return type_match_row;
            }

            public void setType_match_row(String type_match_row) {
                this.type_match_row = type_match_row;
            }

            public String getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(String is_recommend) {
                this.is_recommend = is_recommend;
            }

            public String getBuy_count() {
                return buy_count;
            }

            public void setBuy_count(String buy_count) {
                this.buy_count = buy_count;
            }

            public int getLoad_money() {
                return load_money;
            }

            public void setLoad_money(int load_money) {
                this.load_money = load_money;
            }

            public String getRepay_money() {
                return repay_money;
            }

            public void setRepay_money(String repay_money) {
                this.repay_money = repay_money;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getSuccess_time() {
                return success_time;
            }

            public void setSuccess_time(String success_time) {
                this.success_time = success_time;
            }

            public String getRepay_start_time() {
                return repay_start_time;
            }

            public void setRepay_start_time(String repay_start_time) {
                this.repay_start_time = repay_start_time;
            }

            public String getLast_repay_time() {
                return last_repay_time;
            }

            public void setLast_repay_time(String last_repay_time) {
                this.last_repay_time = last_repay_time;
            }

            public int getNext_repay_time() {
                return next_repay_time;
            }

            public void setNext_repay_time(int next_repay_time) {
                this.next_repay_time = next_repay_time;
            }

            public String getBad_time() {
                return bad_time;
            }

            public void setBad_time(String bad_time) {
                this.bad_time = bad_time;
            }

            public int getDeal_status() {
                return deal_status;
            }

            public void setDeal_status(int deal_status) {
                this.deal_status = deal_status;
            }

            public String getEnddate() {
                return enddate;
            }

            public void setEnddate(String enddate) {
                this.enddate = enddate;
            }

            public String getVoffice() {
                return voffice;
            }

            public void setVoffice(String voffice) {
                this.voffice = voffice;
            }

            public String getVposition() {
                return vposition;
            }

            public void setVposition(String vposition) {
                this.vposition = vposition;
            }

            public String getServices_fee() {
                return services_fee;
            }

            public void setServices_fee(String services_fee) {
                this.services_fee = services_fee;
            }

            public String getPublish_wait() {
                return publish_wait;
            }

            public void setPublish_wait(String publish_wait) {
                this.publish_wait = publish_wait;
            }

            public String getIs_send_bad_msg() {
                return is_send_bad_msg;
            }

            public void setIs_send_bad_msg(String is_send_bad_msg) {
                this.is_send_bad_msg = is_send_bad_msg;
            }

            public String getBad_msg() {
                return bad_msg;
            }

            public void setBad_msg(String bad_msg) {
                this.bad_msg = bad_msg;
            }

            public String getSend_half_msg_time() {
                return send_half_msg_time;
            }

            public void setSend_half_msg_time(String send_half_msg_time) {
                this.send_half_msg_time = send_half_msg_time;
            }

            public String getSend_three_msg_time() {
                return send_three_msg_time;
            }

            public void setSend_three_msg_time(String send_three_msg_time) {
                this.send_three_msg_time = send_three_msg_time;
            }

            public String getIs_send_half_msg() {
                return is_send_half_msg;
            }

            public void setIs_send_half_msg(String is_send_half_msg) {
                this.is_send_half_msg = is_send_half_msg;
            }

            public String getIs_has_loans() {
                return is_has_loans;
            }

            public void setIs_has_loans(String is_has_loans) {
                this.is_has_loans = is_has_loans;
            }

            public String getLoantype() {
                return loantype;
            }

            public void setLoantype(String loantype) {
                this.loantype = loantype;
            }

            public String getWarrant() {
                return warrant;
            }

            public void setWarrant(String warrant) {
                this.warrant = warrant;
            }

            public String getTitlecolor() {
                return titlecolor;
            }

            public void setTitlecolor(String titlecolor) {
                this.titlecolor = titlecolor;
            }

            public String getIs_send_contract() {
                return is_send_contract;
            }

            public void setIs_send_contract(String is_send_contract) {
                this.is_send_contract = is_send_contract;
            }

            public String getRepay_time_type() {
                return repay_time_type;
            }

            public void setRepay_time_type(String repay_time_type) {
                this.repay_time_type = repay_time_type;
            }

            public String getMax_loan_money() {
                return max_loan_money;
            }

            public void setMax_loan_money(String max_loan_money) {
                this.max_loan_money = max_loan_money;
            }

            public String getRisk_rank() {
                return risk_rank;
            }

            public void setRisk_rank(String risk_rank) {
                this.risk_rank = risk_rank;
            }

            public String getRisk_security() {
                return risk_security;
            }

            public void setRisk_security(String risk_security) {
                this.risk_security = risk_security;
            }

            public String getIs_send_success_msg() {
                return is_send_success_msg;
            }

            public void setIs_send_success_msg(String is_send_success_msg) {
                this.is_send_success_msg = is_send_success_msg;
            }

            public String getIps_bill_no() {
                return ips_bill_no;
            }

            public void setIps_bill_no(String ips_bill_no) {
                this.ips_bill_no = ips_bill_no;
            }

            public String getGuarantees_money() {
                return guarantees_money;
            }

            public void setGuarantees_money(String guarantees_money) {
                this.guarantees_money = guarantees_money;
            }

            public String getIps_over() {
                return ips_over;
            }

            public void setIps_over(String ips_over) {
                this.ips_over = ips_over;
            }

            public String getDeal_sn() {
                return deal_sn;
            }

            public void setDeal_sn(String deal_sn) {
                this.deal_sn = deal_sn;
            }

            public String getIs_has_received() {
                return is_has_received;
            }

            public void setIs_has_received(String is_has_received) {
                this.is_has_received = is_has_received;
            }

            public String getManage_fee() {
                return manage_fee;
            }

            public void setManage_fee(String manage_fee) {
                this.manage_fee = manage_fee;
            }

            public String getUser_loan_manage_fee() {
                return user_loan_manage_fee;
            }

            public void setUser_loan_manage_fee(String user_loan_manage_fee) {
                this.user_loan_manage_fee = user_loan_manage_fee;
            }

            public String getManage_impose_fee_day1() {
                return manage_impose_fee_day1;
            }

            public void setManage_impose_fee_day1(String manage_impose_fee_day1) {
                this.manage_impose_fee_day1 = manage_impose_fee_day1;
            }

            public String getManage_impose_fee_day2() {
                return manage_impose_fee_day2;
            }

            public void setManage_impose_fee_day2(String manage_impose_fee_day2) {
                this.manage_impose_fee_day2 = manage_impose_fee_day2;
            }

            public String getImpose_fee_day1() {
                return impose_fee_day1;
            }

            public void setImpose_fee_day1(String impose_fee_day1) {
                this.impose_fee_day1 = impose_fee_day1;
            }

            public String getImpose_fee_day2() {
                return impose_fee_day2;
            }

            public void setImpose_fee_day2(String impose_fee_day2) {
                this.impose_fee_day2 = impose_fee_day2;
            }

            public String getUser_load_transfer_fee() {
                return user_load_transfer_fee;
            }

            public void setUser_load_transfer_fee(String user_load_transfer_fee) {
                this.user_load_transfer_fee = user_load_transfer_fee;
            }

            public String getTransfer_day() {
                return transfer_day;
            }

            public void setTransfer_day(String transfer_day) {
                this.transfer_day = transfer_day;
            }

            public String getCompensate_fee() {
                return compensate_fee;
            }

            public void setCompensate_fee(String compensate_fee) {
                this.compensate_fee = compensate_fee;
            }

            public String getIps_do_transfer() {
                return ips_do_transfer;
            }

            public void setIps_do_transfer(String ips_do_transfer) {
                this.ips_do_transfer = ips_do_transfer;
            }

            public String getDelete_msg() {
                return delete_msg;
            }

            public void setDelete_msg(String delete_msg) {
                this.delete_msg = delete_msg;
            }

            public String getUser_bid_rebate() {
                return user_bid_rebate;
            }

            public void setUser_bid_rebate(String user_bid_rebate) {
                this.user_bid_rebate = user_bid_rebate;
            }

            public String getGuarantees_amt() {
                return guarantees_amt;
            }

            public void setGuarantees_amt(String guarantees_amt) {
                this.guarantees_amt = guarantees_amt;
            }

            public String getReal_freezen_amt() {
                return real_freezen_amt;
            }

            public void setReal_freezen_amt(String real_freezen_amt) {
                this.real_freezen_amt = real_freezen_amt;
            }

            public String getUn_real_freezen_amt() {
                return un_real_freezen_amt;
            }

            public void setUn_real_freezen_amt(String un_real_freezen_amt) {
                this.un_real_freezen_amt = un_real_freezen_amt;
            }

            public String getGuarantor_amt() {
                return guarantor_amt;
            }

            public void setGuarantor_amt(String guarantor_amt) {
                this.guarantor_amt = guarantor_amt;
            }

            public String getGuarantor_margin_amt() {
                return guarantor_margin_amt;
            }

            public void setGuarantor_margin_amt(String guarantor_margin_amt) {
                this.guarantor_margin_amt = guarantor_margin_amt;
            }

            public String getGuarantor_real_freezen_amt() {
                return guarantor_real_freezen_amt;
            }

            public void setGuarantor_real_freezen_amt(String guarantor_real_freezen_amt) {
                this.guarantor_real_freezen_amt = guarantor_real_freezen_amt;
            }

            public String getUn_guarantor_real_freezen_amt() {
                return un_guarantor_real_freezen_amt;
            }

            public void setUn_guarantor_real_freezen_amt(String un_guarantor_real_freezen_amt) {
                this.un_guarantor_real_freezen_amt = un_guarantor_real_freezen_amt;
            }

            public String getGuarantor_pro_fit_amt() {
                return guarantor_pro_fit_amt;
            }

            public void setGuarantor_pro_fit_amt(String guarantor_pro_fit_amt) {
                this.guarantor_pro_fit_amt = guarantor_pro_fit_amt;
            }

            public String getGuarantor_real_fit_amt() {
                return guarantor_real_fit_amt;
            }

            public void setGuarantor_real_fit_amt(String guarantor_real_fit_amt) {
                this.guarantor_real_fit_amt = guarantor_real_fit_amt;
            }

            public String getMer_bill_no() {
                return mer_bill_no;
            }

            public void setMer_bill_no(String mer_bill_no) {
                this.mer_bill_no = mer_bill_no;
            }

            public String getIps_guarantor_bill_no() {
                return ips_guarantor_bill_no;
            }

            public void setIps_guarantor_bill_no(String ips_guarantor_bill_no) {
                this.ips_guarantor_bill_no = ips_guarantor_bill_no;
            }

            public String getMer_guarantor_bill_no() {
                return mer_guarantor_bill_no;
            }

            public void setMer_guarantor_bill_no(String mer_guarantor_bill_no) {
                this.mer_guarantor_bill_no = mer_guarantor_bill_no;
            }

            public String getView_info() {
                return view_info;
            }

            public void setView_info(String view_info) {
                this.view_info = view_info;
            }

            public String getGeneration_position() {
                return generation_position;
            }

            public void setGeneration_position(String generation_position) {
                this.generation_position = generation_position;
            }

            public String getUloadtype() {
                return uloadtype;
            }

            public void setUloadtype(String uloadtype) {
                this.uloadtype = uloadtype;
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

            public String getStart_date() {
                return start_date;
            }

            public void setStart_date(String start_date) {
                this.start_date = start_date;
            }

            public String getRepay_start_date() {
                return repay_start_date;
            }

            public void setRepay_start_date(String repay_start_date) {
                this.repay_start_date = repay_start_date;
            }

            public String getBad_date() {
                return bad_date;
            }

            public void setBad_date(String bad_date) {
                this.bad_date = bad_date;
            }

            public String getContract_id() {
                return contract_id;
            }

            public void setContract_id(String contract_id) {
                this.contract_id = contract_id;
            }

            public String getTcontract_id() {
                return tcontract_id;
            }

            public void setTcontract_id(String tcontract_id) {
                this.tcontract_id = tcontract_id;
            }

            public String getIs_advance() {
                return is_advance;
            }

            public void setIs_advance(String is_advance) {
                this.is_advance = is_advance;
            }

            public String getIs_hidden() {
                return is_hidden;
            }

            public void setIs_hidden(String is_hidden) {
                this.is_hidden = is_hidden;
            }

            public String getPeizi_order_ids() {
                return peizi_order_ids;
            }

            public void setPeizi_order_ids(String peizi_order_ids) {
                this.peizi_order_ids = peizi_order_ids;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getUser_bid_score_fee() {
                return user_bid_score_fee;
            }

            public void setUser_bid_score_fee(String user_bid_score_fee) {
                this.user_bid_score_fee = user_bid_score_fee;
            }

            public String getUser_loan_interest_manage_fee() {
                return user_loan_interest_manage_fee;
            }

            public void setUser_loan_interest_manage_fee(String user_loan_interest_manage_fee) {
                this.user_loan_interest_manage_fee = user_loan_interest_manage_fee;
            }

            public String getAttachment() {
                return attachment;
            }

            public void setAttachment(String attachment) {
                this.attachment = attachment;
            }

            public String getTattachment() {
                return tattachment;
            }

            public void setTattachment(String tattachment) {
                this.tattachment = tattachment;
            }

            public String getPublish_memo() {
                return publish_memo;
            }

            public void setPublish_memo(String publish_memo) {
                this.publish_memo = publish_memo;
            }

            public String getIs_index_show() {
                return is_index_show;
            }

            public void setIs_index_show(String is_index_show) {
                this.is_index_show = is_index_show;
            }

            public String getLoans_pic() {
                return loans_pic;
            }

            public void setLoans_pic(String loans_pic) {
                this.loans_pic = loans_pic;
            }

            public String getAdmin_id() {
                return admin_id;
            }

            public void setAdmin_id(String admin_id) {
                this.admin_id = admin_id;
            }

            public String getCustomers_id() {
                return customers_id;
            }

            public void setCustomers_id(String customers_id) {
                this.customers_id = customers_id;
            }

            public String getUse_rate() {
                return use_rate;
            }

            public void setUse_rate(String use_rate) {
                this.use_rate = use_rate;
            }

            public String getUse_ecv() {
                return use_ecv;
            }

            public void setUse_ecv(String use_ecv) {
                this.use_ecv = use_ecv;
            }

            public String getIs_mortgage() {
                return is_mortgage;
            }

            public void setIs_mortgage(String is_mortgage) {
                this.is_mortgage = is_mortgage;
            }

            public String getMortgage_desc() {
                return mortgage_desc;
            }

            public void setMortgage_desc(String mortgage_desc) {
                this.mortgage_desc = mortgage_desc;
            }

            public String getMortgage_infos() {
                return mortgage_infos;
            }

            public void setMortgage_infos(String mortgage_infos) {
                this.mortgage_infos = mortgage_infos;
            }

            public String getMortgage_contract() {
                return mortgage_contract;
            }

            public void setMortgage_contract(String mortgage_contract) {
                this.mortgage_contract = mortgage_contract;
            }

            public int getMortgage_fee() {
                return mortgage_fee;
            }

            public void setMortgage_fee(int mortgage_fee) {
                this.mortgage_fee = mortgage_fee;
            }

            public String getAutobid_handle() {
                return autobid_handle;
            }

            public void setAutobid_handle(String autobid_handle) {
                this.autobid_handle = autobid_handle;
            }

            public String getIs_users() {
                return is_users;
            }

            public void setIs_users(String is_users) {
                this.is_users = is_users;
            }

            public String getSealstep() {
                return sealstep;
            }

            public void setSealstep(String sealstep) {
                this.sealstep = sealstep;
            }

            public String getExtra_rate() {
                return extra_rate;
            }

            public void setExtra_rate(String extra_rate) {
                this.extra_rate = extra_rate;
            }

            public int getIs_wait() {
                return is_wait;
            }

            public void setIs_wait(int is_wait) {
                this.is_wait = is_wait;
            }

            public int getRemain_time() {
                return remain_time;
            }

            public void setRemain_time(int remain_time) {
                this.remain_time = remain_time;
            }

            public String getLoantype_format() {
                return loantype_format;
            }

            public void setLoantype_format(String loantype_format) {
                this.loantype_format = loantype_format;
            }

            public String getColor_name() {
                return color_name;
            }

            public void setColor_name(String color_name) {
                this.color_name = color_name;
            }

            public String getBorrow_amount_format() {
                return borrow_amount_format;
            }

            public void setBorrow_amount_format(String borrow_amount_format) {
                this.borrow_amount_format = borrow_amount_format;
            }

            public String getLoad_money_format() {
                return load_money_format;
            }

            public void setLoad_money_format(String load_money_format) {
                this.load_money_format = load_money_format;
            }

            public String getRate_foramt() {
                return rate_foramt;
            }

            public void setRate_foramt(String rate_foramt) {
                this.rate_foramt = rate_foramt;
            }

            public String getCreate_time_format() {
                return create_time_format;
            }

            public void setCreate_time_format(String create_time_format) {
                this.create_time_format = create_time_format;
            }

            public String getRate_foramt_w() {
                return rate_foramt_w;
            }

            public void setRate_foramt_w(String rate_foramt_w) {
                this.rate_foramt_w = rate_foramt_w;
            }

            public double getMonth_repay_money() {
                return month_repay_money;
            }

            public void setMonth_repay_money(double month_repay_money) {
                this.month_repay_money = month_repay_money;
            }

            public double getLast_month_repay_money() {
                return last_month_repay_money;
            }

            public void setLast_month_repay_money(double last_month_repay_money) {
                this.last_month_repay_money = last_month_repay_money;
            }

            public String getMonth_repay_money_format() {
                return month_repay_money_format;
            }

            public void setMonth_repay_money_format(String month_repay_money_format) {
                this.month_repay_money_format = month_repay_money_format;
            }

            public int getMonth_manage_money() {
                return month_manage_money;
            }

            public void setMonth_manage_money(int month_manage_money) {
                this.month_manage_money = month_manage_money;
            }

            public String getMonth_manage_money_format() {
                return month_manage_money_format;
            }

            public void setMonth_manage_money_format(String month_manage_money_format) {
                this.month_manage_money_format = month_manage_money_format;
            }

            public int getAll_manage_money() {
                return all_manage_money;
            }

            public void setAll_manage_money(int all_manage_money) {
                this.all_manage_money = all_manage_money;
            }

            public double getTrue_month_repay_money() {
                return true_month_repay_money;
            }

            public void setTrue_month_repay_money(double true_month_repay_money) {
                this.true_month_repay_money = true_month_repay_money;
            }

            public double getTrue_last_month_repay_money() {
                return true_last_month_repay_money;
            }

            public void setTrue_last_month_repay_money(double true_last_month_repay_money) {
                this.true_last_month_repay_money = true_last_month_repay_money;
            }

            public String getTrue_month_repay_money_format() {
                return true_month_repay_money_format;
            }

            public void setTrue_month_repay_money_format(String true_month_repay_money_format) {
                this.true_month_repay_money_format = true_month_repay_money_format;
            }

            public String getNeed_money() {
                return need_money;
            }

            public void setNeed_money(String need_money) {
                this.need_money = need_money;
            }

            public double getProgress_point() {
                return progress_point;
            }

            public void setProgress_point(double progress_point) {
                this.progress_point = progress_point;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public CateInfoBean getCate_info() {
                return cate_info;
            }

            public void setCate_info(CateInfoBean cate_info) {
                this.cate_info = cate_info;
            }

            public TypeInfoBean getType_info() {
                return type_info;
            }

            public void setType_info(TypeInfoBean type_info) {
                this.type_info = type_info;
            }

            public int getAll_mortgage_fee() {
                return all_mortgage_fee;
            }

            public void setAll_mortgage_fee(int all_mortgage_fee) {
                this.all_mortgage_fee = all_mortgage_fee;
            }

            public String getRemain_time_format() {
                return remain_time_format;
            }

            public void setRemain_time_format(String remain_time_format) {
                this.remain_time_format = remain_time_format;
            }

            public String getMin_loan_money_format() {
                return min_loan_money_format;
            }

            public void setMin_loan_money_format(String min_loan_money_format) {
                this.min_loan_money_format = min_loan_money_format;
            }

            public double getRemain_repay_money() {
                return remain_repay_money;
            }

            public void setRemain_repay_money(double remain_repay_money) {
                this.remain_repay_money = remain_repay_money;
            }

            public double getNeed_remain_repay_money() {
                return need_remain_repay_money;
            }

            public void setNeed_remain_repay_money(double need_remain_repay_money) {
                this.need_remain_repay_money = need_remain_repay_money;
            }

            public double getRepay_progress_point() {
                return repay_progress_point;
            }

            public void setRepay_progress_point(double repay_progress_point) {
                this.repay_progress_point = repay_progress_point;
            }

            public int getEnd_repay_time() {
                return end_repay_time;
            }

            public void setEnd_repay_time(int end_repay_time) {
                this.end_repay_time = end_repay_time;
            }

            public String getNext_repay_time_format() {
                return next_repay_time_format;
            }

            public void setNext_repay_time_format(String next_repay_time_format) {
                this.next_repay_time_format = next_repay_time_format;
            }

            public String getPublish_time_format() {
                return publish_time_format;
            }

            public void setPublish_time_format(String publish_time_format) {
                this.publish_time_format = publish_time_format;
            }

            public String getShare_url() {
                return share_url;
            }

            public void setShare_url(String share_url) {
                this.share_url = share_url;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getApp_url() {
                return app_url;
            }

            public void setApp_url(String app_url) {
                this.app_url = app_url;
            }

            public List<?> getMortgage_contract_list() {
                return mortgage_contract_list;
            }

            public void setMortgage_contract_list(List<?> mortgage_contract_list) {
                this.mortgage_contract_list = mortgage_contract_list;
            }

            public static class UserBean {
                /**
                 * id : 757
                 * user_name : 安慕希2号
                 * short_name : null
                 * brief : null
                 * header : null
                 * user_img_url : null
                 * company_brief : null
                 * history : null
                 * content : null
                 * sort : null
                 * acct_type : null
                 * user_pwd : 756f566af74f123732bf2d4b099d9e2e
                 * create_time : 1524419214
                 * update_time : 1524510302
                 * login_ip : 127.0.0.1
                 * group_id : 1
                 * is_effect : 1
                 * is_delete : 0
                 * is_new_user : 0
                 * email : 13552032668@163.com
                 * idno : 412723194106161565
                 * idcardpassed : 0
                 * idcardpassed_time : 0
                 * real_name : 李会锋
                 * mobile : 13685214563
                 * mobilepassed : 1
                 * score : 0
                 * money : 79166.68
                 * quota : 500
                 * lock_money : 0.00
                 * verify :
                 * code :
                 * pid : 0
                 * referer_memo : 13552032668
                 * login_time : 1525389554
                 * referral_count : 0
                 * password_verify :
                 * integrate_id : 0
                 * sina_id :
                 * renren_id : 0
                 * kaixin_id : 0
                 * sohu_id : 0
                 * bind_verify :
                 * verify_create_time : 0
                 * tencent_id :
                 * referer :
                 * login_pay_time : 0
                 * focus_count : 0
                 * focused_count : 0
                 * n_province_id : 2
                 * n_city_id : 52
                 * province_id : 11
                 * city_id : 149
                 * sex : 1
                 * step : 0
                 * byear : 1991
                 * bmonth : 10
                 * bday : 24
                 * graduation : 本科
                 * graduatedyear : 2018
                 * university :
                 * edu_validcode :
                 * has_send_video : 0
                 * marriage : 未婚
                 * haschild : 0
                 * hashouse : 0
                 * houseloan : 0
                 * hascar : 0
                 * carloan : 0
                 * car_brand :
                 * car_year : 0
                 * car_number :
                 * address : 北京市海淀区友谊路
                 * phone :
                 * postcode :
                 * locate_time : 1525389491
                 * xpoint : 0.000000
                 * ypoint : 0.000000
                 * topic_count : 0
                 * fav_count : 0
                 * faved_count : 0
                 * insite_count : 0
                 * outsite_count : 0
                 * level_id : 1
                 * point : 21
                 * sina_app_key :
                 * sina_app_secret :
                 * is_syn_sina :
                 * tencent_app_key :
                 * tencent_app_secret :
                 * is_syn_tencent : 0
                 * t_access_token :
                 * t_openkey :
                 * t_openid :
                 * sina_token :
                 * is_borrow_out : 0
                 * is_borrow_int : 0
                 * creditpassed : 0
                 * creditpassed_time : 0
                 * workpassed : 0
                 * workpassed_time : 0
                 * incomepassed : 0
                 * incomepassed_time : 0
                 * housepassed : 0
                 * housepassed_time : 0
                 * carpassed : 0
                 * carpassed_time : 0
                 * marrypassed : 0
                 * marrypassed_time : 0
                 * edupassed : 0
                 * edupassed_time : 0
                 * skillpassed : 0
                 * skillpassed_time : 0
                 * videopassed : 0
                 * videopassed_time : 0
                 * mobiletruepassed : 0
                 * mobiletruepassed_time : 0
                 * residencepassed : 0
                 * residencepassed_time : 0
                 * alipay_id :
                 * qq_id :
                 * taobao_id :
                 * info_down :
                 * sealpassed : 0
                 * paypassword : 4607e782c4d86fd5364d7e4508bb10d9
                 * apns_code : null
                 * ips_acct_no : null
                 * emailpassed : 0
                 * tmp_email :
                 * view_info :
                 * referral_rate : 0.0000
                 * user_type : 0
                 * create_date : 2018-04-23
                 * register_ip : 127.0.0.1
                 * admin_id : 0
                 * customer_id : 0
                 * is_black : 0
                 * vip_id : 2
                 * vip_state : 1
                 * nmc_amount : 0.00
                 * ips_mer_code : null
                 * enterpriseName :
                 * bankLicense :
                 * orgNo :
                 * businessLicense :
                 * taxNo :
                 * u_year : null
                 * u_special :
                 * u_alipay :
                 * wx_openid :
                 * total_invite_borrow_money : 0.00
                 * total_invite_invest_money : 0.00
                 * vip_end_time : 0
                 * platform :
                 * platform_is_cash : 0
                 * platform_is_ecv : 0
                 * platform_is_rate : 0
                 * bid_start : 10000
                 * reg_type : 1
                 * is_virtual : 0
                 * point_level : HR
                 * url : /index.php?ctl=space&id=757
                 * region_city : 郑州
                 * region : 郑州
                 * region_province : 河南
                 * workinfo : false
                 */

                private String id;
                private String user_name;
                private Object short_name;
                private Object brief;
                private Object header;
                private Object user_img_url;
                private Object company_brief;
                private Object history;
                private Object content;
                private Object sort;
                private Object acct_type;
                private String user_pwd;
                private String create_time;
                private String update_time;
                private String login_ip;
                private String group_id;
                private String is_effect;
                private String is_delete;
                private String is_new_user;
                private String email;
                private String idno;
                private String idcardpassed;
                private String idcardpassed_time;
                private String real_name;
                private String mobile;
                private String mobilepassed;
                private String score;
                private String money;
                private String quota;
                private String lock_money;
                private String verify;
                private String code;
                private String pid;
                private String referer_memo;
                private String login_time;
                private String referral_count;
                private String password_verify;
                private String integrate_id;
                private String sina_id;
                private String renren_id;
                private String kaixin_id;
                private String sohu_id;
                private String bind_verify;
                private String verify_create_time;
                private String tencent_id;
                private String referer;
                private String login_pay_time;
                private String focus_count;
                private String focused_count;
                private String n_province_id;
                private String n_city_id;
                private String province_id;
                private String city_id;
                private String sex;
                private String step;
                private String byear;
                private String bmonth;
                private String bday;
                private String graduation;
                private String graduatedyear;
                private String university;
                private String edu_validcode;
                private String has_send_video;
                private String marriage;
                private String haschild;
                private String hashouse;
                private String houseloan;
                private String hascar;
                private String carloan;
                private String car_brand;
                private String car_year;
                private String car_number;
                private String address;
                private String phone;
                private String postcode;
                private String locate_time;
                private String xpoint;
                private String ypoint;
                private String topic_count;
                private String fav_count;
                private String faved_count;
                private String insite_count;
                private String outsite_count;
                private String level_id;
                private String point;
                private String sina_app_key;
                private String sina_app_secret;
                private String is_syn_sina;
                private String tencent_app_key;
                private String tencent_app_secret;
                private String is_syn_tencent;
                private String t_access_token;
                private String t_openkey;
                private String t_openid;
                private String sina_token;
                private String is_borrow_out;
                private String is_borrow_int;
                private String creditpassed;
                private String creditpassed_time;
                private String workpassed;
                private String workpassed_time;
                private String incomepassed;
                private String incomepassed_time;
                private String housepassed;
                private String housepassed_time;
                private String carpassed;
                private String carpassed_time;
                private String marrypassed;
                private String marrypassed_time;
                private String edupassed;
                private String edupassed_time;
                private String skillpassed;
                private String skillpassed_time;
                private String videopassed;
                private String videopassed_time;
                private String mobiletruepassed;
                private String mobiletruepassed_time;
                private String residencepassed;
                private String residencepassed_time;
                private String alipay_id;
                private String qq_id;
                private String taobao_id;
                private String info_down;
                private String sealpassed;
                private String paypassword;
                private Object apns_code;
                private Object ips_acct_no;
                private String emailpassed;
                private String tmp_email;
                private String view_info;
                private String referral_rate;
                private String user_type;
                private String create_date;
                private String register_ip;
                private String admin_id;
                private String customer_id;
                private String is_black;
                private String vip_id;
                private String vip_state;
                private String nmc_amount;
                private Object ips_mer_code;
                private String enterpriseName;
                private String bankLicense;
                private String orgNo;
                private String businessLicense;
                private String taxNo;
                private Object u_year;
                private String u_special;
                private String u_alipay;
                private String wx_openid;
                private String total_invite_borrow_money;
                private String total_invite_invest_money;
                private String vip_end_time;
                private String platform;
                private String platform_is_cash;
                private String platform_is_ecv;
                private String platform_is_rate;
                private String bid_start;
                private String reg_type;
                private String is_virtual;
                private String point_level;
                private String url;
                private String region_city;
                private String region;
                private String region_province;
                private boolean workinfo;

                public static UserBean objectFromData(String str) {

                    return new Gson().fromJson(str, UserBean.class);
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public Object getShort_name() {
                    return short_name;
                }

                public void setShort_name(Object short_name) {
                    this.short_name = short_name;
                }

                public Object getBrief() {
                    return brief;
                }

                public void setBrief(Object brief) {
                    this.brief = brief;
                }

                public Object getHeader() {
                    return header;
                }

                public void setHeader(Object header) {
                    this.header = header;
                }

                public Object getUser_img_url() {
                    return user_img_url;
                }

                public void setUser_img_url(Object user_img_url) {
                    this.user_img_url = user_img_url;
                }

                public Object getCompany_brief() {
                    return company_brief;
                }

                public void setCompany_brief(Object company_brief) {
                    this.company_brief = company_brief;
                }

                public Object getHistory() {
                    return history;
                }

                public void setHistory(Object history) {
                    this.history = history;
                }

                public Object getContent() {
                    return content;
                }

                public void setContent(Object content) {
                    this.content = content;
                }

                public Object getSort() {
                    return sort;
                }

                public void setSort(Object sort) {
                    this.sort = sort;
                }

                public Object getAcct_type() {
                    return acct_type;
                }

                public void setAcct_type(Object acct_type) {
                    this.acct_type = acct_type;
                }

                public String getUser_pwd() {
                    return user_pwd;
                }

                public void setUser_pwd(String user_pwd) {
                    this.user_pwd = user_pwd;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public String getUpdate_time() {
                    return update_time;
                }

                public void setUpdate_time(String update_time) {
                    this.update_time = update_time;
                }

                public String getLogin_ip() {
                    return login_ip;
                }

                public void setLogin_ip(String login_ip) {
                    this.login_ip = login_ip;
                }

                public String getGroup_id() {
                    return group_id;
                }

                public void setGroup_id(String group_id) {
                    this.group_id = group_id;
                }

                public String getIs_effect() {
                    return is_effect;
                }

                public void setIs_effect(String is_effect) {
                    this.is_effect = is_effect;
                }

                public String getIs_delete() {
                    return is_delete;
                }

                public void setIs_delete(String is_delete) {
                    this.is_delete = is_delete;
                }

                public String getIs_new_user() {
                    return is_new_user;
                }

                public void setIs_new_user(String is_new_user) {
                    this.is_new_user = is_new_user;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public String getIdno() {
                    return idno;
                }

                public void setIdno(String idno) {
                    this.idno = idno;
                }

                public String getIdcardpassed() {
                    return idcardpassed;
                }

                public void setIdcardpassed(String idcardpassed) {
                    this.idcardpassed = idcardpassed;
                }

                public String getIdcardpassed_time() {
                    return idcardpassed_time;
                }

                public void setIdcardpassed_time(String idcardpassed_time) {
                    this.idcardpassed_time = idcardpassed_time;
                }

                public String getReal_name() {
                    return real_name;
                }

                public void setReal_name(String real_name) {
                    this.real_name = real_name;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getMobilepassed() {
                    return mobilepassed;
                }

                public void setMobilepassed(String mobilepassed) {
                    this.mobilepassed = mobilepassed;
                }

                public String getScore() {
                    return score;
                }

                public void setScore(String score) {
                    this.score = score;
                }

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
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

                public String getVerify() {
                    return verify;
                }

                public void setVerify(String verify) {
                    this.verify = verify;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getReferer_memo() {
                    return referer_memo;
                }

                public void setReferer_memo(String referer_memo) {
                    this.referer_memo = referer_memo;
                }

                public String getLogin_time() {
                    return login_time;
                }

                public void setLogin_time(String login_time) {
                    this.login_time = login_time;
                }

                public String getReferral_count() {
                    return referral_count;
                }

                public void setReferral_count(String referral_count) {
                    this.referral_count = referral_count;
                }

                public String getPassword_verify() {
                    return password_verify;
                }

                public void setPassword_verify(String password_verify) {
                    this.password_verify = password_verify;
                }

                public String getIntegrate_id() {
                    return integrate_id;
                }

                public void setIntegrate_id(String integrate_id) {
                    this.integrate_id = integrate_id;
                }

                public String getSina_id() {
                    return sina_id;
                }

                public void setSina_id(String sina_id) {
                    this.sina_id = sina_id;
                }

                public String getRenren_id() {
                    return renren_id;
                }

                public void setRenren_id(String renren_id) {
                    this.renren_id = renren_id;
                }

                public String getKaixin_id() {
                    return kaixin_id;
                }

                public void setKaixin_id(String kaixin_id) {
                    this.kaixin_id = kaixin_id;
                }

                public String getSohu_id() {
                    return sohu_id;
                }

                public void setSohu_id(String sohu_id) {
                    this.sohu_id = sohu_id;
                }

                public String getBind_verify() {
                    return bind_verify;
                }

                public void setBind_verify(String bind_verify) {
                    this.bind_verify = bind_verify;
                }

                public String getVerify_create_time() {
                    return verify_create_time;
                }

                public void setVerify_create_time(String verify_create_time) {
                    this.verify_create_time = verify_create_time;
                }

                public String getTencent_id() {
                    return tencent_id;
                }

                public void setTencent_id(String tencent_id) {
                    this.tencent_id = tencent_id;
                }

                public String getReferer() {
                    return referer;
                }

                public void setReferer(String referer) {
                    this.referer = referer;
                }

                public String getLogin_pay_time() {
                    return login_pay_time;
                }

                public void setLogin_pay_time(String login_pay_time) {
                    this.login_pay_time = login_pay_time;
                }

                public String getFocus_count() {
                    return focus_count;
                }

                public void setFocus_count(String focus_count) {
                    this.focus_count = focus_count;
                }

                public String getFocused_count() {
                    return focused_count;
                }

                public void setFocused_count(String focused_count) {
                    this.focused_count = focused_count;
                }

                public String getN_province_id() {
                    return n_province_id;
                }

                public void setN_province_id(String n_province_id) {
                    this.n_province_id = n_province_id;
                }

                public String getN_city_id() {
                    return n_city_id;
                }

                public void setN_city_id(String n_city_id) {
                    this.n_city_id = n_city_id;
                }

                public String getProvince_id() {
                    return province_id;
                }

                public void setProvince_id(String province_id) {
                    this.province_id = province_id;
                }

                public String getCity_id() {
                    return city_id;
                }

                public void setCity_id(String city_id) {
                    this.city_id = city_id;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getStep() {
                    return step;
                }

                public void setStep(String step) {
                    this.step = step;
                }

                public String getByear() {
                    return byear;
                }

                public void setByear(String byear) {
                    this.byear = byear;
                }

                public String getBmonth() {
                    return bmonth;
                }

                public void setBmonth(String bmonth) {
                    this.bmonth = bmonth;
                }

                public String getBday() {
                    return bday;
                }

                public void setBday(String bday) {
                    this.bday = bday;
                }

                public String getGraduation() {
                    return graduation;
                }

                public void setGraduation(String graduation) {
                    this.graduation = graduation;
                }

                public String getGraduatedyear() {
                    return graduatedyear;
                }

                public void setGraduatedyear(String graduatedyear) {
                    this.graduatedyear = graduatedyear;
                }

                public String getUniversity() {
                    return university;
                }

                public void setUniversity(String university) {
                    this.university = university;
                }

                public String getEdu_validcode() {
                    return edu_validcode;
                }

                public void setEdu_validcode(String edu_validcode) {
                    this.edu_validcode = edu_validcode;
                }

                public String getHas_send_video() {
                    return has_send_video;
                }

                public void setHas_send_video(String has_send_video) {
                    this.has_send_video = has_send_video;
                }

                public String getMarriage() {
                    return marriage;
                }

                public void setMarriage(String marriage) {
                    this.marriage = marriage;
                }

                public String getHaschild() {
                    return haschild;
                }

                public void setHaschild(String haschild) {
                    this.haschild = haschild;
                }

                public String getHashouse() {
                    return hashouse;
                }

                public void setHashouse(String hashouse) {
                    this.hashouse = hashouse;
                }

                public String getHouseloan() {
                    return houseloan;
                }

                public void setHouseloan(String houseloan) {
                    this.houseloan = houseloan;
                }

                public String getHascar() {
                    return hascar;
                }

                public void setHascar(String hascar) {
                    this.hascar = hascar;
                }

                public String getCarloan() {
                    return carloan;
                }

                public void setCarloan(String carloan) {
                    this.carloan = carloan;
                }

                public String getCar_brand() {
                    return car_brand;
                }

                public void setCar_brand(String car_brand) {
                    this.car_brand = car_brand;
                }

                public String getCar_year() {
                    return car_year;
                }

                public void setCar_year(String car_year) {
                    this.car_year = car_year;
                }

                public String getCar_number() {
                    return car_number;
                }

                public void setCar_number(String car_number) {
                    this.car_number = car_number;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getPostcode() {
                    return postcode;
                }

                public void setPostcode(String postcode) {
                    this.postcode = postcode;
                }

                public String getLocate_time() {
                    return locate_time;
                }

                public void setLocate_time(String locate_time) {
                    this.locate_time = locate_time;
                }

                public String getXpoint() {
                    return xpoint;
                }

                public void setXpoint(String xpoint) {
                    this.xpoint = xpoint;
                }

                public String getYpoint() {
                    return ypoint;
                }

                public void setYpoint(String ypoint) {
                    this.ypoint = ypoint;
                }

                public String getTopic_count() {
                    return topic_count;
                }

                public void setTopic_count(String topic_count) {
                    this.topic_count = topic_count;
                }

                public String getFav_count() {
                    return fav_count;
                }

                public void setFav_count(String fav_count) {
                    this.fav_count = fav_count;
                }

                public String getFaved_count() {
                    return faved_count;
                }

                public void setFaved_count(String faved_count) {
                    this.faved_count = faved_count;
                }

                public String getInsite_count() {
                    return insite_count;
                }

                public void setInsite_count(String insite_count) {
                    this.insite_count = insite_count;
                }

                public String getOutsite_count() {
                    return outsite_count;
                }

                public void setOutsite_count(String outsite_count) {
                    this.outsite_count = outsite_count;
                }

                public String getLevel_id() {
                    return level_id;
                }

                public void setLevel_id(String level_id) {
                    this.level_id = level_id;
                }

                public String getPoint() {
                    return point;
                }

                public void setPoint(String point) {
                    this.point = point;
                }

                public String getSina_app_key() {
                    return sina_app_key;
                }

                public void setSina_app_key(String sina_app_key) {
                    this.sina_app_key = sina_app_key;
                }

                public String getSina_app_secret() {
                    return sina_app_secret;
                }

                public void setSina_app_secret(String sina_app_secret) {
                    this.sina_app_secret = sina_app_secret;
                }

                public String getIs_syn_sina() {
                    return is_syn_sina;
                }

                public void setIs_syn_sina(String is_syn_sina) {
                    this.is_syn_sina = is_syn_sina;
                }

                public String getTencent_app_key() {
                    return tencent_app_key;
                }

                public void setTencent_app_key(String tencent_app_key) {
                    this.tencent_app_key = tencent_app_key;
                }

                public String getTencent_app_secret() {
                    return tencent_app_secret;
                }

                public void setTencent_app_secret(String tencent_app_secret) {
                    this.tencent_app_secret = tencent_app_secret;
                }

                public String getIs_syn_tencent() {
                    return is_syn_tencent;
                }

                public void setIs_syn_tencent(String is_syn_tencent) {
                    this.is_syn_tencent = is_syn_tencent;
                }

                public String getT_access_token() {
                    return t_access_token;
                }

                public void setT_access_token(String t_access_token) {
                    this.t_access_token = t_access_token;
                }

                public String getT_openkey() {
                    return t_openkey;
                }

                public void setT_openkey(String t_openkey) {
                    this.t_openkey = t_openkey;
                }

                public String getT_openid() {
                    return t_openid;
                }

                public void setT_openid(String t_openid) {
                    this.t_openid = t_openid;
                }

                public String getSina_token() {
                    return sina_token;
                }

                public void setSina_token(String sina_token) {
                    this.sina_token = sina_token;
                }

                public String getIs_borrow_out() {
                    return is_borrow_out;
                }

                public void setIs_borrow_out(String is_borrow_out) {
                    this.is_borrow_out = is_borrow_out;
                }

                public String getIs_borrow_int() {
                    return is_borrow_int;
                }

                public void setIs_borrow_int(String is_borrow_int) {
                    this.is_borrow_int = is_borrow_int;
                }

                public String getCreditpassed() {
                    return creditpassed;
                }

                public void setCreditpassed(String creditpassed) {
                    this.creditpassed = creditpassed;
                }

                public String getCreditpassed_time() {
                    return creditpassed_time;
                }

                public void setCreditpassed_time(String creditpassed_time) {
                    this.creditpassed_time = creditpassed_time;
                }

                public String getWorkpassed() {
                    return workpassed;
                }

                public void setWorkpassed(String workpassed) {
                    this.workpassed = workpassed;
                }

                public String getWorkpassed_time() {
                    return workpassed_time;
                }

                public void setWorkpassed_time(String workpassed_time) {
                    this.workpassed_time = workpassed_time;
                }

                public String getIncomepassed() {
                    return incomepassed;
                }

                public void setIncomepassed(String incomepassed) {
                    this.incomepassed = incomepassed;
                }

                public String getIncomepassed_time() {
                    return incomepassed_time;
                }

                public void setIncomepassed_time(String incomepassed_time) {
                    this.incomepassed_time = incomepassed_time;
                }

                public String getHousepassed() {
                    return housepassed;
                }

                public void setHousepassed(String housepassed) {
                    this.housepassed = housepassed;
                }

                public String getHousepassed_time() {
                    return housepassed_time;
                }

                public void setHousepassed_time(String housepassed_time) {
                    this.housepassed_time = housepassed_time;
                }

                public String getCarpassed() {
                    return carpassed;
                }

                public void setCarpassed(String carpassed) {
                    this.carpassed = carpassed;
                }

                public String getCarpassed_time() {
                    return carpassed_time;
                }

                public void setCarpassed_time(String carpassed_time) {
                    this.carpassed_time = carpassed_time;
                }

                public String getMarrypassed() {
                    return marrypassed;
                }

                public void setMarrypassed(String marrypassed) {
                    this.marrypassed = marrypassed;
                }

                public String getMarrypassed_time() {
                    return marrypassed_time;
                }

                public void setMarrypassed_time(String marrypassed_time) {
                    this.marrypassed_time = marrypassed_time;
                }

                public String getEdupassed() {
                    return edupassed;
                }

                public void setEdupassed(String edupassed) {
                    this.edupassed = edupassed;
                }

                public String getEdupassed_time() {
                    return edupassed_time;
                }

                public void setEdupassed_time(String edupassed_time) {
                    this.edupassed_time = edupassed_time;
                }

                public String getSkillpassed() {
                    return skillpassed;
                }

                public void setSkillpassed(String skillpassed) {
                    this.skillpassed = skillpassed;
                }

                public String getSkillpassed_time() {
                    return skillpassed_time;
                }

                public void setSkillpassed_time(String skillpassed_time) {
                    this.skillpassed_time = skillpassed_time;
                }

                public String getVideopassed() {
                    return videopassed;
                }

                public void setVideopassed(String videopassed) {
                    this.videopassed = videopassed;
                }

                public String getVideopassed_time() {
                    return videopassed_time;
                }

                public void setVideopassed_time(String videopassed_time) {
                    this.videopassed_time = videopassed_time;
                }

                public String getMobiletruepassed() {
                    return mobiletruepassed;
                }

                public void setMobiletruepassed(String mobiletruepassed) {
                    this.mobiletruepassed = mobiletruepassed;
                }

                public String getMobiletruepassed_time() {
                    return mobiletruepassed_time;
                }

                public void setMobiletruepassed_time(String mobiletruepassed_time) {
                    this.mobiletruepassed_time = mobiletruepassed_time;
                }

                public String getResidencepassed() {
                    return residencepassed;
                }

                public void setResidencepassed(String residencepassed) {
                    this.residencepassed = residencepassed;
                }

                public String getResidencepassed_time() {
                    return residencepassed_time;
                }

                public void setResidencepassed_time(String residencepassed_time) {
                    this.residencepassed_time = residencepassed_time;
                }

                public String getAlipay_id() {
                    return alipay_id;
                }

                public void setAlipay_id(String alipay_id) {
                    this.alipay_id = alipay_id;
                }

                public String getQq_id() {
                    return qq_id;
                }

                public void setQq_id(String qq_id) {
                    this.qq_id = qq_id;
                }

                public String getTaobao_id() {
                    return taobao_id;
                }

                public void setTaobao_id(String taobao_id) {
                    this.taobao_id = taobao_id;
                }

                public String getInfo_down() {
                    return info_down;
                }

                public void setInfo_down(String info_down) {
                    this.info_down = info_down;
                }

                public String getSealpassed() {
                    return sealpassed;
                }

                public void setSealpassed(String sealpassed) {
                    this.sealpassed = sealpassed;
                }

                public String getPaypassword() {
                    return paypassword;
                }

                public void setPaypassword(String paypassword) {
                    this.paypassword = paypassword;
                }

                public Object getApns_code() {
                    return apns_code;
                }

                public void setApns_code(Object apns_code) {
                    this.apns_code = apns_code;
                }

                public Object getIps_acct_no() {
                    return ips_acct_no;
                }

                public void setIps_acct_no(Object ips_acct_no) {
                    this.ips_acct_no = ips_acct_no;
                }

                public String getEmailpassed() {
                    return emailpassed;
                }

                public void setEmailpassed(String emailpassed) {
                    this.emailpassed = emailpassed;
                }

                public String getTmp_email() {
                    return tmp_email;
                }

                public void setTmp_email(String tmp_email) {
                    this.tmp_email = tmp_email;
                }

                public String getView_info() {
                    return view_info;
                }

                public void setView_info(String view_info) {
                    this.view_info = view_info;
                }

                public String getReferral_rate() {
                    return referral_rate;
                }

                public void setReferral_rate(String referral_rate) {
                    this.referral_rate = referral_rate;
                }

                public String getUser_type() {
                    return user_type;
                }

                public void setUser_type(String user_type) {
                    this.user_type = user_type;
                }

                public String getCreate_date() {
                    return create_date;
                }

                public void setCreate_date(String create_date) {
                    this.create_date = create_date;
                }

                public String getRegister_ip() {
                    return register_ip;
                }

                public void setRegister_ip(String register_ip) {
                    this.register_ip = register_ip;
                }

                public String getAdmin_id() {
                    return admin_id;
                }

                public void setAdmin_id(String admin_id) {
                    this.admin_id = admin_id;
                }

                public String getCustomer_id() {
                    return customer_id;
                }

                public void setCustomer_id(String customer_id) {
                    this.customer_id = customer_id;
                }

                public String getIs_black() {
                    return is_black;
                }

                public void setIs_black(String is_black) {
                    this.is_black = is_black;
                }

                public String getVip_id() {
                    return vip_id;
                }

                public void setVip_id(String vip_id) {
                    this.vip_id = vip_id;
                }

                public String getVip_state() {
                    return vip_state;
                }

                public void setVip_state(String vip_state) {
                    this.vip_state = vip_state;
                }

                public String getNmc_amount() {
                    return nmc_amount;
                }

                public void setNmc_amount(String nmc_amount) {
                    this.nmc_amount = nmc_amount;
                }

                public Object getIps_mer_code() {
                    return ips_mer_code;
                }

                public void setIps_mer_code(Object ips_mer_code) {
                    this.ips_mer_code = ips_mer_code;
                }

                public String getEnterpriseName() {
                    return enterpriseName;
                }

                public void setEnterpriseName(String enterpriseName) {
                    this.enterpriseName = enterpriseName;
                }

                public String getBankLicense() {
                    return bankLicense;
                }

                public void setBankLicense(String bankLicense) {
                    this.bankLicense = bankLicense;
                }

                public String getOrgNo() {
                    return orgNo;
                }

                public void setOrgNo(String orgNo) {
                    this.orgNo = orgNo;
                }

                public String getBusinessLicense() {
                    return businessLicense;
                }

                public void setBusinessLicense(String businessLicense) {
                    this.businessLicense = businessLicense;
                }

                public String getTaxNo() {
                    return taxNo;
                }

                public void setTaxNo(String taxNo) {
                    this.taxNo = taxNo;
                }

                public Object getU_year() {
                    return u_year;
                }

                public void setU_year(Object u_year) {
                    this.u_year = u_year;
                }

                public String getU_special() {
                    return u_special;
                }

                public void setU_special(String u_special) {
                    this.u_special = u_special;
                }

                public String getU_alipay() {
                    return u_alipay;
                }

                public void setU_alipay(String u_alipay) {
                    this.u_alipay = u_alipay;
                }

                public String getWx_openid() {
                    return wx_openid;
                }

                public void setWx_openid(String wx_openid) {
                    this.wx_openid = wx_openid;
                }

                public String getTotal_invite_borrow_money() {
                    return total_invite_borrow_money;
                }

                public void setTotal_invite_borrow_money(String total_invite_borrow_money) {
                    this.total_invite_borrow_money = total_invite_borrow_money;
                }

                public String getTotal_invite_invest_money() {
                    return total_invite_invest_money;
                }

                public void setTotal_invite_invest_money(String total_invite_invest_money) {
                    this.total_invite_invest_money = total_invite_invest_money;
                }

                public String getVip_end_time() {
                    return vip_end_time;
                }

                public void setVip_end_time(String vip_end_time) {
                    this.vip_end_time = vip_end_time;
                }

                public String getPlatform() {
                    return platform;
                }

                public void setPlatform(String platform) {
                    this.platform = platform;
                }

                public String getPlatform_is_cash() {
                    return platform_is_cash;
                }

                public void setPlatform_is_cash(String platform_is_cash) {
                    this.platform_is_cash = platform_is_cash;
                }

                public String getPlatform_is_ecv() {
                    return platform_is_ecv;
                }

                public void setPlatform_is_ecv(String platform_is_ecv) {
                    this.platform_is_ecv = platform_is_ecv;
                }

                public String getPlatform_is_rate() {
                    return platform_is_rate;
                }

                public void setPlatform_is_rate(String platform_is_rate) {
                    this.platform_is_rate = platform_is_rate;
                }

                public String getBid_start() {
                    return bid_start;
                }

                public void setBid_start(String bid_start) {
                    this.bid_start = bid_start;
                }

                public String getReg_type() {
                    return reg_type;
                }

                public void setReg_type(String reg_type) {
                    this.reg_type = reg_type;
                }

                public String getIs_virtual() {
                    return is_virtual;
                }

                public void setIs_virtual(String is_virtual) {
                    this.is_virtual = is_virtual;
                }

                public String getPoint_level() {
                    return point_level;
                }

                public void setPoint_level(String point_level) {
                    this.point_level = point_level;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getRegion_city() {
                    return region_city;
                }

                public void setRegion_city(String region_city) {
                    this.region_city = region_city;
                }

                public String getRegion() {
                    return region;
                }

                public void setRegion(String region) {
                    this.region = region;
                }

                public String getRegion_province() {
                    return region_province;
                }

                public void setRegion_province(String region_province) {
                    this.region_province = region_province;
                }

                public boolean isWorkinfo() {
                    return workinfo;
                }

                public void setWorkinfo(boolean workinfo) {
                    this.workinfo = workinfo;
                }
            }

            public static class CateInfoBean {
                /**
                 * id : 1
                 * name : 货抵贷
                 * brief : 货物抵押借款
                 * uname :
                 * icon : ./public/attachment/201803/16/13/5aab5c235d619.png
                 */

                private String id;
                private String name;
                private String brief;
                private String uname;
                private String icon;

                public static CateInfoBean objectFromData(String str) {

                    return new Gson().fromJson(str, CateInfoBean.class);
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

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getUname() {
                    return uname;
                }

                public void setUname(String uname) {
                    this.uname = uname;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }
            }

            public static class TypeInfoBean {
                /**
                 * id : 2
                 * name : 购房借款
                 * brief :
                 * pid : 0
                 * is_delete : 0
                 * is_effect : 1
                 * sort : 9
                 * uname :
                 * icon : ./public/images/dealtype/gf.png
                 * applyto :
                 * condition : 22-55周岁的中国公民
                 在现单位工作满3个月
                 月收入2000以上
                 * credits : N;
                 * is_quota : 1
                 * content :
                 */

                private String id;
                private String name;
                private String brief;
                private String pid;
                private String is_delete;
                private String is_effect;
                private String sort;
                private String uname;
                private String icon;
                private String applyto;
                private String condition;
                private String credits;
                private String is_quota;
                private String content;

                public static TypeInfoBean objectFromData(String str) {

                    return new Gson().fromJson(str, TypeInfoBean.class);
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

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getIs_delete() {
                    return is_delete;
                }

                public void setIs_delete(String is_delete) {
                    this.is_delete = is_delete;
                }

                public String getIs_effect() {
                    return is_effect;
                }

                public void setIs_effect(String is_effect) {
                    this.is_effect = is_effect;
                }

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getUname() {
                    return uname;
                }

                public void setUname(String uname) {
                    this.uname = uname;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getApplyto() {
                    return applyto;
                }

                public void setApplyto(String applyto) {
                    this.applyto = applyto;
                }

                public String getCondition() {
                    return condition;
                }

                public void setCondition(String condition) {
                    this.condition = condition;
                }

                public String getCredits() {
                    return credits;
                }

                public void setCredits(String credits) {
                    this.credits = credits;
                }

                public String getIs_quota() {
                    return is_quota;
                }

                public void setIs_quota(String is_quota) {
                    this.is_quota = is_quota;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }
            }
        }

}
