package com.leyijf.manager;


import android.util.Log;

import com.leyijf.App;
import com.leyijf.bean.BindedCardBean;
import com.leyijf.bean.FeeConfigBean;
import com.leyijf.bean.UserBean;

/**
 * wmq
 * 对于用户登录状态及用户信息的管理
 */
public class UserManager {

    public static final String TAG = "UserManager";
    public static final int WHAT_NEED_LOGIN = 99;

    private static final long LOGIN_USER_ID = 1L;//
    private static final long BINDED_CARD_ID = 1L;
    private static final long FEE_CONFIG = 1L;
    public static final int WHAT_LOGIN = 10;
    public static final int WHAT_LOGIN3RD = 11;
    public static final int INVEST = 23;

    private static UserManager instance = null;

    private UserManager() {

    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public boolean isLogin() {
        Log.d(TAG, "isLogin: "+App.getDaoSession().getUserBeanDao().count());
        return App.getDaoSession().getUserBeanDao().count() == 1L ? true : false;
    }
    public boolean isBindedCard() {
        return App.getDaoSession().getBindedCardBeanDao().count() == 1L ? true : false;
    }
    public  boolean isFeeConfig(){
        return App.getDaoSession().getFeeConfigBeanDao().count() ==1L ? true : false;
    }

    public boolean isCertification(){
        Log.d(TAG, "isCertification: "+App.getDaoSession().getUserBeanDao().count());
        return instance.getLoginUser().getId_passed() ==1 ? true : false;
    }




    public boolean saveLoginedUser(UserBean user) {
        clearLoginMember();
        user.setId(LOGIN_USER_ID);
        App.getDaoSession().getUserBeanDao().insert(user);
        return true;
    }

    public boolean updateLoginedUser(UserBean user) {
        user.setId(LOGIN_USER_ID);
        App.getDaoSession().getUserBeanDao().update(user);
        return true;
    }
    public boolean updateLoginedUserBindeCard(BindedCardBean type){
        type.setId(BINDED_CARD_ID);
        App.getDaoSession().getBindedCardBeanDao().update(type);
        return true;
    }

    public boolean saveLoginedType(BindedCardBean type) {
        clearTypeMember();
        type.setId(BINDED_CARD_ID);
        App.getDaoSession().getBindedCardBeanDao().insert(type);
        return true;
    }
    public boolean saveFeeConfiga(FeeConfigBean feeConfigBean){
        feeConfigBean.setId(FEE_CONFIG);
        App.getDaoSession().getFeeConfigBeanDao().insert(feeConfigBean);
        return true;
    }
    public boolean updateFeeConfiga(FeeConfigBean feeConfigBeane){
        feeConfigBeane.setId(FEE_CONFIG);
        App.getDaoSession().getFeeConfigBeanDao().update(feeConfigBeane);
        return true;
    }

    public boolean clearTypeMember() {
        App.getDaoSession().getBindedCardBeanDao().deleteAll();
        return true;
    }

    public boolean clearLoginMember() {
        App.getDaoSession().getUserBeanDao().deleteAll();

        return true;
    }
    public boolean clearFeeConfing(){
        App.getDaoSession().getFeeConfigBeanDao().deleteAll();
        return true;
    }

    public UserBean getLoginUser() {
        if (!isLogin()) {
            return null;
        }
        UserBean user = App.getDaoSession().getUserBeanDao().load(LOGIN_USER_ID);
        return user;
    }

    public BindedCardBean getLoginType() {
        if (!isBindedCard()) {
            return null;
        }
        BindedCardBean bindedCardBean = App.getDaoSession().getBindedCardBeanDao().load(BINDED_CARD_ID);
        return bindedCardBean;
    }

    public FeeConfigBean getFeeConfig(){
        if(isFeeConfig()){
            return null;
        }
        FeeConfigBean feeConfigBean = App.getDaoSession().getFeeConfigBeanDao().load(FEE_CONFIG);
        return feeConfigBean;
    }

    public void login3rd() {
    }

    public void logout() {
        clearLoginMember();
//        clearTypeMember();
//        clearFeeConfing();
    }

    public void signIn() {
    }

}
