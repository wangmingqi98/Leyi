package com.leyijf.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.leyijf.R;
import com.leyijf.bean.InItBean;
import com.leyijf.view.activity.RegisterActivity;
import com.leyijf.weight.CustomListView;

import java.util.List;

/**
 * Created by Administrator on 2018/3/5.
 */

public class HomeListAdapter extends BaseAdapter {

    Context context;
    private List<InItBean.RecommandXinyongBean> recommand_xinyong;
    private List<InItBean.RecommandGongyingBean> recommand_gongying;
    private List<InItBean.NewerBean> newer;
    public HomeListAdapter(Context context, List<InItBean.RecommandXinyongBean> recommand_xinyong, List<InItBean.RecommandGongyingBean> recommand_gongying, List<InItBean.NewerBean> newer) {
        this.context = context;
        this.recommand_xinyong = recommand_xinyong;
        this.recommand_gongying = recommand_gongying;
        this.newer = newer;
    }

    @Override
    public int getCount() {
        return 4;
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

        switch (i){
            case 0://邀请
                view = LayoutInflater.from(context).inflate(R.layout.home_oneitem, null);
                TextView now_register = view.findViewById(R.id.now_register);
                now_register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(context, RegisterActivity.class));

                    }
                });
                break;
            case 1://新手
                view = LayoutInflater.from(context).inflate(R.layout.home_twoitem, null);
                CustomListView listNovice = view.findViewById(R.id.list_novice);
                HomeNovieceAdapter novieceAdapter = new HomeNovieceAdapter(context,newer);
                listNovice.setAdapter(novieceAdapter);
                novieceAdapter.notifyDataSetChanged();
                break;
            case 2://产业链
                view = LayoutInflater.from(context).inflate(R.layout.home_twoitem, null);
                CustomListView listViewC = view.findViewById(R.id.list_novice);
                HomeDetailCAdapter detailCAdapter = new HomeDetailCAdapter(context,recommand_gongying);
                listViewC.setAdapter(detailCAdapter);
                detailCAdapter.notifyDataSetChanged();
                break;
            case 3://货抵贷
                if(recommand_xinyong.size()>0){
                    view = LayoutInflater.from(context).inflate(R.layout.home_twoitem, null);
                    CustomListView listViewH = view.findViewById(R.id.list_novice);
                    HomeDetailHAdapter detailHAdapter = new HomeDetailHAdapter(context,recommand_xinyong);
                    listViewH.setAdapter(detailHAdapter);
                    detailHAdapter.notifyDataSetChanged();

                }
                break;
            default:
                break;
        }




        return view;
    }


}
