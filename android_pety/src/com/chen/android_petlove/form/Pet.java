package com.chen.android_petlove.form;

import cn.bmob.v3.BmobObject;

public class Pet extends BmobObject{
	private User master;
	private String name;
	private String type;
	private String character;
	private String sex;
	private String note;
	private String[] picId;
	private double weight;
	public String[] getPicId() {
		return picId;
	}
	public void setPicId(String[] picId) {
		this.picId = picId;
	}
	public User getMaster() {
		return master;
	}
	public void setMaster(User master) {
		this.master = master;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight2) {
		this.weight = weight2;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	private int age;
}
