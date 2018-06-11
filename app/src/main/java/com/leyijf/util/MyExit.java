package com.leyijf.util;

import android.os.Handler;

/**
 * Created by Administrator on 2017/12/27.
 */

public class MyExit {

    private boolean mIsExit = false;

    public void clickTwoExit() {
        mIsExit = true;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mIsExit = false;
            }
        }, 2000);
    }

    public boolean getExit() {
        // TODO Auto-generated method stub
        return mIsExit;
    }
}
