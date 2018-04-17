package com.leyi.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.leyi.R;
import com.leyi.bean.HBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class QListAdapter extends BaseAdapter {
    Context context;
    List<HBean.ArticleBean.ListBean> listBeen;


    public QListAdapter(Context context, List<HBean.ArticleBean.ListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }

    @Override
    public int getCount() {
        return listBeen.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.qlistitem, null);
       TextView tit=convertView.findViewById(R.id.tit);
       TextView content=convertView.findViewById(R.id.content);
        tit.setText(listBeen.get(position).getTitle());
        content.setText(Html.fromHtml(listBeen.get(position).getContent()));

        return convertView;
    }
}
