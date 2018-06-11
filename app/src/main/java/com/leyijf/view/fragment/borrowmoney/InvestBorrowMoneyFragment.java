package com.leyijf.view.fragment.borrowmoney;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.leyijf.R;
import com.leyijf.adapter.AllBorrowMoneyAdapter;
import com.leyijf.adapter.InvestBorrowMoneyAdapter;
import com.leyijf.base.BaseFragment;
import com.leyijf.bean.MyBorrowMoneyBean;
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
 * 我的借款--投资中
 * Created by wmq on 2018/5/8.
 */

public class InvestBorrowMoneyFragment extends BaseFragment {
    public static final String TAG = "InvestBorrowMoneyFragment";
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private int page = 1;
    private SmartRefreshLayout refreshLayout;
    private ImageView noData;
    private List<MyBorrowMoneyBean.ItemBean> item = new ArrayList<>();
    private InvestBorrowMoneyAdapter adapter;
    private int refresh = 1;
    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = view.findViewById(R.id.indent_list);
        recyclerView.setLayoutManager(linearLayoutManager);
        noData = view.findViewById(R.id.no_data);
        refreshLayout = view.findViewById(R.id.refreshlayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        adapter = new InvestBorrowMoneyAdapter(R.layout.item_borrowmoney, item);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
     * 我的借款--投资中1
     */
    @SuppressLint("LongLogTag")
    private void initInvest() {
        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String pwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email", email);
            object.put("pwd", pwd);
            object.put("status", 1);
            object.put("page", page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initInvest: " + jjjjj);
        Log.d(TAG, "initInvest: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().borrowMoney(requestData);
        observable.compose(compose(InvestBorrowMoneyFragment.this.bindToLifecycle())).subscribe(new BaseObserver<MyBorrowMoneyBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(MyBorrowMoneyBean myBorrowMoneyBean) {
                recyclerView.setVisibility(View.VISIBLE);
                List<MyBorrowMoneyBean.ItemBean> itemBeanArray = new ArrayList<>();
                item.addAll(myBorrowMoneyBean.getItem());
                if (item.size()>0) {
                    if(refresh==1){//刷新
                        if (myBorrowMoneyBean.getItem().size() > 0) {
                            for (int i = 0; i < myBorrowMoneyBean.getItem().size(); i++) {
                                if (!myBorrowMoneyBean.getItem().get(i).getDeal_status().equals("0")) {
                                    MyBorrowMoneyBean.ItemBean itemBean = myBorrowMoneyBean.getItem().get(i);
                                    itemBeanArray.add(itemBean);
                                }
                            }
                            if(item.size()==0){
                                item.addAll(itemBeanArray);
                            }

                        }
                        refreshLayout.finishRefresh();
                    }else {//加载
                        if (item.size() > 0) {
                            item.addAll(myBorrowMoneyBean.getItem());
                        }
                        refreshLayout.finishLoadMore();
                    }
                }else {
                    refreshLayout.finishLoadMore();
                    refreshLayout.finishRefresh();
                    noData.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
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
