package cn.javaplus.commons.socket;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.javaplus.commons.event.IAllHandler;
import cn.javaplus.commons.event.IConnectHandler;
import cn.javaplus.commons.event.IConnectionScoped;
import cn.javaplus.commons.event.IDataHandler;
import cn.javaplus.commons.event.IHandler;
import cn.javaplus.commons.logger.Logs;

class Dispatcher implements Runnable {
	private IAllHandler						handler;
	private Selector						selector;
	private boolean							isRunning		= true;
	private ExecutorService					pool			= Executors.newFixedThreadPool(3);
	private ConcurrentLinkedQueue<MSocket>	newConnections	= new ConcurrentLinkedQueue<MSocket>();

	private void init() {
		try {
			selector = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	final void acceptNewConnection(MSocket connection) throws IOException {
		newConnections.add(connection);
		wakeup();
	}

	private void wakeup() {
		if (selector != null) {
			selector.wakeup();
		}
	}

	public void setHandler(IHandler handler) {
		if (handler != null) {
			this.handler = new HandlerDecorator(handler);
		}
	}

	void shutdown() {
		if (isRunning)
			isRunning = false;

		for (SelectionKey sk : selector.keys()) {
			MSocket socket = (MSocket) sk.attachment();

			Logs.debug.info("关闭Socket:" + socket);
			socket.close();
		}

		try {
			selector.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processing() throws IOException {
		int n = selector.select();

		if (n > 0) {
			Set<SelectionKey> set = selector.selectedKeys();
			Iterator<SelectionKey> it = set.iterator();

			while (it.hasNext()) {
				SelectionKey sk = it.next();
				it.remove();

				MSocket connection = (MSocket) sk.attachment();

				if (sk.isValid() && sk.isReadable()) {
					try {
						connection.handlerData();
					} catch (Exception e) {
						connection.close();
					}
				}

				if (sk.isValid() && sk.isWritable()) {
					try {
						if (connection.hasDataToSend()) {
							connection.handleWrite();
						}
					} catch (Exception e) {
						connection.close();
					}
				}
			}
		}

		while (!newConnections.isEmpty()) {
			MSocket connection = newConnections.poll();
			try {
				connection.registerSelect(selector, SelectionKey.OP_READ);
				IAllHandler hdl = handler;
				hdl = (IAllHandler) handler.clone();
				connection.init(hdl);

			} catch (Exception e) {
				e.printStackTrace();
				connection.close();
			}
		}
	}

	@Override
	public void run() {

		init();

		while (isRunning) {

			try {
				processing();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class HandlerDecorator implements IAllHandler {

		private IHandler	handler				= null;
		private boolean		isConnectHandler	= false;
		private boolean		isDataHandler		= false;
		private boolean		isConnetionScoped	= false;

		HandlerDecorator(IHandler handler) {
			this.handler = handler;

			if (handler instanceof IConnectHandler) {
				isConnectHandler = true;
			}
			if (handler instanceof IDataHandler) {
				isDataHandler = true;
			}

			if (handler instanceof IConnectionScoped) {
				isConnetionScoped = true;
			}
		}

		@Override
		public boolean onConnectionOpening(final MSocket connection) throws IOException {
			if (isConnectHandler) {
				pool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							((IConnectHandler) handler).onConnectionOpening(connection);
						} catch (Exception e) {
							connection.close();
							e.printStackTrace();
						}
					}
				});
			}

			return false;
		}

		@Override
		public void onData(final MySocket connection) throws IOException {

			if (isDataHandler) {

				pool.execute(new Runnable() {

					@Override
					public void run() {
						try {
							((IDataHandler) handler).onData(connection);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		}

		@Override
		public void onDestroy(final MySocket connection) throws IOException {
			if (isDataHandler) {

				try {
					((IDataHandler) handler).onDestroy(connection);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public Object clone() throws CloneNotSupportedException {

			if (isConnetionScoped) {
				HandlerDecorator copy = (HandlerDecorator) super.clone();
				copy.handler = (IHandler) ((IConnectionScoped) this.handler).clone();
				return copy;
			} else {
				return this;
			}
		}
	}
}
