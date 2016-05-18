package com.chen.android_petlove;

import cn.bmob.v3.BmobUser;

import com.chen.android_petlove.form.User;
import com.chen.android_petlove.update.UpdateUser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Me extends Activity {
	private EditText name;
	private EditText age;
	private EditText address;
	private Button update;
	private Button back;
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
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				User u = new User();

				u = BmobUser.getCurrentUser(getApplicationContext(),User.class);
				while(u == null){

				}
				System.out.println(u.getAddress());
				name.setText(u.getName());
				age.setText(u.getAge()+"");
				address.setText(u.getAddress());
			}
		}).start();
		
		update.setOnClickListener(new OnClickListener() {			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] s = {""};
				if(age.getText().toString().isEmpty()) age.setText("16");
				else {
					UpdateUser uu = new UpdateUser(v.getContext(), name.getText().toString(), address.getText().toString(), Integer.parseInt(age.getText().toString()), s);
					uu.Commit();
				}
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
