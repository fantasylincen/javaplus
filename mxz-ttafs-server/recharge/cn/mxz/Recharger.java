//package cn.mxz;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.nio.ByteBuffer;
//import java.nio.channels.SocketChannel;
//
//import cn.javaplus.common.util.Closer;
//import cn.javaplus.common.util.Util.Buffer;
//import cn.mxz.base.server.OrderResolver;
//import cn.mxz.event.EventDispatcherImpl;
//import cn.mxz.util.debuger.Debuger;
//import db.domain.OrderData;
//import db.domain.TLogicServerManagement;
//import db.domain.TLogicServerManagementDAOImpl;
//
//public class Recharger extends EventDispatcherImpl {
//
//	public class Listener extends Thread {
//
//		@Override
//		public void run() {
//
//			try {
//				ByteBuffer buffer = ByteBuffer.allocate(200);
//				int read = socket.read(buffer);
//
//				buffer.flip();
//
//				byte[] bytes = new byte[read];
//				buffer.flip();
//				buffer.get(bytes, 0, read);
//
//				String readUTF = new String(bytes, "utf8");
//
//				Debuger.debug("Recharger.Listener.run() 服务器反馈:" + readUTF);
//
//				if (readUTF.equals("success")) {
//					dispatchEvent(new RechargeSuccessEvent());
//				} else {
//					dispatchEvent(new RechargeFaildEvent());
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//				dispatchEvent(new RechargeFaildEvent());
//			} finally {
//				Closer.close(socket);
//			}
//		}
//
//		public void end() {
//			try {
//				stop();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	private OrderData		data;
//	private SocketChannel	socket;
//	private Listener		listener;
//
//	public Recharger(OrderData data) {
//		this.data = data;
//	}
//
//	/**
//	 * 给游戏中的玩家充值
//	 *
//	 * @param data
//	 */
//	public void recharge() {
//
//		int serverId = getServerId(data);
//		socket = null;
//
//		try {
//			socket = linkToGameServer(getIp(serverId), getPort(serverId));
//
//			ByteBuffer buffer = Buffer.toByteBuffer(data.getOrderData());
//
//			socket.write(buffer);
//
//			listener = new Listener();
//			listener.start();
//
//			Debuger.debug("Recharger.recharge() 提交给游戏服务器的订单:" + data.getOrderData());
//
//		} catch (IOException e) {
//			e.printStackTrace();
//			dispatchEvent(new RechargeFaildEvent());
//			listener.end();
//			Closer.close(socket);
//		}
//	}
//
//	private int getPort(int serverId) {
//		TLogicServerManagementDAOImpl DAO = new TLogicServerManagementDAOImpl();
//		TLogicServerManagement tt = DAO.get(serverId + "");
//		return tt.getRechargeServerPort();
//	}
//
//	private String getIp(int serverId) {
//		TLogicServerManagementDAOImpl DAO = new TLogicServerManagementDAOImpl();
//		TLogicServerManagement tt = DAO.get(serverId + "");
//		String ip = tt.getLogicServerAiIpDx();
//		return ip;
//	}
//
//	/**
//	 * 根据订单获取服务器ID
//	 *
//	 * @param data
//	 * @return
//	 */
//	private int getServerId(OrderData data) {
//		OrderResolver resolver = new OrderResolver(data.getOrderData());
//		return resolver.getServerId();
//	}
//
//	private SocketChannel linkToGameServer(String ip, int port) {
//		try {
//			SocketChannel s = SocketChannel.open();
//			InetSocketAddress a = new InetSocketAddress(ip, port);
//			s.connect(a);
//			return s;
//		} catch (Exception e) {
//			throw cn.javaplus.common.util.Util.Exception.toRuntimeException(e);
//		}
//	}
//
//}
