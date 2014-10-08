package com.xiaoqu.utils;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

/**
 * 字符串工具类
 * 
 * @author ruijie
 * @date 2013-11-21
 * @version V1.0
 */
public class StringUtil {
	private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年
 
    /**
     * 返回文字描述的日期
     * 
     * @param date
     * @return
     */
    public static String getTimeFormatText(Date date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }
	/**
	 * md5加密
	 * 
	 * @param src
	 * @return
	 */
	public static String md5(String src) {
		try {
			StringBuffer buffer = new StringBuffer();
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(src.getBytes());

			char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'A', 'B', 'C', 'D', 'E', 'F' };
			for (byte b : bytes) {
				buffer.append(chars[(b >> 4) & 0x0F]);
				buffer.append(chars[(b) & 0x0F]);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 首字母转大写
	 * 
	 * @param s
	 * @return
	 */
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toUpperCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(date);
		return str;
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToShortStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		String str = format.format(date);
		return str;
	}
	/**
	 * 
	 * @param mili
	 * @return 把时间戳日期转化成yyyy-MM-dd类型的日期
	 */
	public static String DateToRiQi(String mili) {
		String riqi = "";
		if (ValidateUtil.isValid(mili)) {
			try {
				long times = Long.parseLong(mili);
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd");
				riqi = format.format(times * 1000L);
			} catch (Exception e) {

			}
			
		}
		return riqi;
	}
	/**
	 * 
	 * @param mili
	 * @return 把时间戳日期转化成yyyy-MM-dd-HH-mm-ss类型的日期
	 */
	public static String DateToRiQi2(String mili) {
		String riqi = "";
		if (ValidateUtil.isValid(mili)) {
			try {
				long times = Long.parseLong(mili);
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss");
				riqi = format.format(times * 1000L);
			} catch (Exception e) {

			}
			
		}
		return riqi;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = null;
		try {
			date = format.parse(str);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToShortDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		Date date = null;
		try {
			date = format.parse(str);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @param str
	 * @return date
	 */
	public static long getDateMili() {
		Date date = new Date();
		return date.getTime();
	}
	/**
	 * bitmap转byte
	 * 
	 * @param str
	 * @return date
	 */
	public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}
		
		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
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
	/**
	 * 日期转星座
	 * 
	 * @param date
	 * @return str
	 */
	public static String getConstellation(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(date);

		int month = cal.get(cal.MONTH) + 1;
		int day = cal.get(cal.DATE);
		String s = "魔羯水瓶双鱼牡羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯";
		Integer[] arr = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };
		Integer num = month * 2 - (day < arr[month - 1] ? 2 : 0);
		return s.substring(num, num + 2);
	}
	/**
	 * 判断字符串中是否有特定的字符串
	 * @param str 字符串
	 * @param searchChars 特定的字符串
	 * @return
	 */
	public static boolean containsAny(String str, String searchChars){

		return str.contains(searchChars);
	}
}
