package com.leyijf.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leyijf.R;
import com.leyijf.adapter.B_RepaymentDetailAdapter;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.B_RepaymentDetailBean;
import com.leyijf.bean.LookDetailBean;
import com.leyijf.bean.QuickRefundBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;
import com.leyijf.view.MainTabActivity;
import com.leyijf.weight.LookDetailDialog;
import com.leyijf.weight.PasswordInputView;
import com.leyijf.weight.PasswordKeyboardView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * 我的借款---还款详情
 * Created by wmq on 2018/5/9.
 */

public class B_RepaymentDetailActivity extends BaseActivity implements View.OnClickListener,B_RepaymentDetailAdapter.LookDetail{
    public static final String TAG = "B_RepaymentDetailActivity";
    private RecyclerView recyclerView;
    private ImageView back;
    private RelativeLayout relativeLayout;
    private Intent intent;
    private String id;
    private View headerTop,header;
    private B_RepaymentDetailAdapter adapter;
    private List<B_RepaymentDetailBean.LoanListBean> loan_list = new ArrayList<>();
    private List<LookDetailBean.LoadUserBean> load_user = new ArrayList<>();
    private TextView titleCount,money,time,benxi_money,penalty,income,mangement,incomeContent;
    private ImageView repaymentType;
    private TextView immediately_money,available_balance,top_up,advance;
    private LookDetailDialog detailDialog;
    private PopupWindow payPopupWindow;
    private PopupWindow inputPwdPopupWindow;
    private double moneys,balanceMoney;
    private String repayementId,repaymentDealId;
    private LinearLayoutManager linearLayoutManager;
    private String repay_id;

    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        intent = getIntent();
        id = intent.getStringExtra("id");
        linearLayoutManager = new LinearLayoutManager(this);
        back = (ImageView) findViewById(R.id.zhuce_back);
        back.setOnClickListener(this);
        relativeLayout = (RelativeLayout) findViewById(R.id.layout_immediately);
        relativeLayout.setVisibility(View.VISIBLE);
        immediately_money = (TextView) findViewById(R.id.immediately_money);//立即还款按钮
        immediately_money.setOnClickListener(this);
        available_balance = (TextView) findViewById(R.id.available_balance);//显示用户余额
        top_up = (TextView) findViewById(R.id.top_up);//充值
        top_up.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        headerTop = LayoutInflater.from(this).inflate(R.layout.item_header_top,null);
        titleCount = headerTop.findViewById(R.id.title_count);
        money = headerTop.findViewById(R.id.money);
        time = headerTop.findViewById(R.id.time);
        mangement = headerTop.findViewById(R.id.management);
        mangement.setText("管理费(元)");
        benxi_money = headerTop.findViewById(R.id.benxi_money);
        penalty = headerTop.findViewById(R.id.penalty);
        income = headerTop.findViewById(R.id.income);
        incomeContent = headerTop.findViewById(R.id.income_content);
        incomeContent.setText("预期还款");
        repaymentType = headerTop.findViewById(R.id.repayment_type);
//        header = LayoutInflater.from(this).inflate(R.layout.item_header,null);
        advance = headerTop. findViewById(R.id.advance);
        advance.setOnClickListener(this);
        initRepaymentDetail();
        adapter = new B_RepaymentDetailAdapter(R.layout.item_rapayment,loan_list);
        adapter.setLookDetail(this);
//        adapter.addHeaderView(header);
        adapter.addHeaderView(headerTop);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    /**
     * 我的借款--还款详情
     */
    @SuppressLint("LongLogTag")
    private void initRepaymentDetail() {
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
        Observable observable = RetrofitFactory.getInstance().B_repaymentDetail(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<B_RepaymentDetailBean>(this) {
            @Override
            protected void onHandleSuccess(B_RepaymentDetailBean b_repaymentDetailBean) {
                loan_list.addAll(b_repaymentDetailBean.getLoan_list());
                repay_id = b_repaymentDetailBean.getCurrent_l_key_index().getRepay_id();
                titleCount.setText("第"+b_repaymentDetailBean.getCurrent_l_key_index().getL_key_index()+"期收益(元)");
                money.setText(b_repaymentDetailBean.getCurrent_l_key_index().getMonth_need_all_repay_money_format());
                time.setText("还款日:"+b_repaymentDetailBean.getCurrent_l_key_index().getRepay_day_format());
                benxi_money.setText(b_repaymentDetailBean.getCurrent_l_key_index().getMonth_manage_money_format()+"");
                penalty.setText(b_repaymentDetailBean.getCurrent_l_key_index().getImpose_all_money_format());
                income.setText(b_repaymentDetailBean.getCurrent_l_key_index().getMonth_repay_money_format()+"");
                available_balance.setText("可用余额："+b_repaymentDetailBean.getDeal().getUser().getMoney());
                moneys =Double.valueOf(b_repaymentDetailBean.getCurrent_l_key_index().getMonth_need_all_repay_money());
                balanceMoney = Double.valueOf(b_repaymentDetailBean.getDeal().getUser().getMoney());
                repayementId =b_repaymentDetailBean.getDeal().getId();
                repaymentDealId = b_repaymentDetailBean.getCurrent_l_key_index().getL_key();

                if(b_repaymentDetailBean.getCurrent_l_key_index().getHas_repay().equals(0)){//待还款
                    repaymentType.setImageResource(R.drawable.daihuankuan);
                }else {//还款中的状态
                    switch (b_repaymentDetailBean.getCurrent_l_key_index().getStatus()) {
                        case 1://提前还款
                            repaymentType.setImageResource(R.drawable.tiqianhuankuan);
                            break;
                        case 2://正常还款
                            repaymentType.setImageResource(R.drawable.zhenchanghuankuan);
                            break;
                        case 3://逾期还款
                            repaymentType.setImageResource(R.drawable.putongyuqi);
                            break;
                        case 4://严重逾期
                            repaymentType.setImageResource(R.drawable.yanzhongyuqi);
                            break;
                        case 5://部分还款
                            repaymentType.setImageResource(R.drawable.bufenhuankuan);
                            break;
                        case 6://还款中
                            repaymentType.setImageResource(R.drawable.huankuanzhong);
                            break;
                        default:
                            break;
                    }
                }
                adapter.notifyDataSetChanged();
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        //点击每条数据显示到卡片上

                        titleCount.setText("第"+loan_list.get(position).getL_key_index()+"期收益(元)");
                        money.setText(loan_list.get(position).getMonth_need_all_repay_money_format());
                        time.setText("还款日:"+loan_list.get(position).getRepay_day_format());
                        benxi_money.setText(loan_list.get(position).getMonth_manage_money_format()+"");
                        penalty.setText(loan_list.get(position).getImpose_all_money_format());
                        income.setText(loan_list.get(position).getMonth_repay_money_format()+"");
                        if(loan_list.get(position).getRepay_id().equals(repay_id)){
                            immediately_money.setFocusable(true);
                            immediately_money.setBackgroundResource(R.drawable.btn_shape);
                        }else {
                            immediately_money.setFocusable(false);
                            immediately_money.setBackgroundResource(R.drawable.hui_btn_shape);
                        }
                    }
                });
            }


        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_repayment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuce_back:
                finish();
                break;
            case R.id.immediately_money://点击立即还款跳转支付页面
                showPopupWindow(moneys,balanceMoney);
                break;
            case R.id.top_up://跳转充值
                startActivity(new Intent(this,ChongzhiActivity.class));
                break;
            case R.id.advance://跳转提前还款页面
                    Intent intent = new Intent();
                        intent.setClass(this,AdvanceAlsoMoneyActivity.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 弹出立即还款
     */
    private void showPopupWindow(final double moneys, final double balanceMoney) {
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
                WindowManager.LayoutParams params = B_RepaymentDetailActivity.this.getWindow().getAttributes();
                params.alpha = 1.0f;
                params.dimAmount = 1.0f;
                B_RepaymentDetailActivity.this.getWindow().setAttributes(params);
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
                if(moneys<balanceMoney){//立即还款
                    payPopupWindow.dismiss();
                    showQuickRefund();
                }else {//立即充值
                    Intent intent = new Intent();
                    intent.setClass(B_RepaymentDetailActivity.this,ChongzhiActivity.class);
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
                WindowManager.LayoutParams params = B_RepaymentDetailActivity.this.getWindow().getAttributes();
                params.alpha = 1.0f;
                params.dimAmount = 1.0f;
                B_RepaymentDetailActivity.this.getWindow().setAttributes(params);
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
                //需要调取忘记密码接口
                Intent intent  = new Intent();
                intent.setClass(B_RepaymentDetailActivity.this,SetPayPsdActivity.class);
                B_RepaymentDetailActivity.this.startActivity(intent);
            }
        });

    }

    /**
     * 立即还款接口
     */
    @SuppressLint("LongLogTag")
    private void ininpay(String password) {
        Log.d(TAG, "ininpay: "+UserInfo.getInstance().getSession_id());
        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String pwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
//            object.put("email", email);
//            object.put("pwd", pwd);
            object.put("id",repayementId);
            object.put("ids",repaymentDealId);
            object.put("paypassword",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "ininpay: " + jjjjj);
        Log.d(TAG, "ininpay: " + object.toString());
        Observable observable  = RetrofitFactory.getInstance().quickRefund(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<QuickRefundBean>(this) {
            @Override
            protected void onHandleSuccess(QuickRefundBean quickRefundBean) {
                //弹出支付成功弹窗
                Toast.makeText(B_RepaymentDetailActivity.this,quickRefundBean.getShow_err(),Toast.LENGTH_LONG).show();
                inputPwdPopupWindow.dismiss();
                Intent intent  = new Intent(B_RepaymentDetailActivity.this,MainTabActivity.class);
                intent.putExtra("index","2");
                startActivity(intent);
                B_RepaymentDetailActivity.this.finish();
            }


        });
    }

    /**
     * 处理查看明细的弹框
     * @param
     * @param lKey
     */
    @SuppressLint("LongLogTag")
    @Override
    public void showLookDetail( String lKey) {
        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String pwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email", email);
            object.put("pwd", pwd);
            object.put("id",id);
            object.put("l_key",lKey);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "showLookDetail: " + jjjjj);
        Log.d(TAG, "showLookDetail: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().lookDetail(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<LookDetailBean>(this) {
            @Override
            protected void onHandleSuccess(LookDetailBean lookDetailBean) {
                if(lookDetailBean.getLoad_user().size()>0){
                    load_user.addAll(lookDetailBean.getLoad_user());
                    //展示弹出框
                    detailDialog = new LookDetailDialog(B_RepaymentDetailActivity.this,load_user);
                    detailDialog.show();

                }else {
                    Toast.makeText(B_RepaymentDetailActivity.this,"暂无还款记录！",Toast.LENGTH_LONG).show();
                }
            }


        });

    }
}
