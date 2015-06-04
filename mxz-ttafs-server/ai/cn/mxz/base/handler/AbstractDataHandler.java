package cn.mxz.base.handler;

import java.util.Arrays;
import java.util.List;

import message.S;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;

import cn.javaplus.buffer.LemonByteBuffer;
import cn.javaplus.comunication.BeanFactory;
import cn.javaplus.comunication.ProtocolsLoader;
import cn.javaplus.comunication.Request;
import cn.javaplus.comunication.RequestImpl;
import cn.javaplus.comunication.Response;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.communication.handler.DataHandler;
import cn.mxz.events.AfterRequestEvent;
import cn.mxz.events.AfterRequestSuccessEvent;
import cn.mxz.events.BeforeRequestEvent;
import cn.mxz.events.Events;
import cn.mxz.events.RequestExceptionEvent;
import cn.mxz.events.RequestSuccessEvent;
import cn.mxz.factory.IFactory;
import cn.mxz.handler.DataHandlerImpl;
import cn.mxz.init.SocketManager;
import cn.mxz.util.Factory;
import cn.mxz.util.ServiceFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.lemon.commons.socket.ISocket;

abstract class AbstractDataHandler extends IoHandlerAdapter {

	private cn.javaplus.comunication.DataHandler<City> handler;

	public AbstractDataHandler() {
		super();
		BeanFactory factory = new BeanFactory() {

			@Override
			public Object getBean(Class<?> c) {
				return Factory.get(c);
			}
		};

		ProtocolsLoader.setExternalBeanFactory(factory);
		handler = new cn.javaplus.comunication.DataHandler<City>();
		handler.addOnDataListener(new CashLogListener());
		handler.addOnDataListener(new LastOpperListener());
	}

	protected void onClosed(ISocket socket) {
		final World world = WorldFactory.getWorld();
		final SocketManager socketManager = world.getSocketManager();
		socketManager.onDestroy(socket);
	}

	/**
	 * 检查当前游戏世界是否处于锁定状态
	 */
	private void checkWorldLocked() {
		World world = WorldFactory.getWorld();
		if (world.isLock()) {
			throw new OperationFaildException(S.S10099);
		}
	}

	protected void onData(ISocket socket, byte[] data) {
		final LemonByteBuffer in = new LemonByteBuffer(data, 0); // 客户端发来的数据
		final List<Response> r = Lists.newArrayList();
		final IFactory factory = new ServiceFactory(socket);
		SocketManager sm = WorldFactory.getWorld().getSocketManager();
		City user = sm.getUser(socket);

		int position = in.position();
		int packetId = in.getInt();

		try {

			final DataHandler handler = new DataHandlerImpl();

			// 检查世界是否处于锁定状态
			checkWorldLocked();
			dispatchEvent(new BeforeRequestEvent(user, packetId));
			processRequest(socket, in, r, factory, handler, position, packetId);
			dispatchEvent(new AfterRequestSuccessEvent(user, packetId));
			
		} catch (Throwable e) {
			dispatchEvent(new RequestExceptionEvent(user, e));
		} finally {
			dispatchEvent(new AfterRequestEvent(socket, user, packetId, r));
		}
	}

	/**
	 * 派发系统事件e
	 * 
	 * @param user
	 * @param e
	 */
	private void dispatchEvent(Object e) {
		Events systemEvents = Events.getInstance();
		systemEvents.dispatch(e);
	}

	private void processRequest(ISocket socket, LemonByteBuffer in,
			List<Response> r, IFactory factory, DataHandler handler,
			int position, int packetId) throws Throwable {

		if (packetId == 1800000000) {

			runByDataHandler2(socket, in);

		} else {

			// [0, 1, -83, -80, 0, 5, 108, 99, 49, 49, 48, 0, 6, 50, 50, 50,
			// 50, 50, 50]
			in.position(position);
			handler.onData(in, r, factory);
		}
	}

	private void runByDataHandler2(ISocket socket, final LemonByteBuffer in)
			throws Throwable {

		SocketManager sm = WorldFactory.getWorld().getSocketManager();
		City user = sm.getUser(socket);

		// if(user == null) {
		// System.err.println("玩家还没有接入服务器!");
		// return ;
		// }

		String text = in.getString();
		JSONObject o = JSON.parseObject(text);
		Request rq = new RequestImpl(o);
		
		long time = System.currentTimeMillis();
		handler.onData(user, rq);
		long runTime = System.currentTimeMillis() - time;
		Events.getInstance().dispatch(new RequestSuccessEvent(rq, runTime, user, socket));
	}

	protected byte[] getData(Object message) {
		IoBuffer buffer = (IoBuffer) message;
		byte[] data = new byte[buffer.limit()];
		buffer.get(data);
		data = Arrays.copyOfRange(data, 3, data.length - 1);
		return data;
	}

}