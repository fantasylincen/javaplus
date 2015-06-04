package cn.mxz.base.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import cn.mxz.base.handler.MinaDataHandler;
import cn.mxz.events.Events;
import cn.mxz.events.ServerStartEvent;

public class MinaServer implements Server {

	private int	port;

	private IoAcceptor	acceptor;

	public MinaServer(int port) {

		this.port = port;
	}

	@Override
	public void start() {

		acceptor = new NioSocketAcceptor();

		// 设置服务器端的处理程序
		acceptor.setHandler(new MinaDataHandler());

		try {

			acceptor.bind(new InetSocketAddress(port));

		} catch (IOException e) {

			throw new RuntimeException(e);
		}

		Printer.printServerStart("Mina");

		Events.getInstance().dispatch(new ServerStartEvent(this));
	}

	@Override
	public void stop() {

		acceptor.dispose();
	}

	public boolean isRunning() {
		return !acceptor.isDisposed();
	}
	
}
