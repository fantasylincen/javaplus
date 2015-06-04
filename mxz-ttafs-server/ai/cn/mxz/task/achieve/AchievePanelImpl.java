package cn.mxz.task.achieve;

import cn.mxz.city.City;
import cn.mxz.task.TaskUtil;

class AchievePanelImpl implements AchievePanel {

	private City	city;

	AchievePanelImpl(City city) {
		this.city = city;
	}

	@Override
	public int getTipsMessageCount() {
		AchieveTaskManager manager = city.getAchieveTaskManager();
		return TaskUtil.getTaskNotGiveBackCount(manager);
	}

}
