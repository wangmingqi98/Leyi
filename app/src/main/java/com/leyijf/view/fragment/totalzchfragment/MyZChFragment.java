package com.leyijf.view.fragment.totalzchfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.leyijf.R;
import com.leyijf.base.BaseFragment;
import com.leyijf.bean.AssetsDetailBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;
import com.leyijf.view.fragment.fragment_touzi.InsideUserInfoFragment;
import com.leyijf.weight.RingView;
import com.leyijf.weight.SelfStatistics;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;

/**
 * 我的资产
 * A simple {@link Fragment} subclass.
 */
public class MyZChFragment extends BaseFragment {

    public static final String TAG = "MyZChFragment";
    private RingView selfStatistics;
    private TextView keyongyue;
    private TextView daishoubenjin;
    private TextView daishoulixi;
    private TextView touzidongjie;
    private TextView shenqingtixian;
    private TextView shoutoumane,totalmoney;


    public static final MyZChFragment newInstance(String money)
    {
        MyZChFragment fragment = new MyZChFragment();
        Bundle bundle = new Bundle();
        bundle.putString("money", money);
        fragment.setArguments(bundle);

        return fragment ;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        selfStatistics = view.findViewById(R.id.progress);
        keyongyue = view.findViewById(R.id.keyongyue);
        daishoubenjin = view.findViewById(R.id.daishoubenjin);
        daishoulixi = view.findViewById(R.id.daishoulixi);
        touzidongjie = view.findViewById(R.id.touzidongjie);
        shenqingtixian = view.findViewById(R.id.shenqingtixian);
        shoutoumane = view.findViewById(R.id.shoutoumane);
        totalmoney = view.findViewById(R.id.totalmoney);
        totalmoney.setText("总资产（ 元）"+UserInfo.getInstance().getTotalMoney());

//        selfStatistics.setTotal(UserInfo.getInstance().getTotalMoney());
//        mRvRingView.setAnglesData("12.2","230","6799.01","1","111","200");//直接设置String类型的数据
//        mRvRingView.setAnglesData(12.2,230,6799.01,1,111,200);//直接设置double类型的数据
//        mRvRingView.setAngles(20, 40, 100, 180, 20);//设置的是角度

        //       mRvRingView.setRingStartAngle(-90);//设置圆环的开始角度，不设置默认是-90
        //设置画笔的颜色，支持字符串和资源文件可变参数。
        selfStatistics.initPaint("#FD3B18", "#FEAC2C", "#FDDF1B", "#7EFC40","#33FFCC","#42abfc");//支持字符串
//        mRvRingView.initPaint(R.color.color_first_part,R.color.color_second_part,
//                             R.color.color_third_part,R.color.color_fourth_part,
//                             R.color.color_fifth_part,R.color.color_sixth_part);
//        mRvRingView.setInnerCirclePaintColor("#ffffff");//内圆的画笔颜色，默认#ffffff
        selfStatistics.setRingStrokeWidth(15);//圆环的环宽，默认20
//        mRvRingView.showViewWithAnimation(1000);//自定义动画时长展示圆环
//        mRvRingView.showViewWithoutAnimation();//展示圆环不带动画
        selfStatistics.showViewWithAnimation();//动画展示圆环，默认2s





    }

    /**
     * 我的资产--资产详情 type 1
     */
    @Override
    protected void initData() {

        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        Log.d(TAG, "initData: "+email);
        String pwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email", email);
            object.put("pwd", pwd);
            object.put("type",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initData: " + jjjjj);
        Log.d(TAG, "initData: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().assetsDetails(requestData);
        observable.compose(compose(MyZChFragment.this.bindToLifecycle())).subscribe(new BaseObserver<AssetsDetailBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(AssetsDetailBean assetsDetailBean) {
                daishoulixi.setText(assetsDetailBean.getLoad_wait_earnings());//已收利息
                daishoubenjin.setText(assetsDetailBean.getTotal_load_money());//投资总额
                keyongyue.setText(assetsDetailBean.getUseful_money());//可用余额
                touzidongjie.setText(assetsDetailBean.getLock_money());//投资冻结资金
                shenqingtixian.setText(assetsDetailBean.getCarry_total_money());//提现冻结资金
                shoutoumane.setText(assetsDetailBean.getTotal_success_carry_money());//成功提现金额
//                float datas[] = new float[]{ Float.parseFloat(assetsDetailBean.getUseful_money().substring(0,assetsDetailBean.getUseful_money().length()-1)),
//                        Float.parseFloat(assetsDetailBean.getLoad_wait_earnings().substring(0,assetsDetailBean.getLoad_wait_earnings().length()-1)),
//                        Float.parseFloat(assetsDetailBean.getLock_money().substring(0,assetsDetailBean.getLock_money().length()-1)),
//                        Float.parseFloat(assetsDetailBean.getCarry_total_money().substring(0,assetsDetailBean.getCarry_total_money().length()-1)),
//                        Float.parseFloat(assetsDetailBean.getTotal_load_money().substring(0,assetsDetailBean.getTotal_load_money().length()-1)),
//                        Float.parseFloat(assetsDetailBean.getTotal_success_carry_money().substring(0,assetsDetailBean.getTotal_success_carry_money().length()-1))
//                };
//                selfStatistics.setTotal(UserInfo.getInstance().getTotalMoney());
//                selfStatistics.setDatas(datas);
//                selfStatistics.startDraw();
                selfStatistics.setAnglesData(assetsDetailBean.getUseful_money(),assetsDetailBean.getTotal_load_money(),
                        assetsDetailBean.getLoad_wait_earnings(),assetsDetailBean.getLock_money(),assetsDetailBean.getCarry_total_money(),assetsDetailBean.getTotal_success_carry_money());//直接设置String类型的数据
            }


        });
    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_my_zch;
    }

}
