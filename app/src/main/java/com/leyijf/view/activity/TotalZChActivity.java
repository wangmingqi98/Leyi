package com.leyijf.view.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.leyijf.R;
import com.leyijf.adapter.MyindentViewpagerAdapter;
import com.leyijf.base.BaseActivity;
import com.leyijf.view.fragment.totalzchfragment.MyZChFragment;
import com.leyijf.view.fragment.totalzchfragment.StatisticsFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 我的资产
 */
public class TotalZChActivity extends BaseActivity {

    private ImageView mIvMyindentBack;
    private TabLayout mTablayoutMyindent;
    private ViewPager mViewpagerMyindent;
    private String[] mStringList = {"我的资产","收益统计"};
    private List<Fragment> mList = new ArrayList<>();
    private Intent intent;
    private String totalMoney;
    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {

        intent = getIntent();

        totalMoney = intent.getStringExtra("money");


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

        mList.add(MyZChFragment.newInstance(totalMoney));//我的资产
        mList.add(new StatisticsFragment());//收益统计



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
