package com.chen.android_petlove;

import com.chen.android_petlove.createpet.CreatePet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class NewPet extends Activity{
	private EditText name;
	private EditText type;
	private EditText weight;
	private EditText character;
	private EditText age;
	private EditText sex;
	private EditText note;
	private Button ok;
	private Button back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.newpet);
		
		name = (EditText) findViewById(R.id.newpet_name);
		type = (EditText) findViewById(R.id.newpet_type);
		weight = (EditText) findViewById(R.id.newpet_weight);
		character = (EditText) findViewById(R.id.newpet_character);
		age = (EditText) findViewById(R.id.newpet_age);
		sex = (EditText) findViewById(R.id.newpet_sex);
		note = (EditText) findViewById(R.id.newpet_note);
		ok = (Button) findViewById(R.id.newpet_ok);
		back = (Button) findViewById(R.id.newpet_back);
		
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
				String characters = character.getText().toString();
				String notes = note.getText().toString();
				String sexs = sex.getText().toString();
				Double weights = Double.parseDouble(weight.getText().toString());
				int ages = Integer.parseInt(age.getText().toString());
				
				final CreatePet cp = new CreatePet(v.getContext(), names, types, characters, notes
						, sexs, weights, ages, s);
				
				cp.Commit();
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while(cp.getFlag() == 0){
						
						};
						finish();
					}
				}).start();
			}
		});
	}

}
