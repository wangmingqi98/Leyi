package com.leyi.view.fragment.totalzchfragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.leyi.R;
import com.leyi.adapter.ToTalListAdapter;
import com.leyi.base.BaseFragment;
import com.leyi.bean.UCMoneyLogBean;
import com.leyi.bean.UserInfo;
import com.leyi.util.OkHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends BaseFragment {


    private TextView touziqixian;
    private TextView yujishouyi;
    private LinearLayout no_internet_lin;
    private ListView shouyi_list;
    private LinearLayout has_internet_lin;
    private Map<String, String> map;
    private ToTalListAdapter toTalListAdapter;
    private List<UCMoneyLogBean.ObjectsBean.ItemBean> shouyilist;
    private UCMoneyLogBean ucMoneyLogBean;

    public StatisticsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {

        touziqixian = (TextView) view.findViewById(R.id.touziqixian);

        yujishouyi = (TextView) view.findViewById(R.id.yujishouyi);

        no_internet_lin = (LinearLayout) view.findViewById(R.id.no_internet_lin);

        shouyi_list = (ListView) view.findViewById(R.id.shouyi_list);

        has_internet_lin = (LinearLayout) view.findViewById(R.id.has_internet_lin);
        map = new HashMap<>();

        map.put("act","uc_money_log");
        map.put("email", UserInfo.getInstance().getUserPhone()+"");
        map.put("pwd",UserInfo.getInstance().getUserPwd());
        dohttp();


    }

    private void dohttp() {

        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String s=response.body().string();
                try {
                    JSONObject jsonObject=new JSONObject(s);
                    int response_code = jsonObject.getInt("response_code");


                    if(response_code==1){

                        Gson gson=new Gson();
                        ucMoneyLogBean = gson.fromJson(s, UCMoneyLogBean.class);
                        shouyilist = new ArrayList<UCMoneyLogBean.ObjectsBean.ItemBean>();

                        for (int i = 0; i < ucMoneyLogBean.getObjects().getItem().size(); i++) {
                            String money = ucMoneyLogBean.getObjects().getItem().get(i).getMoney();
                            if(!money.startsWith("-")){
                                             shouyilist.add(ucMoneyLogBean.getObjects().getItem().get(i));

                                           }



                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               toTalListAdapter = new ToTalListAdapter(getActivity(), shouyilist);
                                shouyi_list.setAdapter(toTalListAdapter);
                                yujishouyi.setText(UserInfo.getInstance().getShouyiTJ());

                            }
                        });


                    }else {





                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }


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
        return R.layout.fragment_statistics;
    }


    @Override
    public void onResume() {
        super.onResume();





    }
}
