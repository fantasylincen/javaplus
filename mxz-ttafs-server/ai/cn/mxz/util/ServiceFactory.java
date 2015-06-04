package cn.mxz.util;

import cn.mxz.factory.IFactory;

import com.lemon.commons.socket.ISocket;

/**
 * 服务工厂
 *
 * @author 林岑
 *
 */
public class ServiceFactory implements IFactory {

	private ISocket	socket;

	public ServiceFactory(ISocket socket) {
		this.socket = socket;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object get(String name) {

		final Object obj = Factory.get(name);

		if (obj != null && obj instanceof Service) {

			((Service) obj).init(socket);
		}

		return obj;
	}
}
