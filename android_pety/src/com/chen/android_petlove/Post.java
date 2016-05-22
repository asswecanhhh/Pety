package com.chen.android_petlove;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chen.android_petlove.adapter.PostAdapter;
import com.chen.android_petlove.comment.Send;
import com.chen.android_petlove.discuz.SendPost;
import com.chen.android_petlove.form.Comment;
import com.chen.android_petlove.form.Discuz;
import com.chen.android_petlove.query.Quer;

import android.app.Activity;
import android.app.DownloadManager.Query;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Post extends Activity{
	private String postId = "null";
	private Button back;
	private Button send;
	private EditText text;
	private TextView title;
	private ListView list;
	private LinearLayout loading;
	private ArrayList<Comment> listComment = new ArrayList<Comment>();
	private	List<Map<String, Object>> listItems  = new ArrayList<Map<String,Object>>();
	private PostAdapter postAdapter;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == 0x234){
				loading.setVisibility(View.GONE);
				title.setText((CharSequence) listItems.get(0).get("postName"));
				postAdapter = new PostAdapter(Post.this, listItems);
				list.setAdapter(postAdapter);
				postAdapter.notifyDataSetChanged();
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.post);
		
		Intent intent = getIntent();
		postId = intent.getStringExtra("postId");
		text = (EditText) findViewById(R.id.post_text);
		send = (Button) findViewById(R.id.post_send);
		back = (Button) findViewById(R.id.post_back);
		list = (ListView) findViewById(R.id.post_list);
		title = (TextView) findViewById(R.id.post_title);
		loading = (LinearLayout) findViewById(R.id.loading_layout);
		
		Quer q = new Quer(Post.this);
		q.getAllComment(postId, listComment, 20, 0);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(listComment.size() == 0);
				for(int i = 0; i < listComment.size(); i++){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("postName", listComment.get(i).getPost().getTitle());
					map.put("title", listComment.get(i).getAuthor().getName());
					map.put("info", listComment.get(i).getContent());
					map.put("floor", i+1);
					map.put("createAt", listComment.get(i).getCreatedAt());
					listItems.add(map);
				}
				while(listItems.size() == 0);
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
		
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Send s = new Send(Post.this, text.getText().toString(), postId);
				com.chen.android_petlove.form.Discuz d = new Discuz();
				s.Commit();
				text.setText("");
				Quer q = new Quer(Post.this);
				q.updateDiscuz(postId);
				
				q = new Quer(Post.this);
				listComment.clear();
				listItems.clear();
				q.getAllComment(postId, listComment, 20, 0);
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while(listComment.size() == 0);
						for(int i = 0; i < listComment.size(); i++){
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("postName", listComment.get(i).getPost().getTitle());
							map.put("title", listComment.get(i).getAuthor().getName());
							map.put("info", listComment.get(i).getContent());
							map.put("floor", i+1);
							map.put("createAt", listComment.get(i).getCreatedAt());
							listItems.add(map);
						}
						while(listItems.size() == 0);
						Message msg = Message.obtain();
						msg.what = 0x234;
						handler.sendMessage(msg);
					}
				}).start();
			}
		});
		
	}
}
