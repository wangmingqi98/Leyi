package com.leyijf.view.fragment.tradcordfragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.leyijf.R;
import com.leyijf.adapter.MyAdentListAdapter;
import com.leyijf.base.BaseFragment;
import com.leyijf.bean.TransactionRecordsBean;
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
public class TouziFragment extends BaseFragment {
    public static final String TAG = "AllFragment";
    private RecyclerView indent_list;
    private ImageView noData;
    MyAdentListAdapter myAdentListAdapter;
    private int page = 1;
    private SmartRefreshLayout smart;
    List<TransactionRecordsBean.ItemBean> item = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private int refresh = 1;




    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        indent_list = view.findViewById(R.id.indent_list);
        indent_list.setLayoutManager(linearLayoutManager);
        smart = view.findViewById(R.id.smart);
        noData = view.findViewById(R.id.no_data);
        smart.setRefreshHeader(new ClassicsHeader(getActivity()));
        smart.setRefreshFooter(new ClassicsFooter(getActivity()));
        initTradingrecord();
        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                    page++;
                    refresh = 2;
                  initTradingrecord();
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                    refresh = 1;
                    initTradingrecord();
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
        return R.layout.fragment_all;
    }

    /**
     * 交易记录 投资
     */
    private void initTradingrecord() {
        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String pwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email", email);
            object.put("pwd", pwd);
            object.put("status",1);
            object.put("page",page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initTradingrecord: " + jjjjj);
        Log.d(TAG, "initTradingrecord: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().tradingRecord(requestData);
        observable.compose(compose(TouziFragment.this.bindToLifecycle())).subscribe(new BaseObserver<TransactionRecordsBean>(getActivity()) {

            @Override
            protected void onHandleSuccess(TransactionRecordsBean tradingRecordBean) {
                item.addAll(tradingRecordBean.getItem());
                if (item.size()>0) {
                    if(refresh==1){
                        if(item.size()==0){
                            item.addAll(tradingRecordBean.getItem());
                        }
                        smart.finishRefresh();
                    }else {
                        if(item.size()>0){
                            item.addAll(tradingRecordBean.getItem());
                        }
                        smart.finishLoadMore();
                    }

                }else {
                    smart.finishRefresh();
                    smart.finishLoadMore();
                    noData.setVisibility(View.VISIBLE);
                }
                myAdentListAdapter = new MyAdentListAdapter(R.layout.my_ident_item, item);
                indent_list.setAdapter(myAdentListAdapter);
                myAdentListAdapter.notifyDataSetChanged();
            }
        });
    }

}
