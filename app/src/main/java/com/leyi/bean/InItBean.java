package com.leyi.bean;

/**
 * Created by Administrator on 2018/3/14.
 */

public class InItBean {
//     "rate":"10.58",
//
//             　"min_loan_money":"100",
//
//             "name":"产业链3号",
//
//             "deal_labels":"产业链",
//
//             "need_money":"￥84,880.00",
//
//             "progress_point":15.12,
           String id;
           String repay_time;
           String rate;
           String min_loan_money;
           String name;
           String deal_labels;
           String need_money;
           int progress_point;
          String app_url;
           String deal_status;
            String  repay_time_type;
            String  risk_rank;
            String  borrow_amount;
            String  enddate;

    @Override
    public String toString() {
        return "InItBean{" +
                "id='" + id + '\'' +
                ", repay_time='" + repay_time + '\'' +
                ", rate='" + rate + '\'' +
                ", min_loan_money='" + min_loan_money + '\'' +
                ", name='" + name + '\'' +
                ", deal_labels='" + deal_labels + '\'' +
                ", need_money='" + need_money + '\'' +
                ", progress_point=" + progress_point +
                ", app_url='" + app_url + '\'' +
                ", deal_status='" + deal_status + '\'' +
                ", repay_time_type='" + repay_time_type + '\'' +
                ", risk_rank='" + risk_rank + '\'' +
                ", borrow_amount='" + borrow_amount + '\'' +
                ", enddate='" + enddate + '\'' +
                '}';
    }

    public InItBean(String id, String repay_time, String rate, String min_loan_money, String name, String deal_labels, String need_money, int progress_point, String app_url, String deal_status, String repay_time_type, String risk_rank, String borrow_amount, String enddate) {
        this.id = id;
        this.repay_time = repay_time;
        this.rate = rate;
        this.min_loan_money = min_loan_money;
        this.name = name;
        this.deal_labels = deal_labels;
        this.need_money = need_money;
        this.progress_point = progress_point;
        this.app_url = app_url;
        this.deal_status = deal_status;
        this.repay_time_type = repay_time_type;
        this.risk_rank = risk_rank;
        this.borrow_amount = borrow_amount;
        this.enddate = enddate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getBorrow_amount() {
        return borrow_amount;
    }

    public void setBorrow_amount(String borrow_amount) {
        this.borrow_amount = borrow_amount;
    }


    //    借款期限：repay_time；{if $deal.repay_time_type eq 0}天{else}月{/if}
//    风险等级：rish_rank; 0:低;1:中;2:高　"risk_rank":"0",
//    deal_status：状态；0待等材料，1进行中，2满标，3流标，4还款中，5已还清；



    public String getRepay_time_type() {
        return repay_time_type;
    }

    public void setRepay_time_type(String repay_time_type) {
        this.repay_time_type = repay_time_type;
    }

    public String getRisk_rank() {
        return risk_rank;
    }

    public void setRisk_rank(String risk_rank) {
        this.risk_rank = risk_rank;
    }


    public String getDeal_status() {
        return deal_status;
    }

    public void setDeal_status(String deal_status) {
        this.deal_status = deal_status;
    }


    public String getApp_url() {
        return app_url;
    }

    public void setApp_url(String app_url) {
        this.app_url = app_url;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMin_loan_money() {
        return min_loan_money;
    }

    public void setMin_loan_money(String min_loan_money) {
        this.min_loan_money = min_loan_money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeal_labels() {
        return deal_labels;
    }

    public void setDeal_labels(String deal_labels) {
        this.deal_labels = deal_labels;
    }

    public String getNeed_money() {
        return need_money;
    }

    public void setNeed_money(String need_money) {
        this.need_money = need_money;
    }

    public int getProgress_point() {
        return progress_point;
    }

    public void setProgress_point(int progress_point) {
        this.progress_point = progress_point;
    }



}
