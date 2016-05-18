package com.chen.android_petlove.form;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobPointer;

public class Discuz extends BmobObject{
	private String title;
	private User author;
	private String content;
	private String[] picId;
	private int count = 0;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public User getAuthor() {
		return author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getPicId() {
		return picId;
	}
	public void setPicId(String[] picId) {
		this.picId = picId;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "Discuz [title=" + title + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}

