package com.leyijf.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.LookDetailBean;

import java.util.List;

/**
 * 查看明细
 * Created by wmq on 2018/5/10.
 */

public class LookDetailAdapter extends BaseQuickAdapter<LookDetailBean.LoadUserBean,BaseViewHolder> {
    private List<LookDetailBean.LoadUserBean> load_user;

    public LookDetailAdapter(int layoutResId, @Nullable List<LookDetailBean.LoadUserBean> data) {
        super(layoutResId, data);
        this.load_user = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, LookDetailBean.LoadUserBean item) {
        helper.setText(R.id.vip,item.getUser_name());
        helper.setText(R.id.repay_moey,item.getMonth_repay_money_format());
        helper.setText(R.id.breach_money,item.getImpose_money_format());

    }
}
