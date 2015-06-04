package cn.mxz.task.dailytask;

import cn.mxz.city.City;
import cn.mxz.task.TaskUtil;

class DailyTaskPanelImpl implements DailyTaskPanel {

	private City	city;

	DailyTaskPanelImpl(City city) {
		this.city = city;
	}

	@Override
	public int getTipsMessageCount() {
		DailyTaskManager manager = city.getDailyTaskManager();
		return TaskUtil.getTaskNotGiveBackCount(manager);
	}

}
