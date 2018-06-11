package com.leyijf.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.GongGao;

import java.util.List;

/**
 * Created by Administrator on 2018/3/7.
 */

public class CompanyAdapter extends BaseQuickAdapter<GongGao.NoticeListBean,BaseViewHolder> {

    List<GongGao.NoticeListBean> list;

    public CompanyAdapter(int layoutResId, List<GongGao.NoticeListBean> list) {
        super(layoutResId,list);
        this.list = list;
    }



    @Override
    protected void convert(BaseViewHolder helper, GongGao.NoticeListBean item) {
        helper.setText(R.id.content,item.getTitle());
    }




}
