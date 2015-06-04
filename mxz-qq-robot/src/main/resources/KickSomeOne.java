import java.util.Map;

import cn.javaplus.util.Closer;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.init.SocketManager;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.util.debuger.Debuger;

import com.lemon.commons.socket.ISocket;

public class KickSomeOne {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public void kick(String user) {
		City city = getCity(user);
		if (city == null) {
			throw new RuntimeException("玩家不存在" + user);
		}
		kickUser(city);
	}

	/**
	 * 踢掉在另一地登陆的玩家
	 */
	private void kickUser(City city) {

		SocketManager socketManager = WorldFactory.getWorld().getSocketManager();
		
		final ISocket old = socketManager.getSocket(city);

		MessageFactory.getSystem().kick(old);

		socketManager.unbind(old);

		Closer.closeDelay(old);

		Debuger.debug("Kick", city.getId() + "|" + old.getId());
	}
	
	/**
	 * 根据 昵称 或者 id 获取用户
	 */
	private City getCity(String user) {
		City city = CityFactory.getCity(user);
		if (city != null) {
			return city;
		}

		Map<String, String> all = WorldFactory.getWorld().getNickManager()
				.getNickAll();
		String id = all.get(user);
		if (id == null) {
			return null;
		}

		return getCity(id);
	}
}