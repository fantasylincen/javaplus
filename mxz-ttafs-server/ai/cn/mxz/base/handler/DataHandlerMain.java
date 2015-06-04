package cn.mxz.base.handler;

import java.io.IOException;

import cn.javaplus.util.Util;
import cn.mxz.base.server.Server;

import com.lemon.commons.event.IDataHandler;
import com.lemon.commons.socket.MySocket;

/**
 *
 * 消息处理器，处理所有客户端发来的请求
 *
 * @author 林岑
 * @since 2013年6月1日 23:05:36
 *
 */
public class DataHandlerMain extends AbstractDataHandler implements IDataHandler {


	public DataHandlerMain(Server server) {
	}



	@Override
	public void onData(MySocket s) throws IOException {

		while (!s.getBufferList().isEmpty()) {

			final byte[] data = s.getBufferList().poll();

			if (data != null) {

				synchronized (s) {
					
					super.onData(s, data);
				}
				
			}
		}
	}

	@Override
	public void onDestroy(MySocket socket) throws IOException {

		try {
			super.onClosed(socket);
		} catch (Exception e) {
			throw Util.Exception.toRuntimeException(e);
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

		return super.clone();
	}
}
