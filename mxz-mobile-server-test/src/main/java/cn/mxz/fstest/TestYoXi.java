package cn.mxz.fstest;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import cn.javaplus.comunication.PacketC2S;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.lemon.commons.socket.ISocket;

public class TestYoXi {

	private static Socket socket;

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		socket = new Socket("localhost", 31510);
		getData();
	}

	public static void getData() {
		PacketC2S p = new PacketC2S();
		p.putInt(1800000000);
		p.putString(buildText());
		send(p);
	}


	private static String buildText() {
		JSONObject o = new JSONObject();
		o.put("className", "cn.mxz.yoxi.YoXiTransform");
		o.put("methodName", "yoXi");
		
		List<Object> ls = Lists.newArrayList();
		ls.add("123");
		ls.add("321");
		o.put("args", new JSONArray(ls));
		
		return o.toJSONString();
	}

	private static void send(PacketC2S p) {
		ISocket s = new ISocketImpl2(socket);
		p.send(s);
	}
}
