package com.chen.android_petlove.delete;

import com.chen.android_petlove.form.Comment;
import com.chen.android_petlove.form.Discuz;
import com.chen.android_petlove.form.Pet;
import com.chen.android_petlove.form.SalePet;
import com.chen.android_petlove.form.SaleProduct;
import com.chen.android_petlove.tips.MyDialog;
import com.chen.android_petlove.tips.MyToast;
import com.chen.android_petlove.tips.MyToastBlue;

import android.R;
import android.content.Context;
import android.widget.Toast;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DeleteBatchListener;
import cn.bmob.v3.listener.DeleteListener;


public class Delete {
	private Context context;
	private String flag = "";
	public Delete(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public void deletePet(final String objectId){
		Pet p = new Pet();
		p.setObjectId(objectId);
		p.delete(context, new DeleteListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(context, "É¾³ý³É¹¦", Toast.LENGTH_SHORT).show();
				setFlag(objectId);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "É¾³ýÊ§°Ü", Toast.LENGTH_SHORT).show();
				//new MyDialog(context, "ÌáÊ¾", "É¾³ýÊ§°Ü").show();
				System.out.println(arg1);
			}
		});
	}
	
	public void deleteDiscuz(final String objectId){
		Discuz d = new Discuz();
		d.setObjectId(objectId);
		d.delete(context, new DeleteListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(context, "É¾³ý³É¹¦", Toast.LENGTH_SHORT).show();
				setFlag(objectId);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "É¾³ýÊ§°Ü", Toast.LENGTH_SHORT).show();
				//new MyDialog(context, "ÌáÊ¾", "É¾³ýÊ§°Ü").show();
				System.out.println(arg1);
			}
		});
	}
	
	public void deleteComment(final String objectId){
		Comment c = new Comment();
		c.setObjectId(objectId);
		c.delete(context, new DeleteListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(context, "É¾³ý³É¹¦", Toast.LENGTH_SHORT).show();
				setFlag(objectId);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "É¾³ýÊ§°Ü", Toast.LENGTH_SHORT).show();
				//new MyDialog(context, "ÌáÊ¾", "É¾³ýÊ§°Ü").show();
				System.out.println(arg1);
			}
		});
	}
	public void deleteSalePet(final String objectId){
		SalePet sp = new SalePet();
		sp.setObjectId(objectId);
		sp.delete(context, new DeleteListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(context, "É¾³ý³É¹¦", Toast.LENGTH_SHORT).show();
				setFlag(objectId);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "É¾³ýÊ§°Ü", Toast.LENGTH_SHORT).show();
				//new MyDialog(context, "ÌáÊ¾", "É¾³ýÊ§°Ü").show();
				System.out.println(arg1);
			}
		});
	}
	public void deleteSaleProduct(final String objectId){
		SaleProduct sp = new SaleProduct();
		sp.setObjectId(objectId);
		sp.delete(context, new DeleteListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(context, "É¾³ý³É¹¦", Toast.LENGTH_SHORT).show();
				setFlag(objectId);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "É¾³ýÊ§°Ü", Toast.LENGTH_SHORT).show();
				//new MyDialog(context, "ÌáÊ¾", "É¾³ýÊ§°Ü").show();
				System.out.println(arg1);
			}
		});
	}
	
	public void deletePic(String[] urls){
		BmobFile.deleteBatch(context, urls, new DeleteBatchListener() {
			
			@Override
			public void done(String[] arg0, BmobException arg1) {
				// TODO Auto-generated method stub
				if(arg1 == null) {
					Toast.makeText(context, "É¾³ý³É¹¦", Toast.LENGTH_SHORT).show();
				} else {
					if(arg0 != null) {
						Toast.makeText(context, "É¾³ýÊ§°Ü¸öÊý" +arg0.length + " ,"+arg1.toString(), Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(context, "È«²¿ÎÄ¼þÉ¾³ýÊ§°Ü" + arg1.getErrorCode() + "," + arg1.toString(), Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	}
}
