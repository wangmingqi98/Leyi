package com.leyijf.view.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.LoginOutBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;
import com.leyijf.util.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;

/**
 * 修改完成页面
 * Created by wmq on 2018/4/24.
 */

public class ChangedCompleteActivity extends BaseActivity implements View.OnClickListener
{
    private static final String TAG ="ChangedCompleteActivity" ;
    private TextView title;
    private TextView hintComplete;
    private TextView complete;
    private Intent intent;
    private ImageView zhuce_back;
    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        title = (TextView) findViewById(R.id.title);
        hintComplete = (TextView) findViewById(R.id.hint_complete);
        complete = (TextView) findViewById(R.id.complete);
        complete.setOnClickListener(this);
        zhuce_back.setOnClickListener(this);
        intent = getIntent();
        String tag = intent.getStringExtra("tag");
        if(tag.equals("1")){
            title.setText("修改密码");
            complete.setText("登录密码修改成功");
            hintComplete.setText("登录密码修改成功");
        }else if(tag.equals("2")){
            title.setText("修改密码");
            complete.setText("交易密码修改成功");
            hintComplete.setText("交易密码修改成功");
        }else {
            title.setText("更换手机号");
            complete.setText("手机号更换成功");
            hintComplete.setText("手机号更换成功");

        }

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_changed;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.complete://进行退出登录的操作，并且跳向重新登录界面
                logOut();
                break;
            case R.id.zhuce_back:
                finish();
                break;
            default:
                break;
        }
    }
    /**
     * 退出登录
     */
    private void logOut() {
        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String pwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email",email);
            object.put("pwd", pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "logOut: "+jjjjj);
        Log.d(TAG, "logOut: "+object.toString());
        Observable observable = RetrofitFactory.getInstance().loginOut(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<LoginOutBean>(this) {

            @Override
            protected void onHandleSuccess(LoginOutBean loginOutBean) {
                Toast.makeText(ChangedCompleteActivity.this,loginOutBean.getShow_err(),Toast.LENGTH_LONG).show();
                UserManager.getInstance().logout();
                UserInfo.getInstance().ExeitUser(ChangedCompleteActivity.this);
                AppUtils.setStates(false);
                startActivity(new Intent(ChangedCompleteActivity.this,LoginActivity.class));
                finish();
            }
        });

    }
}
