package com.chen.android_petlove;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobUser;

import com.chen.android_petlove.adapter.MyPetAdapter;
import com.chen.android_petlove.adapter.SaleProAdapter;
import com.chen.android_petlove.form.SaleProduct;
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

public class SalePro extends Activity {
	private Button back;
	private Button ok;
	private User host;
	private ListView list;
	private Integer[] image = {R.drawable.ic_launcher};
	private ArrayList<SaleProduct> listPro = new ArrayList<SaleProduct>();
	private List<Map<String, Object>> listItemsmain = new ArrayList<Map<String,Object>>();
	private LinearLayout loading;
	private SaleProAdapter saleProAdapter;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what == 0x234){
				loading.setVisibility(View.GONE);
				saleProAdapter = new SaleProAdapter(SalePro.this,listItemsmain);
				list.setAdapter(saleProAdapter);
			} 
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saleproduct);

		back = (Button) findViewById(R.id.salepro_back);
		ok = (Button) findViewById(R.id.salepro_add);
		list = (ListView) findViewById(R.id.salepro_list);
		loading = (LinearLayout) findViewById(R.id.salepro_loading);

		host = BmobUser.getCurrentUser(this,User.class);
		
		MyToastBlue.makeText(this, "正在很努力的加载数据中..", Toast.LENGTH_LONG).show();


		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(host == null);
				Quer q = new Quer(getApplicationContext());
				q.getSaleProduct(listPro, 20, 0);
				while(listPro.size() == 0);
				List<Map<String, Object>> listItems = new ArrayList<Map<String,Object>>();
				for(int i = 0; i < listPro.size();i++){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("image", image[0]);
					map.put("objectId", listPro.get(i).getObjectId());
					map.put("title", listPro.get(i).getTitle());
					map.put("price", String.valueOf(listPro.get(i).getPrice()));
					map.put("seller", listPro.get(i).getAuthor().getName());
					map.put("sellerId", String.valueOf(listPro.get(i).getAuthor().getObjectId()));
					map.put("note", listPro.get(i).getNote());
					map.put("hostId", host.getObjectId());
					listItems.add(map);
				}
				listItemsmain = listItems;
				while(listItemsmain.size() == 0){
					
				}
				Message msg = Message.obtain();
				msg.what = 0x234;
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

		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SalePro.this,SaleProAdd.class);
				startActivity(intent);
			}
		});
	}
}
