package com.leyijf.view.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leyijf.R;
import com.leyijf.adapter.MyindentViewpagerAdapter;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.FinancialRepaymentDetailBean;
import com.leyijf.bean.GetPrpfitBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;
import com.leyijf.util.DateUtils;
import com.leyijf.view.fragment.fragment_touzi.InsideUserInfoFragment;
import com.leyijf.view.fragment.fragment_touzi.ProjectDetilsFragment;
import com.leyijf.view.fragment.fragment_touzi.RiskControFragment;
import com.leyijf.view.fragment.fragment_touzi.TouziRenshuFragment;
import com.leyijf.weight.CustomViewpager;
import com.leyijf.weight.CustomizedProgressBar;
import com.leyijf.weight.MarqueeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

/**
 * 投标详情
 */
public class YouBiaoActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "YouBiaoActivity";
    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
    private ImageView jisuan;
    private TextView get_price;
    private FragmentManager supportFragmentManager;
    private PopupWindow popupWindows;
    private FinancialRepaymentDetailBean financialRepaymentDetailBeans;
    private String id;
    private String type;
    private TextView rate, need_money, repay_time, min_loan_money;
    private TextView need_money2, nianlilv, jiezhi, loantype_format;
    private CustomizedProgressBar progress;
    private MarqueeView marqueeView;
    private TabLayout mTablayoutMyindent;
    private CustomViewpager mViewpagerMyindent;
    private String[] mStringList = {"项目详情", "合同/抵押物", "投资人数", "还款详情"};
    private String[] mStringtitle = {"项目详情", "合同/抵押物", "投资人数"};
    private List<Fragment> mList = new ArrayList<>();
    Handler handler = new Handler();
    TouziRenshuFragment touziRenshuFragment;
    ProjectDetilsFragment projectDetilsFragment;
    InsideUserInfoFragment insideUserInfoFragment;
    RiskControFragment fengxianControFragment;
    String dealId, isNew, useEcv, useRate, name, rateContent, time, repayment, needMoney, needMoneys, totalMoney;
    private TextView earnings;

    @Override
    protected void initData() {
        initView();
    }

    @Override
    protected void initId() {

    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_you_biao;
    }

    private void initView() {
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        zhuce_back.setOnClickListener(this);
        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);
        supportFragmentManager = getSupportFragmentManager();
        jisuan = (ImageView) findViewById(R.id.jisuan);
        jisuan.setOnClickListener(this);
        get_price = (TextView) findViewById(R.id.get_price);
        rate = (TextView) findViewById(R.id.rate);
        progress = (CustomizedProgressBar) findViewById(R.id.progress);
        marqueeView = (MarqueeView) findViewById(R.id.marqueeview);
        need_money = (TextView) findViewById(R.id.need_money);
        repay_time = (TextView) findViewById(R.id.repay_time);
        min_loan_money = (TextView) findViewById(R.id.min_loan_money);
        need_money2 = (TextView) findViewById(R.id.need_money2);
        nianlilv = (TextView) findViewById(R.id.nianlilv);
        jiezhi = (TextView) findViewById(R.id.jiezhi_date);
        loantype_format = (TextView) findViewById(R.id.loantype_format);
        mTablayoutMyindent = (TabLayout) findViewById(R.id.tablayout_myindent);
        mViewpagerMyindent = (CustomViewpager) findViewById(R.id.viewpager_myindent);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        type = intent.getStringExtra("type");
        Log.d(TAG, "initView: " + type);
        Log.d(TAG, "initView: " + id);
        switch (type) {
            case "1"://立即投资
                get_price.setText("立即投资");
                get_price.setBackgroundColor(Color.parseColor("#ff7830"));
                get_price.setFocusable(true);
                get_price.setOnClickListener(this);
                break;
            case "2"://还款中
                get_price.setText("还款中");
                get_price.setFocusable(false);
                get_price.setBackgroundColor(Color.parseColor("#999999"));
                break;
            case "3"://已还清
                get_price.setText("已还清");
                get_price.setBackgroundColor(Color.parseColor("#999999"));
                get_price.setFocusable(false);
                break;
            case "4"://还款中
                get_price.setText("还款中");
                get_price.setFocusable(false);
                get_price.setBackgroundColor(Color.parseColor("#999999"));
                break;
            case "5"://已还清
                get_price.setText("已还清");
                get_price.setBackgroundColor(Color.parseColor("#999999"));
                get_price.setFocusable(false);
                break;
            default:
                break;
        }
        getRepaymentDetail();

    }

    /**
     * 理财超市--借款详情
     */
    private void getRepaymentDetail() {
        JSONObject object = new JSONObject();
        try {
            object.put("deal_id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "getRepaymentDetail: " + jjjjj);
        Log.d(TAG, "getRepaymentDetail: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().getRepaymentDetail(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<FinancialRepaymentDetailBean>(this) {
            @Override
            protected void onHandleSuccess(FinancialRepaymentDetailBean financialRepaymentDetailBean) {
                dealId = financialRepaymentDetailBean.getDeal_detail().getDeal_id();
                isNew = financialRepaymentDetailBean.getDeal_detail().getIs_new();
                useEcv = financialRepaymentDetailBean.getDeal_detail().getUse_ecv();
                useRate = financialRepaymentDetailBean.getDeal_detail().getUse_rate();
                name = financialRepaymentDetailBean.getDeal_detail().getName();
                rateContent = financialRepaymentDetailBean.getDeal_detail().getRate();
                time = (financialRepaymentDetailBean.getDeal_detail().getRepay_time() + financialRepaymentDetailBean.getDeal_detail().getRepay_time_type());
                repayment = financialRepaymentDetailBean.getDeal_detail().getLoantype_format();
                needMoney = financialRepaymentDetailBean.getDeal_detail().getRemain_money();
                needMoneys = financialRepaymentDetailBean.getDeal_detail().getBorrow_amount();
                totalMoney = financialRepaymentDetailBean.getDeal_detail().getUser_available_money();
                //最上层
                rate.setText(financialRepaymentDetailBean.getDeal_detail().getRate() + "%");
                progress.setCurrentCount(Float.valueOf(financialRepaymentDetailBean.getDeal_detail().getProgress_point()));
                need_money.setText(financialRepaymentDetailBean.getDeal_detail().getRemain_money());
                repay_time.setText(financialRepaymentDetailBean.getDeal_detail().getRepay_time() + financialRepaymentDetailBean.getDeal_detail().getRepay_time_type());
                min_loan_money.setText(financialRepaymentDetailBean.getDeal_detail().getMin_loan_money() + "元");
                List list = new ArrayList();
                if(financialRepaymentDetailBean.getBest_select_banner()!=null) {
                    for (int j = 0; j < financialRepaymentDetailBean.getBest_select_banner().size(); j++) {
                        list.add(financialRepaymentDetailBean.getBest_select_banner().get(j).getTitle());
                    }
                }
                marqueeView.setTextArray(list);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        marqueeView.resume();
                    }
                }, 1000);
                //中间一层
                need_money2.setText(financialRepaymentDetailBean.getDeal_detail().getBorrow_amount() + "元");
                nianlilv.setText(financialRepaymentDetailBean.getDeal_detail().getDate_of_value());
                loantype_format.setText(financialRepaymentDetailBean.getDeal_detail().getLoantype_format());
                jiezhi.setText(DateUtils.time(String.valueOf(financialRepaymentDetailBean.getDeal_detail().getEnd_time())));
                //最后一层
                touziRenshuFragment = TouziRenshuFragment.newInstance(financialRepaymentDetailBean.getDeal_detail().getDeal_id());
                projectDetilsFragment = ProjectDetilsFragment.newInstance(financialRepaymentDetailBean.getDeal_detail().getDeal_id());
                insideUserInfoFragment = InsideUserInfoFragment.newInstance(financialRepaymentDetailBean.getDeal_detail().getDeal_id());
                fengxianControFragment = RiskControFragment.newInstance(financialRepaymentDetailBean.getDeal_detail().getDeal_id());
                if (type.equals("1")) {
                    mList.add(insideUserInfoFragment);//项目详情
                    mList.add(projectDetilsFragment);//借款合同/照片
                    mList.add(touziRenshuFragment);//投资人数
                    MyindentViewpagerAdapter adapter = new MyindentViewpagerAdapter(supportFragmentManager, YouBiaoActivity.this, mList, Arrays.asList(mStringtitle));
                    mViewpagerMyindent.setAdapter(adapter);
                    mTablayoutMyindent.setupWithViewPager(mViewpagerMyindent);
                    mViewpagerMyindent.setCurrentItem(0);
                } else {
                    mList.add(insideUserInfoFragment);
                    mList.add(projectDetilsFragment);
                    mList.add(touziRenshuFragment);
                    mList.add(fengxianControFragment);//还款详情
                    MyindentViewpagerAdapter adapter = new MyindentViewpagerAdapter(supportFragmentManager, YouBiaoActivity.this, mList, Arrays.asList(mStringList));
                    mViewpagerMyindent.setAdapter(adapter);
                    mTablayoutMyindent.setupWithViewPager(mViewpagerMyindent);
                    mViewpagerMyindent.setCurrentItem(0);
                }

            }


        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhuce_back:
                finish();
                break;
            case R.id.jisuan:
//
                doCount();

                break;
            case R.id.get_price:
                if (UserManager.getInstance().isLogin()) {
                    Log.d(TAG, "onClick: " + UserInfo.getInstance().getUserId());
                    doRePay();
                } else {
                    showNoLogin();
                }
//
                break;


        }
    }

    @SuppressLint("WrongConstant")
    private void showNoPassId() {

        View peicepop = LayoutInflater.from(YouBiaoActivity.this).inflate(R.layout.nopassid, null);
        ImageView popup_close2 = peicepop.findViewById(R.id.jisuan_close);
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

        popupWindows.showAtLocation(peicepop, Gravity.CENTER, 0, 0);
//        popupWindows.showAsDropDown(peicepop,0,0,Gravity.CENTER);

        // 产生背景变暗效果
        WindowManager.LayoutParams ayoutParams = YouBiaoActivity.this.getWindow()
                .getAttributes();
        ayoutParams.alpha = 0.4f;
        YouBiaoActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        YouBiaoActivity.this.getWindow().setAttributes(ayoutParams);

        popupWindows.update();
        popupWindows.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = YouBiaoActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                YouBiaoActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                YouBiaoActivity.this.getWindow().setAttributes(lp);
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
                startActivity(new Intent(YouBiaoActivity.this, TrurhNameActivity.class));
            }
        });


    }


    private void doRePay() {
        Intent intent = new Intent(YouBiaoActivity.this, TouBiaoMoneyActivity.class);
        intent.putExtra("isNew", isNew);
        intent.putExtra("useEcv", useEcv);
        intent.putExtra("useRate", useRate);
        intent.putExtra("name", name);
        intent.putExtra("rateContent", rateContent);
        intent.putExtra("time", time);
        intent.putExtra("repayment", repayment);
        intent.putExtra("needMoney", needMoney);
        intent.putExtra("needMoneys", needMoneys);
        intent.putExtra("totalMoney", totalMoney);
        intent.putExtra("dealId", dealId);
        startActivity(intent);

    }

    @SuppressLint("WrongConstant")
    private void showNoLogin() {
        View peicepop = LayoutInflater.from(this).inflate(R.layout.nologin_pop, null);
        ImageView popup_close2 = peicepop.findViewById(R.id.close);
        Button gologin = peicepop.findViewById(R.id.pop_know);
        popupWindows = new PopupWindow(peicepop, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindows.setBackgroundDrawable(new BitmapDrawable());
        popupWindows.setFocusable(true);// 取得焦点
        //点击外部消失
        popupWindows.setOutsideTouchable(true);
        //设置可以点击
        popupWindows.setTouchable(true);
        popupWindows.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);

        popupWindows.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindows.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        // 产生背景变暗效果
        WindowManager.LayoutParams ayoutParams = this.getWindow()
                .getAttributes();
        ayoutParams.alpha = 0.4f;
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.getWindow().setAttributes(ayoutParams);

        popupWindows.update();
        popupWindows.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = YouBiaoActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                YouBiaoActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                YouBiaoActivity.this.getWindow().setAttributes(lp);
            }
        });


        popup_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindows.dismiss();
            }
        });

        gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                popupWindows.dismiss();
                startActivity(new Intent(YouBiaoActivity.this, LoginActivity.class));
                finish();


            }
        });


    }


    @SuppressLint("WrongConstant")
    private void doCount() {
        View peicepop = LayoutInflater.from(this).inflate(R.layout.jisuan_pop, null);
        final Dialog dialog = new Dialog(this, R.style.custom_dialog2);
        dialog.setContentView(peicepop);
        ImageView close = peicepop.findViewById(R.id.close);
        EditText input = peicepop.findViewById(R.id.input);
        TextView date = peicepop.findViewById(R.id.date);
        earnings = peicepop.findViewById(R.id.money);
        WindowManager manager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        date.setText(time);
        params.width = display.getWidth() - 200;
        dialog.getWindow().setAttributes(params);
        dialog.show();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getProfit(s.toString());
            }
        });
    }

    /**
     * 收益计算
     */
    private void getProfit(String money) {
        JSONObject object = new JSONObject();
        try {
            object.put("id", id);
            object.put("money", money);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "getProfit: " + jjjjj);
        Log.d(TAG, "getProfit: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().getProfit(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<GetPrpfitBean>(this) {
            @Override
            protected void onHandleSuccess(GetPrpfitBean getPrpfitBean) {
                earnings.setText(getPrpfitBean.getProfit()+"元");
            }


        });

    }
}