//package cn.mxz.thirdpaty;
//
//import java.io.IOException;
//
//import cn.javaplus.util.Closer;
//import cn.javaplus.util.Util;
//
//
///**
// * 长连接实现
// * @author 林岑
// */
//class EratingConnectorImpl2 extends AbstractEratingConnector {
//
//	public EratingConnectorImpl2(String eratingPath) {
//		super(eratingPath);
//		connect();
//		bind();
//	}
//
//	protected void end() {
//		if(Util.Random.isHappen(0.1f)) {
//			close();
//		}
//	}
//
//	protected void start() {
//		try {
//			socket.getOutputStream().write(new byte[]{});
//		} catch (IOException e) {
//			connect();
//			bind();
//		}
//	}
//
//	private void close() {
//		Closer.close(socket);
//	}
//}
