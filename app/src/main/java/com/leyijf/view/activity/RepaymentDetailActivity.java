package com.leyijf.view.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leyijf.R;
import com.leyijf.adapter.RapaymentDetailAdapter;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.RepaymentDetailBean;
import com.leyijf.bean.UserInfo;
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
 * 我的投资--还款详情
 * Created by wmq on 2018/5/7.
 */

public class RepaymentDetailActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "RepaymentDetailActivity";
    private RecyclerView recyclerView;
    private ImageView back;
    private Intent intent;
    private String id;
    private View headerTop,header;
    private RapaymentDetailAdapter adapter;
    private List<RepaymentDetailBean.LoanListBean> loan_list = new ArrayList<>();
    TextView titleCount,money,time,benxi_money,penalty,income;
    ImageView repaymentType;
    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        intent = getIntent();
        id = intent.getStringExtra("id");
        back = (ImageView) findViewById(R.id.zhuce_back);
        back.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
         headerTop = LayoutInflater.from(this).inflate(R.layout.item_header_top,null);
         titleCount = headerTop.findViewById(R.id.title_count);
         money = headerTop.findViewById(R.id.money);
         time = headerTop.findViewById(R.id.time);
         benxi_money = headerTop.findViewById(R.id.benxi_money);
         penalty = headerTop.findViewById(R.id.penalty);
         income = headerTop.findViewById(R.id.income);
         repaymentType = headerTop.findViewById(R.id.repayment_type);
         header = LayoutInflater.from(this).inflate(R.layout.item_header,null);
         adapter = new RapaymentDetailAdapter(R.layout.item_rapayment,loan_list);
         adapter.addHeaderView(header);
         adapter.addHeaderView(headerTop);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        initRapayment();


    }

    /**
     * 还款详情接口
     */
    private void initRapayment() {
        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String pwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email", email);
            object.put("pwd", pwd);
            object.put("deal_id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initRapayment: " + jjjjj);
        Log.d(TAG, "initRapayment: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().repaymentDetail(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<RepaymentDetailBean>(this) {
            @Override
            protected void onHandleSuccess(RepaymentDetailBean repaymentDetailBean) {
                if(repaymentDetailBean.getLoan_list().size()!=0){
                    loan_list.addAll(repaymentDetailBean.getLoan_list());
                    titleCount.setText("第"+repaymentDetailBean.getCurrent_l_key_index().getL_key_index()+"期收益(元)");
                    money.setText(repaymentDetailBean.getCurrent_l_key_index().getMonth_repay_money());
                    time.setText("实际还款日:"+repaymentDetailBean.getCurrent_l_key_index().getRepay_day_format());
                    benxi_money.setText(repaymentDetailBean.getCurrent_l_key_index().getTotal_principal_interest()+"");
                    penalty.setText(repaymentDetailBean.getCurrent_l_key_index().getTotal_impose_money());
                    income.setText(repaymentDetailBean.getCurrent_l_key_index().getTotal_expected_earnings()+"");
                    if(repaymentDetailBean.getCurrent_l_key_index().getHas_repay().equals("0")){//待还款
                        repaymentType.setImageResource(R.drawable.daihuankuan);
                    }else {//还款中的状态
                        switch (repaymentDetailBean.getCurrent_l_key_index().getStatus()) {
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
                }

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
        }
    }
}
