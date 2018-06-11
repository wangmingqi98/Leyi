package com.leyijf.http.retrofit_rxjava_ok;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.leyijf.App;
import com.leyijf.bean.UserInfo;
import com.leyijf.manager.UserManager;
import com.leyijf.view.activity.LoginActivity;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if(UserManager.getInstance().isLogin()){

            builder.addHeader("Cookie", "PHPSESSID=" + UserInfo.getInstance().getSession_id());
            Log.v("OkHttp", "Adding Header: " + "PHPSESSID=" + UserInfo.getInstance().getSession_id()); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
        }


            return chain.proceed(builder.build());
    }
}


