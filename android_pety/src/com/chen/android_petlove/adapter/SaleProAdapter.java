package com.chen.android_petlove.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobUser;

import com.chen.android_petlove.R;
import com.chen.android_petlove.delete.Delete;
import com.chen.android_petlove.form.User;
import com.chen.android_petlove.tips.DialogProduct;
import com.chen.android_petlove.tips.MyToastBlue;

import android.content.Context;
import android.content.ClipData.Item;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SaleProAdapter extends BaseAdapter{
	private Context context;
	private LayoutInflater inflater;
	private List<Map<String, Object>> listItems;

	public final class ListItemView {
		public ImageView image;
		public TextView title;
		public TextView info;
		public Button view;
		public Button edit;
		public Button delete;
	}

	public SaleProAdapter(Context context,List<Map<String, Object>> listItems){
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.listItems = listItems;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ListItemView listItemView = null;
		
		if(convertView == null){
			listItemView = new ListItemView();
			convertView = inflater.inflate(R.layout.list, null);
			listItemView.image = (ImageView) convertView.findViewById(R.id.list_image);
			listItemView.title = (TextView) convertView.findViewById(R.id.list_title);
			listItemView.info = (TextView) convertView.findViewById(R.id.list_info);
			listItemView.view = (Button) convertView.findViewById(R.id.list_view);
			listItemView.edit = (Button) convertView.findViewById(R.id.list_edit);
			listItemView.delete = (Button) convertView.findViewById(R.id.list_delete);
			convertView.setTag(listItemView);
		} else {
			listItemView =(ListItemView) convertView.getTag();
		}
		if(listItems.get(position).get("hostId").toString().equals(listItems.get(position).get("sellerId").toString())){
			listItemView.delete.setVisibility(View.VISIBLE);
			listItemView.edit.setVisibility(View.GONE);
		} else{
			listItemView.delete.setVisibility(View.GONE);
			listItemView.edit.setVisibility(View.VISIBLE);
			listItemView.edit.setText("��ϵ����");
		}
		
		listItemView.image.setImageResource(R.drawable.icon_goods);
		listItemView.title.setText((CharSequence) listItems.get(position).get("title"));
		listItemView.info.setText((CharSequence) ("��"+listItems.get(position).get("price")));

		listItemView.view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String titles = (String) listItems.get(position).get("title");
				String prices = (String) listItems.get(position).get("price");
				String sellers = (String) listItems.get(position).get("seller");
				String notes = (String) listItems.get(position).get("note");
				new DialogProduct(context, titles, prices, sellers, notes).show();
			}
		});
		
		listItemView.edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyToastBlue.makeText(context, "�����ڴ�", 2000).show();
			}
		});
		
		listItemView.delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Delete d = new Delete(context);
				final String objectId = (String) listItems.get(position).get("objectId");
				d.deleteSaleProduct(objectId);
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while(!d.getFlag().equals(objectId));
						listItems.remove(position);
					}
				});
			}
		});


		return convertView;
	}

}
