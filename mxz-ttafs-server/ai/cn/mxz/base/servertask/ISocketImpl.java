package cn.mxz.base.servertask;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

import cn.javaplus.util.Closer;

import com.lemon.commons.socket.ISocket;

public class ISocketImpl implements ISocket {

	private Socket socket;

	public ISocketImpl(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void close() {
		Closer.close(socket);
	}

	@Override
	public String getIP() {
		return (socket.getInetAddress() + "").replaceAll("\\", "");
	}

	@Override
	public long getId() {
		return 0;
	}

	@Override
	public void write(ByteBuffer b) throws IOException {

		OutputStream os = socket.getOutputStream();

		b.flip();

		os.write(b.array());

		os.flush();
	}

}
