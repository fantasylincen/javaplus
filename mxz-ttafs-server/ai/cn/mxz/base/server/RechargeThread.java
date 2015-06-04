//package cn.mxz.base.server;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.nio.ByteBuffer;
//import java.nio.channels.SocketChannel;
//
//import cn.javaplus.common.util.Closer;
//import cn.javaplus.common.util.Util.Buffer;
//import cn.mxz.util.debuger.Debuger;
//
//class RechargeThread extends Thread {
//
//	private SocketChannel socket;
//
//	RechargeThread(SocketChannel s) {
//		this.socket = s;
//	}
//
//	@Override
//	public void run() {
//		try {
//
//
//			Debuger.debug("RechargeThread.run() 正在收取充值服务器发的订单!");
//
//			ByteBuffer buffer = ByteBuffer.allocate(1024);
//
//			int read = socket.read(buffer);
//
//			byte[] bytes = new byte[read];
//
//			buffer.flip();
//
//			buffer.get(bytes, 0, read);
//
//			String order = new String(bytes, "utf8");
//
//			Debuger.debug("RechargeThread.run() 收到充值服务器发来的订单信息:" + order);
//			new OrderResolver(order).recharge();
//
//			ByteBuffer b = Buffer.toByteBuffer("success");
//
//			socket.write(b);
//
//		} catch (Exception e) {
//			PrintWriter pw = null;
//			try {
//				pw = new PrintWriter(socket.socket().getOutputStream());
//				e.printStackTrace(pw);
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			} finally {
//				Closer.close(pw);
//			}
//		} finally {
//			Closer.close(socket);
//		}
//	}
//}
