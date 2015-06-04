package cn.mxz.base.servertask;

import java.util.Collection;

import cn.mxz.city.City;
import cn.mxz.system.SystemCounter;
import cn.mxz.system.SystemCounterKey;
import mongo.gen.MongoGen.Daos;

public class NvwaClearTask extends TaskSafetyLogToFile {

	@Override
	public void runSafty() {
		sendBack();
		clearCounter();
		clearUserData();
		freeNvwas();
	}

	private void freeNvwas() {
		Collection<City> nearests = getNearests();
		for (City city : nearests) {
			city.freeNvwa();
		}		
	}

	private void clearUserData() {
		Daos.getNvwaDao().clear();
	}

	private void clearCounter() {
		SystemCounter ins = SystemCounter.getInstance();
		ins.set(SystemCounterKey.NVWA_BOUGHT_COUNT, 0);
	}

	private void sendBack() {
		Collection<City> nearests = getNearests();
		for (City city : nearests) {
			city.getNvwa().sendBack();
		}
	}

}
