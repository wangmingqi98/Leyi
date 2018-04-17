package com.leyi.view.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.leyi.R;
import com.leyi.adapter.MyindentViewpagerAdapter;
import com.leyi.base.BaseActivity;
import com.leyi.view.fragment.totalzchfragment.MyZChFragment;
import com.leyi.view.fragment.totalzchfragment.StatisticsFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 我的红包
 */
public class TotalZChActivity extends BaseActivity {

    private ImageView mIvMyindentBack;
    private TabLayout mTablayoutMyindent;
    private ViewPager mViewpagerMyindent;
    private String[] mStringList = {"我的资产","收益统计"};
    private List<Fragment> mList = new ArrayList<>();
    private Intent intent;

    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {






        mIvMyindentBack = (ImageView) findViewById(R.id.zhuce_back);
        mIvMyindentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mTablayoutMyindent = (TabLayout) findViewById(R.id.tablayout_myindent);
        mViewpagerMyindent = (ViewPager) findViewById(R.id.viewpager_myindent);

        initView();
    }



    private void initView() {

//        MyindentFragment myindentFragment = new MyindentFragment();
//        mList.add(new MyindentFragment());
        mList.add(new MyZChFragment());
        mList.add(new StatisticsFragment());



        MyindentViewpagerAdapter adapter = new MyindentViewpagerAdapter(getSupportFragmentManager(), TotalZChActivity.this, mList, Arrays.asList(mStringList));
        mViewpagerMyindent.setAdapter(adapter);
        mTablayoutMyindent.setupWithViewPager(mViewpagerMyindent);
        mViewpagerMyindent.setCurrentItem(0);
    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_total_zch;
    }
}
