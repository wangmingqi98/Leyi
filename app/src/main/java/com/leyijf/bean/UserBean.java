package com.leyijf.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.leyijf.greendao.DaoSession;
import com.leyijf.greendao.BindedCardBeanDao;
import com.leyijf.greendao.UserBeanDao;
import com.leyijf.greendao.FeeConfigBeanDao;
/**
 * Created by wmq on 2018/4/18.
 * 用户信息实体类
 */
@Entity
public class UserBean {
    @Id(autoincrement = true)
    private Long id;
    /**
     * user_id : 5
     * user_name : 阿发几份
     * user_img :
     * user_mobile : 135****2668
     * user_mobile_referee : 13552032668
     * id_passed : 1
     * has_paypassword : 1
     * binded_card : [{"bank_card_icon":"http://www.leyibank.com./public/bank/CCB.png","bank_card_num":"6796","bank_id":"58","bank_name":"中国建设银行"}]
     */
    @Index(unique = true)//用户id唯一性
    private String user_id;
    private String user_name;
    private String user_img;
    private String user_mobile;
    private String user_mobile_referee;
    private int id_passed;
    private int has_paypassword;
    @ToMany(referencedJoinProperty = "userId")
    private List<BindedCardBean> binded_card;
    @ToMany(referencedJoinProperty = "userId")
    private List<FeeConfigBean> fee_config;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 83707551)
    private transient UserBeanDao myDao;
    @Generated(hash = 1724126633)
    public UserBean(Long id, String user_id, String user_name, String user_img, String user_mobile, String user_mobile_referee, int id_passed,
            int has_paypassword) {
        this.id = id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_img = user_img;
        this.user_mobile = user_mobile;
        this.user_mobile_referee = user_mobile_referee;
        this.id_passed = id_passed;
        this.has_paypassword = has_paypassword;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUser_id() {
        return this.user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getUser_name() {
        return this.user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getUser_img() {
        return this.user_img;
    }
    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }
    public String getUser_mobile() {
        return this.user_mobile;
    }
    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }
    public String getUser_mobile_referee() {
        return this.user_mobile_referee;
    }
    public void setUser_mobile_referee(String user_mobile_referee) {
        this.user_mobile_referee = user_mobile_referee;
    }
    public int getId_passed() {
        return this.id_passed;
    }
    public void setId_passed(int id_passed) {
        this.id_passed = id_passed;
    }
    public int getHas_paypassword() {
        return this.has_paypassword;
    }
    public void setHas_paypassword(int has_paypassword) {
        this.has_paypassword = has_paypassword;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1722403074)
    public List<BindedCardBean> getBinded_card() {
        if (binded_card == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BindedCardBeanDao targetDao = daoSession.getBindedCardBeanDao();
            List<BindedCardBean> binded_cardNew = targetDao._queryUserBean_Binded_card(id);
            synchronized (this) {
                if (binded_card == null) {
                    binded_card = binded_cardNew;
                }
            }
        }
        return binded_card;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1240057091)
    public synchronized void resetBinded_card() {
        binded_card = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_img='" + user_img + '\'' +
                ", user_mobile='" + user_mobile + '\'' +
                ", user_mobile_referee='" + user_mobile_referee + '\'' +
                ", id_passed=" + id_passed +
                ", has_paypassword=" + has_paypassword +
                ", binded_card=" + binded_card +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                '}';
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1517469073)
    public List<FeeConfigBean> getFee_config() {
        if (fee_config == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FeeConfigBeanDao targetDao = daoSession.getFeeConfigBeanDao();
            List<FeeConfigBean> fee_configNew = targetDao._queryUserBean_Fee_config(id);
            synchronized (this) {
                if (fee_config == null) {
                    fee_config = fee_configNew;
                }
            }
        }
        return fee_config;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1478963776)
    public synchronized void resetFee_config() {
        fee_config = null;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1491512534)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserBeanDao() : null;
    }
}


