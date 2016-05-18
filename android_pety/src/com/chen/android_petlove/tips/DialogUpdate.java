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
import android.view.ViewDebug.FlagToString;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/*
 * ×Ô¶¨Òådialog
 */
public class DialogUpdate extends AlertDialog {
	private CharSequence title;
	private CharSequence name;
	private CharSequence type;
	private CharSequence weight;
	private CharSequence character;
	private CharSequence age;
	private CharSequence sex;
	private CharSequence note;
	private String[] picId;
	private String objectId;
	private Context context;
	public DialogUpdate(Context context,String objectId,CharSequence title,CharSequence name,CharSequence type,CharSequence weight,CharSequence character,CharSequence age,CharSequence sex,CharSequence note,String[] picId) {
		super(context);
		this.context = context;
		this.objectId = objectId;
		this.title = title;
		this.name = name;
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
		window.setContentView(R.layout.dialogupdate);
		
		TextView title = (TextView) window.findViewById(R.id.updatedialog_title);
		final EditText name = (EditText) window.findViewById(R.id.updatedialog_name);
		final TextView type = (TextView) window.findViewById(R.id.updatedialog_type);
		final EditText weight = (EditText) window.findViewById(R.id.updatedialog_weight);
		final EditText character = (EditText) window.findViewById(R.id.updatedialog_character);
		final EditText age = (EditText) window.findViewById(R.id.updatedialog_age);
		final TextView sex = (TextView) window.findViewById(R.id.updatedialog_sex);
		final EditText note = (EditText) window.findViewById(R.id.updatedialog_note);
		final Button bn = (Button) window.findViewById(R.id.bn_dialogupdate);
		
		
		title.setText(this.title);
		name.setText(this.name);
		type.setText(this.type);
		weight.setText(this.weight);
		character.setText(this.character);
		age.setText(this.age);
		sex.setText(this.sex);
		note.setText(this.note);
		
		bn.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] s = {""};
				final UpdatePet up = new UpdatePet(context, objectId, name.getText().toString(), type.getText().toString(), Double.parseDouble(weight.getText().toString()), character.getText().toString(), Integer.parseInt(age.getText().toString()), sex.getText().toString(), note.getText().toString(), s);
				up.Commit();
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						System.out.println("start");
						while(up.getFlag() == 0);
						alertDialog.dismiss();
					}
				}).start();
				
			}
		});
	}
	
}
