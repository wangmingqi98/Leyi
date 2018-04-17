package com.leyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leyi.R;
import com.leyi.bean.UCMoneyLogBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/7.
 */

public class MyAdentListAdapter extends BaseAdapter {
    Context context;
    List<UCMoneyLogBean.ObjectsBean.ItemBean> shouyilist;


    public MyAdentListAdapter(Context context, List<UCMoneyLogBean.ObjectsBean.ItemBean> shouyilist) {
        this.context = context;
        this.shouyilist = shouyilist;
    }

    @Override
    public int getCount() {
        return shouyilist.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.my_ident_item, null);
            viewHolder.riqi = view.findViewById(R.id.riqi);
            viewHolder.nian = view.findViewById(R.id.nian);
            viewHolder.zhonglei = view.findViewById(R.id.zhonglei);
            viewHolder.zhonglei_name = view.findViewById(R.id.zhonglei_name);
            viewHolder.price = view.findViewById(R.id.price);
            viewHolder.zhuangtai = view.findViewById(R.id.zhuangtai);
            viewHolder.my_ident_item = view.findViewById(R.id.my_ident_item);

            view.setTag(viewHolder);

        }else {
            viewHolder= (ViewHolder) view.getTag();

        }

        viewHolder.zhonglei.setText(shouyilist.get(i).getLog_info()+"");
        String[] split = shouyilist.get(i).getLog_time_format().split(" ");
        viewHolder.nian.setText(split[0]);
        viewHolder.riqi.setText(split[1]);
        if(shouyilist.get(i).getLog_info().contains("提现")){
            viewHolder.zhonglei.setText("提现");
        }
        if(shouyilist.get(i).getLog_info().contains("充值")){
            viewHolder.zhonglei.setText("充值");
        }
        if(shouyilist.get(i).getLog_info().contains("投标")||
               shouyilist.get(i).getLog_info().contains("回报")){
            viewHolder.zhonglei.setText("投资");
        }

        viewHolder.zhonglei_name.setText(shouyilist.get(i).getLog_info());
        viewHolder.price.setText(shouyilist.get(i).getMoney());

        return view;
    }

    class ViewHolder {

        public TextView  riqi;
        public TextView nian;
        public TextView zhonglei;
        public TextView zhonglei_name;
        public TextView price;
        public TextView zhuangtai;
        public LinearLayout my_ident_item;



    }
}
