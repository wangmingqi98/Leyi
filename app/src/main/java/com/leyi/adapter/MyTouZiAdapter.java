package com.leyi.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.leyi.R;
import com.leyi.bean.UCLendBean;
import com.leyi.view.activity.BackMoneyActivity;
import com.leyi.view.activity.YouBiaoActivity;

import java.util.List;

/**
 * Created by Administrator on 2018/3/8.
 */

public class MyTouZiAdapter extends BaseAdapter {
    Context context;
    List<UCLendBean> lendBeanList;


    public MyTouZiAdapter(Context context, List<UCLendBean> lendBeanList) {
        this.context = context;
        this.lendBeanList = lendBeanList;
    }

    @Override
    public int getCount() {
        return lendBeanList.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder=new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.mytouzi_item, null);
            viewHolder.touziname = (TextView) view.findViewById(R.id.touziname);
            viewHolder.huankuanzhong = (TextView) view.findViewById(R.id.huankuanzhong);
            viewHolder.yiwancheng = (TextView) view.findViewById(R.id.yiwancheng);
            viewHolder.yiliubiao = (TextView) view.findViewById(R.id.yiliubiao);
            viewHolder.nianhuashouyi = (TextView) view.findViewById(R.id.nianhuashouyi);
            viewHolder.touziqixian = (TextView) view.findViewById(R.id.touziqixian);
            viewHolder.textView = (TextView) view.findViewById(R.id.textView);
            viewHolder.touzijine = (TextView) view.findViewById(R.id.touzijine);
            viewHolder.biao_detil = (Button) view.findViewById(R.id.biao_detil);
            viewHolder.huikuanDetil = (Button) view.findViewById(R.id.huikuan_detil);
            view.setTag(viewHolder);

        }else {
           viewHolder= (ViewHolder) view.getTag();
        }

         viewHolder.touziname.setText(lendBeanList.get(i).getName());
        viewHolder.nianhuashouyi.setText(lendBeanList.get(i).getRate_foramt_w());
        viewHolder.touziqixian.setText(lendBeanList.get(i).getRepay_time_format());
        viewHolder.touzijine.setText(lendBeanList.get(i).getMoney_format());
//
//        if(lendBeanList.get(i).getRepay_time_type().equals("0")){
//            viewHolder.touziqixian.setText(lendBeanList.get(i).getRepay_time()+"天");
//        }else {
//            viewHolder.touziqixian.setText(lendBeanList.get(i).getRepay_time()+"月");
//        }


        if(lendBeanList.get(i).getDeal_status().equals("0")){
            viewHolder.huankuanzhong.setVisibility(View.VISIBLE);
            viewHolder.yiliubiao.setVisibility(View.GONE);
            viewHolder.yiwancheng.setVisibility(View.GONE);
            viewHolder.huikuanDetil.setClickable(true);
            viewHolder.huankuanzhong.setText("等待中");
        }else if(lendBeanList.get(i).getDeal_status().equals("1")){
            viewHolder.huankuanzhong.setVisibility(View.VISIBLE);
            viewHolder.yiliubiao.setVisibility(View.GONE);
            viewHolder.yiwancheng.setVisibility(View.GONE);
            viewHolder.huikuanDetil.setClickable(true);
            viewHolder.huankuanzhong.setText("进行中");
        }else if(lendBeanList.get(i).getDeal_status().equals("2")){
            viewHolder.huankuanzhong.setVisibility(View.GONE);
            viewHolder.yiliubiao.setVisibility(View.GONE);
            viewHolder.yiwancheng.setVisibility(View.VISIBLE);
            viewHolder.huikuanDetil.setClickable(true);
            viewHolder.huankuanzhong.setText("已满标");
        }else if(lendBeanList.get(i).getDeal_status().equals("3")){
          ;
            viewHolder.huankuanzhong.setVisibility(View.GONE);
            viewHolder.yiliubiao.setVisibility(View.VISIBLE);
            viewHolder.yiwancheng.setVisibility(View.GONE);
            viewHolder.huikuanDetil.setClickable(false);
            viewHolder.yiliubiao.setText("已流标");
        }else if(lendBeanList.get(i).getDeal_status().equals("4")){
            viewHolder.huankuanzhong.setVisibility(View.VISIBLE);
            viewHolder.yiliubiao.setVisibility(View.GONE);
            viewHolder.yiwancheng.setVisibility(View.GONE);

            viewHolder.huikuanDetil.setClickable(true);
            viewHolder.huankuanzhong.setText("还款中");
        }else if(lendBeanList.get(i).getDeal_status().equals("5")){
            viewHolder.huankuanzhong.setVisibility(View.GONE);
            viewHolder.yiliubiao.setVisibility(View.GONE);
            viewHolder.yiwancheng.setVisibility(View.VISIBLE);
            viewHolder.huikuanDetil.setClickable(true);

            viewHolder.yiwancheng.setText("已还清");
        }

        viewHolder. huikuanDetil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                  * name : 货抵贷8号
//                        * rate_format : 13.58
//                        * money_format : ￥10,489.00
//                        * rate_foramt_w : 13.58%
//     * repay_time_format : 6个月
                Intent intent=new Intent(context,BackMoneyActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("name",lendBeanList.get(i).getName());
                bundle.putString("rate_format",lendBeanList.get(i).getRate_foramt_w());
                bundle.putString("money_format",lendBeanList.get(i).getMoney_format());
                bundle.putString("repay_time_format",lendBeanList.get(i).getRepay_time_format());
                bundle.putString("id",lendBeanList.get(i).getDeal_id());
                intent.putExtras(bundle);
                 context.startActivity(intent);
            }
        });

        viewHolder.biao_detil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,YouBiaoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("id",lendBeanList.get(i).getDeal_id());
                intent.putExtras(bundle);
                context.startActivity(intent);


            }
        });




        return view;
    }

      class ViewHolder {

        public TextView touziname;
        public TextView huankuanzhong;
        public TextView yiwancheng;
        public TextView yiliubiao;
        public TextView nianhuashouyi;
        public TextView touziqixian;
        public TextView textView;
        public TextView touzijine;
        public Button biao_detil;
        public Button huikuanDetil;



    }
}
