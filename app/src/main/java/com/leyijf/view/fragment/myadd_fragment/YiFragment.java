package com.leyijf.view.fragment.myadd_fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.leyijf.R;
import com.leyijf.adapter.RateCouponAdapter;
import com.leyijf.base.BaseFragment;
import com.leyijf.bean.RateCouponBean;
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
 * 我的加息券
 * A simple {@link Fragment} subclass.
 */
public class YiFragment extends BaseFragment {
    private static final String TAG ="YiFragment" ;
private LinearLayoutManager layoutManager;
    List<RateCouponBean.RatesBean> rates = new ArrayList<>();


    private RecyclerView indent_list;
    RateCouponAdapter rateCouponAdapter;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        indent_list = view.findViewById(R.id.indent_list);
        layoutManager = new LinearLayoutManager(getActivity());
        indent_list.setLayoutManager(layoutManager);

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
        Observable observable = RetrofitFactory.getInstance().rateCounpon(requestData);
        observable.compose(compose(YiFragment.this.bindToLifecycle())).subscribe(new BaseObserver<RateCouponBean>(getActivity()) {

            @Override
            protected void onHandleSuccess(RateCouponBean rateCouponBean) {
                if (rateCouponBean.getRates()!=null) {
                    rates.addAll(rateCouponBean.getRates());
                    rateCouponAdapter = new RateCouponAdapter(R.layout.myadd_carviewitem, rates);
                    indent_list.setAdapter(rateCouponAdapter);
                    rateCouponAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    protected void updateTitleBar() {

    }
    @Override
    protected int layoutId() {
        return R.layout.fragment_yi;
    }

}
