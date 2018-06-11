package com.leyijf.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leyijf.R;
import com.leyijf.bean.MortGageInfoBean;
import com.leyijf.weight.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 借款合同与抵押物
 * Created by wmq on 2018/5/16.
 */

public class MortGageInfoAdapter extends BaseQuickAdapter<MortGageInfoBean.MortgageBean,BaseViewHolder> {
    private List<MortGageInfoBean.MortgageBean> mortgage;
    public MortGageInfoAdapter(int layoutResId, @Nullable List<MortGageInfoBean.MortgageBean> data) {
        super(layoutResId, data);
        this.mortgage = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, MortGageInfoBean.MortgageBean item) {
        RecyclerView gridView = helper.getView(R.id.gridview);
        gridView.setLayoutManager(new GridLayoutManager(mContext,2));
        gridView.addItemDecoration(new SpaceItemDecoration(30,2));
        List<MortGageInfoBean.MortgageBean.ContractBean> contract = new ArrayList<>();
        List<MortGageInfoBean.MortgageBean.ContractBean> contractInfos = new ArrayList<>();
            if(item.getType()==1){//合同
                helper.setText(R.id.hukuan_name,"借款合同");
                contract.addAll(item.getContract());
                MortGagePictureAdapter adapter = new MortGagePictureAdapter(R.layout.item_mortgage_img,contract);
                gridView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }else {//抵押物
                helper.setText(R.id.hukuan_name,"抵押物");
               for(int i = 0;i<item.getInfos().size();i++){
                   MortGageInfoBean.MortgageBean.ContractBean  contractBean = new MortGageInfoBean.MortgageBean.ContractBean();
                   contractBean.setImg(item.getInfos().get(i).getImg());
                   contractBean.setName(item.getInfos().get(i).getName());
                   contractInfos.add(contractBean);
               }
                MortGagePictureAdapter adapter = new MortGagePictureAdapter(R.layout.item_mortgage_img,contractInfos);
                gridView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }


        }




}
