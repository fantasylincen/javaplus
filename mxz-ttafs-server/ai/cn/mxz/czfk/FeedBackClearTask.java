package cn.mxz.czfk;

import java.util.Collection;

import mongo.gen.MongoGen.Daos;
import cn.mxz.base.servertask.TaskSafetyLogToFile;
import cn.mxz.city.City;

public class FeedBackClearTask extends TaskSafetyLogToFile {

	@Override
	public void runSafty() {
		Daos.getFeedBackDao().clear();
		freeManager();
	}

	private void freeManager() {
		Collection<City> nearests = getNearests();
		for (City city : nearests) {
			city.freeFeedBackManager();
		}		
	}

}
