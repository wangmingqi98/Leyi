package com.leyijf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leyijf.R;
import com.leyijf.bean.SelectorBank;
import com.leyijf.http.GetUrl;

import java.util.List;

/**
 * Created by Administrator on 2018/3/30.
 */

public class SelectoeBankAdapter extends BaseAdapter {

Context context;
    List<SelectorBank> list;

    public SelectoeBankAdapter(Context context, List<SelectorBank> list) {
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
    public View getView(int i, View view, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(viewHolder==null){
            viewHolder=new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.selectorbankitem, null);
            viewHolder.limt_bankimg=view.findViewById(R.id.limt_bankimg);
            viewHolder.limt_bankname=view.findViewById(R.id.limt_bankname);
            viewHolder.biaozhi=view.findViewById(R.id.biaozhi);
            view.setTag(viewHolder);


        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
//        https://leyibank.com//public/bank/SPDB.png
        viewHolder.limt_bankname.setText(list.get(i).getName());
        Glide.with(context).load(GetUrl.BASEIMG+list.get(i).getIcon()).into(viewHolder.limt_bankimg);





        return view;
    }


    class ViewHolder {
        public ImageView limt_bankimg;
        public TextView limt_bankname;
        public ImageView biaozhi;



    }






}
