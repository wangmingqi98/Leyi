package com.leyi.view.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leyi.R;
import com.leyi.adapter.BankListAdapter;
import com.leyi.base.BaseActivity;
import com.leyi.bean.BankCardBean;
import com.leyi.bean.FeeBean;
import com.leyi.bean.UserInfo;
import com.leyi.util.OkHttpUtil;
import com.leyi.weight.PasswordInputView;
import com.leyi.weight.PasswordKeyboardView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 提现页面
 */
public class WithdrawDepositctivity extends BaseActivity implements View.OnClickListener{

    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
    private ImageView bank_img;
    private TextView bank_name;
    private TextView bank_behind;
    private LinearLayout bank_lin;
    private TextView canuse_money;
    private EditText tixian_edit;
    private TextView allmoney;
    private TextView shouxufei;
    private TextView shijidaozhang;
    private TextView next_stepshen;
    private TextView next_stepqian;
    private ListView bankcardlist;
    private List<BankCardBean> list;
    private List<FeeBean> feeBeanList;

    private PasswordKeyboardView keybordview;
    private PasswordInputView input_view;
    private StringBuffer stringBuffer;

    private PopupWindow popupWindow;
    private ImageView pay_close;
    private Map<String, String> okMap;
    private String paymima;
    private TextView forget_mima;


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
        return R.layout.activity_ti_xian;
    }

    private void initView() {
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);

        canuse_money = (TextView) findViewById(R.id.canuse_money);
        tixian_edit = (EditText) findViewById(R.id.tixian_edit);
        allmoney = (TextView) findViewById(R.id.allmoney);
        shouxufei = (TextView) findViewById(R.id.shouxufei);
        bankcardlist = (ListView) findViewById(R.id.bankcardlist);
        shijidaozhang = (TextView) findViewById(R.id.shijidaozhang);
        next_stepshen = (TextView) findViewById(R.id.next_stepshen);
        next_stepqian = (TextView) findViewById(R.id.next_stepqian);
        zhuce_back.setOnClickListener(this);
        next_stepshen.setOnClickListener(this);


        canuse_money.setText(UserInfo.getInstance().getUseful_money());
        allmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tixian_edit.setText(UserInfo.getInstance().getUserMoney());
                canuse_money.setText(UserInfo.getInstance().getUserMoney());


            }
        });



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zhuce_back:
                finish();
                break;

            case R.id.next_stepshen:


                showPayRepop(R.layout.pay_popview);


                break;

        }
    }

    private void okTixian() {
//        act=uc_save_carry
//        email: 用户名或邮箱
//        pwd: 密码
//        bank_id：银行卡号
//        paypassword：支付密码
//        real_amount：提取金额
//        fee：费率

        okMap = new HashMap();
        okMap.put("act","uc_save_carry");
        okMap.put("email",UserInfo.getInstance().getUserPhone());
        okMap.put("pwd",UserInfo.getInstance().getUserPwd());
        okMap.put("bank_id",list.get(0).getBankcard());
        okMap.put("paypassword",paymima);
        Log.e("---paymima",paymima);
        okMap.put("real_amount",tixian_edit.getText().toString().trim());
        okMap.put("fee_type",feeBeanList.get(0).getFee_type());
        okMap.put("fee",feeBeanList.get(0).getFee());

        OkHttpUtil.getInstance().doHttp(okMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                Log.e("---===",string+"");
                JSONObject jsonObject= null;
                try {
                    jsonObject = new JSONObject(string);
                    int response_code = jsonObject.getInt("response_code");
                    if(response_code==1){
                        final String string1 = jsonObject.getJSONObject("objects").getString("show_err");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(WithdrawDepositctivity.this, string1+"", Toast.LENGTH_SHORT).show();


                            }
                        });
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        });








    }


    @Override
    protected void onResume() {
        super.onResume();


     Map map = new HashMap<>();
        map.put("act","uc_bank");
        map.put("email", UserInfo.getInstance().getUserPhone());
        map.put("pwd",UserInfo.getInstance().getUserPwd());
        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                Log.e("---chongzhi--",string+"");

                try {
                    JSONObject jsonObject=new JSONObject(string);
                    int response_code = jsonObject.getInt("response_code");
                    if(response_code==1){
                        JSONArray jsonArray = jsonObject.getJSONObject("objects").getJSONArray("item");
                        JSONArray jsonArray_fee = jsonObject.getJSONObject("objects").getJSONArray("fee_config");




                        list = new ArrayList<BankCardBean>();
                        if(jsonArray.length()>0){
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                BankCardBean bankCardBean=new BankCardBean(jsonObject1.getString("id"),jsonObject1.getString("bankcard"),jsonObject1.getString("real_name")
                                        ,jsonObject1.getString("bank_name"),jsonObject1.getString("bank_id"),jsonObject1.getString("bankcode"),jsonObject1.getString("img"));
                                list.add(bankCardBean);
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    BankListAdapter bankListAdapter=new BankListAdapter(list,WithdrawDepositctivity.this);
                                    bankcardlist.setAdapter(bankListAdapter);
                                }
                            });

                        }


                        feeBeanList = new ArrayList<FeeBean>();
                        if(jsonArray_fee.length()>0){
                            for (int i = 0; i < jsonArray_fee.length(); i++) {
                                JSONObject jsonObject2 = jsonArray_fee.getJSONObject(i);
                                FeeBean feeBean=new FeeBean(jsonObject2.getString("id"),jsonObject2.getString("name"),jsonObject2.getString("min_price")
                                        ,jsonObject2.getString("max_price"),jsonObject2.getString("fee"),jsonObject2.getString("fee_type"),jsonObject2.getString("vip_id"),jsonObject2.getString("fee_format"));
                                feeBeanList.add(feeBean);
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    initListener();



                                }
                            });

                        }

                    }else {

                    }




                    } catch (JSONException e1) {
                    e1.printStackTrace();


                    }









    }
});
    }

    private void initListener() {

        tixian_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                if(!TextUtils.isEmpty(s1)){
                    float v = Float.parseFloat(s1);
                    float userful = Float.parseFloat(UserInfo.getInstance().getUseful_money().substring(0,UserInfo.getInstance().getUseful_money().length()-1));

                    if(TextUtils.isDigitsOnly(s1)){
                        if(userful>Float.parseFloat(feeBeanList.get(0).getMin_price())){
                            if(v>Float.parseFloat(feeBeanList.get(0).getMin_price())){
                                if(v<Float.parseFloat(feeBeanList.get(0).getMax_price())){
                                    next_stepqian.setVisibility(View.GONE);
                                    next_stepshen.setVisibility(View.VISIBLE);

                                    if(feeBeanList.get(0).getFee_type().equals("0")){

//                        float rate = Float.parseFloat(toubiaoDetilsBean.getRate());
//                        float rates = rate / 100;
//                        float i = Float.parseFloat(s1) * rates;
//                        float i1 = i / 365 * Float.parseFloat(toubiaoDetilsBean.getRepay_time());
//
//                        BigDecimal b  =   new BigDecimal(i1);
//                        float v = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                                        shouxufei.setText(feeBeanList.get(0).getFee_format());

                                        float v1 = Float.parseFloat(feeBeanList.get(0).getFee());
                                        shijidaozhang.setText(v-v1+"元");

                                    }else if(feeBeanList.get(0).getFee_type().equals("1")) {

                                        String substring = feeBeanList.get(0).getFee().substring(0, feeBeanList.get(0).getFee().length() - 1);
                                        float v2 = Float.parseFloat(substring);
                                        float v3 = v2 / 100;
                                        float v4 = v * v3;

                                        shouxufei.setText(v4+"元");
                                        shijidaozhang.setText(v-v4+"元");
                                        return;

                                    }





                                }else {
                                    next_stepqian.setVisibility(View.VISIBLE);
                                    next_stepshen.setVisibility(View.GONE);
                                    Toast.makeText(WithdrawDepositctivity.this, "提现最高限额为"+feeBeanList.get(0).getMax_price()+"元", Toast.LENGTH_SHORT).show();
                                    return;
                                }


                            }else {
                                next_stepqian.setVisibility(View.VISIBLE);
                                next_stepshen.setVisibility(View.GONE);
                                Toast.makeText(WithdrawDepositctivity.this, "提现最低限额为"+feeBeanList.get(0).getMin_price()+"元", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }else {
                            next_stepqian.setVisibility(View.VISIBLE);
                            next_stepshen.setVisibility(View.GONE);
                            Toast.makeText(WithdrawDepositctivity.this, "余额不足", Toast.LENGTH_SHORT).show();
                            return;
                        }





                    }else {
                        next_stepqian.setVisibility(View.VISIBLE);
                        next_stepshen.setVisibility(View.GONE);
                        Toast.makeText(WithdrawDepositctivity.this, "请正确输入金额", Toast.LENGTH_SHORT).show();
                        return;
                    }






                }else {

                    next_stepqian.setVisibility(View.VISIBLE);
                    next_stepshen.setVisibility(View.GONE);
                    return;

                }


            }
        });


    }



    private void showPayRepop(int layout) {
        View inflate = LayoutInflater.from(WithdrawDepositctivity.this).inflate(layout, null);
//        // Init Views


        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT,
                 ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);// 取得焦点
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        //进入退出的动画
        popupWindow.setAnimationStyle(R.style.Animation);
