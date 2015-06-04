//package cn.mxz;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//
//import org.apache.mina.core.service.IoAcceptor;
//import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
//
//import cn.mxz.base.server.Server;
//import cn.mxz.base.server.ServerStartEvent;
//import cn.mxz.event.EventDispatcherImpl;
//
//public class OrderDispatcher extends EventDispatcherImpl implements Server {
//
//	private IoAcceptor	acceptor;
//
//	public OrderDispatcher() {
//	}
//
//	@Override
//	public void start() {
//
//		acceptor = new NioSocketAcceptor();
//
//		// 设置服务器端的处理程序
//		acceptor.setHandler(new RechargeServerDataHandler());
//
//		try {
//
//			acceptor.bind(new InetSocketAddress(RechargeServerDefine.SERVER_PORT));
//
//		} catch (IOException e) {
//
//			throw new RuntimeException(e);
//		}
//
//		dispatchEvent(new ServerStartEvent());
//	}
//
//	@Override
//	public void stop() {
//
//		acceptor.dispose();
//	}
//}
