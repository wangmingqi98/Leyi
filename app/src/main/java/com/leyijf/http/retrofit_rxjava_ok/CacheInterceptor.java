package com.leyijf.http.retrofit_rxjava_ok;

import android.content.Context;
import android.util.Log;

import com.leyijf.App;
import com.leyijf.bean.UserInfo;
import com.leyijf.util.NetUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * caheInterceptor
 * Created by zd on 2017-08-09.
 */
public class CacheInterceptor implements Interceptor {
    private static final String TAG = "gallery";
    private Context context;
    //set cahe times is 3 days
    int maxStale = 60 * 60 * 24 * 3;

    public CacheInterceptor() {
    }

    public CacheInterceptor(Context context) {
        this.context = context;
    }

    /**
     * 设置缓存
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if(UserInfo.getInstance().getSession_id()!=null){
            Log.e("intercept: ",UserInfo.getInstance().getSession_id() );
            if (NetUtil.isNetConnected(App.getInstance())) {
                Response response = chain.proceed(request);
                // read from cache for 60 s
                int maxAge = 10;
                String cacheControl = request.cacheControl().toString();
                Log.d(TAG, "60s load cache" + cacheControl);
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .addHeader("cookie","PHPSESSID="+ UserInfo.getInstance().getSession_id())
                        .build();
            } else {
//            ((Activity) context).runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(context, "当前无网络! 为你智能加载缓存", Toast.LENGTH_SHORT).show();
//                }
//            });
                Log.d(TAG, " no network load cache");
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Response response = chain.proceed(request);
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .addHeader("cookie","PHPSESSID="+ UserInfo.getInstance().getSession_id())
                        .build();
            }
        }else {
            if (NetUtil.isNetConnected(App.getInstance())) {
                Response response = chain.proceed(request);
                // read from cache for 60 s
                int maxAge = 60;
                String cacheControl = request.cacheControl().toString();
                Log.d(TAG, "60s load cache" + cacheControl);
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .addHeader("cookie", "PHPSESSID=" + UserInfo.getInstance().getSession_id())
                        .build();
            } else {
//            ((Activity) context).runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(context, "当前无网络! 为你智能加载缓存", Toast.LENGTH_SHORT).show();
//                }
//            });
                Log.d(TAG, " no network load cache");
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Response response = chain.proceed(request);
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .addHeader("cookie", "PHPSESSID=" + UserInfo.getInstance().getSession_id())
                        .build();
            }
        }
    }
    public static final Interceptor LoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
        //    Logger.t(TAG).i(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
         //   Logger.t(TAG).i(String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    };
}
