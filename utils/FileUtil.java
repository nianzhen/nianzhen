package com.xiaoqu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
/**
 * @author liyh
 *
 */
public class FileUtil {
	public static boolean save(Object obj, Context context) {
		boolean result = false;
		try {
			FileOutputStream stream = context.openFileOutput(obj.getClass()
					.getName() + ".cs", Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(stream);
			oos.writeObject(obj);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public static boolean save(Object obj, String name, Context context) {
		boolean result = false;
		try {
			FileOutputStream stream = context.openFileOutput(name + ".cs",
					Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(stream);
			oos.writeObject(obj);

			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public static boolean save(Object obj, String name) {
		boolean result = false;
		File sdFile = new File(getSaveVideoPath(), name + ".cs");
		try {
			FileOutputStream fos = new FileOutputStream(sdFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);// 写入
			fos.close(); // 关闭输出流
			result = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static Object get(String fname) {
		Object obj = new Object();
		obj = null;
		File sdFile = new File(getSaveVideoPath(), fname + ".cs");
		try {
			FileInputStream fis = new FileInputStream(sdFile); // 获得输入流
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = ois.readObject();
			ois.close();
		} catch (Exception e) {
			// TODO: handle exception
			obj = null;
		}
		return obj;
	}

	public static Object get(String fname, Context context) {
		Object obj = new Object();
		obj = null;
		try {
			FileInputStream stream = context.openFileInput(fname + ".cs");
			ObjectInputStream ois = new ObjectInputStream(stream);
			obj = ois.readObject();
		} catch (Exception e) {
			// TODO: handle exception
			obj = null;
		}
		return obj;
	}

	public static String getSdPath() {
		String path = "";
		if (hasSdCard()) {
			File pathFile = android.os.Environment
					.getExternalStorageDirectory();
			path = pathFile.getPath();
		}
		return path;

	}

	public static String getRootFilePath() {
		if (hasSdCard()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath()
					+ "/";// filePath:/sdcard/
		} else {
			return Environment.getDataDirectory().getAbsolutePath() + "/data/"; // filePath:
																				// /data/data/
		}
	}

	public static String getSaveFilePath() {
		if (hasSdCard()) {
			return getRootFilePath() + "com.baidu.elearn/caches/";
		} else {
			return getRootFilePath() + "com.baidu.elearn/caches/";
		}
	}

	public static String getSaveVideoPath() {
		if (hasSdCard()) {
			return getRootFilePath() + "E_Learning/";
		} else {
			return getRootFilePath() + "E_Learning/";
		}
	}

	/**
	 * 获得sd卡string
	 * 
	 * @return
	 */
	public static String getSDString() {
		if (hasSdCard()) {
			String sdaString = getSDAvailableSize();
			String sdtString = getSDTotalSize();
			String sdAvalible = formateRate(sdaString);
			String sdTotal = formateRate(sdtString);
			return String.format("SD卡空间剩余%sGB，共%sGB", sdAvalible, sdTotal);
		} else {
			String sdaString = getRomAvailableSize();
			String sdtString = getRomTotalSize();
			String sdAvalible = formateRate(sdaString);
			String sdTotal = formateRate(sdtString);
			return String.format("ROM空间剩余%sGB，共%sGB", sdAvalible, sdTotal);
		}

	}

	/**
	 * 获得SD卡总大小
	 * 
	 * @return
	 */
	public static String getSDTotalSize() {
		if (hasSdCard()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long totalBlocks = stat.getBlockCount();
			return (double) (blockSize * totalBlocks) / (1024 * 1024 * 1024)
					+ "";
		} else {
			return "0";
		}
	}

	/**
	 * 获得sd卡剩余容量，即可用大小
	 * 
	 * @return
	 */
	public static String getSDAvailableSize() {
		if (hasSdCard()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return (double) (blockSize * availableBlocks)
					/ (1024 * 1024 * 1024) + "";
		} else {
			return "0";
		}
	}

	/**
	 * 获得机身内存总大小
	 * 
	 * @return
	 */
	public static String getRomTotalSize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return (double) (blockSize * totalBlocks) / (1024 * 1024 * 1024) + "";
	}

	/**
	 * 获得机身可用内存
	 * 
	 * @return
	 */
	public static String getRomAvailableSize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return (double) (blockSize * availableBlocks) / (1024 * 1024 * 1024)
				+ "";
	}
	
	public static boolean hasSdCard() {
		String sDcString = android.os.Environment.getExternalStorageState();
		boolean has = false;
		if (sDcString.equals(android.os.Environment.MEDIA_MOUNTED)) {
			has = true;
		}
		return has;
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		try {
			File file = new File(sPath);
			// 路径为文件且不为空则进行删除
			if (file.isFile() && file.exists()) {
				file.delete();
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}

	public static boolean isExists(String path) {
		boolean flag=false;
		if(new File(path).exists()){
			flag=true;
		}
		return flag;
	}

	// 格式化 保留两位
	public static String formateRate(String rateStr) {
		if (rateStr.indexOf(".") != -1) {
			// 获取小数点的位置
			int num = 0;
			num = rateStr.indexOf(".");

			// 获取小数点后面的数字 是否有两位 不足两位补足两位
			String dianAfter = rateStr.substring(0, num + 1);
			String afterData = rateStr.replace(dianAfter, "");
			if (afterData.length() < 2) {
				afterData = afterData + "0";
			} else {
				// afterData = afterData;
			}
			return rateStr.substring(0, num) + "." + afterData.substring(0, 2);
		} else {
			if (rateStr == "1") {
				return "100";
			} else {
				return rateStr;
			}
		}
	}

}