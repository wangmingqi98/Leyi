package com.leyijf.http.retrofit_rxjava_ok;

import android.app.Activity;
import android.content.Context;
import android.util.Log;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * BaseObserver
 * Created by wmq on 2017/6/23.
 */
public abstract class BaseObserver2<T> implements Observer<T> {

    private static final String TAG = "BaseObserver2";
    private Context mContext;

    protected BaseObserver2(Activity context) {
        this.mContext = context.getApplicationContext();
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T value) {

        onHandleSuccess(value);
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError:" + e.toString());

    }

    @Override
    public void onComplete() {
//        Log.d(TAG, "onComplete");
//        if (mAnimationView != null && mAnimationView.isAnimating()) {
//            mAnimationView.cancelAnimation();
//        }
    }

   // protected abstract void onHandleSuccess(T t);


    protected void onHandleSuccess(T t){

    }


//    protected void onHandleError(String msg) {
//        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
//    }
}
