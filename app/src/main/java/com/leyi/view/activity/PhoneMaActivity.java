package com.leyi.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leyi.R;
import com.leyi.base.BaseActivity;
import com.leyi.bean.UserInfo;

/**
 * 手机号码
 */
public class PhoneMaActivity extends BaseActivity implements View.OnClickListener{

    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
    private TextView bind_shouji;
    private TextView next_stepshen;



    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        initView();
    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_phone_ma;
    }

    private void initView() {
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        zhuce_back.setOnClickListener(this);
        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);
        bind_shouji = (TextView) findViewById(R.id.bind_shouji);
        next_stepshen = (TextView) findViewById(R.id.next_stepshen);
        next_stepshen.setOnClickListener(this);


        bind_shouji.setText(UserInfo.getInstance().getUserPhone()+"");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhuce_back:
                finish();
                break;

            case R.id.next_stepshen:
                startActivityForResult(new Intent(this,RePhoneActivity.class),110);
                break;



        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


//        ToDo...
    }
}
