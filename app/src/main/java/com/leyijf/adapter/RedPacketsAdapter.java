package com.leyijf.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.RedPaketsBean;
import com.leyijf.util.DateUtils;

import java.util.List;

/**
 * Created by wmq on 2018/5/24.
 */

public class RedPacketsAdapter extends BaseQuickAdapter<RedPaketsBean.VouchersBean,BaseViewHolder> {
    private List<RedPaketsBean.VouchersBean> vouchers;

    public RedPacketsAdapter(int layoutResId, @Nullable List<RedPaketsBean.VouchersBean> data) {
        super(layoutResId, data);
        this.vouchers = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, RedPaketsBean.VouchersBean item) {
        //处理数据显示
        if (item.getName().equals("邀请奖励")) {//判断红包类型
            helper.getView(R.id.count).setVisibility(View.VISIBLE);
            helper.setText(R.id.red_type, item.getName());
            helper.setText(R.id.count, "x"+item.getUse_limit());
            helper.setText(R.id.red_title, "单笔投资满" + item.getCan_use_limit() + "元");
            helper.setText(R.id.red_tiem_limit, "投资期限满" + item.getInvest_period_limit() + item.getInvest_period_type());
            helper.setText(R.id.red_sum, "￥" + item.getMoney());
            helper.setText(R.id.time, "有效期至" + DateUtils.strToDate(item.getEnd_time() + ""));
            helper.setText(R.id.red_use_type, "剩余" + item.getRemain_day() + "天过期");
        } else{
            helper.getView(R.id.red_layout).setBackgroundResource(R.drawable.use_background);
            helper.setText(R.id.red_use_type, "未过期");
            helper.setText(R.id.red_use_type, "剩余" + item.getRemain_day() + "天过期");
            helper.setText(R.id.red_type, item.getName());
            helper.setText(R.id.red_title, "单笔投资满" + item.getCan_use_limit() + "元");
            helper.setText(R.id.red_tiem_limit, "投资期限满" + item.getInvest_period_limit() + item.getInvest_period_type());
            helper.setText(R.id.red_sum, "￥" + item.getMoney());
            if(item.getEnd_time()>0){
                String data = String.valueOf(item.getEnd_time());
                helper.setText(R.id.time, "有效期至： " + DateUtils.time(data));
            }else{
                helper.setText(R.id.time, "有效期至： " + "无时间限制" );

            }
        }


    }
}
