package com.leyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.leyi.R;
import com.leyi.bean.VoucherBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/8.
 */

public class MyAddGuoQiAdapter extends BaseAdapter {
    Context context;
    List<VoucherBean> list;


    public MyAddGuoQiAdapter(Context context, List<VoucherBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.myadd_carviewitem3, null);
            viewHolder.danbi = (TextView) view.findViewById(R.id.danbi);
            viewHolder.touziqixian = (TextView) view.findViewById(R.id.touziqixian);
            viewHolder.youxaioqi = (TextView) view.findViewById(R.id.youxaioqi);
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
        public TextView youxaioqi;
        public TextView monry;
        public TextView name;


    }
}
