package cn.javaplus.mxzrobot.smessender;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;

import cn.javaplus.commons.socket.ISocket;
import cn.javaplus.mxzrobot.packet.PacketC2S;
import cn.javaplus.security.DES;
import cn.javaplus.util.Closer;

public class ServerMessageSender {
	private Socket socket;
	private String text;
	private String ip;
	private int port;

	/**
	 * @param userId 接入玩家用户名
	 * @param port 端口
	 * @param text 脚本内容
	 */
	public String send(String ip, int port, String text) {
		this.ip = ip;
		this.port = port;
		this.text = text;

		try {
			connect();
			return sendMessage();
		} catch (Exception e) {
			return e.getMessage();
		} finally {
			Closer.close(socket);
		}
	}

	private String sendMessage() {
		PacketC2S p = new PacketC2S(4001);
		String buildText = buildText();
		p.putString(buildText);
		send(p);

		try {
			InputStream is = socket.getInputStream();
//			byte[] data = new byte[20480];
//			BufferedInputStream bis = new BufferedInputStream(is);
			DataInputStream dis = new DataInputStream(is);
//			int len = bis.read(data);
//			int len = bis.read(data, 0, bis.available());
//			if (len == -1) {
//				throw new RuntimeException("服务器没有反馈消息!");
//			}
			byte head = dis.readByte();
			short no = dis.readShort();
			short len = dis.readShort();
			String string = dis.readUTF();
//			data = Arrays.copyOfRange(data, 7, len - 1);
//			String string = new String(data);
			return string;
			
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter out = new PrintWriter(sw);
			e.printStackTrace(out);
			return sw.toString();
		}
	}

	private String buildText() {
		return DES.encrypt(text);
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
