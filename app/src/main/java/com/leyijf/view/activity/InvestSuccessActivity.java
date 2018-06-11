package com.leyijf.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.view.MainTabActivity;

/**
 * 投资成功页
 * Created by wmq on 2018/5/24.
 */

public class InvestSuccessActivity extends BaseActivity implements View.OnClickListener {
    private TextView go_on;
    private TextView complete;
    private ImageView back;
    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        go_on = (TextView) findViewById(R.id.go_on);
        complete = (TextView) findViewById(R.id.complete);
        back = (ImageView) findViewById(R.id.zhuce_back);
        go_on.setOnClickListener(this);
        complete.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_investsuccess;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuce_back:
                finish();
                break;
            case R.id.go_on:
                Intent intent = new Intent();
                intent.putExtra("index","1");
                intent.setClass(this, MainTabActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.complete:
                Intent intent1 = new Intent();
                intent1.setClass(this,MyTouziActivity.class);
                startActivity(intent1);
                finish();
                break;
            default:
                break;
        }
    }
}
