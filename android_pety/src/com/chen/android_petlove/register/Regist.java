package com.chen.android_petlove.register;

import com.chen.android_petlove.form.User;
import com.chen.android_petlove.tips.MyDialog;
import com.chen.android_petlove.tips.MyToast;
import com.chen.android_petlove.tips.MyToastBlue;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import cn.bmob.v3.listener.SaveListener;


public class Regist {
	private User user = new User();
	private Context context;
	private String flag = "no";
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Regist(Context context,String userName,String passWord,String name,int age,String email,String address,String mobilePhoneNumber){
		user.setUsername(userName);
		user.setName(name);
		user.setPassword(passWord);
		user.setAddress(address);
		user.setEmail(email);		
		user.setMobilePhoneNumber(mobilePhoneNumber);
		if(age > 200) age = 200;
		else if(age < 0 ) age = 0;
		user.setAge(age);
		this.context = context;
	}
	public void Commit() {
		// TODO Auto-generated method stub
		user.signUp(context, new SaveListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(context, "ע��ɹ�", Toast.LENGTH_SHORT).show();
				setFlag("ok");
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				//Toast.makeText(context, "ע��ʧ��" + arg1 , Toast.LENGTH_SHORT).show();
				if(arg0 == 301){
					if(arg1.startsWith("email")){
						//new MyDialog(context, "�ܱ�Ǹ", " �r(�s_�t)�q :����һ�����õ�����Ӵ��").show();
					} else {
						//new MyDialog(context, "�ܱ�Ǹ", " �r(�s_�t)�q :����һ�����õ��ֻ���Ӵ��").show();
					}
				} else if(arg0 == 202){
					//new MyDialog(context, "�ܱ�Ǹ", " �r(�s_�t)�q :�û����ѱ�ע�ᣡ").show();
				} else if(arg0 == 203){
					//new MyDialog(context, "�ܱ�Ǹ", " �r(�s_�t)�q :�����ѱ�ע�ᣡ").show();
				} else if(arg0 == 209){
					//new MyDialog(context, "�ܱ�Ǹ", " �r(�s_�t)�q :�ֻ��ѱ�ע�ᣡ").show();
				}
			}
		});
	}
}
			
