package com.leyi.base;


import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.leyi.R;
import com.leyi.util.DialogUtils;


/**
 * Created by Administrator on 2017/12/26.
 */

public abstract class BaseFragment  extends Fragment {
    private static Handler handler = new Handler();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId(), container, false);
        initView(view);
        initData();
        updateTitleBar();
        return view;
    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//    }

    public abstract void onClick(View v);

    /**
     * 初始化View控件
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 更改标题
     */
    protected abstract void updateTitleBar();

    /**
     * Fragment加载布局
     */
    protected abstract int layoutId();

    /**
     * add hide show 旧的Fragemnt在跳转到新的Fragment之前会回调这个方法 旧的Frgament不会调用onStop()方法
     * 新的Fragment也不会回调这个方法
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden)
            onHidden();
        else
            onShow();

    }

    private void onShow() {
        updateTitleBar();
    }

    private void onHidden() {

    }

    @Override
    public void onDestroyView() {
//        layoutId();
        super.onDestroyView();
    }





    /**
     * 短时间显示Toast
     *
     * @param info
     *            显示的内容
     */
    public void showToast(String info) {
        Toast.makeText(getActivity(), info, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param info
     *            显示的内容
     */
    public void showToastLong(String info) {
        Toast.makeText(getActivity(), info, Toast.LENGTH_LONG).show();
    }



    /**
     * 判断手机是否有网络
     *
     * @return true 有网络
     */
    public boolean isConnected() {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        try {
            ConnectivityManager connectivity = (ConnectivityManager) getActivity()
                    .getSystemService(getActivity().CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();

                if (info != null && info.isConnected()) {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 显示正在加载的进度条
     *
     */
    public void showLoadingDialog(String text, Context context) {
        // marginTop 0dp 背景透明
        DialogUtils.Loading.showLoading(text, context, R.dimen.dp0,
                Color.TRANSPARENT);
    }







    /**
     * 隐藏正在加载的进度条
     *
     */
    public void dismissLoadDialog() {
        DialogUtils.Loading.finishLoading();

    }



    /**
     * 获取控件的宽高
     *
     * @param view
     * @return
     */
    public int[] getWigetWidthHeight(View view) {
        int[] array = new int[2];
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int width = view.getMeasuredWidth();
        int height = view.getMeasuredHeight();
        array[0] = width;
        array[1] = height;
        return array;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
