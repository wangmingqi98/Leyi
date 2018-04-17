package com.leyi.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leyi.R;
import com.leyi.auto.util.GlideImage;
import com.leyi.bean.InItBean;
import com.leyi.bean.SystemBean;
import com.leyi.view.activity.BannerShowActivity;
import com.leyi.view.activity.RegisterActivity;
import com.leyi.view.activity.YouBiaoActivity;
import com.leyi.weight.CustomizedProgressBar;
import com.leyi.weight.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

/**
 * Created by Administrator on 2018/3/5.
 */

public class HomeListAdapter extends BaseAdapter {

    Context context;
    private TextView now_register;
    private List list;
    List<InItBean> inItBeanList;
    private List text;
SystemBean systemBean;
    public   MarqueeView marqueeView;

    Handler handler=new Handler();


    public HomeListAdapter(Context context, List list, List<InItBean> inItBeanList, List text, SystemBean systemBean) {
        this.context = context;
        this.list = list;
        this.inItBeanList = inItBeanList;
        this.text = text;
        this.systemBean = systemBean;
    }

    @Override
    public int getCount() {
        return inItBeanList.size() + 3;
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

        if (i == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.home_headeritem, null);
            Banner banner = view.findViewById(R.id.banner);
            marqueeView = view.findViewById(R.id.marqueeview);
            marqueeView.setTextArray(text);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    marqueeView.resume();
                }
            },1000);


            banner.setImages(list)//添加图片集合或图片url集合
                    .setDelayTime(2000)//设置轮播时间
                    .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                    .setImageLoader(new GlideImage())//加载图片
                    .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)  //设置数字指示器
                    .setIndicatorGravity(BannerConfig.CENTER)//设置指示器位置
                    .start();

                   banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    context.startActivity(new Intent(context,BannerShowActivity.class));

                }
            });



        } else if (i == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.home_oneitem, null);
            now_register = view.findViewById(R.id.now_register);
            now_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, RegisterActivity.class));

                }
            });
        } else if (i == 2) {
            view = LayoutInflater.from(context).inflate(R.layout.home_twoitem, null);
            CustomizedProgressBar progressBar = view.findViewById(R.id.progress);
            TextView now_registers = view.findViewById(R.id.now_register);
            TextView da_name=view.findViewById(R.id.da_name);
            TextView da_pro=view.findViewById(R.id.da_pro);
            TextView da_shengyu=view.findViewById(R.id.da_shengyu);
            TextView da_jindu=view.findViewById(R.id.da_jindu);
            TextView banck_moneys=view.findViewById(R.id.banck_money);
            da_name.setText(inItBeanList.get(i-2).getName()+"");

            String substring = inItBeanList.get(i - 2).getNeed_money().substring(1, inItBeanList.get(i - 2).getNeed_money().length());
            Log.e("---substring---",substring+"");

            if(inItBeanList.get(i-2).getRate().equals("0")&&substring.equals("0")){
               da_jindu.setText("100%");
                progressBar.setCurrentCount(100);
                now_registers.setVisibility(View.GONE);
                banck_moneys.setVisibility(View.VISIBLE);


                if(inItBeanList.get(i-2).getDeal_status().equals("0")){
                    banck_moneys.setText("等待中");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("1")){
                    banck_moneys.setText("进行中");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("2")){
                    banck_moneys.setText("已满标");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("3")){
                    banck_moneys.setText("流标");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("4")){
                    banck_moneys.setText("还款中");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("5")){
                    banck_moneys.setText("已还清");
                }




            }else {
                da_pro.setText(inItBeanList.get(i-2).getRate()+"%");
                da_shengyu.setText(inItBeanList.get(i-2).getNeed_money()+"");
                da_jindu.setText(inItBeanList.get(i-2).getProgress_point()+"%");
                progressBar.setCurrentCount(inItBeanList.get(i-2).getProgress_point());
                now_registers.setVisibility(View.VISIBLE);
                banck_moneys.setVisibility(View.GONE);

                if(inItBeanList.get(i-2).getDeal_status().equals("0")){
                    now_registers.setText("等待中");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("1")){
                    now_registers.setText("进行中");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("2")){
                    now_registers.setText("已满标");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("3")){
                    now_registers.setText("流标");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("4")){
                    now_registers.setText("还款中");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("5")){
                    now_registers.setText("已还清");
                }



            }

            now_registers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,YouBiaoActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("id",inItBeanList.get(i-2).getId());
                    intent.putExtras(bundle);
                    context.startActivity(intent);


                }
            });
            banck_moneys.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,YouBiaoActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("id",inItBeanList.get(i-2).getId());
                    intent.putExtras(bundle);
                    context.startActivity(intent);


                }
            });






        } else if (i == inItBeanList.size() +2) {
            view = LayoutInflater.from(context).inflate(R.layout.home_bottomitem, null);

            TextView textView1=view.findViewById(R.id.di_tv1);
            TextView textView2=view.findViewById(R.id.di_tv2);
            textView1.setText(systemBean.getVirtual_money_2());
            textView2.setText(systemBean.getVirtual_money_1());






        } else {
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
            project_name.setText(inItBeanList.get(i-2).getName());
            project.setText(inItBeanList.get(i-2).getDeal_labels());
            if(project.getText().equals("产业链")){
                red_bank.setVisibility(View.VISIBLE);
                yellow_bank.setVisibility(View.GONE);

            }else {
                red_bank.setVisibility(View.GONE);
                yellow_bank.setVisibility(View.VISIBLE);

            }
            rate_foramt.setText(inItBeanList.get(i-2).getRate()+"%");
            if(inItBeanList.get(i-2).getRepay_time_type().equals("0")){
                repay_time.setText(inItBeanList.get(i-2).getRepay_time()+"天");
            }else {
                repay_time.setText(inItBeanList.get(i-2).getRepay_time()+"月");
            }
            min_loan_money_fromat.setText(inItBeanList.get(i-2).getMin_loan_money());
            need_money.setText(inItBeanList.get(i-2).getNeed_money());

            if(inItBeanList.get(i-2).getProgress_point()==0&&inItBeanList.get(i-2).getNeed_money().startsWith("0",1)){
                banck_money.setVisibility(View.VISIBLE);
                now_repay.setVisibility(View.GONE);
                progress_point.setText("100%");
                progressBar2.setCurrentCount(100);
                if(inItBeanList.get(i-2).getDeal_status().equals("0")){
                     banck_money.setText("等待中");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("1")){
                    banck_money.setText("进行中");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("2")){
                    banck_money.setText("已满标");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("3")){
                    banck_money.setText("流标");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("4")){
                    banck_money.setText("还款中");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("5")){
                    banck_money.setText("已还清");
                }


            }else if(inItBeanList.get(i-2).getProgress_point()==100){
                banck_money.setVisibility(View.VISIBLE);
                now_repay.setVisibility(View.GONE);
                progress_point.setText("100%");
                progressBar2.setCurrentCount(100);
                if(inItBeanList.get(i-2).getDeal_status().equals("0")){
                    banck_money.setText("等待中");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("1")){
                    banck_money.setText("进行中");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("2")){
                    banck_money.setText("已满标");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("3")){
                    banck_money.setText("流标");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("4")){
                    banck_money.setText("还款中");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("5")){
                    banck_money.setText("已还清");
                }
            }


            else {
                banck_money.setVisibility(View.GONE);
                now_repay.setVisibility(View.VISIBLE);
                progressBar2.setCurrentCount(inItBeanList.get(i-2).getProgress_point());
                progress_point.setText(inItBeanList.get(i-2).getProgress_point()+"%");
                if(inItBeanList.get(i-2).getDeal_status().equals("0")){
                    now_repay.setText("等待中");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("1")){
                    now_repay.setText("进行中");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("2")){
                    now_repay.setText("已满标");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("3")){
                    now_repay.setText("流标");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("4")){
                    now_repay.setText("还款中");
                }else if(inItBeanList.get(i-2).getDeal_status().equals("5")){
                    now_repay.setText("已还清");
                }



            }
            banck_money.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,YouBiaoActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("id",inItBeanList.get(i-2).getId());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            now_repay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,YouBiaoActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("id",inItBeanList.get(i-2).getId());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });







        }


        return view;
    }


}
