package com.leyi.view.fragment.fragment_touzi;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.leyi.R;
import com.leyi.base.BaseFragment;
import com.leyi.bean.RePayUser;
import com.leyi.util.OkHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsideUserInfoFragment extends BaseFragment {


    private TextView hukuan_name;
    private TextView xingming;
    private TextView nianling;
    private TextView xingbie;
    private TextView shimingxinxi;
    private TextView jiekuancishu;
    private TextView yuqicishu;
    private TextView shoujihaoma;
    private TextView xuelixinxi;
    private TextView changzhudizhi;
    private TextView hujidizhi;
    private TextView youwugouche;
    private TextView youwuchedai;
    private TextView jiekuanhetong;
    private TextView danbaoxieyi;
    private TextView hunyin;
    private TextView gongzuodanwei;
    private TextView zhiyeshouru;
    private TextView shenghuojiaofei;
    private TextView lianxiren;
    private TextView youwugoufang;
    private TextView youwufangdai;
    private TextView jiekuanxieyi;
    private TextView yinhangliushui;
    RePayUser rePayUser;
    private String deal_count;
    private String yuqi_count;

    public InsideUserInfoFragment(RePayUser rePayUser) {
        // Required empty public constructor
        this.rePayUser=rePayUser;


    }



    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {

        hukuan_name = (TextView)view.findViewById(R.id.hukuan_name);
        xingming = (TextView) view.findViewById(R.id.xingming);
         nianling = (TextView)view.findViewById(R.id.nianling);

        xingbie = (TextView) view.findViewById(R.id.xingbie);
        jiekuancishu = (TextView) view.findViewById(R.id.jiekuancishu);

        shimingxinxi = (TextView) view.findViewById(R.id.shimingxinxi);

        yuqicishu = (TextView) view.findViewById(R.id.yuqicishu);

        shoujihaoma = (TextView)view.findViewById(R.id.shoujihaoma);

        xuelixinxi = (TextView) view.findViewById(R.id.xuelixinxi);

        changzhudizhi = (TextView) view.findViewById(R.id.changzhudizhi);

        hujidizhi = (TextView)view.findViewById(R.id.hujidizhi);

        youwugouche = (TextView)view.findViewById(R.id.youwugouche);

        youwuchedai = (TextView) view.findViewById(R.id.youwuchedai);

        jiekuanhetong = (TextView) view.findViewById(R.id.jiekuanhetong);

        danbaoxieyi = (TextView) view.findViewById(R.id.danbaoxieyi);

        hunyin = (TextView) view.findViewById(R.id.hunyin);

        gongzuodanwei = (TextView) view.findViewById(R.id.gongzuodanwei);

        zhiyeshouru = (TextView)view.findViewById(R.id.zhiyeshouru);

        shenghuojiaofei = (TextView)view.findViewById(R.id.shenghuojiaofei);

        lianxiren = (TextView) view.findViewById(R.id.lianxiren);

        youwugoufang = (TextView)view.findViewById(R.id.youwugoufang);

        youwufangdai = (TextView) view.findViewById(R.id.youwufangdai);

        jiekuanxieyi = (TextView) view.findViewById(R.id.jiekuanxieyi);

        yinhangliushui = (TextView)view.findViewById(R.id.yinhangliushui);

    }

    @Override
    protected void initData() {


        Map map=new HashMap();
        map.put("act","v2_deals_borrower_info");
        map.put("deal_id",rePayUser.getId());

        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.e("string---",""+string);
                try {
                    JSONObject jsonObject=new JSONObject(string);
                    if(jsonObject.getInt("response_code")==1){
                        deal_count = jsonObject.getJSONObject("objects").getString("deal_count");
                        yuqi_count = jsonObject.getJSONObject("objects").getString("yuqi_count");


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }








            }
        });



        xingming.setText(rePayUser.getUser_name());
        if(rePayUser.getSex().equals("1")){
            xingbie.setText("男");
        }else {
            xingbie.setText("女");
        }

        nianling.setText(2018-Integer.parseInt(rePayUser.getByear())+"");
        yuqicishu.setText(yuqi_count+"");
        jiekuancishu.setText(deal_count+"");
        if(rePayUser.getReal_name().length()==2){
            shimingxinxi.setText(rePayUser.getReal_name().charAt(0)+"**");
        }else {
            shimingxinxi.setText(rePayUser.getReal_name().charAt(0)+"***");
        }
       String substring = rePayUser.getMobile().substring(0, 4);
        String substring1 = rePayUser.getMobile().substring(rePayUser.getMobile().length() - 3, rePayUser.getMobile().length());
        String s = substring + "****" + substring1;
        shoujihaoma.setText(s);






    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_inside_user_info;
    }

}
