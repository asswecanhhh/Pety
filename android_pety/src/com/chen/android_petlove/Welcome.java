package com.chen.android_petlove;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;

public class Welcome extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		if(BmobUser.getCurrentUser(this) != null){
			Intent intent = new Intent();
			intent.setClass(Welcome.this, Home.class);
			startActivity(intent);
			overridePendingTransition(R.anim.fade, R.anim.hold);
			finish();
		} else {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setClass(Welcome.this, Login.class);
					startActivity(intent);
					overridePendingTransition(R.anim.fade, R.anim.hold);
					finish();
				}
			}, 1000);
		}
	}
}
