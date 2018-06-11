package com.leyijf.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wangzhian on 2018/4/18.
 * 用户绑定银行卡实体类
 */
@Entity
public class BindedCardBean {
    /**
     * bank_card_icon : http://www.leyibank.com./public/bank/CCB.png
     * bank_card_num : 6796
     * bank_id : 58
     * bank_name : 中国建设银行
     */
    @Id(autoincrement = true)//数据库中自增长
    private Long id;
    private String bank_card_icon;
    private String bank_card_num;
    @Index(unique = true)//每张银行卡的id 唯一性
    private String bank_id;
    private String bank_name;
    private Long userId;
    @Generated(hash = 928317414)
    public BindedCardBean(Long id, String bank_card_icon, String bank_card_num,
            String bank_id, String bank_name, Long userId) {
        this.id = id;
        this.bank_card_icon = bank_card_icon;
        this.bank_card_num = bank_card_num;
        this.bank_id = bank_id;
        this.bank_name = bank_name;
        this.userId = userId;
    }
    @Generated(hash = 1192197141)
    public BindedCardBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBank_card_icon() {
        return this.bank_card_icon;
    }
    public void setBank_card_icon(String bank_card_icon) {
        this.bank_card_icon = bank_card_icon;
    }
    public String getBank_card_num() {
        return this.bank_card_num;
    }
    public void setBank_card_num(String bank_card_num) {
        this.bank_card_num = bank_card_num;
    }
    public String getBank_id() {
        return this.bank_id;
    }
    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }
    public String getBank_name() {
        return this.bank_name;
    }
    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

}

