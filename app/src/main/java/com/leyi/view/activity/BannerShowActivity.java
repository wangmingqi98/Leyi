package com.leyi.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.leyi.R;
import com.leyi.base.BaseActivity;
import com.leyi.bean.GetCodeBean;
import com.leyi.bean.RegisterBean;
import com.leyi.bean.UserInfo;
import com.leyi.util.CountDownTimerUtils;
import com.leyi.util.Maches;
import com.leyi.util.OkHttpUtil;
import com.leyi.view.MainActivity;
import com.leyi.weight.vp.ClipViewPager;
import com.leyi.weight.vp.RecyclingPagerAdapter;
import com.leyi.weight.vp.ScalePageTransformer;

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
 * 注册展示页面
 */
public class BannerShowActivity extends BaseActivity implements View.OnClickListener {

    private ImageView zhuce_back;

    private EditText shoujihao_edt;
    private EditText duanxin_edt;
    private Button huoqu_btn;
    private Button jishi_btn;
    private EditText mima_edt;
    private EditText tuijian_edt;
    private CheckBox check_box;
    private TextView liji_zhuce;
    private ClipViewPager viewpager;
    private TextView mashang_lingqu;
    private TubatuAdapter mPagerAdapter;
    private TextView gotouzi1;
    private TextView gotouzi2;
    private TextView gotouzi3;
    private Map map;
    private GetCodeBean getCodeBean;
    private String token;
    private String registerOk;
    private JSONObject jsonObject;


    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        initView();
    }


    @Override
    protected int getContentViewId() {
         withColor();
        return R.layout.activity_banner_show;
    }

    private void initView() {
        map = new HashMap();
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        shoujihao_edt = (EditText) findViewById(R.id.shoujihao_edt);
        duanxin_edt = (EditText) findViewById(R.id.duanxin_edt);
        huoqu_btn = (Button) findViewById(R.id.huoqu_btn);
        jishi_btn = (Button) findViewById(R.id.jishi_btn);
        mima_edt = (EditText) findViewById(R.id.mima_edt);
        tuijian_edt = (EditText) findViewById(R.id.tuijian_edt);
        check_box = (CheckBox) findViewById(R.id.check_box);
        liji_zhuce = (TextView) findViewById(R.id.liji_zhuce);
        viewpager = (ClipViewPager) findViewById(R.id.viewpager);
        mashang_lingqu = (TextView) findViewById(R.id.mashang_lingqu);
        gotouzi1 = (TextView) findViewById(R.id.gotouzi1);
        gotouzi2 = (TextView) findViewById(R.id.gotouzi2);
        gotouzi3 = (TextView) findViewById(R.id.gotouzi3);
        gotouzi1.setOnClickListener(this);
        gotouzi2.setOnClickListener(this);
        gotouzi3.setOnClickListener(this);
        zhuce_back.setOnClickListener(this);
        huoqu_btn.setOnClickListener(this);
        jishi_btn.setOnClickListener(this);
        liji_zhuce.setOnClickListener(this);
        mashang_lingqu.setOnClickListener(this);

        viewpager.setPageTransformer(true, new ScalePageTransformer());
        findViewById(R.id.page_container).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewpager.dispatchTouchEvent(event);
            }
        });

        mPagerAdapter = new TubatuAdapter(BannerShowActivity.this);
        viewpager.setAdapter(mPagerAdapter);
        init();
        viewpager.setCurrentItem(1);



    }

    private void init() {
        List<Integer> list = new ArrayList<>();

        list.add(R.drawable.a2);
        list.add(R.drawable.a1);
        list.add(R.drawable.a3);
         viewpager.setOffscreenPageLimit(3);
        mPagerAdapter.addAll(list);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gotouzi1:
                if(UserInfo.getInstance().getUserState()!=1){
                   Intent intent=new Intent(this,LoginActivity.class);
                    startActivity(intent);

                }else {
                    Intent intent=new Intent(this,MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("page",1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.gotouzi2:
                if(UserInfo.getInstance().getUserState()!=1){
                    Intent intent=new Intent(this,LoginActivity.class);
                    startActivity(intent);

                }else {
                    Intent intent=new Intent(this,MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("page",1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
                break;

            case R.id.gotouzi3:
                if(UserInfo.getInstance().getUserState()!=1){
                    Intent intent=new Intent(this,LoginActivity.class);
                    startActivity(intent);

                }else {
                    Intent intent=new Intent(this,MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("page",1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
                break;




            case R.id.huoqu_btn:
//                获取验证码

                String shpujihao = shoujihao_edt.getText().toString().trim();
                if(!TextUtils.isEmpty(shpujihao)){
                    getCode();
                }else {
                    Toast.makeText(this, "请输入手机号！", Toast.LENGTH_SHORT).show();
                }



                break;
            case R.id.zhuce_back:
               finish();
                break;
            case R.id.liji_zhuce:
//          立即注册

                toRegister();
                break;
            case R.id.mashang_lingqu:
//                马上领取666
                startActivity(new Intent(this,RegisterActivity.class));
                finish();
                break;
        }




    }



    public static class TubatuAdapter extends RecyclingPagerAdapter {

        private final List<Integer> mList;
        private final Context mContext;

        public TubatuAdapter(Context context) {
            mList = new ArrayList<>();
            mContext = context;
        }

        public void addAll(List<Integer> list) {
            mList.addAll(list);
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            ImageView imageView = null;
            if (convertView == null) {
                imageView = new ImageView(mContext);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setTag(position);
            imageView.setImageResource(mList.get(position));
            return imageView;
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }
    private void getCode() {

        if(Maches.isChinaPhoneLegal(shoujihao_edt.getText().toString().trim())){

            map.put("mobile",shoujihao_edt.getText().toString().trim());
            map.put("act","send_register_code");
            OkHttpUtil.getInstance().doHttp(map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("失败",e.toString());
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {



                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            String ss= null;
                            try {
                                ss = response.body().string();
                                Log.e("----",ss);
                                JSONObject jsonObject = new JSONObject(ss);
                                int response_code = jsonObject.getInt("response_code");
                                if(response_code==1){
                                    CountDownTimerUtils countDownTimerUtils=new CountDownTimerUtils(jishi_btn,huoqu_btn,60000,1000);
                                    countDownTimerUtils.start();

                                    Gson gson=new Gson();
                                    getCodeBean = gson.fromJson(ss, GetCodeBean.class);
                                    token = getCodeBean.getObjects().getToken();
                                    UserInfo.getInstance().saveUserToken(token);

                                    Toast.makeText(BannerShowActivity.this, "短信验证已发送"+"，请注意查收", Toast.LENGTH_SHORT).show();



                                }else {

                                    String e0rr = jsonObject.getString("show_err");
                                    Toast.makeText(BannerShowActivity.this, e0rr+"", Toast.LENGTH_SHORT).show();
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });

                }
            });

        }else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BannerShowActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
    private void toRegister() {
////        act=register
//        user_name:用户名
//        user_pwd: 密码
//        user_pwd_confirm:确认密码
//        mobile：手机号码
//        mobile_code：手机验证码
        if(!TextUtils.isEmpty(shoujihao_edt.getText().toString().trim())){
            if(!TextUtils.isEmpty(duanxin_edt.getText().toString().trim())){
                if(!TextUtils.isEmpty(mima_edt.getText().toString().trim())){
                    if(check_box.isChecked()) {


                        Map<String, String> registerMap = new HashMap();
                        registerMap.put("act", "register");
                        registerMap.put("user_name", shoujihao_edt.getText().toString().trim());
                        registerMap.put("user_pwd", mima_edt.getText().toString().trim());
                        registerMap.put("user_pwd_confirm", mima_edt.getText().toString().trim());
                        registerMap.put("mobile", shoujihao_edt.getText().toString().trim());
                        registerMap.put("mobile_code", duanxin_edt.getText().toString().trim());
                        registerMap.put("token", token);
                        Log.e("aaaaa", UserInfo.getInstance().getUserToken() + "");

                        OkHttpUtil.getInstance().doHttp(registerMap, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, final Response response) throws IOException {


                                registerOk = response.body().string();
                                jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(registerOk);
                                    int response_code = jsonObject.getInt("response_code");


                                    if (response_code == 1) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Log.e("registerOk", registerOk);
                                                Gson gson = new Gson();
                                                RegisterBean registerBean = gson.fromJson(registerOk, RegisterBean.class);
                                                UserInfo.getInstance().saveUserPhone(shoujihao_edt.getText().toString().trim());
                                                UserInfo.getInstance().saveUserId(registerBean.getObjects().getUser_info().getUser_id());
                                                UserInfo.getInstance().saveId_passed(registerBean.getObjects().getUser_info().getId_passed());
                                                UserInfo.getInstance().saveHas_paypassword(registerBean.getObjects().getUser_info().getHas_paypassword());
                                                UserInfo.getInstance().saveUserImg(registerBean.getObjects().getUser_info().getUser_img() + "");
                                                UserInfo.getInstance().saveUserName(registerBean.getObjects().getUser_info().getUser_name() + "");
                                                Toast.makeText(BannerShowActivity.this, "注册成功,请登录", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(BannerShowActivity.this, LoginActivity.class));
                                                finish();


                                            }
                                        });

                                    } else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    String show_err = jsonObject.getString("show_err");
                                                    Toast.makeText(BannerShowActivity.this, show_err, Toast.LENGTH_SHORT).show();


                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }


                                            }
                                        });
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        });
                    }else {
                        Toast.makeText(this, "请勾选注册协议与隐私条款", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "请输入短信验证码", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();

        }




    }
}
