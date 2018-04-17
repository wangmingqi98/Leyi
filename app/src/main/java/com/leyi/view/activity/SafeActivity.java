package com.leyi.view.activity;

import android.view.View;
import android.widget.ImageView;

import com.leyi.R;
import com.leyi.base.BaseActivity;

/**
 * 安全保证
 */
public class SafeActivity extends BaseActivity {

    private ImageView zhuce_back;


    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        zhuce_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_safe;
    }
}
