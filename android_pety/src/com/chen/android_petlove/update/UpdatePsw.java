package com.chen.android_petlove.update;


import com.chen.android_petlove.form.User;
import com.chen.android_petlove.tips.MyDialog;
import com.chen.android_petlove.tips.MyToast;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.ResetPasswordByEmailListener;
import cn.bmob.v3.listener.UpdateListener;

public class UpdatePsw {
	private String email;
	private Context context;
	private int result = 1;
	private User user = null;
	public UpdatePsw(Context context,String email){
		user = BmobUser.getCurrentUser(context, User.class);
		if (user == null) {
			this.email = email;
		} else {
			this.email = user.getEmail();
		}		
		this.context = context;
	}
	
	public void UpdateByOldPsw(String oldPwd,String newPwd){
		BmobUser.updateCurrentUserPassword(context, oldPwd, newPwd, new UpdateListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(context, "修改密码成功", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "修改密码失败", Toast.LENGTH_SHORT).show();
				System.out.println(arg0);
				System.out.println(arg1);
			}
		});
	}
	public void UpdateByEmail() {
		// TODO Auto-generated method stub
		BmobUser.resetPasswordByEmail(context, email, new ResetPasswordByEmailListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "修改密码,请到" + email,Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "重置密码失败"+arg1,Toast.LENGTH_SHORT).show();
				if(arg0 == 9018){
					//new MyDialog(context, "很抱歉", "你还没有填邮箱呢┴─┴︵╰（‵□′╰）").show();					
				} else if(arg0 == 301){
					//new MyDialog(context, "很抱歉", "你这个确定是邮箱？ （╯‵□′）╯︵┴─┴ ").show();					
				} else if(arg0 == 205){
					//new MyDialog(context, "很抱歉", "这个邮箱还未注册啊╮（￣▽￣）╭").show();										
				}
				
			}
		});
	}
}
