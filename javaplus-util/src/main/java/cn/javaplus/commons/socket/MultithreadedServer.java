package cn.javaplus.commons.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

import cn.javaplus.commons.event.IDataHandler;

public class MultithreadedServer implements Runnable {

	public static final int					DISPATCHER_SIZE	= 16;

	private ServerSocketChannel				ssc;

	private SocketChannel					sc;

	private final LinkedList<Dispatcher>	dispatchers		= new LinkedList<Dispatcher>();

	private IDataHandler					handler;

	private int								dispatcherPointer;

	private boolean							isRunning		= true;

	private int								port;

	public MultithreadedServer(int port) {
		this.port = port;
	}

	public void setHandler(IDataHandler handler) {
		this.handler = handler;

		for (Dispatcher dispatcher : dispatchers) {
			dispatcher.setHandler(handler);
		}
	}

	public final synchronized void setDispatcherSize() {
		int poolsize = dispatchers.size();

		if (poolsize > DISPATCHER_SIZE) {
			for (int i = DISPATCHER_SIZE; i < poolsize; i++) {
				Dispatcher dispatcher = dispatchers.getLast();
				dispatchers.remove(dispatcher);
				dispatcher.shutdown();
			}

		} else if (poolsize < DISPATCHER_SIZE) {
			for (int i = poolsize; i < DISPATCHER_SIZE; i++) {
				String dispatcherName = "U" + i;
				Dispatcher dispatcher = new Dispatcher();

				dispatcher.setHandler(handler);
				dispatchers.addLast(dispatcher);

				Thread t = new Thread(dispatcher);
				t.setName(dispatcherName);
				t.start();
			}
		}
	}

	private void init() { // init server monitor
		try {

			setDispatcherSize();

			ssc = ServerSocketChannel.open();
			ssc.configureBlocking(true);
			ssc.socket().bind(new InetSocketAddress(port));
		} catch (java.net.BindException e) {
			System.err.println("端口号:" + port);
			throw new BindRuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
			isRunning = false;
		}
	}

	private void processing() throws IOException { // processing client
													// connection
		sc = ssc.accept();
		getNextDispatcher().acceptNewConnection(new MSocket(sc));
	}

	private Dispatcher getNextDispatcher() {
		dispatcherPointer++;
		if (dispatcherPointer >= dispatchers.size()) {
			dispatcherPointer = 0;
		}
		return dispatchers.get(dispatcherPointer);
	}

	@Override
	public void run() { // main loop
		init();
		while (isRunning) {
			try {
				processing();
			} catch (java.nio.channels.AsynchronousCloseException e) {
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void shutDown() {

		System.out.println("shutDown...");

		try {

			isRunning = false;

			for (Dispatcher d : dispatchers) {
				d.shutdown();
			}

			ssc.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isRunning() {
		return isRunning;
	}
}
