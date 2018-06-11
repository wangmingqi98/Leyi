package com.leyijf.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Process;

public class ProcessUtil {

	private ProcessUtil() {
	}

	@SuppressLint("WrongConstant")
	public static boolean isCurMainProcess(Context context) {
		int pid = Process.myPid();
		for (RunningAppProcessInfo appProcess : ((ActivityManager) context
				.getSystemService("activity")).getRunningAppProcesses()) {
			if (appProcess.pid == pid && !appProcess.processName.contains(":")) {
				return true;
			}
		}
		return false;
	}

}
