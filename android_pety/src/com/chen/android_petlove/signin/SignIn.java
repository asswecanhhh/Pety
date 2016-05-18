package com.chen.android_petlove.signin;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

import com.chen.android_petlove.*;
import com.chen.android_petlove.form.User;
import com.chen.android_petlove.tips.MyDialog;
import com.chen.android_petlove.tips.MyToast;
import com.chen.android_petlove.tips.MyToastBlue;

public class SignIn {
	private String account;
	private String passWord;
	private Context context;
	private	int flag = 0;
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getFlag() {
		return flag;
	}
	//private PassWord;
	public SignIn(Context context,String account,String passWord){
		this.context = context;
		this.account = account;
		this.passWord = passWord;
	}
	public void Commit() {
		// TODO Auto-generated method stub
		BmobUser.loginByAccount(context, account, passWord, new LogInListener<User>() {

			@Override
			public void done(User arg0, BmobException arg1) {
				// TODO Auto-generated method stub
				if(arg0 != null){
					Toast.makeText(context, "µÇÂ¼³É¹¦£¨£¾¨Œ£¼£©/", Toast.LENGTH_SHORT).show();
					setFlag(1);
				} else {
					System.out.println(arg1.toString());
					//Toast.makeText(context, "µÇÂ¼Ê§°Ü", Toast.LENGTH_SHORT).show();
					if(arg1.toString().endsWith("network!")){
						Toast.makeText(context, "ÍøÂç´íÎó£¬Çë¼ì²éÊÖ»úµÄÍøÂçÉèÖÃ",Toast.LENGTH_LONG).show();

					}else{
						Toast.makeText(context, "ÕËºÅ»òÃÜÂë´íÎó",Toast.LENGTH_LONG).show();
					}
				}
			}
		});
	}

}
