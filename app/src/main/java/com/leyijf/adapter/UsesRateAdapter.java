package com.leyijf.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.UsesRateBean;
import com.leyijf.util.DateUtils;

import java.util.List;

/**
 * Created by wmq on 2018/5/24.
 */

public class UsesRateAdapter extends BaseQuickAdapter<UsesRateBean.RatesBean,BaseViewHolder> {
    private List<UsesRateBean.RatesBean> rates;

    public UsesRateAdapter(int layoutResId, @Nullable List<UsesRateBean.RatesBean> data) {
        super(layoutResId, data);
        this.rates = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, UsesRateBean.RatesBean item) {
        helper.getView(R.id.red_layout).setBackgroundResource(R.drawable.use_background);
        if(item.getRemain_day()>0){
            String data = String.valueOf(item.getEnd_time());
            helper.setText(R.id.time, "有效期至： " + DateUtils.time(data));
            helper.setText(R.id.red_use_type, "剩余" + item.getRemain_day() + "天过期");
        }else {
            helper.setText(R.id.red_use_type, "无限制");
            helper.setText(R.id.time, "有效期至： " + "无时间限制" );


        }


        helper.setText(R.id.red_type,item.getName());
        helper.setText(R.id.red_title, "单笔投资满" + item.getCan_use_limit() + "元");
        helper.setText(R.id.red_tiem_limit, "投资期限满" + item.getInvest_period_limit() + item.getInvest_period_type());
        helper.setText(R.id.red_sum,  item.getMoney());



}
}
