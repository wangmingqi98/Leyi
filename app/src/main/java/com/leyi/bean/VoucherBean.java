package com.leyi.bean;
//
//import java.util.List;
//
///**
// * Created by Administrator on 2018/4/2.
// */
//
//public class MyRedPackBean {
//
//
//    /**
//     * response_code : 1
//     * objects : {"vouchers":[]}
//     */
//
//    private int response_code;
//    private ObjectsBean objects;
//
//    public int getResponse_code() {
//        return response_code;
//    }
//
//    public void setResponse_code(int response_code) {
//        this.response_code = response_code;
//    }
//
//    public ObjectsBean getObjects() {
//        return objects;
//    }
//
//    public void setObjects(ObjectsBean objects) {
//        this.objects = objects;
//    }
//
//    public static class ObjectsBean {
//        private List<VoucherBean> vouchers;
//
//        public List<VoucherBean> getVouchers() {
//            return vouchers;
//        }
//
//        public void setVouchers(List<VoucherBean> vouchers) {
//            this.vouchers = vouchers;
//        }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    }
//
//
//
//
//}
public  class VoucherBean  {
    private String voucher_id;
    private String end_time;
    private String user_time;
    private String money;
    private String name;
    private String can_use_limit;
    private String invest_period_limit;
    private String invest_period_type;
    private String remain_day;
    private String status;

    public VoucherBean(String voucher_id, String end_time, String user_time, String money, String name, String can_use_limit, String invest_period_limit, String invest_period_type, String remain_day, String status) {
        this.voucher_id = voucher_id;
        this.end_time = end_time;
        this.user_time = user_time;
        this.money = money;
        this.name = name;
        this.can_use_limit = can_use_limit;
        this.invest_period_limit = invest_period_limit;
        this.invest_period_type = invest_period_type;
        this.remain_day = remain_day;
        this.status = status;
    }

    public String getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(String voucher_id) {
        this.voucher_id = voucher_id;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getUser_time() {
        return user_time;
    }

    public void setUser_time(String user_time) {
        this.user_time = user_time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCan_use_limit() {
        return can_use_limit;
    }

    public void setCan_use_limit(String can_use_limit) {
        this.can_use_limit = can_use_limit;
    }

    public String getInvest_period_limit() {
        return invest_period_limit;
    }

    public void setInvest_period_limit(String invest_period_limit) {
        this.invest_period_limit = invest_period_limit;
    }

    public String getInvest_period_type() {
        return invest_period_type;
    }

    public void setInvest_period_type(String invest_period_type) {
        this.invest_period_type = invest_period_type;
    }

    public String getRemain_day() {
        return remain_day;
    }

    public void setRemain_day(String remain_day) {
        this.remain_day = remain_day;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}