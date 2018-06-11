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
import com.leyijf.bean.SetPasswordCodeBean;
import com.leyijf.bean.SetPayPwdBean;
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

import io.reactivex.Observable;

/**
 * 设置交易密码
 */
public class SetPayPsdActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "SetPayPsdActivity";
    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
    private TextView yanzheng_yishi;
    private EditText inputcode;
    private TextView time;
    private TextView get;
    private TextView next_step2;
    private LinearLayout zhuce_step2;
    private EditText set_mima;
    private ImageView shezhi_biyan;
    private ImageView shezhi_zhengyan;
    private EditText ok_mima;
    private ImageView queren_biyan;
    private ImageView queren_zhengyan;
    private TextView next_step3;
    private LinearLayout zhuce_step3;
    private String setM;
    private String okM;
    private String trim,token,inputCodeNum;


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
        return R.layout.activity_set_pay_psd;
    }

    private void initView() {

        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);
        yanzheng_yishi = (TextView) findViewById(R.id.yanzheng_yishi);
        inputcode = (EditText) findViewById(R.id.inputcode);
        time = (TextView) findViewById(R.id.time);
        get = (TextView) findViewById(R.id.get);
        next_step2 = (TextView) findViewById(R.id.next_step2);
        zhuce_step2 = (LinearLayout) findViewById(R.id.zhuce_step2);
        set_mima = (EditText) findViewById(R.id.set_mima);
        set_mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        shezhi_biyan = (ImageView) findViewById(R.id.shezhi_biyan);
        shezhi_zhengyan = (ImageView) findViewById(R.id.shezhi_zhengyan);
        ok_mima = (EditText) findViewById(R.id.ok_mima);
        ok_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());
        queren_biyan = (ImageView) findViewById(R.id.queren_biyan);
        queren_zhengyan = (ImageView) findViewById(R.id.queren_zhengyan);
        next_step3 = (TextView) findViewById(R.id.next_step3);
        zhuce_step3 = (LinearLayout) findViewById(R.id.zhuce_step3);
        next_step2.setOnClickListener(this);
        next_step3.setOnClickListener(this);
        queren_biyan.setOnClickListener(this);
        queren_zhengyan.setOnClickListener(this);
        shezhi_biyan.setOnClickListener(this);
        shezhi_zhengyan.setOnClickListener(this);
        get.setOnClickListener(this);
        zhuce_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.next_step3:
                setM = set_mima.getText().toString().trim();
                okM = ok_mima.getText().toString().trim();
                if(!TextUtils.isEmpty(setM)&&!TextUtils.isEmpty(okM)) {
                    if (setM.equals(okM)) {
                        if (Maches.isPayMima(setM)) {
                            setPayPwd(setM);

                        } else {
                            Toast.makeText(this, "密码格式不合法", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    } else {

                        Toast.makeText(this, "请确认输入的密码是否一致", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "请输入", Toast.LENGTH_SHORT).show();
                    return;

                }

                break;

            case R.id.zhuce_back:
                 finish();

                break;
            case R.id.shezhi_biyan:
//
                set_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                shezhi_biyan.setVisibility(View.GONE);
                shezhi_zhengyan.setVisibility(View.VISIBLE);
                break;
            case R.id.shezhi_zhengyan:
//

                set_mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                shezhi_biyan.setVisibility(View.VISIBLE);
                shezhi_zhengyan.setVisibility(View.GONE);
                break;
            case R.id.queren_biyan:
//                显示
                ok_mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                queren_biyan.setVisibility(View.GONE);
                queren_zhengyan.setVisibility(View.VISIBLE);
                break;
            case R.id.queren_zhengyan:
//                隐藏
                ok_mima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                queren_zhengyan.setVisibility(View.GONE);
                queren_biyan.setVisibility(View.VISIBLE);

                break;


            case R.id.next_step2:
                if(!TextUtils.isEmpty(inputcode.getText().toString())){
                        initVerifyCode();
                }else {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.get:
                iningetCode();


                break;


        }


    }

    /**
     * 设置交易密码---验证验证码
     */
    private void initVerifyCode() {

        if(inputcode.getText().toString()!=null) {
            inputCodeNum = inputcode.getText().toString();
            JSONObject object = new JSONObject();
            try {
                object.put("email", UserManager.getInstance().getLoginUser().getUser_mobile_referee());
                object.put("pwd", UserInfo.getInstance().getUserPwd());
                object.put("mobile_code", inputCodeNum);
                object.put("token", token);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String requestData = Aes128.encrypt((object.toString()));
            String jjjjj = Aes128.decrypt(requestData);
            Log.d(TAG, "initVerify: " + jjjjj);
            Log.d(TAG, "initVerify: " + object.toString());
            Observable observable = RetrofitFactory.getInstance().verifyCode(requestData);
            observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<VerifyCodeBean>(this) {

                @Override
                protected void onHandleSuccess(VerifyCodeBean verifyCodeBean) {
                    Log.d(TAG, "onHandleSuccess: " + verifyCodeBean.getToken());
                    //进入设置交易密码页面
                    zhuce_step2.setVisibility(View.GONE);
                    zhuce_step3.setVisibility(View.VISIBLE);
                }
            });
        }else {
            Toast.makeText(this,"请输入验证码",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 设置交易密码---发送验证码
     */
    private void iningetCode() {
        yanzheng_yishi.setVisibility(View.VISIBLE);
        yanzheng_yishi.setText("短信验证已发送至"+UserManager.getInstance().getLoginUser().getUser_mobile_referee()+""+"，请注意查收");
        CountDownTimerUtils countDownTimerUtils=new CountDownTimerUtils(time,get,60000,1000);
        countDownTimerUtils.start();
        JSONObject object = new JSONObject();
        try {
            object.put("email",UserManager.getInstance().getLoginUser().getUser_mobile_referee());
            object.put("pwd", UserInfo.getInstance().getUserPwd());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initVerify: "+jjjjj);
        Log.d(TAG, "initVerify: "+object.toString());
        Observable observable = RetrofitFactory.getInstance().getLoginCode(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<SetPasswordCodeBean>(this) {
            @Override
            protected void onHandleSuccess(SetPasswordCodeBean setPasswordCodeBean) {
                Log.d(TAG, "onHandleSuccess: "+setPasswordCodeBean.toString());
                token = setPasswordCodeBean.getToken();
            }


        });

    }

    /**
     *设置交易密码---提交
     *
     */
    private void setPayPwd(String setM) {
        JSONObject object = new JSONObject();
        try {
            object.put("email", UserManager.getInstance().getLoginUser().getUser_mobile_referee());
            object.put("pwd", UserInfo.getInstance().getUserPwd());
            object.put("pay_pwd", setM);
            object.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "setPayPwd: " + jjjjj);
        Log.d(TAG, "setPayPwd: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().setPaypwd(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<SetPayPwdBean>(this) {

            @Override
            protected void onHandleSuccess(SetPayPwdBean setPayPwdBean) {
                Intent intent  = new Intent(SetPayPsdActivity.this,ChangedCompleteActivity.class);
                intent.putExtra("tag","2");
                startActivity(intent);
                finish();
            }
        });




    }
}
