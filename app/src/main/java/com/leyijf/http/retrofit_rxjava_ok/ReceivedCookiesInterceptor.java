package com.leyijf.http.retrofit_rxjava_ok;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.leyijf.App;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

//            @SuppressLint("WrongConstant")
//            SharedPreferences.Editor config = App.getSharedPreferences("config", 1)
//                    .edit();
//            config.putStringSet("cookie", cookies);
//            config.commit();
        }

        return originalResponse;
    }


}
