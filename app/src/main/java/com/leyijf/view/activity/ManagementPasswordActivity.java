package com.leyijf.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.manager.UserManager;
import com.leyijf.util.AppUtils;
import com.leyijf.weight.SwitchView;
import com.leyijf.weight.grsture.Contants;
import com.leyijf.weight.grsture.LockMode;
import com.leyijf.weight.grsture.PasswordUtil;
import com.leyijf.weight.grsture.StringUtils;

/**
 *  管理密码页面
 * Created by wmq on 2018/4/23.
 */

public class ManagementPasswordActivity extends BaseActivity implements View.OnClickListener ,SwitchView.OnStateChangedListener{
    private LinearLayout updateLogin;
    private LinearLayout updatePay;
    private LinearLayout updateDraw;
    private TextView setPay;
    private ImageView back;
    private SwitchView aSwitch;
    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        initView();
        int payPassord =  UserManager.getInstance().getLoginUser().getHas_paypassword();
        if(payPassord!=0){
            setPay.setText("已设置");
            updatePay.setFocusable(true);
        }else {
            setPay.setText("未设置");
            updatePay.setFocusable(false);
        }

    }

    /**
     * 初始化控件
     */
    private void initView() {
        updateLogin = (LinearLayout) findViewById(R.id.update_login);
        updatePay = (LinearLayout) findViewById(R.id.update_pay);
        updateDraw = (LinearLayout) findViewById(R.id.update_draw);
        setPay = (TextView) findViewById(R.id.setpay);
        back = (ImageView) findViewById(R.id.zhuce_back);
        aSwitch = (SwitchView) findViewById(R.id.switch_view);
        if(AppUtils.getStates()){
            aSwitch.toggleSwitch(true);
            updateDraw.setVisibility(View.VISIBLE);
        }else {
            aSwitch.toggleSwitch(false);
            updateDraw.setVisibility(View.GONE);
        }
        aSwitch.setOnStateChangedListener(this);
        back.setOnClickListener(this);
        updateLogin.setOnClickListener(this);
        updatePay.setOnClickListener(this);
        updateDraw.setOnClickListener(this);
    }



    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.mannagement_acitivity;
    }

    /**
     * 所有view的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.zhuce_back:
                finish();
                break;
            case R.id.update_login://管理登录密码
                intent.putExtra("tag","1");
                intent.setClass(this,GetCodeActivity.class);
                startActivity(intent);
                break;
            case R.id.update_pay://设置交易密码
                intent.setClass(this,SetPayPsdActivity.class);
                startActivity(intent);
                break;
            case R.id.update_draw:
                intent.setClass(this,DrawPsdActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    @Override
    public void toggleToOn() {
        aSwitch.toggleSwitch(true);
        updateDraw.setVisibility(View.VISIBLE);
//        Intent intent = new Intent();
//        intent.setClass(this,LoginActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putInt("mode",1);
//        intent.putExtras(bundle);
//        startActivity(intent);
        actionSecondActivity(LockMode.SETTING_PASSWORD);

    }

    @Override
    public void toggleToOff() {
        aSwitch.toggleSwitch(false);
        updateDraw.setVisibility(View.GONE);
        actionSecondActivity(LockMode.CLEAR_PASSWORD);

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
