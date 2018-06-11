package com.leyijf.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.leyijf.R;
import com.leyijf.bean.InItBean;
import com.leyijf.view.activity.YouBiaoActivity;
import com.leyijf.weight.CustomizedProgressBar;

import java.util.List;

/**
 * Created by WMQ on 2018/5/22.
 */

public class HomeNovieceAdapter extends BaseAdapter {
    Context context;
    private List<InItBean.NewerBean> newer;
    int type;
    public HomeNovieceAdapter(Context context, List<InItBean.NewerBean> newer) {
        this.context = context;
        this.newer = newer;
    }

    @Override
    public int getCount() {
        return newer.size();
    }

    @Override
    public Object getItem(int position) {
        return newer.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView ==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_homenovice,null);
            holder.name = convertView.findViewById(R.id.da_name);
            holder.pro = convertView.findViewById(R.id.da_pro);
            holder.money = convertView.findViewById(R.id.da_shengyu);
            holder.progressBar = convertView.findViewById(R.id.progress);
            holder.schedul = convertView.findViewById(R.id.da_jindu);
            holder.invest = convertView.findViewById(R.id.invest);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(newer.get(position).getName());
        holder.pro.setText(newer.get(position).getRate()+"%");
        holder.money.setText(newer.get(position).getRemain_money()+"元");
        holder.progressBar.setCurrentCount(Float.valueOf(newer.get(position).getProgress_point()));
        holder.schedul.setText(newer.get(position).getProgress_point()+"%");
        switch (newer.get(position).getDeal_status()){
            case "1"://立即投资
                holder.invest.setText("立即投资");
                holder.invest.setBackgroundResource(R.drawable.btn_shape);
                break;
            case "4"://还款中
                holder.invest.setText("还款中");
                holder.invest.setBackgroundResource(R.drawable.hui_btn_shape);
                break;
            case "5"://已还清
                holder.invest.setText("已还清");
                holder.invest.setBackgroundResource(R.drawable.hui_btn_shape);
                break;
            default:
                break;

        }
        holder.invest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("type",newer.get(position).getDeal_status());
                intent.putExtra("id",newer.get(position).getDeal_id());
                intent.setClass(context,YouBiaoActivity.class);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
    class ViewHolder{
        TextView name;
        TextView pro;
        CustomizedProgressBar progressBar;
        TextView money;
        TextView schedul;
        TextView invest;
    }
}
