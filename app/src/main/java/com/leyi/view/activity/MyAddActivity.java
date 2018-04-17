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
import com.leyi.view.fragment.myadd_fragment.GuoqiFragment;
import com.leyi.view.fragment.myadd_fragment.WeiFragment;
import com.leyi.view.fragment.myadd_fragment.YiFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 我的加息卷
 */
public class MyAddActivity extends BaseActivity {




    private ImageView mIvMyindentBack;
    private TabLayout mTablayoutMyindent;
    private ViewPager mViewpagerMyindent;
    private String[] mStringList = {"未使用","已使用","已过期"};
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

//        MyindentFragment myindentFragment = new MyindentFragment();
//        mList.add(new MyindentFragment());
        mList.add(new WeiFragment("uc_rates_list"));
        mList.add(new YiFragment("uc_rates_list"));
        mList.add(new GuoqiFragment("uc_rates_list"));


        MyindentViewpagerAdapter adapter = new MyindentViewpagerAdapter(getSupportFragmentManager(), MyAddActivity.this, mList, Arrays.asList(mStringList));
        mViewpagerMyindent.setAdapter(adapter);
        mTablayoutMyindent.setupWithViewPager(mViewpagerMyindent);
        mViewpagerMyindent.setCurrentItem(0);
    }
}
