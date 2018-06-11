package com.leyijf.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wangzhian on 2018/5/29.
 */
@Entity
public class FeeConfigBean {
    @Id(autoincrement = true)//数据库中自增长
    private Long id;
    @Index(unique = true)//用户id唯一性
    private String fee_id;
    private String name;
    private double min_price;
    private double max_price;
    private double fee;
    private String fee_type;
    private String vip_id;
    private Long userId;
    @Generated(hash = 516187074)
    public FeeConfigBean(Long id, String fee_id, String name, double min_price,
            double max_price, double fee, String fee_type, String vip_id,
            Long userId) {
        this.id = id;
        this.fee_id = fee_id;
        this.name = name;
        this.min_price = min_price;
        this.max_price = max_price;
        this.fee = fee;
        this.fee_type = fee_type;
        this.vip_id = vip_id;
        this.userId = userId;
    }
    @Generated(hash = 842688897)
    public FeeConfigBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFee_id() {
        return this.fee_id;
    }
    public void setFee_id(String fee_id) {
        this.fee_id = fee_id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getMin_price() {
        return this.min_price;
    }
    public void setMin_price(double min_price) {
        this.min_price = min_price;
    }
    public double getMax_price() {
        return this.max_price;
    }
    public void setMax_price(double max_price) {
        this.max_price = max_price;
    }
    public double getFee() {
        return this.fee;
    }
    public void setFee(double fee) {
        this.fee = fee;
    }
    public String getFee_type() {
        return this.fee_type;
    }
    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }
    public String getVip_id() {
        return this.vip_id;
    }
    public void setVip_id(String vip_id) {
        this.vip_id = vip_id;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
