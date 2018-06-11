package com.leyijf.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.UUID;

public class DeviceUtil {

    public static final String SPECIAL_IMEI = "000000000000000";
    private static final String TAG = DeviceUtil.class.getSimpleName();

    public static boolean isNetworkEnabled(Context context) {
        return isNetEnabled(context) || isWifiEnabled(context);
    }

    public static boolean isNetworkConnected(Context context) {
        return isWifiContected(context) || isNetContected(context);
    }

    public static boolean isWifiEnabled(Context context) {
        boolean enable = false;
        if (((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).isWifiEnabled()) {
            enable = true;
            Log.i(TAG, "isWifiEnabled");
        }
        Log.i(TAG, "isWifiDisabled");
        return enable;
    }

    /**
     * 判断当前网络是否是wifi
     *
     * @param context
     * @return
     */
    public static boolean isWifiContected(Context context) {
        if (((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .getNetworkInfo(1).isConnected()) {
            return true;
        }
        return false;
    }

    public static boolean isNetEnabled(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null || telephonyManager.getNetworkType() == 0) {
            return false;
        }
        Log.i(TAG, "isNetEnabled");
        return true;
    }

    public static boolean isNetContected(Context context) {
        if (((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .getNetworkInfo(0).isConnected()) {
            Log.i(TAG, "isNetContected");
            return true;
        }
        Log.i(TAG, "isNetDisconnected");
        return false;
    }

    public static String getIMEI(Context context) {
        String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        if (imei != null && !SPECIAL_IMEI.equals(imei)) {
            return imei;
        }
        String deviceId = Secure.getString(context.getContentResolver(),
                "android_id");
        if (deviceId != null) {
            return deviceId;
        }
        return DeviceConfig.BLANK;
    }

    public static String getDeviceId(Context context) {
        String macAddress = ((WifiManager) context.getSystemService(Context.WIFI_SERVICE))
                .getConnectionInfo().getMacAddress();
        if (macAddress != null) {
            return macAddress.replace(".", DeviceConfig.BLANK)
                    .replace(":", DeviceConfig.BLANK)
                    .replace("-", DeviceConfig.BLANK)
                    .replace("_", DeviceConfig.BLANK)
                    .replace("+", DeviceConfig.BLANK)
                    .replace("-", DeviceConfig.BLANK)
                    .replace("=", DeviceConfig.BLANK);
        }
        @SuppressLint("MissingPermission") String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE))
                .getDeviceId();
        if (imei != null && !SPECIAL_IMEI.equals(imei)) {
            return imei;
        }
        String deviceId = Secure.getString(context.getContentResolver(),
                "android_id");
        if (deviceId != null) {
            return deviceId.replace("+", DeviceConfig.BLANK)
                    .replace("-", DeviceConfig.BLANK)
                    .replace("=", DeviceConfig.BLANK);
        }
        return null;
    }

    public static String getMyUUID() {
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        return uniqueId;
    }

    public static String getMobileType() {
        return Build.MODEL;
    }

    public static String getMobileName() {
        return Build.BRAND;
    }

    public static String getDeviceProduct() {
        return Build.PRODUCT;
    }

    public static String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static Object[] getScreenInfo(Context context) {
        boolean largeScreen;
        Object[] array = new Object[3];
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        if (dm.densityDpi <= 240) {
            largeScreen = false;
        } else {
            largeScreen = true;
        }
        array[0] = Integer.valueOf(dm.widthPixels);
        array[1] = Integer.valueOf(dm.heightPixels);
        array[2] = Boolean.valueOf(largeScreen);

        return array;
    }

    public static String getCpuInfo() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = {"", ""};  //1-cpu型号  //2-cpu频率
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
        }

        return cpuInfo[0];
    }
    //获取手机可用内存和总内存：

    public static String getRAM(Context context) {
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String result ="";
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        mActivityManager.getMemoryInfo(mi);
        long mAvailMem = mi.availMem;
        result = Formatter.formatFileSize(context, mAvailMem);
        return result;
    }

    //ROM容量显示
    public static String getROM() {
        String path = Environment.getDataDirectory().getPath();
        StatFs statFs = new StatFs(path);
        long blockSize = statFs.getBlockSize();
        long totalBlocks = statFs.getBlockCount();
        long availableBlocks = statFs.getAvailableBlocks();
        long usedBlocks = totalBlocks - availableBlocks;

        //处理存储容量格式
        String[] total = fileSize(totalBlocks * blockSize);
        String[] available = fileSize(availableBlocks * blockSize);
        String[] used = fileSize(usedBlocks * blockSize);

        return total[0]+total[1];
    }
//处理存储容量格式

    private static String[] fileSize(long size) {
        String str = "";
        if (size >= 1024) {
            str = "KB";
            size /= 1024;
            if (size >= 1024) {
                str = "MB";
                size /= 1024;
                if (size >= 1024) {
                    str = "GB";
                    size /= 1024;
                }
            }

        }
        DecimalFormat formatter = new DecimalFormat();
        formatter.setGroupingSize(3);
        String[] result = new String[2];
        result[0] = formatter.format(size);
        result[1] = str;

        return result;
    }

    public static float getDensity(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        return dm.density;
    }

}
