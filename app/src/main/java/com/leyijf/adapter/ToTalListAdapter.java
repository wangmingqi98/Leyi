package com.leyijf.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.leyijf.R;
import com.leyijf.bean.UCMoneyLogBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/23.
 */

public class ToTalListAdapter extends BaseAdapter {

Context context;

    List<UCMoneyLogBean.ObjectsBean.ItemBean> list;

    public ToTalListAdapter(Context context, List<UCMoneyLogBean.ObjectsBean.ItemBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView= LayoutInflater.from(context).inflate(R.layout.shouyi_item,null);
        TextView touzixiangmu_name=convertView.findViewById(R.id.touzixiangmu_name);
        TextView shouyi_money=convertView.findViewById(R.id.shouyi_money);
        TextView shouyi_data=convertView.findViewById(R.id.shouyi_data);
        TextView shouyi_other=convertView.findViewById(R.id.shouyi_other);
        String[] split = list.get(position).getLog_info().split(",");
        String substring_name = split[0].substring(0, split[0].length());
        Log.e("-----subname",substring_name+"");

        touzixiangmu_name.setText(substring_name+"");
        shouyi_money.setText(list.get(position).getMoney_format()+"");
        shouyi_data.setText(list.get(position).getLog_time_format()+"");
        String other="";
        for (int i = 1; i < split.length; i++) {
            other=other+split[i];

        }
        shouyi_other.setText(other);



        return convertView;
    }
}
