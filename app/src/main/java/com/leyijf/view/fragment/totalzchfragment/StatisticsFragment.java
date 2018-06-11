package com.leyijf.view.fragment.totalzchfragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leyijf.R;
import com.leyijf.adapter.StatisicsAdapter;
import com.leyijf.base.BaseFragment;
import com.leyijf.bean.StatisticsBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * 收益统计
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends BaseFragment {
    public static final String TAG = "StatisticsFragment";
    private LinearLayoutManager linearLayoutManager;
    private TextView touziqixian;
    private TextView yujishouyi;
    private LinearLayout no_internet_lin;
    private RecyclerView shouyi_list;
    private LinearLayout has_internet_lin;
    private ImageView noData;
    private StatisicsAdapter adapter;
    private List<StatisticsBean.LoadListBean> load_list = new ArrayList<>();

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        touziqixian = view.findViewById(R.id.touziqixian);
        yujishouyi =  view.findViewById(R.id.yujishouyi);
        noData = view.findViewById(R.id.no_data);
        no_internet_lin = view.findViewById(R.id.no_internet_lin);
        shouyi_list =  view.findViewById(R.id.shouyi_list);
        has_internet_lin =  view.findViewById(R.id.has_internet_lin);
        shouyi_list.setLayoutManager(linearLayoutManager);
        adapter = new StatisicsAdapter(R.layout.shouyi_item,load_list);
        adapter.notifyDataSetChanged();
        dohttp();


    }

    /**
     * 我的资产--收益统计 type 2
     */
    private void dohttp() {
        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        Log.d(TAG, "initData: "+email);
        String pwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email", email);
            object.put("pwd", pwd);
            object.put("type",2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initData: " + jjjjj);
        Log.d(TAG, "initData: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().assetsDetail(requestData);
        observable.compose(compose(StatisticsFragment.this.bindToLifecycle())).subscribe(new BaseObserver<StatisticsBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(StatisticsBean statisticsBean) {
                List<StatisticsBean.LoadListBean> loadListBeans = new ArrayList<>();
                touziqixian.setText(statisticsBean.getLast_month_profit());
                yujishouyi.setText(statisticsBean.getLoad_earnings());
                if (statisticsBean.getLoad_list()!=null) {
                    if (statisticsBean.getLoad_list().size() > 0) {
                        loadListBeans.addAll(statisticsBean.getLoad_list());
                        load_list.addAll(loadListBeans);
                        adapter.notifyDataSetChanged();
                    } else {
                        noData.setVisibility(View.VISIBLE);
                    }
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
        return R.layout.fragment_statistics;
    }


    @Override
    public void onResume() {
        super.onResume();





    }
}
