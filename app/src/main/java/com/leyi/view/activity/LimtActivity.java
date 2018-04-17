package com.leyi.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.leyi.R;
import com.leyi.adapter.BankLimtAdapter;
import com.leyi.base.BaseActivity;
import com.leyi.bean.BankName;

import java.util.ArrayList;
import java.util.List;

/**
 * 限额说明
 */
public class LimtActivity extends BaseActivity implements View.OnClickListener {

    private ImageView zhuce_back;

    private ListView limt_list;
    private BankLimtAdapter bankLimtAdapter;
    private List<String> list;
    private List<String> contentlist;
    private int[] arr;
    private List<BankName> bankNameList;


    @Override
    protected void initData() {
        bancontent();

        bankNameList = new ArrayList<>();
        for (int i = 0; i <list.size(); i++) {
            BankName bankName=new BankName(list.get(i),contentlist.get(i),arr[i]);
            bankNameList.add(bankName);

        }

        bankLimtAdapter = new BankLimtAdapter(this,bankNameList);
        limt_list.setAdapter(bankLimtAdapter);









    }

    private void bancontent() {

        list = new ArrayList<>();
        list.add("工商银行");
        list.add("农业银行");
        list.add("中国银行");
        list.add("建设银行");
        list.add("邮政储蓄银行");
        list.add("平安银行");
        list.add("光大银行");
        list.add("广发银行");
        list.add("中信银行");
        list.add("兴业银行");
        list.add("华夏银行");
        list.add("招商银行");
        list.add("浦发银行");
        list.add("交通银行");
        list.add("上海银行");


        contentlist = new ArrayList<>();
        contentlist.add("5万/笔，5万/日，100万/月");
        contentlist.add("5万/笔，5万/日，100万/月");
        contentlist.add("5万/笔，20万/日，100万/月");
        contentlist.add("5万/笔，10万/日，100万/月");
        contentlist.add("5000/笔，5000/日，100万/月");
        contentlist.add("5万/笔，5万/日，100万/月");
        contentlist.add("5万/笔，5万/日，100万/月");
        contentlist.add("10万/笔，20万/日，100万/月");
        contentlist.add("1000/笔，1000/日，2000/月");
        contentlist.add("5万/笔，5万/日，100万/月");
        contentlist.add("1000/笔，5000/日，100万/月");
        contentlist.add("5万/笔，5万/日，100万/月");
        contentlist.add("10万/笔，20万/日，100万/月");
        contentlist.add("5万/笔，5万/日，100万/月");
        contentlist.add("5万/笔，5万/日，100万/月");


        arr = new int[]{R.drawable.gs,R.drawable.ny,R.drawable.zg,R.drawable.js,R.drawable.yz,R.drawable.pa,R.drawable.gd,R.drawable.gf,R.drawable.zx,R.drawable.xy,R.drawable.hx,R.drawable.zs,R.drawable.sh,R.drawable.jt,R.drawable.shsh};








    }

    @Override
    protected void initId() {
        initView();
    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_limt;
    }

    private void initView() {
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        zhuce_back.setOnClickListener(this);
        limt_list = (ListView) findViewById(R.id.limt_list);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhuce_back:
                finish();
                break;




        }
    }
}
