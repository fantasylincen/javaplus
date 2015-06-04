//package cn.mxz.base.server;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.nio.channels.ServerSocketChannel;
//import java.nio.channels.SocketChannel;
//
//import cn.javaplus.common.util.Closer;
//import cn.javaplus.common.util.Util.Exception;
//import cn.mxz.base.config.ServerConfig;
//import db.domain.TLogicServerManagement;
//
//class RechargeServer extends Thread {
//
//	public RechargeServer() {
//		setDaemon(true);
//	}
//
//	@Override
//	public void run() {
//
//		TLogicServerManagement config = ServerConfig.getConfig();
//
//		int port = config.getRechargeServerPort();
//
//		ServerSocketChannel ss = null;
//
//		try {
//
//			ss = ServerSocketChannel.open();
//			ss.socket().bind(new InetSocketAddress(port));
//
//		} catch (IOException e) {
//			Closer.close(ss);
//			throw Exception.toRuntimeException(e);
//		}
//
//		while (true) {
//
//			SocketChannel s = null;
//
//			try {
//				s = ss.accept();
//			} catch (IOException e) {
//				e.printStackTrace();
//				continue;
//			}
//
//			new RechargeThread(s).start();
//		}
//	}
//}
