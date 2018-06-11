package com.leyijf.view.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.leyijf.R;
import com.leyijf.base.BaseActivity;

/**\
 * 富友界面
 * Created by wmq on 2018/5/28.
 */

public class FuYouPayActivity extends BaseActivity implements View.OnClickListener{
    private ImageView back;
    private WebView webView;
    private Intent intent;
    private String api;
    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        back = (ImageView) findViewById(R.id.zhuce_back);
        back.setOnClickListener(this);
        webView = (WebView) findViewById(R.id.web);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
        //设置渲染优先级
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);//关闭webview中缓存
        // 开启DOM storage API 功能
        webView.getSettings().setDomStorageEnabled(true);
        // 开启database storage API功能
        webView.getSettings().setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + "/webcache2";
        // 设置数据库缓存路径
        webView.getSettings().setDatabasePath(cacheDirPath); // API 19 deprecated
        // 设置Application caches缓存目录
        webView.getSettings().setAppCachePath(cacheDirPath);
        // 开启Application Cache功能
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);//设置缓冲大小，我设的是8M
        intent = getIntent();
        api = intent.getStringExtra("api");
        webView.loadUrl(api);


    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fuyou;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuce_back:
                finish();
                break;
        }

    }
}
