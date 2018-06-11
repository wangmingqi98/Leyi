package com.leyijf.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.AdvanceAlsoMoneyBean;
import com.leyijf.bean.AlsoMoneyBean;
import com.leyijf.bean.QuickRefundBean;
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

import static com.leyijf.R.id.zhuce_back;

/**
 * 提前还款页面
 * Created by wmq on 2018/5/9.
 */

public class AdvanceAlsoMoneyActivity extends BaseActivity implements View.OnClickListener{
    public static final String TAG = "AdvanceAlsoMoneyActivity";
    private ImageView back;
    private TextView money;//还款金额
    private TextView interestRate;//利率
    private TextView benxiMoney;//已还金额
    private TextView penalty;//应还本息
    private TextView income;//期限
    private TextView product;//项目名称
    private TextView managementMoney;//管理费
    private TextView advanceMoney;//提前罚息
    private TextView totalMoney;//总计还款
    private TextView availableBalance;//可用余额
    private TextView topUp;//充值
    private TextView immediatelyMoney;//立即还款按钮
    private Intent intent;
    private String id;
    private PopupWindow payPopupWindow,inputPwdPopupWindow;
    private double moneys,balanceMoney;
    private String repaymentId;
    @Override
    protected void initData() {


    }

    @Override
    protected void initId() {
        intent = getIntent();
        id = intent.getStringExtra("id");
        back = (ImageView) findViewById(zhuce_back);
        money = (TextView) findViewById(R.id.money);
        interestRate = (TextView) findViewById(R.id.interest_rate);
        benxiMoney = (TextView) findViewById(R.id.benxi_money);
        penalty = (TextView) findViewById(R.id.penalty);
        income = (TextView) findViewById(R.id.income);
        product = (TextView) findViewById(R.id.title_product);
        managementMoney = (TextView) findViewById(R.id.management_money);
        advanceMoney = (TextView) findViewById(R.id.advance_money);
        totalMoney = (TextView) findViewById(R.id.total_money);
        availableBalance = (TextView) findViewById(R.id.available_balance);
        availableBalance.setText("可用余额："+UserInfo.getInstance().getTotalMoney());
        topUp = (TextView) findViewById(R.id.top_up);
        immediatelyMoney = (TextView) findViewById(R.id.immediately_money);
        back.setOnClickListener(this);
        topUp.setOnClickListener(this);
        immediatelyMoney.setOnClickListener(this);
        initAdvance();


    }

    /**
     * 我的借款--提前还款
     */
    @SuppressLint("LongLogTag")
    private void initAdvance() {
        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String pwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email", email);
            object.put("pwd", pwd);
            object.put("id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initRepaymentDetail: " + jjjjj);
        Log.d(TAG, "initRepaymentDetail: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().advanceAlsoMoney(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<AdvanceAlsoMoneyBean>(this) {
            @Override
            protected void onHandleSuccess(AdvanceAlsoMoneyBean advanceAlsoMoneyBean) {
                //处理数据
                //剩余本息+管理费+提前罚息
                repaymentId = advanceAlsoMoneyBean.getDeal().getId();
                double totalRepayMoner = advanceAlsoMoneyBean.getTotal_repay_money();
                double trueAllManageMoney = advanceAlsoMoneyBean.getTrue_all_manage_money();
                double imposeMoney = advanceAlsoMoneyBean.getImpose_money();
                String repayMoney = advanceAlsoMoneyBean.getDeal().getRepay_money();
                money.setText((totalRepayMoner+trueAllManageMoney+imposeMoney)+"");//还款金额
                benxiMoney.setText(repayMoney);//已还金额
                penalty.setText(advanceAlsoMoneyBean.getTotal_repay_money()+"");//应还本息
                if(advanceAlsoMoneyBean.getDeal().getRepay_time_type().equals("0")){
                    income.setText(advanceAlsoMoneyBean.getDeal().getRepay_time()+"天");//期限
                }else {
                    income.setText(advanceAlsoMoneyBean.getDeal().getRepay_time()+"月");//期限
                }
                interestRate.setText("预期年利率:"+advanceAlsoMoneyBean.getDeal().getRate());//利率
                product.setText(advanceAlsoMoneyBean.getDeal().getName());//项目名称
                //还款金额+已还金额
                totalMoney.setText((totalRepayMoner+trueAllManageMoney+imposeMoney+(Double.valueOf(repayMoney)))+"");
                managementMoney.setText(trueAllManageMoney+"");//管理费
                advanceMoney.setText(imposeMoney+"");//提前罚息
                moneys = totalRepayMoner+trueAllManageMoney+imposeMoney;
//                balanceMoney = Double.valueOf(advanceAlsoMoneyBean.getDeal().getUser().getMoney());


            }


        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_advancealsomoney;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuce_back:
                finish();
                break;
            case R.id.immediately_money://弹出确认支付弹框
                showSurePay(moneys,Double.valueOf(UserInfo.getInstance().getTotalMoney()));
                break;
        }

    }

