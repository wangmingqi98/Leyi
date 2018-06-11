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

public class HomeDetailHAdapter extends BaseAdapter {
    Context context;
    private List<InItBean.RecommandXinyongBean> recommand_xinyong;
    int type;
    public HomeDetailHAdapter(Context context, List<InItBean.RecommandXinyongBean> recommand_xinyong) {
        this.context = context;
        this.recommand_xinyong = recommand_xinyong;
    }

    @Override
    public int getCount() {
        return recommand_xinyong.size();
    }

    @Override
    public Object getItem(int position) {
        return recommand_xinyong.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            holder  = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.home_threeitem,null);
            holder.name = convertView.findViewById(R.id.project_name);
            holder.project = convertView.findViewById(R.id.project);
            holder.rate_foramt = convertView.findViewById(R.id.rate_foramt);
            holder.repay_time =convertView.findViewById(R.id.repay_time);
            holder.min_loan_money_fromat = convertView.findViewById(R.id.min_loan_money_fromat);
            holder.progress = convertView.findViewById(R.id.progress);
            holder.progress_point = convertView.findViewById(R.id.progress_point);
            holder.need_money = convertView.findViewById(R.id.need_money);
            holder.now_repay = convertView.findViewById(R.id.now_repay);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(recommand_xinyong.get(position).getName());
        holder.rate_foramt.setText(recommand_xinyong.get(position).getRate()+"%");
        holder.repay_time.setText(recommand_xinyong.get(position).getRepay_time()+recommand_xinyong.get(position).getRepay_time_type());
        holder.min_loan_money_fromat.setText(recommand_xinyong.get(position).getMin_loan_money());
        holder.progress.setCurrentCount(Float.valueOf(recommand_xinyong.get(position).getProgress_point()));
        holder.progress_point.setText(recommand_xinyong.get(position).getProgress_point()+"%");
        holder.need_money.setText(recommand_xinyong.get(position).getRemain_money());
        switch (recommand_xinyong.get(position).getDeal_status()){
            case "1"://立即投资
                holder.now_repay.setText("立即投资");
                holder.now_repay.setBackgroundResource(R.drawable.btn_shape);
                break;
            case "4"://还款中
                holder.now_repay.setText("还款中");
                holder.now_repay.setBackgroundResource(R.drawable.hui_btn_shape);
                break;
            case "5"://已还清
                holder.now_repay.setText("已还清");
                holder.now_repay.setBackgroundResource(R.drawable.hui_btn_shape);
                break;
            default:
                break;

        }
        holder.now_repay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("type",recommand_xinyong.get(position).getDeal_status());
                intent.putExtra("id",recommand_xinyong.get(position).getDeal_id());
                intent.setClass(context,YouBiaoActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        TextView name;
        TextView project;
        TextView rate_foramt;
        TextView repay_time;
        TextView min_loan_money_fromat;
        CustomizedProgressBar progress;
        TextView need_money;
        TextView progress_point;
        TextView now_repay;
    }
}
