package com.leyi.view.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import com.leyi.R;
import com.leyi.base.BaseActivity;
import com.leyi.bean.UserInfo;

import java.util.HashMap;
import java.util.Map;

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
    private String borrow_amount;
    private String need_money;
    private String id;
    private TextView need;
    private TextView can_usemoney;
    private TextView quantou;
    private Map<String, String> map;
    private String bid_money;
    Handler handler=new Handler();
    private String loantype_format;
    private String rate;
    private String repay_time;
    private String repay_time_type;


    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {

        map = new HashMap<>();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        borrow_amount = extras.getString("borrow_amount");
        need_money = extras.getString("need_money");
        id = extras.getString("id");
        loantype_format = extras.getString("loantype_format");
        rate = extras.getString("rate");
        repay_time = extras.getString("repay_time");
        repay_time_type = extras.getString("repay_time_type");
        map.put("act","deal_dobid");
        map.put("email",UserInfo.getInstance().getUserPhone());
        map.put("pwd",UserInfo.getInstance().getUserPwd());
        map.put("id",id);


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

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(s.toString())&&TextUtils.isDigitsOnly(s.toString())&&Integer.parseInt(s.toString())>=100){
                    next_stepqian.setVisibility(View.GONE);
                    next_stepshen.setVisibility(View.VISIBLE);


                }else {

                    next_stepqian.setVisibility(View.VISIBLE);
                    next_stepshen.setVisibility(View.GONE);


                }


            }
        });



        quantou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chongzhi_edit.setText(UserInfo.getInstance().getUserMoney());
                canuse_money.setText(UserInfo.getInstance().getUserMoney());


            }
        });



        next_stepshen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bid_money = chongzhi_edit.getText().toString().trim();
                map.put("bid_money",bid_money);
                if(UserInfo.getInstance().getHas_paypassword()!=0){
                        Intent intent=new Intent(TouBiaoMoneyActivity.this,OkOrderActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("borrow_amount", borrow_amount+"");
                    bundle.putString("need_money",need_money+"");
                    bundle.putString("id",id+"");
                    bundle.putString("bid_money",bid_money);
                    bundle.putString("loantype_format",loantype_format);
                    bundle.putString("rate",rate);
                    bundle.putString("repay_time",repay_time);
                    bundle.putString("repay_time_type",repay_time_type);
                    intent.putExtras(bundle);;
                   startActivity(intent);
                    finish();



                }else {
                    View  peicepop = LayoutInflater.from(TouBiaoMoneyActivity.this).inflate(R.layout.nopaypsd,null);
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
                    popupWindows.showAtLocation(TouBiaoMoneyActivity.this.getWindow().getDecorView(), Gravity.CENTER,0,0);
                    // 产生背景变暗效果
                    WindowManager.LayoutParams ayoutParams =TouBiaoMoneyActivity. this.getWindow()
                            .getAttributes();
                    ayoutParams.alpha = 0.4f;
                   TouBiaoMoneyActivity. this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    TouBiaoMoneyActivity.this.getWindow().setAttributes(ayoutParams);

                    popupWindows.update();
                    popupWindows.setOnDismissListener(new PopupWindow.OnDismissListener() {

                        // 在dismiss中恢复透明度
                        public void onDismiss() {
                            WindowManager.LayoutParams lp = TouBiaoMoneyActivity.this.getWindow()
                                    .getAttributes();
                            lp.alpha = 1f;
                            TouBiaoMoneyActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                            TouBiaoMoneyActivity.this.getWindow().setAttributes(lp);
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
                            startActivity(new Intent(TouBiaoMoneyActivity.this,SetPayPsdActivity.class));
                        }
                    });



                }

            }
        });

    }

    private void towrite() {
        xiane.setText(borrow_amount);
        need.setText(need_money);
        can_usemoney.setText(UserInfo.getInstance().getUserMoney()+"元");


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
        need = (TextView) findViewById(R.id.textView3);
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
