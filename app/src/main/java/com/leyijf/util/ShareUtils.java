package com.leyijf.util;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.leyijf.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

/**
 * 分享工具类
 * Created by wmq on 2018/5/24.
 */

public class ShareUtils {
    private static Activity activity;
    public ShareUtils(Activity activity) {
        this.activity = activity;
    }

    /**
     *  title              用乐毅，好收益
        content           乐毅金服，乐享收益————注册立享百元奖励，邀请好友一起来领取吧
        logo image           sharelogo

     * @param
     * @param url
     */
    public static void show( final String url){
        final ShareAction action = new ShareAction(activity);
        final UMImage image = new UMImage(activity, BitmapFactory.decodeResource(activity.getResources(), R.drawable.logo));

         final UMWeb  web = new UMWeb(url);
        web.setTitle("用乐毅，好收益");//标题
        web.setThumb(image);  //缩略图
        web.setDescription("乐毅金服，乐享收益————注册立享百元奖励，邀请好友一起来领取吧");//描述
        action.setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)//分享平台
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (share_media==null){
                            //根据key来区分自定义按钮的类型，并进行对应的操作
                            if (snsPlatform.mKeyword.equals("umeng_sharebutton_custom")){
                                Toast.makeText(activity,"add buttonsuccess",Toast.LENGTH_LONG).show();
                            }

                        }
                        else {//社交平台的分享行为

                                    action.setPlatform(share_media)
                                    .setCallback(new UMShareListener() {
                                        @Override
                                        public void onStart(SHARE_MEDIA share_media) {

                                        }

                                        @Override
                                        public void onResult(SHARE_MEDIA share_media) {

                                        }

                                        @Override
                                        public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                                        }

                                        @Override
                                        public void onCancel(SHARE_MEDIA share_media) {

                                        }
                                    })
                                    .withMedia(web)
                                    .share();
                        }
                    }
                })
                .open();



//      ShareBoardlistener shareBoardlistener = new  ShareBoardlistener() {
//
//        @Override
//        public void onclick(SnsPlatform snsPlatform,SHARE_MEDIA share_media) {
//
//        }
//    };
    }
}
