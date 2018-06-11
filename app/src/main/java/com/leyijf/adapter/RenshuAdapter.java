package com.leyijf.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.TouziRenshuBean;

import java.util.List;

/**
 * Created by wmq on 2018/3/9.
 */

public class RenshuAdapter extends BaseQuickAdapter<TouziRenshuBean.BuyRecordBean,BaseViewHolder> {
    private List<TouziRenshuBean.BuyRecordBean> buy_record;

    public RenshuAdapter(int layoutResId, @Nullable List<TouziRenshuBean.BuyRecordBean> data) {
        super(layoutResId, data);
        this.buy_record = data;
    }


    @Override
    protected void convert(BaseViewHolder helper, TouziRenshuBean.BuyRecordBean item) {
        helper.setText(R.id.name,item.getUser_name());
        helper.setText(R.id.riqi,item.getTime_his());
        helper.setText(R.id.qian,item.getMoney());
        if(!item.getEcv_money().equals(0)&&item.getRate_money().equals("0.00")){

            helper.setText(R.id.jia,"+红包"+item.getEcv_money());
        }else if(item.getEcv_money().equals(0)&&!item.getRate_money().equals("0.00")){
            helper.setText(R.id.jia,"年化收益+"+item.getRate_money()+"%");
        }else {
            helper.setText(R.id.jia,"未使用");
        }

    }
}
