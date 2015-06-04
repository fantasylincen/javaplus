package cn.mxz.listeners.init;

import cn.mxz.FighterTempletConfig;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.events.Listener;
import cn.mxz.events.ServerStartEvent;
import cn.mxz.init.ReadyUserImpl;
import cn.mxz.user.init.ReadyUser;

public class CreateGMUser implements Listener<ServerStartEvent> {

	@Override
	public void onEvent(ServerStartEvent e) {
		World world = WorldFactory.getWorld();
		String id = "hzmxzgmrole001hzmxz";
		City city = world.get(id);
		if(city == null) {
			ReadyUser ready = new ReadyUserImpl();
			ready.setAccounts(id);
			ready.setFighterTypeId(FighterTempletConfig.findByCategory(3).get(0).getId());
			ready.setClientType(0);
			ready.setNick(world.getNickManager().getRandomNick());
			ready.setRoleId(id);
			CityFactory.createNewCity(ready);
		}
	}

}
