package com.chen.android_petlove;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobUser;

import com.chen.android_petlove.adapter.SalePeAdapter;
import com.chen.android_petlove.form.SalePet;
import com.chen.android_petlove.form.User;
import com.chen.android_petlove.query.Quer;
import com.chen.android_petlove.tips.MyToastBlue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class SalePe extends Activity {
	private Button back;
	private Button ok;
	private User host;
	private ListView list;
	private Integer[] image = {R.drawable.ic_launcher};
	private ArrayList<SalePet> listPe = new ArrayList<SalePet>();
	private List<Map<String, Object>> listItemsMain = new ArrayList<Map<String,Object>>();
	private LinearLayout loading;
	private SalePeAdapter salePeAdapter;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what == 0x234){
				loading.setVisibility(View.GONE);
				salePeAdapter = new SalePeAdapter(SalePe.this,listItemsMain);
				list.setAdapter(salePeAdapter);
				salePeAdapter.notifyDataSetChanged();
			} 
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.salepet);

		back = (Button) findViewById(R.id.salepe_back);
		ok = (Button) findViewById(R.id.salepe_add);
		list = (ListView) findViewById(R.id.salepe_list);
		loading = (LinearLayout) findViewById(R.id.salepe_loading);

		host = BmobUser.getCurrentUser(this,User.class);
		
		MyToastBlue.makeText(this, "正在很努力的加载数据中..", Toast.LENGTH_LONG).show();


		

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
				Intent intent = new Intent();
				intent.setClass(SalePe.this,SalePetAdd.class);
				startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(host == null);
				Quer q = new Quer(getApplicationContext());
				listPe.clear();
				q.getSalePet(listPe, 20, 0);
				while(listPe.size() == 0);
				List<Map<String, Object>> listItems = new ArrayList<Map<String,Object>>();
				for(int i = 0; i < listPe.size();i++){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("image", image[0]);
					map.put("objectId", listPe.get(i).getObjectId());
					map.put("title", listPe.get(i).getTitle());
					map.put("type", listPe.get(i).getType());
					map.put("price", String.valueOf(listPe.get(i).getPrice()));
					map.put("age", String.valueOf(listPe.get(i).getAge()));
					map.put("sex", String.valueOf(listPe.get(i).getSex()));
					map.put("seller", listPe.get(i).getAuthor().getName());
					map.put("sellerId", String.valueOf(listPe.get(i).getAuthor().getObjectId()));
					map.put("note", listPe.get(i).getNote());
					map.put("hostId", host.getObjectId());
					listItems.add(map);
				}
				listItemsMain = listItems;
				while(listItemsMain.size() == 0){
					
				}
				Message msg = Message.obtain();
				msg.what = 0x234;
				handler.sendMessage(msg);

			}
		}).start();
	}
}
