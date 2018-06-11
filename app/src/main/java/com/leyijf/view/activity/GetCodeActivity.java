package com.leyijf.view.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.GetCodeBean;
import com.leyijf.bean.SetPasswordCodeBean;
import com.leyijf.bean.UploadPhoneBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.bean.VerifyCodeBean;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;
import com.leyijf.util.CountDownTimerUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;

/**
 * 获取验证码
 * Created by wmq on 2018/4/24.
 */

public class GetCodeActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG ="GetCodeActivity" ;
    private ImageView back;
    private TextView phonenum;
    private EditText inputcode;
    private TextView time,get;
    private TextView nextButton;
    private Map<String, String> map;
    private String numPhone;
    private GetCodeBean getCodeBean = new GetCodeBean();
    private Intent intent;
    String tag,token,mobile,inputCodeNum;

    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        back = (ImageView) findViewById(R.id.zhuce_back);
        phonenum = (TextView) findViewById(R.id.phonenum);
        inputcode = (EditText) findViewById(R.id.inputcode);
        time = (TextView) findViewById(R.id.time);
        get= (TextView) findViewById(R.id.get);
        nextButton = (TextView) findViewById(R.id.next_button);
        numPhone = UserManager.getInstance().getLoginUser().getUser_mobile_referee().toString();
        Log.d(TAG, "initId: "+numPhone);
        phonenum.setVisibility(View.GONE);
        get.setOnClickListener(this);
        back.setOnClickListener(this);
        time.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        intent = getIntent();
        tag = intent.getStringExtra("tag");
        if(tag.equals("3")){
            CountDownTimerUtils countDownTimerUtils=new CountDownTimerUtils(time,get,60000,1000);
            countDownTimerUtils.start();
             token = intent.getStringExtra("token");
             mobile = intent.getStringExtra("mobile");
             phonenum.setVisibility(View.VISIBLE);
            phonenum.setText("短信验证已发送至"+mobile+""+"，请注意查收");

        }

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_getcode;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuce_back:
                finish();
                break;
            case R.id.get://获取验证码
                if(tag.equals("1")){
//                    get.setVisibility(View.GONE);
                    phonenum.setVisibility(View.VISIBLE);
                    phonenum.setText("短信验证已发送至"+UserManager.getInstance().getLoginUser().getUser_mobile_referee()+""+"，请注意查收");
                    iningetCode();
                }
                else {
                }
                break;
            case R.id.next_button://下一步
                if(tag.equals("1")){//进入设置登录密码页面
//                    Intent intent = new Intent(this,)

                    initVerifyCode();
                }else {//跳转完成更换手机号页面
                    initChangePnone();
                }
                break;
            default:
                break;
        }

    }

    /**
     * 设置登录密码---验证验证码
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
                        //进入设置密码页面
                    Intent intent = new Intent(GetCodeActivity.this,SetLoginPasswordActivity.class);
                    intent.putExtra("token",verifyCodeBean.getToken());
                    startActivity(intent);
                }
            });
        }else {
            Toast.makeText(this,"请输入验证码",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 设置登录密码---发送验证码
     */
    private void iningetCode() {
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
     * 更换手机号接口
     */
    private void initChangePnone() {
        String mobileCode = inputcode.getText().toString();
        if(token!=null&&mobile!=null&mobileCode!=null){
            JSONObject object = new JSONObject();
            try {
                object.put("email",UserManager.getInstance().getLoginUser().getUser_mobile_referee());
                object.put("pwd", UserInfo.getInstance().getUserPwd());
                object.put("mobile",mobile);
                object.put("token",token);
                object.put("mobile_code",mobileCode);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String requestData = Aes128.encrypt((object.toString()));
            String jjjjj = Aes128.decrypt(requestData);
            Log.d(TAG, "initVerify: "+jjjjj);
            Log.d(TAG, "initVerify: "+object.toString());
            Observable observable = RetrofitFactory.getInstance().uploadPhoneNum(requestData);
            observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<UploadPhoneBean>(this) {

                @Override
                protected void onHandleSuccess(UploadPhoneBean uploadPhoneBean) {
                    Log.d(TAG, "onHandleSuccess: "+uploadPhoneBean.toString());
                    //需要对用户信息进行更新
                    Intent intent  = new Intent(GetCodeActivity.this,ChangedCompleteActivity.class);
                    intent.putExtra("tag","3");
                    startActivity(intent);
                    finish();
                }
            });

        }

    }


}
