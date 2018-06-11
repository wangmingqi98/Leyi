package com.leyijf.view.fragment.licaishop_fragment;


import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.leyijf.R;
import com.leyijf.adapter.NoviceListAdapter;
import com.leyijf.base.BaseFragment;
import com.leyijf.bean.InItBean;
import com.leyijf.bean.RepaymentListBean;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.util.Aes128;
import com.leyijf.weight.MyPopupWindow;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 * 新手理财
 */
public class NoviceFragment extends BaseFragment implements View.OnClickListener{

    public static final String TAG = "NoviceFragment";
    private LinearLayout linear;
    int a=1;
    private PopupWindow popupWindow;
    private TextView paixu_name;
    private RadioGroup mRgShopStorePop;
    private View inflate;
    private RecyclerView novice_list;
    private List<InItBean> inItBeanList;
    private SmartRefreshLayout smart;
    private LinearLayoutManager linearLayoutManager;
    int page=1;
    private NoviceListAdapter noviceListAdapter;
    private String cateId;
    private String orderType = "0";


    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.linear:
            if(a==1){
                showPop(inflate);
                a=0;
            }else if(a==0){
                popupWindow.dismiss();
            }
            break;
    }



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        cateId = bundle.getString("id");
        Log.d(TAG, "onActivityCreated: "+cateId);
        doHttp(orderType);
    }

    @Override
    public void onResume() {
        super.onResume();
        doHttp(orderType);

    }

    private void initPaixuView(View inflate) {

        mRgShopStorePop = inflate.findViewById(R.id.rg_shop_store_pop);
            LinearLayout mLine = inflate.findViewById(R.id.line);
            final RadioButton mRbStoreFront = inflate.findViewById(R.id.rb_storefront);
            final RadioButton mRbMember = inflate.findViewById(R.id.rb_member);
            final RadioButton mRbHistory = inflate.findViewById(R.id.rb_history);
            mRbStoreFront.setChecked(true);
        mRgShopStorePop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressWarnings("Since15")
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_storefront:
                        //年利率由高至低
                        orderType = "1";
                        paixu_name.setText(mRbStoreFront.getText().toString());
                        doHttp(orderType);
                        break;
                    case R.id.rb_member:
                        //投资期限由短至长
                        orderType = "2";
                        paixu_name.setText(mRbMember.getText().toString());
                        doHttp(orderType);
                        break;
                    case R.id.rb_history:
                        //起投金额由小至大
                        orderType = "3";
                        paixu_name.setText(mRbHistory.getText().toString());
                        doHttp(orderType);
                        break;
                }
                popupWindow.dismiss();

            }
        });
            mLine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                }
            });
        }



    @Override
    protected void initView(View view) {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linear = view.findViewById(R.id.linear);
        paixu_name = view.findViewById(R.id.paixu_name);
        smart = view.findViewById(R.id.smart);
//        smart.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
//        smart.setRefreshHeader(new ClassicsHeader(getActivity()));
        linear.setOnClickListener(this);
        initPopdata();
        novice_list = view.findViewById(R.id.novice_list);
        novice_list.setLayoutManager(linearLayoutManager);
        doHttp(orderType);
        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page++;
                doHttp(orderType);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                doHttp(orderType);
            }
        });

    }

    private void initPopdata() {
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.tankuang_pop, null);
        initPaixuView(inflate);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_novice;
    }


    private void showPop(View view) {
        popupWindow = new MyPopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.setFocusable(true);

        // 设置点击其他地方 就消失 (只设置这个，没有效果) 必须设置背景
        popupWindow.setOutsideTouchable(true);

        popupWindow.setTouchable(true);

        popupWindow.setAnimationStyle(R.style.AnimBottom); // 设置动画

        popupWindow.showAsDropDown(linear, 0, 0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
              a=1;
            }
        });
    }


    /**
     * 理财超市--获取借款列表
     */
    private void doHttp(String orderType) {
        JSONObject object = new JSONObject();
        try {
            object.put("order_type",orderType);
            object.put("type",cateId);
            object.put("page",page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initRapayment: " + jjjjj);
        Log.d(TAG, "initRapayment: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().getRepaymentList(requestData);
        observable.compose(compose(NoviceFragment.this.bindToLifecycle())).subscribe(new BaseObserver<RepaymentListBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(RepaymentListBean repaymentListBean) {
                List<RepaymentListBean.DealsListBean> deals_list = new ArrayList<>();
                List<RepaymentListBean.DealsListBean> dealslist = new ArrayList<>();
                if (repaymentListBean.getDeals_list()!=null) {
                    if (repaymentListBean.getDeals_list().size() > 0) {
                        for (int i = 0; i < repaymentListBean.getDeals_list().size(); i++) {
                            if (!repaymentListBean.getDeals_list().get(i).getDeal_status().equals("0") &&
                                    !repaymentListBean.getDeals_list().get(i).getDeal_status().equals("2") &&
                                    !repaymentListBean.getDeals_list().get(i).getDeal_status().equals("3")) {
                                RepaymentListBean.DealsListBean dealsListBean = repaymentListBean.getDeals_list().get(i);
                                dealslist.add(dealsListBean);
                            }
                        }
                        deals_list.clear();
                        deals_list.addAll(dealslist);
                        noviceListAdapter = new NoviceListAdapter(R.layout.home_threeitem,deals_list);
                        novice_list.setAdapter(noviceListAdapter);
                        noviceListAdapter.notifyDataSetChanged();
                        smart.finishRefresh();
                        smart.finishLoadMore();

                    } else {
                        smart.finishRefresh();
                        smart.finishLoadMore();
                    }
                }
            }

        });
    }

}
