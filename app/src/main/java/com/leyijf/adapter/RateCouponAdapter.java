package com.leyijf.adapter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.RateCouponBean;
import com.leyijf.util.DateUtils;

import java.util.List;

/**
 * 加息券
 * Created by wmq on 2018/5/3.
 */

public class RateCouponAdapter extends BaseQuickAdapter<RateCouponBean.RatesBean,BaseViewHolder> {
    List<RateCouponBean.RatesBean> rates;
    public RateCouponAdapter(int layoutResId, @Nullable List<RateCouponBean.RatesBean> data) {
        super(layoutResId, data);
        this.rates = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, RateCouponBean.RatesBean item) {
        if(item.getUse_limit()-item.getUse_count()<=0){//已使用
            String data = String.valueOf(item.getUse_time());
            Log.d(TAG, "convert: "+data);
            helper.setText(R.id.time, "有效期至： " + DateUtils.time(data));
            helper.setText(R.id.red_use_type, "已使用");
        }else {//未使用
            if (item.getToo_time_status() != 0) {//过期
                helper.getView(R.id.red_layout).setBackgroundResource(R.drawable.expire_background);
                helper.setText(R.id.red_use_type, "已过期");
            } else {//未过期
                helper.getView(R.id.red_layout).setBackgroundResource(R.drawable.use_background);

                if (item.getRemain_day() > 0) {
                    String data = String.valueOf(item.getEnd_time());
                    helper.setText(R.id.time, "有效期至： " + DateUtils.time(data));
                    helper.setText(R.id.red_use_type, "剩余" + item.getRemain_day() + "天过期");
                } else {
                    helper.setText(R.id.red_use_type, "无限制");
                    helper.setText(R.id.time, "有效期至： " + "无时间限制");


                }

            }
        }
        helper.setText(R.id.red_type,item.getName());
        helper.setText(R.id.red_title, "单笔投资满" + item.getCan_use_limit() + "元");
        helper.setText(R.id.red_tiem_limit, "投资期限满" + item.getInvest_period_limit() + item.getInvest_period_type());
        helper.setText(R.id.red_sum,  item.getMoney());


        }



}
