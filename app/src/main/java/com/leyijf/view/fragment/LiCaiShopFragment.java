package com.leyijf.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.leyijf.R;
import com.leyijf.base.BaseFragment;
import com.leyijf.bean.RepaymentClasificationBean;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.view.fragment.licaishop_fragment.NewsAdapter;
import com.leyijf.view.fragment.licaishop_fragment.NoviceFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * 理财超市
 * A simple {@link Fragment} subclass.
 */
public class LiCaiShopFragment extends BaseFragment {
    public ViewPager viewpager;
    private TabLayout my_tab;
    private RelativeLayout title_zixun;
    private LinearLayout licai;
    public static final String TAG = "LiCaiShopFragment";
    private List<RepaymentClasificationBean.CateInfoBean> tabs = new ArrayList<>();
    private List fragments;
    private NoviceFragment fragment;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        viewpager = view.findViewById(R.id.viewpager);
        my_tab = view.findViewById(R.id.my_tab);
        title_zixun = view.findViewById(R.id.title_zixun);
        licai = view.findViewById(R.id.licai);
        licai.setPadding(0,getStatusBarHeight(getActivity()),0,0);
        Log.e("aaa",getStatusBarHeight(getActivity())+"aaa");
        getTabTitle();
    }

    /**
     * 获取理财超市标题接口
     */
    private void getTabTitle() {
        Observable observable = RetrofitFactory.getInstance().getRepaymentClassification();
        observable.compose(compose(LiCaiShopFragment.this.bindToLifecycle())).subscribe(new BaseObserver<RepaymentClasificationBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(RepaymentClasificationBean repaymentClasificationBean) {
                tabs.addAll(repaymentClasificationBean.getCate_info());
                fragments = new ArrayList<>();
                for(int i = 0;i<tabs.size();i++){
                    fragment = new NoviceFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("id",tabs.get(i).getCate_id());
                    fragment.setArguments(bundle);
                    fragments.add(fragment);
                }
                Log.d(TAG, "onHandleSuccess: "+tabs.size());
                NewsAdapter newsAdapter = new NewsAdapter(LiCaiShopFragment.this.getChildFragmentManager(),tabs,fragments,getActivity());
                viewpager.setAdapter(newsAdapter);
                my_tab.setupWithViewPager(viewpager);
            }


        });
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_li_cai_shop;
    }




}
