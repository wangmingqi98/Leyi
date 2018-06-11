package com.leyijf.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.auto.util.L;
import com.leyijf.bean.MyBorrowMoneyBean;
import com.leyijf.view.activity.AdvanceAlsoMoneyActivity;
import com.leyijf.view.activity.B_RepaymentDetailActivity;
import com.leyijf.view.activity.YouBiaoActivity;

import java.util.List;

/**
 * 我的借款--全部
 * Created by wmq on 2018/5/9.
 */

public class AllBorrowMoneyAdapter extends BaseQuickAdapter<MyBorrowMoneyBean.ItemBean,BaseViewHolder> {
    private List<MyBorrowMoneyBean.ItemBean> item;
    String type = null ;
    public AllBorrowMoneyAdapter(int layoutResId, @Nullable List<MyBorrowMoneyBean.ItemBean> data) {
        super(layoutResId, data);
        this.item = data;
        Log.d(TAG, "AllBorrowMoneyAdapter: "+item.size());
    }

    @Override
    protected void convert(BaseViewHolder helper, final MyBorrowMoneyBean.ItemBean item) {
        switch (item.getDeal_status()){
            case "1"://投资中
                helper.getView(R.id.repay_deal).setVisibility(View.GONE);
                helper.getView(R.id.advance_repay).setVisibility(View.GONE);
                helper.getView(R.id.huankuanzhong).setBackgroundResource(R.drawable.btn_shape);
                helper.setText(R.id.huankuanzhong,"投资中");
                helper.getView(R.id.biao_detil).setOnClickListener(new View.OnClickListener() {//标的详情
                    @Override
                    public void onClick(View v) {
                        Intent intent  = new Intent();
                        intent.setClass(mContext, YouBiaoActivity.class);
                        intent.putExtra("id",item.getId());
                        intent.putExtra("type",item.getDeal_status());
                        mContext.startActivity(intent);
                    }
                });
                break;
            case "2"://满标
                helper.getView(R.id.repay_deal).setVisibility(View.GONE);
                helper.getView(R.id.advance_repay).setVisibility(View.GONE);
                helper.getView(R.id.huankuanzhong).setBackgroundResource(R.drawable.btn_shape);
                helper.setText(R.id.huankuanzhong,"投资中");
                helper.getView(R.id.biao_detil).setOnClickListener(new View.OnClickListener() {//标的详情
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext,"已满标", Toast.LENGTH_LONG).show();

                    }
                });
                break;
            case "3"://流标
                helper.getView(R.id.repay_deal).setVisibility(View.GONE);
                helper.getView(R.id.advance_repay).setVisibility(View.GONE);
                helper.getView(R.id.huankuanzhong).setBackgroundResource(R.drawable.invest_liubiao);
                helper.setText(R.id.huankuanzhong,"已流标");
                helper.getView(R.id.biao_detil).setOnClickListener(new View.OnClickListener() {//标的详情
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext,"已流标", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case "4"://还款中
                helper.getView(R.id.biao_detil).setVisibility(View.GONE);
                helper.getView(R.id.repay_deal).setVisibility(View.VISIBLE);
                helper.getView(R.id.advance_repay).setVisibility(View.VISIBLE);
                helper.getView(R.id.huankuanzhong).setBackgroundResource(R.drawable.invest_huankuan);
                helper.setText(R.id.huankuanzhong,"还款中");
                helper.getView(R.id.biao_detil).setOnClickListener(new View.OnClickListener() {//标的详情
                    @Override
                    public void onClick(View v) {
                        Intent intent  = new Intent();
                        intent.setClass(mContext, YouBiaoActivity.class);
                        intent.putExtra("id",item.getId());
                        intent.putExtra("type",item.getDeal_status());
                        mContext.startActivity(intent);
                    }
                });
                break;
            case "5"://已还清
                helper.getView(R.id.biao_detil).setVisibility(View.GONE);
                helper.getView(R.id.repay_deal).setVisibility(View.VISIBLE);
                helper.getView(R.id.advance_repay).setVisibility(View.VISIBLE);
                helper.getView(R.id.huankuanzhong).setBackgroundResource(R.drawable.invest_huanqing);
                helper.setText(R.id.huankuanzhong,"已还清");
                helper.getView(R.id.biao_detil).setOnClickListener(new View.OnClickListener() {//标的详情
                    @Override
                    public void onClick(View v) {
                        Intent intent  = new Intent();
                        intent.setClass(mContext, YouBiaoActivity.class);
                        intent.putExtra("id",item.getId());
                        intent.putExtra("type",item.getDeal_status());
                        mContext.startActivity(intent);
                    }
                });
                break;
            default:
                break;

        }
        helper.setText(R.id.touziname,item.getName());
        helper.setText(R.id.describe,"借款金额(元)");
        helper.setText(R.id.sharp_type,item.getLoantype_format());
        helper.setText(R.id.nianhuashouyi,item.getRate()+"%");
        if(item.getRepay_time_type().equals("1")){

            helper.setText(R.id.touziqixian,item.getRepay_time()+"月");
        }else {
            helper.setText(R.id.touziqixian,item.getRepay_time()+"天");

        }
        helper.setText(R.id.touzijine,item.getBorrow_amount_format());

        helper.getView(R.id.repay_deal).setOnClickListener(new View.OnClickListener() {//还款详情
            @Override
            public void onClick(View v) {
                if(item.getDeal_status().equals("5")) {
                    Toast.makeText(mContext, "此借款已还清！", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("id", item.getId());
                    intent.setClass(mContext, B_RepaymentDetailActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });

        helper.getView(R.id.advance_repay).setOnClickListener(new View.OnClickListener() {//提前还款
            @Override
            public void onClick(View v) {

                if(item.getDeal_status().equals("5")){
                    Toast.makeText(mContext,"此借款已还清！",Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("id", item.getId());
                    intent.setClass(mContext, AdvanceAlsoMoneyActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });
    }
}
