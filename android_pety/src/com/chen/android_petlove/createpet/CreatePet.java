package com.chen.android_petlove.createpet;

import com.chen.android_petlove.form.Pet;
import com.chen.android_petlove.form.User;
import com.chen.android_petlove.tips.MyDialog;
import com.chen.android_petlove.tips.MyToast;
import com.chen.android_petlove.tips.MyToastBlue;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;


import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class CreatePet {
	private Pet pet;
	private int flag = 0;
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	private Context context;
	
	public CreatePet(Context context, String name, String type,
			String character, String note, String sex, double weight, int age ,String[] picId) {
		super();
		pet = new Pet();
		this.context = context;
		pet.setMaster(BmobUser.getCurrentUser(context, User.class));
		pet.setName(name);
		pet.setType(type);
		pet.setCharacter(character);
		pet.setNote(note);
		pet.setSex(sex);
		pet.setWeight(weight);
		pet.setAge(age);
		pet.setPicId(picId);
	}

	public void Commit() {
		// TODO Auto-generated method stub
		pet.save(context, new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				setFlag(1);
				Toast.makeText(context, "成功添加", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "添加失败", Toast.LENGTH_SHORT).show();
				//new MyDialog(context, "提示", "添加失败").show();
			}
		});
	}

}
