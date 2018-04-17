package com.leyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leyi.R;
import com.leyi.bean.BankName;

import java.util.List;

/**
 * Created by Administrator on 2018/3/8.
 */

public class BankLimtAdapter extends BaseAdapter {
    Context context;
    List<BankName> list;

    public BankLimtAdapter(Context context, List<BankName> list) {
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
        ViewHolder viewHolder = null;
        if(viewHolder==null){
            viewHolder=new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.bank_limtitem, null);
            viewHolder.limt_bankcontent=view.findViewById(R.id.limt_bankcontent);
            viewHolder.limt_bankimg=view.findViewById(R.id.limt_bankimg);
            viewHolder.limt_bankname=view.findViewById(R.id.limt_bankname);
            view.setTag(viewHolder);


        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.limt_bankcontent.setText(list.get(i).getContent());
        viewHolder.limt_bankname.setText(list.get(i).getName());
        viewHolder.limt_bankimg.setImageResource(list.get(i).getImg());




        return view;
    }

     class ViewHolder {
        public ImageView limt_bankimg;
        public TextView limt_bankname;
        public TextView limt_bankcontent;



    }
}
