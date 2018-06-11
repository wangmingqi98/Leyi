package com.leyijf.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.LoginOutBean;
import com.leyijf.bean.UpLoadImageBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.UserManager;
import com.leyijf.util.Aes128;
import com.leyijf.util.AppUtils;
import com.leyijf.util.SimpleRxGalleryFinal;
import com.leyijf.weight.GlideCircleTransform;
import com.leyijf.weight.SelectPicPopupWindow;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.util.List;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import cn.finalteam.rxgalleryfinal.ui.base.IRadioImageCheckedListener;
import cn.finalteam.rxgalleryfinal.utils.Logger;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 设置页面
 */
public class SetingActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "SetingActivity";
    private ImageView zhuce_back;
    private TextView set_phone;
    private LinearLayout set_phone_lin;
    private TextView set_mybank;
    private LinearLayout set_mybank_lin;
    private TextView set_jiaoy;
    private LinearLayout managementLayout;
    private TextView set_shiming;
    private LinearLayout set_shiming_lin;
    private TextView next_stepshen;
    private ImageView user_img;
    private SelectPicPopupWindow popupWindow;
    private String filePath;
    String tag = "0";


    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        initView();
        if(UserManager.getInstance().isLogin()) {
            if (!TextUtils.isEmpty(UserManager.getInstance().getLoginUser().getUser_img())) {
                filePath = UserManager.getInstance().getLoginUser().getUser_img();
                Glide.with(this).load(filePath).bitmapTransform(new GlideCircleTransform(this)).crossFade(1000).into(user_img);

            }
        }
    }

    @Override
    protected int getContentViewId() {
        //为状态栏着色
        withColor();
        return R.layout.activity_seting;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(UserManager.getInstance().isLogin()) {
            if (!TextUtils.isEmpty(UserManager.getInstance().getLoginUser().getUser_img())) {
                filePath = UserManager.getInstance().getLoginUser().getUser_img();
                Glide.with(this).load(filePath).bitmapTransform(new GlideCircleTransform(this)).crossFade(1000).into(user_img);

            }
        }
       if(UserManager.getInstance().isBindedCard()){
           set_mybank.setText("已绑定");
           set_mybank_lin.setFocusable(false);
       }else {
           set_mybank.setText("未绑定");
           set_mybank_lin.setFocusable(true);
       }
        if(UserManager.getInstance().isCertification()){
           tag = "1";
            set_shiming.setText("已认证");
            set_shiming_lin.setFocusable(false);
        }else {
            tag = "0";
            set_shiming.setText("未认证");
            set_shiming_lin.setFocusable(true);
        }
    set_phone.setText(""+UserManager.getInstance().getLoginUser().getUser_mobile());
    }

    private void initView() {
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        zhuce_back.setOnClickListener(this);
        user_img = (ImageView) findViewById(R.id.user_img);
        user_img.setOnClickListener(this);
        set_phone = (TextView) findViewById(R.id.set_phone);
        set_phone_lin = (LinearLayout) findViewById(R.id.set_phone_lin);
        set_phone_lin.setOnClickListener(this);
        set_mybank = (TextView) findViewById(R.id.set_mybank);
        set_mybank_lin = (LinearLayout) findViewById(R.id.set_mybank_lin);
        set_mybank_lin.setOnClickListener(this);
        set_jiaoy = (TextView) findViewById(R.id.set_jiaoy);
        managementLayout = (LinearLayout) findViewById(R.id.management_layout);
        managementLayout.setOnClickListener(this);
        set_shiming = (TextView) findViewById(R.id.set_shiming);
        set_shiming_lin = (LinearLayout) findViewById(R.id.set_shiming_lin);
        set_shiming_lin.setOnClickListener(this);
        next_stepshen = (TextView) findViewById(R.id.next_stepshen);
        next_stepshen.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.zhuce_back:
                finish();
                break;
            case R.id.next_stepshen:
                logOut();

              break;
            case R.id.set_phone_lin://绑定手机号码
                startActivity(new Intent(this,PhoneMaActivity.class));
                break;
            case R.id.set_mybank_lin://绑定银行卡页面
                startActivity(new Intent(this,BindBankCardActivity.class));
                break;
            case R.id.management_layout://管理密码
                startActivity(new Intent(this,ManagementPasswordActivity.class));
                break;
            case R.id.set_shiming_lin://实名认证
              Intent intent = new Intent(this,TrurhNameActivity.class);
                    intent.putExtra("tag",tag);
                    startActivity(intent);
                break;
            case R.id.user_img://获取用户头像
                    popupWindow = new SelectPicPopupWindow(this,itemOnClick);
                    popupWindow.showAtLocation(SetingActivity.this.findViewById(R.id.setting), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,0,0);
                break;
            default:
                break;

        }
    }

    /**
     * 退出登录
     */
    private void logOut() {
        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String pwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email",email);
            object.put("pwd", pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "logOut: "+jjjjj);
        Log.d(TAG, "logOut: "+object.toString());
        Observable observable = RetrofitFactory.getInstance().loginOut(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<LoginOutBean>(this) {

            @Override
            protected void onHandleSuccess(LoginOutBean loginOutBean) {
                Toast.makeText(SetingActivity.this,loginOutBean.getShow_err(),Toast.LENGTH_LONG).show();
                UserManager.getInstance().logout();
                UserInfo.getInstance().ExeitUser(SetingActivity.this);
                AppUtils.setStates(false);
                startActivity(new Intent(SetingActivity.this,LoginActivity.class));
                finish();
            }
        });

    }



    private  View.OnClickListener itemOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_take_photo:
                    // 调用本地相机
                    if(showCompetence("android.permission.CAMERA")){//进行相机授权
                        if(showCompetence("android.permission.WRITE_EXTERNAL_STORAGE"))
                            SimpleRxGalleryFinal.get().init(new SimpleRxGalleryFinal.RxGalleryFinalCropListener() {
                                @NonNull
                                @Override
                                public Activity getSimpleActivity() {
                                    return SetingActivity.this;
                                }

                                @Override
                                public void onCropCancel() {
                                    Toast.makeText(SetingActivity.this, "裁剪被取消", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCropSuccess(@Nullable Uri uri) {
                                    //图片上传操作与设置头像
                                    filePath = uri.getPath().toString();
                                    Glide.with(SetingActivity.this).load(filePath).bitmapTransform(new GlideCircleTransform(SetingActivity.this)).crossFade(1000).into(user_img);
                                    upLoadImage(filePath);

                                }

                                @Override
                                public void onCropError(@NonNull String errorMessage) {
                                    Toast.makeText(SetingActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                                }
                            }).openCamera();

                    }

                    break;
                case R.id.btn_pick_photo:
                    // 调用本地相册
                    RxGalleryFinalApi.getInstance(SetingActivity.this)
                            .openGalleryRadioImgDefault(new RxBusResultDisposable<ImageRadioResultEvent>() {
                                @Override
                                protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                                    Log.d("单选", "onEvent: "+ imageRadioResultEvent.getResult().getOriginalPath());
//                                     filePath = imageRadioResultEvent.getResult().getOriginalPath();



                                }
                            })
                            .onCrop(true)//是否裁剪
                            .onCropImageResult(new IRadioImageCheckedListener() {
                                @Override
                                public void cropAfter(Object t) {
                                    Logger.i("裁剪完成");
                                    filePath = t.toString();
                                    Glide.with(SetingActivity.this).load(filePath).bitmapTransform(new GlideCircleTransform(SetingActivity.this)).crossFade(1000).into(user_img);
                                    upLoadImage(filePath);

                                }

                                @Override
                                public boolean isActivityFinish() {
                                    Logger.i("返回false不关闭，返回true则为关闭");
                                    return true;
                                }

                            });
                    break;
                default:
                    break;
            }

        }
    };

    /**
     *头像上传
     */

    private void upLoadImage(final String path) {
        String email = UserManager.getInstance().getLoginUser().getUser_mobile_referee();
        String pwd = UserInfo.getInstance().getUserPwd();
        JSONObject object = new JSONObject();
        try {
            object.put("email",email);
            object.put("pwd", pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initVerify: "+jjjjj);
        Log.d(TAG, "initVerify: "+object.toString());
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)//表单类型
                .addFormDataPart("requestData",requestData);

        final File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        builder.addFormDataPart("photo",file.getName(),requestBody);
        List<MultipartBody.Part> partList = builder.build().parts();
        Observable observable = RetrofitFactory.getInstance().upLoadImage(partList);
        observable.compose(compose(this.<UpLoadImageBean>bindToLifecycle())).subscribe(new BaseObserver<UpLoadImageBean>(this) {

            @Override
            protected void onHandleSuccess(UpLoadImageBean upLoadImageBean) {
                filePath = upLoadImageBean.getBase_url().toString();
                Glide.with(SetingActivity.this).load(filePath).bitmapTransform(new GlideCircleTransform(SetingActivity.this)).crossFade(1000).into(user_img);
                Toast.makeText(SetingActivity.this,upLoadImageBean.getShow_err(),Toast.LENGTH_LONG).show();
            }
        });
    }


    /**
     * 调取相机回调函数
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        SimpleRxGalleryFinal.get().onActivityResult(requestCode, resultCode, data);
    }
}
