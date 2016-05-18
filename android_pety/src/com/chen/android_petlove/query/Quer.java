package com.chen.android_petlove.query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chen.android_petlove.form.Comment;
import com.chen.android_petlove.form.Discuz;
import com.chen.android_petlove.form.Pet;
import com.chen.android_petlove.form.SalePet;
import com.chen.android_petlove.form.SaleProduct;
import com.chen.android_petlove.form.User;
import com.chen.android_petlove.tips.MyDialog;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;


public class Quer {
	private Context context;
	private int flag = 0;
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Quer(Context context){
		this.context = context;
	}
	public void getAllPost(final ArrayList<Discuz> listDiscuz,int limit, int skip){
		BmobQuery<Discuz> query = new BmobQuery<Discuz>();
		setFlag(0);
		query.addWhereExists("author");
		query.include("author");
		query.setLimit(limit);
		query.order("-updatedAt");
		query.setSkip(skip);
		query.findObjects(context, new FindListener<Discuz>() {
			@Override
			public void onSuccess(List<Discuz> arg0) {
				// TODO Auto-generated method stub
				for(Discuz discuz : arg0) {
					listDiscuz.add(discuz);
				}
			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "查找失败" + arg1, Toast.LENGTH_SHORT).show();
				//new MyDialog(context, "提示", "获取失败").show();
			}			
		});	
	}

	public void getAllComment(String postId, final ArrayList<Comment> listComment,int limit, int skip){
		BmobQuery<Comment> query = new BmobQuery<Comment>();
		setFlag(0);
		query.addWhereEqualTo("post", postId);
		query.include("author,post");
		query.setLimit(limit);
		query.order("createdAt");
		query.setSkip(skip);
		query.findObjects(context, new FindListener<Comment>() {

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "查找失败 "+ arg1 , Toast.LENGTH_SHORT).show();
				//new MyDialog(context, "提示", "获取失败").show();
				
			}

			@Override
			public void onSuccess(List<Comment> arg0) {
				// TODO Auto-generated method stub
				for(Comment comment : arg0){
					listComment.add(comment);
				}
			}
		});
	}

	public void getPet(String masterId,final ArrayList<Pet> listPet){
		BmobQuery<Pet> query = new BmobQuery<Pet>();
		setFlag(0);
		query.addWhereEqualTo("master", masterId);
		query.include("master");
		query.order("-updatedAt");
		query.findObjects(context, new FindListener<Pet>() {
			
			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "查找失败 "+ arg1 , Toast.LENGTH_SHORT).show();
				//new MyDialog(context, "提示", "获取失败").show();
				Message msg = Message.obtain();
			}

			@Override
			public void onSuccess(List<Pet> arg0) {
				// TODO Auto-generated method stub
				for(Pet pet : arg0) {
					listPet.add(pet);
				}
				System.out.println("arg0:"+arg0.size());
				if(arg0.size() == 0) {
					setFlag(-1);
					System.out.println(-1);
				} else {
					setFlag(1);
				}
			}
		});
	}

	public void getUser(String username,final ArrayList<User> listUser){
		if(username == null){
			User user = new User();
			user = BmobUser.getCurrentUser(context,User.class);
			listUser.add(user);
		} else {
			BmobQuery<User> query = new BmobQuery<User>();
			query.addWhereEqualTo("username", username);
			query.findObjects(context, new FindListener<User>() {

				@Override
				public void onError(int arg0, String arg1) {
					// TODO Auto-generated method stub
					//Toast.makeText(context, "查找失败" + arg1, Toast.LENGTH_SHORT).show();
					//new MyDialog(context, "提示", "获取失败").show();
				}

				@Override
				public void onSuccess(List<User> arg0) {
					// TODO Auto-generated method stub
					for(User user : arg0){
						listUser.add(user);
					}
				}
			});
		}
	}
	
	public void getSalePet(final ArrayList<SalePet> listSalePet,int limit,int skip){
		BmobQuery<SalePet> query = new BmobQuery<SalePet>();
		setFlag(0);
		query.addWhereExists("author");
		query.include("author");
		query.setLimit(limit);
		query.order("-updatedAt");
		query.setSkip(skip);
		query.findObjects(context, new FindListener<SalePet>() {
			@Override
			public void onSuccess(List<SalePet> arg0) {
				// TODO Auto-generated method stub
				for(SalePet salePet : arg0) {
					listSalePet.add(salePet);
				}
			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "查找失败" + arg1, Toast.LENGTH_SHORT).show();
				new MyDialog(context, "提示", "获取失败").show();
			}			
		});	
	}
	
	
	public void getSaleProduct(final ArrayList<SaleProduct> listSaleProduct,int limit ,int skip){
		BmobQuery<SaleProduct> query = new BmobQuery<SaleProduct>();
		setFlag(0);
		query.addWhereExists("author");
		query.include("author");
		query.setLimit(limit);
		query.order("-updatedAt");
		query.setSkip(skip);
		query.findObjects(context, new FindListener<SaleProduct>() {
			@Override
			public void onSuccess(List<SaleProduct> arg0) {
				// TODO Auto-generated method stub
				for(SaleProduct saleProduct : arg0) {
					listSaleProduct.add(saleProduct);
				}
			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "查找失败" + arg1, Toast.LENGTH_SHORT).show();
				new MyDialog(context, "提示", "获取失败").show();
			}			
		});	
	}
	
	public void updateDiscuz(final String objectId){
		BmobQuery<Discuz> query = new BmobQuery<Discuz>();
		query.addWhereEqualTo("objectId", objectId);
		query.findObjects(context, new FindListener<Discuz>() {

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				System.out.println("error");
			}

			@Override
			public void onSuccess(List<Discuz> arg0) {
				// TODO Auto-generated method stub
				Discuz d = new Discuz();
				d = (Discuz) arg0.get(0);
				d.setCount(d.getCount()+1);
				d.update(context, objectId, new UpdateListener() {
					
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO Auto-generated method stub
						
					}
				});
				System.out.println(d.getCount());
			}
			
		});
	}
}
	
	
	
