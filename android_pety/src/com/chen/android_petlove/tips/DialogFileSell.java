package com.chen.android_petlove.tips;

import com.chen.android_petlove.R;
import com.chen.android_petlove.update.UpdatePet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.text.AlteredCharSequence;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class DialogFileSell extends AlertDialog {
	private CharSequence title;
	private CharSequence name;
	private CharSequence price;
	private CharSequence seller;
	private CharSequence age;
	private CharSequence sex;
	private CharSequence note;
	private Context context;
	public DialogFileSell(Context context,CharSequence title,CharSequence name,CharSequence price,CharSequence seller,CharSequence age,CharSequence sex,CharSequence note) {
		super(context);
		this.context = context;
		this.title = title;
		this.name = name;
		this.price = price;
		this.seller = seller;
		this.age = age;
		this.sex = sex;
		this.note = note;
		// TODO Auto-generated constructor stub
	}
	public void show(){
		final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setContentView(R.layout.dialogfilesell);
		
		TextView title = (TextView) window.findViewById(R.id.dialog_title);
		TextView name = (TextView) window.findViewById(R.id.dialog_name);
		TextView price = (TextView) window.findViewById(R.id.dialog_price);
		TextView seller = (TextView) window.findViewById(R.id.dialog_seller);
		TextView age = (TextView) window.findViewById(R.id.dialog_age);
		TextView sex = (TextView) window.findViewById(R.id.dialog_sex);
		TextView note = (TextView) window.findViewById(R.id.dialog_note);
		Button bn = (Button) window.findViewById(R.id.bn_dialogfile);
		
		title.setText(this.title);
		name.setText(this.name);
		price.setText(this.price);
		seller.setText(this.seller);
		age.setText(this.age);
		sex.setText(this.sex);
		note.setText(this.note);
		
		bn.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialog.dismiss();
			}
		});
	}
	
}
