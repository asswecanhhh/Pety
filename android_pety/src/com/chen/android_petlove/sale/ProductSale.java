package com.chen.android_petlove.sale;

import java.util.ArrayList;
import java.util.Map;

import com.chen.android_petlove.form.SaleProduct;
import com.chen.android_petlove.form.User;
import com.chen.android_petlove.tips.MyDialog;
import com.chen.android_petlove.tips.MyToastBlue;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;


public class ProductSale {
	private SaleProduct saleProduct;
	private Context context;
	private int flag = 0;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public ProductSale(Context context,String title,String note,String[] picId,double price){
		this.context = context;
		saleProduct = new SaleProduct();
		saleProduct.setTitle(title);
		saleProduct.setNote(note);
		saleProduct.setPicId(picId);
		saleProduct.setAuthor(BmobUser.getCurrentUser(context, User.class));
		saleProduct.setPrice(price);
		//saleProduct.setSaler(BmobUser.getCurrentUser(context, User.class));
	}
	public void Commit() {
		// TODO Auto-generated method stub
		saleProduct.save(context, new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(context, saleProduct.getTitle() + "上传成功", Toast.LENGTH_SHORT).show();
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
