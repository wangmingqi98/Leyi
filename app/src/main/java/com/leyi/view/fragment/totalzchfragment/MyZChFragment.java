package com.leyi.view.fragment.totalzchfragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.leyi.R;
import com.leyi.base.BaseFragment;
import com.leyi.bean.UserInfo;
import com.leyi.weight.SelfStatistics;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyZChFragment extends BaseFragment {


    private SelfStatistics selfStatistics;
    private TextView keyongyue;
    private TextView daishoubenjin;
    private TextView daishoulixi;
    private TextView touzidongjie;
    private TextView shenqingtixian;
    private TextView shoutoumane;

    public MyZChFragment() {
        // Required empty public constructor
    }




    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        selfStatistics = view.findViewById(R.id.progress);
        float datas[] = new float[]{ Float.parseFloat(UserInfo.getInstance().getUseful_money().substring(0,UserInfo.getInstance().getUseful_money().length()-1)),
                Float.parseFloat(UserInfo.getInstance().getBenJin().substring(0,UserInfo.getInstance().getBenJin().length()-1)),
                Float.parseFloat(UserInfo.getInstance().getWaitLiXi().substring(0,UserInfo.getInstance().getWaitLiXi().length()-1))
        };
        selfStatistics.setDatas(datas);
        selfStatistics.startDraw();

        keyongyue = view.findViewById(R.id.keyongyue);
        daishoubenjin = view.findViewById(R.id.daishoubenjin);
        daishoulixi = view.findViewById(R.id.daishoulixi);
        touzidongjie = view.findViewById(R.id.touzidongjie);
        shenqingtixian = view.findViewById(R.id.shenqingtixian);
        shoutoumane = view.findViewById(R.id.shoutoumane);







    }

    @Override
    protected void initData() {
//        Float.parseFloat(UserInfo.getInstance().getUseful_money().substring(0,UserInfo.getInstance().getUseful_money().length()));
//        Float.parseFloat(UserInfo.getInstance().getUseful_money().substring(0,UserInfo.getInstance().getTotalMoney().length()));
//        Float.parseFloat(UserInfo.getInstance().getUseful_money().substring(0,UserInfo.getInstance().getWaitLiXi().length()));
//        Float.parseFloat(UserInfo.getInstance().getUseful_money().substring(0,UserInfo.getInstance().getBenJin().length()));

       daishoulixi.setText(UserInfo.getInstance().getWaitLiXi());
        daishoubenjin.setText(UserInfo.getInstance().getBenJin());
        keyongyue.setText(UserInfo.getInstance().getUseful_money());
        touzidongjie.setText("0.00元");
        shenqingtixian.setText("0.00元");
        shoutoumane.setText("0.00元");
    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_my_zch;
    }

}
