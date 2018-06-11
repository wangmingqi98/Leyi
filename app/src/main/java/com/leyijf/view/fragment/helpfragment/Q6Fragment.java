package com.leyijf.view.fragment.helpfragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.leyijf.R;
import com.leyijf.adapter.QListAdapter;
import com.leyijf.base.BaseFragment;
import com.leyijf.bean.HBean;
import com.leyijf.util.OkHttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Q6Fragment extends BaseFragment {


    private List<HBean.ArticleBean.ListBean> list1;

    public Q6Fragment() {
        // Required empty public constructor
    }


    private QListAdapter qListAdapter;



    private ListView qlist;



    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView(View view) {

        qlist=view.findViewById(R.id.qlist);


        Map map = new HashMap();
        map.put("act", "helpcenter");
        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    if (jsonObject.getInt("response_code") == 1) {
                        JSONArray jsonArray = jsonObject.getJSONObject("objects").getJSONArray("article");
                        list1 = new ArrayList<HBean.ArticleBean.ListBean>();

                        JSONArray jsonArray1 = jsonArray.getJSONObject(4).getJSONObject("article").getJSONArray("list");
                        for (int j = 0; j < jsonArray1.length(); j++) {
                            HBean.ArticleBean.ListBean articleBean = new HBean.ArticleBean.ListBean(jsonArray1.getJSONObject(j).getString("id"),
                                    jsonArray1.getJSONObject(j).getString("title"), jsonArray1.getJSONObject(j).getString("content"));
                            list1.add(articleBean);

                        }
                        getActivity().runOnUiThread(new TimerTask() {
                            @Override
                            public void run() {
                                qListAdapter = new QListAdapter(getActivity(), list1);
                                qlist.setAdapter(qListAdapter);
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
        return R.layout.fragment_q6;
    }

}
