package com.leyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.leyi.R;
import com.leyi.bean.GongGao;

import java.util.List;

/**
 * Created by Administrator on 2018/3/7.
 */

public class CompanyAdapter extends BaseAdapter {

    Context context;
    List<GongGao> list;

    public CompanyAdapter(Context context, List<GongGao> list) {
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
        view= LayoutInflater.from(context).inflate(R.layout.company_newsitem,null);
        TextView gonggao_title=view.findViewById(R.id.gonggao_title);
        gonggao_title.setText(list.get(i).getTitle()+"");


        return view;
    }
}
