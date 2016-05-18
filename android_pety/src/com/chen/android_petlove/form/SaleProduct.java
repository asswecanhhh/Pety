package com.chen.android_petlove.form;

import cn.bmob.v3.BmobObject;

public class SaleProduct extends BmobObject{
	private String title;
	private String note;
	private String[] picId;
	private double price;
	private User author;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String[] getPicId() {
		return picId;
	}
	public void setPicId(String[] picId) {
		this.picId = picId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
}
