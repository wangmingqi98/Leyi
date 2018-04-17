package com.leyi.view.fragment;


import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.leyi.R;
import com.leyi.adapter.HomeListAdapter;
import com.leyi.base.BaseFragment;
import com.leyi.bean.InItBean;
import com.leyi.bean.SystemBean;
import com.leyi.http.GetUrl;
import com.leyi.util.LogUtils;
import com.leyi.util.OkHttpUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Document;

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
public class HomeFragment extends BaseFragment {


    private ListView listView;
    public  HomeListAdapter homeListAdapter;
    private SmartRefreshLayout smart;
    private Map<String, String> map;
    private String s;
    private Document document;
    private List img_list;
    private List img_list1;
    private List<InItBean> inItBeanList;
    private SystemBean systemBean;
    private List<String> textArray;

    public HomeFragment() {
        // Required empty public constructor
    }



    Handler handler = new Handler();


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        listView = view.findViewById(R.id.home_list);
        smart = view.findViewById(R.id.smart);

      getNews();
     getData();



    }



    private void getData() {




        map = new HashMap<>();
        map.put("act","init");


        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String  initdata=response.body().string();
                try {
                    JSONObject jsonObject=new JSONObject(initdata);
                    if(jsonObject.getInt("response_code")==1){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("init",initdata);
                                LogUtils.e("__________",initdata+"");
                                Log.e("init", GetUrl.getUrl(map)+"");

                                try {
                                    img_list=new ArrayList();

                                    JSONObject jsonObject=new JSONObject(initdata);
                                    systemBean = new SystemBean( jsonObject.getJSONObject("objects").getString("virtual_money_1"),
                                            jsonObject.getJSONObject("objects").getString("virtual_money_2"),
                                            jsonObject.getJSONObject("objects").getString("virtual_money_3"),
                                            jsonObject.getJSONObject("objects").getString("program_title"),
                                            jsonObject.getJSONObject("objects").getString("site_domain"),
                                            jsonObject.getJSONObject("objects").getString("kf_phone"),
                                            jsonObject.getJSONObject("objects").getString("kf_email"),
                                            jsonObject.getJSONObject("objects").getString("version"));





                                    JSONArray adv_list = jsonObject.getJSONObject("objects").getJSONObject("index_list").getJSONArray("adv_list");
                                    JSONArray deal_list = jsonObject.getJSONObject("objects").getJSONObject("index_list").getJSONArray("deal_list");
                                    for (int i = 0; i < adv_list.length(); i++) {

                                        JSONObject jsonObject1= adv_list.getJSONObject(i);
                                        String img = jsonObject1.getString("img");
                                        img_list.add(GetUrl.BASEIMG+img);

                                    }

//                            String deal_status;
//                            String  repay_time_type;
//                            String  risk_rank;
//

                                    inItBeanList = new ArrayList<InItBean>();
                                    for (int i = 0; i < deal_list.length(); i++) {
                                        JSONObject jsonObject1 = deal_list.getJSONObject(i);
                                        InItBean inItBean=new InItBean(jsonObject1.getString("id"),
                                                jsonObject1.getString("repay_time"), jsonObject1.getString("rate"), jsonObject1.getString("min_loan_money"),
                                                jsonObject1.getString("name"),  jsonObject1.getString("deal_labels"),jsonObject1.getString("need_money"),
                                                jsonObject1.getInt("progress_point"),jsonObject1.getString("app_url"),jsonObject1.getString("deal_status")
                                                ,jsonObject1.getString("repay_time_type"),jsonObject1.getString("risk_rank"),jsonObject1.getString("borrow_amount"),jsonObject1.getString("enddate"));
                                        inItBeanList.add(inItBean);
                                        Log.e("------initBean",inItBean.toString()+"");


                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                                homeListAdapter = new HomeListAdapter(getActivity(),img_list,inItBeanList,textArray,systemBean);
                                listView.setAdapter(homeListAdapter);
                                smart.setRefreshHeader(new ClassicsHeader(getActivity()));
                                smart.setOnRefreshListener(new OnRefreshListener() {
                                    @Override
                                    public void onRefresh(RefreshLayout refreshlayout) {

                                        smart.finishRefresh(2000);
                                        getData();
                                    }
                                });


                            }
                        });                getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("init",initdata);
                                LogUtils.e("__________",initdata+"");
                                Log.e("init", GetUrl.getUrl(map)+"");

                                try {
                                    img_list=new ArrayList();

                                    JSONObject jsonObject=new JSONObject(initdata);
                                    systemBean = new SystemBean( jsonObject.getJSONObject("objects").getString("virtual_money_1"),
                                            jsonObject.getJSONObject("objects").getString("virtual_money_2"),
                                            jsonObject.getJSONObject("objects").getString("virtual_money_3"),
                                            jsonObject.getJSONObject("objects").getString("program_title"),
                                            jsonObject.getJSONObject("objects").getString("site_domain"),
                                            jsonObject.getJSONObject("objects").getString("kf_phone"),
                                            jsonObject.getJSONObject("objects").getString("kf_email"),
                                            jsonObject.getJSONObject("objects").getString("version"));





                                    JSONArray adv_list = jsonObject.getJSONObject("objects").getJSONObject("index_list").getJSONArray("adv_list");
                                    JSONArray deal_list = jsonObject.getJSONObject("objects").getJSONObject("index_list").getJSONArray("deal_list");
                                    for (int i = 0; i < adv_list.length(); i++) {

                                        JSONObject jsonObject1= adv_list.getJSONObject(i);
                                        String img = jsonObject1.getString("img");
                                        img_list.add(GetUrl.BASEIMG+img);

                                    }

//                            String deal_status;
//                            String  repay_time_type;
//                            String  risk_rank;
//

                                    inItBeanList = new ArrayList<InItBean>();
                                    for (int i = 0; i < deal_list.length(); i++) {
                                        JSONObject jsonObject1 = deal_list.getJSONObject(i);
                                        InItBean inItBean=new InItBean(jsonObject1.getString("id"),
                                                jsonObject1.getString("repay_time"), jsonObject1.getString("rate"), jsonObject1.getString("min_loan_money"),
                                                jsonObject1.getString("name"),  jsonObject1.getString("deal_labels"),jsonObject1.getString("need_money"),
                                                jsonObject1.getInt("progress_point"),jsonObject1.getString("app_url"),jsonObject1.getString("deal_status")
                                                ,jsonObject1.getString("repay_time_type"),jsonObject1.getString("risk_rank"),jsonObject1.getString("borrow_amount"),jsonObject1.getString("enddate"));
                                        inItBeanList.add(inItBean);
                                        Log.e("------initBean",inItBean.toString()+"");


                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                                homeListAdapter = new HomeListAdapter(getActivity(),img_list,inItBeanList,textArray,systemBean);
                                listView.setAdapter(homeListAdapter);
                                smart.setRefreshHeader(new ClassicsHeader(getActivity()));
                                smart.setOnRefreshListener(new OnRefreshListener() {
                                    @Override
                                    public void onRefresh(RefreshLayout refreshlayout) {

                                        smart.finishRefresh(2000);
                                        getData();
                                    }
                                });






                            }
                        });
                    }else {
                        final String show_err = jsonObject.getString("show_err");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), show_err+"", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }






                } catch (JSONException e) {
                    e.printStackTrace();
                }







            }
        });


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    private void getNews() {
        Map<String,String> map=new HashMap<>();
        map.put("act","website_notice");
        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.e("---website_notice--",string+"");
             getActivity().runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     JSONObject jsonObject= null;
                     try {
                         jsonObject = new JSONObject(string);
                         int response_code = jsonObject.getInt("response_code");
                         if(response_code==1){

                             textArray=new ArrayList<String>();
                             JSONArray jsonArray = jsonObject.getJSONObject("objects").getJSONArray("notice_list");
                             for (int i = 0; i < jsonArray.length(); i++) {

                                 textArray.add(jsonArray.getJSONObject(i).getString("title"));

                             }




                         }


                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                 }
             });




            }
        });




    }


}
