package com.leyijf.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.base.BaseFragment;
import com.leyijf.view.fragment.helpfragment.Q1Fragment;
import com.leyijf.view.fragment.helpfragment.Q2Fragment;
import com.leyijf.view.fragment.helpfragment.Q3Fragment;
import com.leyijf.view.fragment.helpfragment.Q4Fragment;
import com.leyijf.view.fragment.helpfragment.Q6Fragment;

import java.util.ArrayList;

/**
 * 帮助页面
 */
public class HelpActivity extends BaseActivity{



    private FrameLayout flHomeContent;


    RadioGroup rgTools;


    private int position = 0;

    //缓存Fragment或上次显示的Fragment
    private Fragment tempFragment;
    private ArrayList<BaseFragment> fragments;
    private ImageView zhuce_back;


    @Override
    protected void initData() {
        initFragment();
        commitOneFrag();
    }


    private void initListener() {
        rgTools.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rbTab1:
//                        首页
                        position = 0;

//

                        break;
                    case R.id.rbTab2:
//
                        position = 1;

                        break;
//                    case R.id.rbTab3:
////
//                        position = 2;
//
//
//
//
//                        break;
                    case R.id.rbTab4:
//
                        position = 2;

                        break;
                    case R.id.rbTab5:
//
                        position = 3;

                        break;
                    default:
                        position = 0;
                        break;
                }
                Fragment f = getFragment(position);
                switchFragment(tempFragment, f);
            }
        });


    }

    private void commitOneFrag() {
        rgTools.check(R.id.rbTab1);
        Fragment f = getFragment(0);
        switchFragment(tempFragment, f);
    }

    /**
     * 根据位置得到对应的 Fragment
     *
     * @param position
     * @return
     */
    private Fragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            Fragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 切换Fragment
     *
     * @param fragment
     * @param nextFragment
     */
    private void switchFragment(Fragment fragment, Fragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加成功
                if (!nextFragment.isAdded()) {
                    //隐藏当前的Fragment
                    if (fragment != null) {
                        transaction.hide(fragment);
                        transaction.addToBackStack(fragment.getClass().getSimpleName());

                    }
                    //添加Fragment
                    transaction.add(R.id.flHomeContent, nextFragment).commit();

                } else {
                    //隐藏当前Fragment
                    if (fragment != null) {
                        transaction.hide(fragment);
                        transaction.addToBackStack(fragment.getClass().getSimpleName());
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    private void initFragment() {

        fragments = new ArrayList<>();
        fragments.add(new Q1Fragment());
        fragments.add(new Q2Fragment());
//        fragments.add(new Q3Fragment());
        fragments.add(new Q4Fragment());
        fragments.add(new Q6Fragment());
    }

    @Override
    protected void initId() {

        flHomeContent = (FrameLayout) findViewById(R.id.flHomeContent);
        rgTools = (RadioGroup) findViewById(R.id.tabGroup);
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        zhuce_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initListener();
    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_help;
    }
}
