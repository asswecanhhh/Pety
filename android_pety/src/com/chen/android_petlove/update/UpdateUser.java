package com.chen.android_petlove.update;

import com.chen.android_petlove.form.User;
import com.chen.android_petlove.tips.MyDialog;
import com.chen.android_petlove.tips.MyToast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;


import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class UpdateUser {
	private User user; 
	private Context context;
	public UpdateUser(Context context,String name,String address,int age,String[] picId){
		user = BmobUser.getCurrentUser(context, User.class);
		user.setName(name);
		user.setAddress(address);
		user.setAge(age);
		user.setPicId(picId);
		this.context = context;
	}
	public void Commit() {
		// TODO Auto-generated method stub
		user.update(context, user.getObjectId(), new UpdateListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(context, "更新成功", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "更新失败" + arg1, Toast.LENGTH_SHORT).show();
				//new MyDialog(context, "很抱歉", "更新失败").show();
				System.out.println(arg0);
				System.out.println(arg1);
				
			}
		});
	}
	
}
