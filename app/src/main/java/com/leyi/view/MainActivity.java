package com.leyi.view;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leyi.R;
import com.leyi.base.BaseActivity;
import com.leyi.base.BaseFragment;
import com.leyi.util.MyExit;
import com.leyi.view.fragment.HomeFragment;
import com.leyi.view.fragment.LiCaiShopFragment;
import com.leyi.view.fragment.MoreFragment;
import com.leyi.view.fragment.MyMoneyFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_PICK_CITY = 233;
    private LinearLayout city_selector;
    private ImageView add;
    private FrameLayout flHomeContent;
    private MyExit exit;
    private final String TAG = "TAG";

    RadioGroup rgTools;
    Handler handler=new Handler();

    private int position = 0;

    //缓存Fragment或上次显示的Fragment
    private Fragment tempFragment;
    private ArrayList<BaseFragment> fragments;
    private MoreFragment myFragment;
    private ImageView title_header;
    private TextView title_textname;
    public TextView edtor;

    public LiCaiShopFragment shopFragment;
//    public MyMoneyFragment shopCarFragment;

    private TextView city_name;
    private ImageView right;


    private TextView sedCname;
    private ListView recyclerViewCity;
    DoShopEditer doShopEditer;
    private RelativeLayout activity_main_title;
    public RelativeLayout title_zixun;
    public TabLayout my_tab;
    private MyMoneyFragment newsFragment;
    private RelativeLayout mains;
    private View childAt;
    private HomeFragment homeFragment;

    @Override
    protected void initData() {

        initFragment();
        commitOneFrag();
        requestPermissions(MainActivity.this, neededPermissions, new RequestPermissionCallBack() {
            @Override
            public void granted() {

            }

            @Override
            public void denied() {
                Toast.makeText(MainActivity.this, "权限被禁用，请到设置里打开", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void initListener() {
        rgTools.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rbTabHome:
//                        首页
                        position = 0;




//

                        break;
                    case R.id.rbTabShop:
//
                        position = 1;


                        break;
                    case R.id.rbTabCar:
//
                        position = 2;





                        break;
                    case R.id.rbTabMy:
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
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
     if( bundle!=null) {
         int page = bundle.getInt("page");
         rgTools.check(R.id.rbTabShop);
         Fragment f = getFragment(page);
         switchFragment(tempFragment, f);
     }else {
         rgTools.check(R.id.rbTabHome);
         Fragment f = getFragment(0);
         switchFragment(tempFragment, f);
     }



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
        homeFragment = new HomeFragment();
        myFragment = new MoreFragment();
        shopFragment = new LiCaiShopFragment();
//        shopCarFragment = new ShopCarFragment();
        newsFragment = new MyMoneyFragment();
        fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(shopFragment);
        fragments.add(newsFragment);
        fragments.add(myFragment);
    }

    @Override
    protected void initId() {
        exit = new MyExit();
        mains = (RelativeLayout) findViewById(R.id.mains);
        flHomeContent = (FrameLayout) findViewById(R.id.flHomeContent);
        rgTools = (RadioGroup) findViewById(R.id.tabGroup);
        childAt = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);



//        activity_main_title = (RelativeLayout) findViewById(R.id.activity_main_title);
//        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);
//        my_tab = (TabLayout) findViewById(R.id.my_tab);

        initListener();
    }

    @Override
    protected int getContentViewId() {


        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {

    }






    private void showpop(View inflate,View voew) {
        PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);// 取得焦点
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        //进入退出的动画
//                popupWindow.setAnimationStyle(R.style.Animation);
//                popupWindow.showAtLocation(inflate, Gravity.BOTTOM,0,130);
        popupWindow.showAsDropDown(voew, 0, 40);

        // 产生背景变暗效果
        WindowManager.LayoutParams ayoutParams = this.getWindow()
                .getAttributes();
        ayoutParams.alpha = 0.4f;
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.getWindow().setAttributes(ayoutParams);

        popupWindow.update();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = MainActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                MainActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                MainActivity.this.getWindow().setAttributes(lp);
            }
        });
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            if (exit.getExit()) {
                System.exit(0);
                return true;
            } else {
                Toast.makeText(this, "连续点击两次退出程序", Toast.LENGTH_SHORT).show();
                exit.clickTwoExit();
                return false;
            }

        }
        return super.onKeyDown(keyCode, event);
    }



//
//    private void initPopupwindow(View inflate) {
//        cityPickerPop = new PopupWindow(this);
//        cityPickerPop.setContentView(inflate);
//        int width1 = getWindowManager().getDefaultDisplay().getWidth();
//        int height1 = getWindowManager().getDefaultDisplay().getHeight();
//        cityPickerPop.setWidth(width1);
//        float temp1 = (float) (height1 * 0.7);
//        cityPickerPop.setHeight((int) temp1);
//        cityPickerPop.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
//        cityPickerPop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        final WindowManager.LayoutParams attributes1 = getWindow().getAttributes();
//        attributes1.alpha = 0.5f;
//        cityPickerPop.setBackgroundDrawable(new BitmapDrawable());
//        getWindow().setAttributes(attributes1);
//        cityPickerPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
////                        popupWindow.dismiss();
//                attributes1.alpha = 1.0f;
//                getWindow().setAttributes(attributes1);
//            }
//        });
////                popupWindow.setHeight(DensityUtil.dp2px(480));
//        cityPickerPop.setFocusable(true);
//        cityPickerPop.setOutsideTouchable(true);
//        cityPickerPop.update();
//        cityPickerPop.showAsDropDown(city_selector);
//    }


    public interface DoShopEditer{
        void  doEdit();
    }
    public void doEdtior(DoShopEditer doShopEditer){
        this.doShopEditer=doShopEditer;
    }



}
