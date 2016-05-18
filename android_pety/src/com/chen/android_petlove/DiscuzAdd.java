package com.chen.android_petlove;

import com.chen.android_petlove.comment.Send;
import com.chen.android_petlove.discuz.SendPost;
import com.chen.android_petlove.tips.MyToastBlue;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DiscuzAdd extends Activity {
	private Button bn;
	private EditText title;
	private EditText content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.discuzadd);
		bn = (Button) findViewById(R.id.bn_discuzadd);
		content = (EditText) findViewById(R.id.discuzadd_content);
		title = (EditText) findViewById(R.id.discuzadd_title);
		
		bn.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String []s = {""};
				final SendPost sp = new SendPost(DiscuzAdd.this, title.getText().toString(), content.getText().toString(), s);
				sp.Commit();
				MyToastBlue.makeText(v.getContext(), "ÕýÔÚ·¢ËÍ", Toast.LENGTH_SHORT).show();
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						while(sp.getFlag() == 0);
						
						finish();
					}
				}).start();
				
			}
			
		});
	}
}
