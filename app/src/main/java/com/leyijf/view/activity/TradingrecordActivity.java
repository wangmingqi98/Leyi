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
import com.leyijf.view.fragment.tradcordfragment.AllFragment;
import com.leyijf.view.fragment.tradcordfragment.ChongzhiFragment;
import com.leyijf.view.fragment.tradcordfragment.TixianFragment;
import com.leyijf.view.fragment.tradcordfragment.TouziFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 交易记录
 */

public class TradingrecordActivity extends BaseActivity {


    private ImageView mIvMyindentBack;
    private TabLayout mTablayoutMyindent;
    private ViewPager mViewpagerMyindent;
    private String[] mStringList = {"全部","投资","充值","提现"};
    private List<Fragment> mList = new ArrayList<>();
    private Intent intent;
    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        intent = getIntent();
        mIvMyindentBack = (ImageView) findViewById(R.id.zhuce_back);
        mIvMyindentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTablayoutMyindent = (TabLayout) findViewById(R.id.tablayout_myindent);
        mViewpagerMyindent = (ViewPager) findViewById(R.id.viewpager_myindent);
        initdata();
    }

    private void initdata() {
        mList.add(new AllFragment());
        mList.add(new TouziFragment());
        mList.add(new ChongzhiFragment());
        mList.add(new TixianFragment());
        MyindentViewpagerAdapter adapter = new MyindentViewpagerAdapter(getSupportFragmentManager(), TradingrecordActivity.this, mList, Arrays.asList(mStringList));
        mViewpagerMyindent.setAdapter(adapter);
        mTablayoutMyindent.setupWithViewPager(mViewpagerMyindent);
        mViewpagerMyindent.setCurrentItem(0);


            }




    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_tradingrecord;
    }

}

