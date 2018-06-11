package com.leyijf.view.fragment.fragment_touzi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leyijf.R;
import com.leyijf.base.BaseFragment;
import com.leyijf.util.OkHttpUtil;
import com.leyijf.util.StringReplaceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 项目详情
 * A simple {@link Fragment} subclass.
 */
public class InsideUserInfoFragment extends BaseFragment {
    public static final String TAG = "";
    private TextView name;
    private TextView age;
    private TextView marriage;//婚姻
    private TextView education;//学历
    private TextView address;//地址
    private TextView identity_card;//身份证号
    private TextView repayment_count;//借款次数
    private TextView overdue_money;//逾期金额
    private TextView repaymentContent;//借款描述
    private TextView riskContent;//风险控制
    private TextView overdueCount;//逾期次数
    private String id;
    private LinearLayout layoutUser;
    private LinearLayout layoutCompany;
    private TextView companyName;
    private TextView industry;//所属行业
    private TextView assets;//资产总值
    private TextView company_count;//企业借款次数
    private TextView legal_person;//法人代表
    private TextView register_money;//注册资金
    private TextView company_size;//公司规模
    private TextView company_overdue_money;//企业逾期金额
    private TextView companyOverdueCount;//企业逾期次数

    public static final InsideUserInfoFragment newInstance(String id)
    {
        InsideUserInfoFragment fragment = new InsideUserInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        fragment.setArguments(bundle);

        return fragment ;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        id=getArguments().getString("id");
        layoutUser = view.findViewById(R.id.layout_user);
        layoutCompany = view.findViewById(R.id.layout_company);
        name = view.findViewById(R.id.name);
        age = view.findViewById(R.id.age);
        marriage = view.findViewById(R.id.marriage);
        education = view.findViewById(R.id.education);
        address = view.findViewById(R.id.address);
        identity_card = view.findViewById(R.id.identity_card);
        repayment_count = view.findViewById(R.id.repayment_count);
        overdue_money = view.findViewById(R.id.overdue_money);
        repaymentContent = view.findViewById(R.id.repayment_content);
        riskContent = view.findViewById(R.id.risk_content);
        companyName = view.findViewById(R.id.company_name);
        industry = view.findViewById(R.id.industry);
        assets = view.findViewById(R.id.assets);
        company_count = view.findViewById(R.id.company_count);
        legal_person = view.findViewById(R.id.legal_person);
        register_money = view.findViewById(R.id.register_money);
        company_size = view.findViewById(R.id.company_size);
        company_overdue_money = view.findViewById(R.id.company_overdue_money);
        companyOverdueCount = view.findViewById(R.id.company_overdue_count);
        overdueCount = view.findViewById(R.id.overdue_count);
    }

    /**
     *获取借款人信息、借款描述、风险控制
     */
    @Override
    protected void initData() {
        Map<String,String>  map=new HashMap<>();
        map.put("act","v2_deals_project_details");
        map.put("deal_id",id);
        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String  xishoudata=response.body().string();
                try {
                    JSONObject jsonObject= new JSONObject(xishoudata);
                    JSONObject objects = jsonObject.getJSONObject("objects");
                    JSONObject project_details = objects.getJSONObject("project_details");
                    final JSONObject user =  project_details.getJSONObject("user");
                    String user_type = user.getString("user_type");
                    final JSONObject userInfo = user.getJSONObject("u_info");
                    Log.d(TAG, "onResponse: "+user_type);
                    if(jsonObject.getInt("response_code")==1){
                        if(user_type.equals("0")){//个人信息
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    layoutUser.setVisibility(View.VISIBLE);
                                    layoutCompany.setVisibility(View.GONE);
                                    try {
                                        name.setText(StringReplaceUtils.userNameReplaceWithStar(userInfo.getString("user_name")));
                                        age.setText(userInfo.getString("age"));
                                        marriage.setText(userInfo.getString("marriage"));
                                        education.setText(userInfo.getString("graduation"));
                                        address.setText(userInfo.getString("address"));
                                        identity_card.setText(userInfo.getString("idno"));
                                        repayment_count.setText(user.getString("deal_count")+"次");
                                        overdue_money.setText(user.getString("yuqi_impose"));
                                        overdueCount.setText(user.getString("yuqi_count")+"次");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });



                        }else {//企业信息
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    layoutUser.setVisibility(View.GONE);
                                    layoutCompany.setVisibility(View.VISIBLE);
                                    try {
                                        companyName.setText(StringReplaceUtils.userNameReplaceWithStar(userInfo.getString("company_name")));
                                        industry.setText(userInfo.getString("officedomain"));
                                        assets.setText(userInfo.getString("asset_value")+"万元");
                                        legal_person.setText(StringReplaceUtils.userNameReplaceWithStar(userInfo.getString("contact")));
                                        register_money.setText(userInfo.getString("register_capital")+"万元");
                                        company_size.setText(userInfo.getString("officecale"));
                                        company_count.setText(user.getString("deal_count")+"次");
                                        company_overdue_money.setText(user.getString("yuqi_impose"));
                                        companyOverdueCount.setText(user.getString("yuqi_count")+"次");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });



                        }
                        final String description = project_details.getString("descriptions");
                        final String riskSecurity = project_details.getString("risk_security");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                repaymentContent.setText(description);
                                riskContent.setText(riskSecurity);
                                riskContent.requestLayout();
                            }
                        });


                    }else {
                        Toast.makeText(getActivity(),jsonObject.getString("show_err"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });



    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_inside_user_info;
    }

}
