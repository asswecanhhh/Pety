package com.chen.android_petlove;

import java.io.Externalizable;

import com.chen.android_petlove.createpet.CreatePet;
import com.chen.android_petlove.pic.Upload;
import com.chen.android_petlove.tips.MyToastBlue;

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

public class NewPet extends Activity{
	private EditText name;
	private EditText type;
	private EditText weight;
	private EditText character;
	private EditText age;
	private EditText sex;
	private EditText note;
	private Button ok;
	private Button back;
	private ImageButton head;
	private String[] s = {""};
	private String pathImage;
	private final int IMAGE_OPEN = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.newpet);
		
		name = (EditText) findViewById(R.id.newpet_name);
		type = (EditText) findViewById(R.id.newpet_type);
		weight = (EditText) findViewById(R.id.newpet_weight);
		character = (EditText) findViewById(R.id.newpet_character);
		age = (EditText) findViewById(R.id.newpet_age);
		sex = (EditText) findViewById(R.id.newpet_sex);
		note = (EditText) findViewById(R.id.newpet_note);
		ok = (Button) findViewById(R.id.newpet_ok);
		back = (Button) findViewById(R.id.newpet_back);
		head = (ImageButton) findViewById(R.id.newpet_head);
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						s[0] = pathImage;
						String names = name.getText().toString();
						String types = type.getText().toString();
						String characters = character.getText().toString();
						String notes = note.getText().toString();
						String sexs = sex.getText().toString();
						Double weights = Double.parseDouble(weight.getText().toString());
						int ages = Integer.parseInt(age.getText().toString());
						
						Upload u = new Upload(NewPet.this, s);
						u.Commit();
						
						while(u.getUrls() == null);
						s = u.getUrls();
						
						CreatePet cp = new CreatePet(NewPet.this, names, types, characters, notes
								, sexs, weights, ages, s);
						cp.Commit();
						
						while(cp.getFlag() == 0){
						
						};
						finish();
					}
				}).start();
			}
		});
		
		head.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyToastBlue.makeText(NewPet.this, "更换头像", Toast.LENGTH_SHORT).show();
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
