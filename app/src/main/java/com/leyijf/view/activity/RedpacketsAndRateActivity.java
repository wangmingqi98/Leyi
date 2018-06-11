package com.leyijf.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leyijf.R;
import com.leyijf.adapter.RedPacketsAdapter;
import com.leyijf.adapter.UsesRateAdapter;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.RedPaketsBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.bean.UsesRateBean;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * 可使用红包或者加息劵页面
 * Created by wmq on 2018/5/23.
 */

public class RedpacketsAndRateActivity extends BaseActivity implements View.OnClickListener{
    public static final String TAG = "RedpacketsAndRateActivity" ;
    private Intent intent;
    private ImageView back;
    private TextView title;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RedPacketsAdapter redPacketAdapter;
    private UsesRateAdapter usesRateAdapter;
    private List<RedPaketsBean.VouchersBean> vouchers = new ArrayList<>();
    private List<UsesRateBean.RatesBean> rates = new ArrayList<>();
    private ImageView noData;
    String type;
    String dealId,money;
    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        intent = getIntent();
        linearLayoutManager = new LinearLayoutManager(this);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        title = (TextView) findViewById(R.id.title);
        noData = (ImageView) findViewById(R.id.no_data);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        back.setOnClickListener(this);
        type = intent.getStringExtra("type");
        dealId = intent.getStringExtra("dealId");
        money = intent.getStringExtra("bid_money");
        if(type.equals("1")){
            title.setText("我的红包");
            getRedPackets();
        }else {
            title.setText("我的加息券");
            getRates();

        }

    }

    /**
     * 获取加息券
     */
    @SuppressLint("LongLogTag")
    private void getRates() {
        JSONObject object = new JSONObject();
        try {
            object.put("email", UserManager.getInstance().getLoginUser().getUser_mobile_referee());
            object.put("pwd", UserInfo.getInstance().getUserPwd());
            object.put("deal_id", dealId);
            object.put("invest_money", money);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initVerify: " + jjjjj);
        Log.d(TAG, "initVerify: " + object.toString());

        Observable observable = RetrofitFactory.getInstance().getRate(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<UsesRateBean>(this) {
            @Override
            protected void onHandleSuccess(UsesRateBean usesRateBean) {
                if(usesRateBean.getRates().size()>0){
                    noData.setVisibility(View.GONE);
                    rates.addAll(usesRateBean.getRates());
                    usesRateAdapter = new UsesRateAdapter(R.layout.myadd_carviewitem,rates);
                    recyclerView.setAdapter(usesRateAdapter);
                    usesRateAdapter.notifyDataSetChanged();
                    usesRateAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent();
                            intent.putExtra("rate_id",rates.get(position).getRate_id());
                            setResult(100,intent);
                            finish();
                        }
                    });
                }else {
                    noData.setVisibility(View.VISIBLE);
                }

            }

        });
    }

    /**
     * 获取可用红包
     */
    @SuppressLint("LongLogTag")
    private void getRedPackets() {
        JSONObject object = new JSONObject();
        try {
            object.put("email", UserManager.getInstance().getLoginUser().getUser_mobile_referee());
            object.put("pwd", UserInfo.getInstance().getUserPwd());
            object.put("deal_id", dealId);
            object.put("invest_money", money);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initVerify: " + jjjjj);
        Log.d(TAG, "initVerify: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().getRedPakets(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<RedPaketsBean>(this) {
            @Override
            protected void onHandleSuccess(RedPaketsBean redPaketsBean) {
                if(redPaketsBean.getVouchers().size()>0){
                    noData.setVisibility(View.GONE);
                    vouchers.addAll(redPaketsBean.getVouchers());
                    redPacketAdapter = new RedPacketsAdapter(R.layout.myadd_carviewitem,vouchers);
                    recyclerView.setAdapter(redPacketAdapter);
                    redPacketAdapter.notifyDataSetChanged();
                    redPacketAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent();
                            if(vouchers.get(position).getName().equals("邀请奖励")){
                                intent.putExtra("voucher_id",vouchers.get(position).getVoucher_id()+vouchers.get(position).getUse_limit()+vouchers.get(position).getUser_id());
                            }else {
                                intent.putExtra("voucher_id",vouchers.get(position).getVoucher_id());
                            }
                            setResult(100,intent);
                            finish();
                        }
                    });
                }else {
                    noData.setVisibility(View.VISIBLE);
                }
            }


        });

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_redandrate;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
