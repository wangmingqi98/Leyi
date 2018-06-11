package com.leyijf.view.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.BindedCardBean;
import com.leyijf.bean.FeeConfigBean;
import com.leyijf.bean.LoginBean;
import com.leyijf.bean.UserBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.view.MainTabActivity;
import com.leyijf.weight.grsture.Contants;
import com.leyijf.weight.grsture.LockMode;
import com.leyijf.weight.grsture.PasswordUtil;
import com.leyijf.weight.grsture.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "LoginActivity";

    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
    private EditText shuru_phone;
    private EditText shuru_mima;
    private ImageView biyan;
    private ImageView zhengyan;
    private TextView ok_login;
    private TextView fast_regist;
    private TextView forget_mima;
    private LinearLayout zhuce_step3;
    private String results;



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
        return R.layout.activity_login;
    }

    private void initView() {
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);
        shuru_phone = (EditText) findViewById(R.id.shuru_phone);
        shuru_mima = (EditText) findViewById(R.id.shuru_mima);
        biyan = (ImageView) findViewById(R.id.login_biyan);
        shuru_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());
        zhengyan = (ImageView) findViewById(R.id.zhengyan);
        ok_login = (TextView) findViewById(R.id.ok_login);
        fast_regist = (TextView) findViewById(R.id.fast_regist);
        forget_mima = (TextView) findViewById(R.id.forget_mima);
        zhuce_step3 = (LinearLayout) findViewById(R.id.zhuce_step3);
        ok_login.setOnClickListener(this);
        fast_regist.setOnClickListener(this);
        forget_mima.setOnClickListener(this);
        biyan.setOnClickListener(this);
        zhengyan.setOnClickListener(this);
        zhuce_back.setOnClickListener(this);
        if(!TextUtils.isEmpty(UserInfo.getInstance().getUserPhone())){
            shuru_phone.setText(UserInfo.getInstance().getUserPhone());
            shuru_phone.setEnabled(false);
        }else {
            shuru_phone.setEnabled(true);
        }


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.zhuce_back:
                finish();
                break;
            case R.id.ok_login:
                    //确定登陆
                     goLogin();

                break;
            case R.id.fast_regist:
                    //快速注册
                startActivity(new Intent(this,RegisterActivity.class));
                finish();

                break;
            case R.id.forget_mima:
                //忘记密码？
                startActivity(new Intent(this,RePsdActivity.class));
                finish();
                break;

            case R.id.login_biyan:
                zhengyan.setVisibility(View.VISIBLE);
                biyan.setVisibility(View.GONE);
                shuru_mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;
            case R.id.zhengyan:

                biyan.setVisibility(View.VISIBLE);
                zhengyan.setVisibility(View.GONE);
                shuru_mima .setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;

        }


    }

    /**
     * 登录
     */
    private void goLogin() {
        if(!TextUtils.isEmpty(shuru_phone.getText().toString().trim())){
            if(!TextUtils.isEmpty(shuru_mima.getText().toString().trim())){
                Map<String,String> map=new HashMap();
                map.put("act","login");
                map.put("email",shuru_phone.getText().toString().trim());
                map.put("pwd",shuru_mima.getText().toString().trim());
                String email = shuru_phone.getText().toString().trim();
                String pwd = shuru_mima.getText().toString().trim();
                Observable observable = RetrofitFactory.getInstance().login(email,pwd);
                observable.compose(compose(this.<LoginBean>bindToLifecycle())).subscribe(new BaseObserver<LoginBean>(this) {

                    @Override
                    protected void onHandleSuccess(LoginBean loginBean) {
                        Log.d(TAG, "onHandleSuccess: "+loginBean.toString());
                        UserBean userBean = new UserBean();
                        List<BindedCardBean>  list = new ArrayList<>();
                        List<FeeConfigBean> feeConfigBeanList = new ArrayList<>();
                        userBean.setUser_name(loginBean.getUser_info().getUser_name());
                        userBean.setUser_id(loginBean.getUser_info().getUser_id());
                        userBean.setHas_paypassword(loginBean.getUser_info().getHas_paypassword());
                        userBean.setId_passed(loginBean.getUser_info().getId_passed());
                        userBean.setUser_img(loginBean.getUser_info().getUser_img());
                        userBean.setUser_mobile(loginBean.getUser_info().getUser_mobile());
                        userBean.setUser_mobile_referee(loginBean.getUser_info().getUser_mobile_referee());
                        UserInfo.getInstance().saveUserPwd(shuru_mima.getText().toString().trim());
                        Log.d(TAG, "onHandleSuccess: "+loginBean.getSession_id());
                        UserInfo.getInstance().saveSession_id(loginBean.getSession_id());
                        if(loginBean.getUser_info().getBinded_card().size()!=0){
                            //保存绑定银行卡
                             list.addAll(loginBean.getUser_info().getBinded_card());
                            for(int i = 0;i<list.size();i++){
                                UserManager.getInstance().saveLoginedType( list.get(i));

                            }

                        }
//                        if(loginBean.getUser_info().getFee_config().size()>0){
//                            feeConfigBeanList.addAll(loginBean.getUser_info().getFee_config());
//                            for(int i =0;i<feeConfigBeanList.size();i++){
//                                UserManager.getInstance().saveFeeConfiga(feeConfigBeanList.get(i));
//                            }
//                        }
                        UserManager.getInstance().saveLoginedUser(userBean);
                        Log.d(TAG, "retrofit "+userBean.toString());
                        Toast.makeText(LoginActivity.this, loginBean.getShow_err()+"", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(LoginActivity.this,MainTabActivity.class);
                        intent.putExtra("index","2");
                        startActivity(intent);
                        finish();

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });


            }else {
                Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }





    }


    /**
     * 跳转到密码处理界面
     */
    private void actionSecondActivity(final LockMode mode) {
                if (mode != LockMode.SETTING_PASSWORD) {
                    if (StringUtils.isEmpty(PasswordUtil.getPin(LoginActivity.this))) {
                        Toast.makeText(getBaseContext(), "请先设置密码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                Intent intent = new Intent(LoginActivity.this, DrawPsdActivity.class);
                intent.putExtra(Contants.INTENT_SECONDACTIVITY_KEY, mode);
                startActivity(intent);
                LoginActivity.this.finish();
            }






    }




