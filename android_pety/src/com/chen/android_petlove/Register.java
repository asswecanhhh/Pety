package com.chen.android_petlove;

import cn.bmob.v3.Bmob;

import com.chen.android_petlove.register.Regist;
import com.chen.android_petlove.tips.MyToast;
import com.chen.android_petlove.tips.MyToastBlue;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {
	private Button back;
	private Button regist;
	private EditText account;
	private EditText password;
	private EditText phone;
	private EditText mail;
	private EditText name;
	private EditText age;
	private EditText address;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.register);

		Bmob.initialize(getApplicationContext(), "cca0b5cb0ead5fd93f772ee80496041d");

		back = (Button) findViewById(R.id.register_back);
		regist = (Button) findViewById(R.id.register_regist);
		account = (EditText) findViewById(R.id.register_account);
		password = (EditText) findViewById(R.id.register_password);
		phone = (EditText) findViewById(R.id.register_phone);
		mail = (EditText) findViewById(R.id.register_mail);
		name = (EditText) findViewById(R.id.register_name);
		age = (EditText) findViewById(R.id.register_age);
		address = (EditText) findViewById(R.id.register_address);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		regist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(account.getText().toString().isEmpty()){
					MyToast.makeText(v.getContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
				} else if(password.getText().toString().isEmpty()){
					MyToast.makeText(v.getContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
				} else if(phone.getText().toString().isEmpty()){
					MyToast.makeText(v.getContext(), "手机号不能为空", Toast.LENGTH_SHORT).show();
				} else if(mail.getText().toString().isEmpty()){
					MyToast.makeText(v.getContext(), "邮箱不能为空", Toast.LENGTH_SHORT).show();
				} else if(name.getText().toString().isEmpty()){
					MyToast.makeText(v.getContext(), "昵称不能为空", Toast.LENGTH_SHORT).show();
				} else if(age.getText().toString().isEmpty()){
					age.setText("16");
				} else if(address.getText().toString().isEmpty()){
					address.setText("╮（╯＿╰）╭ 这个人很懒，地址只能由我来填了");
				} else {	
					MyToast.makeText(v.getContext(), "（～￣▽￣～）正在努力注册中", Toast.LENGTH_SHORT).show();
					final Regist r = new Regist(v.getContext(), account.getText().toString(),
							password.getText().toString(), name.getText().toString(), 
							Integer.parseInt(age.getText().toString()), mail.getText().toString(), 
							address.getText().toString(), phone.getText().toString());
					r.Commit();
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while(r.getFlag() == "no");
							finish();
						}
					}).start();
				}
			}
		});
	}
}
