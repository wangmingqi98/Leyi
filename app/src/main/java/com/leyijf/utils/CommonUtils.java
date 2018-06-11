package com.leyijf.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.Message;

import java.util.List;

public class CommonUtils {

	public static String getPackageName(Context c) {
		String str = "";
		try {
			return c.getPackageManager().getPackageInfo(c.getPackageName(), 0).packageName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return str;
		}
	}



	public static boolean isAppOnForeground(Context context, String packageName) {
		if (packageName == null || context == null) {
			return false;
		}
		@SuppressLint("WrongConstant") List<RunningAppProcessInfo> appProcesses = ((ActivityManager) context
				.getSystemService("activity")).getRunningAppProcesses();
		if (appProcesses == null) {
			return false;
		}
		for (RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == 100) {
				return true;
			}
		}
		return false;
	}

	public static boolean isAppOnRunning(Context context, String packageName) {
		if (packageName == null || context == null) {
			return false;
		}
		List<RunningAppProcessInfo> appProcesses = ((ActivityManager) context
				.getSystemService("activity")).getRunningAppProcesses();
		if (appProcesses == null) {
			return false;
		}
		for (RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.processName.equals(packageName)) {
				return true;
			}
		}
		return false;
	}

	public static String getAppVersionName(Context c) {
		try {
			return c.getPackageManager().getPackageInfo(c.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static int getAppVersionCode(Context c) {
		try {
			return c.getPackageManager().getPackageInfo(c.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public static void delivery2Handler(Handler handler, int what, Object obj) {
		if (handler != null) {
			Message msg = handler.obtainMessage(what);
			msg.obj = obj;
			handler.sendMessage(msg);
		}
	}

}
