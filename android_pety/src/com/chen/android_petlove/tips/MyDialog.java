package com.chen.android_petlove.tips;

import com.chen.android_petlove.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.AlteredCharSequence;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


/*
 * ×Ô¶¨Òådialog
 */
public class MyDialog extends AlertDialog {
	private CharSequence title;
	private CharSequence content;
	private Context context;
	public MyDialog(Context context,CharSequence title,CharSequence content) {
		super(context);
		this.context = context;
		this.title = title;
		this.content = content;
		// TODO Auto-generated constructor stub
	}
	public void show(){
		final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setContentView(R.layout.dialog);
		
		TextView tv_title = (TextView) window.findViewById(R.id.tv_dialog_title);
		TextView tv_content = (TextView) window.findViewById(R.id.tv_dialog_content);
		Button bn = (Button) window.findViewById(R.id.bn_dialog);
		
		tv_title.setText(title);
		tv_content.setText(content);
		bn.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialog.dismiss();
			}
		});
	}
	
}
