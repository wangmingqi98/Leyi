package com.leyi.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leyi.R;
import com.leyi.bean.RePayUser;
import com.leyi.bean.ToubiaoDetilsBean;
import com.leyi.view.fragment.fragment_touzi.FengxianControFragment;
import com.leyi.view.fragment.fragment_touzi.InsideUserInfoFragment;
import com.leyi.view.fragment.fragment_touzi.ProjectDetilsFragment;
import com.leyi.view.fragment.fragment_touzi.TouziRenshuFragment;
import com.leyi.weight.CustomizedProgressBar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/9.
 */

public class MyToubiaoAdapter extends BaseAdapter {

    Context context;
    FragmentManager fragmentManager;
    ToubiaoDetilsBean toubiaoDetilsBean;
    TouziRenshuFragment touziRenshuFragment;
     ProjectDetilsFragment projectDetilsFragment;
    InsideUserInfoFragment   insideUserInfoFragment;
            FengxianControFragment   fengxianControFragment;

    private ImageView mIvMyindentBack;
    private TabLayout mTablayoutMyindent;
    private ViewPager mViewpagerMyindent;
    private String[] mStringList = {"投资人数", "项目详情", "借者信息", "风险控制"};
    private List<Fragment> mList = new ArrayList<>();

RePayUser rePayUser;

    public MyToubiaoAdapter(Context context, FragmentManager fragmentManager, ToubiaoDetilsBean toubiaoDetilsBean, RePayUser rePayUser) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.toubiaoDetilsBean = toubiaoDetilsBean;
        this.rePayUser=rePayUser;

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
                view = LayoutInflater.from(context).inflate(R.layout.toubiao, null);
                TextView name = view.findViewById(R.id.name);
                TextView rate = view.findViewById(R.id.rate);
                CustomizedProgressBar progress = view.findViewById(R.id.progress);
                TextView need_money = view.findViewById(R.id.need_money);
                TextView repay_time = view.findViewById(R.id.repay_time);
                TextView min_loan_money = view.findViewById(R.id.min_loan_money);

                name.setText(toubiaoDetilsBean.getName() + "");
                rate.setText(toubiaoDetilsBean.getRate() + "%");
                progress.setCurrentCount(toubiaoDetilsBean.getProject());
                need_money.setText(toubiaoDetilsBean.getNeed_money());
                if(toubiaoDetilsBean.getRepay_time_type().equals("0")){
                    repay_time.setText(toubiaoDetilsBean.getRepay_time()+"天");
                }else {
                    repay_time.setText(toubiaoDetilsBean.getRepay_time()+"月");
                }
                min_loan_money.setText(toubiaoDetilsBean.getMin_loan_money() + "元");

                break;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.toubiao2, null);
                TextView need_money2=view.findViewById(R.id.need_money2);
                 TextView nianlilv=view.findViewById(R.id.nianlilv);
                 TextView jiezhi=view.findViewById(R.id.jiezhi);
                 TextView loantype_format=view.findViewById(R.id.loantype_format);


                need_money2.setText(toubiaoDetilsBean.getBorrow_amount()+"元");
                loantype_format.setText(toubiaoDetilsBean.getLoantype_format());
                jiezhi.setText(toubiaoDetilsBean.getRemain_time_format());

                break;
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.toubiao3, null);

                mTablayoutMyindent = (TabLayout) view.findViewById(R.id.tablayout_myindent);
                mViewpagerMyindent = (ViewPager) view.findViewById(R.id.viewpager_myindent);


                touziRenshuFragment=new TouziRenshuFragment();
                projectDetilsFragment=new ProjectDetilsFragment(toubiaoDetilsBean.getDescription(),toubiaoDetilsBean.getAttachment());
                insideUserInfoFragment=new InsideUserInfoFragment(rePayUser);
                fengxianControFragment=new FengxianControFragment(toubiaoDetilsBean);
                mList.add(touziRenshuFragment);
                mList.add(projectDetilsFragment);
                mList.add(insideUserInfoFragment);
                mList.add(fengxianControFragment);
                MyindentViewpagerAdapter adapter = new MyindentViewpagerAdapter(fragmentManager, this.context, mList, Arrays.asList(mStringList));
                mViewpagerMyindent.setAdapter(adapter);
                mTablayoutMyindent.setupWithViewPager(mViewpagerMyindent);
                mViewpagerMyindent.setCurrentItem(0);
               touziRenshuFragment.count= Integer.parseInt(toubiaoDetilsBean.getId());
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
