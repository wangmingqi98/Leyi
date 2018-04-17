package com.leyi.view.fragment.licaishop_fragment;


import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.leyi.R;
import com.leyi.adapter.NoviceListAdapter;
import com.leyi.base.BaseFragment;
import com.leyi.bean.InItBean;
import com.leyi.util.LogUtils;
import com.leyi.util.OkHttpUtil;
import com.leyi.weight.MyPopupWindow;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * 供应链接
 */
public class SupplylinkFragment extends BaseFragment implements View.OnClickListener {



    private LinearLayout linear;
    int a=1;
    private PopupWindow popupWindow;
    private TextView paixu_name;
    private RadioGroup mRgShopStorePop;
    private View inflate;
    private ListView novice_list;
    private List<InItBean> inItBeanList;
    private SmartRefreshLayout smart;
    int num=1;
    private NoviceListAdapter noviceListAdapter;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear:
                if(a==1){

                    showPop(inflate);




                    a=0;
                }else if(a==0){
                    popupWindow.dismiss();
                }
                break;
        }



    }

    @Override
    public void onResume() {
        super.onResume();
        doHttp();

    }

    private void initPaixuView(View inflate) {

        mRgShopStorePop = inflate.findViewById(R.id.rg_shop_store_pop);
        LinearLayout mLine = inflate.findViewById(R.id.line);
        final RadioButton mRbStoreFront = inflate.findViewById(R.id.rb_storefront);
        final RadioButton mRbMember = inflate.findViewById(R.id.rb_member);
        final RadioButton mRbHistory = inflate.findViewById(R.id.rb_history);
        mRbStoreFront.setChecked(true);
        mRgShopStorePop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressWarnings("Since15")
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_storefront:
//年利率由高至低
                        if(inItBeanList.size()!=0){
                            Collections.sort(inItBeanList,new Comparator<InItBean>() {
                                @Override
                                public int compare(InItBean inItBean, InItBean t1) {
                                    return Integer.parseInt(t1.getRate().substring(0,2))-Integer.parseInt(inItBean.getRate().substring(0,2));
                                }
                            });
                            noviceListAdapter.notifyDataSetChanged();

                        }else {
                            Toast.makeText(getActivity(), "暂无数据！", Toast.LENGTH_SHORT).show();
                        }

                        paixu_name.setText(mRbStoreFront.getText().toString());



                        break;
                    case R.id.rb_member:
//投资期限由短至长
                        if(inItBeanList.size()!=0){
                            Collections .sort(inItBeanList,new Comparator<InItBean>() {
                                @Override
                                public int compare(InItBean inItBean, InItBean t1) {
                                    if(inItBean.getRepay_time_type().equals(t1.getRepay_time_type())){
                                        return Integer.parseInt(inItBean.getRepay_time())-Integer.parseInt(t1.getRepay_time());
                                    }else {
                                        if(inItBean.getRepay_time().equals("0")&&t1.getRepay_time().equals("1")){
                                            return -1;
                                        }else {
                                            return 1;
                                        }



                                    }


                                }
                            });
                            noviceListAdapter.notifyDataSetChanged();

                        }else {
                            Toast.makeText(getActivity(), "暂无数据！", Toast.LENGTH_SHORT).show();
                        }



                        paixu_name.setText(mRbMember.getText().toString());

                        break;
                    case R.id.rb_history:
//起投金额由小至大
                        if(inItBeanList.size()!=0){
                            Collections.sort(inItBeanList,new Comparator<InItBean>() {
                                @Override
                                public int compare(InItBean inItBean, InItBean t1) {
                                    return Integer.parseInt(inItBean.getMin_loan_money())-Integer.parseInt(t1.getMin_loan_money());
                                }
                            });
                            noviceListAdapter.notifyDataSetChanged();

                        }else {
                            Toast.makeText(getActivity(), "暂无数据！", Toast.LENGTH_SHORT).show();
                        }


                        paixu_name.setText(mRbHistory.getText().toString());
                        break;
                }
                popupWindow.dismiss();

            }
        });
        mLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }



    @Override
    protected void initView(View view) {
        linear = view.findViewById(R.id.linear);
        paixu_name = view.findViewById(R.id.paixu_name);
        smart = view.findViewById(R.id.smart);
        smart.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
        smart.setRefreshHeader(new ClassicsHeader(getActivity()));
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                smart.finishRefresh(2000);
            }
        });
        smart.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                smart.finishLoadmore(2000);
                num=num+1;
                doHttp();
            }
        });
        linear.setOnClickListener(this);
        initPopdata();

        novice_list = view.findViewById(R.id.novice_list);
        inItBeanList = new ArrayList<InItBean>();
        noviceListAdapter = new NoviceListAdapter(getActivity(),inItBeanList);
        novice_list.setAdapter(noviceListAdapter);


    }

    private void initPopdata() {
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.tankuang_pop, null);
        initPaixuView(inflate);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateTitleBar() {

    }



    private void showPop(View view) {
        popupWindow = new MyPopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.setFocusable(true);

        // 设置点击其他地方 就消失 (只设置这个，没有效果) 必须设置背景
        popupWindow.setOutsideTouchable(true);

        popupWindow.setTouchable(true);

        popupWindow.setAnimationStyle(R.style.AnimBottom); // 设置动画

        popupWindow.showAsDropDown(linear, 0, 0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                a=1;
            }
        });
    }



//    @Override
//    public void onResume() {
//        super.onResume();
//
//        doHttp();
//
//
//    }

    private void doHttp() {
        Map<String,String>  map=new HashMap<>();
        map.put("act","deals");
        map.put("cid","2");
        map.put("page", num+"");
        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String  xishoudata=response.body().string();
                LogUtils.e("--------",xishoudata+"");
                JSONObject jsonObject= null;

                try {
                    jsonObject = new JSONObject(xishoudata);
                    ;               if(jsonObject.getInt("response_code")==1){
                        final List<InItBean>  inItBeanListss = new ArrayList<InItBean>();
                        JSONArray jsonArray = jsonObject.getJSONObject("objects").getJSONArray("item");

                        if(jsonArray.length()!=0){
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                if(jsonObject1.getString("deal_labels").equals("产业链")){
                                    InItBean inItBean=new InItBean(jsonObject1.getString("id"),
                                            jsonObject1.getString("repay_time"), jsonObject1.getString("rate"), jsonObject1.getString("min_loan_money"),
                                            jsonObject1.getString("name"),  jsonObject1.getString("deal_labels"),jsonObject1.getString("need_money"),
                                            jsonObject1.getInt("progress_point"),jsonObject1.getString("app_url"),jsonObject1.getString("deal_status")
                                            ,jsonObject1.getString("repay_time_type"),jsonObject1.getString("risk_rank"),jsonObject1.getString("borrow_amount"),jsonObject1.getString("enddate"));
                                    inItBeanListss.add(inItBean);
                                    Log.e("------initBean",inItBean.toString()+"");
                                }

                            }


                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {


                                    inItBeanList.addAll(inItBeanListss);
                                    noviceListAdapter.notifyDataSetChanged();






                                }
                            });





                        }
//                        else {
//                           getActivity().runOnUiThread(new Runnable() {
//                               @Override
//                               public void run() {
//
//                                   noviceListAdapter = new NoviceListAdapter(getActivity(),inItBeanList);
//                                   novice_list.setAdapter(noviceListAdapter);
//                               }
//                           });
//                        }





                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }





            }
        });

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_supplylink;
    }

}
