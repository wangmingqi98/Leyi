package com.leyijf.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leyijf.R;
import com.leyijf.bean.FinancialRepaymentDetailBean;
import com.leyijf.bean.ToubiaoDetilsBean;
import com.leyijf.util.DateUtils;
import com.leyijf.view.fragment.fragment_touzi.InsideUserInfoFragment;
import com.leyijf.view.fragment.fragment_touzi.ProjectDetilsFragment;
import com.leyijf.view.fragment.fragment_touzi.RiskControFragment;
import com.leyijf.view.fragment.fragment_touzi.TouziRenshuFragment;
import com.leyijf.weight.CustomizedProgressBar;
import com.leyijf.weight.MarqueeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by wmq on 2018/3/9.
 */

public class MyToubiaoAdapter extends BaseAdapter {
    public static final String TAG = "MyToubiaoAdapter";
    Context context;
    FragmentManager fragmentManager;
    ToubiaoDetilsBean toubiaoDetilsBean;
    TouziRenshuFragment touziRenshuFragment;
     ProjectDetilsFragment projectDetilsFragment;
    InsideUserInfoFragment   insideUserInfoFragment;
   RiskControFragment fengxianControFragment;
    private FinancialRepaymentDetailBean deal_detail;
    private ImageView mIvMyindentBack;
    private TabLayout mTablayoutMyindent;
    private ViewPager mViewpagerMyindent;
    private String[] mStringList = { "项目详情", "合同/抵押物", "投资人数","还款详情"};
    private String[] mStringtitle = { "项目详情", "合同/抵押物", "投资人数"};
    private List<Fragment> mList = new ArrayList<>();
    private int type;
    Handler handler = new Handler();
    public MyToubiaoAdapter(Context context, FragmentManager fragmentManager, FinancialRepaymentDetailBean deal_detail,int type) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.deal_detail = deal_detail;
        this.type = type;


    }

    @Override
    public int getCount() {
        return 3;
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
        switch (i) {
            case 0:
                Log.d(TAG, "getView:进来了吗 ");
                view = LayoutInflater.from(context).inflate(R.layout.toubiao, null);
                TextView rate = view.findViewById(R.id.rate);
                CustomizedProgressBar progress = view.findViewById(R.id.progress);
                final MarqueeView marqueeView = view.findViewById(R.id.marqueeview);
                TextView need_money = view.findViewById(R.id.need_money);
                TextView repay_time = view.findViewById(R.id.repay_time);
                TextView min_loan_money = view.findViewById(R.id.min_loan_money);
                rate.setText(deal_detail.getDeal_detail().getRate() + "%");
                progress.setCurrentCount(Float.valueOf(deal_detail.getDeal_detail().getProgress_point()));
                need_money.setText(deal_detail.getDeal_detail().getRemain_money());
                repay_time.setText(deal_detail.getDeal_detail().getRepay_time()+deal_detail.getDeal_detail().getRepay_time_type());
                min_loan_money.setText(deal_detail.getDeal_detail().getMin_loan_money() + "元");
                List list = new ArrayList();
                for(int j = 0;j<deal_detail.getBest_select_banner().size();j++){
                    list.add(deal_detail.getBest_select_banner().get(i).getTitle());
                }
                marqueeView.setTextArray(list);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        marqueeView.resume();
                    }
                },1000);
                break;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.toubiao2, null);
                TextView need_money2=view.findViewById(R.id.need_money2);
                 TextView nianlilv=view.findViewById(R.id.nianlilv);
                 TextView jiezhi=view.findViewById(R.id.jiezhi_date);
                 TextView loantype_format=view.findViewById(R.id.loantype_format);
                need_money2.setText(deal_detail.getDeal_detail().getBorrow_amount()+"元");
                nianlilv.setText(deal_detail.getDeal_detail().getDate_of_value());
                loantype_format.setText(deal_detail.getDeal_detail().getLoantype_format());
                jiezhi.setText(DateUtils.time(String.valueOf(deal_detail.getDeal_detail().getEnd_time())));

                break;
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.toubiao3, null);
                mTablayoutMyindent =  view.findViewById(R.id.tablayout_myindent);
                mViewpagerMyindent =  view.findViewById(R.id.viewpager_myindent);
                touziRenshuFragment=TouziRenshuFragment.newInstance(deal_detail.getDeal_detail().getDeal_id());
                projectDetilsFragment= ProjectDetilsFragment.newInstance(deal_detail.getDeal_detail().getDeal_id());
                insideUserInfoFragment= InsideUserInfoFragment.newInstance(deal_detail.getDeal_detail().getDeal_id());
                fengxianControFragment= RiskControFragment.newInstance(deal_detail.getDeal_detail().getDeal_id());
               if(type==1){
                   mList.add(insideUserInfoFragment);//项目详情
                   mList.add(projectDetilsFragment);//借款合同/照片
                   mList.add(touziRenshuFragment);//投资人数
                   MyindentViewpagerAdapter adapter = new MyindentViewpagerAdapter(fragmentManager, this.context, mList, Arrays.asList(mStringtitle));
                   mViewpagerMyindent.setAdapter(adapter);
                   mTablayoutMyindent.setupWithViewPager(mViewpagerMyindent);
                   mViewpagerMyindent.setCurrentItem(0);
               }else {
                   mList.add(insideUserInfoFragment);
                   mList.add(projectDetilsFragment);
                   mList.add(touziRenshuFragment);
                   mList.add(fengxianControFragment);//还款详情
                   MyindentViewpagerAdapter adapter = new MyindentViewpagerAdapter(fragmentManager, this.context, mList, Arrays.asList(mStringList));
                   mViewpagerMyindent.setAdapter(adapter);
                   mTablayoutMyindent.setupWithViewPager(mViewpagerMyindent);
                   mViewpagerMyindent.setCurrentItem(0);
               }
//               touziRenshuFragment.count= Integer.parseInt(toubiaoDetilsBean.getId());
                break;


        }


        return view;
    }



    /****
     * 传入具体日期 ，返回具体日期减一个月。
     *
     * @param
     *
     * @return 2014-03-20
     * @throws ParseException
     */
    //Day:日期字符串例如 2015-3-10  Num:需要减少的天数例如 7
    public  String subMonth(String day,int num) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date nowDate = null;
//        try {
//            nowDate = df.parse(day);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        //如果需要向后计算日期 -改为+
//        Date newDate2 = new Date(nowDate.getTime() - (long)num * 24 * 60 * 60 * 1000);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String dateOk = simpleDateFormat.format(newDate2);
        long l2 = Long.parseLong(day + "000");

        long l = (long) num * 24 * 60 * 60 * 1000;
        long l1 = l2 + l;
        Date date = new Date(l2);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);

    }

}
