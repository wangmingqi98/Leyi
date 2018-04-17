package com.leyi.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leyi.R;
import com.leyi.bean.InItBean;
import com.leyi.view.activity.YouBiaoActivity;
import com.leyi.weight.CustomizedProgressBar;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */

public class NoviceListAdapter extends BaseAdapter {
    Context context;
    List<InItBean> inItBeanList;

    public NoviceListAdapter(Context context, List<InItBean> inItBeanList) {
        this.context = context;
        this.inItBeanList = inItBeanList;
    }

    @Override
    public int getCount() {
        return inItBeanList.size();
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

        view = LayoutInflater.from(context).inflate(R.layout.home_threeitem, null);
        CustomizedProgressBar progressBar2 = view.findViewById(R.id.progress);
        TextView project_name=view.findViewById(R.id.project_name);
        ImageView red_bank=view.findViewById(R.id.red_bank);
        ImageView yellow_bank=view.findViewById(R.id.yellow_bank);
        TextView project=view.findViewById(R.id.project);
        TextView rate_foramt=view.findViewById(R.id.rate_foramt);
        TextView repay_time=view.findViewById(R.id.repay_time);
        TextView min_loan_money_fromat=view.findViewById(R.id.min_loan_money_fromat);
        TextView need_money=view.findViewById(R.id.need_money);
        TextView progress_point=view.findViewById(R.id.progress_point);
        TextView banck_money=view.findViewById(R.id.banck_money);
        TextView now_repay=view.findViewById(R.id.now_repay);
        project_name.setText(inItBeanList.get(i).getName());
        project.setText(inItBeanList.get(i).getDeal_labels());
        if(project.getText().equals("产业链")){
            red_bank.setVisibility(View.VISIBLE);
            yellow_bank.setVisibility(View.GONE);

        }else {
            red_bank.setVisibility(View.GONE);
            yellow_bank.setVisibility(View.VISIBLE);

        }
        rate_foramt.setText(inItBeanList.get(i).getRate()+"%");
        if(inItBeanList.get(i).getRepay_time_type().equals("0")){
            repay_time.setText(inItBeanList.get(i).getRepay_time()+"天");
        }else {
            repay_time.setText(inItBeanList.get(i).getRepay_time()+"月");
        }


        min_loan_money_fromat.setText(inItBeanList.get(i).getMin_loan_money()+"元");
        need_money.setText(inItBeanList.get(i).getNeed_money());




        if(inItBeanList.get(i).getProgress_point()==0&&inItBeanList.get(i).getNeed_money().startsWith("0",1)){
            banck_money.setVisibility(View.VISIBLE);
            now_repay.setVisibility(View.GONE);
            progress_point.setText("100%");
            progressBar2.setCurrentCount(100);
            if(inItBeanList.get(i).getDeal_status().equals("0")){
                banck_money.setText("等待中");
            }else if(inItBeanList.get(i).getDeal_status().equals("1")){
                banck_money.setText("进行中");
            }else if(inItBeanList.get(i).getDeal_status().equals("2")){
                banck_money.setText("已满标");
            }else if(inItBeanList.get(i).getDeal_status().equals("3")){
                banck_money.setText("流标");
            }else if(inItBeanList.get(i).getDeal_status().equals("4")){
                banck_money.setText("还款中");
            }else if(inItBeanList.get(i).getDeal_status().equals("5")){
                banck_money.setText("已还清");
            }


        }else if(inItBeanList.get(i).getProgress_point()==100) {

            banck_money.setVisibility(View.VISIBLE);
            now_repay.setVisibility(View.GONE);
            progress_point.setText("100%");
            progressBar2.setCurrentCount(100);
            if(inItBeanList.get(i).getDeal_status().equals("0")){
                banck_money.setText("等待中");
            }else if(inItBeanList.get(i).getDeal_status().equals("1")){
                banck_money.setText("进行中");
            }else if(inItBeanList.get(i).getDeal_status().equals("2")){
                banck_money.setText("已满标");
            }else if(inItBeanList.get(i).getDeal_status().equals("3")){
                banck_money.setText("流标");
            }else if(inItBeanList.get(i).getDeal_status().equals("4")){
                banck_money.setText("还款中");
            }else if(inItBeanList.get(i).getDeal_status().equals("5")){
                banck_money.setText("已还清");
            }


        }else {

            banck_money.setVisibility(View.GONE);
            now_repay.setVisibility(View.VISIBLE);
            progressBar2.setCurrentCount(inItBeanList.get(i).getProgress_point());
            progress_point.setText(inItBeanList.get(i).getProgress_point()+"%");

        }


        banck_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,YouBiaoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("id",inItBeanList.get(i).getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        now_repay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,YouBiaoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("id",inItBeanList.get(i).getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });





        return view;
    }
}
