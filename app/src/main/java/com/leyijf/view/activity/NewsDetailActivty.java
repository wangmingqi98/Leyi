package com.leyijf.view.activity;

import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.NewsDetailsBean;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;

import io.reactivex.Observable;

/**
 * Created by wmq on 2018/5/4.
 */

public class NewsDetailActivty extends BaseActivity implements View.OnClickListener{
    private ImageView back;
    private TextView title;
    private TextView content;
    private Intent intent;
    String articleId,newsTitle;
    @Override
    protected void initData() {
        if(!TextUtils.isEmpty(articleId)){
            Observable observable = RetrofitFactory.getInstance().newsDetails(articleId);
            observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<NewsDetailsBean>(this) {

                @Override
                protected void onHandleSuccess(NewsDetailsBean newsDetailsBean) {
                    content.setText(Html.fromHtml(newsDetailsBean.getNotice_detail().getContent()));
                }
            });
        }


    }

    @Override
    protected void initId() {
        intent = getIntent();
        articleId = intent.getStringExtra("id");
        newsTitle = intent.getStringExtra("title");
        back = (ImageView) findViewById(R.id.zhuce_back);
        back.setOnClickListener(this);
        title = (TextView) findViewById(R.id.title);
        title.setText(newsTitle);
        content = (TextView) findViewById(R.id.content);
        content.setMovementMethod(LinkMovementMethod.getInstance());

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_newsdetail;
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
