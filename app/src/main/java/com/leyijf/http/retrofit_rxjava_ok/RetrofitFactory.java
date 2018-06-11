package com.leyijf.http.retrofit_rxjava_ok;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leyijf.App;
import com.leyijf.bean.UserInfo;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



/**
 * RetrofitFactory
 * Created by jaycee on 2017/6/23.
 */
public class RetrofitFactory {

//    public  static final String BASEURL="http://leyibank.com//mapi/";
    public  static final String BASEURL="http://ceshi.leyibank.com/mapi/";
    public  static final String BASEIMG="http://leyibank.com//";
    public  static final String ACT="index.php";
    public  static final String TYPE="&r_type=1";

    private static final long TIMEOUT = 30;
    //缓存容量
    static long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MiB
    //缓存路径
    static String cacheFile = App.getInstance().getCacheDir() + "/gallery_cash";
    static Cache cache = new Cache(new File(cacheFile), SIZE_OF_CACHE);

    // Retrofit是基于OkHttpClient的，可以创建一个OkHttpClient进行一些配置
    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            // 添加通用的Header
//            .addNetworkInterceptor(new Interceptor() {
//      下          @Override
//      载          public Response intercept(Chain chain) throws IOException {
//      进              Response orginalResponse = chain.proceed(chain.request());
//      度              return orginalResponse.newBuilder().body(new ProgressResponseBody(orginalResponse.body(), new ProgressListener() {
//      条                  @Override
//      栏                  public void onProgress(long progress, long total, boolean done) {
//      截                      Log.e("PROGRESS", Looper.myLooper() + "==download");
//      器                      Log.e("PROGRESS", "onProgress: " + "total ---->" + total + "done ---->" + progress);
//                        }
//                    })).build();
//                }
//            })
//            .addInterceptor(new BaseInterceptor().intercept(c))
            .addNetworkInterceptor(new CacheInterceptor())
            .addInterceptor(new CacheInterceptor())
            .addInterceptor(new AddCookiesInterceptor())
//            .addInterceptor(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request.Builder builder = chain.request().newBuilder();
//                    // 替换为自己的token
////                    if(!TextUtils.isEmpty(UserInfo.getInstance().getSession_id())){
////                        builder.addHeader("Cookie", "PHPSESSID＝"+UserInfo.getInstance().getSession_id());
////
////                    }
//                    Log.e("请求头", UserInfo.getInstance().getSession_id());
//                    return chain.proceed(builder.build());
//                }
//            })
            /*
            这里可以添加一个HttpLoggingInterceptor，因为Retrofit封装好了从Http请求到解析，
            出了bug很难找出来问题，添加HttpLoggingInterceptor拦截器方便调试接口
             */
            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("httplog", message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BASIC))
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .cache(cache)
            .build();

    private static RetrofitService retrofitService = new Retrofit.Builder()
            .baseUrl(BASEURL)
            // 添加Gson转换器
            .addConverterFactory(CustomGsonConverterFactory.create(buildGson()))
            // 添加Retrofit到RxJava的转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(RetrofitService.class);

    public static RetrofitService getInstance() {

        return retrofitService;
    }
    private static Gson buildGson() {
        return new GsonBuilder()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                // 此处可以添加Gson 自定义TypeAdapter
                //    .registerTypeAdapter(User.class, new UserTypeAdapter())
                .create();
    }


}
