package com.chen.android_petlove;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import cn.bmob.v3.BmobUser;

import com.chen.android_petlove.adapter.MyPetAdapter;
import com.chen.android_petlove.form.Pet;
import com.chen.android_petlove.form.User;
import com.chen.android_petlove.query.Quer;
import com.chen.android_petlove.tips.MyToastBlue;

import android.app.Activity;
import android.app.DownloadManager.Query;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyPet extends Activity {
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what ==  0x123){
				loading.setVisibility(View.GONE);
				myAdapter = new MyPetAdapter(MyPet.this,listItemsmain);
				listView.setAdapter(myAdapter);
			} else if(msg.what == 0x321){
				System.out.println("handler");
				bar.setVisibility(View.GONE);
				text.setTextSize(18);
				text.setPadding(0, 20, 0, 0);
				text.setText("很抱歉，你还没有宠物呢");
			}
		}
	};
	private LinearLayout loading;
	private Button back;
	private Button add;
	private User host;
	private ProgressBar bar;
	private TextView text;
	private ArrayList<Pet> petList = new ArrayList<Pet>();
	private List<Map<String, Object>> listItemsmain = new ArrayList<Map<String,Object>>();
	private ListView listView;
	private Integer[] image = {R.drawable.ic_launcher};
	private MyPetAdapter myAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mypet);
		
		MyToastBlue.makeText(this, "正在很努力的加载数据中..", Toast.LENGTH_LONG).show();
		
		
		back = (Button) findViewById(R.id.mypet_back);
		add =(Button) findViewById(R.id.mypet_add);
		listView = (ListView) findViewById(R.id.mypet_list);
		loading = (LinearLayout) findViewById(R.id.mypet_loading);
		text = (TextView) findViewById(R.id.login_text);
		bar = (ProgressBar) findViewById(R.id.login_bar);
		
		
		host = BmobUser.getCurrentUser(this,User.class);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(host == null);
				Quer q = new Quer(getApplicationContext());
				q.getPet(host.getObjectId(), petList);
				Message msg = Message.obtain();
				while(petList.size() == 0){
					if(q.getFlag() == -1){
						msg.what = 0x321;
						System.out.println("msg 0x321");
						handler.sendMessage(msg);
						break;
					}
				};
				List<Map<String, Object>> listItems = new ArrayList<Map<String,Object>>();
				for(int i = 0; i < petList.size();i++){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("image", image[0]);
					map.put("objectId", petList.get(i).getObjectId());
					map.put("name", petList.get(i).getName());
					map.put("type", petList.get(i).getType());
					map.put("id", petList.get(i).getObjectId());
					map.put("weight", String.valueOf(petList.get(i).getWeight()));
					map.put("character", petList.get(i).getCharacter());
					map.put("age", String.valueOf(petList.get(i).getAge()));
					map.put("sex", petList.get(i).getSex());
					map.put("note", petList.get(i).getNote());
					map.put("picId", petList.get(i).getPicId());
					listItems.add(map);
				}
				listItemsmain = listItems;
				
				while(listItemsmain.size() == 0){
				}
				
				msg.what = 0x123;
				handler.sendMessage(msg);
				
			}
		}).start();
		
		
		

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MyPet.this, NewPet.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
			}
		});
	}
}
