package com.xiaoqu.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.lidroid.xutils.util.LogUtils;

public class JsonUtils extends JSONObject {

	public static Object JsonUtils(JSONObject object, Type type) {
		try {
			if (object.getInt("code") == 0) {
				if (object.getString("data") == null) {
					LogUtils.d("成功");
					return null;
				}
				if (ValidateUtil.isValid(object.getJSONArray("data"))) {
					GsonBuilder builder = new GsonBuilder();
					builder.excludeFieldsWithoutExposeAnnotation();
					Gson gson = builder.create();
					JSONArray jsonArray = object.getJSONArray("data");
					ArrayList<Object> list = gson.fromJson(
							jsonArray.toString(), type);
					return list;
				}
				if (ValidateUtil.isValid(object.getJSONObject("data"))) {
					return object.getJSONObject("data");
				}
				if (ValidateUtil.isValid(object.getString("data"))) {
					return object.getString("data");
				}
				if (ValidateUtil.isValid(object.getInt("data"))) {
					return object.getInt("data");
				}
			} else {
				LogUtils.d(object.toString());
				LogUtils.d(object.getString("massage"));
			}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtils.d(e.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtils.d(e.toString());
		}
		LogUtils.d("null");
		return null;

	}
}
