package nd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 同步SDK
 * @author DXF
 *
 */
public class Sdk_tb {

	// 这里设置你的APPID
//	private static String appid 	= "140200";
	// 这里设置你的APPKEY
//	private static String appkey 	= "jwl9IfVs6PEco*2jYvlTIfsC6Pczo2Lj";
	// 同步的服务器地址
	private static String goUrl 	= "http://tgi.tongbu.com/checkv2.aspx?";
	
	public static int checkUserLogin( String sessionID ) throws Exception{
		StringBuilder getUrl = new StringBuilder();
		getUrl.append("k=");
		getUrl.append(sessionID);
		return GetResult(HttpGetGo(getUrl.toString()));
	
	}

	private static int GetResult( String jsonStr ) {
		return Integer.parseInt( jsonStr );
	}

	private static String HttpGetGo( String getUrl ) throws Exception {
		StringBuffer readOneLineBuff 	= new StringBuffer();
		String content 					= "";
		URL url = new URL(goUrl + getUrl);
		URLConnection conn = url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), "utf-8"));
		String line = "";
		while ((line = reader.readLine()) != null) {
			readOneLineBuff.append(line);
		}
		content = readOneLineBuff.toString();
		reader.close();
		return content;
	}
	
}
