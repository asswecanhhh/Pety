package com.chen.android_petlove;

import com.chen.android_petlove.createpet.CreatePet;
import com.chen.android_petlove.form.Pet;
import com.chen.android_petlove.form.SaleProduct;
import com.chen.android_petlove.sale.PetSale;
import com.chen.android_petlove.sale.ProductSale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class SalePetAdd extends Activity{
	private EditText name;
	private EditText type;
	private EditText age;
	private EditText sex;
	private EditText price;
	private EditText note;
	private Button ok;
	private Button back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.salepetadd);
		
		name = (EditText) findViewById(R.id.salepetadd_name);
		type = (EditText) findViewById(R.id.salepetadd_type);
		age = (EditText) findViewById(R.id.salepetadd_age);
		sex = (EditText) findViewById(R.id.salepetadd_sex);
		price = (EditText) findViewById(R.id.salepetadd_price);
		note = (EditText) findViewById(R.id.salepetadd_note);
		ok = (Button) findViewById(R.id.salepetadd_ok);
		back = (Button) findViewById(R.id.salepetadd_back);
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] s = {""};
				String names = name.getText().toString();
				String types = type.getText().toString(); 
				int ages = Integer.parseInt(age.getText().toString());
				Double prices = Double.parseDouble(price.getText().toString());
				String notes = note.getText().toString();
				String sexs = sex.getText().toString();
				
				final PetSale ps = new PetSale(v.getContext(), names, types, sexs, s, notes, ages, prices);
				ps.Commit();
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while(ps.getFlag() == 0){
						
						};
						finish();
					}
				}).start();
			}
		});
	}

}
