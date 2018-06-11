package com.leyijf.view.fragment;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.leyijf.R;
import com.leyijf.base.BaseFragment;
import com.leyijf.view.activity.AboutUsActivity;
import com.leyijf.view.activity.ActiveActivity;
import com.leyijf.view.activity.CompanyNewsActivity;
import com.leyijf.view.activity.HelpActivity;
import com.leyijf.view.activity.SafeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends BaseFragment implements View.OnClickListener {


    private RelativeLayout title_zixun;
    private LinearLayout company_news;
    private LinearLayout actives;
    private LinearLayout about_us;
    private LinearLayout help;
    private LinearLayout safe;
    private LinearLayout call_us;
    private LinearLayout more;

    public MoreFragment() {
        // Required empty public constructor
    }




    @Override
    public void onClick(View v) {
     switch (v.getId()){

         case R.id.company_news:
           startActivity(new Intent(getActivity(),CompanyNewsActivity.class));
             break;
         case R.id.actives:
             startActivity(new Intent(getActivity(),ActiveActivity.class));
             break;
         case R.id.about_us:
             startActivity(new Intent(getActivity(),AboutUsActivity.class));
             break;
         case R.id.help:
             startActivity(new Intent(getActivity(),HelpActivity.class));
             break;
         case R.id.safe:
             startActivity(new Intent(getActivity(),SafeActivity.class));
             break;
         case R.id.call_us:
             Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"4000-166-158"));
             startActivity(intent);
             break;

     }


    }

    @Override
    protected void initView(View view) {

        title_zixun = view.findViewById(R.id.title_zixun);
        title_zixun.setOnClickListener(this);
        company_news = view.findViewById(R.id.company_news);
        company_news.setOnClickListener(this);
        actives = view.findViewById(R.id.actives);
        actives.setOnClickListener(this);
        about_us = view.findViewById(R.id.about_us);
        about_us.setOnClickListener(this);
        help = view.findViewById(R.id.help);
        help.setOnClickListener(this);
        safe = view.findViewById(R.id.safe);
        safe.setOnClickListener(this);
        call_us = view.findViewById(R.id.call_us);
        call_us.setOnClickListener(this);
        more = view.findViewById(R.id.more);
        more.setPadding(0,getStatusBarHeight(getActivity()),0,0);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_more;
    }

}
