package com.leyijf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.leyijf.R;
import com.leyijf.bean.BackMoney;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9.
 */

public class BankMoneyDetilsAdapter extends BaseAdapter {

    Context context;
    List<BackMoney.ObjectsBean.LoanRepayListBean> loanRepayListBeen;

    public BankMoneyDetilsAdapter(Context context, List<BackMoney.ObjectsBean.LoanRepayListBean> loanRepayListBeen) {
        this.context = context;
        this.loanRepayListBeen = loanRepayListBeen;
    }

    @Override
    public int getCount() {
        return loanRepayListBeen.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.back_moneydetils_list, null);
            viewHolder.hukuan_name = (TextView) view.findViewById(R.id.hukuan_name);
            viewHolder.xiangmu_name = (TextView) view.findViewById(R.id.xiangmu_name);
            viewHolder.jiekuanjine = (TextView) view.findViewById(R.id.jiekuanjine);
            viewHolder.nianlilv = (TextView) view.findViewById(R.id.nianlilv);
            viewHolder.touziqixian = (TextView) view.findViewById(R.id.touziqixian);
            viewHolder.guanlifei = (TextView) view.findViewById(R.id.guanlifei);

            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }


        viewHolder.xiangmu_name.setText(loanRepayListBeen.get(i).getDate()+"");

        viewHolder.jiekuanjine.setText(loanRepayListBeen.get(i).getMonth_has_repay_money()+"");
        viewHolder.nianlilv.setText(loanRepayListBeen.get(i).getMonth_repay_money()+"");
        viewHolder.touziqixian.setText(loanRepayListBeen.get(i).getStatus()+"");






        return view;
    }

 class ViewHolder {

        public TextView hukuan_name;
        public TextView xiangmu_name;
        public TextView jiekuanjine;
        public TextView nianlilv;
        public TextView touziqixian;
        public TextView guanlifei;




    }
}
