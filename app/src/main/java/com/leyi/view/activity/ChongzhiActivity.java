package com.leyi.view.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fuiou.mobile.FyPay;
import com.fuiou.mobile.FyPayCallBack;
import com.fuiou.mobile.bean.MchantMsgBean;
import com.fuiou.mobile.util.EncryptUtils;
import com.leyi.R;
import com.leyi.adapter.BankListAdapter;
import com.leyi.base.BaseActivity;
import com.leyi.bean.BankCardBean;
import com.leyi.bean.CodeBean;
import com.leyi.bean.FuYoPayResult;
import com.leyi.bean.UserInfo;
import com.leyi.util.OkHttpUtil;
import com.leyi.util.XmlUtils;

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
import okhttp3.HttpUrl;
import okhttp3.Response;

/**
 * 充值页面
 */
public class ChongzhiActivity extends BaseActivity implements View.OnClickListener{

    private ImageView zhuce_back;
    private RelativeLayout title_zixun;
    private TextView xiane;
    private LinearLayout add_bankcard;
    private TextView canuse_money;
    private EditText chongzhi_edit;
    private TextView next_stepshen;
    private TextView next_stepqian;
    private Map<String, String> map;
    private ListView bankcardlist;
    private List<BankCardBean> list;
    private String recharge_money;
    private String mchnt_orderid;
    private String back_url;
    private String user_id;
    private String user_real_name;
    private String user_idno;
    private String user_bankcard;
    private Map<String, String> map2;
    private Map map3;
    private String version;
    private int enctp;
    private String logotp;
    private String mchntcd;
    private String fm;
    private HttpUrl url;
    private String temp, ordernumber;
//    private String mchnt_key = "d8n0dh23w2yzrnez52ocqb4ckzp7t0fs";
    private String mchnt_key = "z4oldjrg872hov8h5yo1nrkw0ng6tajf";
    private String mchnt_cd = "0002900F0496836";

    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        FyPay.setDev(true);//此代码是配置jar包为环境配置，true是生产   false测试
        FyPay.init(ChongzhiActivity.this);
        initView();

    }

    @Override
    protected int getContentViewId() {
        withColor();
        return R.layout.activity_chongzhi;
    }

    private void initView() {



        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        zhuce_back.setOnClickListener(this);
        title_zixun = (RelativeLayout) findViewById(R.id.title_zixun);
        xiane = (TextView) findViewById(R.id.xiane);
        xiane.setOnClickListener(this);
        add_bankcard = (LinearLayout) findViewById(R.id.add_bankcard);
        add_bankcard.setOnClickListener(this);
        canuse_money = (TextView) findViewById(R.id.canuse_money);
        canuse_money.setText(UserInfo.getInstance().getUseful_money());
        chongzhi_edit = (EditText) findViewById(R.id.chongzhi_edit);
        next_stepshen = (TextView) findViewById(R.id.next_stepshen);
        bankcardlist = (ListView) findViewById(R.id.bankcardlist);

        next_stepqian = (TextView) findViewById(R.id.next_stepqian);
        next_stepshen.setOnClickListener(this);


        bankcardlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


        chongzhi_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
             if(!TextUtils.isEmpty(s.toString())){
                 next_stepqian.setVisibility(View.GONE);
                 next_stepshen.setVisibility(View.VISIBLE);


             }else {
                 next_stepqian.setVisibility(View.VISIBLE);
                 next_stepshen.setVisibility(View.GONE);
             }


            }
        });





    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhuce_back:
                finish();
                break;

            case R.id.xiane:
                Intent intent=new Intent(this,LimtActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("MCHNTCD",mchnt_cd);
                bundle.putString("SIGN","0002900F0496836|z4oldjrg872hov8h5yo1nrkw0ng6tajf");
                intent.putExtras(bundle);



                startActivity(intent);
                break;

            case R.id.add_bankcard:
                startActivity(new Intent(this,BindBankCardActivity.class));
                break;
            case R.id.next_stepshen:
//                确认充值
                saveIncharge();


//                Pay();


                break;



        }
    }

    private void saveIncharge() {

   Map<String,String> map=new HashMap<>();
        map.put("act","uc_recharge_app");
        map.put("email",UserInfo.getInstance().getUserPhone());
        map.put("pwd",UserInfo.getInstance().getUserPwd());
        map.put("money",chongzhi_edit.getText().toString().trim());
        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                Log.e("---uc_recharge_app--",string+"");
//                user_id: 用户id
//                user_real_name:真实名称
//                user_idno:身份证号码
//                user_bankcard:充值的银行卡号
//                recharge_money:充值金额
//                mchnt_orderid:订单号
//                back_url:回调ur
                try {
                    JSONObject jsonObject=new JSONObject(string);
                    int response_code = jsonObject.getInt("response_code");
                    if(response_code==1){
                        JSONObject jsonObject1 = jsonObject.getJSONObject("objects").getJSONObject("data");
                        recharge_money = jsonObject1.getString("recharge_money");
                        mchnt_orderid = jsonObject1.getString("mchnt_orderid");
                        user_id = jsonObject1.getString("user_id");
                          back_url = jsonObject1.getString("back_url");
                        user_real_name = jsonObject1.getString("user_real_name");
                        user_idno = jsonObject1.getString("user_idno");
                        user_bankcard = jsonObject1.getString("user_bankcard");
                        Log.e("--back_url",back_url+"");
                        doPay();


                            }else {
                        final String show_err = jsonObject.getString("show_err");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ChongzhiActivity.this, show_err+"", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }







                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });


    }

    private void doPay() {

        map3 = new HashMap<String, String>();

        MchantMsgBean bean = new MchantMsgBean();
        bean.setOrderId(mchnt_orderid);
        bean.setKey(mchnt_key);
        bean.setMchntCd(mchnt_cd);//设置商户号
        bean.setAmt(recharge_money);
        bean.setUserId(user_id);
        bean.setCardNo(user_bankcard);
        bean.setIDcardType("0");
        bean.setIDNo(user_idno);
        bean.setUserName(user_real_name);
        bean.setBackUrl(back_url);
        bean.setPayType("mobilePay");


        final int i = FyPay.pay(ChongzhiActivity.this, bean, new FyPayCallBack() {

            @Override
            public void onPayComplete(String rspCode, final String rspDesc, Bundle extraData) {
                // rspCode: 0001（唯一）；
                // rspDesc：用户取消支付（唯一）；
                // extraData：支付传递的参数。
                // 考虑不同的商户ui设计的不同，所以这里商户自行根据响应对界面做成功或者失败的展示
                Log.i("http", "rspCode = " + rspCode + " ; rspDesc = " + rspDesc);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ChongzhiActivity.this, rspDesc+"", Toast.LENGTH_SHORT).show();
                    }
                });


            }

            @Override
            public void onPayBackMessage(
                    String paramString) {
                // 支付返回处理，商户根据返回的paramString（参数参考表3.3.2）自行解析做界面展示，这里不再做技术解析说明！
                Log.i("http", "onPayBackMessage >>>"+ paramString);
//                                                p: onPayBackMessage >>>"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><RESPONSE><VERSION>2.0</VERSION><RESPONSECODE>999998</RESPONSECODE><RESPONSEMSG>交易失败，其他类型错误 | 故障怀疑,关联交易错误(默认场景)

             String   paramStrings = paramString.substring(61,paramString.length()-1);

                FuYoPayResult fuYoPayResult = XmlUtils.toBean(FuYoPayResult.class, paramStrings.getBytes());
                 final String responsecode = fuYoPayResult.getRESPONSECODE();
                Log.e("----code",responsecode);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String s = CodeBean.thePayResult(responsecode);
                        Toast.makeText(ChongzhiActivity.this, s+"", Toast.LENGTH_SHORT).show();


                    }
                });


            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();

        map = new HashMap<>();
        map.put("act","uc_bank");
        map.put("email", UserInfo.getInstance().getUserPhone());
        map.put("pwd",UserInfo.getInstance().getUserPwd());
        OkHttpUtil.getInstance().doHttp(map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                Log.e("---chongzhi--",string+"");

                try {
                    JSONObject jsonObject=new JSONObject(string);
                    int response_code = jsonObject.getInt("response_code");
                    if(response_code==1){
                        JSONArray jsonArray = jsonObject.getJSONObject("objects").getJSONArray("item");
                        list = new ArrayList<BankCardBean>();
                        if(jsonArray.length()>0){
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                BankCardBean bankCardBean=new BankCardBean(jsonObject1.getString("id"),jsonObject1.getString("bankcard"),jsonObject1.getString("real_name")
                                ,jsonObject1.getString("bank_name"),jsonObject1.getString("bank_id"),jsonObject1.getString("bankcode"),jsonObject1.getString("img"));
                                list.add(bankCardBean);
                            }
runOnUiThread(new Runnable() {
    @Override
    public void run() {
        BankListAdapter bankListAdapter=new BankListAdapter(list,ChongzhiActivity.this);
        bankcardlist.setAdapter(bankListAdapter);
    }
});

                        }else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showNoPassId();
                                }
                            });
                        }

                    }else {

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        });





    }




    //获取微信支付的响应代码，手机支付对接可以不用管
    private void getIntentValue() {
        if (getIntent() != null & getIntent().getExtras() != null) {

            String paycode = getIntent().getExtras().getString("PAYCODE");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage(getResources().getString(R.string.pay_result_callback_msg)+paycode);
            builder.setMessage(paycode);
            builder.show();
        }


    }

