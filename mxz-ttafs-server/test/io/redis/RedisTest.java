package io.redis;

import java.util.List;

import cn.javaplus.util.Util;

public class RedisTest {
	// private static final String a = "{\"a\":\"1\",\"b\":\"2\",\"c\":\"3\"}";
	//
	// public static void main(String[] args) {
	//
	// // string a={"a":"1","b":"2","c":"3"};
	//
	//
	// //方法1
	// Pattern compile = Pattern.compile(":\"[0-9]+\"");
	// Matcher matcher = compile.matcher(a);
	// while(matcher.find()) {
	// String group = matcher.group();
	// System.out.println(group);
	// }
	//
	// //方法2
	// String[] split = a.split(",");
	// for (String s : split) {
	// String[] ss = s.split(":");
	// print(ss);
	// }
	//
	// //方法3
	// JSONObject obj = (JSONObject) JSON.parse(a);
	//
	// System.out.println("RedisTest.main() 方法3:");
	// System.out.println(obj.get("a"));
	// System.out.println(obj.get("b"));
	// System.out.println(obj.get("c"));
	// }
	//
	// private static void print(String[] ss) {
	// System.out.println(ss[0] + "--------" + ss[1]);
	// }

	public static void main(String[] args) throws Exception {

		List<Class<?>> classes = Util.Clazz.getClasses("cn.mxz");
		for (Class<?> class1 : classes) {
			System.out.println(class1);
		}

//		while (true) {
//			try {
//				DatagramSocket server = new DatagramSocket(5050);
//				byte[] recvBuf = new byte[100];
//				DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
//				server.receive(recvPacket);
//				String recvStr = new String(recvPacket.getData(), 0, recvPacket.getLength());
//				System.out.println("Hello World!" + recvStr);
//				int port = recvPacket.getPort();
//				InetAddress addr = recvPacket.getAddress();
//				String sendStr = "Hello ! I'm Server";
//				byte[] sendBuf;
//				sendBuf = sendStr.getBytes();
//				DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, addr, port);
//				server.send(sendPacket);
//				server.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}

		// DatagramSocket client = new DatagramSocket();
		//
		// String sendStr = "Hello! I'm Client";
		// byte[] sendBuf;
		// sendBuf = sendStr.getBytes();
		// InetAddress addr = InetAddress.getByName("127.0.0.1");
		// int port = 5050;
		// DatagramPacket sendPacket = new DatagramPacket(sendBuf,
		// sendBuf.length, addr, port);
		// client.send(sendPacket);
		// byte[] recvBuf = new byte[100];
		// DatagramPacket recvPacket = new DatagramPacket(recvBuf,
		// recvBuf.length);
		// client.receive(recvPacket);
		// String recvStr = new String(recvPacket.getData(), 0,
		// recvPacket.getLength());
		// System.out.println("收到:" + recvStr);
		// client.close();
	}
}
