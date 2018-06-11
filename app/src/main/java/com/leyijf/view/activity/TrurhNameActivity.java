package com.leyijf.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.GetTrurhNameBean;
import com.leyijf.bean.TrurhNameBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;

/**
 * 实名认证
 */
public class TrurhNameActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "TrurhNameActivity";
    private EditText set_mima;
    private EditText ok_mima;
    private TextView next_step3;
    private LinearLayout zhuce_step3;
    private ImageView zhuce_back;
    private LinearLayout shehzi_ok;
    private TextView wancheng;
    private int has_paypassword;
    private String tag;


    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        initView();
        Intent intent = getIntent();
        tag = intent.getStringExtra("tag");
        if(tag.equals("1")){

            initGetTrurhName();
        }
    }



    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_trurh_name;
    }

    private void initView() {
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        set_mima = (EditText) findViewById(R.id.set_mima);
        ok_mima = (EditText) findViewById(R.id.ok_mima);
        next_step3 = (TextView) findViewById(R.id.next_step3);
        zhuce_step3 = (LinearLayout) findViewById(R.id.zhuce_step3);
        shehzi_ok = (LinearLayout) findViewById(R.id.shehzi_ok);
        wancheng = (TextView) findViewById(R.id.wancheng);
        zhuce_back.setOnClickListener(this);
        next_step3.setOnClickListener(this);
        wancheng.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuce_back:
                finish();

                break;
            case R.id.next_step3:
                if(TextUtils.isEmpty(set_mima.getText().toString())||TextUtils.isEmpty(ok_mima.getText().toString())){
                    Toast.makeText(this, "录入信息不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    replay();
                }




                break;

            case R.id.wancheng:
                if(has_paypassword==0){
                 showNoCard();
                }else {
                  finish();
                }

                break;

        }
    }

    /**
     *获取实名认证信息
     */
    private void initGetTrurhName() {
        String userId = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String password = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email", UserManager.getInstance().getLoginUser().getUser_mobile_referee());
            object.put("pwd", UserInfo.getInstance().getUserPwd());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initGetTrurhName: " + jjjjj);
        Log.d(TAG, "initGetTrurhName: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().getTrurhName(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<GetTrurhNameBean>(this) {

            @Override
            protected void onHandleSuccess(GetTrurhNameBean getTrurhNameBean) {
                set_mima.setText(getTrurhNameBean.getInfo().getReal_name());
                ok_mima.setText(getTrurhNameBean.getInfo().getIdno());
            }
        });
    }


    /**
     *修改实名认证
     */
    private void replay() {
        String userId = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String password = UserInfo.getInstance().getUserPwd();
        String realName = set_mima.getText().toString();
        String idNo = ok_mima.getText().toString();
        JSONObject object = new JSONObject();
        try {
            object.put("email", UserManager.getInstance().getLoginUser().getUser_mobile_referee());
            object.put("pwd", UserInfo.getInstance().getUserPwd());
            object.put("real_name", realName);
            object.put("id_no", idNo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "replay: " + jjjjj);
        Log.d(TAG, "replay: " + object.toString());
        if(UserManager.getInstance().isLogin()){
            Observable observable = RetrofitFactory.getInstance().trurhName(requestData);
            observable.compose(compose(this.<TrurhNameBean>bindToLifecycle())).subscribe(new BaseObserver<TrurhNameBean>(this) {
                @Override
                protected void onHandleSuccess(TrurhNameBean trurhNameBean) {
                    Log.d(TAG, "onHandleSuccess: "+trurhNameBean.toString());

                    UserManager.getInstance().getLoginUser().setId_passed( trurhNameBean.getUser_info().getId_passed());
                    Toast.makeText(TrurhNameActivity.this,"实名认证成功！",Toast.LENGTH_LONG).show();
                    finish();
                }


            });
        }


    }


    @SuppressLint("WrongConstant")
    private void showNoCard() {

        View  peicepop = LayoutInflater.from(TrurhNameActivity.this).inflate(R.layout.nobindcard,null);
        ImageView   popup_close2 = peicepop.findViewById(R.id.jisuan_close);
        TextView go = peicepop.findViewById(R.id.go_jiaoyipsd);
        final PopupWindow popupWindows = new PopupWindow(peicepop, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindows.setBackgroundDrawable(new BitmapDrawable());
        popupWindows.setFocusable(true);// 取得焦点
        //点击外部消失
        popupWindows.setOutsideTouchable(true);
        //设置可以点击
        popupWindows.setTouchable(true);
        popupWindows.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);

        popupWindows.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindows.showAtLocation(TrurhNameActivity.this.getWindow().getDecorView(), Gravity.CENTER,0,0);
        // 产生背景变暗效果
        WindowManager.LayoutParams ayoutParams =TrurhNameActivity. this.getWindow()
                .getAttributes();
        ayoutParams.alpha = 0.4f;
        TrurhNameActivity. this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        TrurhNameActivity.this.getWindow().setAttributes(ayoutParams);

        popupWindows.update();
        popupWindows.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = TrurhNameActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                TrurhNameActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                TrurhNameActivity.this.getWindow().setAttributes(lp);
            }
        });


        popup_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindows.dismiss();
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrurhNameActivity.this,BindBankCardActivity.class));
                popupWindows.dismiss();
                finish();
            }
        });



    }

}
