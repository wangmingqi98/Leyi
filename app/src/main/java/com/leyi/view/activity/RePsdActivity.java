package com.leyi.view.activity;

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

import com.leyi.R;
import com.leyi.base.BaseActivity;
import com.leyi.bean.UserInfo;
import com.leyi.util.CountDownTimerUtils;
import com.leyi.util.Maches;
import com.leyi.util.OkHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RePsdActivity extends BaseActivity implements View.OnClickListener {

    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
    private EditText phone_edit;
    private TextView next_step;
    private LinearLayout re_step1;
    private TextView yanzheng_yishi;
    private EditText yanzheng_edit;
    private TextView next_step2;
    private LinearLayout re_step2;
    private EditText re_mima;
    private ImageView re_zhengyan;
    private ImageView re_biyan;
    private EditText okre_mima;
    private ImageView ok_biyan;
    private ImageView ok_zhengyan;
    private TextView ok_andlogin;
    private LinearLayout re_step3;
    private Map<String, String> map;
    private TextView dao_time;
    private TextView huoqu;
    private String okremima;
    private String remima;

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
        return R.layout.activity_re_psd;
    }

    private void initView() {
        map = new HashMap();
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);
        phone_edit = (EditText) findViewById(R.id.phone_edit);
        next_step = (TextView) findViewById(R.id.next_step);
        re_step1 = (LinearLayout) findViewById(R.id.re_step1);
        yanzheng_yishi = (TextView) findViewById(R.id.yanzheng_yishi);
        yanzheng_edit = (EditText) findViewById(R.id.yanzheng_edit);
        next_step2 = (TextView) findViewById(R.id.next_step2);
        re_step2 = (LinearLayout) findViewById(R.id.re_step2);
        re_mima = (EditText) findViewById(R.id.re_mima);
        re_zhengyan = (ImageView) findViewById(R.id.re_zhengyan);
        re_biyan = (ImageView) findViewById(R.id.re_biyan);
        okre_mima = (EditText) findViewById(R.id.okre_mima);
        ok_biyan = (ImageView) findViewById(R.id.ok_biyan);
        ok_zhengyan = (ImageView) findViewById(R.id.ok_zhengyan);
        ok_andlogin = (TextView) findViewById(R.id.ok_andlogin);
        re_step3 = (LinearLayout) findViewById(R.id.re_step3);
        dao_time = (TextView) findViewById(R.id.dao_time);
        huoqu = (TextView) findViewById(R.id.huoqu);
        zhuce_back.setOnClickListener(this);
        next_step.setOnClickListener(this);
        next_step2.setOnClickListener(this);
        ok_andlogin.setOnClickListener(this);
        re_zhengyan.setOnClickListener(this);
        re_biyan.setOnClickListener(this);
        ok_zhengyan.setOnClickListener(this);
        ok_biyan.setOnClickListener(this);
        huoqu.setOnClickListener(this);




    }

////act=save_reset_pwd
//    mobile：手机号码
//    mobile_code：手机验证码
//    user_pwd: 密码
//    user_pwd_confirm:确认密码
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhuce_back:
                finish();
                break;
            case R.id.huoqu:
                getCode();
                break;

            case R.id.next_step:
             getCode();

                break;


//            act=send_reset_pwd_code
//            输入参数：
//            act=send_reset_pwd_code
//            mobile：手机号码

            case R.id.next_step2:
                if(!TextUtils.isEmpty(yanzheng_edit.getText().toString().trim())){

                    map.put("mobile_code",yanzheng_edit.getText().toString().trim());



                     re_step2.setVisibility(View.GONE);
                    re_step3.setVisibility(View.VISIBLE);
              }else {
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.ok_andlogin:
                remima = re_mima.getText().toString().trim();
                okremima = okre_mima.getText().toString().trim();
                if(!TextUtils.isEmpty(remima)){
                    if(!TextUtils.isEmpty(okremima)){
                        if(okremima.equals(remima)){
                            map.put("user_pwd", remima);
                            map.put("user_pwd_confirm", okremima);
                            OkHttpUtil.getInstance().doHttp(map, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    final String results = response.body().string();


                                    try {

                                        final JSONObject jsonObject=new JSONObject(results);
                                        int response_code = jsonObject.getInt("response_code");
                                        if (response_code==1){
                                            Log.e("save_reset_pwd",results+"");
                                            UserInfo.getInstance().saveUserPwd(okremima);
                                            
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(RePsdActivity.this, "密码重置成功！，请重新登录", Toast.LENGTH_SHORT).show();
                                                    UserInfo.getInstance().ExeitUser(RePsdActivity.this);
                                                    startActivity(new Intent(RePsdActivity.this,LoginActivity.class));
                                                    finish();
                                                }
                                            });
                                            

                                        }else {

                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });




                        }else {
                            Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }else {
                        Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }


                break;
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
            case R.id.ok_biyan:
                ok_biyan.setVisibility(View.GONE);
                ok_zhengyan.setVisibility(View.VISIBLE);
                okre_mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;
            case R.id.ok_zhengyan:
                ok_zhengyan.setVisibility(View.GONE);
                ok_biyan.setVisibility(View.VISIBLE);

                okre_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;
        }
    }

    private void getCode() {

        if(!TextUtils.isEmpty(phone_edit.getText().toString().trim())&& Maches.isChinaPhoneLegal(phone_edit.getText().toString().trim())){
            map.put("act","save_reset_pwd");
            map.put("mobile",phone_edit.getText().toString().trim());
            Map<String,String> maps=new HashMap<>();
            maps.put("act","send_reset_pwd_code");
            maps.put("mobile",phone_edit.getText().toString().trim());

            OkHttpUtil.getInstance().doHttp(maps, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    final String ss= response.body().string();
                    Log.e("send_reset_pwd_code",ss+"");


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CountDownTimerUtils countDownTimerUtils=new CountDownTimerUtils(dao_time,huoqu,60000,1000);
                            countDownTimerUtils.start();


                            try {

                                Log.e("----",ss);
                                JSONObject jsonObject = new JSONObject(ss);
                                int response_code = jsonObject.getInt("response_code");
                                if(response_code==1){


                                    re_step1.setVisibility(View.GONE);
                                    re_step2.setVisibility(View.VISIBLE);
                                    yanzheng_yishi.setText("短信验证已发送至"+phone_edit.getText().toString().trim()+""+"，请注意查收");




                                }else {

                                    String e0rr = jsonObject.getString("show_err");
                                    Toast.makeText(RePsdActivity.this, e0rr+"", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });

                }
            });

        }else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(RePsdActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }





}
