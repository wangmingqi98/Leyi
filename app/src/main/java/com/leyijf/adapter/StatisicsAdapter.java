package com.leyijf.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.StatisticsBean;

import java.util.List;

/**
 * Created by wmq on 2018/5/14.
 */

public class StatisicsAdapter extends BaseQuickAdapter<StatisticsBean.LoadListBean,BaseViewHolder> {
    private List<StatisticsBean.LoadListBean> load_list;
    public StatisicsAdapter(int layoutResId, @Nullable List<StatisticsBean.LoadListBean> data) {
        super(layoutResId, data);
        this.load_list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, StatisticsBean.LoadListBean item) {
        helper.setText(R.id.touzixiangmu_name,item.getName());
        helper.setText(R.id.shouyi_data,item.getTrue_repay_time());
        helper.setText(R.id.shouyi_money,"+"+item.getLoad_per_profit());
        helper.setText(R.id.shouyi_other,item.getLabel());

    }
}
