package com.leyijf.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/3/7.
 */

public class MyindentViewpagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<Fragment> mList;
    private List<String> mStringList;

    public MyindentViewpagerAdapter(FragmentManager fm, Context context, List<Fragment> mList, List<String> mStringList) {
        super(fm);
        this.context = context;
        this.mList = mList;
        this.mStringList = mStringList;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStringList.get(position);
    }
}
