package com.leyijf.view.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.ComitGoodsBean;
import com.leyijf.bean.SureGoodsBean;
import com.leyijf.bean.SureInvestGoods;
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

import io.reactivex.Observable;

/**
 * 确认订单
 */
public class OkOrderActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG ="OkOrderActivity" ;
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
    private PasswordKeyboardView keybordview;
    private PasswordInputView input_view;
    private StringBuffer stringBuffer;
    private PopupWindow payPopupWindow,inputPwdPopupWindow;
    String bid_money,dealId,isNew,useEcv,useRate,name,rateContent,time,repayment,needMoney,needMoneys,totalMoney;
    private TextView now_chongzhi;
    private TextView canuseryue;
    private CheckBox checkBox;
    private TextView paymoney;
    private ImageView close;
    private PopupWindow popupWindows;
    private ImageView pay_close;
    private LinearLayout red_layout,rate_layout;
    private TextView rate,red;
    private String voucher_id,rate_id;
    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        initView();
        sureGoods();
        iniList();

    }

    private void iniList() {
        next_stepshen.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //调取确认投资订单
                sureInvestGoods();
            }
        });

    }

    /**
     * 确认投资订单接口
     */
    private void sureInvestGoods() {
        JSONObject object = new JSONObject();
        try {
            object.put("deal_id",dealId);
            object.put("invest_money",bid_money);
            object.put("pay_money",bid_money);
            object.put("voucher_id","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "sureInvestGoods: " + jjjjj);
        Log.d(TAG, "sureInvestGoods: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().sureInvestGoods(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<SureInvestGoods>(this) {
            @Override
            protected void onHandleSuccess(SureInvestGoods sureInvestGoods) {
                if(sureInvestGoods.getHas_paypassword()==1){
                    showSurePay(Double.valueOf(bid_money),sureInvestGoods.getAvailable_money(),sureInvestGoods.getMoney_is_enough());
                }else {//进入设置交易密码界面
                    Intent intent = new Intent();
                    intent.setClass(OkOrderActivity.this,SetPayPsdActivity.class);
                    startActivity(intent);
                }
            }

        });
    }

    /**
     * 确认订单接口
     */
    private void sureGoods() {
        JSONObject object = new JSONObject();
        try {
            object.put("deal_id",dealId);
            object.put("invest_money",bid_money);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "sureGoods: " + jjjjj);
        Log.d(TAG, "sureGoods: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().sureGoods(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<SureGoodsBean>(this) {
            @Override
            protected void onHandleSuccess(SureGoodsBean sureGoodsBean) {
                if(isNew.equals("0")){//不是新手标
                    red_layout.setVisibility(View.VISIBLE);
                    rate_layout.setVisibility(View.VISIBLE);
                    if(sureGoodsBean.getDeal_info().getUse_ecv().equals("0")){
                        red.setText("无可用红包");
                        useEcv = "0";
                    }else {
                        useEcv = sureGoodsBean.getDeal_info().getUse_ecv();
                        red.setText("有"+sureGoodsBean.getDeal_info().getUser_vouchers_count()+"个可用红包");
                    }
                    if(sureGoodsBean.getDeal_info().getUse_rate().equals("0")){
                        rate.setText("无可用加息券");
                        useRate = "0";
                    }else {
                        useRate = sureGoodsBean.getDeal_info().getUse_rate();
                        rate.setText("有"+sureGoodsBean.getDeal_info().getUser_rates_count()+"个可用加息券");
                    }
                }else{//是新手标
                    red_layout.setVisibility(View.GONE);
                    rate_layout.setVisibility(View.GONE);
                }
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
        red_layout = (LinearLayout) findViewById(R.id.red_layout);
        rate_layout = (LinearLayout) findViewById(R.id.rate_layout);
        red_layout.setOnClickListener(this);
        rate_layout.setOnClickListener(this);
        rate = (TextView) findViewById(R.id.rate);
        red = (TextView) findViewById(R.id.red);
        Intent intent = getIntent();
        isNew = intent.getStringExtra("isNew");
        useEcv = intent.getStringExtra("useEcv");
        useRate = intent.getStringExtra("useRate");
        name = intent.getStringExtra("name");
        rateContent = intent.getStringExtra("rateContent");
        time = intent.getStringExtra("time");
        repayment = intent.getStringExtra("repayment");
        needMoney = intent.getStringExtra("needMoney");
        needMoneys = intent.getStringExtra("needMoneys");
        totalMoney = intent.getStringExtra("totalMoney");
        bid_money = intent.getStringExtra("bid_money");
        dealId = intent.getStringExtra("dealId");
        hukuan_name.setText(name);
        need_money2.setText(rateContent+"%");
        jiezhi.setText(time);
        loantype_format.setText(repayment);
        xiane.setText(bid_money+"元");
        textView3.setText(bid_money+"元");
        if(voucher_id!=null&&rate_id==null){
            rate_layout.setFocusable(false);
            red_layout.setFocusable(true);
            Toast.makeText(OkOrderActivity.this,"红包与加息券只能使用一种",Toast.LENGTH_LONG).show();
        }else if(voucher_id==null&&rate_id!=null){
            rate_layout.setFocusable(true);
            red_layout.setFocusable(false);
            Toast.makeText(OkOrderActivity.this,"红包与加息券只能使用一种",Toast.LENGTH_LONG).show();
        }


    zhuce_back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });



    }


    /**
     * 确认支付弹框
     */
    private void showSurePay( double moneys,  double balanceMoney, final int money_is_enough) {
        payPopupWindow = new PopupWindow();
        View  view = LayoutInflater.from(this).inflate(R.layout.popupwindow_pay,null);
        payPopupWindow.setContentView(view);
        TextView money = view.findViewById(R.id.money);
        TextView balance = view.findViewById(R.id.balance);
        ImageView close = view.findViewById(R.id.close);
        TextView btn_alsomoney = view.findViewById(R.id.btn_alsomoney);
        money.setText(moneys+"");
        balance.setText("余额支付(可用余额:"+balanceMoney+"元)");
        if(money_is_enough==1){
            btn_alsomoney.setText("立即还款");
        }else {
            btn_alsomoney.setText("立即充值");

        }
        payPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        payPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        payPopupWindow.setAnimationStyle(R.style.AnimBottom_leyi);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        payPopupWindow.setBackgroundDrawable(dw);
        final WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.alpha = 0.5f;
        params.dimAmount = 0.5f;
        this.getWindow().setAttributes(params);
        payPopupWindow.setFocusable(true);
        payPopupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
        payPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = OkOrderActivity.this.getWindow().getAttributes();
                params.alpha = 1.0f;
                params.dimAmount = 1.0f;
                OkOrderActivity.this.getWindow().setAttributes(params);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payPopupWindow.dismiss();
            }
        });
        btn_alsomoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                //需要判断跳转的是充值还是付款
                if(money_is_enough==1){//立即还款
                    payPopupWindow.dismiss();
                    showQuickRefund();
                }else {//立即充值
                    Intent intent = new Intent();
                    intent.setClass(OkOrderActivity.this,ChongzhiActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
    /**
     * 弹出输入支付密码弹窗
     */
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
                WindowManager.LayoutParams params = OkOrderActivity.this.getWindow().getAttributes();
                params.alpha = 1.0f;
                params.dimAmount = 1.0f;
                OkOrderActivity.this.getWindow().setAttributes(params);
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
                //进行提交订单操作，调取支付接口
                if(inputView.getOriginText().length()==inputView.getMaxPasswordLength()){

                    ininpay(inputView.getOriginText());
                }
            }
        });
        forgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
                intent.setClass(OkOrderActivity.this,SetPayPsdActivity.class);
                OkOrderActivity.this.startActivity(intent);
            }
        });

    }

    /**
     * 立即投资----提交订单
     */
    private void ininpay(String payPassword) {
        JSONObject object = new JSONObject();
        try {
            object.put("email", UserManager.getInstance().getLoginUser().getUser_mobile_referee());
            object.put("pwd", UserInfo.getInstance().getUserPwd());
            object.put("deal_id", dealId);
            object.put("invest_money", bid_money);
            object.put("pay_money", bid_money);
            object.put("voucher_id", voucher_id);
            object.put("rate_id", rate_id);
            object.put("pay_password", payPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "ininpay: " + jjjjj);
        Log.d(TAG, "ininpay: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().comitGoods(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<ComitGoodsBean>(this) {
            @Override
            protected void onHandleSuccess(ComitGoodsBean comitGoodsBean) {
                //弹出支付成功弹窗
                Toast.makeText(OkOrderActivity.this,"投资成功！！！",Toast.LENGTH_LONG).show();
                inputPwdPopupWindow.dismiss();
                Intent intent  = new Intent(OkOrderActivity.this,InvestSuccessActivity.class);
                startActivity(intent);
                OkOrderActivity.this.finish();
            }


        });

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.red_layout:
                if(useEcv.equals("0")){
                    Toast.makeText(this,"暂无可用红包",Toast.LENGTH_LONG).show();
                }else {//跳转可用红包页面
                    intent.putExtra("type","1");

                }

                break;
            case R.id.rate_layout:
                if(useRate.equals("0")){
                    Toast.makeText(this,"暂无可用加息券",Toast.LENGTH_LONG).show();
                }else {//跳转可用加息券
                    intent.putExtra("type","2");
                }
                break;
            default:
                break;
        }
        intent.putExtra("dealId",dealId);
        intent.putExtra("bid_money",bid_money);
        intent.setClass(this,RedpacketsAndRateActivity.class);
        startActivityForResult(intent,100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100){
            voucher_id = data.getStringExtra("voucher_id");
            rate_id = data.getStringExtra("rate_id");
        }
    }
}
