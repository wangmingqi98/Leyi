package com.leyi.view.fragment.fragment_touzi;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.leyi.R;
import com.leyi.adapter.RenshuAdapter;
import com.leyi.base.BaseFragment;
import com.leyi.bean.TouziRenshuBean;
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
public class TouziRenshuFragment extends BaseFragment {


    public ListView renshulist;
    public RenshuAdapter renshuAdapter;
  public   int count;
    private TouziRenshuBean touziRenshuBean;

    public TouziRenshuFragment() {
        // Required empty public constructor
    }




    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {
        renshulist = view.findViewById(R.id.renshulist);

        Map map=new HashMap();
        map.put("act","v2_deals_buy_record");
        map.put("deal_id",count+"");

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

                        Gson gson=new Gson();
                        touziRenshuBean = gson.fromJson(string, TouziRenshuBean.class);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                renshuAdapter = new RenshuAdapter(getActivity(),touziRenshuBean.getObjects().getBuy_record().get(0).getRecords());
                                renshulist.setAdapter(renshuAdapter);
                            }
                        });



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
        return R.layout.fragment_touzi_renshu;
    }




}
