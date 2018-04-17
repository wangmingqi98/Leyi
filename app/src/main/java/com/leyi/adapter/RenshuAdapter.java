package com.leyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.leyi.R;
import com.leyi.bean.TouziRenshuBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9.
 */

public class RenshuAdapter extends BaseAdapter {

    Context context;
    List<TouziRenshuBean.ObjectsBean.BuyRecordBean.RecordsBean> list;


    public RenshuAdapter(Context context, List<TouziRenshuBean.ObjectsBean.BuyRecordBean.RecordsBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.renshu_listitem, null);
            viewHolder.jia=view.findViewById(R.id.jia);
            viewHolder.qian=view.findViewById(R.id.qian);
            viewHolder.riqi=view.findViewById(R.id.riqi);
            viewHolder.name=view.findViewById(R.id.name);
            view.setTag(viewHolder);

        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
     if(list.get(i).getEcv_money().equals("0")&&list.get(i).getRate_money().equals("0.00")){
         viewHolder.jia.setText("未使用");
     }else if(!list.get(i).getEcv_money().equals("0")){
         viewHolder.jia.setText("+红包"+list.get(i).getEcv_money()+"元");
     }else if(!list.get(i).getRate_money().equals("0.00")){
         viewHolder.jia.setText("+加息券"+list.get(i).getRate_money()+"元");
     }

        viewHolder.qian.setText(list.get(i).getMoney()+".00");
        viewHolder.riqi.setText(list.get(i).getTime_his()+"");
        viewHolder.name.setText(list.get(i).getUser_name());




        return view;
    }

  class ViewHolder {

        public TextView name;
        public TextView qian;
        public TextView riqi;
        public TextView jia;



    }
}
