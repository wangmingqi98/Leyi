package com.leyijf.bean;

import android.content.Context;
import android.content.SharedPreferences;

import com.leyijf.App;
import com.leyijf.weight.grsture.PasswordUtil;

/**
 * Created by Administrator on 2018/3/12.
 */


    public class UserInfo {
        private static UserInfo sInstance;

        private SharedPreferences prefs;

        private boolean isUserInfoRetrieved;

        private UserInfo(){
            prefs = App.getInstance().getSharedPreferences("user_info", 0);
        }

        public static UserInfo getInstance() {
            if (sInstance == null) {
                sInstance = new UserInfo();
            }
            return sInstance;
        }
        public  void saveSession_id(String session_id){
            prefs.edit().putString("session_id",session_id).commit();
        }
        public  String getSession_id(){
            return prefs.getString("session_id",null);
        }

    public String getUserMoney() {
        return prefs.getString("userMoney", null);
    }

    public void saveUserMoney(String usermoney) {
        prefs.edit().putString("userMoney", usermoney).commit();
    }

        public String getUserToken() {
            return prefs.getString("userToken", null);
        }

        public void saveUserToken(String ticket) {
            prefs.edit().putString("userToken", ticket).commit();
        }
    public void saveUserPwd(String pwd){
        prefs.edit().putString("userpwd",pwd).commit();
    }
    public String getUserPwd(){
        return prefs.getString("userpwd",null);
    }

        public void saveUserName(String userName){
            prefs.edit().putString("username",userName).commit();
        }
        public String getUserName(){
            return prefs.getString("username",null);
        }

        public String getUserId() {
            return prefs.getString("userid", null);
        }

        public void saveUserId(String usercityid) {
            prefs.edit().putString("userid", usercityid).commit();
        }
        public String getUserPhone() {
            return prefs.getString("userphone", null);
        }

        public void saveUserPhone(String nickName) {
            prefs.edit().putString("userphone", nickName).commit();
        }

        public String getUserImg() {
            return prefs.getString("userimg", null);
        }

        public void saveUserImg(String userface) {
            prefs.edit().putString("userimg", userface).commit();
        }

    public int getHas_paypassword() {
        return prefs.getInt("has_paypassword", 0);
    }

    public void saveHas_paypassword(int has_paypassword) {
        prefs.edit().putInt("has_paypassword", has_paypassword).commit();
    }

    public int getId_passed() {
        return prefs.getInt("id_passed", 0);
    }

    public void saveId_passed(int id_passed) {
        prefs.edit().putInt("id_passed", id_passed).commit();
    }

    public int getUserState() {
        return prefs.getInt("userState", 0);
    }

    public void saveUserState(int userState) {
        prefs.edit().putInt("userState", userState).commit();
    }


    public String getTotalMoney() {
        return prefs.getString("totalmoney", null);
    }

    public void saveTotalMoney(String totalmoney) {
        prefs.edit().putString("totalmoney", totalmoney).commit();
    }

    public String getUseful_money() {
        return prefs.getString("useful_money", null);
    }

    public void saveUseful_money(String totalmoney) {
        prefs.edit().putString("useful_money", totalmoney).commit();
    }

    public String getWaitLiXi() {
        return prefs.getString("waitlixi", null);
    }

    public void saveWaitLiXi(String totalmoney) {
        prefs.edit().putString("waitlixi", totalmoney).commit();
    }

    public String getBenJin() {
        return prefs.getString("benjin", null);
    }

    public void saveBenjin(String totalmoney) {
        prefs.edit().putString("benjin", totalmoney).commit();
    }
    public String getShouyiTJ() {
        return prefs.getString("sytj", null);
    }

    public void saveShouyiTJ(String totalmoney) {
        prefs.edit().putString("sytj", totalmoney).commit();
    }
    public String getPayPwd() {
        return prefs.getString("paypwd", null);
    }

    public void savePayPwd(String totalmoney) {
        prefs.edit().putString("paypwd", totalmoney).commit();
    }

    /**
         * 是否成功请求过用户信息
         * @return
         */
        public boolean isUserbindCard() {
            return isUserInfoRetrieved;
        }

        public void setUserbindCard(boolean b) {
            isUserInfoRetrieved = b;
        }

        /**
         * 删除用户信息
         */
        public void clearUser(Context context) {
            prefs.edit().remove("userToken").remove("userpwd").remove("username")
                    .remove("userid").remove("userphone").remove("userimg")
                    .remove("has_paypassword").remove("id_passed").remove("userState")
                    .commit();
             PasswordUtil.delectPin(context);
        }
    /**
     * 安全退出登录
     */
    public void ExeitUser(Context context) {
        prefs.edit().remove("userToken").remove("userpwd").remove("username")
                 .remove("userphone").remove("userimg")
                .remove("has_paypassword").remove("id_passed").remove("userState").remove("session_id")
                .commit();
//        PasswordUtil.delectPin(context);
    }

    }



