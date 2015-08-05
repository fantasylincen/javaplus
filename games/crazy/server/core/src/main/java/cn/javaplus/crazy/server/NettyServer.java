package cn.javaplus.crazy.server;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import cn.mxz.base.server.Server;

public class NettyServer implements Server {

	private int port;
	private ServerBootstrap server;
	private ExecutorService boss;
	private ExecutorService worker;

	public NettyServer(int port) {
		this.port = port;
		boss = Executors.newCachedThreadPool();
		worker = Executors.newCachedThreadPool();
		server = new ServerBootstrap(newChannelFactory());
	}

	private NioServerSocketChannelFactory newChannelFactory() {
		return new NioServerSocketChannelFactory(boss, worker);
	}

	@Override
	public void start() {
		server.setPipelineFactory(new ServerPipelineFactory());
		server.bind(new InetSocketAddress(port));
	}

	@Override
	public void stop() {
		boss.shutdown();
		worker.shutdown();
	}

	@Override
	public boolean isRunning() {
		return !worker.isShutdown();
	}

}
