package cn.javaplus.proxy.http;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.collections.map.Maps;
import cn.javaplus.compressor.GZip;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;

public class HttpProxy extends Thread {

	private ServerSocket httpSock;

	public HttpProxy(ServerSocket httpProxySock) {
		httpSock = httpProxySock;
	}

	public void run() {
		while (true) {
			try {
				new Proxy(httpSock.accept()).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

class IpAndPort {
	int port;
	String ip;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}

class Proxy extends Thread {
	private Socket handleSock;

	public Proxy(Socket serverSock) throws IOException {
		handleSock = serverSock;
	}

	@SuppressWarnings("resource")
	public void run() {
		byte[] clientRequestData = new byte[102400];
		int len;
		try {

			DataInputStream fromClient = new DataInputStream(handleSock.getInputStream());
			DataOutputStream toClient = new DataOutputStream(handleSock.getOutputStream());

			len = fromClient.read(clientRequestData, 0, 10000);

			if (len > 0) {
				clientRequestData = Arrays.copyOf(clientRequestData, len);// 1
				IpAndPort ip = getIpAndPort(clientRequestData);
				if (ip == null) {
					return;
				}

				Socket proxy_Web = new Socket(ip.getIp(), ip.getPort());

				DataInputStream fromWeb = new DataInputStream(proxy_Web.getInputStream());
				DataOutputStream toWeb = new DataOutputStream(proxy_Web.getOutputStream());

				toWeb.write(clientRequestData, 0, len);
				toWeb.flush();

				new RequestThread(fromClient, toWeb).start();
				new ResponseThread(fromWeb, toClient).start();
			}
		} catch (IOException e) {
			e.printStackTrace();

			Closer.close(handleSock);
			return;
		}
	}

	private IpAndPort getIpAndPort(byte[] buff) {
		String request = new String(buff);
		if (request.indexOf("http:") == -1 || request.indexOf("HTTP/1.1") == -1)
			return null;
		request = request.substring(request.indexOf("http:") + 7, request.indexOf("HTTP/1.1") - 2);
		if (request.indexOf("/") != -1)
			request = request.substring(0, request.indexOf("/"));

		IpAndPort p = new IpAndPort();
		if (request.contains(":")) {
			String[] ss = request.split(":");

			p.setIp(ss[0]);
			p.setPort(new Integer(ss[1]));
		} else {
			p.setIp(request);
			p.setPort(80);
		}
		return p;

	}

}

class RequestThread extends Thread {

	private DataInputStream input;
	private DataOutputStream output;

	public RequestThread(DataInputStream input, DataOutputStream output) {
		this.input = input;
		this.output = output;
	}

	public void run() {
		byte[] data = new byte[2048];
		int len = 0;
		while (true) {
			try {
				if (len == -1)
					return;
				len = input.read(data);
				if (len > 0) {
					output.write(data);
					output.flush();
				}
				Util.Thread.sleep(2);
			} catch (IOException e) {
				break;
			}
		}
//		商界支付网站
	}
}

/**
 * 服务器给客户端的响应
 */
class ResponseThread extends Thread {

	private DataInputStream input;
	private DataOutputStream output;

	public ResponseThread(DataInputStream input, DataOutputStream output) {
		this.input = input;
		this.output = output;
	}

	public void run() {
		byte[] data = new byte[2048];
		int len = 0;
		while (true) {
			try {
				if (len == -1)
					return;
				len = input.read(data);
				if (len > 0) {

					data = Arrays.copyOf(data, len);
//					onData(data);
					output.write(data);
					output.flush();
				}
				Util.Thread.sleep(2);
			} catch (IOException e) {
				break;
			}
		}
	}

//	private void onData(byte[] data) {
//		String s = new String(data);
//		System.out.println(s);
//		GZip g = new GZip();
//		try {
//			data = g.unCompress(data);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	private void filter(String ss) {
		String t = Util.File.getContent("E://360data//重要数据//桌面//aaa.txt");
		ss.replace(t, Util.File.getContent("E://360data//重要数据//桌面//bbb.txt"));
	}

	private Map<String, Object> buildResponse(byte[] data) {

		String string = new String(data);
		StringReader sr = new StringReader(string);
		BufferedReader br = new BufferedReader(sr);
		HashMap<String, Object> map = Maps.newHashMap();
		while (true) {
			try {
				String ln = br.readLine();
				if (ln == null)
					break;

				if (isPar(ln)) {
					String[] ss = ln.split(":");
					map.put(ss[0].trim(), ss[1].trim());
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}
		try {
			putContentValue(data, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	private void putContentValue(byte[] data, HashMap<String, Object> map) {
		Object v = map.get("Content-Length");
		if (v == null)
			return;
		Integer len = new Integer(v.toString());
		if (len <= 0)
			return;
		try {
			byte[] d = Arrays.copyOfRange(data, data.length - len, data.length);
			map.put("Content-Value", d);
		} catch (Exception e) {
			System.err.println(data.length + " " + (data.length - len) + "   " + len);
		}

	}

	private boolean isPar(String ln) {
		String a = "[A-Za-z\\-, 0-9]";
		return ln.matches(a + "+:" + a + "+");
	}

	private String findContentEncoding(byte[] data) {
		Pattern c = Pattern.compile("Content-Encoding: .+");
		Matcher m = c.matcher(new String(data));
		if (!m.find())
			return null;
		String find = m.group();
		return find.replaceAll("Content-Encoding: ", "").trim();
	}

	private int findContentLen(byte[] data) {
		Pattern c = Pattern.compile("Content-Length: [0-9]+");
		Matcher m = c.matcher(new String(data));
		if (!m.find())
			return 0;
		String find = m.group();
		return new Integer(find.replaceAll("Content-Length: ", "").trim());
	}
}