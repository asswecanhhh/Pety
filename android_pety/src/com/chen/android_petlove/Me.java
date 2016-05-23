package com.chen.android_petlove;

import java.io.IOException;

import cn.bmob.v3.BmobUser;

import com.chen.android_petlove.delete.Delete;
import com.chen.android_petlove.form.User;
import com.chen.android_petlove.http.ImageService;
import com.chen.android_petlove.pic.Upload;
import com.chen.android_petlove.tips.MyToastBlue;
import com.chen.android_petlove.update.UpdateUser;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Me extends Activity {
	private EditText name;
	private EditText age;
	private EditText address;
	private Button update;
	private Button back;
	private User host;
	private ImageButton head;
	private final int IMAGE_OPEN = 1;
	private String[] s = {""};
	private String pathImage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.me);

		name = (EditText) findViewById(R.id.update_name);
		age = (EditText) findViewById(R.id.update_age);
		address = (EditText) findViewById(R.id.update_address);
		update = (Button) findViewById(R.id.update_update);
		back = (Button) findViewById(R.id.update_back);
		head = (ImageButton) findViewById(R.id.me_head);
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				host = new User();

				host = BmobUser.getCurrentUser(getApplicationContext(),User.class);
				while(host == null){

				}
				System.out.println(host.getAddress());
				name.setText(host.getName());
				age.setText(host.getAge()+"");
				address.setText(host.getAddress());
				String[] urlPathContent = host.getPicId();
				try{
					if(urlPathContent == null){
						
					} else {
						byte[] data = ImageService.getImage(urlPathContent[0]);
						Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
						head.setImageBitmap(bitmap);
					}
				} catch(IOException e) {
					Toast.makeText(Me.this, "网络不稳定连接超时", Toast.LENGTH_SHORT).show();
				}
			}
		}).start();
		
		update.setOnClickListener(new OnClickListener() {			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				s[0] = pathImage;
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Delete d = new Delete(Me.this);
						d.deletePic(host.getPicId());
						
						Upload u = new Upload(Me.this, s); 
						u.Commit();
						
						while(u.getUrls() == null);
						s = u.getUrls();
						if(age.getText().toString().isEmpty()) age.setText("16");
						else {
							UpdateUser uu = new UpdateUser(Me.this, name.getText().toString(), address.getText().toString(), Integer.parseInt(age.getText().toString()), s);
							uu.Commit();
						}
					}
				}).start();
				
				
			}
		});
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		head.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyToastBlue.makeText(Me.this, "更换头像", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, IMAGE_OPEN);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//打开图片
		if(resultCode == RESULT_OK && requestCode == IMAGE_OPEN) {
			Uri uri = data.getData();
			//查询选择图片
			if(!TextUtils.isEmpty(uri.getAuthority())) {
				Cursor cursor = getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);

				if(null == cursor){
					return;
				}
				//光标移动至开头 获取图片路径;
				cursor.moveToFirst();
				pathImage = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
			}

		}//end if
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(!TextUtils.isEmpty(pathImage)){
			Bitmap addmp = BitmapFactory.decodeFile(pathImage);
			head.setImageBitmap(addmp);
		}
	}
}
