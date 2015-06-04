//package cn.mxz.base.servertask;
//
//import java.io.IOException;
//import java.net.Socket;
//import java.nio.ByteBuffer;
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//
//import cn.javaplus.comunication.Bytes;
//import cn.javaplus.comunication.Packet;
//import cn.javaplus.file.IProperties;
//import cn.javaplus.util.Closer;
//import cn.javaplus.util.Util;
//import cn.mxz.base.config.ServerConfig;
//import cn.mxz.base.world.World;
//import cn.mxz.base.world.WorldFactory;
//import cn.mxz.config.ConfigProperties;
//import cn.mxz.util.debuger.Debuger;
//
//import com.google.common.collect.Sets;
//import com.lemon.commons.define.Define;
//import com.lemon.commons.socket.ISocket;
//
//class NotifyAIServerOnlineTask extends TaskSafetyLogToFile {
//
//	private class AI2LoginServerPacket extends Packet {
//
//		@Override
//		public void put(Bytes message) {
//		}
//
//		@Override
//		protected int getHead() {
//			return Define.CLIENT_PACKETS_HEAD;
//		}
//
//		@Override
//		public void send(ISocket writer) {
//
//			if (writer == null) {
//				return;
//			}
//
//			// if(!isSend) {
//
//			ByteBuffer buff = ByteBuffer.allocate(info.capacity() + 4);
//
//			buff.put((byte) getHead());
//
//			buff.putShort((short) info.position());
//
//			info.flip();
//
//			buff.put(info); // 包尾 长度1
//
//			buff.put((byte) 127); // 包尾 长度1
//
//			try {
//
//				buff = sub(buff);
//
//				writer.write(buff);
//
//			} catch (Exception e) {
//
//				Debuger.error("包发送失败了!");
//			}
//
//			// isSend = true;
//			//
//			// } else {
//			// new Exception("该数据包已经发送过了!").printStackTrace();
//			// }
//		}
//
//		private ByteBuffer sub(ByteBuffer info) {
//
//			ByteBuffer bf = ByteBuffer.allocate(info.position());
//
//			byte[] datas = new byte[bf.limit()];
//
//			info.flip();
//
//			for (int i = 0; i < datas.length; i++) {
//
//				datas[i] = info.get();
//			}
//
//			bf.put(datas);
//
//			return bf;
//		}
//
//	}
//
//	@SuppressWarnings("deprecation")
//	@Override
//	public void runSafty() {
//
//		String ip = Util.Property.getProperties(ConfigProperties.PATH).getProperty("LoginServerIp");
//
//		Socket s = null;
//
//		World w = WorldFactory.getWorld();
//
//		Set<Integer> serverIds = getServerIds(ServerConfig.getServerId());
//
//		int size = w.getOnlineAll().size();
//		for (Integer serverId : serverIds) {
//
//			try {
//
//				s = new Socket(ip, LoginDefine.LOGIN_SERVER_PORT);
//
//				Packet p = new AI2LoginServerPacket();
//
//				p.putShort(LoginDefine.NOTIFY_SERVER_ONLINE);
//
//				p.putInt(serverId); // 服务器ID
//
//				p.putInt(size); // 当前在线人数
//
//				p.putString("0.0.0"); // 版本号
//
//				p.send(new ISocketImpl(s));
//
//				 Debuger.debug("NotifyAIServerOnlineTask.runSafty() 服务器ID:" +
//				 serverId + " 在线人数:" + w.getOnlineAll().size() );
//
//			} catch (IOException e) {
//
//				Debuger.error("NotifyAIServerOnlineTask.runSafty()连接登陆服务器失败!"
//						+ ip + ":" + LoginDefine.LOGIN_SERVER_PORT);
//
//			} finally {
//
//				Closer.close(s);
//			}
//		}
//
//	}
//
////	public static void main(String[] args) {
////		System.out.println(getServerIds(10004));
////	}
//
//	/**
//	 * 获得与之绑定的服务器ID, 加上自身
//	 * @param serverId
//	 * @return
//	 */
//	private static Set<Integer> getServerIds(int serverId) {
//
//		Set<Integer> set = Sets.newHashSet();
//		set.add(serverId);
//		IProperties p = Util.Property.getProperties(ConfigProperties.PATH);
//		String value = p.getProperty("bindServerId");
//		String[] all = value.split("\\|");
//		for (String text : all) {
//			if (contains(text, serverId)) {
//				set.addAll(getOther(text, serverId));
//			}
//		}
//		return set;
//	}
//
//	private static Collection<? extends Integer> getOther(String text, int serverId) {
//		Set<Integer> is = Sets.newHashSet(Util.Collection.getIntegers(text));
//		is.remove(serverId);
//		return is;
//	}
//
//	private static boolean contains(String text, int serverId) {
//		List<Integer> is = Util.Collection.getIntegers(text);
//		return is.contains(serverId);
//	}
//}