    /**
     * 确认支付弹框
     */
    private void showSurePay(final double moneys, final double balanceMoney) {
        payPopupWindow = new PopupWindow();
        View  view = LayoutInflater.from(this).inflate(R.layout.popupwindow_pay,null);
        payPopupWindow.setContentView(view);
        TextView money = view.findViewById(R.id.money);
        TextView balance = view.findViewById(R.id.balance);
        ImageView close = view.findViewById(R.id.close);
        TextView btn_alsomoney = view.findViewById(R.id.btn_alsomoney);
        money.setText(moneys+"");
        balance.setText("余额支付(可用余额:"+balanceMoney+"元)");
        if(moneys<balanceMoney){
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
                WindowManager.LayoutParams params = AdvanceAlsoMoneyActivity.this.getWindow().getAttributes();
                params.alpha = 1.0f;
                params.dimAmount = 1.0f;
                AdvanceAlsoMoneyActivity.this.getWindow().setAttributes(params);
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
//                payPopupWindow.dismiss();
                //需要判断跳转的是充值还是付款
                if(moneys<balanceMoney){//立即还款
                    showQuickRefund();
                }else {//立即充值
                    Intent intent = new Intent();
                    intent.setClass(AdvanceAlsoMoneyActivity.this,ChongzhiActivity.class);
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
                WindowManager.LayoutParams params = AdvanceAlsoMoneyActivity.this.getWindow().getAttributes();
                params.dimAmount = 1.0f;
                AdvanceAlsoMoneyActivity.this.getWindow().setAttributes(params);
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
                //进行支付操作，调取支付接口
                if(inputView.getOriginText().length()==inputView.getMaxPasswordLength()){

                    ininpay(inputView.getOriginText());
                }
            }
        });
        forgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
                intent.setClass(AdvanceAlsoMoneyActivity.this,SetPayPsdActivity.class);
                AdvanceAlsoMoneyActivity.this.startActivity(intent);
            }
        });

    }
    /**
     * 立即还款接口
     */
    @SuppressLint("LongLogTag")
    private void ininpay(String password) {
        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String pwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email", email);
            object.put("pwd", pwd);
            object.put("id",repaymentId);
            object.put("paypassword",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "ininpay: " + jjjjj);
        Log.d(TAG, "ininpay: " + object.toString());
        Observable observable  = RetrofitFactory.getInstance().alsoMoneyComit(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<AlsoMoneyBean>(this) {
            @Override
            protected void onHandleSuccess(AlsoMoneyBean alsoMoneyBean) {
                //弹出支付成功弹窗
                Toast.makeText(AdvanceAlsoMoneyActivity.this,alsoMoneyBean.getShow_err(),Toast.LENGTH_LONG).show();
                inputPwdPopupWindow.dismiss();
                Intent intent  = new Intent(AdvanceAlsoMoneyActivity.this,MainTabActivity.class);
                intent.putExtra("index","2");
                startActivity(intent);
                AdvanceAlsoMoneyActivity.this.finish();
            }



        });
    }
}
