package com.leyi.view.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.leyi.R;
import com.leyi.adapter.MyindentViewpagerAdapter;
import com.leyi.base.BaseActivity;
import com.leyi.bean.UCMoneyLogBean;
import com.leyi.bean.UserInfo;
import com.leyi.util.OkHttpUtil;
import com.leyi.view.fragment.tradcordfragment.AllFragment;
import com.leyi.view.fragment.tradcordfragment.ChongzhiFragment;
import com.leyi.view.fragment.tradcordfragment.TixianFragment;
import com.leyi.view.fragment.tradcordfragment.TouziFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 交易记录
 */

public class TradingrecordActivity extends BaseActivity {


    private ImageView mIvMyindentBack;
    private TabLayout mTablayoutMyindent;
    private ViewPager mViewpagerMyindent;
    private String[] mStringList = {"全部","投资","充值","提现"};
    private List<Fragment> mList = new ArrayList<>();
    private Intent intent;
    private UCMoneyLogBean ucMoneyLogBean;
    private List<UCMoneyLogBean.ObjectsBean.ItemBean> shouyilist;
    private List<UCMoneyLogBean.ObjectsBean.ItemBean> tixianlist;
    private List<UCMoneyLogBean.ObjectsBean.ItemBean> touzilist;
    private List<UCMoneyLogBean.ObjectsBean.ItemBean> chongzhilist;
    private int pageTotal;

    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        intent = getIntent();

        mIvMyindentBack = (ImageView) findViewById(R.id.zhuce_back);
        mIvMyindentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mTablayoutMyindent = (TabLayout) findViewById(R.id.tablayout_myindent);
        mViewpagerMyindent = (ViewPager) findViewById(R.id.viewpager_myindent);


        initdata();

