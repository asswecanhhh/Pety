package com.chen.android_petlove.pic;

import java.io.File;


import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.GetListener;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class DownLoad {
	private Context context;
	private BmobFile file;
	
	public DownLoad(Context context,String saveName,String url){
		this.context = context;
		file = new BmobFile(saveName, null, url);
	}
	public void Commit(BmobFile file, final String savePath) {
		// TODO Auto-generated method stub
		File saveFile = new File(savePath,file.getFilename());
		file.download(context, saveFile, new DownloadFileListener() {
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				Toast.makeText(context, "开始下载...", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onSuccess(String arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "下载成功，保存路径"+savePath, Toast.LENGTH_SHORT).show();

			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
				System.out.println(arg1);
			}
		});
	}
	
	public BmobFile getFile() {
		return file;
	}
	public void setFile(BmobFile file) {
		this.file = file;
	}
}
