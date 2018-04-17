package com.leyi.view.fragment.myadd_fragment;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.leyi.R;
import com.leyi.adapter.MyAddGuoQiAdapter;
import com.leyi.base.BaseFragment;
import com.leyi.bean.UserInfo;
import com.leyi.bean.VoucherBean;
import com.leyi.util.OkHttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuoqiFragment extends BaseFragment {

String act;
    private List<VoucherBean> list;

    public GuoqiFragment(String act) {
        // Required empty public constructor
        this.act=act;

    }



    private ListView indent_list;
    MyAddGuoQiAdapter myAdentListAdapter;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        indent_list = view.findViewById(R.id.indent_list);
        Map map=new HashMap();
        map.put("act",act);
        map.put("email", UserInfo.getInstance().getUserPhone());
        map.put("pwd", UserInfo.getInstance().getUserPwd());
        map.put("type", 3+"");

        if(act.equals("uc_vouchers_list")){
            OkHttpUtil.getInstance().doHttp(map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    Log.e("string---",""+string);
                    try {
                        JSONObject jsonObject=new JSONObject(string);
                        if(jsonObject.getInt("response_code")==1){
                            list = new ArrayList<VoucherBean>();
                            JSONArray jsonArray = jsonObject.getJSONObject("objects").getJSONArray("vouchers");
                            if(jsonArray.length()!=0){
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    VoucherBean voucherBean=new VoucherBean(jsonArray.getJSONObject(i).getString("voucher_id"),jsonArray.getJSONObject(i).getString("end_time"),
                                            jsonArray.getJSONObject(i).getString("user_time"),jsonArray.getJSONObject(i).getString("money"),jsonArray.getJSONObject(i).getString("name"),
                                            jsonArray.getJSONObject(i).getString("can_use_limit"),jsonArray.getJSONObject(i).getString("invest_period_limit"),jsonArray.getJSONObject(i).getString("invest_period_type"),
                                            jsonArray.getJSONObject(i).getString("remain_day"),jsonArray.getJSONObject(i).getString("status"));
                                    list.add(voucherBean);
                                }

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        myAdentListAdapter=new MyAddGuoQiAdapter(getActivity(), list);
                                        indent_list.setAdapter(myAdentListAdapter);
                                    }
                                });
                            }







                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }








                }
            });

        }else  if(act.equals("uc_rates_list")){
            OkHttpUtil.getInstance().doHttp(map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    Log.e("string---",""+string);
                    try {
                        JSONObject jsonObject=new JSONObject(string);
                        if(jsonObject.getInt("response_code")==1){
                            list = new ArrayList<VoucherBean>();
                            JSONArray jsonArray = jsonObject.getJSONObject("objects").getJSONArray("vouchers");
                            if(jsonArray.length()!=0){
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    VoucherBean voucherBean=new VoucherBean(jsonArray.getJSONObject(i).getString("rate_id"),jsonArray.getJSONObject(i).getString("end_time"),
                                            jsonArray.getJSONObject(i).getString("user_time"),jsonArray.getJSONObject(i).getString("money"),jsonArray.getJSONObject(i).getString("name"),
                                            jsonArray.getJSONObject(i).getString("can_use_limit"),jsonArray.getJSONObject(i).getString("invest_period_limit"),jsonArray.getJSONObject(i).getString("invest_period_type"),
                                            jsonArray.getJSONObject(i).getString("remain_day"),jsonArray.getJSONObject(i).getString("status"));
                                    list.add(voucherBean);
                                }

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        myAdentListAdapter=new MyAddGuoQiAdapter(getActivity(),list);
                                        indent_list.setAdapter(myAdentListAdapter);
                                    }
                                });
                            }







                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }








                }
            });

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
        return R.layout.fragment_guoqi;
    }

}
