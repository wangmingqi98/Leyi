package com.leyi.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.leyi.R;
import com.leyi.base.BaseActivity;
import com.leyi.bean.UserInfo;
import com.leyi.util.AppUtils;
import com.leyi.weight.RoundImageView;
import com.leyi.weight.grsture.Contants;
import com.leyi.weight.grsture.LockMode;
import com.leyi.weight.grsture.PasswordUtil;
import com.leyi.weight.grsture.StringUtils;

/**
 * 设置页面
 */
public class SetingActivity extends BaseActivity implements View.OnClickListener {

    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
    private TextView set_phone;
    private LinearLayout set_phone_lin;
    private LinearLayout set_mima_lin;
    private TextView set_mybank;
    private LinearLayout set_mybank_lin;
    private TextView set_jiaoy;
    private LinearLayout set_jiaoyi_lin;
    private TextView set_shiming;
    private LinearLayout set_shiming_lin;
    private TextView next_stepshen;
    private TextView next_stepqian;
    private ImageView kaiguan;
    private LinearLayout xiugai_mima;
    private ImageView kaikai;
    private RoundImageView user_img;


    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        initView();
    }

    @Override
    protected int getContentViewId() {
        //为状态栏着色
        withColor();
        return R.layout.activity_seting;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (StringUtils.isEmpty(PasswordUtil.getPin(this))) {
            kaikai.setVisibility(View.GONE);
            kaiguan.setVisibility(View.VISIBLE);
        }else {

            kaiguan.setVisibility(View.GONE);
            kaikai.setVisibility(View.VISIBLE);
        }
if(!"".equals(UserInfo.getInstance().getUserImg())){
    Glide.with(this).load(UserInfo.getInstance().getUserImg()+"");

}
       if(UserInfo.getInstance().getHas_paypassword()!=0){
           set_mybank.setText("已绑定");
           set_jiaoy.setText("已设置");
       }else {
           set_mybank.setText("未绑定");
           set_jiaoy.setText("未设置");
       }
        if(UserInfo.getInstance().getId_passed()!=0){
            set_shiming.setText("已认证");
        }else {
            set_shiming.setText("未认证");
        }

set_phone.setText(""+UserInfo.getInstance().getUserPhone());


    }

    private void initView() {
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        zhuce_back.setOnClickListener(this);
        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);
        user_img = (RoundImageView) findViewById(R.id.user_img);
        set_phone = (TextView) findViewById(R.id.set_phone);
        set_phone_lin = (LinearLayout) findViewById(R.id.set_phone_lin);
        set_phone_lin.setOnClickListener(this);
        set_mima_lin = (LinearLayout) findViewById(R.id.set_mima_lin);
        set_mima_lin.setOnClickListener(this);
        set_mybank = (TextView) findViewById(R.id.set_mybank);
        set_mybank_lin = (LinearLayout) findViewById(R.id.set_mybank_lin);
        set_mybank_lin.setOnClickListener(this);
        set_jiaoy = (TextView) findViewById(R.id.set_jiaoy);
        set_jiaoyi_lin = (LinearLayout) findViewById(R.id.set_jiaoyi_lin);
        set_jiaoyi_lin.setOnClickListener(this);
        set_shiming = (TextView) findViewById(R.id.set_shiming);
        set_shiming_lin = (LinearLayout) findViewById(R.id.set_shiming_lin);
        set_shiming_lin.setOnClickListener(this);
        next_stepshen = (TextView) findViewById(R.id.next_stepshen);
        next_stepshen.setOnClickListener(this);
        kaiguan = (ImageView) findViewById(R.id.kaiguan);
        kaiguan.setOnClickListener(this);
        kaikai = (ImageView) findViewById(R.id.kaikai);
        kaikai.setOnClickListener(this);
        xiugai_mima = (LinearLayout) findViewById(R.id.xiugai_mima);
        xiugai_mima.setOnClickListener(this);


    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.zhuce_back:
                finish();
                break;

            case R.id.next_stepshen:
                UserInfo.getInstance().ExeitUser(SetingActivity.this);
                AppUtils.setStates(false);
                startActivity(new Intent(this,LoginActivity.class));
                finish();
              break;
            case R.id.set_phone_lin:
                startActivity(new Intent(this,PhoneMaActivity.class));
                break;
            case R.id.set_mima_lin:
                startActivity(new Intent(this,RePsdActivity.class));
                break;
            case R.id.set_mybank_lin:
                break;
            case R.id.set_jiaoyi_lin:
                break;
            case R.id.set_shiming_lin:

                break;
            case R.id.kaiguan:
//                开启手势密码
                kaiguan.setVisibility(View.GONE);
                kaikai.setVisibility(View.VISIBLE);
                Intent intent=new Intent(this,LoginActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("mode",1);

                intent.putExtras(bundle);
                startActivity(intent);
//                actionSecondActivity(LockMode.SETTING_PASSWORD);
                break;
            case R.id.kaikai:
//                关闭
                kaikai.setVisibility(View.GONE);
                kaiguan.setVisibility(View.VISIBLE);
                actionSecondActivity(LockMode.CLEAR_PASSWORD);


                break;
            case R.id.xiugai_mima:
//                Intent intent3=new Intent(this,DrawPsdActivity.class);
//                Bundle bundle3=new Bundle();
//                bundle3.putInt("mode",3);
//
//                intent3.putExtras(bundle3);
//                startActivity(intent3);
                actionSecondActivity(LockMode.EDIT_PASSWORD);
                break;

        }
    }

    private void actionSecondActivity(LockMode mode) {
        if (mode != LockMode.SETTING_PASSWORD) {
            if (StringUtils.isEmpty(PasswordUtil.getPin(this))) {
                Toast.makeText(getBaseContext(), "请先设置密码", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Intent intent = new Intent(this, DrawPsdActivity.class);
        intent.putExtra(Contants.INTENT_SECONDACTIVITY_KEY, mode);
        startActivity(intent);

    }

}
