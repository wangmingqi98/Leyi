package com.leyijf.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.B_RepaymentDetailBean;

import java.util.List;

/**
 * 我的借款--还款详情
 * Created by wmq on 2018/5/9.
 */

public class B_RepaymentDetailAdapter extends BaseQuickAdapter<B_RepaymentDetailBean.LoanListBean,BaseViewHolder> {
    private List<B_RepaymentDetailBean.LoanListBean> loan_list;

    static LookDetail lookDetails;
    public B_RepaymentDetailAdapter(int layoutResId, @Nullable List<B_RepaymentDetailBean.LoanListBean> data) {
        super(layoutResId, data);
        this.loan_list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final B_RepaymentDetailBean.LoanListBean item) {
        helper.getView(R.id.look_detail).setVisibility(View.VISIBLE);
        helper.setText(R.id.count,"第"+item.getL_key_index()+"期");
        helper.setText(R.id.money,item.getMonth_repay_money()+"");
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
        helper.getView(R.id.look_detail).setOnClickListener(new View.OnClickListener() {//查看详情
            @Override
            public void onClick(View v) {//弹出查看明细dialog
//                Toast.makeText(mContext,"点击",Toast.LENGTH_LONG).show();
                lookDetails.showLookDetail(item.getL_key());
            }
        });

    }

    public static void setLookDetail(LookDetail lookDetail){
        lookDetails = lookDetail;

    }
    public interface LookDetail{
        void showLookDetail(String lKey);
    }
}
