package cn.mxz.base.servertask;

import java.util.Collection;

import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.messagesender.MessageFactory;

public class EmptyPacketToAll extends TaskSafetyLogToFile {

	@Override
	public void runSafty() {
		World world = WorldFactory.getWorld();
		Collection<City> all = world.getOnlineAll();
		for (City city : all) {
			MessageFactory.getSystem().beat(city.getSocket());
		}
	}

}
