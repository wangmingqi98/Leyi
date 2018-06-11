package com.leyijf.view.fragment.mytouzi_fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.leyijf.R;
import com.leyijf.adapter.AllInvestAdapter;
import com.leyijf.base.BaseFragment;
import com.leyijf.bean.MyInvestBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuanBuFragment extends BaseFragment {
    public static final String TAG = "QuanBuFragment";
    private RecyclerView indent_list;
    private LinearLayoutManager linearLayoutManager;
    private SmartRefreshLayout refreshLayout;
    private int page=1;
    private AllInvestAdapter adapter;
    private ImageView noData;
    private List<MyInvestBean.ItemBean> item = new ArrayList<>();
    private int refresh = 1;
    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        noData   = view.findViewById(R.id.no_data);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        indent_list = view.findViewById(R.id.indent_list);
        refreshLayout = view.findViewById(R.id.refreshlayout);
        indent_list.setLayoutManager(linearLayoutManager);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        initInvest();
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page++;
                refresh = 2;
                initInvest();
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refresh = 1;
                initInvest();
            }
        });

    }

    /**
     * 我的投资 全部0
     */
    private void initInvest() {
        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String pwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email", email);
            object.put("pwd", pwd);
            object.put("status",0);
            object.put("page",page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initInvest: " + jjjjj);
        Log.d(TAG, "initInvest: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().myInvest(requestData);
        observable.compose(compose(QuanBuFragment.this.bindToLifecycle())).subscribe(new BaseObserver<MyInvestBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(MyInvestBean myInvestBean) {
                if(myInvestBean.getItem()!=null){

                    item.addAll(myInvestBean.getItem());
                }
                if (item.size()>0) {
                    if(refresh==1){
                        if(item.size()==0){
                            item.addAll(myInvestBean.getItem());
                        }
                        refreshLayout.finishRefresh();
                    }else {
                        if(item.size()>0){
                          item.addAll(myInvestBean.getItem());
                        }
                        refreshLayout.finishLoadMore();
                    }

                }else {
                    refreshLayout.finishRefresh();
                    refreshLayout.finishLoadMore();
                    noData.setVisibility(View.VISIBLE);
                }
                adapter = new AllInvestAdapter(R.layout.mytouzi_item, item);
                indent_list.setAdapter(adapter);
                adapter.notifyDataSetChanged();
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
        return R.layout.fragment_quan_bu;
    }

}