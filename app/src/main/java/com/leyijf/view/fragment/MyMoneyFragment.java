package com.leyijf.view.fragment;


import android.content.Intent;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.leyijf.R;
import com.leyijf.base.BaseFragment;
import com.leyijf.bean.BindedCardBean;
import com.leyijf.bean.PersonalInformationBean;
import com.leyijf.bean.UserBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;
import com.leyijf.view.activity.BorrowMoneyActivity;
import com.leyijf.view.activity.ChongzhiActivity;
import com.leyijf.view.activity.InvitationActivity;
import com.leyijf.view.activity.LoginActivity;
import com.leyijf.view.activity.MyAddActivity;
import com.leyijf.view.activity.MyTouziActivity;
import com.leyijf.view.activity.RePhoneActivity;
import com.leyijf.view.activity.SetingActivity;
import com.leyijf.view.activity.WithdrawDepositctivity;
import com.leyijf.view.activity.TotalZChActivity;
import com.leyijf.view.activity.TradingrecordActivity;
import com.leyijf.weight.GlideCircleTransform;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyMoneyFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = "MyMoneyFragment";
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
    private LinearLayout borrowMoney;
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
    private PersonalInformationBean myMoenyBean;
    private LinearLayout totalzichan;
    private float waitlixi = 0;
    private String filePath;
    private String tottalMoney;
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
//             未登录 我的奖励
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
//             已登录 我的邀请
                startActivity(new Intent(getActivity(), InvitationActivity.class));
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
                if(!UserInfo.getInstance().getUseful_money().equals("0.00")){
                    startActivity(new Intent(getActivity(), WithdrawDepositctivity.class));
                }else {
                    Toast.makeText(getActivity(),"可用余额不足，不能提现！",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.chongzhi:
//             已登录 充值
                if(UserManager.getInstance().isBindedCard()){
                    startActivity(new Intent(getActivity(), ChongzhiActivity.class));
                }else {
                    Toast.makeText(getActivity(),"请您先绑定银行卡！",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.zongzichan:
//             已登录 总资产
                //需要判断是否有资产再进行跳转
                Intent intent = new Intent(getActivity(), TotalZChActivity.class);
                intent.putExtra("money",tottalMoney);
                startActivity(intent);
                break;
            case R.id.al_phnoe_lin:
//             已登录 修改手机
                startActivity(new Intent(getActivity(), RePhoneActivity.class));
                break;
            case R.id.biyan_bai:
                biyan_bai.setVisibility(View.GONE);
                zhengyan_bai.setVisibility(View.VISIBLE);
                canuse.setTransformationMethod(PasswordTransformationMethod.getInstance());
                all_zichan.setTransformationMethod(PasswordTransformationMethod.getInstance());
                add_zichan.setTransformationMethod(PasswordTransformationMethod.getInstance());

                break;
            case R.id.zhengyan_bai:
                zhengyan_bai.setVisibility(View.GONE);
                biyan_bai.setVisibility(View.VISIBLE);

                canuse.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                all_zichan.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                add_zichan.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;
            case R.id.borrow_money://我的借款
                startActivity(new Intent(getActivity(), BorrowMoneyActivity.class));
                break;
            default:
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
        zhengyan_bai = view.findViewById(R.id.zhengyan_bai);
        biyan_bai = view.findViewById(R.id.biyan_bai);
        userHead = view.findViewById(R.id.user_head);
        my_investment = view.findViewById(R.id.my_investment);
        al_my_investment = view.findViewById(R.id.al_my_investment);
        borrowMoney = view.findViewById(R.id.borrow_money);
        trading_record = view.findViewById(R.id.trading_record);
        my_red = view.findViewById(R.id.my_red);
        al_trading_record = view.findViewById(R.id.al_trading_record);
        al_my_red = view.findViewById(R.id.al_my_red);
        no_login = view.findViewById(R.id.no_login);
        al_loginLayout = view.findViewById(R.id.al_login);
        al_head = view.findViewById(R.id.al_head);
        tiixan = view.findViewById(R.id.tiixan);
        chongzhi = view.findViewById(R.id.chongzhi);
        al_renheng = view.findViewById(R.id.al_renheng);
        al_phone = view.findViewById(R.id.al_phone);
        al_phnoe_lin = view.findViewById(R.id.al_phnoe_lin);
        totalzichan = view.findViewById(R.id.zongzichan);
        borrowMoney.setOnClickListener(this);
        totalzichan.setOnClickListener(this);
        al_phnoe_lin.setOnClickListener(this);
        my_add = view.findViewById(R.id.my_add);
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
        frameLayouts.setPadding(0, getStatusBarHeight(getActivity()), 0, 0);
        no_login.setPadding(0, getStatusBarHeight(getActivity()), 0, 0);
        zhengyan_bai.setOnClickListener(this);
        biyan_bai.setOnClickListener(this);
        if (UserManager.getInstance().isLogin()) {
            no_login.setVisibility(View.GONE);
            al_loginLayout.setVisibility(View.VISIBLE);
            al_phone.setText(UserManager.getInstance().getLoginUser().getUser_mobile());
            filePath = UserManager.getInstance().getLoginUser().getUser_img();
            Glide.with(getActivity()).load(filePath).bitmapTransform(new GlideCircleTransform(getActivity())).crossFade(1000).into(al_head);

        } else {
            no_login.setVisibility(View.VISIBLE);
            al_loginLayout.setVisibility(View.GONE);
        }


    }


    @Override
    public void onResume() {
        super.onResume();
        if (UserManager.getInstance().isLogin()) {
            no_login.setVisibility(View.GONE);
            al_loginLayout.setVisibility(View.VISIBLE);
            al_phone.setText(UserManager.getInstance().getLoginUser().getUser_mobile());
            showView();
            filePath = UserManager.getInstance().getLoginUser().getUser_img();
            if(!filePath.equals("")){

                Glide.with(getActivity()).load(filePath).bitmapTransform(new GlideCircleTransform(getActivity())).crossFade(1000).into(al_head);
            }else {
                al_head.setImageResource(R.drawable.head);
            }
        } else {
            no_login.setVisibility(View.VISIBLE);
            al_loginLayout.setVisibility(View.GONE);
        }


    }


    /**
     * 获取个人信息接口
     */
    @Override
    protected void initData() {
        if (UserManager.getInstance().isLogin()) {
            String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
            Log.d(TAG, "initData: "+email);
            String pwd = UserInfo.getInstance().getUserPwd();
            JSONObject object = new JSONObject();
            try {
                object.put("email", email);
                object.put("pwd", pwd);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String requestData = Aes128.encrypt((object.toString()));
            String jjjjj = Aes128.decrypt(requestData);
            Log.d(TAG, "initVerify: " + jjjjj);
            Log.d(TAG, "initVerify: " + object.toString());
            Observable observable = RetrofitFactory.getInstance().getPersonalInformation();
            observable.compose(compose(MyMoneyFragment.this.<PersonalInformationBean>bindToLifecycle())).subscribe(new BaseObserver<PersonalInformationBean>(getActivity()) {
                @Override
                protected void onHandleSuccess(PersonalInformationBean personalInformationBean) {
                    Log.d(TAG, "onHandleSuccess: " + personalInformationBean.toString());
                    myMoenyBean = personalInformationBean;
                    //处理我的财富页面的数据,并且对用户的信息进行更新
                    UserBean userBean = new UserBean();
                    List<BindedCardBean> list = new ArrayList<>();
                    userBean.setUser_mobile(personalInformationBean.getUser_info().getUser_mobile());
                    userBean.setUser_id(personalInformationBean.getUser_info().getUser_id() + "");
                    userBean.setId_passed(personalInformationBean.getUser_info().getId_passed());
                    userBean.setUser_img(personalInformationBean.getUser_info().getUser_img());
                    userBean.setHas_paypassword(personalInformationBean.getUser_info().getHas_paypassword());
                    userBean.setUser_name(personalInformationBean.getUser_info().getUser_name());
                    if (personalInformationBean.getUser_info().getBinded_card().size() != 0) {
                        list.addAll(personalInformationBean.getUser_info().getBinded_card());
                        for (int i = 0; i < list.size(); i++) {
                            UserManager.getInstance().updateLoginedUserBindeCard(list.get(i));

                        }
                    }
                    UserInfo.getInstance().saveUseful_money(personalInformationBean.getUseful_money());
                    UserManager.getInstance().updateLoginedUser(userBean);
                    all_zichan.setText(personalInformationBean.getTotal_money());
                    UserInfo.getInstance().saveTotalMoney(personalInformationBean.getTotal_money());
                    tottalMoney = personalInformationBean.getTotal_money();
                    add_zichan.setText(personalInformationBean.getAccumulated_income());
                    canuse.setText(personalInformationBean.getUseful_money());
                    al_phone.setText(personalInformationBean.getUser_info().getUser_mobile());
                    filePath = personalInformationBean.getUser_img_url();

                    if(!filePath.equals("")){

                        Glide.with(getActivity()).load(filePath).bitmapTransform(new GlideCircleTransform(getActivity())).crossFade(1000).into(al_head);
                    }else {
                        al_head.setImageResource(R.drawable.head);
                    }
                    if (personalInformationBean.getUser_info().getId_passed() != 0) {
                        al_renheng.setText("已实名认证");
                    } else {
                        al_renheng.setText("未认证");
                    }
                }
            });
        }

    }


    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_my_money;
    }

    private void showView() {
        // 判断用户登录状态
        Log.d(TAG, "showView: "+UserManager.getInstance().isLogin());
        if (UserManager.getInstance().isLogin()) {
            String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
            String pwd = UserInfo.getInstance().getUserPwd();
            JSONObject object = new JSONObject();
            try {
                object.put("email", email);
                object.put("pwd", pwd);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String requestData = Aes128.encrypt((object.toString()));
            String jjjjj = Aes128.decrypt(requestData);
            Log.d(TAG, "initVerify: " + jjjjj);
            Log.d(TAG, "initVerify: " + object.toString());
            Observable observable = RetrofitFactory.getInstance().getPersonalInformation();
            observable.compose(compose(MyMoneyFragment.this.<PersonalInformationBean>bindToLifecycle())).subscribe(new BaseObserver<PersonalInformationBean>(getActivity()) {
                @Override
                protected void onHandleSuccess(PersonalInformationBean personalInformationBean) {
                    Log.d(TAG, "onHandleSuccess: " + personalInformationBean.toString());
                    //处理我的财富页面的数据,并且对用户的信息进行更新
                    UserBean userBean = new UserBean();
                    List<BindedCardBean> list = new ArrayList<>();
                    userBean.setUser_mobile(personalInformationBean.getUser_info().getUser_mobile());
                    userBean.setUser_id(personalInformationBean.getUser_info().getUser_id() + "");
                    userBean.setId_passed(personalInformationBean.getUser_info().getId_passed());
                    userBean.setUser_img(personalInformationBean.getUser_info().getUser_img());
                    userBean.setHas_paypassword(personalInformationBean.getUser_info().getHas_paypassword());
                    userBean.setUser_name(personalInformationBean.getUser_info().getUser_name());
                    userBean.setUser_mobile_referee(UserManager.getInstance().getLoginUser().getUser_mobile_referee());
                    if (personalInformationBean.getUser_info().getBinded_card().size() != 0) {
                        list.addAll(personalInformationBean.getUser_info().getBinded_card());
                        for (int i = 0; i < list.size(); i++) {
                            UserManager.getInstance().updateLoginedUserBindeCard(list.get(i));

                        }
                    }
                    UserInfo.getInstance().saveUseful_money(personalInformationBean.getUseful_money());
                    UserManager.getInstance().updateLoginedUser(userBean);
                    all_zichan.setText(personalInformationBean.getTotal_money());
                    UserInfo.getInstance().saveTotalMoney(personalInformationBean.getTotal_money());
                    tottalMoney = personalInformationBean.getTotal_money();
                    add_zichan.setText(personalInformationBean.getAccumulated_income());
                    canuse.setText(personalInformationBean.getUseful_money());
                    filePath = personalInformationBean.getUser_img_url();
                    if(!filePath.equals("")){

                        Glide.with(getActivity()).load(filePath).bitmapTransform(new GlideCircleTransform(getActivity())).crossFade(1000).into(al_head);
                    }else {
                        al_head.setImageResource(R.drawable.head);
                    }
                    if (personalInformationBean.getUser_info().getId_passed() != 0) {
                        al_renheng.setText("已实名认证");
                    } else {
                        al_renheng.setText("未认证");
                    }


                }
            });
        }


    }
}