//    private void initView() {
////        mchnt_cd = (EditText) findViewById(R.id.mchnt_cd);
////        amount = (EditText) findViewById(R.id.amount);
////        userId = (EditText) findViewById(R.id.UserId);
////        cardNo = (EditText) findViewById(R.id.BankCard);
////        idCardType = (EditText) findViewById(R.id.IdType);
////        idCardNo = (EditText) findViewById(R.id.IdNo);
////        userName = (EditText) findViewById(R.id.Name);
////
////    }
//
//    public void Wechat_pay(View view) {
//        Intent intent = new Intent();
////		intent.putExtra("orderNo", "");
//        intent.setClass(this, WXPayEntryActivity.class);
//        startActivity(intent);
//    }
//
//    public void Pay( ) {
//
////        if (mchnt_cd.getText().toString() == null
////                || "".equals(mchnt_cd.getText().toString())) {
////            DialogUtils.showDialog(this, "商户号不能为空");
////            return;
////        } else if (amount.getText().toString() == null
////                || "".equals(amount.getText().toString())
////                || amount.getText().toString().contains(".")) {
////            DialogUtils.showDialog(this, "金额输入有误");
////            return;
////        }
////        temp = EncryptUtils.md5Encrypt(
////                mchnt_orderid+ "|"
////                        + recharge_money + "|"
////                        + mchnt_key).toLowerCase();
////
////        HashMap<String, String> mhashMap = new HashMap<String, String>();
////        mhashMap.put("Rmk1", "");
////        mhashMap.put("Rmk2", "");
////        mhashMap.put("Rmk3", "");
////        mhashMap.put("Sign", temp);
////        mhashMap.put("MchntCd",mchnt_orderid);
////        mhashMap.put("Amt", recharge_money);
////
//        /**********这个获取订单号接口是模拟商户写的，这里只是举例说明，商户需自行获取订单号后按要求传值*************/
//        FyHttpClient.getXMLWithPostUrl("createOrder.pay", mhashMap, new FyHttpInterface() {
//
//            @Override
//            public void requestSuccess(FyHttpResponse resData) {
//                FyXmlNodeData data = resData.getXml();
////                Log.i("wyl", "订单请求成功返回的响应数据：" + data.toString());
//                ordernumber = (String) data.get("OrderId");
//                    /*	Bundle bundle = new Bundle();
//						bundle.putString(FyPay.KEY_ORDER_NO, ordernumber);
////						bundle.putString(FyPay.KEY_MOBILE_NO, "");
//						bundle.putString(FyPay.KEY_MAC, getMac());*/
//
//
//
//
//            }
//
//            @Override
//            public void requestFailed(FyHttpResponse resData) {
//                // TODO Auto-generated method stub
////                        Toast.makeText(MainActivity.this,resData.getXml().getText("RDesc"),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void executeFailed(FyHttpResponse resData) {
//                // TODO Auto-generated method stub
////                        Toast.makeText(MainActivity.this,resData.getText(),Toast.LENGTH_SHORT).show();
////                        Toast.makeText(MainActivity.this,resData.getXml().getText("RDesc"),Toast.LENGTH_SHORT).show();
//
//            }
//
//        });
//
//    }
//
    private String getMac() {
        return EncryptUtils
                .md5Encrypt("" + "|" + ordernumber + "|" + mchnt_key)
                .toLowerCase();
    }
