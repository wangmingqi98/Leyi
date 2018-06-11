package com.leyijf.bean;

/**
 * Created by Administrator on 2018/3/19.
 */

public class ToubiaoDetilsBean {
//
//            "id":"16",
//            　　　　　　"name":"货抵贷6号",
//
//
//            "description":"<p class="MsoNormal"> 借款人杭州聂栓贸易有限公司，本次通过乐毅金服申请借款<span>20</span>万元，借款用于资金周转，还款来源为经营收入，还款保障措施为企业信用及无限连带保证。同时，经审核借款人所提供资料真实有效，符合乐毅金服的借款审核标准。<span></span> </p>",
//
//            "borrow_amount":"200000",　"min_loan_money":"100""repay_time":"3",
//            　　　　　　"rate":"10.58","loantype_format":"付息还本",　"need_money":"￥170,000.00","buy_count":"1",
//            　　　　　　"load_money":30000,


    String  id;
    String name;
    String description;
    String  borrow_amount;
    String min_loan_money;
    String repay_time;
    String repay_time_type;
    String repay_start_time;
    String rate;
    String loantype_format;
    String need_money;
    String buy_count;
    int load_money;
    int project;
    String attachment;
    String risk_security;
    String remain_time_format;
    String deal_status;

    @Override
    public String toString() {
        return "ToubiaoDetilsBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", borrow_amount='" + borrow_amount + '\'' +
                ", min_loan_money='" + min_loan_money + '\'' +
                ", repay_time='" + repay_time + '\'' +
                ", repay_time_type='" + repay_time_type + '\'' +
                ", repay_start_time='" + repay_start_time + '\'' +
                ", rate='" + rate + '\'' +
                ", loantype_format='" + loantype_format + '\'' +
                ", need_money='" + need_money + '\'' +
                ", buy_count='" + buy_count + '\'' +
                ", load_money=" + load_money +
                ", project=" + project +
                ", attachment='" + attachment + '\'' +
                ", risk_security='" + risk_security + '\'' +
                ", remain_time_format='" + remain_time_format + '\'' +
                ", deal_status='" + deal_status + '\'' +
                '}';
    }

    public ToubiaoDetilsBean(String id, String name, String description, String borrow_amount, String min_loan_money, String repay_time, String repay_time_type, String repay_start_time, String rate, String loantype_format, String need_money, String buy_count, int load_money, int project, String attachment, String risk_security, String remain_time_format, String deal_status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.borrow_amount = borrow_amount;
        this.min_loan_money = min_loan_money;
        this.repay_time = repay_time;
        this.repay_time_type = repay_time_type;
        this.repay_start_time = repay_start_time;
        this.rate = rate;
        this.loantype_format = loantype_format;
        this.need_money = need_money;
        this.buy_count = buy_count;
        this.load_money = load_money;
        this.project = project;
        this.attachment = attachment;
        this.risk_security = risk_security;
        this.remain_time_format = remain_time_format;
        this.deal_status = deal_status;
    }

    public String getRepay_time_type() {
        return repay_time_type;
    }

    public void setRepay_time_type(String repay_time_type) {
        this.repay_time_type = repay_time_type;
    }

    public String getRemain_time_format() {
        return remain_time_format;
    }

    public void setRemain_time_format(String remain_time_format) {
        this.remain_time_format = remain_time_format;
    }

    public String getDeal_status() {
        return deal_status;
    }

    public void setDeal_status(String deal_status) {
        this.deal_status = deal_status;
    }


    public String getRisk_security() {
        return risk_security;
    }

    public void setRisk_security(String risk_security) {
        this.risk_security = risk_security;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }


    public String getRepay_start_time() {
        return repay_start_time;
    }

    public void setRepay_start_time(String repay_start_time) {
        this.repay_start_time = repay_start_time;
    }




    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBorrow_amount() {
        return borrow_amount;
    }

    public void setBorrow_amount(String borrow_amount) {
        this.borrow_amount = borrow_amount;
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

    public String getLoantype_format() {
        return loantype_format;
    }

    public void setLoantype_format(String loantype_format) {
        this.loantype_format = loantype_format;
    }

    public String getNeed_money() {
        return need_money;
    }

    public void setNeed_money(String need_money) {
        this.need_money = need_money;
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
}
