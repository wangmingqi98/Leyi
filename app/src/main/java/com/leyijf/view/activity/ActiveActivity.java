package com.leyijf.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leyijf.R;
import com.leyijf.adapter.HotActiveAdapter;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.HotActiveBean;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import io.reactivex.Observable;

/**
 * 活动页面
 */
public class ActiveActivity extends BaseActivity implements View.OnClickListener{

    private List<HotActiveBean.HotactivityBean> hotactivity;

    private ImageView zhuceBack;
    private RecyclerView hot_active;
    private HotActiveAdapter hotActiveAdapter;
    private LinearLayoutManager linearLayoutManager;
    private SmartRefreshLayout smartRefreshLayout;

    /**
     * 网络
     */
    @Override
    protected void initData() {
        Observable observable = RetrofitFactory.getInstance().hotActivity();
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<HotActiveBean>(this) {
            @Override
            protected void onHandleSuccess(HotActiveBean hotActiveBean) {
                hotactivity = hotActiveBean.getHotactivity();
                hotActiveAdapter = new HotActiveAdapter(R.layout.hotact_item,hotactivity);
                hot_active.setAdapter(hotActiveAdapter);
                hotActiveAdapter.notifyDataSetChanged();

        hotActiveAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(ActiveActivity.this,"点了一个",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.putExtra("url",hotactivity.get(position).getUrl());
                intent.setClass(ActiveActivity.this,HotDetailActivity.class);
                startActivity(intent);
            }
        });
            }


        });


    }

    @Override
    protected void initId() {

        zhuceBack = (ImageView) findViewById(R.id.zhuce_back);
        zhuceBack.setOnClickListener(this);
        hot_active = (RecyclerView) findViewById(R.id.hot_active);
//        smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.layout_active);
        linearLayoutManager = new LinearLayoutManager(this);
        hot_active.setLayoutManager(linearLayoutManager);
//        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
//        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(this));


    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_active;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuce_back:
                finish();
                break;
            default:
                break;
        }
    }

}
