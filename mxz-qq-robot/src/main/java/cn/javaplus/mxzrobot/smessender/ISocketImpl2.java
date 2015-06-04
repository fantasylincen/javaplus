package cn.javaplus.mxzrobot.smessender;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

import cn.javaplus.util.Closer;

import com.lemon.commons.socket.ISocket;

public class ISocketImpl2 implements ISocket {

	private Socket	socket;

	public ISocketImpl2(Socket socket) {
		this.socket = socket;
	}

	public void close() {
		Closer.close(socket);
	}

	public String getIP() {
		return (socket.getInetAddress() + "").replaceAll("\\", "");
	}

	public long getId() {
		return 0;
	}

	public void write(ByteBuffer b) throws IOException {

		OutputStream os = socket.getOutputStream();

		b.flip();

		byte[] array = b.array();

		array = getArray(array);

		os.write(array);

		os.flush();
	}

	private byte[] getArray(byte[] array) {
		int position = getPosition(array);
		return Arrays.copyOf(array, position + 1);
	}

	private int getPosition(byte[] array) {
		for (int i = 1; i <= array.length; i++) {
			int index = array.length - i;
			if(array[index] != 0) {
				return index;
			}
		}
		return -1;
	}
}
