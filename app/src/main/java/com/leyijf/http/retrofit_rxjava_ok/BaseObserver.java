package com.leyijf.http.retrofit_rxjava_ok;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import com.leyijf.App;
import com.leyijf.view.activity.LoginActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * BaseObserver
 * Created by wmq on 2017/6/23.
 */
public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {

    private static final String TAG = "BaseObserver";
    private Activity mContext;

    protected BaseObserver(Activity context) {
        this.mContext = context;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseEntity<T> value) {
        Log.d(TAG, "onNext: "+value.toString());
        if (value.isSuccess()) {
            T t = value.getData();
            onHandleSuccess(t);
        } else {
            onHandleError(value.getShow_err());
            if(value.getShow_err().equals("未登录")){
                Intent intent = new Intent(App.getInstance(), LoginActivity.class);
                App.getInstance().startActivity(intent);
            }

        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError:" + e.toString());

    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");
    }

    protected abstract void onHandleSuccess(T t);

    protected void onHandleError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
