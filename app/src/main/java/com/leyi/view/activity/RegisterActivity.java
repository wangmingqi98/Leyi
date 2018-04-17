package com.leyi.view.activity;

import android.content.Intent;
import android.os.Handler;
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
import com.leyi.bean.GetCodeBean;
import com.leyi.bean.RegisterBean;
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

/**
 * 注册页面
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
    private EditText phone_edit;
    private TextView next_step;
    private TextView zhuceXieyi;
    private TextView yinsiZhengce;
    private TextView zhuce_xieyi;
    private TextView yinsi_zhengce;
    private LinearLayout zhuceStep1;
    private TextView yanzhengYishi;
    private EditText yanzhengEdit;
    private TextView nextStep2;
    private LinearLayout zhuceStep2;
    private EditText setMima;
    private EditText okMima;
    private EditText yaoqingHaoma;
    private TextView nextStep3;
    private LinearLayout zhuceStep3;
    private Map<String, String> map;
    private TextView dao_time;
    private TextView huoqu;
Handler handler=new Handler();
    private int current;
    private ImageView shezhi_b;
    private ImageView shezhi_z;
    private ImageView queren_z;
    private ImageView queren_b;
    private GetCodeBean getCodeBean;
    private String registerOk;
    private JSONObject jsonObject;
    private String token;

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
        return R.layout.activity_register;
    }

    private void initView() {
        map = new HashMap<>();
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);
        phone_edit = (EditText) findViewById(R.id.phone_edit);
        next_step = (TextView) findViewById(R.id.next_step);
        zhuceXieyi = (TextView) findViewById(R.id.zhuce_xieyi);
        yinsiZhengce = (TextView) findViewById(R.id.yinsi_zhengce);
        zhuceStep1 = (LinearLayout) findViewById(R.id.zhuce_step1);
        yanzhengYishi = (TextView) findViewById(R.id.yanzheng_yishi);
        yanzhengEdit = (EditText) findViewById(R.id.yanzheng_edit);
        nextStep2 = (TextView) findViewById(R.id.next_step2);
        zhuceStep2 = (LinearLayout) findViewById(R.id.zhuce_step2);
        setMima = (EditText) findViewById(R.id.set_mima);
        setMima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        okMima = (EditText) findViewById(R.id.ok_mima);
        okMima.setTransformationMethod(PasswordTransformationMethod.getInstance());
        yaoqingHaoma = (EditText) findViewById(R.id.yaoqing_haoma);
        nextStep3 = (TextView) findViewById(R.id.next_step3);
        zhuceStep3 = (LinearLayout) findViewById(R.id.zhuce_step3);
        dao_time = (TextView) findViewById(R.id.dao_time);
        huoqu = (TextView) findViewById(R.id.huoqu);
        shezhi_b = (ImageView) findViewById(R.id.shezhi_biyan);
        shezhi_z = (ImageView) findViewById(R.id.shezhi_zhengyan);
        queren_z = (ImageView) findViewById(R.id.queren_zhengyan);
        queren_b = (ImageView) findViewById(R.id.queren_biyan);
        shezhi_b.setOnClickListener(this);
        shezhi_z.setOnClickListener(this);
        queren_b.setOnClickListener(this);
        queren_z.setOnClickListener(this);
        huoqu.setOnClickListener(this);
        zhuce_back.setOnClickListener(this);
        next_step.setOnClickListener(this);
        nextStep2.setOnClickListener(this);
        nextStep3.setOnClickListener(this);
    }


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
            case R.id.next_step2:
               if(checkCode()){
                   zhuceStep2.setVisibility(View.GONE);
                   zhuceStep3.setVisibility(View.VISIBLE);
               }else {
                   Toast.makeText(this, "验证码错误！请重试", Toast.LENGTH_SHORT).show();
               }

                break;
            case R.id.next_step3:
            toRegister();

                break;
            case R.id.shezhi_biyan:
//
                setMima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                shezhi_b.setVisibility(View.GONE);
                shezhi_z.setVisibility(View.VISIBLE);
                break;
            case R.id.shezhi_zhengyan:
//

                setMima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                shezhi_b.setVisibility(View.VISIBLE);
                shezhi_z.setVisibility(View.GONE);
                break;
            case R.id.queren_biyan:
//                显示
                okMima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                queren_b.setVisibility(View.GONE);
                queren_z.setVisibility(View.VISIBLE);
                break;
            case R.id.queren_zhengyan:
//                隐藏
                okMima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                queren_z.setVisibility(View.GONE);
                queren_b.setVisibility(View.VISIBLE);

                break;
        }
    }

    private void toRegister() {
////        act=register
//        user_name:用户名
//        user_pwd: 密码
//        user_pwd_confirm:确认密码
//        mobile：手机号码
//        mobile_code：手机验证码
        if(!TextUtils.isEmpty(setMima.getText().toString().trim())){
            if(!TextUtils.isEmpty(okMima.getText().toString().trim())){
                if(setMima.getText().toString().trim().equals(okMima.getText().toString().trim())){

                    Map<String,String> registerMap=new HashMap();
                     registerMap.put("act","register");
                     registerMap.put("user_name",phone_edit.getText().toString().trim());
                     registerMap.put("user_pwd",setMima.getText().toString().trim());
                     registerMap.put("user_pwd_confirm",okMima.getText().toString().trim());
                     registerMap.put("mobile",phone_edit.getText().toString().trim());
                     registerMap.put("mobile_code",yanzhengEdit.getText().toString().trim());
                     registerMap.put("token",token);
                    Log.e("aaaaa",UserInfo.getInstance().getUserToken()+"");

                    OkHttpUtil.getInstance().doHttp(registerMap, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {


                            registerOk = response.body().string();
                            jsonObject = null;
                            try {
                                jsonObject = new JSONObject(registerOk);
                                int response_code = jsonObject.getInt("response_code");



                                if(response_code==1){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.e("registerOk", registerOk);
                                            Gson gson=new Gson();
                                            RegisterBean registerBean = gson.fromJson(registerOk, RegisterBean.class);
                                            UserInfo.getInstance().saveUserPhone(phone_edit.getText().toString().trim());
                                            UserInfo.getInstance().saveUserId(registerBean.getObjects().getUser_info().getUser_id());
                                            UserInfo.getInstance().saveId_passed(registerBean.getObjects().getUser_info().getId_passed());
                                            UserInfo.getInstance().saveHas_paypassword(registerBean.getObjects().getUser_info().getHas_paypassword());
                                            UserInfo.getInstance().saveUserImg(registerBean.getObjects().getUser_info().getUser_img()+"");
                                            UserInfo.getInstance().saveUserName(registerBean.getObjects().getUser_info().getUser_name()+"");
                                            Toast.makeText(RegisterActivity.this, "注册成功,请登录", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                                    finish();




                                        }
                                    });

                                }else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                String show_err = jsonObject.getString("show_err");
                                                Toast.makeText(RegisterActivity.this,show_err , Toast.LENGTH_SHORT).show();


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
                    Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();

        }




    }

    private boolean checkCode() {
        boolean res=true;
        if(!TextUtils.isEmpty(yanzhengEdit.getText().toString().trim())){
             res=true;
        }else {
            res=false;
        }
        return res;
    }

    private void getCode() {

        if(Maches.isChinaPhoneLegal(phone_edit.getText().toString().trim())){

            map.put("mobile",phone_edit.getText().toString().trim());
            map.put("act","send_register_code");
            OkHttpUtil.getInstance().doHttp(map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("失败",e.toString());
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {



                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            String ss= null;
                            try {
                                ss = response.body().string();
                                Log.e("----",ss);
                                JSONObject jsonObject = new JSONObject(ss);
                                int response_code = jsonObject.getInt("response_code");
                                if(response_code==1){
                                    CountDownTimerUtils countDownTimerUtils=new CountDownTimerUtils(dao_time,huoqu,60000,1000);
                                    countDownTimerUtils.start();
                                    Gson gson=new Gson();
                                    getCodeBean = gson.fromJson(ss, GetCodeBean.class);
                                    token = getCodeBean.getObjects().getToken();
                                    UserInfo.getInstance().saveUserToken(token);

                                    zhuceStep1.setVisibility(View.GONE);
                                    zhuceStep2.setVisibility(View.VISIBLE);
                                    yanzhengYishi.setText("短信验证已发送至"+phone_edit.getText().toString().trim()+""+"，请注意查收");



                                }else {

                                    String e0rr = jsonObject.getString("show_err");
                                    Toast.makeText(RegisterActivity.this, e0rr+"", Toast.LENGTH_SHORT).show();
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
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
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


}
