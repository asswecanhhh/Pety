package com.chen.android_petlove.tips;

import com.chen.android_petlove.Discuz;
import com.chen.android_petlove.R;
import com.chen.android_petlove.discuz.SendPost;
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
import android.widget.Toast;


public class DialogDiscuz extends AlertDialog {
	private TextView title;
	private TextView content;
	private Context context;
	public DialogDiscuz(Context context) {
		super(context);
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	public void show(){
		final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setContentView(R.layout.dialogdiscuz);

		title = (TextView) window.findViewById(R.id.dialogdiscuz_title);
		content = (TextView) window.findViewById(R.id.dialogdiscuz_content);
		Button bn = (Button) window.findViewById(R.id.bn_dialogdiscuz);


		bn.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String []s = {""};
						SendPost sp = new SendPost(context, title.getText().toString(), content.getText().toString(), s);
						sp.Commit();
						while(sp.getFlag() == 0);
						MyToastBlue.makeText(context, "·¢ËÍ³É¹¦", Toast.LENGTH_SHORT).show();
						alertDialog.dismiss();
					}
				}).start();
				
			}
		});
	}

}
