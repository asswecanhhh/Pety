package com.chen.android_petlove;


import com.chen.android_petlove.signin.*;
import com.chen.android_petlove.tips.MyToast;

import cn.bmob.v3.Bmob;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.FlagToString;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	private EditText user ;
	private EditText password;
	private Button login;
	private Button regist;
	private Button find;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.login);
		Bmob.initialize(getApplicationContext(), "cca0b5cb0ead5fd93f772ee80496041d");
		user = (EditText) findViewById(R.id.login_user);
		password = (EditText) findViewById(R.id.login_password);
		login = (Button) findViewById(R.id.login_button_login);
		regist = (Button) findViewById(R.id.login_button_regist);
		find = (Button) findViewById(R.id.login_button_find);

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PackageManager pm = getPackageManager();
				boolean permission = (PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.INTERNET", "com.chen.android_petlove"));
				if(permission){
					if(user.getText().toString().equals("")) Toast.makeText(v.getContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
					else {
						final SignIn s = new SignIn(v.getContext(), user.getText().toString(), password.getText().toString());
						s.Commit();
						MyToast.makeText(getApplicationContext(), "正在努力登录中请稍等（～￣▽￣～）", 4000).show();
						new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								
								while(s.getFlag() == 0)
								{
									
								}
								Intent intent = new Intent();
								intent.setClass(Login.this, Home.class);
								startActivity(intent);
								overridePendingTransition(R.anim.fade, R.anim.hold);
								finish();
							}
						}).start();
					}					
				} else {
					MyToast.makeText(v.getContext(), "当前网络不可用", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		regist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Login.this, Register.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
			}
		});
		
		find.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Login.this, FindPsw.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
			}
		});

	}
}
