package com.leyijf.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.BindBankCardBean;
import com.leyijf.bean.BindedCardBean;
import com.leyijf.bean.UserBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;
import com.leyijf.weight.weel.JsonBean;
import com.leyijf.weight.weel.JsonFileReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * 绑定银行卡
 */
public class BindBankCardActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "BindBankCardActivity";
    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
    private EditText kaname;
    private EditText kahao;
    private TextView cadiqu;
    private EditText cazhihang;
    private TextView next_stepshen;
    private TextView next_stepqian;
    private LinearLayout cadiqu_lin;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Map<String, String> map;
    private String real_name,bankcard,region_lv,zh;
    private String id;
    private String name;

    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        initView();
        initJsonData();




    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_bind_bank_card;
    }

    private void initView() {
        map = new HashMap<>();
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        zhuce_back.setOnClickListener(this);
        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);
        kaname = (EditText) findViewById(R.id.kaname);
        kahao = (EditText) findViewById(R.id.kahao);
        cadiqu = (TextView) findViewById(R.id.cadiqu);
        cazhihang = (EditText) findViewById(R.id.cazhihang);
        next_stepshen = (TextView) findViewById(R.id.next_stepshen);
        next_stepqian = (TextView) findViewById(R.id.next_stepqian);
        next_stepqian.setVisibility(View.GONE);
        next_stepshen.setVisibility(View.VISIBLE);
        cadiqu_lin = (LinearLayout) findViewById(R.id.cadiqu_lin);
        cadiqu_lin.setOnClickListener(this);
        next_stepshen.setOnClickListener(this);




    }

    private void doexam() {
        if(TextUtils.isEmpty(kaname.getText().toString())||TextUtils.isEmpty(kahao.getText().toString())||TextUtils.isEmpty(cazhihang.getText().toString())){
            Toast.makeText(this, "信息不能为空", Toast.LENGTH_SHORT).show();
        }else {
            addCard();
        }



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhuce_back:
                finish();
                break;
            case R.id.cadiqu_lin:
                showPickerView();
                break;

            case R.id.next_stepshen:
                doexam();

                break;
        }
    }

    /**
     * 绑定银行卡
     */
    private void addCard() {
        String username = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String pwd = UserInfo.getInstance().getUserPwd();
        if(!TextUtils.isEmpty(kaname.getText().toString())){
             real_name =kaname.getText().toString();
        }
        if(!TextUtils.isEmpty(kahao.getText().toString())){
             bankcard = kahao.getText().toString();
        }
//        if(!TextUtils.isEmpty(cadiqu.getText().toString())){
//             region_lv = cadiqu.getText().toString();
//        }
        if(!TextUtils.isEmpty(cazhihang.getText().toString())){
             zh = cazhihang.getText().toString();
        }
            JSONObject object = new JSONObject();
            try {
                object.put("email", username);
                object.put("pwd", pwd);
                object.put("real_name", real_name);
                object.put("bankcard", bankcard);
                object.put("region_lv", region_lv);
                object.put("zh", zh);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String requestData = Aes128.encrypt((object.toString()));
            String jjjjj = Aes128.decrypt(requestData);
            Log.d(TAG, "addCard: " + jjjjj);
            Log.d(TAG, "addCard: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().bindBankCard(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<BindBankCardBean>(this) {
            @Override
            protected void onHandleSuccess(BindBankCardBean bindBankCardBean) {
                Toast.makeText(BindBankCardActivity.this,"绑卡成功！",Toast.LENGTH_LONG).show();
                UserBean userBean = new UserBean();

                List<BindedCardBean>  list = new ArrayList<>();
                userBean.setUser_name(bindBankCardBean.getUser_info().getUser_name());
                userBean.setUser_id(bindBankCardBean.getUser_info().getUser_id()+"");
                userBean.setHas_paypassword(bindBankCardBean.getUser_info().getHas_paypassword());
                userBean.setId_passed(bindBankCardBean.getUser_info().getId_passed());
                userBean.setUser_img(bindBankCardBean.getUser_info().getUser_img());
                userBean.setUser_mobile(bindBankCardBean.getUser_info().getUser_mobile());
                if(bindBankCardBean.getUser_info().getBinded_card().size()>0){
//                    list.addAll(bindBankCardBean.getUser_info().getBinded_card());
                    for(int i = 0;i<bindBankCardBean.getUser_info().getBinded_card().size();i++){
                        BindedCardBean bindedCardBean = new BindedCardBean();
                        bindedCardBean.setBank_name(bindBankCardBean.getUser_info().getBinded_card().get(i).getBank_name());
                        bindedCardBean.setBank_id(bindBankCardBean.getUser_info().getBinded_card().get(i).getBank_id());
                        bindedCardBean.setBank_card_num(bindBankCardBean.getUser_info().getBinded_card().get(i).getBank_card_num());
                        bindedCardBean.setBank_card_icon(bindBankCardBean.getUser_info().getBinded_card().get(i).getBank_card_icon());
                        list.add(bindedCardBean);
                        UserManager.getInstance().saveLoginedType( bindedCardBean);
                    }
                }
                UserManager.getInstance().updateLoginedUser(userBean);
                finish();
            }


        });


    }


    private void showPickerView() {
        OptionsPickerView pvOptions=new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String text = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                region_lv = options1Items.get(options1).getPickerViewText()+","+ options2Items.get(options1).get(options2)+","+options3Items.get(options1).get(options2).get(options3);
                cadiqu.setText(text);

            }
        })
                .setDividerColor(Color.WHITE)
                .setTextColorCenter(Color.BLACK)
                .setTextColorOut(Color.GRAY)
                .setContentTextSize(14)
                .setSubmitColor(Color.RED)
                .setCancelColor(Color.GRAY)
                .setOutSideCancelable(false)
                .setTitleText("请选择区域")
                .build();
          /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }




    private void initJsonData() {   //解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        //  获取json数据
        String JsonData = JsonFileReader.getJson(this, "province_data.json");
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1234&&resultCode==1234){
            Bundle extras = data.getExtras();
            name = extras.getString("name");
            id = extras.getString("id");


        }


    }
}
