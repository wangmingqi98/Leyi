package com.leyijf.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.leyijf.App;
import com.leyijf.R;
import com.leyijf.application.SysApplication;
import com.leyijf.base.BaseActivity;
import com.leyijf.bean.VersionUpdateBean;
import com.leyijf.http.retrofit_rxjava_ok.BaseObserver;
import com.leyijf.http.retrofit_rxjava_ok.RetrofitFactory;
import com.leyijf.manager.DownloadManager;
import com.leyijf.manager.UpdateConfiguration;
import com.leyijf.util.Aes128;
import com.leyijf.util.MyExit;
import com.leyijf.util.VersionUtils;
import com.leyijf.view.fragment.HomeFragment;
import com.leyijf.view.fragment.LiCaiShopFragment;
import com.leyijf.view.fragment.MoreFragment;
import com.leyijf.view.fragment.MyMoneyFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observable;

public class MainTabActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MainTabActivity";
    private MoreFragment myFragment;
    public LiCaiShopFragment shopFragment;
    private MyMoneyFragment newsFragment;
    private HomeFragment homeFragment;
    private String indextab = "0";
    public String mCurrentItem = "0";
    public String mCurrentItemCode = null;
    private final int mRequestCode = 1024;
    private RequestPermissionCallBack mRequestPermissionCallBack;
    private  DownloadManager manager ;
    /**
     * 底部RadioGroup
     */
    RadioGroup rgTools;
    private FrameLayout flHomeContent;
    private MyExit exit;
    private RelativeLayout mains;
    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;
    public int tabIndex;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        fragmentManager = getSupportFragmentManager();
        if (intent.getStringExtra("index") != null) {
            indextab = intent.getStringExtra("index");
            Log.w(TAG, "onNewIntent:  index=" + indextab);
            setTabSelection(Integer.parseInt(indextab));
        }
    }




    /**
     * 根据传入的index参数来设置选中的tab页。
     */
    @SuppressLint("NewApi")
    public void setTabSelection(int index) {
        Log.w(TAG, "板块索引index=" + index);
        tabIndex = index;
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (homeFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.flHomeContent, homeFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(homeFragment);
                }
                RadioButton rbTabHome = rgTools.findViewById(R.id.rbTabHome);
                rbTabHome.setChecked(true);
                break;
            case 1:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                if ( shopFragment== null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    shopFragment = new LiCaiShopFragment();
                    transaction.add(R.id.flHomeContent, shopFragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(shopFragment);
                }
                RadioButton rbTabShop = rgTools.findViewById(R.id.rbTabShop);
                rbTabShop.setChecked(true);
                break;
            case 2:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                if (newsFragment == null) {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    newsFragment = new MyMoneyFragment();
                    transaction.add(R.id.flHomeContent, newsFragment);
                } else {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(newsFragment);
                }
                RadioButton rbTabCar = rgTools.findViewById(R.id.rbTabCar);
                rbTabCar.setChecked(true);
                Log.w(TAG, "mCurrentItem====" + mCurrentItem);
                break;
            case 3:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                if (myFragment == null) {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    myFragment = new MoreFragment();
                    transaction.add(R.id.flHomeContent, myFragment);
                } else {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(myFragment);
                }
                RadioButton rbTabMy = rgTools.findViewById(R.id.rbTabMy);
                rbTabMy.setChecked(true);
                break;
        }
        transaction.commitAllowingStateLoss();
    }


    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */

    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (shopFragment != null) {
            transaction.hide(shopFragment);
        }
        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (myFragment != null) {
            transaction.hide(myFragment);
        }

    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.rbTabHome:
                setTabSelection(0);
                setTabSelection(tabIndex);
                break;
            case R.id.rbTabShop:
                setTabSelection(1);
                setTabSelection(tabIndex);
                break;
            case R.id.rbTabCar:
                setTabSelection(2);
                setTabSelection(tabIndex);
                break;
            case R.id.rbTabMy:
                setTabSelection(3);
                setTabSelection(tabIndex);
                break;
            default:
                break;
        }
    }

    public void setJumpCurrentItem(String mCurrItem) {
        Log.w(TAG, "setJumpCurrentItem:  mCurrentItem=" + mCurrItem);
        this.mCurrentItem = mCurrItem;
    }

    public void setJumpCurrentItemCode(String mCurrItem) {
        Log.w(TAG, "setJumpCurrentItem:  mCurrentItem=" + mCurrItem);
        this.mCurrentItemCode = mCurrItem;
    }

    public void setJumpView(int index) {
        Log.w(TAG, "setJumpView:  index=" + index);
        setTabSelection(index);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
        }
        return false;

    }

    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        App.mainTabActivity  = this;
        SysApplication.getInstance().addActivity(this);
        manager = DownloadManager.getInstance(MainTabActivity.this);
        fragmentManager = getSupportFragmentManager();
        exit = new MyExit();
        mains = (RelativeLayout) findViewById(R.id.mains);
        flHomeContent = (FrameLayout) findViewById(R.id.flHomeContent);
        rgTools = (RadioGroup) findViewById(R.id.tabGroup);
        setTabSelection(0);
        initListener();
        initPerssion();
        initGetNewVersion();

    }

    /**
     * 获取新版本
     *
     */
    private void initGetNewVersion() {
        final int versionCode = Integer.parseInt(VersionUtils.getAppVersionCode(this));
        JSONObject object = new JSONObject();
        try {
            object.put("dev_type", "android");
            object.put("version", VersionUtils.getAppVersionCode(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestData = Aes128.encrypt((object.toString()));
        String jjjjj = Aes128.decrypt(requestData);
        Log.d(TAG, "initGetNewVersion: " + jjjjj);
        Log.d(TAG, "initGetNewVersion: " + object.toString());
        Observable observable = RetrofitFactory.getInstance().versionUpdate(requestData);
        observable.compose(compose(this.bindToLifecycle())).subscribe(new BaseObserver<VersionUpdateBean>(this) {
            @Override
            protected void onHandleSuccess(VersionUpdateBean versionUpdateBean) {
                if (versionUpdateBean.getServerVersion()>versionCode) {
                    if (versionUpdateBean.getForced_upgrade() == 0) {//非强制升级先展示dialog用户选择升级
                        manager.setApkName("leyijinfu.apk")
                                .setApkUrl(versionUpdateBean.getFilename())
                                .setDownloadPath(Environment.getExternalStorageDirectory() + "/AppUpdate")
                                .setSmallIcon(R.drawable.logo)
                                //可设置，可不设置
                                .setConfiguration(new UpdateConfiguration())
                                .setApkDescription(versionUpdateBean.getAndroid_upgrade())
                                .setApkVersionCode(versionUpdateBean.getServerVersion())
                                .download();
                    } else {//强制升级直接下载
                        manager.setApkName("leyijinfu.apk")
                                .setApkUrl(versionUpdateBean.getFilename())
                                .setDownloadPath(Environment.getExternalStorageDirectory() + "/AppUpdate")
                                .setSmallIcon(R.drawable.logo)
                                //可设置，可不设置
                                .setConfiguration(new UpdateConfiguration().setForcedUpgrade(true))
                                .setApkDescription(versionUpdateBean.getAndroid_upgrade())
                                .setApkVersionCode(versionUpdateBean.getServerVersion())
                                .download();
                    }
                }

            }
        });
    }

    private void initPerssion() {
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
    }
    /**
     * 权限请求结果回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasAllGranted = true;
        StringBuilder permissionName = new StringBuilder();
        for (String s : permissions) {
            permissionName = permissionName.append(s + "\r\n");
        }
        switch (requestCode) {
            case mRequestCode: {
                for (int i = 0; i < grantResults.length; ++i) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        hasAllGranted = false;
                        //在用户已经拒绝授权的情况下，如果shouldShowRequestPermissionRationale返回false则
                        // 可以推断出用户选择了“不在提示”选项，在这种情况下需要引导用户至设置页手动授权
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                            new AlertDialog.Builder(MainTabActivity.this).setTitle("PermissionTest")//设置对话框标题
                                    .setMessage("【用户选择了不再提示按钮，或者系统默认不在提示（如MIUI）。" +
                                            "引导用户到应用设置页去手动授权,注意提示用户具体需要哪些权限】" +
                                            "获取相关权限失败:" + permissionName +
                                            "将导致部分功能无法正常使用，需要到设置页面手动授权")//设置显示的内容
                                    .setPositiveButton("去授权", new DialogInterface.OnClickListener() {//添加确定按钮
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                            //TODO Auto-generated method stub
                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                                            intent.setData(uri);
                                            startActivity(intent);
                                            dialog.dismiss();
                                        }
                                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加返回按钮
                                @Override
                                public void onClick(DialogInterface dialog, int which) {//响应事件
                                    // TODO Auto-generated method stub
                                    dialog.dismiss();
                                }
                            }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    mRequestPermissionCallBack.denied();
                                }
                            }).show();//在按键响应事件中显示此对话框
                        } else {
                            //用户拒绝权限请求，但未选中“不再提示”选项
                            mRequestPermissionCallBack.denied();
                        }
                        break;
                    }
                }
                if (hasAllGranted) {
                    mRequestPermissionCallBack.granted();
                }
            }
        }
    }

    private void initListener() {
        rgTools.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rbTabHome:
//                        首页
                        setTabSelection(0);
                        setTabSelection(tabIndex);
                        break;
                    case R.id.rbTabShop:
                        setTabSelection(1);
                        setTabSelection(tabIndex);
                        break;
                    case R.id.rbTabCar:
                        setTabSelection(2);
                        setTabSelection(tabIndex);
                        break;
                    case R.id.rbTabMy:
                        setTabSelection(3);
                        setTabSelection(tabIndex);
                        break;
                    default:
                        break;
                }
            }
        });


    }


}
