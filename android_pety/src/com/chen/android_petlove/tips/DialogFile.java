package com.chen.android_petlove.tips;

import java.io.IOException;

import com.chen.android_petlove.Me;
import com.chen.android_petlove.R;
import com.chen.android_petlove.http.ImageService;
import com.chen.android_petlove.update.UpdatePet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.text.AlteredCharSequence;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class DialogFile extends AlertDialog {
	private CharSequence title;
	private CharSequence type;
	private CharSequence weight;
	private CharSequence character;
	private CharSequence age;
	private CharSequence sex;
	private CharSequence note;
	private String[] picId;
	private Context context;
	private ImageButton head;
	public DialogFile(Context context,CharSequence title,CharSequence type,CharSequence weight,CharSequence character,CharSequence age,CharSequence sex,CharSequence note,String[] picId) {
		super(context);
		this.context = context;
		this.title = title;
		this.type = type;
		this.weight = weight;
		this.character = character;
		this.age = age;
		this.sex = sex;
		this.note = note;
		this.picId = picId;
		// TODO Auto-generated constructor stub
	}
	public void show(){
		final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setContentView(R.layout.dialogfile);
		
		TextView title = (TextView) window.findViewById(R.id.dialog_title);
		TextView type = (TextView) window.findViewById(R.id.dialog_type);
		TextView weight = (TextView) window.findViewById(R.id.dialog_weight);
		TextView character = (TextView) window.findViewById(R.id.dialog_character);
		TextView age = (TextView) window.findViewById(R.id.dialog_age);
		TextView sex = (TextView) window.findViewById(R.id.dialog_sex);
		TextView note = (TextView) window.findViewById(R.id.dialog_note);
		Button bn = (Button) window.findViewById(R.id.bn_dialogfile);
		head = (ImageButton) window.findViewById(R.id.dialog_head);
		
		title.setText(this.title);
		type.setText(this.type);
		weight.setText(this.weight);
		character.setText(this.character);
		age.setText(this.age);
		sex.setText(this.sex);
		note.setText(this.note);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String[] urlPathContent = picId;
				try{
					if(urlPathContent == null ){
						
					} else {
						byte[] data = ImageService.getImage(urlPathContent[0]);
						Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
						head.setImageBitmap(bitmap);
					}
				} catch(IOException e) {
				}
			}
		}).start();
		
		
		bn.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialog.dismiss();
			}
		});
	}
	
}
