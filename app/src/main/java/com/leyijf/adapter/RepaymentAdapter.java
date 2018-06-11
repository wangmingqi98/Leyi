package com.leyijf.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.MyInvestBean;
import com.leyijf.view.activity.RepaymentDetailActivity;
import com.leyijf.view.activity.YouBiaoActivity;

import java.util.List;

/**
 * 还款
 * Created by wmq on 2018/5/7.
 */

public class RepaymentAdapter extends BaseQuickAdapter<MyInvestBean.ItemBean,BaseViewHolder> {
    private List<MyInvestBean.ItemBean> item;

    public RepaymentAdapter(int layoutResId, @Nullable List<MyInvestBean.ItemBean> data) {
        super(layoutResId, data);
        this.item = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final MyInvestBean.ItemBean item) {
        helper.getView(R.id.huikuan_detil).setVisibility(View.VISIBLE);
        helper.getView(R.id.huankuanzhong).setBackgroundResource(R.drawable.invest_huankuan);
        helper.setText(R.id.huankuanzhong,"还款中");
        helper.setText(R.id.touziname,item.getName());
        helper.setText(R.id.sharp_type,item.getLoantype_format());
        helper.setText(R.id.nianhuashouyi,item.getRate()+"%");
        if(item.getRepay_time_type().equals("1")){

            helper.setText(R.id.touziqixian,item.getRepay_time()+"月");
        }else {
            helper.setText(R.id.touziqixian,item.getRepay_time()+"天");

        }
        helper.setText(R.id.touzijine,item.getMoney());
        helper.getView(R.id.biao_detil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到标的详情
                Intent intent  = new Intent();
                intent.setClass(mContext, YouBiaoActivity.class);
                intent.putExtra("id",item.getId());
                intent.putExtra("type",item.getDeal_status());
                mContext.startActivity(intent);
            }
        });
        helper.getView(R.id.huikuan_detil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到还款详情
                Intent intent  = new Intent();
                intent.setClass(mContext, RepaymentDetailActivity.class);
                intent.putExtra("id",item.getId());
                mContext.startActivity(intent);
            }
        });

    }
}
