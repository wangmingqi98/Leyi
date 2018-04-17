package com.leyi.view.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leyi.R;
import com.leyi.base.BaseActivity;
import com.leyi.bean.UserInfo;
import com.leyi.util.OkHttpUtil;
import com.leyi.weight.PasswordInputView;
import com.leyi.weight.PasswordKeyboardView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 确认订单
 */
public class OkOrderActivity extends BaseActivity {

    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
    private TextView hukuan_name;
    private TextView need_money2;
    private TextView jiezhi;
    private TextView loantype_format;
    private TextView xiane;
    private TextView textView3;
    private LinearLayout add_bankcard;
    private TextView next_stepshen;
    private Map map;
    private String borrow_amount;
    private String need_money;
    private String id;
    private PasswordKeyboardView keybordview;
    private PasswordInputView input_view;
    private StringBuffer stringBuffer;
    private String bid_money;
    private PopupWindow popupWindow;
    private TextView wancheng;
    private String loantype_formats;
    private String rate;
    private String repay_time;
    private String repay_time_type;

    private TextView now_chongzhi;
    private TextView canuseryue;
    private CheckBox checkBox;
    private TextView paymoney;
    private ImageView close;
    private PopupWindow popupWindows;
    private ImageView pay_close;

    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        initView();

        iniList();

    }

    private void iniList() {
        next_stepshen.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
//                      showRepop(R.layout.pay_popview);
                        showRepop(R.layout.querenpaypop);






            }
        });

    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_ok_order;
    }

    private void initView() {
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);
        hukuan_name = (TextView) findViewById(R.id.hukuan_name);
        need_money2 = (TextView) findViewById(R.id.need_money2);
        jiezhi = (TextView) findViewById(R.id.jiezhi);
        loantype_format = (TextView) findViewById(R.id.loantype_format);
        xiane = (TextView) findViewById(R.id.xiane);
        textView3 = (TextView) findViewById(R.id.textView3);
        add_bankcard = (LinearLayout) findViewById(R.id.add_bankcard);
        next_stepshen = (TextView) findViewById(R.id.next_stepshen);



        map = new HashMap<>();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        borrow_amount = extras.getString("borrow_amount");
        need_money = extras.getString("need_money");
        bid_money = extras.getString("bid_money");
        id = extras.getString("id");
        loantype_formats = extras.getString("loantype_format");
        rate = extras.getString("rate");
        repay_time = extras.getString("repay_time");
        repay_time_type = extras.getString("repay_time_type");
        map.put("act","deal_dobid");
        map.put("email", UserInfo.getInstance().getUserPhone());
        map.put("pwd",UserInfo.getInstance().getUserPwd());
        map.put("id", id);

        need_money2.setText(rate+"%");
        if(repay_time_type.equals("0")){
            jiezhi.setText(repay_time+"天");
        }else {
            jiezhi.setText(repay_time+"月");
        }

        loantype_format.setText(loantype_formats);
        xiane.setText(borrow_amount+"元");
        textView3.setText(bid_money+"元");




    }


    private void showRepop(int layout) {
        View inflate = LayoutInflater.from(OkOrderActivity.this).inflate(layout, null);
//        // Init Views


        popupWindows = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT,
                 ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindows.setBackgroundDrawable(new BitmapDrawable());
        popupWindows.setFocusable(true);// 取得焦点
        //点击外部消失
        popupWindows.setOutsideTouchable(true);
        //设置可以点击
        popupWindows.setTouchable(true);
        //进入退出的动画
        popupWindows.setAnimationStyle(R.style.Animation);
//             int xuNiHeight = getDaoHangHeight(this);
//             Log.e("xuNiHeight",""+xuNiHeight);
        popupWindows.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);

        popupWindows.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//             popupWindow.showAtLocation(inflate,Gravity.BOTTOM,0,getDaoHangHeight(this));
        popupWindows.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        if (lp.alpha == 0.3f) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug
        }
        getWindow().setAttributes(lp);
        popupWindows.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });

//        keybordview= inflate.findViewById(R.id.keybordview);
//        input_view=inflate.findViewById(R.id.input_view);
//        input_view.setFocusable(false);
//        input_view.setBorderWidth(1);
//        stringBuffer = new StringBuffer();
//        keybordview.setIOnKeyboardListener(new PasswordKeyboardView.IOnKeyboardListener() {
//            @Override
//            public void onInsertKeyEvent(String text) {
//
//
//                if(stringBuffer.length()<=6){
//                    stringBuffer.append(text);
//                    input_view.setText(stringBuffer.toString());
//                    Log.e("----keybord",stringBuffer.toString());
//
//                    if(stringBuffer.length()==6){
//                         popupWindow.dismiss();
//                        map.put("bid_paypassword",stringBuffer.toString());
//                        map.put("bid_money",bid_money);
//                        doB_id(map);
//
//
//
//
//
//                    }
//                }else {
//
//                }
//
//
//            }
//
//            @Override
//            public void onDeleteKeyEvent() {
//
//                stringBuffer.delete(stringBuffer.length()-1,stringBuffer.length());
//
//                input_view.setText(stringBuffer.toString());
//                Log.e("----keybord",stringBuffer.toString());
//            }
//        });

        now_chongzhi=inflate.findViewById(R.id.now_chongzhi);
        canuseryue=inflate.findViewById(R.id.canuseryue);
        checkBox=inflate.findViewById(R.id.checkBox);
        paymoney=inflate.findViewById(R.id.paymoney);
        close=inflate.findViewById(R.id.close);
        paymoney.setText("¥"+bid_money);

        canuseryue.setText("余额支付(可用余额"+UserInfo.getInstance().getUseful_money()+")");
        if(Float.parseFloat(UserInfo.getInstance().getUseful_money().substring(0,UserInfo.getInstance().getUseful_money().length()-1))<Float.parseFloat(paymoney.getText().toString().substring(1,paymoney.getText().toString().length()))){

            now_chongzhi.setClickable(true);
            checkBox.setClickable(false);
            Toast.makeText(this, "账户余额不足，请充值", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "请选择支付方式", Toast.LENGTH_SHORT).show();
            now_chongzhi.setClickable(true);
            checkBox.setClickable(true);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    map.put("bid_money",paymoney.getText().toString().substring(1,paymoney.getText().toString().length()));

                    popupWindows.dismiss();


                    showPayRepop(R.layout.pay_popview);

                }
            }
        });

        now_chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OkOrderActivity.this,ChongzhiActivity.class));
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindows.dismiss();
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
                                    Toast.makeText(OkOrderActivity.this, string, Toast.LENGTH_SHORT).show();
                                }
                            });


                    }else {

                        final String show_err = jsonObject.getString("show_err");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(OkOrderActivity.this, show_err+"", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });



    }



    private void showPayRepop(int layout) {
        View inflate = LayoutInflater.from(OkOrderActivity.this).inflate(layout, null);
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
                         popupWindow.dismiss();
                        map.put("bid_paypassword",stringBuffer.toString());
                        doB_id(map);





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
    }


}
