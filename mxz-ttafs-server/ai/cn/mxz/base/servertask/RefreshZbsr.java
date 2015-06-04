package cn.mxz.base.servertask;

import java.util.Collection;

import mongo.gen.MongoGen.Daos;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.util.debuger.Debuger;

public class RefreshZbsr extends TaskSafetyLogToFile {


	@Override
	public void runSafty() {
		World world = WorldFactory.getWorld();
		Collection<City> all = world.getOnlineAll();
		Daos.getZbsrDao().clear();
		Debuger.debug("RefreshHeiShi.runSafty() 装备商人自动刷新");
		for (City city : all) {
			city.freeZbsr();
		}
	}

}
