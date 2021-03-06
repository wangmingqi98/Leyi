package com.leyijf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.leyijf.R;
import com.leyijf.bean.VoucherBean;

import java.util.List;

import yyydjk.com.library.CouponView;

/**
 * Created by Administrator on 2018/3/8.
 */

public class MyAddAdapter extends BaseAdapter {


    Context context;
    List<VoucherBean> list;

    public MyAddAdapter(Context context, List<VoucherBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null){
            viewHolder=new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.myadd_carviewitem, null);
            viewHolder.danbi = (TextView) view.findViewById(R.id.danbi);
           viewHolder.touziqixian = (TextView) view.findViewById(R.id.touziqixian);
            viewHolder.couponView = (CouponView) view.findViewById(R.id.couponView);
            viewHolder.youxaioqi = (TextView) view.findViewById(R.id.youxaioqi);
            viewHolder.name= (TextView) view.findViewById(R.id.name);
            viewHolder.monry = (TextView) view.findViewById(R.id.money);
            view.setTag(viewHolder);

        }else {
            viewHolder= (ViewHolder) view.getTag();

        }

        viewHolder.danbi.setText("单笔投资满"+list.get(i).getCan_use_limit()+"元");
        viewHolder.touziqixian.setText("投资期限满"+list.get(i).getInvest_period_limit()+list.get(i).getInvest_period_type()+"");
        viewHolder.youxaioqi.setText("剩余"+list.get(i).getEnd_time()+"天结束");
        viewHolder.name.setText(list.get(i).getName()+"");
        viewHolder.monry.setText(list.get(i).getMoney()+"");



        return view;
    }

 class ViewHolder {

        public TextView danbi;
        public TextView touziqixian;
        public CouponView couponView;
        public TextView youxaioqi;
        public TextView monry;
        public TextView name;





    }
}
