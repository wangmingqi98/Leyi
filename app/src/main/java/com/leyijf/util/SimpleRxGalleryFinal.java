package com.leyijf.util;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * by y on 17/07/2017.
 * <p>
 * 使用相机拍照并裁剪
 */

public class SimpleRxGalleryFinal {

    private static final String IMAGE_TYPE = "image/jpeg";
    private static final int TYPE_CAMERA = 1111;

    private RxGalleryFinalCropListener listener = null;

    private Uri imagePath;
    private boolean isCircle=false;


    private static final class SimpleRxGalleryFinalHolder {
        private static final SimpleRxGalleryFinal SIMPLE_RX_GALLERY_FINAL = new SimpleRxGalleryFinal();
    }

    public static SimpleRxGalleryFinal get() {
        return SimpleRxGalleryFinalHolder.SIMPLE_RX_GALLERY_FINAL;
    }


    public SimpleRxGalleryFinal init(RxGalleryFinalCropListener listener) {
        this.listener = listener;
        this.isCircle=isCircle;
        return this;
    }


    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imagePath = Uri.fromFile(getDiskCacheDir());
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imagePath);
        } else {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, imagePath.getPath());
            contentValues.put(MediaStore.Images.Media.MIME_TYPE, IMAGE_TYPE);
            Uri uri = listener.getSimpleActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            //如果是7.0及以上的系统使用FileProvider的方式创建一个Uri
//            Uri uri = FileProvider.getUriForFile(listener.getSimpleActivity(), "com.hm.camerademo.fileprovider", new File(imagePath.getPath()));
//            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//        } else {
//            //7.0以下使用这种方式创建一个Uri
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, imagePath);
//        }

        listener
                .getSimpleActivity()
                .startActivityForResult(intent, TYPE_CAMERA);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(listener!=null){
           switch (resultCode) {
               case Activity.RESULT_CANCELED:
                   listener.onCropCancel();
                   break;
               case UCrop.RESULT_ERROR:
                   if (data != null) {
                       Throwable cropError = UCrop.getError(data);
                       if (cropError != null) {
                           listener.onCropError(cropError.getMessage());
                       } else {
                           listener.onCropError("裁剪出现未知错误");
                       }
                   } else {
                       listener.onCropError("获取相册图片出现错误");
                   }
                   break;

               case Activity.RESULT_OK:
                   switch (requestCode) {
                       case TYPE_CAMERA:
                           notifyImageToCamera(listener.getSimpleActivity(), imagePath);

                        if(isCircle){
                            UCrop.Options options = new UCrop.Options();
                            options.setCircleDimmedLayer(true);//设置圆形裁剪
                            options.setHideBottomControls(true);//是否显示其余操作按钮
                            options.setShowCropFrame(false);//是否显示裁剪框
                            options.setShowCropGrid(false);//是否显示裁剪框网格
//                        uCrop.withOptions(options);
                            UCrop of = UCrop.of(imagePath, Uri.fromFile(getDiskCacheDir()));
                            of.withOptions(options);
                            of.withAspectRatio(1, 1);//设置图片宽高比
                            of.start(listener.getSimpleActivity());
                        }else{
                            notifyImageToCamera(listener.getSimpleActivity(), imagePath);
                            UCrop of = UCrop.of(imagePath, Uri.fromFile(getDiskCacheDir()));
                            of.start(listener.getSimpleActivity());
                        }

                           break;
                       case UCrop.REQUEST_CROP:
                           listener.onCropSuccess(UCrop.getOutput(data));
                           break;
                   }
                   break;
           }
       }
    }

    private File getDiskCacheDir() {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            File externalCacheDir = listener.getSimpleActivity().getExternalCacheDir();
            if (externalCacheDir != null) {
                cachePath = externalCacheDir.getPath();
            } else {
                cachePath = listener.getSimpleActivity().getCacheDir().getPath();
            }
        } else {
            cachePath = listener.getSimpleActivity().getCacheDir().getPath();
        }
        return new File(cachePath, imageName());
    }

    private void notifyImageToCamera(Context context, Uri uri) {
        try {
            File file = new File(uri.getPath());
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), file.getName(), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
    }

    private String imageName() {
        return System.currentTimeMillis() + ".jpg";
    }


    public interface RxGalleryFinalCropListener {

        @NonNull
        Activity getSimpleActivity();


        /**
         * 裁剪被取消
         */
        void onCropCancel();

        /**
         * 裁剪成功
         *
         * @param uri 裁剪的 Uri , 有可能会为Null
         */
        void onCropSuccess(@Nullable Uri uri);


        /**
         * 裁剪失败
         *
         * @param errorMessage 错误信息
         */
        void onCropError(@NonNull String errorMessage);

    }

    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }
}
