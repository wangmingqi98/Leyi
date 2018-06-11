package com.leyijf.manager;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Entity mapped to table CONFIG.
 */
@Entity
public class Config {
    @Id(autoincrement = true)
    private Long id;
    /** Not-null value. */
    private String Key;
    /** Not-null value. */
    private String Value;
    @Generated(hash = 1561602937)
    public Config(Long id, String Key, String Value) {
        this.id = id;
        this.Key = Key;
        this.Value = Value;
    }
    @Generated(hash = 589037648)
    public Config() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKey() {
        return this.Key;
    }
    public void setKey(String Key) {
        this.Key = Key;
    }
    public String getValue() {
        return this.Value;
    }
    public void setValue(String Value) {
        this.Value = Value;
    }



}
