package com.leyi.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leyi.R;
import com.leyi.adapter.MyToubiaoAdapter;
import com.leyi.base.BaseActivity;
import com.leyi.bean.RePayUser;
import com.leyi.bean.ToubiaoDetilsBean;
import com.leyi.bean.UserInfo;
import com.leyi.util.LogUtils;
import com.leyi.util.OkHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 投标详情
 */
public class YouBiaoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
    private ListView toubiao;
    private ImageView jisuan;
    private TextView get_price;
    private FragmentManager supportFragmentManager;
    private MyToubiaoAdapter myToubiaoAdapter;
    private PopupWindow popupWindows;
    private ToubiaoDetilsBean toubiaoDetilsBean;
    private RePayUser rePayUser;


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
        toubiao = (ListView) findViewById(R.id.toubiao);
        supportFragmentManager = getSupportFragmentManager();




        jisuan = (ImageView) findViewById(R.id.jisuan);
        jisuan.setOnClickListener(this);
        get_price = (TextView) findViewById(R.id.get_price);
        get_price.setOnClickListener(this);
//        WebView webView= (WebView) findViewById(R.id.web);

        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        String id = bundle.getString("id");

        Map<String,String> map=new HashMap<>();
        map.put("act","deal");
        map.put("id",id);

        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(YouBiaoActivity.this,"暂无数据！",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String  detilsRespones= response.body().string();
                LogUtils.e("deal--------",detilsRespones);

                try {
                    JSONObject jsonObject=new JSONObject(detilsRespones);
                    if(jsonObject.getInt("response_code")==1){
                        JSONObject jsonObject1 = jsonObject.getJSONObject("objects").getJSONObject("deal");
                        toubiaoDetilsBean = new ToubiaoDetilsBean(jsonObject1.getString("id"),
                                jsonObject1.getString("name"),jsonObject1.getString("description"),jsonObject1.getString("borrow_amount"),
                                jsonObject1.getString("min_loan_money"),jsonObject1.getString("repay_time") ,jsonObject1.getString("repay_time_type"),jsonObject1.getString("repay_start_time"),jsonObject1.getString("rate"),
                                jsonObject1.getString("loantype_format"),jsonObject1.getString("need_money"),jsonObject1.getString("buy_count"),
                                jsonObject1.getInt("load_money"),jsonObject1.getInt("progress_point"),jsonObject1.getString("attachment"),jsonObject1.getString("risk_security"),jsonObject1.getString("remain_time_format"),jsonObject1.getString("deal_status"));

                        JSONObject user = jsonObject1.getJSONObject("user");
                        rePayUser = new RePayUser(jsonObject1.getString("id"),user.getString("user_name"),user.getString("real_name"),user.getString("sex"),user.getString("mobile"),user.getString("byear"),user.getString("graduation"),user.getString("marriage")
                                ,user.getString("hashouse"),user.getString("houseloan"),user.getString("hascar"),user.getString("carloan"),user.getString("address"));



                        runOnUiThread(new Runnable() {
    @Override
    public void run() {
        myToubiaoAdapter = new MyToubiaoAdapter(YouBiaoActivity.this,supportFragmentManager,toubiaoDetilsBean,rePayUser);
        toubiao.setAdapter(myToubiaoAdapter);
        if(toubiaoDetilsBean.getDeal_status().equals("0")){
            get_price.setText("等待中");
            get_price.setClickable(false);
        }else if(toubiaoDetilsBean.getDeal_status().equals("1")){
            get_price.setText("立即投资");
            get_price.setClickable(true);
        }else if(toubiaoDetilsBean.getDeal_status().equals("2")){
            get_price.setText("已满标");
            get_price.setClickable(false);
        }else if(toubiaoDetilsBean.getDeal_status().equals("3")){
            get_price.setText("流标");
            get_price.setClickable(false);
        }else if(toubiaoDetilsBean.getDeal_status().equals("4")){
            get_price.setText("还款中");
            get_price.setClickable(false);
        }else if(toubiaoDetilsBean.getDeal_status().equals("5")){
            get_price.setText("已还清");
            get_price.setClickable(false);
        }




    }
});

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });








//        webView.loadUrl(app_url);
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });


//

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
                if(UserInfo.getInstance().getUserId()!=null){
                    if(UserInfo.getInstance().getId_passed()!=0){
                        doRePay();
                    }else {
//                        startActivity(new Intent(YouBiaoActivity.this,BindBankCardActivity.class));
                        showNoPassId();


                    }


                }else {
                    showNoLogin();
                }
