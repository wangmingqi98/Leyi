package com.leyijf.adapter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.TransactionRecordsBean;
import com.leyijf.util.DateUtils;

import java.util.List;

/**
 * Created by wmq on 2018/3/7.
 */

public class MyAdentListAdapter extends BaseQuickAdapter<TransactionRecordsBean.ItemBean,BaseViewHolder> {
    private List<TransactionRecordsBean.ItemBean> item;

    public MyAdentListAdapter(int layoutResId, @Nullable List<TransactionRecordsBean.ItemBean> data) {
        super(layoutResId, data);
        this.item = data;
    }


    @Override
    protected void convert(BaseViewHolder helper, TransactionRecordsBean.ItemBean item) {
        helper.setText(R.id.zhonglei,item.getTitle());
        helper.setText(R.id.price,item.getMoney());
        helper.setText(R.id.zhonglei_name,item.getMemo());

        String time =  DateUtils.data(item.getCreate_time());
        Log.d(TAG, "convert: "+time);
        String year = DateUtils.year(time);
        String month = DateUtils.month(time);
        helper.setText(R.id.month,month);
        helper.setText(R.id.year,year+"年");


    }
}
