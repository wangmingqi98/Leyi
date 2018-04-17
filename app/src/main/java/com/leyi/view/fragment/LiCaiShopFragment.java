package com.leyi.view.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.leyi.R;
import com.leyi.base.BaseFragment;
import com.leyi.view.fragment.licaishop_fragment.CertificationFragment;
import com.leyi.view.fragment.licaishop_fragment.NewsAdapter;
import com.leyi.view.fragment.licaishop_fragment.NoviceFragment;
import com.leyi.view.fragment.licaishop_fragment.SupplylinkFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiCaiShopFragment extends BaseFragment {
    public ViewPager viewpager;
    private TabLayout my_tab;
    private RelativeLayout title_zixun;
    private LinearLayout licai;

    public LiCaiShopFragment() {
        // Required empty public constructor
    }




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
        List tabs = new ArrayList<>();
        tabs.add("新手专享");
        tabs.add("货抵贷");
        tabs.add("产业链");
        List fragments = new ArrayList<>();
        fragments.add(new NoviceFragment());
        fragments.add(new CertificationFragment());
        fragments.add(new SupplylinkFragment());

        NewsAdapter newsAdapter = new NewsAdapter(this.getChildFragmentManager(),tabs,fragments,getActivity());

        viewpager.setAdapter(newsAdapter);
        my_tab.setupWithViewPager(viewpager);





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
