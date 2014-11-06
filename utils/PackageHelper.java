package com.xiaoqu.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;

public class PackageHelper {
	public final static String getChannel(final Context ctx) {
		return getMetaData(ctx, "UMENG_CHANNEL");
	}

	public final static String getVersion(final Context ctx) {
		try {
			return ctx.getPackageManager().getPackageInfo(getPackageName(ctx), 0).versionName;
		} catch (NameNotFoundException e) {
			return "unknown";
		}
	}
	
	public final static Integer getVersionCode(final Context ctx) {
		try {
			return ctx.getPackageManager().getPackageInfo(getPackageName(ctx), 0).versionCode;
		} catch (NameNotFoundException e) {
			return 0;
		}
	}

	public final static String getPackageName(final Context ctx) {
		return ctx.getPackageName();
	}

	final public static String getMetaData(Context ctx, String key) {
		try {
			ApplicationInfo ai = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(),
					PackageManager.GET_META_DATA);
			Bundle bundle = ai.metaData;
			final String value = bundle.getString(key);
			return value;
		} catch (Exception e) {
			Log.e("LazyTool", "Failed to load meta-data: " + e.getMessage());
			return null;
		}
	}

	public static ArrayList<APP> getAPPList(Context appContext) {
		ArrayList<APP> result = new ArrayList<APP>();
		PackageManager packageManager = appContext.getPackageManager();

		List<PackageInfo> packageInfoList = appContext.getPackageManager().getInstalledPackages(0); // 返回已安装的包信息列表
		for (PackageInfo packageInfo : packageInfoList) {
			/*
			 * 判断是否为非系统应用
			 */
			if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
				APP app = new APP();
				app.setName(packageInfo.applicationInfo.loadLabel(packageManager).toString());
				app.setPackageName(packageInfo.packageName);
				app.setVersionName(packageInfo.versionName);
				result.add(app);
			}
		}
		return result;
	}
	
	//判断应用是否安装
	
	public static boolean isInstalled(String appname){
		if(appname==null){
			return false;
		}
		ArrayList<APP> appList=getAPPList(null);
		for (APP app : appList) {
			String _appnameString=app.getName();
			if(_appnameString!=null){
				if(_appnameString.equals(appname)){
					return true;
				}
			}
			
		}
		return false;
	}

}
