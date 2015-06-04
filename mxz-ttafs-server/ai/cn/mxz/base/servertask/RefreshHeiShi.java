package cn.mxz.base.servertask;

import java.util.Collection;

import mongo.gen.MongoGen.Daos;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.util.debuger.Debuger;

/**
 * 刷新黑市所有商品
 * @author 林岑
 *
 */
public class RefreshHeiShi extends TaskSafetyLogToFile {

	@Override
	public void runSafty() {
		World world = WorldFactory.getWorld();
		Collection<City> all = world.getOnlineAll();
		Daos.getHeiShiStoreDao().clear();
		Debuger.debug("RefreshHeiShi.runSafty() 招贤榜自动刷新");
		for (City city : all) {
			city.freeHeiShi();
		}
	}

}
