package com.leyijf.view.activity;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.leyijf.R;
import com.leyijf.base.BaseActivity;

/**
 * 支付结果
 */
public class PayResultActivity extends BaseActivity {

    private WebView tv_web;
    private String html;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_result);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        html = bundle.getString("html");
        Log.e("====",html);
        tv_web = (WebView) findViewById(R.id.tv_web);
        WebSettings settings = tv_web.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }





        tv_web.loadUrl(html);
        tv_web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                tv_web.loadUrl(html);

                return super.shouldOverrideUrlLoading(view, request);
            }


            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
                handler.proceed(); // 接受网站证书
            }




        });
    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_pay_result;
    }

}