//             int xuNiHeight = getDaoHangHeight(this);
//             Log.e("xuNiHeight",""+xuNiHeight);
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);

        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//             popupWindow.showAtLocation(inflate,Gravity.BOTTOM,0,getDaoHangHeight(this));
        popupWindow.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        if (lp.alpha == 0.3f) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug
        }
        getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pay_close = inflate.findViewById(R.id.close);
        forget_mima = inflate.findViewById(R.id.wnagji_mima);
        keybordview= inflate.findViewById(R.id.keybordview);
        input_view=inflate.findViewById(R.id.input_view);
        input_view.setFocusable(false);
        input_view.setBorderWidth(1);
        stringBuffer = new StringBuffer();
        keybordview.setIOnKeyboardListener(new PasswordKeyboardView.IOnKeyboardListener() {
            @Override
            public void onInsertKeyEvent(String text) {


                if(stringBuffer.length()<=6){
                    stringBuffer.append(text);
                    input_view.setText(stringBuffer.toString());
                    Log.e("----keybord",stringBuffer.toString());

                    if(stringBuffer.length()==6){
                        paymima = stringBuffer.toString();
                        okTixian();
                        popupWindow.dismiss();







                    }
                }else {

                }


            }

            @Override
            public void onDeleteKeyEvent() {

                if(stringBuffer.length()>0){
                    stringBuffer.delete(stringBuffer.length()-1,stringBuffer.length());

                    input_view.setText(stringBuffer.toString());
                    Log.e("----keybord",stringBuffer.toString());
                }else {
                    popupWindow.dismiss();
                }


            }
        });


        pay_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


        forget_mima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                startActivity(new Intent(WithdrawDepositctivity.this,SetPayPsdActivity.class));


            }
        });




    }
    private void doB_id(Map map) {
        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String payResult=response.body().string();
                Log.e("---d_bod--",payResult);
                JSONObject jsonObject= null;
                try {
                    jsonObject = new JSONObject(payResult);
                    if(jsonObject.getInt("response_code")==1){
                        final String string = jsonObject.getJSONObject("objects").getString("show_err");
//                        {"response_code":1,"objects":{"session_id":"n97ulga4c831uhs497hb4bfd87","user_login_status":1,"status":1,"show_err":"投标成功！","id":41}}
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(WithdrawDepositctivity.this, string, Toast.LENGTH_SHORT).show();
                            }
                        });


                    }else {

                        final String show_err = jsonObject.getString("show_err");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(WithdrawDepositctivity.this, show_err+"", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });



    }
}

