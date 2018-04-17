package com.leyi.util;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/3/15.
 */

public class CountDownTimerUtils extends CountDownTimer {
TextView jishi;
TextView huoqu;
    public CountDownTimerUtils(TextView jishi,TextView huoqu,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.jishi=jishi;
        this.huoqu=huoqu;
    }

    @Override
    public void onTick(long l) {
        jishi.setVisibility(View.VISIBLE);
        huoqu.setVisibility(View.GONE);
        jishi.setClickable(false); //设置不可点
         jishi.setText(l / 1000 + "秒"); //设置倒计时时间

    }

    @Override
    public void onFinish() {
        huoqu.setVisibility(View.VISIBLE);
        jishi.setVisibility(View.GONE);
        huoqu.setText("重新获取验证码");

    }
}

