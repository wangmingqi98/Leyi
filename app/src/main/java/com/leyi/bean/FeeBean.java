package com.leyi.bean;

/**
 * Created by Administrator on 2018/3/29.
 */

public class FeeBean {


    /**
     * id : 10
     * name : 提现费
     * min_price : 50
     * max_price : 50000
     * fee : 0.00
     * fee_type : 0
     * vip_id : 0
     * fee_format : ￥0.00
     */

    private String id;
    private String name;
    private String min_price;
    private String max_price;
    private String fee;
    private String fee_type;
    private String vip_id;
    private String fee_format;

    public FeeBean(String id, String name, String min_price, String max_price, String fee, String fee_type, String vip_id, String fee_format) {
        this.id = id;
        this.name = name;
        this.min_price = min_price;
        this.max_price = max_price;
        this.fee = fee;
        this.fee_type = fee_type;
        this.vip_id = vip_id;
        this.fee_format = fee_format;
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

    public String getMin_price() {
        return min_price;
    }

    public void setMin_price(String min_price) {
        this.min_price = min_price;
    }

    public String getMax_price() {
        return max_price;
    }

    public void setMax_price(String max_price) {
        this.max_price = max_price;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getVip_id() {
        return vip_id;
    }

    public void setVip_id(String vip_id) {
        this.vip_id = vip_id;
    }

    public String getFee_format() {
        return fee_format;
    }

    public void setFee_format(String fee_format) {
        this.fee_format = fee_format;
    }
}
