package com.leyijf.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.HotActiveBean;

import java.util.List;

/**
 * 热门活动
 * Created by Administrator on 2018/3/7.
 */

public class HotActiveAdapter  extends BaseQuickAdapter<HotActiveBean.HotactivityBean,BaseViewHolder>{
    private List<HotActiveBean.HotactivityBean> hotactivity;


    public HotActiveAdapter(int layoutResId, @Nullable List<HotActiveBean.HotactivityBean> data) {
        super(layoutResId, data);
        this.hotactivity = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, HotActiveBean.HotactivityBean item) {
        helper.setText(R.id.act_name,item.getName());
        Glide.with(mContext).load(item.getImg()).into((ImageView) helper.getView(R.id.act_img));

    }
}
