package com.leyi.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.leyi.R;
import com.leyi.base.BaseFragment;
import com.leyi.bean.MyMoenyBean;
import com.leyi.bean.UserInfo;
import com.leyi.util.OkHttpUtil;
import com.leyi.view.activity.ChongzhiActivity;
import com.leyi.view.activity.LoginActivity;
import com.leyi.view.activity.MyAddActivity;
import com.leyi.view.activity.MyRedPackActivity;
import com.leyi.view.activity.MyTouziActivity;
import com.leyi.view.activity.RePhoneActivity;
import com.leyi.view.activity.SetingActivity;
import com.leyi.view.activity.WithdrawDepositctivity;
import com.leyi.view.activity.TotalZChActivity;
import com.leyi.view.activity.TradingrecordActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyMoneyFragment extends BaseFragment implements View.OnClickListener {


    private ImageView userHead;
    private LinearLayout my_investment;
    private LinearLayout trading_record;
    private LinearLayout al_trading_record;
    private LinearLayout al_my_red;
    private LinearLayout my_red;

    private RelativeLayout no_login;
    private ImageView zhengyan_bai;
    private ImageView biyan_bai;
    private ImageView seting;
    private ImageView al_head;
    private TextView al_phone;
    private TextView al_renheng;
    private LinearLayout chongzhi;
    private LinearLayout tiixan;
    private LinearLayout al_my_investment;
    private LinearLayout my_add;
    private FrameLayout al_loginLayout;
    private LinearLayout al_phnoe_lin;
    private TextView canuse;
    private TextView add_zichan;
    private TextView all_zichan;
    private ImageView aaaa;
    private FrameLayout frameLayout;
    private FrameLayout frameLayouts;
    private TextView gozhuce;
    private String results;
    private MyMoenyBean myMoenyBean;
    private LinearLayout totalzichan;
    private float waitlixi=0;


    public MyMoneyFragment() {
        // Required empty public constructor
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gozhuce:
//                未登录 立即登录
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;

            case R.id.user_head:
//                未登录 头像
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.my_investment:
//             未登录 我的投资
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.trading_record:
//             未登录 交易记录
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.my_red:
//             未登录 我的红包
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.al_my_investment:
//             已登录 我的投资
                startActivity(new Intent(getActivity(), MyTouziActivity.class));
                break;
            case R.id.al_trading_record:
//             已登录 交易记录
                startActivity(new Intent(getActivity(), TradingrecordActivity.class));

                break;
            case R.id.al_my_red:
//             已登录 我的红包
                startActivity(new Intent(getActivity(), MyRedPackActivity.class));
                break;
            case R.id.my_add:
//             已登录 我的加息券
                startActivity(new Intent(getActivity(), MyAddActivity.class));
                break;
            case R.id.al_head:
//             已登录 头像
                break;
            case R.id.seting:
//             已登录 设置
                startActivity(new Intent(getActivity(), SetingActivity.class));
                break;
            case R.id.tiixan:
//             已登录 提现
                startActivity(new Intent(getActivity(), WithdrawDepositctivity.class));
                break;
            case R.id.chongzhi:
//             已登录 充值
                startActivity(new Intent(getActivity(), ChongzhiActivity.class));
                break;
            case R.id.zongzichan:
//             已登录 总资产

                Intent intent=new Intent(getActivity(), TotalZChActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("total_money",myMoenyBean.getObjects().getTotal_money()+"");
                bundle.putString("useful_money",myMoenyBean.getObjects().getUseful_money()+"");
                bundle.putString("waitlixi",waitlixi+"");
//                bundle.putString("","");
//                bundle.putString("","");
//                bundle.putString("","");

              intent.putExtras(bundle);

                startActivity(intent);
                break;
            case R.id.al_phnoe_lin:
//             已登录 修改手机
                startActivity(new Intent(getActivity(), RePhoneActivity.class));
                break;
            case  R.id.biyan_bai:
                biyan_bai.setVisibility(View.GONE);
                zhengyan_bai.setVisibility(View.VISIBLE);
                canuse.setTransformationMethod(PasswordTransformationMethod.getInstance());
                all_zichan.setTransformationMethod(PasswordTransformationMethod.getInstance());
                add_zichan.setTransformationMethod(PasswordTransformationMethod.getInstance());

                break;
            case  R.id.zhengyan_bai:
                zhengyan_bai.setVisibility(View.GONE);
                biyan_bai.setVisibility(View.VISIBLE);

                canuse.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                all_zichan.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                add_zichan.setTransformationMethod(HideReturnsTransformationMethod.getInstance());


                break;
        }
    }

    @Override
    protected void initView(View view) {

        gozhuce = view.findViewById(R.id.gozhuce);
        gozhuce.setOnClickListener(this);
        canuse = view.findViewById(R.id.canuse);
        add_zichan = view.findViewById(R.id.add_zichan);
        all_zichan = view.findViewById(R.id.all_zichan);
        seting = view.findViewById(R.id.seting);
        seting.setOnClickListener(this);
        zhengyan_bai=view.findViewById(R.id.zhengyan_bai);
        biyan_bai=view.findViewById(R.id.biyan_bai);
        userHead = view.findViewById(R.id.user_head);
        my_investment = view.findViewById(R.id.my_investment);
        al_my_investment = view.findViewById(R.id.al_my_investment);
        trading_record = view.findViewById(R.id.trading_record);
        my_red = view.findViewById(R.id.my_red);
        al_trading_record=view.findViewById(R.id.al_trading_record);
        al_my_red=view.findViewById(R.id.al_my_red);
        no_login=view.findViewById(R.id.no_login);
        al_loginLayout = view.findViewById(R.id.al_login);
        al_head=view.findViewById(R.id.al_head);
        tiixan=view.findViewById(R.id.tiixan);
        chongzhi=view.findViewById(R.id.chongzhi);
        al_renheng=view.findViewById(R.id.al_renheng);
        al_phone=view.findViewById(R.id.al_phone);
        al_phnoe_lin = view.findViewById(R.id.al_phnoe_lin);
        totalzichan = view.findViewById(R.id.zongzichan);
        totalzichan.setOnClickListener(this);
        al_phnoe_lin.setOnClickListener(this);
        my_add=view.findViewById(R.id.my_add);
        my_add.setOnClickListener(this);
        al_my_red.setOnClickListener(this);
        al_my_investment.setOnClickListener(this);
        al_trading_record.setOnClickListener(this);
        al_head.setOnClickListener(this);
        my_red.setOnClickListener(this);
        my_investment.setOnClickListener(this);
        trading_record.setOnClickListener(this);
        userHead.setOnClickListener(this);
        tiixan.setOnClickListener(this);
        chongzhi.setOnClickListener(this);
        aaaa = view.findViewById(R.id.aaaa);

        frameLayouts = view.findViewById(R.id.myaa);

//        aaaa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "aaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
//            }
//        });
        frameLayouts.setPadding(0,getStatusBarHeight(getActivity()),0,0);
        no_login.setPadding(0,getStatusBarHeight(getActivity()),0,0);
        zhengyan_bai.setOnClickListener(this);
        biyan_bai.setOnClickListener(this);

//
//
//
//






    }

    @Override
    public void onResume() {
        super.onResume();


        // 判断用户登录状态
        if(UserInfo.getInstance().getUserState()==1){



            Map<String,String> map=new HashMap();
            map.put("act","uc_center");
            map.put("email",UserInfo.getInstance().getUserPhone()+"");
            map.put("pwd",UserInfo.getInstance().getUserPwd());
            OkHttpUtil.getInstance().doHttp(map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    results = response.body().string();

                    JSONObject jsonObject= null;
                    try {
                        jsonObject = new JSONObject(results);
                        int response_code = jsonObject.getInt("response_code");
                        if(response_code==1){
                            Gson gson=new Gson();
                            myMoenyBean = gson.fromJson(results, MyMoenyBean.class);
                            UserInfo.getInstance().saveUserName(myMoenyBean.getObjects().getUser_info().getUser_name());
                            UserInfo.getInstance().saveUserImg(myMoenyBean.getObjects().getUser_img_url()+"");
                            UserInfo.getInstance().saveUserId(myMoenyBean.getObjects().getUser_info().getUser_id()+"");
                            UserInfo.getInstance().saveId_passed(myMoenyBean.getObjects().getUser_info().getId_passed());
                            UserInfo.getInstance().saveHas_paypassword(myMoenyBean.getObjects().getUser_info().getHas_paypassword());
                            UserInfo.getInstance().saveUserImg(myMoenyBean.getObjects().getUser_info().getUser_img()+"");
                            UserInfo.getInstance().saveUserMoney(myMoenyBean.getObjects().getUseful_money()+"");

                            Log.e("---",myMoenyBean.toString()+"");
                                  waitlixi=0;
                            for (int i = 0; i < myMoenyBean.getObjects().getDeals().size(); i++) {

                                if((myMoenyBean.getObjects().getDeals().get(i).getActual_return()).equals("0")){
                                    waitlixi=waitlixi+Float.parseFloat(myMoenyBean.getObjects().getDeals().get(i).getYuqi_money());
                                      }
                            }


                            float benjin = Float.parseFloat(myMoenyBean.getObjects().getTotal_money()) - waitlixi-Float.parseFloat(myMoenyBean.getObjects().getUseful_money());
                            UserInfo.getInstance().saveWaitLiXi(waitlixi+"元");
                            UserInfo.getInstance().saveTotalMoney(myMoenyBean.getObjects().getTotal_money()+"元");
                            UserInfo.getInstance().saveUseful_money(myMoenyBean.getObjects().getUseful_money()+"元");
                            UserInfo.getInstance().saveShouyiTJ(myMoenyBean.getObjects().getAccumulated_income()+"元");
                            UserInfo.getInstance().saveBenjin(benjin+"元");
                            }




                            if(myMoenyBean.getObjects().getUser_info().getBinded_card().size()!=0){
                                UserInfo.getInstance().setUserbindCard(true);

                            }else {
                                UserInfo.getInstance().setUserbindCard(false);
                            }
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    no_login.setVisibility(View.GONE);
                                    al_loginLayout.setVisibility(View.VISIBLE);
                                    if(!"".equals(UserInfo.getInstance().getUserImg())){
                                        Glide.with(getActivity()).load(UserInfo.getInstance().getUserImg()).into(userHead);
                                    }
                                    al_phone.setText(myMoenyBean.getObjects().getUser_info().getUser_mobile()+"");
                                    if(UserInfo.getInstance().getId_passed()!=0){
                                        al_renheng.setText("已实名认证");
                                    }else {
                                        al_renheng.setText("未实名认证");
                                    }


                                    all_zichan.setText(myMoenyBean.getObjects().getTotal_money()+"");
                                    add_zichan.setText(myMoenyBean.getObjects().getAccumulated_income()+"");
                                    canuse.setText(myMoenyBean.getObjects().getUseful_money()+"");
                                }
                            });





                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });







        }else {
            no_login.setVisibility(View.VISIBLE);
            al_loginLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_my_money;
    }


}
