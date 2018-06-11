package com.leyijf.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leyijf.R;
import com.leyijf.adapter.CompanyAdapter;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.GongGao;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import io.reactivex.Observable;

/**
 * 公司公告
 */
public class CompanyNewsActivity extends BaseActivity  {


    private RecyclerView com_newslist;
    private CompanyAdapter companyAdapter;
    private ImageView zhuceBack;
    private SmartRefreshLayout refreshLayout;
    private List<GongGao.NoticeListBean> list;
    private LinearLayoutManager  layoutManager;
    @Override
    protected void initData() {
       Observable observable = RetrofitFactory.getInstance().companyNews();
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<GongGao>(this) {

           @Override
           protected void onHandleSuccess(GongGao gongGao) {
               list = gongGao.getNotice_list();
               companyAdapter = new CompanyAdapter(R.layout.company_newsitem, list);
               com_newslist.setAdapter(companyAdapter);
               companyAdapter.notifyDataSetChanged();
               companyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                   @Override
                   public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                       Intent intent  =  new Intent();
                       intent.putExtra("id",list.get(position).getArticle_id());
                       intent.putExtra("title",list.get(position).getTitle());
                       intent.setClass(CompanyNewsActivity.this,NewsDetailActivty.class);
                       startActivity(intent);
                   }
               });
           }
       });






    }

    @Override
    protected void initId() {
        com_newslist = (RecyclerView) findViewById(R.id.com_newslist);
        zhuceBack = (ImageView) findViewById(R.id.zhuce_back);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshlayout);
        layoutManager = new LinearLayoutManager(this);

        com_newslist.setLayoutManager(layoutManager);

        //设置 Header 为 Material风格
//        refreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        //设置Header为战争游戏
//        refreshLayout.setRefreshHeader(new FunGameBattleCityHeader(this));
        //设置header为经典模式
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        //设置 Footer 为 球脉冲
//        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        zhuceBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                //写在请求里当没有更多数据的时候调用
                refreshLayout.finishLoadMoreWithNoMoreData();

                refreshLayout.finishLoadMore();
                refreshLayout.finishRefresh();

            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {

            }
        });

    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_company_news;
    }
}
