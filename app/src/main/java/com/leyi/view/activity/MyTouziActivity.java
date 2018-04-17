package com.leyi.view.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.leyi.R;
import com.leyi.adapter.MyindentViewpagerAdapter;
import com.leyi.base.BaseActivity;
import com.leyi.bean.UCLendBean;
import com.leyi.bean.UserInfo;
import com.leyi.util.OkHttpUtil;
import com.leyi.view.fragment.mytouzi_fragment.AlHuanFragment;
import com.leyi.view.fragment.mytouzi_fragment.AlLiuFragment;
import com.leyi.view.fragment.mytouzi_fragment.AlTouziFragment;
import com.leyi.view.fragment.mytouzi_fragment.BackMoneytFragment;
import com.leyi.view.fragment.mytouzi_fragment.QuanBuFragment;

import org.json.JSONArray;
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

public class MyTouziActivity extends BaseActivity {


    private ImageView mIvMyindentBack;
    private TabLayout mTablayoutMyindent;
    private ViewPager mViewpagerMyindent;
    private String[] mStringList = {"全部","已投资","回款中","已还清","已流标"};
    private List<Fragment> mList = new ArrayList<>();
    private Intent intent;
    private List<UCLendBean> list;

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



        initView();
    }

    private void inidata() {

        Map<String,String> map=new HashMap<>();
        map.put("act","uc_lend");
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("===touzi",string+"");
                        try {
                            JSONObject jsonObject=new JSONObject(string);
                            int response_code = jsonObject.getInt("response_code");

                            if(response_code==1){
                                list = new ArrayList<UCLendBean>();
                                JSONArray jsonArray = jsonObject.getJSONObject("objects").getJSONArray("item");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    Log.e("===touzi","-----------");
                                    Gson gson=new Gson();
                                    UCLendBean ucLendBean = gson.fromJson(jsonObject1.toString(), UCLendBean.class);
                                    Log.e("------------",ucLendBean.toString());
                                    list.add(ucLendBean);


                                }
                                mList.add(new QuanBuFragment(list));
                                mList.add(new AlTouziFragment(list));
                                mList.add(new BackMoneytFragment(list));
                                mList.add(new AlHuanFragment(list));
                                mList.add(new AlLiuFragment(list));

                                MyindentViewpagerAdapter adapter = new MyindentViewpagerAdapter(getSupportFragmentManager(), MyTouziActivity.this, mList, Arrays.asList(mStringList));
                                mViewpagerMyindent.setAdapter(adapter);
                                mTablayoutMyindent.setupWithViewPager(mViewpagerMyindent);
                                mViewpagerMyindent.setCurrentItem(0);



                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });






            }
        });


    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_my_touzi;
    }

    private void initView() {

        inidata();


//        MyindentFragment myindentFragment = new MyindentFragment();
//        mList.add(new MyindentFragment());

    }

}
