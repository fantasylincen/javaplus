package cn.javaplus.mxzrobot.smessender;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import cn.javaplus.compressor.ZLib;
import cn.javaplus.comunication.PacketC2S;
import cn.javaplus.security.DES;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.lemon.commons.socket.ISocket;

public class ServerMessageSender {
	private Socket socket;
	private String text;
	private String ip;
	private int port;
	private String id;

	/**
	 * @param userId 接入玩家用户名
	 * @param ip	地址
	 * @param port 端口
	 * @param text 脚本内容
	 */
	public String send(String userId, String ip, int port, String text) {
		id = userId;
		this.ip = ip;
		this.port = port;
		this.text = text;

		try {
			connect();
			access();
			return sendMessage();
		} catch (Exception e) {
			return e.getMessage();
		} finally {
			Closer.close(socket);
		}
	}

	private String sendMessage() {
		PacketC2S p = new PacketC2S();
		p.putInt(1800000000);
		p.putString(buildText());
		send(p);

		try {
			InputStream is = socket.getInputStream();
			byte[] data = new byte[20480];
			BufferedInputStream bis = new BufferedInputStream(is);
			int len = bis.read(data);
			if (len == -1) {
				throw new RuntimeException("服务器没有反馈消息!");
			}
			data = Arrays.copyOfRange(data, 3, len - 1);
			data = new ZLib().unCompress(data);
			data = Arrays.copyOfRange(data, 6, data.length);
			String string = new String(data);
			org.json.JSONObject o = new org.json.JSONObject(string);
			
			if(o.has("error")) {
				String err = o.getString("error");
				return "通知服务器时报错了: " + err;
			}
			return o.getString("yoXi");
			
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter out = new PrintWriter(sw);
			e.printStackTrace(out);
			return sw.toString();
		}
	}

	private String buildText() {
		JSONObject o = new JSONObject();
		o.put("className", "cn.mxz.yoxi.YoXiTransform");
		o.put("methodName", "yoXi");

		List<Object> ls = Lists.newArrayList();
		ls.add(DES.encrypt(text));
		ls.add(pwd());
		o.put("args", new JSONArray(ls));

		return o.toJSONString();
	}

	private String pwd() {
		int min = Util.Time.getCurrentMin();
		String text = min + " serverId = ?";
		for (int i = 0; i < 5; i++) {
			text = Util.Secure.md5(text + text);
		}
		return text;
	}

	public void access() {

		PacketC2S p = new PacketC2S();
		p.putInt(210000);
		p.putString(getUserId());
		p.putString("");
		p.putInt(1);
		p.putString("");
		p.putInt(1);
		p.putInt(1);
		p.putString("");

		send(p);
		try {
			socket.getInputStream().read(new byte[10240]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String getUserId() {
		return id;
	}

	private void send(PacketC2S p) {
		ISocket s = new ISocketImpl2(socket);
		p.send(s);
	}

	private void connect() {
		try {
			socket = new Socket(ip, port);
			socket.setSoTimeout(5000);
		} catch (Exception e) {
			throw new RuntimeException("服务器连接失败:" + "--" + ip + ":" + port);
		}
	}

}
