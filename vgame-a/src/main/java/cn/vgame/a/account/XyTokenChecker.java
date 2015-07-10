package cn.vgame.a.account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import cn.javaplus.log.Log;
import cn.vgame.a.result.ErrorResult;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class XyTokenChecker implements TokenChecker {

	@Override
	public void check(String userId, String token, String appId) {

		String param = "uid=UID&appid=APPID&token=TOKEN";
		param = param.replaceAll("UID", userId);
		param = param.replaceAll("APPID", appId);
		param = param.replaceAll("TOKEN", token);
		String content = sendPost("http://passport.xyzs.com/checkLogin.php", param);

		Log.d("http://passport.xyzs.com/checkLogin.php", param);

		JSONObject obj = JSON.parseObject(content);
		int ret = obj.getInteger("ret");
		if (ret != 0) {
			String dsc = obj.getString("error");
			throw new ErrorResult(0, dsc).toException();
		}
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);

			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();

			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			Log.e(e);
			e.printStackTrace();
		}
		finally {
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

}
