package com.chen.android_petlove;

import cn.bmob.v3.BmobUser;

import com.chen.android_petlove.form.User;
import com.chen.android_petlove.logout.Logout;
import com.chen.android_petlove.tips.MyToast;
import com.chen.android_petlove.tips.MyToastBlue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Home extends Activity {
	public static String host = "";
	private ImageButton setting;
	private ImageButton news;
	private ImageButton me;
	private ImageButton mypet;
	private ImageButton salePet;
	private ImageButton saleProduct;
	private ImageButton	discuz;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		news =(ImageButton) findViewById(R.id.home_news);
		me = (ImageButton) findViewById(R.id.home_me);
		mypet = (ImageButton) findViewById(R.id.home_mypet);
		salePet = (ImageButton) findViewById(R.id.home_salepet);
		saleProduct = (ImageButton) findViewById(R.id.home_saleproduct);
		discuz = (ImageButton) findViewById(R.id.home_discuz);
		setting = (ImageButton) findViewById(R.id.home_setting);
		
		news.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyToastBlue.makeText(v.getContext(), "¾´ÇëÆÚ´ý", Toast.LENGTH_SHORT).show();
			}
		});
		me.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Home.this, Me.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
			}
		});
		mypet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Home.this, MyPet.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
			}
		});
		salePet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Home.this, SalePe.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
			}
		});
		saleProduct.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Home.this, SalePro.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
			}
		});
		discuz.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Home.this, Discuz.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
			}
		});
		
		setting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Logout l = new Logout(v.getContext());
				l.Commit();
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while(l.getFlag() == 0);
						finish();
					}
				}).start();
				
			}
		});
	}
}
