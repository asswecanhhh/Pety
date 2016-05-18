package com.chen.android_petlove.discuz;

import com.chen.android_petlove.comment.Send;
import com.chen.android_petlove.form.Discuz;
import com.chen.android_petlove.form.User;
import com.chen.android_petlove.tips.MyDialog;
import com.chen.android_petlove.tips.MyToastBlue;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;


import android.content.Context;
import android.widget.Toast;

public class SendPost {
	private Discuz discuz;
	private Context context;
	private String content;
	private String[] picId;
	private int flag = 0;
	private String objectId = "null";
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public SendPost(Context context,String title,String content,String[] picId){
		discuz = new Discuz();
		discuz.setAuthor(BmobUser.getCurrentUser(context,User.class));
		discuz.setTitle(title);
		discuz.setContent(content);
		discuz.setPicId(picId);
		this.context = context;
	}
	public void Commit() {
		// TODO Auto-generated method stub
		discuz.save(context, new SaveListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(context, "发送成功", Toast.LENGTH_SHORT).show();
				setFlag(1);
				Send s= new Send(context, discuz.getContent(), discuz.getObjectId());
				s.Commit();
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "发送失败" + arg1, Toast.LENGTH_SHORT).show();
				//new MyDialog(context, "提示", "发送失败").show();

			}
		});
	}

}
