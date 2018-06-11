package com.leyijf.view.fragment.fragment_touzi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.leyijf.R;
import com.leyijf.adapter.DealsLoanRepayAdapter;
import com.leyijf.base.BaseFragment;
import com.leyijf.bean.DealsLoanRepayBean;
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
 * 还款详情
 * A simple {@link Fragment} subclass.
 */
public class RiskControFragment extends BaseFragment {
    public static final String TAG = "FengxianControFragment";
    private String id;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<DealsLoanRepayBean.LoanRepayListBean> loan_repay_list = new ArrayList<>();
    private DealsLoanRepayAdapter adapter;

    public RiskControFragment() {
    }


    public static final RiskControFragment newInstance(String id)
    {
        RiskControFragment fragment = new RiskControFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        fragment.setArguments(bundle);

        return fragment ;
    }




    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        id = getArguments().getString("id");
        recyclerView = view.findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new DealsLoanRepayAdapter(R.layout.item_dealsloanrepay,loan_repay_list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * 获取还款详情
     */
    @Override
    protected void initData() {
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
        Log.d(TAG, "initData: " + jjjjj);
        Log.d(TAG, "initData: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().getDealsloanRepay(requestData);
        observable.compose(compose(RiskControFragment.this.bindToLifecycle())).subscribe(new BaseObserver<DealsLoanRepayBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(DealsLoanRepayBean dealsLoanRepayBean) {
                List<DealsLoanRepayBean.LoanRepayListBean> loanRepayListBeans = new ArrayList<>();
                if (dealsLoanRepayBean.getLoan_repay_list()!=null) {
                    if (dealsLoanRepayBean.getLoan_repay_list().size() > 0) {
                        loanRepayListBeans.addAll(dealsLoanRepayBean.getLoan_repay_list());
                        loan_repay_list.addAll(loanRepayListBeans);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

        });
    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_fengxian_contro;
    }

}