//
//
//
//




    private void showNoPassId() {

        View  peicepop = LayoutInflater.from(ChongzhiActivity.this).inflate(R.layout.nopassid,null);
        ImageView   popup_close2 = peicepop.findViewById(R.id.jisuan_close);
        TextView go = peicepop.findViewById(R.id.go_jiaoyipsd);
        final PopupWindow popupWindows = new PopupWindow(peicepop, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindows.setBackgroundDrawable(new BitmapDrawable());
        popupWindows.setFocusable(true);// 取得焦点
        //点击外部消失
        popupWindows.setOutsideTouchable(true);
        //设置可以点击
        popupWindows.setTouchable(true);
        popupWindows.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);

        popupWindows.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindows.showAtLocation(ChongzhiActivity.this.getWindow().getDecorView(), Gravity.CENTER,0,0);
        // 产生背景变暗效果
        WindowManager.LayoutParams ayoutParams =ChongzhiActivity. this.getWindow()
                .getAttributes();
        ayoutParams.alpha = 0.4f;
        ChongzhiActivity. this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ChongzhiActivity.this.getWindow().setAttributes(ayoutParams);

        popupWindows.update();
        popupWindows.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = ChongzhiActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                ChongzhiActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                ChongzhiActivity.this.getWindow().setAttributes(lp);
            }
        });


        popup_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindows.dismiss();
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChongzhiActivity.this,BindBankCardActivity.class));
            }
        });



    }



}
