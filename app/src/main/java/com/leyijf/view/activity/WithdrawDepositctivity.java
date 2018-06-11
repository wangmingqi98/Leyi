package com.leyijf.view.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
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
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.CurrentMoneyBean;
import com.leyijf.bean.GetUseMoneyBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;
import com.leyijf.view.MainTabActivity;
import com.leyijf.weight.PasswordInputView;
import com.leyijf.weight.PasswordKeyboardView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * 提现页面
 */
public class WithdrawDepositctivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "WithdrawDepositctivity";
    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
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
    private String binkID,poundage,real_amount,paypassword;
    private PasswordKeyboardView keybordview;
    private PasswordInputView input_view;
    private StringBuffer stringBuffer;

    private PopupWindow inputPwdPopupWindow;
    private ImageView pay_close;
    private String use_money = "0.00";
    private TextView forget_mima;
    private ImageView bank_img;
    private String textmoney;
    private double money,useMoney;
    private  List<GetUseMoneyBean.FeeConfigBean> fee_config = new ArrayList<>();

    @Override
    protected void initData() {
//        initListener();

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
        bank_img = (ImageView) findViewById(R.id.bank_img);
        canuse_money = (TextView) findViewById(R.id.canuse_money);
        tixian_edit = (EditText) findViewById(R.id.tixian_edit);
        allmoney = (TextView) findViewById(R.id.allmoney);
        shouxufei = (TextView) findViewById(R.id.shouxufei);
        shijidaozhang = (TextView) findViewById(R.id.shijidaozhang);
        next_stepshen = (TextView) findViewById(R.id.next_stepshen);
        next_stepqian = (TextView) findViewById(R.id.next_stepqian);
        bank_name = (TextView) findViewById(R.id.bank_name);
        bank_behind = (TextView) findViewById(R.id.bank_behind);
        tixian_edit.setFocusable(true);
        tixian_edit.setFocusableInTouchMode(true);
        zhuce_back.setOnClickListener(this);
        next_stepshen.setOnClickListener(this);
        Glide.with(this).load(UserManager.getInstance().getLoginType().getBank_card_icon()).into(bank_img);
        bank_behind.setText("尾号："+UserManager.getInstance().getLoginType().getBank_card_num());
        binkID = UserManager.getInstance().getLoginType().getBank_id();
        bank_name.setText(UserManager.getInstance().getLoginType().getBank_name());
        getMoney();
        allmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tixian_edit.setText(use_money);
                canuse_money.setText("0.00");
                textmoney = canuse_money.getText().toString().trim();
                Log.d(TAG, "******: "+textmoney);
                money = Double.parseDouble(textmoney);
                Log.d(TAG, "initView: "+money);
                DecimalFormat df = new DecimalFormat("0.00");
                useMoney = Double.parseDouble(df.format(money));


            }
        });


        initListener();


    }

    /**
     * 获取可用余额
     *
     */
    private void getMoney() {
        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String pwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email", email);
            object.put("pwd", pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "getMoney: " + jjjjj);
        Log.d(TAG, "getMoney: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().getUseMoney(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<GetUseMoneyBean>(this) {
            @Override
            protected void onHandleSuccess(GetUseMoneyBean getUseMoneyBean) {
                use_money = getUseMoneyBean.getUseful_money();
                canuse_money.setText(getUseMoneyBean.getUseful_money());
                textmoney = canuse_money.getText().toString().trim();
                Log.d(TAG, "******: "+textmoney);
                money = Double.parseDouble(textmoney);
                Log.d(TAG, "initView: "+money);
                DecimalFormat df = new DecimalFormat("0.00");
                useMoney = Double.parseDouble(df.format(money));
//                initListener();


                if(getUseMoneyBean.getFee_config().size()>0){
                    fee_config.addAll(getUseMoneyBean.getFee_config());


                }
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
//                showPayRepop(R.layout.pay_popview);
                    showQuickRefund();

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




    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    private void initListener() {

        tixian_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double shouxu  = 0.00;
                if(s.length()>0){
                    next_stepqian.setVisibility(View.GONE);
                    next_stepshen.setVisibility(View.VISIBLE);
                }else {
                    next_stepqian.setVisibility(View.VISIBLE);
                    next_stepshen.setVisibility(View.GONE);
                }
                if(useMoney>0){
                    if(s.length()==0){
                        canuse_money.setText(use_money);
                    }else {
                        double moneys = useMoney - (Double.valueOf(s.toString()));
//                   double moneys = useMoney-(Double.valueOf(s.toString()));
                        Log.d(TAG, "afterTextChanged: " + moneys);
                        DecimalFormat df = new DecimalFormat("0.00");
                        String useMoney = df.format(moneys);
                        if (moneys > 0) {
                            tixian_edit.setFocusable(true);
                            tixian_edit.setFocusableInTouchMode(true);
                            canuse_money.setText(useMoney + "");
                        } else {
                            tixian_edit.setFocusable(false);
                            tixian_edit.setFocusableInTouchMode(false);
                            tixian_edit.setText("");
                            shouxufei.setText("0.00 元");
                            Toast.makeText(WithdrawDepositctivity.this, "可用余额不足,请充值!", Toast.LENGTH_SHORT).show();
                        }
                        if(s.length()>0){

                            for (int i = 0;i<fee_config.size();i++){
                                if((Double.valueOf(s.toString()))>fee_config.get(i).getMin_price()&&(Double.valueOf(s.toString()))<fee_config.get(i).getMax_price()){
                                    if(fee_config.get(i).getFee_type().equals("1")){//百分比
                                        shouxufei.setText(((Double.valueOf(s.toString()))*(fee_config.get(i).getFee()/100))+"元");
                                        shouxu = ((Double.valueOf(s.toString()))*(fee_config.get(i).getFee()/100));
                                        poundage = ((Double.valueOf(s.toString()))*(fee_config.get(i).getFee()/100))+"";
                                    }else {
                                        shouxufei.setText(fee_config.get(i).getFee()+"元");
                                        shouxu = fee_config.get(i).getFee();
                                        poundage = fee_config.get(i).getFee()+"";
                                    }

                                }else { //取最大的
                                    if(fee_config.get(fee_config.size()-1).getFee_type().equals("1")){
                                        shouxufei.setText(((Double.valueOf(s.toString()))*(fee_config.get(fee_config.size()-1).getFee()/100))+"元");
                                        shouxu = ((Double.valueOf(s.toString()))*(fee_config.get(fee_config.size()-1).getFee()/100));
                                        poundage = ((Double.valueOf(s.toString()))*(fee_config.get(fee_config.size()-1).getFee()/100))+"";
                                    }else {
                                        shouxu = fee_config.get(fee_config.size()-1).getFee();
                                        poundage = fee_config.get(fee_config.size()-1).getFee()+"";
                                        shouxufei.setText(fee_config.get(fee_config.size()-1).getFee()+"元");
                                    }
                                }
                                if(((Double.valueOf(s.toString()))-shouxu)>0){

                                    shijidaozhang.setText(((Double.valueOf(s.toString()))-shouxu)+"元");
                                    real_amount = ((Double.valueOf(s.toString()))-shouxu)+"";
                                }else {
                                    shijidaozhang.setText("0.00 元");
                                }

                            }
                        }else {
                            shijidaozhang.setText("0.00 元");
                        }
                    }
                }else {
                    canuse_money.setText(use_money);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                double shouxu  = 0.00;
                if(s.length()>0){
                    next_stepqian.setVisibility(View.GONE);
                    next_stepshen.setVisibility(View.VISIBLE);
                }else {
                    next_stepqian.setVisibility(View.VISIBLE);
                    next_stepshen.setVisibility(View.GONE);
                }
                if(useMoney>0){
                    if(s.length()==0){
                        canuse_money.setText(use_money);
                    }else {
                        double moneys = useMoney - (Double.valueOf(s.toString()));
//                   double moneys = useMoney-(Double.valueOf(s.toString()));
                        Log.d(TAG, "afterTextChanged: " + moneys);
                        DecimalFormat df = new DecimalFormat("0.00");
                        String useMoney = df.format(moneys);
                        if (moneys > 0) {
                            tixian_edit.setFocusable(true);
                            tixian_edit.setFocusableInTouchMode(true);
                            canuse_money.setText(useMoney + "");
                        } else {
                            tixian_edit.setFocusable(false);
                            tixian_edit.setFocusableInTouchMode(false);
                            tixian_edit.setText("");
                            shouxufei.setText("0.00 元");
                            Toast.makeText(WithdrawDepositctivity.this, "可用余额不足,请充值!", Toast.LENGTH_SHORT).show();
                        }
                        if(s.length()>0){

                            for (int i = 0;i<fee_config.size();i++){
                                if((Double.valueOf(s.toString()))>fee_config.get(i).getMin_price()&&(Double.valueOf(s.toString()))<fee_config.get(i).getMax_price()){
                                    if(fee_config.get(i).getFee_type().equals("1")){//百分比
                                        shouxufei.setText(((Double.valueOf(s.toString()))*(fee_config.get(i).getFee()/100))+"元");
                                        shouxu = ((Double.valueOf(s.toString()))*(fee_config.get(i).getFee()/100));
                                        poundage = ((Double.valueOf(s.toString()))*(fee_config.get(i).getFee()/100))+"";
                                    }else {
                                        shouxufei.setText(fee_config.get(i).getFee()+"元");
                                        shouxu = fee_config.get(i).getFee();
                                        poundage = fee_config.get(i).getFee()+"";
                                    }

                                }else { //取最大的
                                    if(fee_config.get(fee_config.size()-1).getFee_type().equals("1")){
                                        shouxufei.setText(((Double.valueOf(s.toString()))*(fee_config.get(fee_config.size()-1).getFee()/100))+"元");
                                        shouxu = ((Double.valueOf(s.toString()))*(fee_config.get(fee_config.size()-1).getFee()/100));
                                        poundage = ((Double.valueOf(s.toString()))*(fee_config.get(fee_config.size()-1).getFee()/100))+"";
                                    }else {
                                        shouxu = fee_config.get(fee_config.size()-1).getFee();
                                        poundage = fee_config.get(fee_config.size()-1).getFee()+"";
                                        shouxufei.setText(fee_config.get(fee_config.size()-1).getFee()+"元");
                                    }
                                }
                                if(((Double.valueOf(s.toString()))-shouxu)>0){

                                    shijidaozhang.setText(((Double.valueOf(s.toString()))-shouxu)+"元");
                                    real_amount = ((Double.valueOf(s.toString()))-shouxu)+"";
                                }else {
                                    shijidaozhang.setText("0.00 元");
                                }

                            }
                        }else {
                            shijidaozhang.setText("0.00 元");
                        }
                    }
                }else {
                    canuse_money.setText(use_money);
                }



            }
        });


    }



    private void showQuickRefund() {
        inputPwdPopupWindow = new PopupWindow();
        View view = LayoutInflater.from(this).inflate(R.layout.pay_popview,null);
        inputPwdPopupWindow.setContentView(view);
        ImageView close = view.findViewById(R.id.close);
        final PasswordInputView inputView = view.findViewById(R.id.input_view);
        TextView forgetPwd = view.findViewById(R.id.forgetpwd);
        PasswordKeyboardView keybordview = view.findViewById(R.id.keybordview);
        inputPwdPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        inputPwdPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        inputPwdPopupWindow.setAnimationStyle(R.style.AnimBottom_leyi);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        inputPwdPopupWindow.setBackgroundDrawable(dw);
        // 产生背景变暗效果
        WindowManager.LayoutParams ayoutParams = this.getWindow()
                .getAttributes();
        ayoutParams.alpha = 0.4f;
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.getWindow().setAttributes(ayoutParams);

        inputPwdPopupWindow.update();
        inputPwdPopupWindow.setFocusable(true);
        inputPwdPopupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
        keybordview.setIOnKeyboardListener(new PasswordKeyboardView.IOnKeyboardListener() {
            @Override
            public void onInsertKeyEvent(String text) {
                inputView.append(text);
            }

            @Override
            public void onDeleteKeyEvent() {
                int start = inputView.length() - 1;
                if (start >= 0) {
                    inputView.getText().delete(start, start + 1);
                }
            }
        });
        inputPwdPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = WithdrawDepositctivity.this.getWindow().getAttributes();
                params.alpha = 1.0f;
                params.dimAmount = 1.0f;
                WithdrawDepositctivity.this.getWindow().setAttributes(params);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputPwdPopupWindow.dismiss();

            }
        });

        inputView.setOnFinishListener(new PasswordInputView.OnFinishListener() {
            @Override
            public void setOnPasswordFinished() {
                if(inputView.getOriginText().length()==inputView.getMaxPasswordLength()){
                    //进行支付操作，调取支付接口
                    paypassword = inputView.getOriginText();
                    Log.d(TAG, "showQuickRefund: "+paypassword);
                    doB_id(paypassword,poundage,real_amount,binkID);
                }
            }
        });
        forgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
                intent.setClass(WithdrawDepositctivity.this,SetPayPsdActivity.class);
                WithdrawDepositctivity.this.startActivity(intent);
            }
        });

    }

    /**
     * 提现接口
     */
    private void doB_id(String pwd,String poundage,String real_amount,String bankID) {
        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String passwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email", email);
            object.put("pwd", passwd);
            object.put("fee", poundage);
            object.put("paypassword", pwd);
            object.put("real_amount", real_amount);
            object.put("bank_id", bankID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "doB_id: " + jjjjj);
        Log.d(TAG, "doB_id: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().currentMoney(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<CurrentMoneyBean>(this) {
            @Override
            protected void onHandleSuccess(CurrentMoneyBean currentMoneyBean) {
                canuse_money.setText(currentMoneyBean.getCurrent_amount()+"");
                Toast.makeText(WithdrawDepositctivity.this,currentMoneyBean.getShow_err(),Toast.LENGTH_LONG).show();
                inputPwdPopupWindow.dismiss();
                Intent intent  = new Intent(WithdrawDepositctivity.this,MainTabActivity.class);
                intent.putExtra("index","2");
                startActivity(intent);
                finish();
            }

        });

    }
}

