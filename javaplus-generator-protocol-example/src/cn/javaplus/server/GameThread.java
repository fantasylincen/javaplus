package cn.javaplus.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;

import cn.javaplus.comunication.DataHandler;
import cn.javaplus.comunication.ProtocolUser;
import cn.javaplus.util.Util;

/**
 * 游戏服务
 */
public class GameThread extends Thread {

	DataHandler			handler	= new DataHandler();
	private Socket		socket;
	private InputStream	input;

	public GameThread(Socket socket) {
		this.socket = socket;
		try {
			input = socket.getInputStream();
		} catch (IOException e) {
			throw Util.Exception.toRuntimeException(e);
		}
	}

	@Override
	public void run() {
		byte[] data = readFromClinet();
		ProtocolUser user = GameWorld.getUser("574907580@qq.com", socket);
		try {
			handler.onData(user, data);
		} catch (Throwable e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 从客户端读入数据
	 */
	private byte[] readFromClinet() {
		byte[] data = new byte[10240];
		
		try {
			int read = input.read(data);
			data = Arrays.copyOf(data, read);
		} catch (IOException e) {
			throw Util.Exception.toRuntimeException(e);
		}
		return data;
	}
}
