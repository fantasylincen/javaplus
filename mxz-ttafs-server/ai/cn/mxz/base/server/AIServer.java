package cn.mxz.base.server;

import cn.mxz.base.handler.DataHandlerMain;
import cn.mxz.events.Events;
import cn.mxz.events.ServerStartEvent;

import com.lemon.commons.socket.MultithreadedServer;

public class AIServer implements Server {

	private MultithreadedServer	server;
	
	public AIServer(int port) {

		server = new MultithreadedServer(port);

		DataHandlerMain handler = new DataHandlerMain(this);
		server.setHandler(handler);
	}

	
	
	@Override
	public void start() {

		Thread thread = new Thread(server);

		thread.start();

		Printer.printServerStart("普通");

		Events.getInstance().dispatch(new ServerStartEvent(this));
	}

	@Override
	public void stop() {

		server.shutDown();

//		Set<Thread> threads = Thread.getAllStackTraces().keySet();
//
//		Debuger.debug("AIServer.stop() 线程数量:" + threads.size());
//
//		for (Thread thread : threads) {
//			if(!thread.isDaemon()) {
//				thread.stop();
//				Debuger.debug("AIServer.stop() 停止线程:" + thread);
//			}
//		}
	}



	public boolean isRunning() {
		return server.isRunning();
	}



	

}
