package com.leyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leyi.R;
import com.leyi.bean.BankCardBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/27.
 */

public class BankListAdapter extends BaseAdapter {

    List<BankCardBean> list;
    Context context;


    public BankListAdapter(List<BankCardBean> list, Context context) {
        this.list = list;
        this.context = context;
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
        ViewHolder holder = null;
        if(convertView==null){
            holder = new ViewHolder();
        convertView = LayoutInflater.from(context).inflate(R.layout.bankcarditem, null);
        holder. bank_img=convertView.findViewById(R.id.bank_img);
        holder.bank_name=convertView.findViewById(R.id.bank_name);
        holder.bank_behind=convertView.findViewById(R.id.bank_behind);
        holder.bank_lin=convertView.findViewById(R.id.bank_lin);
        holder.xuanze = convertView.findViewById(R.id.xuanze);
        convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
            Glide.with(context).load(list.get(position).getImg()).into(holder.bank_img);
            holder.bank_name.setText(list.get(position).getBank_name());
            holder.bank_behind.setText("尾号" + list.get(position).getBankcode().substring(list.get(position).getBankcode().length() - 4, list.get(position).getBankcode().length()));
            if (position == 0) {
                holder.xuanze.setVisibility(View.VISIBLE);
            } else {
                holder.xuanze.setVisibility(View.GONE);
            }


        return convertView;
    }
    class ViewHolder{
         ImageView bank_img;
         TextView bank_name;
         TextView bank_behind;
         LinearLayout bank_lin;
         ImageView xuanze;

    }
}
