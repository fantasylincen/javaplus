package cn.javaplus.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.javaplus.log.Log;
import cn.javaplus.string.StringPrinter;

public class WebContentFethcer {
	public static String get(String encoding, String url) {
		URL getUrl;
		HttpURLConnection connection = null;
		StringPrinter out = new StringPrinter();
		try {
			getUrl = new URL(url);
			connection = (HttpURLConnection) getUrl.openConnection();
			connection.setConnectTimeout(8000);
			connection.connect();
			InputStream is = connection.getInputStream();
			InputStreamReader in = new InputStreamReader(is, encoding);
			BufferedReader reader = new BufferedReader(in);
			String line;
			while(true) {
				line = reader.readLine();
				if(line == null)
					break;
				out.println(line);
			}
			
			reader.close();
		} catch (Exception e) {
			Log.e("Error", url);
			throw new RuntimeException(e);
		} finally {
			if (connection != null)
				connection.disconnect();
		}
		return out.toString();
	}

	public static void main(String[] args) {
//		String string = get("utf8", "http://192.168.120.172/gameconfig/getGameConfig?projectId=142562186547910000009&zoneId=10002&serverConfigKey=ACoiuqwe9712311d");
//		String string = get("utf8", "http://www.baidu.com");
//		System.out.println(string);
		
	}
}