        initView();
    }

    private void initdata() {

        Map<String,String> map=new HashMap<>();
        map.put("act","uc_money_log");
        map.put("email", UserInfo.getInstance().getUserPhone());
        map.put("pwd",UserInfo.getInstance().getUserPwd());



//        输入参数：
//        act=uc_lend
//        email: 用户名或邮箱
//        pwd: 密码
//        page：当前分页数
//
        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();


                        Log.e("===touzi",string+"");
                        try {
                            JSONObject jsonObject=new JSONObject(string);
                            int response_code = jsonObject.getInt("response_code");

                            if(response_code==1){
                                Gson gson=new Gson();
                                ucMoneyLogBean = gson.fromJson(string, UCMoneyLogBean.class);
                                shouyilist = new ArrayList<UCMoneyLogBean.ObjectsBean.ItemBean>();
                                tixianlist = new ArrayList<UCMoneyLogBean.ObjectsBean.ItemBean>();
                                touzilist = new ArrayList<UCMoneyLogBean.ObjectsBean.ItemBean>();
                                chongzhilist = new ArrayList<UCMoneyLogBean.ObjectsBean.ItemBean>();
                                pageTotal=ucMoneyLogBean.getObjects().getPage().getPage_total();

                                for (int j = 0; j <=pageTotal ; j++) {


                                    for (int i = 0; i < ucMoneyLogBean.getObjects().getItem().size(); i++) {
                                        if (ucMoneyLogBean.getObjects().getItem().get(i).getLog_info().contains("提现")) {
                                            tixianlist.add(ucMoneyLogBean.getObjects().getItem().get(i));
                                        }
                                        if (ucMoneyLogBean.getObjects().getItem().get(i).getLog_info().contains("充值")) {
                                            chongzhilist.add(ucMoneyLogBean.getObjects().getItem().get(i));
                                        }
                                        if (ucMoneyLogBean.getObjects().getItem().get(i).getLog_info().contains("投标") ||
                                                ucMoneyLogBean.getObjects().getItem().get(i).getLog_info().contains("回报")) {
                                            touzilist.add(ucMoneyLogBean.getObjects().getItem().get(i));
                                        }


                                        shouyilist.add(ucMoneyLogBean.getObjects().getItem().get(i));

                                    }

                                    Map map1=new HashMap();
                                    map1.put("act","uc_money_log");
                                    map1.put("email", UserInfo.getInstance().getUserPhone());
                                    map1.put("pwd",UserInfo.getInstance().getUserPwd());
                                    map1.put("page",j+2+"");
                                    OkHttpUtil.getInstance().doHttp(map1, new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {

                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {
                                            String string = response.body().string();
                                            Log.e("===touzi",string+"");
                                            JSONObject jsonObject= null;
                                            try {
                                                jsonObject = new JSONObject(string);
                                                int response_code = jsonObject.getInt("response_code");
                                                if(response_code==1){

                                                    Gson gson=new Gson();
                                                   UCMoneyLogBean   ucMoneyLogBeans = gson.fromJson(string, UCMoneyLogBean.class);
                                                   final List<UCMoneyLogBean.ObjectsBean.ItemBean>  shouyilists = new ArrayList<UCMoneyLogBean.ObjectsBean.ItemBean>();
                                                    final List<UCMoneyLogBean.ObjectsBean.ItemBean> tixianlists = new ArrayList<UCMoneyLogBean.ObjectsBean.ItemBean>();
                                                    final List<UCMoneyLogBean.ObjectsBean.ItemBean>  touzilists = new ArrayList<UCMoneyLogBean.ObjectsBean.ItemBean>();
                                                    final List<UCMoneyLogBean.ObjectsBean.ItemBean>  chongzhilists = new ArrayList<UCMoneyLogBean.ObjectsBean.ItemBean>();
                                                    for (int i = 0; i < ucMoneyLogBeans.getObjects().getItem().size(); i++) {
                                                        if (ucMoneyLogBeans.getObjects().getItem().get(i).getLog_info().contains("提现")) {
                                                            tixianlists.add(ucMoneyLogBeans.getObjects().getItem().get(i));
                                                        }
                                                        if (ucMoneyLogBeans.getObjects().getItem().get(i).getLog_info().contains("充值")) {
                                                            chongzhilists.add(ucMoneyLogBeans.getObjects().getItem().get(i));
                                                        }
                                                        if (ucMoneyLogBeans.getObjects().getItem().get(i).getLog_info().contains("投标") ||
                                                                ucMoneyLogBeans.getObjects().getItem().get(i).getLog_info().contains("回报")) {
                                                            touzilists.add(ucMoneyLogBeans.getObjects().getItem().get(i));
                                                        }


                                                        shouyilists.add(ucMoneyLogBeans.getObjects().getItem().get(i));

                                                    }

                                                  runOnUiThread(new Runnable() {
                                                      @Override
                                                      public void run() {
                                                          tixianlist.addAll(tixianlists );
                                                          shouyilist.addAll(shouyilists);
                                                          chongzhilist.addAll(chongzhilists);
                                                          touzilist.addAll(touzilists);
                                                      }
                                                  });


                                                }else {
                                                    final String show_err = jsonObject.getString("show_err");
                                                    TradingrecordActivity.this.runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toast.makeText(TradingrecordActivity.this, show_err+"", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }



                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }



                                        }
                                    });
                                }

                                TradingrecordActivity.this. runOnUiThread(new Runnable() {
                                     @Override
                                     public void run() {
                                         mList.add(new AllFragment(shouyilist));
                                         mList.add(new TouziFragment(touzilist));
                                         mList.add(new ChongzhiFragment(chongzhilist));
                                         mList.add(new TixianFragment(tixianlist));
                                         MyindentViewpagerAdapter adapter = new MyindentViewpagerAdapter(getSupportFragmentManager(), TradingrecordActivity.this, mList, Arrays.asList(mStringList));
                                         mViewpagerMyindent.setAdapter(adapter);
                                         mTablayoutMyindent.setupWithViewPager(mViewpagerMyindent);
                                         mViewpagerMyindent.setCurrentItem(0);
                                     }
                                 });






                            }else {
                                final String show_err = jsonObject.getString("show_err");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(TradingrecordActivity.this, show_err+"", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });






            }


    private void initView() {

//        MyindentFragment myindentFragment = new MyindentFragment();
//        mList.add(new MyindentFragment());



    }


    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_tradingrecord;
    }

}

