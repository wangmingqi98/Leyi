package com.leyi.util;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Administrator on 2017/11/24.
 */

public class SharePreferenceUtil {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharePreferenceUtil(Context context, String file) {
        sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
        editor = sp.edit();
    }
//
//    public SharePreferenceUtil(Context context) {
//        sp = context.getSharedPreferences(AppContext.SP_FILE_NAME,
//                Context.MODE_PRIVATE);
//        editor = sp.edit();
//    }

    /**
     * 备注 ：
     * 关于用户信息的读取 已抽至UserManager.class
     *
     */
    public void  removeAll(int count){
        editor.remove("history"+count).commit();
        editor.remove("count").commit();
    }
    public void setCounts(int count){
        editor.putInt("count",count);
        editor.commit();
    }


    public int getCounts(){
        return sp.getInt("count",0);
    }

    public void setHistWords(String histwords, int count){
        editor.putString("history"+count,histwords);
        editor.commit();
    }

    public String getHistWords(int count){
        return sp.getString("history"+count,"");
    }

    public void setPush(String sign) {
        editor.putString("push", sign);
        editor.commit();
    }

    public String getPush() {
        return sp.getString("push", "close");
    }

    public void setIsFirstShareWx(boolean isFirst) {
        editor.putBoolean("isFirstShareWx", isFirst);
        editor.commit();
    }

    public Boolean getIsFirstShareWx() {
        return sp.getBoolean("isFirstShareWx", true);
    }

    public void setIsFirstLoginWx(boolean isFirst){
        editor.putBoolean("isFirstLoginWx", isFirst);
        editor.commit();
    }

    public Boolean getIsFirstLoginWx() {
        return sp.getBoolean("isFirstLoginWx", true);
    }

    public Boolean getIsFirstShareSinaWeib() {
        return sp.getBoolean("isFirstShareSinaWeib", true);
    }
    public void setIsFirstShareSinaWeib(boolean isFirst) {
        editor.putBoolean("isFirstShareSinaWeib", isFirst);
        editor.commit();
    }

    public Boolean getIsFirstLoginSinaWeib() {
        return sp.getBoolean("isFirstLoginSinaWeib", true);
    }

    public void setIsFirstLoginSinaWeib(boolean isFirst) {
        editor.putBoolean("isFirstLoginSinaWeib", isFirst);
        editor.commit();
    }


    public Boolean getIsFirstOpenPhoto() {
        return sp.getBoolean("isFirstOpenPhoto", true);
    }

    public void setIsFirstOpenPhoto(boolean isFirst) {
        editor.putBoolean("isFirstOpenPhoto", isFirst);
        editor.commit();
    }
    public Boolean getIsFirstOpenCamera() {
        return sp.getBoolean("isFirstOpenCamera", true);
    }

    public void setIsFirstOpenCamera(boolean isFirst) {
        editor.putBoolean("isFirstOpenCamera", isFirst);
        editor.commit();
    }



    public Boolean getIsFirstLoginQQ() {
        return sp.getBoolean("isFirstLoginQQ", true);
    }
    public void setIsFirstLoginQQ(boolean isFirst) {
        editor.putBoolean("isFirstLoginQQ", isFirst);
        editor.commit();
    }
    public Boolean getIsWXLoginSuccess(){return sp.getBoolean("isWXLoginSuccess",false);}
    public void setIsWXLoginSuccess(Boolean isSuccess){
        editor.putBoolean("isWXLoginSuccess",isSuccess);
        editor.commit();
    }

    public Boolean getIsRegistSuccess(){return sp.getBoolean("isRegistSuccess",false);}
    public void setIsRegistSuccess(Boolean isSuccess){
        editor.putBoolean("isRegistSuccess",isSuccess);
        editor.commit();
    }
    public String getWeChatCode() {
        return sp.getString("wechat_code", "");
    }

    public  void saveWeChatCode(String code) {

        editor.putString("wechat_code", code);
        editor.commit();
    }
    public void setWeb(String webName, String webAddr) {

        editor.putString(webName, webAddr);
        editor.commit();
    }

    public String getWebAddress(String webName) {

        return sp.getString(webName, "");
    }

    public void setLanguage(String language){
        editor.putString("language",language);
        editor.commit();
    }

    public String getLanguage(){
        return  sp.getString("language","");
    }
}
