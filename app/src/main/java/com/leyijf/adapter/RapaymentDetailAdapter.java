package com.leyijf.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.RepaymentDetailBean;

import java.util.List;

/**
 * Created by wmq on 2018/5/7.
 */

public class RapaymentDetailAdapter extends BaseQuickAdapter<RepaymentDetailBean.LoanListBean,BaseViewHolder> {
    private List<RepaymentDetailBean.LoanListBean> loan_list;
    public RapaymentDetailAdapter(int layoutResId, @Nullable List<RepaymentDetailBean.LoanListBean> data) {
        super(layoutResId, data);
        this.loan_list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, RepaymentDetailBean.LoanListBean item) {
        helper.setText(R.id.count,"第"+item.getL_key_index()+"期");
        helper.setText(R.id.money,item.getMonth_repay_money());
        TextView status = helper.getView(R.id.status);
        switch (item.getStatus()){
            case 1://提前还款
                status.setTextColor(Color.parseColor("#f2a625"));
                break;
            case 3://逾期还款
                status.setTextColor(Color.parseColor("#ec691f"));
                break;
            case 4://严重逾期
                status.setTextColor(Color.parseColor("#fd3b19"));
                break;
            default:
                break;

        }
        helper.setText(R.id.status,item.getStatus_format());

    }
}
