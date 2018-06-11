package com.leyijf.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.leyijf.R;
import com.leyijf.adapter.WelcomeAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Administrator
 *         <p>
 *         首次安装引导页
 */
public class NavigationPageActivity extends Activity implements OnClickListener,
        OnPageChangeListener {
    private ViewPager vp;
    private WelcomeAdapter vpAdapter;
    private List<View> views;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_viewpager);

        views = new ArrayList<View>();

        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater = LayoutInflater.from(this);
        views.add(inflater.inflate(R.layout.what_new1, null));
        views.add(inflater.inflate(R.layout.what_new2, null));
        views.add(inflater.inflate(R.layout.what_new3, null));
        vp = (ViewPager) findViewById(R.id.viewpager);
        // 初始化Adapter
        vpAdapter = new WelcomeAdapter(views, this);
        vp.setAdapter(vpAdapter);
        // 绑定回调
        vp.setOnPageChangeListener(this);

        // 初始化底部小点

    }

    // 当滑动状态改变时调用
    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    // 当当前页面被滑动时调用
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    // 当新的页面被选中时调用
    @Override
    public void onPageSelected(int arg0) {
        // 设置底部小点选中状态
    }

    @Override
    public void onClick(View v) {

    }


}