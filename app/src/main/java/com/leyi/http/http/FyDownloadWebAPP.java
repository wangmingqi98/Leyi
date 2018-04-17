package com.leyi.http.http;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask.Status;
import android.os.Handler;
import android.os.Message;

import com.fuiou.mobile.util.AppConfig;
import com.fuiou.mobile.util.AppLibTask;
import com.fuiou.mobile.util.ZipUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class FyDownloadWebAPP {

	private static List<AppLibTask> appLibTask = new ArrayList<AppLibTask>();
	private static Lock lock = new ReentrantLock();
	private static int threadFinishedCount = 0;// 已完成任务的数量
	//下载标示
	public static int REQ;
	public static void startDownload(Context context,String url){
		appLibTask.add(new AppLibTask(url,ZipUtil.getRootPath(context),
				AppConfig.New_WEB_FILE_NM,initAppLibHandler));

		REQ = AppLibTask.THREAD_BEGIN;

		Message message = new Message();
		message.what = AppLibTask.THREAD_BEGIN;
		initAppLibHandler.sendMessage(message);
	}

	/**
	 * 下载web资源包的回调
	 */
	@SuppressLint("HandlerLeak")
	private static Handler initAppLibHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case AppLibTask.THREAD_BEGIN:
				try {
					lock.lock();
					if (appLibTask != null
							&& appLibTask.size() > threadFinishedCount
							&& appLibTask.get(threadFinishedCount).getStatus() == Status.PENDING) {
						// 开始一个新的下载任务
						appLibTask.get(threadFinishedCount).execute();
					} else {
						//所有任务完成
						REQ = AppLibTask.THREAD_FINISHED;
					}
				} finally {
					lock.unlock();
				}
				break;

			case AppLibTask.THREAD_FINISHED:
				try {
					lock.lock();
					if (appLibTask.size() >= threadFinishedCount) {
						// 设置当前下载任务已完成
						threadFinishedCount++;
						// 开始下一个任务
						Message message = new Message();
						message.what = AppLibTask.THREAD_BEGIN;
						sendMessage(message);
					}
					REQ = AppLibTask.THREAD_FINISHED;
				} finally {
					lock.unlock();
				}
				break;
			case AppLibTask.THREAD_ERROR:

				REQ = AppLibTask.THREAD_ERROR;
				break;
			}
		}
	};
	
	/**
	 * 得到下载标示*/
	public static int getREQ() {
		return REQ;
	}


}
