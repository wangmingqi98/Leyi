package com.leyijf.manager;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liulijuan on 16/11/8.
 */
@Entity
public class Type {
    @Id(autoincrement = true)
    private Long id;
    private String type;
    private String loginName;
    @Generated(hash = 488086259)
    public Type(Long id, String type, String loginName) {
        this.id = id;
        this.type = type;
        this.loginName = loginName;
    }
    @Generated(hash = 1782799822)
    public Type() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getLoginName() {
        return this.loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

   
}
