package com.chen.android_petlove.tips;

import com.chen.android_petlove.R;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MyToastBlue extends Toast {

	public MyToastBlue(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public static Toast makeText(Context context, CharSequence text,int duration) {
		// TODO Auto-generated constructor stub
		Toast result  = new Toast(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.toast2, null);
		TextView textview = (TextView) layout.findViewById(R.id.tv_toast);
		textview.setText(text);
		result.setView(layout);
		result.setGravity(Gravity.CENTER_VERTICAL, 0, Gravity.BOTTOM);
		result.setDuration(duration);
		return result;
	}

}
