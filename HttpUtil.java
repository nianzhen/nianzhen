package com.xiaoqu.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HttpUtil {
	//服务器地址
	private static String URL="";
	private static     AsyncHttpClient client =new AsyncHttpClient();    //实例话对象
    static
    {
        client.setTimeout(11000);   //设置链接超时，如果不设置，默认为10s
    }
   
    /*1注册
     * 入参：
	school，String  学校名称
	grade，int，  入学年份
	phone，bigint，  电话号码
	password，string，  密码
	headIcon，string，   头像
	sex，   int，    性别
	nickName， string，  昵称
	birth，string，   生日
	出参：
	code：0,message：””,data：{}
	code:为数字0.1.2.3.
	message:信息  
	data ：uid
     */
	@SuppressWarnings("unused")
	private static void userRegister(String url,String school,String grade,String phone,String password,
    		String headIcon,String sex,String nickName,String birth,JsonHttpResponseHandler jsonHttpResponseHandler)		
    {
    	RequestParams qParams=new RequestParams();
    	qParams.put("school", school);
    	qParams.put("grade", grade);
    	qParams.put("phone", phone);
    	qParams.put("password", password);
    	qParams.put("headIcon", headIcon);
    	qParams.put("sex", sex);
    	qParams.put("nickName", nickName);
    	qParams.put("birth", birth);
    	client.post(URL+url, qParams,jsonHttpResponseHandler);
    }
	/*
	 * 2.登陆
	get   userLogin
	入参
	phone
	password
	出参
	code：0,message：””,data：
	[{
	uid 用户id
	sname，String  学校名称
	sid，int 学校id
	grade，int，  入学年份
	phone，bigint，  电话号码
	headIcon，string，   头像
	sex，   int，    性别
	nickName， string，  昵称
	birth，string，   生日
	integral,   int  ,   活跃度
	},……….]
	 */
    @SuppressWarnings("unused")
	private static void  userLogin(String url,String phone,String password,JsonHttpResponseHandler jsonHttpResponseHandler) 
    {
		RequestParams qParams=new RequestParams();
		qParams.put("phone", phone);
		qParams.put("password", password);
		client.get(URL+url, qParams,jsonHttpResponseHandler);
	}
    
    /*
     * 3.获取关注小组
		get     userFavorite
		入参
		uid，用户id
		出参
		Code：0,message：””,data：[{
		gid：int，小组id
		icon：string，小组头像地址
		}

     */
    
    @SuppressWarnings("unused")
	private static void userFavorite(String url,String uid,JsonHttpResponseHandler jsonHttpResponseHandler)
    {
    	RequestParams qParams=new RequestParams();
    	qParams.put("uid", uid);
    	client.get(URL+url, qParams,jsonHttpResponseHandler);
    }
    /*
     * 4.获取我的相册列表
		get  getUpicurl
		入参
		uid  用户id
		,num  获取的数量
		出参
		Code：0,message：””,data：{}
		picurl  图片地址
		pictxt  图片说明
		picdate 时间时间

     */
    @SuppressWarnings("unused")
	private static void getUpicurl(String url,String uid,String num,JsonHttpResponseHandler jsonHttpResponseHandler)
    {
    	RequestParams qParams=new RequestParams();
    	qParams.put("uid", uid);
    	qParams.put("num", num);
    	client.get(URL+url, qParams,jsonHttpResponseHandler);
    } 
    /*
     * 5.全部状态
		get   getTwitter
		入参
		fromid，从第几条开始
		max:    最大获取几条
		sid 学校id  
		uid用户id
		出参：
		code：0,message：””,data：{}
			uid  发状态人的id
			username  发状态的名字
			headicon 头像
			time   时间
			gid  小组id
			game  小组名字
			content   状态内容
			pics  状态图片（jsonarry）
			zan 赞的数目
			iszan 0是赞 1是不赞
			comments 评论的数目
     */
    @SuppressWarnings("unused")
 	private static void getTwitter(String url,String fromid,String max,String sid,String uid,JsonHttpResponseHandler jsonHttpResponseHandler)
     {
     	RequestParams qParams=new RequestParams();
     	qParams.put("uid", fromid);
     	qParams.put("max", max);
     	qParams.put("sid", sid);
     	qParams.put("uid", uid);
     	client.get(URL+url, qParams,jsonHttpResponseHandler);
     }
}
