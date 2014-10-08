package com.xiaoqu.utils;

import com.google.gson.annotations.Expose;

public class APP {

	@Expose
	private String name; // 应用名
	@Expose
	private String icon; // 应用icon
	@Expose
	private String url; // 应用下载连接
	@Expose
	private String description; // 应用描述
	
	private String packageName; // 包名
	
	private String versionName; // 版本名

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
