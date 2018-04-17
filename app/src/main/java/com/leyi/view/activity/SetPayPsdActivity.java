package com.leyi.view.activity;

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
import com.leyi.bean.PayMessagebean;
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
 * 设置支付密码
 */
public class SetPayPsdActivity extends BaseActivity implements View.OnClickListener {

    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
    private TextView yanzheng_yishi;
    private EditText yanzheng_edit;
    private TextView dao_time;
    private TextView huoqu;
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
    private TextView wancheng;
    private LinearLayout shehzi_ok;
    private String setM;
    private String okM;
    private Map<String, String> payMap;
    private String trim;


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

        payMap = new HashMap<>();
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);
        yanzheng_yishi = (TextView) findViewById(R.id.yanzheng_yishi);
        yanzheng_edit = (EditText) findViewById(R.id.yanzheng_edit);
        dao_time = (TextView) findViewById(R.id.dao_time);
        huoqu = (TextView) findViewById(R.id.huoqu);
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
        wancheng = (TextView) findViewById(R.id.wancheng);
        shehzi_ok = (LinearLayout) findViewById(R.id.shehzi_ok);
        next_step2.setOnClickListener(this);
        next_step3.setOnClickListener(this);
        wancheng.setOnClickListener(this);
        queren_biyan.setOnClickListener(this);
        queren_zhengyan.setOnClickListener(this);
        shezhi_biyan.setOnClickListener(this);
        shezhi_zhengyan.setOnClickListener(this);
        huoqu.setOnClickListener(this);
        zhuce_back.setOnClickListener(this);

        getYanzhengMa();




    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.next_step3:
                setM = set_mima.getText().toString().trim();
                okM = ok_mima.getText().toString().trim();
                if(!TextUtils.isEmpty(setM)&&!TextUtils.isEmpty(okM)){


                    if(Maches.isPayMima(setM)){
                        payMap.put("act","save_pay_pwd");
                        payMap.put("mobile_code",trim);
                        payMap.put("pay_pwd",setM);
                        payMap.put("pay_pwd_confirm",okM);
                        payMap.put("email",UserInfo.getInstance().getUserPhone());
                        payMap.put("pwd",UserInfo.getInstance().getUserPwd());
                        setPayPwd(payMap);






                    }else {
                        Toast.makeText(this, "密码格式不合法", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }else {
                    Toast.makeText(this, "请输入", Toast.LENGTH_SHORT).show();
                    return;

                }

                break;

            case R.id.zhuce_back:
                 finish();

                break;



            case R.id.wancheng:
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
//                act=save_pay_pwd
//                mobile_code: 验证码
//                pay_pwd:支付密码
//                pay_pwd_confirm:确认支付密码
                if(!TextUtils.isEmpty(yanzheng_edit.getText().toString())){

                    trim = yanzheng_edit.getText().toString().trim();
                    zhuce_step2.setVisibility(View.GONE);
                    zhuce_step3.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                }




                break;

            case R.id.huoqu:
                  getYanzhengMa();



                break;

        }


    }

    private void setPayPwd(final Map<String, String> payMap) {

        OkHttpUtil.getInstance().doHttp(payMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

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
//                                Gson gson=new Gson();
//                                PayMessagebean payMessagebean = gson.fromJson(ss, PayMessagebean.class);
////                                getCodeBean = gson.fromJson(ss, GetCodeBean.class);
////                                UserInfo.getInstance().saveUserToken(getCodeBean.getObjects().getToken());
////                                zhuceStep1.setVisibility(View.GONE);
////                                zhuceStep2.setVisibility(View.VISIBLE);
//                                yanzheng_yishi.setText("短信验证已发送至"+UserInfo.getInstance().getUserPhone()+""+"，请注意查收");
                                UserInfo.getInstance().saveHas_paypassword(1);
                                UserInfo.getInstance().savePayPwd(setM);
                                zhuce_step3.setVisibility(View.GONE);
                                shehzi_ok.setVisibility(View.VISIBLE);



                            }else {

                                String e0rr = jsonObject.getString("show_err");
                                Toast.makeText(SetPayPsdActivity.this, e0rr+"", Toast.LENGTH_SHORT).show();
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


    }

    private void getYanzhengMa() {
        Map<String,String> map=new HashMap();
       map.put("act","send_reset_pwd_code");
       map.put("mobile", UserInfo.getInstance().getUserPhone());
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
                                PayMessagebean payMessagebean = gson.fromJson(ss, PayMessagebean.class);
//                                getCodeBean = gson.fromJson(ss, GetCodeBean.class);
//                                UserInfo.getInstance().saveUserToken(getCodeBean.getObjects().getToken());
//                                zhuceStep1.setVisibility(View.GONE);
//                                zhuceStep2.setVisibility(View.VISIBLE);
                                yanzheng_yishi.setText("短信验证已发送至"+UserInfo.getInstance().getUserPhone()+""+"，请注意查收");
                                next_step2.setClickable(true);


                            }else {

                                String e0rr = jsonObject.getString("show_err");
                                yanzheng_yishi.setText(e0rr+"");
                                Toast.makeText(SetPayPsdActivity.this, e0rr+"", Toast.LENGTH_SHORT).show();
                                next_step2.setClickable(false);
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
    }
}
