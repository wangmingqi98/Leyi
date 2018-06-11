package com.leyijf.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.MyRedPacketsBean;
import com.leyijf.util.DateUtils;

import java.util.List;

/**
 * Created by wmq on 2018/5/3.
 */

public class RedPacketAdapter extends BaseQuickAdapter<MyRedPacketsBean.VouchersBean,BaseViewHolder>{
    List<MyRedPacketsBean.VouchersBean> list;
    public RedPacketAdapter(int layoutResId, @Nullable List<MyRedPacketsBean.VouchersBean> data) {
        super(layoutResId, data);
        this.list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyRedPacketsBean.VouchersBean item) {
        //处理数据显示
        if (item.getName().equals("邀请奖励")) {//判断红包类型
            helper.getView(R.id.count).setVisibility(View.VISIBLE);
            helper.setText(R.id.red_type, item.getName());
            helper.setText(R.id.count, "x"+item.getUse_limit());
            helper.setText(R.id.red_title, "单笔投资满" + item.getCan_use_limit() + "元");
            helper.setText(R.id.red_tiem_limit, "投资期限满" + item.getInvest_period_limit() + item.getInvest_period_type());
            helper.setText(R.id.red_sum, "￥" + item.getMoney());
            Log.d(TAG, "convert: "+item.getEnd_time());
            helper.setText(R.id.time, "有效期至" + DateUtils.time(item.getEnd_time() + ""));
            helper.setText(R.id.red_use_type, "剩余" + item.getRemain_day() + "天过期");
        } else{
            if ((Integer.parseInt(item.getUse_limit()) - Integer.parseInt(item.getUse_count())) > 0) {//未使用
                if (item.getToo_time_status() != 0) {//过期
                    helper.getView(R.id.red_layout).setBackgroundResource(R.drawable.expire_background);
                    helper.setText(R.id.red_use_type, "已过期");
                } else {//未过期
                    helper.getView(R.id.red_layout).setBackgroundResource(R.drawable.use_background);
                    helper.setText(R.id.red_use_type, "未过期");
                    helper.setText(R.id.red_use_type, "剩余" + item.getRemain_day() + "天过期");
                }
            } else {//已使用
                helper.getView(R.id.count).setVisibility(View.VISIBLE);
                helper.getView(R.id.count).setBackgroundResource(R.drawable.use_logo);
                helper.getView(R.id.red_layout).setBackgroundResource(R.drawable.use_background);
                helper.setText(R.id.red_use_type, "已使用");
            }
            helper.setText(R.id.red_type, item.getName());
            helper.setText(R.id.red_title, "单笔投资满" + item.getCan_use_limit() + "元");
            helper.setText(R.id.red_tiem_limit, "投资期限满" + item.getInvest_period_limit() + item.getInvest_period_type());
            helper.setText(R.id.red_sum, "￥" + item.getMoney());
            if(item.getEnd_time()>0){
                String data = String.valueOf(item.getEnd_time());
                Log.d(TAG, "convert: "+data);
                helper.setText(R.id.time, "有效期至： " + DateUtils.time(data));
            }else{
                helper.setText(R.id.time, "有效期至： " + "无时间限制" );

            }
    }




    }
}
