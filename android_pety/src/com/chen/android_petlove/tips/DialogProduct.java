package com.chen.android_petlove.tips;

import com.chen.android_petlove.R;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class DialogProduct extends AlertDialog {
	private CharSequence title;
	private CharSequence price;
	private CharSequence seller;
	private CharSequence note;
	private Context context;
	public DialogProduct(Context context,CharSequence title,CharSequence price,CharSequence seller,CharSequence note) {
		super(context);
		this.context = context;
		this.title = title;
		this.price = price;
		this.seller = seller;
		this.note = note;
		// TODO Auto-generated constructor stub
	}
	public void show(){
		final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setContentView(R.layout.pro_dialog);
		
		TextView title = (TextView) window.findViewById(R.id.prodialog_title);
		TextView price = (TextView) window.findViewById(R.id.prodialog_price);
		TextView seller = (TextView) window.findViewById(R.id.prodialog_seller);
		TextView note = (TextView) window.findViewById(R.id.prodialog_note);
		Button bn = (Button) window.findViewById(R.id.bn_prodialog);
		
		title.setText(this.title);
		price.setText(this.price);
		seller.setText(this.seller);
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
