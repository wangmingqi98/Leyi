package com.leyijf.view.fragment.borrowmoney;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.leyijf.R;
import com.leyijf.adapter.AllBorrowMoneyAdapter;
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
 * 我的借款---全部
 * Created by wmq on 2018/5/8.
 */

public class AllBorrowMoneyFragment extends BaseFragment {
    public static final String TAG = "AllBorrowMoneyFragment";
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private ImageView noData;
    private SmartRefreshLayout refreshLayout;
    private int page = 1;
    int refresh = 1;
    private List<MyBorrowMoneyBean.ItemBean> item = new ArrayList<>();
    private AllBorrowMoneyAdapter adapter;

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
        initAllBorrowMoney();
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page++;
                refresh = 2;
                initAllBorrowMoney();

            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refresh = 1;
                initAllBorrowMoney();
            }
        });

    }

    /**
     * 我的借款---全部0
     */
    private void initAllBorrowMoney() {
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
        Log.d(TAG, "initAllBorrowMoney: " + jjjjj);
        Log.d(TAG, "initAllBorrowMoney: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().borrowMoney(requestData);
        observable.compose(compose(AllBorrowMoneyFragment.this.bindToLifecycle())).subscribe(new BaseObserver<MyBorrowMoneyBean>(getActivity()) {
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
                    refreshLayout.finishRefresh();
                    refreshLayout.finishLoadMore();
                    noData.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                adapter = new AllBorrowMoneyAdapter(R.layout.item_borrowmoney,item);
                recyclerView.setAdapter(adapter);
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
