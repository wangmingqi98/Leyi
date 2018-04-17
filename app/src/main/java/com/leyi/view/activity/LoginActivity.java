package com.leyi.view.activity;

import android.content.Intent;
import android.os.Bundle;
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

import com.google.gson.Gson;
import com.leyi.R;
import com.leyi.base.BaseActivity;
import com.leyi.bean.LoginOkBean;
import com.leyi.bean.UserInfo;
import com.leyi.util.AppUtils;
import com.leyi.util.OkHttpUtil;
import com.leyi.weight.grsture.Contants;
import com.leyi.weight.grsture.LockMode;
import com.leyi.weight.grsture.PasswordUtil;
import com.leyi.weight.grsture.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

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
//确定登陆
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

    private void goLogin() {
        if(!TextUtils.isEmpty(shuru_phone.getText().toString().trim())){
            if(!TextUtils.isEmpty(shuru_mima.getText().toString().trim())){
                Map<String,String> map=new HashMap();
                map.put("act","login");
                map.put("email",shuru_phone.getText().toString().trim());
                map.put("pwd",shuru_mima.getText().toString().trim());

                OkHttpUtil.getInstance().doHttp(map, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        results = response.body().string();
                        try {
                            final JSONObject jsonObject=new JSONObject(results);
                            int response_code = jsonObject.getInt("response_code");
                            if(response_code==1){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.e("loginresult", results);
                                        Gson gson=new Gson();
                                        LoginOkBean loginOkBean = gson.fromJson(results, LoginOkBean.class);

                                        if(loginOkBean.getObjects().getUser_info().getUser_id().equals(UserInfo.getInstance().getUserId())&& AppUtils.getStates()){
                                            UserInfo.getInstance().saveUserState(loginOkBean.getObjects().getUser_login_status());
                                            UserInfo.getInstance().saveUserPhone(shuru_phone.getText().toString().trim());
                                            UserInfo.getInstance().saveUserId(loginOkBean.getObjects().getUser_info().getUser_id());
                                            UserInfo.getInstance().saveId_passed(loginOkBean.getObjects().getUser_info().getId_passed());
                                            UserInfo.getInstance().saveHas_paypassword(loginOkBean.getObjects().getUser_info().getHas_paypassword());
                                            UserInfo.getInstance().saveUserImg(loginOkBean.getObjects().getUser_info().getUser_img()+"");
                                            UserInfo.getInstance().saveUserName(loginOkBean.getObjects().getUser_info().getUser_name()+"");
                                            UserInfo.getInstance().saveUserPwd(shuru_mima.getText().toString().trim());
                                            Toast.makeText(LoginActivity.this, loginOkBean.getObjects().getShow_err()+"", Toast.LENGTH_SHORT).show();
                                            finish();


                                        }


                                        else {
                                            UserInfo.getInstance().saveUserState(loginOkBean.getObjects().getUser_login_status());
                                            UserInfo.getInstance().saveUserPhone(shuru_phone.getText().toString().trim());
                                            UserInfo.getInstance().saveUserId(loginOkBean.getObjects().getUser_info().getUser_id());
                                            UserInfo.getInstance().saveId_passed(loginOkBean.getObjects().getUser_info().getId_passed());
                                            UserInfo.getInstance().saveHas_paypassword(loginOkBean.getObjects().getUser_info().getHas_paypassword());
                                            UserInfo.getInstance().saveUserImg(loginOkBean.getObjects().getUser_info().getUser_img()+"");
                                            UserInfo.getInstance().saveUserName(loginOkBean.getObjects().getUser_info().getUser_name()+"");
                                            UserInfo.getInstance().saveUserPwd(shuru_mima.getText().toString().trim());
                                            Intent intent = getIntent();
                                            Bundle extras = intent.getExtras();
                                            if(extras!=null){
                                                int mode = extras.getInt("mode");
                                                if(mode==1){
                                                    actionSecondActivity(LockMode.SETTING_PASSWORD);
                                                }else if(mode==2){
                                                    actionSecondActivity(LockMode.CLEAR_PASSWORD);
                                                }

                                            }else {


                                                actionSecondActivity(LockMode.SETTING_PASSWORD);
                                                LoginActivity.this.finish();

                                            }

                                        }











                                    }
                                });
                            }else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            String show_err = jsonObject.getString("show_err");
                                            Toast.makeText(LoginActivity.this,show_err, Toast.LENGTH_SHORT).show();


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                });
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




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




