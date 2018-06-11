package com.leyijf.adapter;

import android.annotation.SuppressLint;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.MortGageInfoBean;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;

import java.util.List;

/**
 * Created by wmq on 2018/5/17.
 */

public class MortGagePictureAdapter extends BaseQuickAdapter<MortGageInfoBean.MortgageBean.ContractBean,BaseViewHolder> {
    private List<MortGageInfoBean.MortgageBean.ContractBean> contract ;
    private String url;
    private PopupWindow popupWindow;
    public static void setShowPhoto(ShowPhoto showPhoto) {
        showPhotos = showPhoto;
    }

    static ShowPhoto showPhotos;

    public MortGagePictureAdapter(int layoutResId, @Nullable List<MortGageInfoBean.MortgageBean.ContractBean> data) {
        super(layoutResId, data);
        this.contract =data;
    }



    @Override
    protected void convert(BaseViewHolder helper, MortGageInfoBean.MortgageBean.ContractBean item) {
        url = item.getImg();
        ImageView imageView = helper.getView(R.id.picture);
        Glide.with(mContext).load(RetrofitFactory.BASEIMG + url).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showPhotos.show(url);
                showNoLogin(url);
            }
        });
    }
    @SuppressLint("WrongConstant")
    private void showNoLogin(String imgurl) {
        View  peicepop = LayoutInflater.from(mContext).inflate(R.layout.bantoupop,null);
        ImageView big_img = peicepop.findViewById(R.id.big_img);
        LinearLayout linearLayout = peicepop.findViewById(R.id.line);
        popupWindow = new PopupWindow(peicepop, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);// 取得焦点
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);

        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.showAtLocation(peicepop, Gravity.CENTER,0,0);
        // 产生背景变暗效果
//        WindowManager.LayoutParams ayoutParams = mContext.getWindow()
//                .getAttributes();
//        ayoutParams.alpha = 0.4f;
//        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        getActivity().getWindow().setAttributes(ayoutParams);

        popupWindow.update();
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//
//            // 在dismiss中恢复透明度
//            public void onDismiss() {
//                WindowManager.LayoutParams lp = ProjectDetilsFragment.this.getActivity().getWindow()
//                        .getAttributes();
//                lp.alpha = 1f;
//                ProjectDetilsFragment.this.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                ProjectDetilsFragment.this.getActivity().getWindow().setAttributes(lp);
//            }
//        });
        Glide.with(mContext).load(RetrofitFactory.BASEIMG+imgurl).into(big_img);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });




    }

    public interface ShowPhoto{
        void show(String url);
    }
}
