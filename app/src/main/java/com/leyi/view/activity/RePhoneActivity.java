package com.leyi.view.activity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leyi.R;
import com.leyi.base.BaseActivity;

/**
 * 重置密码
 */
public class RePhoneActivity extends BaseActivity implements View.OnClickListener {

    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
    private EditText re_mima;
    private ImageView re_zhengyan;
    private ImageView re_biyan;
    private TextView next_step;
    private LinearLayout re_step1;
    private TextView yanzheng_yishi;
    private EditText yanzheng_edit;
    private TextView yanzheng_time;
    private TextView yanzheng_ok;
    private LinearLayout re_step3;
    private EditText phone_edit;
    private TextView huoqu_yanzheng;
    private LinearLayout re_step2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        initView();
    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_re_phone;
    }

    private void initView() {
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        zhuce_back.setOnClickListener(this);
        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);
        re_mima = (EditText) findViewById(R.id.re_mima);
        re_zhengyan = (ImageView) findViewById(R.id.re_zhengyan);
        re_zhengyan.setOnClickListener(this);
        re_biyan = (ImageView) findViewById(R.id.re_biyan);
        re_biyan.setOnClickListener(this);
        next_step = (TextView) findViewById(R.id.next_step);
        re_step1 = (LinearLayout) findViewById(R.id.re_step1);
        re_step1.setOnClickListener(this);
        yanzheng_yishi = (TextView) findViewById(R.id.yanzheng_yishi);
        yanzheng_edit = (EditText) findViewById(R.id.yanzheng_edit);
        yanzheng_time = (TextView) findViewById(R.id.yanzheng_time);
        yanzheng_ok = (TextView) findViewById(R.id.yanzheng_ok);
        re_step3 = (LinearLayout) findViewById(R.id.re_step3);
        phone_edit = (EditText) findViewById(R.id.phone_edit);
        huoqu_yanzheng = (TextView) findViewById(R.id.huoqu_yanzheng);
        re_step2 = (LinearLayout) findViewById(R.id.re_step2);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhuce_back:
                finish();
                break;

            case R.id.next_step:
                re_step1.setVisibility(View.GONE);
                re_step2.setVisibility(View.VISIBLE);
                break;
            case R.id.next_step2:
                re_step2.setVisibility(View.GONE);
                re_step3.setVisibility(View.VISIBLE);

            case R.id.re_zhengyan:
                re_zhengyan.setVisibility(View.GONE);
                re_biyan.setVisibility(View.VISIBLE);
                re_mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;
            case R.id.re_biyan:
                re_biyan.setVisibility(View.GONE);
                re_zhengyan.setVisibility(View.VISIBLE);
                re_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;

        }
    }
}
