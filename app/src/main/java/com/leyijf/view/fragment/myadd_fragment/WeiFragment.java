package com.leyijf.view.fragment.myadd_fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.leyijf.R;
import com.leyijf.adapter.RedPacketAdapter;
import com.leyijf.base.BaseFragment;
import com.leyijf.bean.MyRedPacketsBean;
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
 * 红包列表
 * A simple {@link Fragment} subclass.
 */
public class WeiFragment extends BaseFragment {
    private static final String TAG ="WeiFragment" ;
    String act;
    List<MyRedPacketsBean.VouchersBean> list = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;


    private RecyclerView indent_list;
    RedPacketAdapter redPacketAdapter;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        indent_list = view.findViewById(R.id.indent_list);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        indent_list.setLayoutManager(linearLayoutManager);

    }
    @Override
    protected void initData() {
        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String pwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email",email);
            object.put("pwd", pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initData: "+jjjjj);
        Log.d(TAG, "initData: "+object.toString());
        Observable observable = RetrofitFactory.getInstance().redPackets(requestData);
        observable.compose(compose(WeiFragment.this.bindToLifecycle())).subscribe(new BaseObserver<MyRedPacketsBean>(getActivity()) {


            @Override
            protected void onHandleSuccess(MyRedPacketsBean myRedPacketsBean) {
                if (myRedPacketsBean.getVouchers()!=null) {
                    list.addAll( myRedPacketsBean.getVouchers());
                    redPacketAdapter = new RedPacketAdapter(R.layout.myadd_carviewitem, list);
                    indent_list.setAdapter(redPacketAdapter);
                    redPacketAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_wei;
    }

}
