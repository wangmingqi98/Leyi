package com.leyi.util;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.leyi.R;


public class DialogUtils {
	private static Dialog dialog;

	public static class Loading {
		// 记录循环次数，循�?30关闭窗口
		private static int iAllCount;
		private static Handler hander = new Handler();

		/**
		 * 显示Dialog
		 * 
		 * @param text
		 *            要显示的文字
		 * @param context
		 *            当前上下�?
		 * @param resId
		 *            marginTop大小Id
		 * @param resColorId
		 *            背景颜色Id
		 */
		public static void showLoading(String text, Context context, int resId,
				int resColorId) {
			finishLoading();
			dialog = new AlertDialog.Builder(context, R.style.NoBackDialog)
					.create();
			dialog.show();
			dialog.setCancelable(false);
			dialog.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss(DialogInterface dialog) {
					if (hander != null) {
						hander.removeCallbacks(runnable);
					}
				}
			});
			RelativeLayout v = (RelativeLayout) LayoutInflater.from(context)
					.inflate(R.layout.layout_loading, null);
			RelativeLayout rlRoot = (RelativeLayout) v.findViewById(R.id.root);
			rlRoot.setBackgroundColor(resColorId);
			RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) rlRoot
					.getLayoutParams();
			lp.topMargin = context.getResources().getDimensionPixelSize(resId);
			v.setLayoutParams(lp);
//			((GifView) v.findViewById(R.id.dialog_gv))
//					.setGifImage(R.drawable.loadings);
			ImageView imageView=v.findViewById(R.id.dialog_gv);
			Glide.with(context).load(R.drawable.loadings).asGif().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
//			((GifView) v.findViewById(R.id.dialog_gv)).setShowDimension(context
//					.getResources().getDimensionPixelSize(R.dimen.dp60),
//					context.getResources().getDimensionPixelSize(R.dimen.dp60));
			Window window = dialog.getWindow();
			window.setContentView(v);
			android.view.WindowManager.LayoutParams layoutParams = window
					.getAttributes();
			layoutParams.height = android.view.WindowManager.LayoutParams.MATCH_PARENT;
			layoutParams.width = android.view.WindowManager.LayoutParams.MATCH_PARENT;
			window.setAttributes(layoutParams);
			iAllCount = 0;
			hander.postDelayed(runnable, 500);
		}

		public static void finishLoading() {
			if (dialog != null && dialog.isShowing()) {
				dialog.dismiss();
			}
		}

		private static Runnable runnable = new Runnable() {

			@Override
			public void run() {
				iAllCount++;
				if (iAllCount == 30) {
					dialog.dismiss();
				}
				hander.postDelayed(runnable, 500);
			}
		};
	}

}
