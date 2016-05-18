package com.chen.android_petlove.adapter;

import java.util.List;
import java.util.Map;

import com.chen.android_petlove.R;
import com.chen.android_petlove.delete.Delete;
import com.chen.android_petlove.tips.DialogFile;
import com.chen.android_petlove.tips.DialogUpdate;
import com.chen.android_petlove.tips.MyDialog;

import android.content.ClipData.Item;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

public class MyPetAdapter extends BaseAdapter {
	private Context context;
	private List<Map<String, Object>> listItems;
	private LayoutInflater inflater;

	public final class ListItemView {
		public ImageView image;
		public TextView title;
		public TextView info;
		public Button view;
		public Button edit;
		public Button delete;
	}

	public MyPetAdapter(Context context, List<Map<String, Object>> listItems) {
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
	public View getView( final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ListItemView listItemView = null;
		if(convertView == null) {
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
			listItemView = (ListItemView) convertView.getTag();
		}
		String type = (String) listItems.get(position).get("type");
		char key = type.charAt(0);
		switch (key) {
		case 'd':
			listItemView.image.setImageResource(R.drawable.icon_dog);
			break;
		case 'c':
			listItemView.image.setImageResource(R.drawable.icon_cat);
			break;

		default:
			listItemView.image.setImageResource((Integer) listItems.get(
					position).get("image"));
			break;
		}
		
		listItemView.title.setText((CharSequence) listItems.get(position).get(
				"name"));
		listItemView.info.setText((CharSequence) type);
		listItemView.view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				new DialogFile(context, (CharSequence) listItems.get(position)
						.get("name"), (CharSequence) listItems.get(position)
						.get("type"), (CharSequence) listItems.get(position)
						.get("weight"), (CharSequence) listItems.get(position)
						.get("character"), (CharSequence) listItems.get(
								position).get("age"), (CharSequence) listItems.get(
										position).get("sex"), (CharSequence) listItems.get(
												position).get("note")).show();
			}
		});

		listItemView.edit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] s = { "" };
				new DialogUpdate(context, (String) listItems.get(position).get(
						"objectId"), "ÐÞ¸Ä³èÎïµµ°¸", (CharSequence) listItems.get(
								position).get("name"), (CharSequence) listItems.get(
										position).get("type"), (CharSequence) listItems.get(
												position).get("weight"), (CharSequence) listItems.get(
														position).get("character"), (CharSequence) listItems
														.get(position).get("age"), (CharSequence) listItems
														.get(position).get("sex"), (CharSequence) listItems
														.get(position).get("note"), s).show();
			}
		});

		listItemView.delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Delete d = new Delete(context);
				final String objectId = (String) listItems.get(position).get(
						"objectId");
				d.deletePet(objectId);
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						while (!d.getFlag().equals(objectId));
						listItems.remove(position);
					}
				});
			}
		});
		return convertView;
	}

}
