package com.xiaoqu.utils;


public class RegularHelper {
	
	public static final String EMAILADDRESS="\\w+@\\w+\\.(com\\.cn)|\\w+@\\w+\\.(com|cn)";
	// 手机号校验
	public static final String MOBILEPHONENO = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";
	//短信验证码
	public static final String SMSVAILD = "([0-9])\\d{5}$";
	
	// 利用正则表达式判断用户输入的格式
	public static boolean checkString(String str, String strMatches) {
		return str.matches(strMatches);
	}
	
}
