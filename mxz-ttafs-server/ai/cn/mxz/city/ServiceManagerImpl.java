package cn.mxz.city;

import cn.javaplus.comunication.Response;
import cn.mxz.handler.UserService;
import cn.mxz.init.UserServiceImpl;
import cn.mxz.user.ServiceManager;
import cn.mxz.util.packet.MyPacket;

import com.google.protobuf.AbstractMessage;

public class ServiceManagerImpl implements ServiceManager {

	private City	city;

	public ServiceManagerImpl(City city) {
		this.city = city;
	}

	private Response createResponse() {
		return new MyPacket();
	}

	@Override
	public UserService getUserService() {
		UserServiceImpl s = new UserServiceImpl();
		s.init(city.getSocket());
		return s;
	}

	@Override
	public void send(int packet, AbstractMessage message) {
		Response response = createResponse();
		response.putInt(packet);
		response.put(message);
		response.send(city.getSocket());
	}

}
