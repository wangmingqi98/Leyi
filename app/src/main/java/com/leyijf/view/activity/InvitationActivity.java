package com.leyijf.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.zxing.Result;
import com.leyijf.R;
import com.leyijf.base.BaseActivity;
import com.leyijf.manager.UserManager;
import com.leyijf.util.ShareUtils;
import com.leyijf.weight.CustomDialog;
import com.leyijf.zxing.DecodeImage;
import com.umeng.socialize.UMShareAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 邀请页面
 * Created by wmq on 2018/4/24.
 */

public class InvitationActivity extends BaseActivity implements View.OnClickListener ,View.OnLongClickListener{
    private ImageView share;
    private ImageView back;
    private WebView webView;
    private ProgressBar progressBar;
    private CustomDialog dialog;
    private ArrayAdapter<String> adapter;
    private boolean isQR;//判断是否为二维码
    private Result result;//二维码解析结果
    private File file;
    private String imgurl;
    //测试
    private String url = "http://cs.leyibank.com/#/myInvitedapp?referee="+ UserManager.getInstance().getLoginUser().getUser_mobile_referee();
    //线上
//    private String url = "http://m.leyibank.com/#/myInvitedapp?referee="+ UserManager.getInstance().getLoginUser().getUser_mobile_referee();
    private String ShareUrl = "http://m.leyibank.com/#/myInvitedRegistration?referee="+ UserManager.getInstance().getLoginUser().getUser_mobile_referee();
    @Override
    protected void initData() {

    }

    @Override
    protected void initId() {
        progressBar  = (ProgressBar) findViewById(R.id.progress);
        share = (ImageView) findViewById(R.id.share);
        back = (ImageView) findViewById(R.id.zhuce_back);
        webView = (WebView) findViewById(R.id.web);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
        //设置渲染优先级
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);//关闭webview中缓存
        // 开启DOM storage API 功能
        webView.getSettings().setDomStorageEnabled(true);
        // 开启database storage API功能
        webView.getSettings().setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + "/webcache2";
        // 设置数据库缓存路径
        webView.getSettings().setDatabasePath(cacheDirPath); // API 19 deprecated
        // 设置Application caches缓存目录
        webView.getSettings().setAppCachePath(cacheDirPath);
        // 开启Application Cache功能
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);//设置缓冲大小，我设的是8M
        webView.loadUrl(url);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress==100){
                    progressBar.setVisibility(View.GONE);
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }
        });
        back.setOnClickListener(this);
        share.setOnClickListener(this);
        webView.setOnLongClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_invitation;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share:
                new ShareUtils(this).show(url);
                break;
            case R.id.zhuce_back:
                finish();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

//    @Override
//    public void onLongClickCallBack(String imgUrl) {
//        url=imgUrl;
//        // 获取到图片地址后做相应的处理
//        MyAsyncTask	mTask = new MyAsyncTask();
//        mTask.execute(imgUrl);
//        showDialog();
//
//    }
    /**
     * 判断是否为二维码
     * param url 图片地址
     * return
     */
    private boolean decodeImage(String sUrl){
        result = DecodeImage.handleQRCodeFormBitmap(getBitmap(sUrl));
        if(result == null){
            isQR = false;
        }else {
            isQR = true;
        }
        return isQR;
    }

    @Override
    public boolean onLongClick(View v) {
        // 长按事件监听（注意：需要实现LongClickCallBack接口并传入对象）
        WebView.HitTestResult htr = webView.getHitTestResult();
        if (htr.getType() == WebView.HitTestResult.IMAGE_TYPE) {//判断被点击的类型为图片
//            mCallBack.onLongClickCallBack(htr.getExtra());
            imgurl=htr.getExtra();
            // 获取到图片地址后做相应的处理
            MyAsyncTask	mTask = new MyAsyncTask();
            mTask.execute(imgurl);
            showDialog();
        }
        return false;
    }

    public class MyAsyncTask extends AsyncTask<String, Void, String> {


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (isQR){
                handler.sendEmptyMessage(0);
            }


        }

        @Override
        protected String doInBackground(String... params) {
            decodeImage(params[0]);
            return null;
        }
    }
    /**
     * 根据地址获取网络图片
     * @param sUrl 图片地址
     * @return
     * @throws IOException
     */
    public Bitmap getBitmap(String sUrl){
        try {
            URL url = new URL(sUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if(conn.getResponseCode() == 200){
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                saveMyBitmap(bitmap,"code");//先把bitmap生成jpg图片
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 显示Dialog
     * param v
     */
    private void  showDialog() {
        initAdapter();
        dialog = new CustomDialog(this) {
            @Override
            public void initViews() {
                // 初始CustomDialog化控件
                ListView mListView = (ListView) findViewById(R.id.lv_dialog);
                mListView.setAdapter(adapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // 点击事件
                        switch (position) {
                            case 0:
                                sendToFriends();//把图片发送给好友
                                closeDialog();
                                break;
                            case 1:
                                saveImageToGallery(InvitationActivity.this);
                                closeDialog();
                                break;
                            case 2:
                                Toast.makeText(InvitationActivity.this, "已收藏", Toast.LENGTH_LONG).show();
                                closeDialog();
                                break;
                            case 3:
                                goIntent();
                                closeDialog();
                                break;
                        }

                    }
                });
            }
        };
        dialog.show();
    }

    /**
     * 初始化数据
     */
    private void initAdapter() {
        adapter = new ArrayAdapter<String>(this, R.layout.item_dialog);
        adapter.add("发送给朋友");
        adapter.add("保存到手机");
        adapter.add("收藏");
    }

    /**
     * 是二维码时，才添加为识别二维码
     */
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                if (isQR){
                    adapter.add("识别图中二维码");
                }
                adapter.notifyDataSetChanged();
            }
        };
    };
    /**
     * 发送给好友
     */
    private void sendToFriends() {
        Intent intent=new Intent(Intent.ACTION_SEND);
        Uri imageUri=  Uri.parse(file.getAbsolutePath());
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, getTitle()));
    }
    /**
     * bitmap 保存为jpg 图片
     * @param mBitmap 图片源
     * @param bitName  图片名
     */
    public void saveMyBitmap(Bitmap mBitmap,String bitName)  {
        file= new File( Environment.getExternalStorageDirectory()+"/"+bitName + ".jpg");
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 先保存到本地再广播到图库
     * */
    public  void saveImageToGallery(Context context) {

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), "code", null);
            // 最后通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"
                    + file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void goIntent(){
        Uri uri = Uri.parse(result.toString());
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

}
