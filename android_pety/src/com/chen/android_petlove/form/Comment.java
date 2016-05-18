package com.chen.android_petlove.form;

import cn.bmob.v3.BmobObject;

public class Comment extends BmobObject{
	private String content;
	private User author;
	private Discuz post;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Discuz getPost() {
		return post;
	}
	public void setPost(Discuz post) {
		this.post = post;
	}
}
