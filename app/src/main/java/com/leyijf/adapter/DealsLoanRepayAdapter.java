package com.leyijf.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.DealsLoanRepayBean;

import java.util.List;

/**
 * Created by wmq on 2018/5/17.
 */

public class DealsLoanRepayAdapter extends BaseQuickAdapter<DealsLoanRepayBean.LoanRepayListBean,BaseViewHolder>{
    private List<DealsLoanRepayBean.LoanRepayListBean> loan_repay_list;
    public DealsLoanRepayAdapter(int layoutResId, @Nullable List<DealsLoanRepayBean.LoanRepayListBean> data) {
        super(layoutResId, data);
        this.loan_repay_list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, DealsLoanRepayBean.LoanRepayListBean item) {
        helper.setText(R.id.repaytime,item.getDate())
        .setText(R.id.repayinterest,item.getMonth_has_repay_money())
        .setText(R.id.alsointerest,item.getMonth_repay_money())
        .setText(R.id.status,item.getStatus());
    }
}
