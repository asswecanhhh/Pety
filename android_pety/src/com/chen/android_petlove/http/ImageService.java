package com.chen.android_petlove.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.StreamTokenizer;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageService {
	public static byte[] getImage(String path) throws IOException{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setReadTimeout(5000);
		InputStream inputStream = conn.getInputStream();
		byte[] data = StreamTool.readInputStream(inputStream);
		return data;
	}
}
