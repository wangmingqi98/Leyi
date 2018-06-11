package com.leyijf.view.fragment.fragment_touzi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.leyijf.R;
import com.leyijf.adapter.RenshuAdapter;
import com.leyijf.base.BaseFragment;
import com.leyijf.bean.TouziRenshuBean;
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
 *投资人数
 * A simple {@link Fragment} subclass.
 */
public class TouziRenshuFragment extends BaseFragment {
    public static final String TAG = "TouziRenshuFragment";
    private LinearLayoutManager linearLayoutManager;
    public RecyclerView renshulist;
    public RenshuAdapter renshuAdapter;
    public   int count;
    private TouziRenshuBean touziRenshuBean;
    private String id;
    private List<TouziRenshuBean.BuyRecordBean> buyRecord = new ArrayList<>();



    public static final TouziRenshuFragment newInstance(String id)
    {
        TouziRenshuFragment fragment = new TouziRenshuFragment();
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
        id = getArguments().getString("id");
        linearLayoutManager = new LinearLayoutManager(getActivity());
        renshulist = view.findViewById(R.id.renshulist);
        renshulist.setLayoutManager(linearLayoutManager);



    }

    @Override
    protected void initData() {
        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String pwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email", email);
            object.put("pwd", pwd);
            object.put("deal_id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initData: " + jjjjj);
        Log.d(TAG, "initData: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().getDealsPeopleNum(requestData);
        observable.compose(compose(TouziRenshuFragment.this.bindToLifecycle())).subscribe(new BaseObserver<TouziRenshuBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(TouziRenshuBean touziRenshuBean) {
                if (touziRenshuBean.getBuy_record()!=null) {
                    if (touziRenshuBean.getBuy_record().size() > 0) {
                        buyRecord.addAll(touziRenshuBean.getBuy_record());
                        renshuAdapter = new RenshuAdapter(R.layout.renshu_listitem, buyRecord);
                        renshulist.setAdapter(renshuAdapter);
                        renshuAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getActivity(), "暂无数据！", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getActivity(), "暂无数据！", Toast.LENGTH_LONG).show();
                }

            }

        });

    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_touzi_renshu;
    }




}
