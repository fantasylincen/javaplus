package cn.javaplus.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import cn.javaplus.collections.list.Lists;

public class Test {
	public static void main(String[] args) {
//		String cc = sendPost("http://120.24.61.79:10001/getUserData");
//		System.out.println(cc);

			List<Integer> ls = Lists.newArrayList(1, 2, 3, 4, 5);
			System.out.println(ls.subList(1, 5));
			
		
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print("did=1&aid=aaaaaa");
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
//	
//	public static void main(String[] args) {
//		String cc = sendPost("http://120.24.61.79:10001");
//		System.out.println(cc);
//	}
//
//	// out.print("did=1&uid=152128907&goodsId=com.pocketriver.minilegend.1&money=2&pt=11&tcd=1&st=1LINGCHENGDENGXINFENG");
//
//	public static String sendPost(String url) {
//		String uriAPI = "http://120.24.61.79:10001/getUserData"; // 这是我测试的本地,大家可以随意改
//		HttpPost httpRequest = new HttpPost(uriAPI);
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("aid", "aaaaaa"));
//		params.add(new BasicNameValuePair("did", "1"));
//		try {
//			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
//			if (httpResponse.getStatusLine().getStatusCode() == 200) {
//				String strResult = EntityUtils.toString(httpResponse.getEntity());
//				return strResult;
//			} else {
//				return "Error Response: " + httpResponse.getStatusLine().toString();
//			}
//		} catch (ClientProtocolException e) {
//			return e.getMessage().toString();
//		} catch (IOException e) {
//			return e.getMessage().toString();
//		} catch (Exception e) {
//			return e.getMessage().toString();
//		}
//	}
}
