import java.util.Collection;

import message.S;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;

public class NotifyAllUser {

	public String run() {
		return "RUN_FOUNCTION";
	}

	void sendNotify(String message) {
		World w = WorldFactory.getWorld();
		Collection<City> all = w.getOnlineAll();
		for (City city : all) {
			
			// 如果这个方法出了莫名其妙的错误的话, 应该是这里的问题
			if (!city.getId().equals("hzmxzgmrole001hzmxz")) {
				city.getNoticeSender().send(S.S0, message);
			}
		}
	}
}