package com.leyijf.view.activity;

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
import com.leyijf.bean.RegisterBean;
import com.leyijf.bean.SetPasswordCodeBean;
import com.leyijf.bean.UserBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.bean.VerifyCodeBean;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;
import com.leyijf.util.Maches;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class RePsdActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "RePsdActivity";
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
    private String mobile,mobileCode,token;

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
            case R.id.next_step2:
                initVerifyCode();
                break;

            case R.id.ok_andlogin:
                initForgetPwdComit();
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

    /**
     * 获取验证码
     */
    private void getCode() {

        if(!TextUtils.isEmpty(phone_edit.getText().toString().trim())&& Maches.isChinaPhoneLegal(phone_edit.getText().toString().trim())){
            JSONObject object = new JSONObject();
            try {
                object.put("mobile", mobile);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String requestData = Aes128.encrypt((object.toString()));
            String jjjjj = Aes128.decrypt(requestData);
            Log.d(TAG, "getCode: " + jjjjj);
            Log.d(TAG, "getCode: " + object.toString());
            Observable observable = RetrofitFactory.getInstance().forgetCode(requestData);
            observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<SetPasswordCodeBean>(this) {
                @Override
                protected void onHandleSuccess(SetPasswordCodeBean setPasswordCodeBean) {
                    token = setPasswordCodeBean.getToken();
                    re_step1.setVisibility(View.GONE);
                    re_step2.setVisibility(View.VISIBLE);
                    yanzheng_yishi.setText("短信验证已发送至"+phone_edit.getText().toString().trim()+""+"，请注意查收");
                }


            });

        }else {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 验证验证码接口
     */
    private void initVerifyCode(){
        if(!TextUtils.isEmpty(yanzheng_edit.getText().toString().trim())) {
            mobileCode = yanzheng_edit.getText().toString().trim();
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
                    re_step2.setVisibility(View.GONE);
                    re_step3.setVisibility(View.VISIBLE);
                }


            });
        }else {
            Toast.makeText(this,"请输入验证码",Toast.LENGTH_LONG).show();
        }

    }

    /**
     * 忘记密码--提交
     */
    private void initForgetPwdComit(){
        if(!TextUtils.isEmpty(re_mima.getText().toString().trim())){
            if(!TextUtils.isEmpty(okre_mima.getText().toString().trim())){
                if(re_mima.getText().toString().trim().equals(re_mima.getText().toString().trim())){
                    JSONObject object = new JSONObject();
                    try {
                        object.put("mobile", mobile);
                        object.put("user_pwd", okre_mima.getText().toString().trim());
                        object.put("token", token);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String requestData = Aes128.encrypt((object.toString()));
                    String jjjjj = Aes128.decrypt(requestData);
                    Log.d(TAG, "initVerify: " + jjjjj);
                    Log.d(TAG, "initVerify: " + object.toString());
                    Observable observable = RetrofitFactory.getInstance().forgetPwdComit(requestData);
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
                            UserInfo.getInstance().saveUserPwd(okre_mima.getText().toString().trim());
                            if(registerBean.getUser_info().getBinded_card().size()!=0){
                                //保存绑定银行卡
                                list = JSON.parseArray(registerBean.getUser_info().getBinded_card().toString(),BindedCardBean.class);
                                for(int i = 0;i<list.size();i++){
                                    UserManager.getInstance().saveLoginedType(list.get(i));

                                }

                            }
                            UserManager.getInstance().saveLoginedUser(userBean);
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


}
