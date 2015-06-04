package cn.mxz.base.servertask;

import mongo.gen.MongoGen.Daos;

public class UserCountersZeroTask extends TaskSafetyLogToFile {

	@Override
	public void runSafty() {
		Daos.getUserCountersDao().clear();
	}

}
