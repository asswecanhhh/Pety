package com.chen.android_petlove.form;

import android.content.Context;
import cn.bmob.v3.BmobUser;

public class User extends BmobUser{
	private int age;
	private String name;
	private String address;
	private String[] picId;
	
	public String[] getPicId() {
		return picId;
	}
	public void setPicId(String[] picId) {
		this.picId = picId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
