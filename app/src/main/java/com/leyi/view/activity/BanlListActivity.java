package com.leyi.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.leyi.R;
import com.leyi.adapter.SelectoeBankAdapter;
import com.leyi.base.BaseActivity;
import com.leyi.bean.SelectorBank;
import com.leyi.bean.UserInfo;
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
 * 银行列表页面
 */
public class BanlListActivity extends BaseActivity {

    private ImageView zhuce_back;

    private ListView limt_list;
    private List<SelectorBank> list;


    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        initView();
        toRequestBankList();


    }

    private void toRequestBankList() {

        Map<String,String> map=new HashMap<>();
        map.put("act","uc_add_bank");
        map.put("email", UserInfo.getInstance().getUserPhone());
        map.put("pwd",UserInfo.getInstance().getUserPwd());

        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.e("---uc_recharge_app--",string+"");
//

                JSONObject jsonObject= null;
                try {
                    jsonObject = new JSONObject(string);
                    int response_code = jsonObject.getInt("response_code");
                    if(response_code==1){
                        JSONArray jsonArray = jsonObject.getJSONObject("objects").getJSONArray("item");
                        list = new ArrayList<SelectorBank>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            SelectorBank selectorBank=new SelectorBank(jsonArray.getJSONObject(i).getString("id"),jsonArray.getJSONObject(i).getString("name"),jsonArray.getJSONObject(i).getString("is_rec")
                            ,jsonArray.getJSONObject(i).getString("day"),jsonArray.getJSONObject(i).getString("sort"),jsonArray.getJSONObject(i).getString("icon"),jsonArray.getJSONObject(i).getString("code"));

                            list.add(selectorBank);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SelectoeBankAdapter selectoeBankAdapter=new SelectoeBankAdapter(BanlListActivity.this,list);
                                limt_list.setAdapter(selectoeBankAdapter);

                                limt_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                           ImageView imageView=view.findViewById(R.id.biaozhi);
                                            imageView.setVisibility(View.VISIBLE);

                                        Intent intent=new Intent();
                                        Bundle bundle=new Bundle();
                                        bundle.putString("name",list.get(position).getName());
                                        bundle.putString("id",list.get(position).getId());
                                        intent.putExtras(bundle);
                                        setResult(1234,intent);
                                        finish();






                                    }
                                });




                            }
                        });


                    }else {
                        final String show_err = jsonObject.getString("show_err");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(BanlListActivity.this, show_err+"", Toast.LENGTH_SHORT).show();
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
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_banl_list;
    }

    private void initView() {
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        limt_list = (ListView) findViewById(R.id.limt_list);
    }
}
