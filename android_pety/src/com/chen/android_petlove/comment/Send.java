package com.chen.android_petlove.comment;

import com.chen.android_petlove.form.Comment;
import com.chen.android_petlove.form.Discuz;
import com.chen.android_petlove.form.User;
import com.chen.android_petlove.tips.MyDialog;
import com.chen.android_petlove.tips.MyToastBlue;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;


import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Send{
	private Comment comment;
	private Discuz discuz;
	private String content;
	private Context context;
	public Send(Context context,String content,String postId){
		this.context = context;
		this.content = content;
		discuz = new Discuz();
		discuz.setObjectId(postId);
		comment = new Comment();
		comment.setContent(content);
		comment.setAuthor(BmobUser.getCurrentUser(context, User.class));
		comment.setPost(discuz);
	}
	public void Commit() {
		// TODO Auto-generated method stub
		comment.save(context, new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(context, "发送成功", Toast.LENGTH_SHORT).show();
				
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "发送失败", Toast.LENGTH_SHORT).show();
				//new MyDialog(context, "提示", "发送失败").show();
			}
		});
	}

}
