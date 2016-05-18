package com.chen.android_petlove;

import cn.bmob.v3.Bmob;

import com.chen.android_petlove.logout.Logout;
import com.chen.android_petlove.update.UpdatePsw;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class FindPsw extends Activity {
	private EditText mail;
	private Button find;
	private Button back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.findpsw);
		Bmob.initialize(getApplicationContext(), "cca0b5cb0ead5fd93f772ee80496041d");
		
		mail = (EditText) findViewById(R.id.find_mail);
		find = (Button) findViewById(R.id.find_ok);
		back = (Button) findViewById(R.id.find_back);
		
		find.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UpdatePsw up = new UpdatePsw(v.getContext(), mail.getText().toString());
				up.UpdateByEmail();
			}
		});
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
