package com.chen.android_petlove.pic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadBatchListener;
import android.content.Context;
import android.widget.Toast;

public class Upload {
	private String[] filePaths = new String[20];
	private Context context;
	private	String[] urls;
	
	public Upload(Context context,String[] filePaths){
		this.context = context;
		this.filePaths = filePaths;
	}
	
	
	public void Commit() {
		BmobFile.uploadBatch(context, filePaths, new UploadBatchListener() {
				
				@Override
				public void onSuccess(List<BmobFile> arg0, List<String> arg1) {
					// TODO Auto-generated method stub
					if(arg1.size() == filePaths.length){
						Toast.makeText(context, "�ļ�ȫ���ϴ����", Toast.LENGTH_SHORT).show();
						String[] s = arg1.toArray(new String[arg1.size()]);
						setUrls(s);
					}
				}
				
				@Override
				public void onProgress(int arg0, int arg1, int arg2, int arg3) {
					// TODO Auto-generated method stub
					//1��curIndex--��ʾ��ǰ�ڼ����ļ������ϴ�
			        //2��curPercent--��ʾ��ǰ�ϴ��ļ��Ľ���ֵ���ٷֱȣ�
			        //3��total--��ʾ�ܵ��ϴ��ļ���
			        //4��totalPercent--��ʾ�ܵ��ϴ����ȣ��ٷֱȣ�
				}
				
				@Override
				public void onError(int arg0, String arg1) {
					// TODO Auto-generated method stub
					Toast.makeText(context, "�ļ��ϴ�ʧ��", Toast.LENGTH_SHORT).show();
					System.out.println(arg1);

				}
			});
		}


	public String[] getUrls() {
		return urls;
	}


	public void setUrls(String[] urls) {
		this.urls = urls;
	}
	

}
