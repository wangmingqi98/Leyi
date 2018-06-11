package com.leyijf.util;

import android.util.Log;

import com.leyijf.http.GetUrl;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/3/13.
 */

public class OkHttpUtil {
    public static final String TAG = "OkHttpUtil";
    private static OkHttpUtil okhttpUtil;
    private OkHttpClient okHttpClient;

    private OkHttpUtil() {
        okHttpClient = new OkHttpClient();
    }

    public static OkHttpUtil getInstance() {
        if (okhttpUtil == null) {
            synchronized (OkHttpUtil.class) {
                if (okhttpUtil == null) {
                    okhttpUtil = new OkHttpUtil();
                }
            }
        }
        return okhttpUtil;
    }
//    public void post(String url, Callback callback){
////        String s = MapToJson.toJson();
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), "");
//        Request request = new Request.Builder().url(MapToJson.getUrl(url)).post(requestBody).build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(callback);
//    }
//    public void postDocument(String url, Map map, Callback callback){
//        String s = MapToJson.toJson(map);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), s);
//        Request request = new Request.Builder().url(MapToJson.getUrl(url)).post(requestBody).build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(callback);
//    }

    /**
     * okHttp get同步请求

     */

        public void doHttp(Map<String,String> map, Callback callback){


            Request request = new Request.Builder().url(GetUrl.getUrl(map)).build();
            Log.d(TAG, "doHttp: "+GetUrl.getUrl(map));
            Call call = okHttpClient.newCall(request);
            call.enqueue(callback);
        }


    public void postDocument(String url, Map map, Callback callback,String  params){

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=utf-8"), params);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }



}
