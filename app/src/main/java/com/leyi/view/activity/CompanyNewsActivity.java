package com.leyi.view.activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.leyi.R;
import com.leyi.adapter.CompanyAdapter;
import com.leyi.base.BaseActivity;
import com.leyi.bean.GongGao;
import com.leyi.util.OkHttpUtil;

import org.json.JSONArray;
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
 * 公司公告
 */
public class CompanyNewsActivity extends BaseActivity {


    private ListView com_newslist;
    private CompanyAdapter companyAdapter;
    private ImageView zhuceBack;
    private List<GongGao> list;

    @Override
    protected void initData() {
        Map<String,String> map=new HashMap<>();
        map.put("act","website_notice");
        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.e("---website_notice--",string+"");
                JSONObject jsonObject= null;
                try {
                    jsonObject = new JSONObject(string);
                    int response_code = jsonObject.getInt("response_code");
                    if(response_code==1){
                        list = new ArrayList<GongGao>();
                        JSONArray jsonArray = jsonObject.getJSONObject("objects").getJSONArray("notice_list");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            GongGao gong=new GongGao(jsonArray.getJSONObject(i).getString("article_id"),jsonArray.getJSONObject(i).getString("title"));
                            list.add(gong);

                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                companyAdapter = new CompanyAdapter(CompanyNewsActivity.this, list);
                                com_newslist.setAdapter(companyAdapter);
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
    protected void initId() {
        com_newslist = (ListView) findViewById(R.id.com_newslist);
        zhuceBack = (ImageView) findViewById(R.id.zhuce_back);
        zhuceBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_company_news;
    }
}
