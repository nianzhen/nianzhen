package com.xiaoqu.utils;

import java.io.File;
import java.io.FileOutputStream;

import com.lidroid.xutils.util.LogUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Environment;
import android.view.Display;
import android.view.View;

public class ShareImageHelper {

	// 获取指定View的截屏，保存到png文件
	private static Bitmap takeScreenShot(Activity activity,View view) {
		// View是你需要截图的View
		// View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
		view.buildDrawingCache();
		// 获取状态栏高度
		Rect frame = new Rect();
		view.getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = Math.abs(frame.top);
		// 获取屏幕长和高
		Display display = activity.getWindowManager().getDefaultDisplay();
		// 去掉标题栏
		Bitmap tempBitmap = Bitmap.createBitmap(view.getDrawingCache(), 0,
				statusBarHeight, display.getWidth(), display.getHeight()
						- statusBarHeight);
		// Cleanup
		view.setDrawingCacheEnabled(false);
		return tempBitmap;
	}

	public static String save2Gallery(Activity activity, View currentView) {
		Bitmap screenshotbitmap = takeScreenShot(activity,currentView);
		return save2Gallery(screenshotbitmap);
	}

	public static String save2Gallery(Bitmap bitmap) {

		String saveFolder = Environment.getExternalStorageDirectory()
				+ "/Pictures";
		String savePath = saveFolder + "/tifen-" + System.currentTimeMillis()
				+ ".png";

		File saveDir = new File(saveFolder);

		LogUtils.d("savePath: " + saveFolder);

		if (!saveDir.exists()) {
			saveDir.mkdirs();
			LogUtils.d("Directory created in gallery.");
		}

		File savedFile = new File(savePath);
		FileOutputStream outputStream;

		try {
			outputStream = new FileOutputStream(savedFile);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		// Refresh gallery
		// String[] paths = { savePath };
		// String[] mediaType = { "image/png" };
		// MediaScannerConnection.scanFile(TifenApp.getAppContext(), paths,
		// mediaType, null);

		return savePath;
	}
}
