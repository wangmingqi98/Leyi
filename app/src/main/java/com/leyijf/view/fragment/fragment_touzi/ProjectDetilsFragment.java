package com.leyijf.view.fragment.fragment_touzi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.leyijf.R;
import com.leyijf.adapter.MortGageInfoAdapter;
import com.leyijf.base.BaseFragment;
import com.leyijf.bean.MortGageInfoBean;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.util.Aes128;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * 借款合同，照片
 * A simple {@link Fragment} subclass.
 */
public class ProjectDetilsFragment extends BaseFragment{
    public static final String TAG = "ProjectDetilsFragment";
    private RecyclerView listView;
    public TextView hukuan_name;
    public  TextView desions;
    PopupWindow popupWindow;
    private LinearLayoutManager linearLayoutManager;
    private String id;
    private MortGageInfoAdapter adapter;
    private List<MortGageInfoBean.MortgageBean> mortgage = new ArrayList<>();



    public static final ProjectDetilsFragment newInstance(String id)
    {
        ProjectDetilsFragment fragment = new ProjectDetilsFragment();
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
        hukuan_name=view.findViewById(R.id.hukuan_name);
        listView = view.findViewById(R.id.listview);
        listView.setLayoutManager(linearLayoutManager);
        adapter = new MortGageInfoAdapter(R.layout.item_moetgage,mortgage);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }

    /**
     *获取借款合同、抵押物照片
     */
    @Override
    protected void initData() {
        JSONObject object = new JSONObject();
        try {
            object.put("deal_id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initData: " + jjjjj);
        Log.d(TAG, "initData: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().getMortgageInfo(requestData);
        observable.compose(compose(ProjectDetilsFragment.this.bindToLifecycle())).subscribe(new BaseObserver<MortGageInfoBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(final MortGageInfoBean mortGageInfoBean) {
                if (mortGageInfoBean.getMortgage()!=null) {
                    if (mortGageInfoBean.getMortgage().size() > 0) {
                        mortgage.addAll(mortGageInfoBean.getMortgage());
                        adapter.notifyDataSetChanged();
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
        return R.layout.fragment_project_detils;
    }







}
