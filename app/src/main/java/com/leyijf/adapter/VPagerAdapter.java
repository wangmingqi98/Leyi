package com.leyijf.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/3/7.
 */

public class VPagerAdapter extends FragmentPagerAdapter {

    List<String> tabs;
    List<Fragment> fragments;
    Context context;


    public VPagerAdapter(FragmentManager fm, List<String> tabs, List<Fragment> fragments, Context context) {
        super(fm);
        this.tabs = tabs;
        this.fragments = fragments;
        this.context = context;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        Drawable dImage = context.getResources().getDrawable(tabImg[position]);
//        dImage.setBounds(0, 0, dImage.getIntrinsicWidth(), dImage.getIntrinsicHeight());
//        //这里前面加的空格就是为图片显示
//        SpannableString sp = new SpannableString("  "+ tabs.get(position));
//        ImageSpan imageSpan = new ImageSpan(dImage, ImageSpan.ALIGN_BOTTOM);
//        sp.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return  tabs.get(position);
    }
}
