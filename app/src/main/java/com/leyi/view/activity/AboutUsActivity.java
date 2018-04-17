package com.leyi.view.activity;

import android.view.View;
import android.widget.ImageView;

import com.leyi.R;
import com.leyi.base.BaseActivity;
/**
 * 关于页面
 */
public class AboutUsActivity extends BaseActivity {


    private ImageView zhuceBack;

    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        zhuceBack = (ImageView) findViewById(R.id.zhuce_back);
        zhuceBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_about_us;
    }
}
