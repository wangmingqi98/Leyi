package com.leyijf.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.leyijf.App;
import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.UserInfo;
import com.leyijf.weight.grsture.Contants;
import com.leyijf.weight.grsture.CustomLockView;
import com.leyijf.weight.grsture.LockMode;
import com.leyijf.weight.grsture.PasswordUtil;

import static com.leyijf.weight.grsture.LockMode.CLEAR_PASSWORD;
import static com.leyijf.weight.grsture.LockMode.SETTING_PASSWORD;

/**
 * 手势密码
 */
public class DrawPsdActivity extends BaseActivity {
    private static final String TAG =DrawPsdActivity.class.getSimpleName();

    private Context mContext;
    private App myApp;

    private TextView tv_text;
    private TextView user_num;
    private CustomLockView lvLock;
    private TextView forgect;
    private TextView shoushi_zuoshang_tv;

    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {

        forgect = (TextView) findViewById(R.id.forgect);
        shoushi_zuoshang_tv = (TextView) findViewById(R.id.shoushi_zuoshang_tv);
        tv_text = (TextView) findViewById(R.id.tv_text);
        user_num = (TextView) findViewById(R.id.user_num);
        user_num.setText(UserInfo.getInstance().getUserName());
        lvLock = (CustomLockView) findViewById(R.id.lv_lock);
        //设置模式
        LockMode lockMode = (LockMode) getIntent().getSerializableExtra(Contants.INTENT_SECONDACTIVITY_KEY);
        setLockMode(lockMode);


        //显示绘制方向
        lvLock.setShow(true);
        //允许最大输入次数
        lvLock.setErrorNumber(3);
        //密码最少位数
        lvLock.setPasswordMinLength(4);
        //编辑密码或设置密码时，是否将密码保存到本地，配合setSaveLockKey使用
        lvLock.setSavePin(true);
        //保存密码Key
        lvLock.setSaveLockKey(Contants.PASS_KEY);
        lvLock.setOnCompleteListener(onCompleteListener);
        forgect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DrawPsdActivity.this,LoginActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("mode",3);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        shoushi_zuoshang_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


}

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_draw_psd;
    }
    /**
     * 密码输入模式
     */
    private void setLockMode(LockMode mode, String password, String msg) {
        lvLock.setMode(mode);
        lvLock.setErrorNumber(3);
        lvLock.setClearPasssword(true);
        if (mode != SETTING_PASSWORD) {
            shoushi_zuoshang_tv.setVisibility(View.GONE);
            tv_text.setText("验证密码");
            lvLock.setOldPassword(password);
        } else {
            tv_text.setText("请输入要设置的密码");
            shoushi_zuoshang_tv.setVisibility(View.VISIBLE);

        }
//        tv_text.setText("");
    }


    /**
     * 密码输入监听
     */
    CustomLockView.OnCompleteListener onCompleteListener = new CustomLockView.OnCompleteListener() {
        @Override
        public void onComplete(String password, int[] indexs) {
            tv_text.setText(getPassWordHint());

            setResult(111);
            finish();
        }

        @Override
        public void onError(String errorTimes) {
            tv_text.setText("密码错误，还可以输入" + errorTimes + "次");
        }

        @Override
        public void onPasswordIsShort(int passwordMinLength) {
            tv_text.setText("密码不能少于" + passwordMinLength + "个点");
        }

        @Override
        public void onAginInputPassword(LockMode mode, String password, int[] indexs) {
            tv_text.setText("请再次输入密码");
        }


        @Override
        public void onInputNewPassword() {
            tv_text.setText("请输入新密码");
        }

        @Override
        public void onEnteredPasswordsDiffer() {
            tv_text.setText("两次输入的密码不一致");
        }

        @Override
        public void onErrorNumberMany() {
            tv_text.setText("密码错误次数超过限制，不能再输入");
        }

    };


    /**
     * 密码相关操作完成回调提示
     */
    private String getPassWordHint() {

        String str = null;
        switch (lvLock.getMode()) {
            case SETTING_PASSWORD:
                str = "密码设置成功";
                break;
            case EDIT_PASSWORD:
                str = "密码修改成功";
                break;
            case VERIFY_PASSWORD:
                str = "密码正确";
                break;
            case CLEAR_PASSWORD:
                str = "密码已经清除";
                break;
        }
        return str;
    }

    /**
     * 设置解锁模式
     */
    private void setLockMode(LockMode mode) {
        String str = "";
        switch (mode) {
            case CLEAR_PASSWORD:

                str = "清除密码";
                setLockMode(CLEAR_PASSWORD, PasswordUtil.getPin(this), str);
                break;
            case EDIT_PASSWORD:

                str = "修改密码";
                setLockMode(LockMode.EDIT_PASSWORD, PasswordUtil.getPin(this), str);
                break;
            case SETTING_PASSWORD:

                str = "设置密码";
                setLockMode(SETTING_PASSWORD, null, str);
                break;
            case VERIFY_PASSWORD:
                str = "验证密码";
                setLockMode(LockMode.VERIFY_PASSWORD, PasswordUtil.getPin(this), str);
                break;
        }
        tv_text.setText(str);
    }
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK)
            return true;//不执行父类点击事件
        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }

}
