package com.chingtech.carlogo.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;

public class Utils {

	public static String getDepthByType(int depth) {
		String depthValue = "";
		switch (depth) {
		case 1:
			depthValue = "品牌";
			break;
		case 2:
			depthValue = "子公司";
			break;
		case 3:
			depthValue = "车型";
			break;
		case 4:
			depthValue = "具体车型";
			break;
		default:
			break;
		}

		return depthValue;
	}

	/**
	 * 得到车体颜色
	 * 
	 * @param colors
	 * @return
	 */
	public static List<String> getColors(String colors) {
		List<String> color_list = new ArrayList<String>();

		String a[] = colors.split(",");

		for (int i = 1; i < a.length; i++) {
			if (i > 0) {
				if (a[i].length() < 7 && a[i].length() > 1) {
					if (a[i].length() == 2) {// 如#3 -> #333333
						String s = a[i].substring(1, 2);

						for (int j = 0; j < 5; j++) {
							a[i] += s;
						}
						color_list.add(a[i]);
					}

					if (a[i].length() == 3) {// 如#34 -> #343434
						String s = a[i].substring(1, 3);

						for (int j = 0; j < 2; j++) {
							a[i] += s;
						}

						color_list.add(a[i]);
					}

					if (a[i].length() == 4) {// 如#666 -> #666666
						String s = a[i].substring(1, 4);
						a[i] += s;
						color_list.add(a[i]);
					}
				} else {
					String str = a[i].substring(0, 7);
					color_list.add(str);
				}
			}
		}
		return color_list;
	}

	public static String doubleToString(double d) {
		DecimalFormat df = new DecimalFormat("######0.00");
		return df.format(d);
	}

	private static PackageManager manager;

	/***
	 * 得到程序名称
	 * 
	 * @param context
	 * @return
	 */
	public static String getAppName(Context context) {
		String appName = null;
		try {
			manager = context.getPackageManager();
			appName = context.getApplicationInfo().loadLabel(manager).toString();
			return appName;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取包名
	 * 
	 * @param context
	 * @return
	 */
	public static String getPkName(Context context) {
		try {
			String pkName = context.getPackageName();
			return pkName;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取版本名
	 * 
	 * @param context
	 * @return 当前应用的版本名
	 */
	public static String getVersionName(Context context) {
		try {
			manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(getPkName(context), 0);
			String versionName = info.versionName;
			return versionName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取版本号
	 * 
	 * @param context
	 * @return 当前应用的版本号
	 */
	public static int getVersionCode(Context context) {
		try {
			manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(getPkName(context), 0);
			int versionCode = info.versionCode;
			return versionCode;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	/**
	 * 安装应用
	 * 
	 * @param context
	 * @param fileName
	 */
	public static void update(Context context, String fileName) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	/**
	 * 检测Sdcard是否存在
	 * 
	 * @return
	 */
	public static boolean isExitsSdcard() {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
			return true;
		else
			return false;
	}

	private static File sdDir = null;

	/**
	 * 得到SD卡根目录
	 * 
	 * @return
	 */
	public static String getSDPath() {
		if (isExitsSdcard()) {
			sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
		}
		return sdDir.getAbsolutePath() + "/";
	}
}
