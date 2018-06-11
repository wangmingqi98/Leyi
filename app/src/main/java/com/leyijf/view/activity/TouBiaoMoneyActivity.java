package com.leyijf.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leyijf.R;
import com.leyijf.base.BaseActivity;

/**
 * 投资金额
 */
public class TouBiaoMoneyActivity extends BaseActivity {

    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
    private TextView xiane;
    private LinearLayout add_bankcard;
    private TextView canuse_money;
    private EditText chongzhi_edit;
    private TextView next_stepshen;
    private TextView next_stepqian;
    private TextView need;
    private TextView can_usemoney;
    private TextView quantou;
    private String bid_money;
    String dealId,isNew,useEcv,useRate,name,rateContent,time,repayment,needMoney,needMoneys,totalMoney;

    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {

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
        dealId = intent.getStringExtra("dealId");
        initView();
        towrite();
        listener();

    }

    private void listener() {

        chongzhi_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    if ((Double.valueOf(totalMoney) - Double.valueOf(chongzhi_edit.getText().toString())) > 0) {
                        String money = (Double.valueOf(totalMoney) - Double.valueOf(chongzhi_edit.getText().toString())) + "";
                        can_usemoney.setText("可用余额：" + money + "元");
                    } else {
                        Toast.makeText(TouBiaoMoneyActivity.this, "可用余额不足，请重新输入或去充值", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(s.toString())&&TextUtils.isDigitsOnly(s.toString())&&Integer.parseInt(s.toString())>=1000){
                    next_stepqian.setVisibility(View.GONE);
                    next_stepqian.setFocusable(false);
                    next_stepshen.setVisibility(View.VISIBLE);
                    next_stepshen.setFocusable(true);


                }else {
                    next_stepqian.setVisibility(View.VISIBLE);
                    next_stepqian.setFocusable(false);
                    next_stepshen.setVisibility(View.GONE);


                }


            }
        });



        quantou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalMoney!=null){
                    chongzhi_edit.setText(totalMoney);
                    can_usemoney.setText("可用余额："+"0.00 元");
                }else {
                    Toast.makeText(TouBiaoMoneyActivity.this,"可用余额不足，请先充值",Toast.LENGTH_LONG).show();
                }


            }
        });



        next_stepshen.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                bid_money = chongzhi_edit.getText().toString().trim();
                if((Double.valueOf(bid_money))<=(Double.valueOf(totalMoney))){
                    Intent intent=new Intent(TouBiaoMoneyActivity.this,OkOrderActivity.class);
                    intent.putExtra("isNew",isNew);
                    intent.putExtra("useEcv",useEcv);
                    intent.putExtra("useRate",useRate);
                    intent.putExtra("name",name);
                    intent.putExtra("rateContent",rateContent);
                    intent.putExtra("time",time);
                    intent.putExtra("repayment",repayment);
                    intent.putExtra("needMoney",needMoney);
                    intent.putExtra("needMoneys",needMoneys);
                    intent.putExtra("totalMoney",totalMoney);
                    intent.putExtra("dealId",dealId);
                    intent.putExtra("bid_money",bid_money);
                    startActivity(intent);
                }else {
                    Toast.makeText(TouBiaoMoneyActivity.this,"可用余额不足，请先充值",Toast.LENGTH_LONG).show();
                }



//                }else {
//                    View  peicepop = LayoutInflater.from(TouBiaoMoneyActivity.this).inflate(R.layout.nopaypsd,null);
//                    ImageView   popup_close2 = peicepop.findViewById(R.id.jisuan_close);
//                     TextView go = peicepop.findViewById(R.id.go_jiaoyipsd);
//                     final PopupWindow popupWindows = new PopupWindow(peicepop, ViewGroup.LayoutParams.WRAP_CONTENT,
//                            ViewGroup.LayoutParams.WRAP_CONTENT);
//                    popupWindows.setBackgroundDrawable(new BitmapDrawable());
//                    popupWindows.setFocusable(true);// 取得焦点
//                    //点击外部消失
//                    popupWindows.setOutsideTouchable(true);
//                    //设置可以点击
//                    popupWindows.setTouchable(true);
//                    popupWindows.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
//
//                    popupWindows.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//                    popupWindows.showAtLocation(TouBiaoMoneyActivity.this.getWindow().getDecorView(), Gravity.CENTER,0,0);
//                    // 产生背景变暗效果
//                    WindowManager.LayoutParams ayoutParams =TouBiaoMoneyActivity. this.getWindow()
//                            .getAttributes();
//                    ayoutParams.alpha = 0.4f;
//                   TouBiaoMoneyActivity. this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                    TouBiaoMoneyActivity.this.getWindow().setAttributes(ayoutParams);
//
//                    popupWindows.update();
//                    popupWindows.setOnDismissListener(new PopupWindow.OnDismissListener() {
//
//                        // 在dismiss中恢复透明度
//                        public void onDismiss() {
//                            WindowManager.LayoutParams lp = TouBiaoMoneyActivity.this.getWindow()
//                                    .getAttributes();
//                            lp.alpha = 1f;
//                            TouBiaoMoneyActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                            TouBiaoMoneyActivity.this.getWindow().setAttributes(lp);
//                        }
//                    });
//
//
//                    popup_close2.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            popupWindows.dismiss();
//                        }
//                    });
//
//                    go.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(TouBiaoMoneyActivity.this,SetPayPsdActivity.class));
//                        }
//                    });
//
//
//
//                }

            }
        });

    }

    private void towrite() {
        xiane.setText(needMoney+"元");
        need.setText(needMoneys+"元");
        if(totalMoney!=null){

            can_usemoney.setText("可用余额："+totalMoney+"元");
        }else {
            can_usemoney.setText("可用余额："+"0.00 元");
        }


    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_tou_biao_money;
    }

    private void initView() {
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);
        xiane = (TextView) findViewById(R.id.xiane);
        add_bankcard = (LinearLayout) findViewById(R.id.add_bankcard);
        canuse_money = (TextView) findViewById(R.id.canuse_money);
        chongzhi_edit = (EditText) findViewById(R.id.chongzhi_edit);
        next_stepshen = (TextView) findViewById(R.id.next_stepshen);
        next_stepqian = (TextView) findViewById(R.id.next_stepqian);
        next_stepqian.setFocusable(false);
        need = (TextView) findViewById(R.id.remain);
        can_usemoney= (TextView) findViewById(R.id.can_usemoney);
        quantou = (TextView) findViewById(R.id.quantou);
        zhuce_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
