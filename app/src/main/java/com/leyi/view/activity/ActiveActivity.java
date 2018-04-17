package com.leyi.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.leyi.R;
import com.leyi.adapter.HotActiveAdapter;
import com.leyi.base.BaseActivity;

/**
 * 活动页面
 */
public class ActiveActivity extends BaseActivity {


    private ImageView zhuceBack;
    private ListView hot_active;
    private HotActiveAdapter hotActiveAdapter;

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

        hot_active = (ListView) findViewById(R.id.hot_active);
        hotActiveAdapter = new HotActiveAdapter(this);
        hot_active.setAdapter(hotActiveAdapter);


    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_active;
    }
}
