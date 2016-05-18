package com.chen.android_petlove.update;

import com.chen.android_petlove.form.Pet;
import com.chen.android_petlove.tips.MyDialog;
import com.chen.android_petlove.tips.MyToast;
import com.chen.android_petlove.tips.MyToastBlue;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;


import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class UpdatePet {
	private Pet pet = new Pet(); 
	private Context context;
	private int flag = 0;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public UpdatePet(Context context,String objectId,String name,String type,Double weight,String character,int age,String sex,String note,String[] picId){
		this.context = context;
		pet.setObjectId(objectId);
		pet.setName(name);
		pet.setType(type);
		pet.setWeight(weight);
		pet.setCharacter(character);
		pet.setAge(age);
		pet.setSex(sex);
		pet.setNote(note);
		pet.setPicId(picId);
	}
	public void Commit() {
		// TODO Auto-generated method stub
		pet.update(context, pet.getObjectId(), new UpdateListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(context, "更新成功", Toast.LENGTH_SHORT).show();
				setFlag(1);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "更新失败" + arg1, Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	
}
