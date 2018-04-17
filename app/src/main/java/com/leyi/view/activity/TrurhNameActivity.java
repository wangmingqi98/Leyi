package com.leyi.view.activity;

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

import com.leyi.R;
import com.leyi.base.BaseActivity;
import com.leyi.bean.UserInfo;
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
 * 实名认证
 */
public class TrurhNameActivity extends BaseActivity implements View.OnClickListener {

    private EditText set_mima;
    private EditText ok_mima;
    private TextView next_step3;
    private LinearLayout zhuce_step3;
    private ImageView zhuce_back;
    private LinearLayout shehzi_ok;
    private TextView wancheng;
    private int has_paypassword;


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

    private void replay() {
//        act= uc_center_certificate
//        email: 用户名或者手机号
//        pwd: 密码
//        real_name:真实名称
//        id_no:身份证
        Map<String,String> map=new HashMap<>();
        map.put("act","uc_center_certificate");
        map.put("email", UserInfo.getInstance().getUserPhone());
        map.put("pwd", UserInfo.getInstance().getUserPwd());
        map.put("real_name",set_mima.getText().toString());
        map.put("id_no",ok_mima.getText().toString());


        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.e("---shimingrenzheng--",string+"");

                JSONObject jsonObject= null;
                try {
                    jsonObject = new JSONObject(string);
                    if(jsonObject.getInt("response_code")==1){
//                        {"response_code":1,"objects":{"show_err":"提交成功","user_info":{"user_id":105,"user_name":"u176MJER2312","user_img":"",
//                                "user_mobile":"176****2312","id_passed":1,"has_paypassword":0,"binded_card":[]}}}
                        UserInfo.getInstance().saveId_passed(1);
                        has_paypassword = jsonObject.getJSONObject("objects").getJSONObject("user_info").getJSONArray("binded_card").length();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                shehzi_ok.setVisibility(View.VISIBLE);
                                zhuce_step3.setVisibility(View.GONE);
                            }
                        });


                    }else {
                        final String show_err = jsonObject.getString("show_err");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(TrurhNameActivity.this, show_err+"", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });





    }


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
