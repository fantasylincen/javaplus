package cn.mxz.base.servertask;

import mongo.gen.MongoGen.Daos;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;

//这个任务必须要在 0点执行一次, 否则玩家签到这些都有可能出现问题
public class DailyTaskClear extends TaskSafetyLogToFile {

	@Override
	public void runSafty() {

		Daos.getDailyTaskDao().clear();

		for (City city : WorldFactory.getWorld().getAll()) {

			city.freeMemory();
		}
	}
}
