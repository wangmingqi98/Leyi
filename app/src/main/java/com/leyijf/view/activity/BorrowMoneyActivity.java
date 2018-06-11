package com.leyijf.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.leyijf.R;
import com.leyijf.adapter.MyindentViewpagerAdapter;
import com.leyijf.base.BaseActivity;
import com.leyijf.view.fragment.borrowmoney.AllBorrowMoneyFragment;
import com.leyijf.view.fragment.borrowmoney.FlowStandardFragment;
import com.leyijf.view.fragment.borrowmoney.InvestBorrowMoneyFragment;
import com.leyijf.view.fragment.borrowmoney.PayOffFragment;
import com.leyijf.view.fragment.borrowmoney.RepaymentFragment;
import android.support.design.widget.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 我的借款
 * Created by wmq on 2018/5/8.
 */

public class BorrowMoneyActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private ViewPager viewPager;
    private TabLayout tableLayout;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] mStringList = {"全部","投资中","还款中","已还清","已流标"};
    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        back = (ImageView) findViewById(R.id.zhuce_back);
        back.setOnClickListener(this);
        tableLayout = (TabLayout) findViewById(R.id.tablayout_myindent);
        viewPager = (ViewPager) findViewById(R.id.viewpager_myindent);
            initView();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_borrowmoney;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuce_back:
                finish();
                break;
        }

    }
    private void initView() {
        fragmentList.add(new AllBorrowMoneyFragment());
        fragmentList.add(new InvestBorrowMoneyFragment());
        fragmentList.add(new RepaymentFragment());
        fragmentList.add(new PayOffFragment());
        fragmentList.add(new FlowStandardFragment());
        MyindentViewpagerAdapter adapter = new MyindentViewpagerAdapter(getSupportFragmentManager(), this, fragmentList, Arrays.asList(mStringList));
        viewPager.setAdapter(adapter);
        tableLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);


    }
}
