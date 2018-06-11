package com.leyijf.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.RepaymentListBean;
import com.leyijf.view.activity.YouBiaoActivity;
import com.leyijf.weight.CustomizedProgressBar;

import java.util.List;

/**
 * Created by wmq on 2018/3/6.
 */

public class NoviceListAdapter extends BaseQuickAdapter<RepaymentListBean.DealsListBean,BaseViewHolder> {
    private List<RepaymentListBean.DealsListBean> deals_list;
    String type;
    public NoviceListAdapter(int layoutResId, @Nullable List<RepaymentListBean.DealsListBean> data) {
        super(layoutResId, data);
        this.deals_list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final RepaymentListBean.DealsListBean item) {
        helper.setText(R.id.project_name,item.getName());
        if(item.getIs_new().equals("1")){
            helper.setText(R.id.project,"新手标");
            helper.getView(R.id.project).setBackgroundResource(R.drawable.xinshoub);
        }else {
            switch (item.getCate_id()) {
                case "1":
                    helper.setText(R.id.project, "货抵贷");
                    helper.getView(R.id.project).setBackgroundResource(R.drawable.shouyedaididai);
                    break;
                case "2":
                    helper.setText(R.id.project, "产业链");
                    helper.getView(R.id.project).setBackgroundResource(R.drawable.shouye_baoxianfenqi);
                    break;
                default:
                    break;
            }
        }
        helper.setText(R.id.rate_foramt,item.getRate()+"%");
        helper.setText(R.id.repay_time,item.getRepay_time()+item.getRepay_time_type());
        helper.setText(R.id.min_loan_money_fromat,item.getMin_loan_money());
        helper.setText(R.id.need_money,item.getRemain_money()+"元");
        helper.setText(R.id.progress_point,item.getProgress_point()+"%");
        CustomizedProgressBar progressBar   = helper.getView(R.id.progress);
        progressBar.setCurrentCount(Float.parseFloat(item.getProgress_point()));
        switch (item.getDeal_status()){
            case "1"://立即投资
                type = "1";
                helper.setText(R.id.now_repay,"立即投资");
                helper.getView(R.id.now_repay).setBackgroundResource(R.drawable.btn_shape);
                break;
            case "4"://还款中
                type = "2";
                helper.setText(R.id.now_repay,"还款中");
                helper.getView(R.id.now_repay).setBackgroundResource(R.drawable.hui_btn_shape);
                break;
            case "5"://已还清
                type = "3";
                helper.setText(R.id.now_repay,"已还清");
                helper.getView(R.id.now_repay).setBackgroundResource(R.drawable.hui_btn_shape);
                break;
            default:
                break;

        }

        helper.getView(R.id.now_repay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Intent intent = new Intent();
                intent.putExtra("type",item.getDeal_status());
                intent.putExtra("id",item.getDeal_id());
                intent.setClass(mContext,YouBiaoActivity.class);
                mContext.startActivity(intent);
            }
        });
    }
}
