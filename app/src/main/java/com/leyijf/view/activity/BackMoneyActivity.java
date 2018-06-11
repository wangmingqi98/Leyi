package com.leyijf.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.leyijf.R;
import com.leyijf.adapter.BankMoneyDetilsAdapter;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.BackMoney;
import com.leyijf.bean.UserInfo;
import com.leyijf.util.OkHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 回款页面
 */

public class BackMoneyActivity extends BaseActivity implements View.OnClickListener {

    private ImageView zhuce_back;
    private TextView xieyi;
    private RelativeLayout title_zixun;
    private ListView back_detils_list;
    private BankMoneyDetilsAdapter bankMoneyDetilsAdapter;
    private TextView xiangmu_name;
    private TextView jiekuanjine;
    private TextView nianlilv;
    private TextView touziqixian;
    private TextView guanlifei;
    private TextView lixiguanlifei;
    private TextView yuqiweiyue;
    private TextView huankuanfangshi;
    private BackMoney backMoney;
    private String name;
    private String rate_format;
    private String money_format;
    private String repay_time_format;
    private String id;


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
        return R.layout.activity_back_money;
    }

    private void initView() {

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        name = bundle.getString("name");
        rate_format = bundle.getString("rate_format");
        money_format = bundle.getString("money_format");
        repay_time_format = bundle.getString("repay_time_format");
        id = bundle.getString("id");

        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        xieyi = (TextView) findViewById(R.id.xieyi);
        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);
        back_detils_list = (ListView) findViewById(R.id.back_detils_list);

        zhuce_back.setOnClickListener(this);



        Map map=new HashMap();
        map.put("act","v2_deals_loan_repay_list");
        map.put("deal_id", id);
        map.put("email", UserInfo.getInstance().getUserPhone());
        map.put("pwd",UserInfo.getInstance().getUserPwd());

        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();

                Log.e("--huikuan-",string+"");
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(string);
                    int response_code = jsonObject.getInt("response_code");
                    if(response_code==1){

                        Gson gson=new Gson();
                        backMoney = gson.fromJson(string, BackMoney.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                View inflate = LayoutInflater.from(BackMoneyActivity.this).inflate(R.layout.backheager, null);
                                xiangmu_name= (TextView) inflate.findViewById(R.id.xiangmu_name);
                                jiekuanjine= (TextView) inflate.findViewById(R.id.jiekuanjine);
                                nianlilv= (TextView) inflate.findViewById(R.id.nianlilv);
                                touziqixian= (TextView) inflate.findViewById(R.id.touziqixian);
                                guanlifei= (TextView) inflate.findViewById(R.id.guanlifei);
                                lixiguanlifei= (TextView) inflate.findViewById(R.id.lixiguanlifei);
                                yuqiweiyue= (TextView) inflate.findViewById(R.id.yuqiweiyue);
                                huankuanfangshi= (TextView)inflate.findViewById(R.id.huankuanfangshi);
                                xiangmu_name.setText(name);
                                jiekuanjine.setText(money_format);
                                nianlilv.setText(rate_format);
                                touziqixian.setText(repay_time_format);
                                guanlifei.setText("¥0.00");
                                lixiguanlifei.setText("¥0.00");
                                yuqiweiyue.setText("¥0.00");
                                huankuanfangshi.setText("付息还本");
                                back_detils_list.addHeaderView(inflate);

                                bankMoneyDetilsAdapter = new BankMoneyDetilsAdapter(BackMoneyActivity.this, backMoney.getObjects().getLoan_repay_list());
                                back_detils_list.setAdapter(bankMoneyDetilsAdapter);
                                bankMoneyDetilsAdapter.notifyDataSetChanged();



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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhuce_back:
                finish();
                break;
            case R.id.xieyi:
//
                break;


        }
    }


}
