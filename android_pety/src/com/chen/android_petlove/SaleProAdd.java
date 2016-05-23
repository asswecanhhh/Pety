package com.chen.android_petlove;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadBatchListener;

import com.chen.android_petlove.pic.Upload;
import com.chen.android_petlove.sale.ProductSale;
import com.chen.android_petlove.tips.MyToastBlue;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.Window;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.SimpleAdapter.ViewBinder;

public class SaleProAdd extends Activity{
	private EditText title;
	private EditText price;
	private EditText note;
	private Button ok;
	private Button back;
	private GridView gridView1;
	private Bitmap bmp;
	private final int IMAGE_OPEN = 1;
	private String pathImage;
	private ArrayList<HashMap<String, Object>> imageItem;
	private SimpleAdapter simpleAdapter;
	private String[]s;
	String titles ;
	Double prices ;
	String notes ;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what == 0x234){
				final ProductSale ps = new ProductSale(SaleProAdd.this, titles, notes, s, prices);
				ps.Commit();
				finish();
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.saleproadd);

		title = (EditText) findViewById(R.id.saleproadd_title);
		price = (EditText) findViewById(R.id.saleproadd_price);
		note = (EditText) findViewById(R.id.saleproadd_note);
		ok = (Button) findViewById(R.id.saleproadd_ok);
		back = (Button) findViewById(R.id.saleproadd_back);
		gridView1 = (GridView) findViewById(R.id.gridView1);
		//获取资源图片加号
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.py_gridadd);
		imageItem = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("itemImage", bmp);
		imageItem.add(map);
		simpleAdapter = new SimpleAdapter(this, imageItem, R.layout.griditem_addpic, new String[]{"itemImage"},new int[]{ R.id.imageView1});
		simpleAdapter.setViewBinder(new ViewBinder() {    
			@Override    
			public boolean setViewValue(View view, Object data,    
					String textRepresentation) {    
				// TODO Auto-generated method stub    
				if(view instanceof ImageView && data instanceof Bitmap){    
					ImageView i = (ImageView)view;    
					i.setImageBitmap((Bitmap) data);    
					return true;    
				}    
				return false;    
			}  
		}); 
		gridView1.setAdapter(simpleAdapter);

		gridView1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(position == imageItem.size()-1){
					if(imageItem.size() == 5){
						MyToastBlue.makeText(SaleProAdd.this, "图片数4张已满", Toast.LENGTH_SHORT).show();

					} else {
						MyToastBlue.makeText(SaleProAdd.this, "添加图片", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						startActivityForResult(intent, IMAGE_OPEN);
					}
				} else {
					dialog(position);
				}
			}
		});


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
				if(imageItem.size() == 1){
					s = null;
					Message msg = Message.obtain();
					msg.what = 0x234;
					handler.sendMessage(msg);
				} else {
					s  = new String[imageItem.size()-1];
					for(int i = 0; i < imageItem.size()-1; i++ ) {
						s[i] = (String) imageItem.get(i).get("path");
					}
					
					final Upload u = new Upload(SaleProAdd.this, s);
					u.Commit();
					
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while(u.getUrls() == null);
							s = u.getUrls();
							Message msg = Message.obtain();
							msg.what = 0x234;
							handler.sendMessage(msg);
						}
					}).start();
					
				}


				titles = title.getText().toString();
				prices = Double.parseDouble(price.getText().toString());
				notes = note.getText().toString();
				
			}
		});
	}
	//获取图片路径 响应startActivityForResult 
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//打开图片
		if(resultCode == RESULT_OK && requestCode == IMAGE_OPEN) {
			Uri uri = data.getData();
			//查询选择图片
			if(!TextUtils.isEmpty(uri.getAuthority())) {
				Cursor cursor = getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);

				if(null == cursor){
					return;
				}
				//光标移动至开头 获取图片路径;
				cursor.moveToFirst();
				pathImage = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
			}

		}//end if
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(!TextUtils.isEmpty(pathImage)){
			Bitmap addmp = BitmapFactory.decodeFile(pathImage);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", addmp);
			map.put("path", pathImage);
			imageItem.remove(imageItem.size()-1);
			imageItem.add(map);
			map = new HashMap<String, Object>();
			map.put("itemImage", bmp);
			imageItem.add(map);

			simpleAdapter = new SimpleAdapter(this, imageItem, R.layout.griditem_addpic, new String[]{"itemImage"}, new int[] {R.id.imageView1});
			simpleAdapter.setViewBinder(new ViewBinder() {

				@Override
				public boolean setViewValue(View view, Object data,
						String textRepresentation) {
					// TODO Auto-generated method stub
					if(view instanceof ImageView && data instanceof Bitmap){
						ImageView i = (ImageView) view;
						i.setImageBitmap((Bitmap) data);
						return true;
					}
					return true;
				}
			});
			gridView1.setAdapter(simpleAdapter);
			simpleAdapter.notifyDataSetChanged();
			//刷新后释放 防止手机休眠后自动添加
			pathImage = null;
		}
	}

	protected void dialog(final int position){
		AlertDialog.Builder builder = new Builder(SaleProAdd.this);
		builder.setMessage("确认移除已添加图片吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				imageItem.remove(position);
				simpleAdapter.notifyDataSetChanged();
			}
		}); 
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

}
