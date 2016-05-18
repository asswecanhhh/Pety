package com.chen.android_petlove.logout;

import com.chen.android_petlove.form.User;
import com.chen.android_petlove.tips.MyToast;
import com.chen.android_petlove.tips.MyToastBlue;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;


public class Logout {
	private User user;
	private Context context;
	private int flag = 0;
	public Logout(Context context){
		this.context = context;
	}
	public void Commit() {
		// TODO Auto-generated method stub
		BmobUser.logOut(context);
		BmobUser currentUser = BmobUser.getCurrentUser(context);
		Toast.makeText(context, "ÒÑÍË³ö", Toast.LENGTH_SHORT).show();
		setFlag(1);
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
