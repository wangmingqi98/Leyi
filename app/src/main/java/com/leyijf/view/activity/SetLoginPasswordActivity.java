package com.leyijf.view.activity;

import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.ComitNewPwdBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;
import com.leyijf.util.Maches;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;

/**
 * 设置登录密码页面
 * Created by wmq on 2018/4/27.
 */

public class SetLoginPasswordActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG ="SetLoginPasswordActivity" ;
    private ImageView back;
    private EditText inputLoginpwd,surepwd;
    private ImageView open,close,sureOpen,sureClose;
    private TextView nextStep;
    private String loginPwd,surePassword,token;
    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        back = (ImageView) findViewById(R.id.zhuce_back);
        inputLoginpwd = (EditText) findViewById(R.id.login_pwd);
        surepwd = (EditText) findViewById(R.id.sure_pwd);
        open = (ImageView) findViewById(R.id.login_zhengyan);
        close = (ImageView) findViewById(R.id.login_biyan);
        sureOpen = (ImageView) findViewById(R.id.sure_zhengyan);
        sureClose = (ImageView) findViewById(R.id.sure_biyan);
        nextStep = (TextView) findViewById(R.id.next_step);
        back.setOnClickListener(this);
        open.setOnClickListener(this);
        close.setOnClickListener(this);
        sureOpen.setOnClickListener(this);
        sureClose.setOnClickListener(this);
        nextStep.setOnClickListener(this);

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setloginpwd;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuce_back:
                finish();
                break;
            case R.id.login_zhengyan:
                inputLoginpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                close.setVisibility(View.GONE);
                open.setVisibility(View.VISIBLE);
                break;
            case R.id.login_biyan:
                inputLoginpwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                close.setVisibility(View.VISIBLE);
                open.setVisibility(View.GONE);
                break;
            case R.id.sure_zhengyan:
                surepwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                sureClose.setVisibility(View.GONE);
                sureOpen.setVisibility(View.VISIBLE);
                break;
            case R.id.sure_biyan:
                surepwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                sureOpen.setVisibility(View.GONE);
                sureClose.setVisibility(View.VISIBLE);
                break;
            case R.id.next_step:
                initChangeComit();
                break;
            default:
                    break;
        }

    }

    /**
     * 设置登录密码---提交
     */
    @SuppressLint("LongLogTag")
    private void initChangeComit() {
        if (inputLoginpwd.getText().toString() != null) {
            loginPwd = inputLoginpwd.getText().toString();
            if (surepwd.getText().toString() != null) {
                surePassword = surepwd.getText().toString();
                if (Maches.isPayMima(loginPwd) && Maches.isPayMima(surePassword)) {
                    if (loginPwd.equals(surePassword)) {
                        JSONObject object = new JSONObject();
                        try {
                            object.put("email", UserManager.getInstance().getLoginUser().getUser_mobile_referee());
                            object.put("pwd", UserInfo.getInstance().getUserPwd());
                            object.put("login_pwd", loginPwd);
                            object.put("token", token);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String requestData = Aes128.encrypt((object.toString()));
                        String jjjjj = Aes128.decrypt(requestData);
                        Log.d(TAG, "initChangeComit: " + jjjjj);
                        Log.d(TAG, "initChangeComit: " + object.toString());
                        Observable observable = RetrofitFactory.getInstance().changeLoginpwd(requestData);
                        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<ComitNewPwdBean>(this) {

                            @Override
                            protected void onHandleSuccess(ComitNewPwdBean comitNewPwdBean) {
                                //进入完成页面，并且对用户信息进行更新
                                Log.d(TAG, "onHandleSuccess: " + comitNewPwdBean.toString());
                                Intent intent = new Intent(SetLoginPasswordActivity.this, ChangedCompleteActivity.class);
                                intent.putExtra("tag", "3");
                                startActivity(intent);
                                finish();
                            }
                        });
                    } else {

                        Toast.makeText(this, "两次密码输入不一致，请重新输入", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "请输入合法的密码", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "请确认登录密码", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "请输入登录密码", Toast.LENGTH_LONG).show();

        }
    }
}
