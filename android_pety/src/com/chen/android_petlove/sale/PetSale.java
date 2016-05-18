package com.chen.android_petlove.sale;

import com.chen.android_petlove.form.SalePet;
import com.chen.android_petlove.form.User;
import com.chen.android_petlove.tips.MyDialog;
import com.chen.android_petlove.tips.MyToastBlue;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;


import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class PetSale {
	private SalePet petSale;
	private Context context;
	private int flag = 0;
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public PetSale(Context context,String title, String type, String sex, String[] picID,
			String note, int age, double price) {
		petSale = new SalePet();
		this.context = context;
		petSale.setTitle(title);
		petSale.setType(type);
		petSale.setSex(sex);
		petSale.setPicID(picID);
		petSale.setNote(note);
		petSale.setAuthor(BmobUser.getCurrentUser(context, User.class));
		petSale.setAge(age);
		petSale.setPrice(price);
	}
	public void Commit(){
		// TODO Auto-generated method stub
		petSale.save(context, new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(context, petSale.getTitle() + "上传成功", Toast.LENGTH_SHORT).show();
				setFlag(1);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "上传失败", Toast.LENGTH_SHORT).show();
				//new MyDialog(context, "提示", "上传失败").show();
				
			}
		});
	}
	
}
