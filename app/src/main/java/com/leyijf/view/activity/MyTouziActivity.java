package com.leyijf.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.leyijf.R;
import com.leyijf.adapter.MyindentViewpagerAdapter;
import com.leyijf.base.BaseActivity;
import com.leyijf.view.fragment.mytouzi_fragment.AlHuanFragment;
import com.leyijf.view.fragment.mytouzi_fragment.AlLiuFragment;
import com.leyijf.view.fragment.mytouzi_fragment.AlTouziFragment;
import com.leyijf.view.fragment.mytouzi_fragment.BackMoneytFragment;
import com.leyijf.view.fragment.mytouzi_fragment.QuanBuFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 我的投资页面
 */
public class MyTouziActivity extends BaseActivity {


    private ImageView mIvMyindentBack;
    private TabLayout mTablayoutMyindent;
    private ViewPager mViewpagerMyindent;
    private String[] mStringList = {"全部","投资中","还款中","已还清","已流标"};
    private List<Fragment> mList = new ArrayList<>();

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

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_my_touzi;
    }

    private void initView() {
        mList.add(new QuanBuFragment());
        mList.add(new AlTouziFragment());
        mList.add(new BackMoneytFragment());
        mList.add(new AlHuanFragment());
        mList.add(new AlLiuFragment());
        MyindentViewpagerAdapter adapter = new MyindentViewpagerAdapter(getSupportFragmentManager(), MyTouziActivity.this, mList, Arrays.asList(mStringList));
        mViewpagerMyindent.setAdapter(adapter);
        mTablayoutMyindent.setupWithViewPager(mViewpagerMyindent);
        mViewpagerMyindent.setCurrentItem(0);


    }

}