//


                break;


        }
    }

    @SuppressLint("WrongConstant")
    private void showNoPassId() {

        View  peicepop = LayoutInflater.from(YouBiaoActivity.this).inflate(R.layout.nopassid,null);
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

        popupWindows.showAtLocation(peicepop, Gravity.CENTER,0,0);
//        popupWindows.showAsDropDown(peicepop,0,0,Gravity.CENTER);

        // 产生背景变暗效果
        WindowManager.LayoutParams ayoutParams =YouBiaoActivity. this.getWindow()
                .getAttributes();
        ayoutParams.alpha = 0.4f;
        YouBiaoActivity. this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
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
                startActivity(new Intent(YouBiaoActivity.this,TrurhNameActivity.class));
            }
        });



    }




    private void doRePay() {
        Intent intent=new Intent(YouBiaoActivity.this,TouBiaoMoneyActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("borrow_amount",toubiaoDetilsBean.getBorrow_amount()+"");
        bundle.putString("need_money",toubiaoDetilsBean.getNeed_money()+"");
        bundle.putString("id",toubiaoDetilsBean.getId()+"");
        bundle.putString("loantype_format",toubiaoDetilsBean.getLoantype_format()+"");
        bundle.putString("rate",toubiaoDetilsBean.getRate()+"");
        bundle.putString("repay_time",toubiaoDetilsBean.getRepay_time()+"");
        bundle.putString("repay_time_type",toubiaoDetilsBean.getRepay_time_type()+"");
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @SuppressLint("WrongConstant")
    private void showNoLogin() {
        View  peicepop = LayoutInflater.from(this).inflate(R.layout.nologin_pop,null);
        ImageView   popup_close2 = peicepop.findViewById(R.id.popup_close2);
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
        popupWindows.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER,0,0);
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
                startActivity(new Intent(YouBiaoActivity.this,LoginActivity.class));


            }
        });


    }


    @SuppressLint("WrongConstant")
    private void doCount() {
        View  peicepop = LayoutInflater.from(this).inflate(R.layout.jisuan_pop,null);
        ImageView   popup_close2 = peicepop.findViewById(R.id.jisuan_close);
        EditText shuru_ed = peicepop.findViewById(R.id.jine_shuru);
        final TextView qixian = peicepop.findViewById(R.id.touziqixian);
        if(toubiaoDetilsBean.getRepay_time_type().equals("0")){
            qixian.setText(toubiaoDetilsBean.getRepay_time()+"天");
        }else {
            qixian.setText(toubiaoDetilsBean.getRepay_time()+"月");
        }
        final TextView shouyi = peicepop.findViewById(R.id.yujishouyi);
       final PopupWindow popupWindows = new PopupWindow(peicepop, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindows.setBackgroundDrawable(new BitmapDrawable());
        popupWindows.setFocusable(true);// 取得焦点
        //点击外部消失
        popupWindows.setOutsideTouchable(true);
        //设置可以点击
        popupWindows.setTouchable(true);
        popupWindows.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);

        popupWindows.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindows.showAtLocation(YouBiaoActivity.this.getWindow().getDecorView(), Gravity.CENTER,0,0);
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

        shuru_ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                if(!TextUtils.isEmpty(s.toString())&&TextUtils.isDigitsOnly(s1)){

                    if(toubiaoDetilsBean.getRepay_time_type().equals("0")){

                        float rate = Float.parseFloat(toubiaoDetilsBean.getRate());
                        float rates = rate / 100;
                        float i = Float.parseFloat(s1) * rates;
                        float i1 = i / 365 * Float.parseFloat(toubiaoDetilsBean.getRepay_time());

                        BigDecimal b  =   new BigDecimal(i1);
                        float v = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();


                        shouyi.setText(v+"元");
                    }else {

                        float rate = Float.parseFloat(toubiaoDetilsBean.getRate());
                        float rates = rate / 100;
                        float v = Float.parseFloat(s1) * rates;
                        float v1 = v / 12 * Float.parseFloat(toubiaoDetilsBean.getRepay_time());
                        BigDecimal b  =   new BigDecimal(v1);
                        float v2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();

                        shouyi.setText(v2+"元");


                    }





                }



            }
        });




    }


}
