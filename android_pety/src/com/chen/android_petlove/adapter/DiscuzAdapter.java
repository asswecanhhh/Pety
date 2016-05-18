package com.chen.android_petlove.adapter;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import com.chen.android_petlove.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DiscuzAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private List<Map<String, Object>> listItems;
	
	public final class ListItemView{
		public TextView title;
		public TextView info;
		public ImageView image;
		public TextView time;
	};
	
	public DiscuzAdapter(Context context,List<Map<String, Object>> listItems){
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ListItemView listItemView = null;
		if(convertView == null){
			listItemView = new ListItemView();
			convertView = inflater.inflate(R.layout.listdiscuz, null);
			listItemView.title = (TextView) convertView.findViewById(R.id.discuz_title);
			listItemView.info = (TextView) convertView.findViewById(R.id.discuz_info);
			listItemView.image = (ImageView) convertView.findViewById(R.id.discuz_image);
			listItemView.time = (TextView) convertView.findViewById(R.id.discuz_time);
			convertView.setTag(listItemView);
		}
		else{
			listItemView = (ListItemView) convertView.getTag();
		}
		
		listItemView.title.setText((CharSequence) listItems.get(position).get("title"));
		listItemView.info.setText((CharSequence) listItems.get(position).get("info"));
		listItemView.image.setVisibility(View.GONE);
		listItemView.time.setText((CharSequence) listItems.get(position).get("updateTime"));
		
		return convertView;
	}

}
