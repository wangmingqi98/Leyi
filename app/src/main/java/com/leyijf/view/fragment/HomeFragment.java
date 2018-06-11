package com.leyijf.view.fragment;


import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.leyijf.R;
import com.leyijf.adapter.HomeListAdapter;
import com.leyijf.auto.util.GlideImage;
import com.leyijf.base.BaseFragment;
import com.leyijf.bean.BannerBean;
import com.leyijf.bean.InItBean;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.view.activity.HotDetailActivity;
import com.leyijf.weight.MarqueeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    public static final String TAG = "HomeFragment";
    private ListView listView;
    public  HomeListAdapter homeListAdapter;
    private SmartRefreshLayout smart;
    private List img_list = new ArrayList();
    private List<String> textArray = new ArrayList<>();
    private List<BannerBean.TopBean> top = new ArrayList<>();
    private List<BannerBean.HeadlineBean> headline = new ArrayList<>();
    private MarqueeView marqueeView;
    private Banner banner;
    private TextView users,moneys;
    private List<InItBean.RecommandXinyongBean> recommand_xinyong = new ArrayList<>();
    private List<InItBean.RecommandGongyingBean> recommand_gongying = new ArrayList<>();
    private List<InItBean.NewerBean> newer = new ArrayList<>();
    Handler handler = new Handler();
    public HomeFragment() {
    }






    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        listView = view.findViewById(R.id.home_list);
        View footerView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_footer,null);
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.home_headeritem, null);
        listView.addHeaderView(headerView);
        listView.addFooterView(footerView);
         users = footerView.findViewById(R.id.user);
         moneys = footerView.findViewById(R.id.moneys);
        banner = headerView.findViewById(R.id.banner);
        marqueeView = headerView.findViewById(R.id.marqueeview);

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), HotDetailActivity.class);
                Log.d(TAG, "OnBannerClick: "+top.get(position).getUrl());
                intent.putExtra("url",top.get(position).getUrl());

                getActivity().startActivity(intent);
            }
        });
        smart = view.findViewById(R.id.smart);
        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getNews();
                getData();
            }
        });
        getNews();
        getData();



    }



    private void getData() {
        Observable observable = RetrofitFactory.getInstance().getRecomond();
        observable.compose(compose(HomeFragment.this.bindToLifecycle())).subscribe(new BaseObserver<InItBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(InItBean inItBean) {
                if(inItBean.getNewer().size()>0){
                    newer.addAll(inItBean.getNewer());
                }
                if(inItBean.getRecommand_gongying().size()>0){
                    for(int i = 0;i<inItBean.getRecommand_gongying().size();i++){
                        if(!inItBean.getRecommand_gongying().get(i).getDeal_status().equals(0)&&
                                !inItBean.getRecommand_gongying().get(i).getDeal_status().equals("2")&&
                                !inItBean.getRecommand_gongying().get(i).getDeal_status().equals("3")){
                                InItBean.RecommandGongyingBean recommandGongyingBean = inItBean.getRecommand_gongying().get(i);
                                recommand_gongying.add(recommandGongyingBean);
                        }
                    }
                }
                if(inItBean.getRecommand_xinyong().size()>0){
                    for(int i = 0;i<inItBean.getRecommand_gongying().size();i++){
                        if(!inItBean.getRecommand_xinyong().get(i).getDeal_status().equals("0")&&
                                !inItBean.getRecommand_xinyong().get(i).getDeal_status().equals("2")&&
                                !inItBean.getRecommand_xinyong().get(i).getDeal_status().equals("3")){
                            InItBean.RecommandXinyongBean recommandXinyongBean = inItBean.getRecommand_xinyong().get(i);
                            recommand_xinyong.add(recommandXinyongBean);

                        }
                    }

                }
                users.setText(inItBean.getVirtual().getVIRTUAL_USERS()+"");
                moneys.setText(inItBean.getVirtual().getVIRTUAL_MONEY_1()+"");
                homeListAdapter = new HomeListAdapter(getActivity(),recommand_xinyong,recommand_gongying,newer);
                listView.setAdapter(homeListAdapter);
                homeListAdapter.notifyDataSetChanged();
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
        return R.layout.fragment_home;
    }

    /**
     * 获取首页banner图与广告
     */
    private void getNews() {
        int is_app = 1;
        Observable observable = RetrofitFactory.getInstance().getBanner(is_app);
        observable.compose(compose(HomeFragment.this.bindToLifecycle())).subscribe(new BaseObserver<BannerBean>(getActivity()) {
            @Override
            protected void onHandleSuccess(BannerBean bannerBean) {
                if(bannerBean.getHeadline().size()>0){
                    headline.addAll(bannerBean.getHeadline());
                    for (int i  =0;i<headline.size();i++){
                        textArray.add(headline.get(i).getName());
                    }
                }
                if(bannerBean.getTop().size()>0){
                   top.addAll( bannerBean.getTop());
                   for(int i = 0;i<top.size();i++){
                    img_list.add(top.get(i).getImg());
                   }
                }
                marqueeView.setTextArray(textArray);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        marqueeView.resume();
                    }
                },1000);
                banner.setImages(img_list)//添加图片集合或图片url集合
                        .setDelayTime(2000)//设置轮播时间
                        .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                        .setImageLoader(new GlideImage())//加载图片
                        .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)  //设置数字指示器
                        .setIndicatorGravity(BannerConfig.CENTER)//设置指示器位置
                        .start();

            }


        });
    }


}
