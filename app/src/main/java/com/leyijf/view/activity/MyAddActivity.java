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
import com.leyijf.view.fragment.myadd_fragment.WeiFragment;
import com.leyijf.view.fragment.myadd_fragment.YiFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 我的奖励
 */
public class MyAddActivity extends BaseActivity {




    private ImageView mIvMyindentBack;
    private TabLayout mTablayoutMyindent;
    private ViewPager mViewpagerMyindent;
    private String[] mStringList = {"我的红包","我的加息劵"};
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
        initView();
    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_my_add;
    }

    private void initView() {

        mList.add(new WeiFragment());//我的红包
        mList.add(new YiFragment());//我的加息券


        MyindentViewpagerAdapter adapter = new MyindentViewpagerAdapter(getSupportFragmentManager(), MyAddActivity.this, mList, Arrays.asList(mStringList));
        mViewpagerMyindent.setAdapter(adapter);
        mTablayoutMyindent.setupWithViewPager(mViewpagerMyindent);
        mViewpagerMyindent.setCurrentItem(0);
    }
}
