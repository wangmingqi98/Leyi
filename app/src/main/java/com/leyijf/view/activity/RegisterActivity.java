package com.leyijf.view.activity;

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

import com.alibaba.fastjson.JSON;
import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.BindedCardBean;
import com.leyijf.bean.GetCodeBean;
import com.leyijf.bean.RegisterBean;
import com.leyijf.bean.SetPasswordCodeBean;
import com.leyijf.bean.UserBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.bean.VerifyCodeBean;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;
import com.leyijf.util.CountDownTimerUtils;
import com.leyijf.util.Maches;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * 注册页面
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG ="RegisterActivity" ;
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
    private String token,mobile,mobileCode,referer;

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
        CountDownTimerUtils countDownTimerUtils=new CountDownTimerUtils(dao_time,huoqu,60000,1000);
                                    countDownTimerUtils.start();
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
                    initVerifyCode();
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

                    if(!TextUtils.isEmpty(yaoqingHaoma.getText().toString().trim())){
                        referer = yaoqingHaoma.getText().toString().trim();
                    }else {
                        referer = "";

                    }
                    JSONObject object = new JSONObject();
                    try {
                        object.put("mobile", mobile);
                        object.put("referer", referer);
                        object.put("user_pwd", setMima.getText().toString().trim());
                        object.put("token", token);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String requestData = Aes128.encrypt((object.toString()));
                    String jjjjj = Aes128.decrypt(requestData);
                    Log.d(TAG, "initVerify: " + jjjjj);
                    Log.d(TAG, "initVerify: " + object.toString());
                    Observable observable = RetrofitFactory.getInstance().registerComit(requestData);
                    observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<RegisterBean>(this) {
                        @Override
                        protected void onHandleSuccess(RegisterBean registerBean) {
                            UserBean userBean = new UserBean();
                            List<BindedCardBean> list = new ArrayList<>();
                            userBean.setUser_name(registerBean.getUser_info().getUser_name());
                            userBean.setUser_id(registerBean.getUser_info().getUser_id());
                            userBean.setHas_paypassword(registerBean.getUser_info().getHas_paypassword());
                            userBean.setId_passed(registerBean.getUser_info().getId_passed());
                            userBean.setUser_img(registerBean.getUser_info().getUser_img());
                            userBean.setUser_mobile(registerBean.getUser_info().getUser_mobile());
                            userBean.setUser_mobile_referee(registerBean.getUser_info().getUser_mobile_referee());
                            UserInfo.getInstance().saveUserPwd(setMima.getText().toString().trim());
                            if(registerBean.getUser_info().getBinded_card().size()!=0){
                                //保存绑定银行卡
                                list.addAll(userBean.getBinded_card());
                                for(int i = 0;i<list.size();i++){
                                    UserManager.getInstance().saveLoginedType(list.get(i));

                                }

                            }
                            UserManager.getInstance().saveLoginedUser(userBean);
                            Toast.makeText(RegisterActivity.this,"注册成功，请登录",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            finish();
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

    /**
     * 获取验证码接口
     */
    private void getCode() {

        if(Maches.isChinaPhoneLegal(phone_edit.getText().toString().trim())){
            mobile = phone_edit.getText().toString().trim();
            JSONObject object = new JSONObject();
            try {
                object.put("mobile", mobile);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String requestData = Aes128.encrypt((object.toString()));
            String jjjjj = Aes128.decrypt(requestData);
            Log.d(TAG, "initVerify: " + jjjjj);
            Log.d(TAG, "initVerify: " + object.toString());
            Observable observable = RetrofitFactory.getInstance().register(requestData);
            observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<SetPasswordCodeBean>(this) {
                @Override
                protected void onHandleSuccess(SetPasswordCodeBean setPasswordCodeBean) {
                    token = setPasswordCodeBean.getToken();
                    zhuceStep1.setVisibility(View.GONE);
                    zhuceStep2.setVisibility(View.VISIBLE);
                    yanzhengYishi.setText("短信验证已发送至"+phone_edit.getText().toString().trim()+""+"，请注意查收");
                }


            });

        }else {
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }


    }

    /**
     * 验证验证码接口
     */
    private void initVerifyCode() {
        if (!TextUtils.isEmpty(yanzhengEdit.getText().toString().trim())) {
            mobileCode = yanzhengEdit.getText().toString().trim();
            JSONObject object = new JSONObject();
            try {
                object.put("mobile", mobile);
                object.put("mobile_code", mobileCode);
                object.put("token", token);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String requestData = Aes128.encrypt((object.toString()));
            String jjjjj = Aes128.decrypt(requestData);
            Log.d(TAG, "initVerify: " + jjjjj);
            Log.d(TAG, "initVerify: " + object.toString());
            Observable observable = RetrofitFactory.getInstance().registerVerify(requestData);
            observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<VerifyCodeBean>(this) {
                @Override
                protected void onHandleSuccess(VerifyCodeBean verifyCodeBean) {
                    token = verifyCodeBean.getToken();
                    zhuceStep2.setVisibility(View.GONE);
                    zhuceStep3.setVisibility(View.VISIBLE);
                }


            });


        }else {
            Toast.makeText(this,"请输入验证码",Toast.LENGTH_LONG).show();
        }
    }

}
