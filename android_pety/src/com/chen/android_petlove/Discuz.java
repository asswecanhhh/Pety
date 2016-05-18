package com.chen.android_petlove;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobUser;

import com.chen.android_petlove.adapter.DiscuzAdapter;
import com.chen.android_petlove.form.User;
import com.chen.android_petlove.query.Quer;
import com.chen.android_petlove.tips.DialogDiscuz;
import com.chen.android_petlove.tips.MyToastBlue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Discuz extends Activity{
	private Button back;
	private Button add;
	private ProgressBar bar;
	private LinearLayout loading;
	private Integer[] image = {R.drawable.ic_launcher};
	private User host;
	private TextView text;
	private ArrayList<com.chen.android_petlove.form.Discuz> listDiscuz = new ArrayList<com.chen.android_petlove.form.Discuz>();
	private List<Map<String, Object>>listItemsMain = new ArrayList<Map<String,Object>>();
	private ListView list;
	private DiscuzAdapter discuzAdapter;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what == 0x234){
				loading.setVisibility(View.GONE);
				discuzAdapter = new DiscuzAdapter(Discuz.this, listItemsMain);
				list.setAdapter(discuzAdapter);
				discuzAdapter.notifyDataSetChanged();
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.discuz);
		back = (Button) findViewById(R.id.discuz_back);
		add = (Button) findViewById(R.id.discuz_add);
		bar = (ProgressBar) findViewById(R.id.loading_bar);
		text = (TextView) findViewById(R.id.loading_text);
		loading = (LinearLayout) findViewById(R.id.discuz_loading);
		list = (ListView) findViewById(R.id.discuz_list);

		host = BmobUser.getCurrentUser(this,User.class);


		list.setOnItemClickListener(new  OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("postId", listItemsMain.get(position).get("postId").toString());
				intent.setClass(Discuz.this, Post.class);
				startActivity(intent);
			}

		});

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
				intent.setClass(Discuz.this, DiscuzAdd.class);
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
				host = BmobUser.getCurrentUser(getApplicationContext(), User.class);
				while(host == null);
				Quer q = new Quer(Discuz.this);
				listDiscuz.clear();
				q.getAllPost(listDiscuz, 20, 0);
				List<Map<String, Object>> listItems = new ArrayList<Map<String,Object>>();
				while(listDiscuz.size() == 0);
				for(int i = 0; i < listDiscuz.size();i++){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("title", listDiscuz.get(i).getTitle());
					map.put("info", listDiscuz.get(i).getAuthor().getName());
					map.put("updateTime", listDiscuz.get(i).getUpdatedAt());
					map.put("postId", listDiscuz.get(i).getObjectId());
					listItems.add(map);
				}
				listItemsMain = listItems;
				System.out.println(listItemsMain.size());
				while(listItemsMain.size() == 0);
				Message msg = Message.obtain();
				msg.what = 0x234;
				handler.sendMessage(msg);

			}
		}).start();
	}
}
