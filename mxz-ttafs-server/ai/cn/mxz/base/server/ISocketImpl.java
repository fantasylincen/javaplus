//package cn.mxz.base.server;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.Socket;
//import java.nio.ByteBuffer;
//
//import cn.javaplus.common.util.Closer;
//
//import com.lemon.commons.socket.ISocket;
//
//public class ISocketImpl implements ISocket {
//
//	private Socket	socket;
//
//	public ISocketImpl(Socket socket) {
//		this.socket = socket;
//	}
//
//	@Override
//	public long getId() {
//		return 1;
//	}
//
//	@Override
//	public String getIP() {
//		return "127.0.0.1";
//	}
//
//	@Override
//	public void write(ByteBuffer data) throws IOException {
//		byte [] datas = new byte [data.limit()];
//		data.get(datas);
//		OutputStream os = socket.getOutputStream();
//		os.write(datas);
//		os.flush();
//	}
//
//	@Override
//	public void close() {
//		Closer.close(socket);
//	}
//
//}
