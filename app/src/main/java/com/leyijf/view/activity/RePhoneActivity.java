package com.leyijf.view.activity;

import android.content.Intent;
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
import com.leyijf.bean.GetCodeBean;
import com.leyijf.bean.GetCodeModel;
import com.leyijf.bean.UserInfo;
import com.leyijf.bean.VerifyReponseBean;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;

/**
 * 重置密码
 */
public class RePhoneActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG ="RePhoneActivity" ;
    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
    private EditText re_mima;
    private ImageView re_zhengyan;
    private ImageView re_biyan;
    private TextView next_step,sure,getCode;
    private LinearLayout re_step1;
    private TextView yanzheng_yishi;
    private EditText yanzheng_edit;
    private TextView yanzheng_time;
    private TextView yanzheng_ok;
    private LinearLayout re_step3;
    private EditText phone_edit;
    private TextView huoqu_yanzheng;
    private LinearLayout re_step2;
    private Map<String, String> map;
    private GetCodeBean getCodeBean = new GetCodeBean();

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
        return R.layout.activity_re_phone;
    }

    private void initView() {
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        zhuce_back.setOnClickListener(this);
        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);
        re_mima = (EditText) findViewById(R.id.re_mima);
        re_zhengyan = (ImageView) findViewById(R.id.re_zhengyan);
        re_zhengyan.setOnClickListener(this);
        re_biyan = (ImageView) findViewById(R.id.re_biyan);
        re_biyan.setOnClickListener(this);
        next_step = (TextView) findViewById(R.id.next_step);
        re_step1 = (LinearLayout) findViewById(R.id.re_step1);
        re_step1.setOnClickListener(this);
        yanzheng_yishi = (TextView) findViewById(R.id.yanzheng_yishi);
        yanzheng_edit = (EditText) findViewById(R.id.yanzheng_edit);
        yanzheng_time = (TextView) findViewById(R.id.yanzheng_time);
        re_step3 = (LinearLayout) findViewById(R.id.re_step3);
        phone_edit = (EditText) findViewById(R.id.phone_edit);
        re_step2 = (LinearLayout) findViewById(R.id.re_step2);
        next_step.setOnClickListener(this);
        sure = (TextView) findViewById(R.id.sure);
        getCode = (TextView) findViewById(R.id.getcode);
        sure.setOnClickListener(this);
        getCode.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhuce_back:
                finish();
                break;

            case R.id.next_step:
                initVerify();
                break;
            case R.id.sure:
                re_step2.setVisibility(View.GONE);
                re_step3.setVisibility(View.VISIBLE);
                break;
            case R.id.getcode://跳转获取验证码界面
                if(phone_edit.getText().toString()!=null){
                    String mobile = phone_edit.getText().toString();
                    getCode(mobile);
                }else {
                    Toast.makeText(this,"请输入您的新手机号",Toast.LENGTH_LONG).show();
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

        }
    }

    /**
     * 获取验证码接口
     */
    private void getCode(String mobile) {
        String userid = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        JSONObject object = new JSONObject();
        try {
            object.put("email",userid);
            object.put("pwd", UserInfo.getInstance().getUserPwd());
            object.put("mobile",mobile);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initVerify: "+jjjjj);
        Log.d(TAG, "initVerify: "+object.toString());

        Observable observable = RetrofitFactory.getInstance().getCode(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<GetCodeModel>(this) {
            @Override
            protected void onHandleSuccess(GetCodeModel getCodeModel) {
                Log.d(TAG, "onHandleSuccess: "+getCodeModel.toString());
                Intent intent  = new Intent(RePhoneActivity.this,GetCodeActivity.class);
                    intent.putExtra("tag","3");
                    intent.putExtra("token",getCodeModel.getToken());
                    intent.putExtra("mobile",getCodeModel.getMobile());
                    startActivity(intent);

            }

        });

    }

    /**
     * 身份验证接口
     */
    private void initVerify() {
        String password = re_mima.getText().toString().trim();
        String userid = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        JSONObject object = new JSONObject();
        try {
            object.put("email",userid);
            object.put("confirm_pwd",password);
            object.put("pwd", UserInfo.getInstance().getUserPwd());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initVerify: "+jjjjj);
        Log.d(TAG, "initVerify: "+object.toString());
        Observable observable = RetrofitFactory.getInstance().verify(requestData);
        observable.compose(compose(this.<VerifyReponseBean>bindToLifecycle())).subscribe(new BaseObserver<VerifyReponseBean>(this) {

            @Override
            protected void onHandleSuccess(VerifyReponseBean verifyReponseBean) {
                Toast.makeText(RePhoneActivity.this,verifyReponseBean.getShow_err(),Toast.LENGTH_LONG).show();
                re_step1.setVisibility(View.GONE);
                re_step2.setVisibility(View.VISIBLE);
            }
        });
    }
}
