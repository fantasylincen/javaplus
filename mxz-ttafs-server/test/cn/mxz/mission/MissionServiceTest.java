package cn.mxz.mission;

import org.junit.Test;

import cn.mxz.handler.MissionService;
import cn.mxz.testbase.TestBaseAccessed;

public class MissionServiceTest extends TestBaseAccessed {


	private MissionService getService() {
		return ((MissionService) factory.get("missionService"));
	}

	@Test
	public void getEnter() {

		getService().enter(2);
		//getService().giveUp();
	}
}
