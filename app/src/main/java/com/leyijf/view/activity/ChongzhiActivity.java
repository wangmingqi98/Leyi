package com.leyijf.view.activity;

import android.annotation.SuppressLint;
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
import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.BankCardBean;
import com.leyijf.bean.CodeBean;
import com.leyijf.bean.FuYoPayResult;
import com.leyijf.bean.FyPayBean;
import com.leyijf.bean.PayBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;
import com.leyijf.util.XmlUtils;
import com.leyijf.view.MainTabActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.HttpUrl;

/**
 * 充值页面
 */
public class ChongzhiActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "ChongzhiActivity";
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
    private String money;
    private HttpUrl url;
    private String temp, ordernumber;
//    private String mchnt_key = "wxdm454dmrrjmu6xgrsvmk8azlc87l43";
//    private String mchnt_cd = "0001000F1118695";//线上
    private String mchnt_key = "d8n0dh23w2yzrnez52ocqb4ckzp7t0fs";//线下
    private String mchnt_cd = "0001000F0358674";//线下

    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
//        FyPay.setDev(true);//此代码是配置jar包为环境配置，true是生产   false测试
//        FyPay.init(ChongzhiActivity.this);
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
                surePay();


                break;



        }
    }

    /**
     * 确认充值接口
     */
    private void surePay() {
        if(chongzhi_edit.getText().toString()!=null){

             money = chongzhi_edit.getText().toString();
        }
        JSONObject object = new JSONObject();
        try {
            object.put("email", UserManager.getInstance().getLoginUser().getUser_mobile_referee());
            object.put("pwd", UserInfo.getInstance().getUserPwd());
            object.put("money", money);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "ininpay: " + jjjjj);
        Log.d(TAG, "ininpay: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().surePay(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<PayBean>(this) {
            @Override
            protected void onHandleSuccess(PayBean payBean) {
                Log.d(TAG, "onHandleSuccess: "+payBean.toString());
                MchantMsgBean bean = new MchantMsgBean();
                bean.setOrderId(payBean.getData().getMchnt_orderid());
                bean.setKey(mchnt_key);
                bean.setMchntCd(mchnt_cd);//设置商户号
                bean.setAmt(payBean.getData().getRecharge_money()+"");
                bean.setUserId(payBean.getData().getUser_id());
                bean.setCardNo(payBean.getData().getUser_bankcard());
                bean.setIDcardType("0");
                bean.setIDNo(payBean.getData().getUser_idno());
                bean.setUserName(payBean.getData().getUser_real_name());
                bean.setBackUrl(payBean.getData().getBack_url());
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
                                Intent intent  = new Intent(ChongzhiActivity.this,MainTabActivity.class);
                                intent.putExtra("index","2");
                                startActivity(intent);
                                finish();
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


        });
    }

    /**
     * 充值---获取富友请求信息
     */
    private void saveIncharge(String user_id,String user_real_name,String user_idno,String user_bankcard,String recharge_money, String mchnt_orderid,String back_url) {
        Observable observable = RetrofitFactory.getInstance().pay(user_id,user_real_name,user_idno,user_bankcard,recharge_money,mchnt_orderid,back_url);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<FyPayBean>(this) {
            @Override
            protected void onHandleSuccess(FyPayBean fyPayBean) {
//                Intent intent = new Intent();
//                intent.setClass(ChongzhiActivity.this,FuYouPayActivity.class);
//                intent.putExtra("api",fyPayBean.getApi());
//                startActivity(intent);



            }


        });

    }


    @Override
    protected void onResume() {
        super.onResume();



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


    private String getMac() {
        return EncryptUtils
                .md5Encrypt("" + "|" + ordernumber + "|" + mchnt_key)
                .toLowerCase();
    }
//
//
//
//




    @SuppressLint("WrongConstant")
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
