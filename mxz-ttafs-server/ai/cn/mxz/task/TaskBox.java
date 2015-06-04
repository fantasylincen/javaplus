package cn.mxz.task;

import cn.javaplus.util.Util;
import cn.mxz.ActivityTemplet;
import cn.mxz.ActivityTempletConfig;
import cn.mxz.DailyIntegralTaskTemplet;
import cn.mxz.city.City;
import cn.mxz.city.FunctionOpenManager;
import cn.mxz.city.UserPrizeSender;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.debuger.Debuger;

public class TaskBox {

	private DailyIntegralTaskTemplet task;
	private City city;
	private int score;

	public TaskBox(DailyIntegralTaskTemplet task, City city, int score) {
		this.task = task;
		this.city = city;
		this.score = score;
	}

	public DailyIntegralTaskTemplet getTemplet() {
		return task;
	}

	public boolean canReceive() {
		if (isOpen()) {
			return false;
		}
		int need = task.getIntegral();
		return score >= need;
	}

	public boolean isOpen() {
		return city.getUserCounter().isMark(CounterKey.DAILY_TASK_BOX_IS_OPEN,
				task.getId());
	}

	/**
	 * 开启宝箱
	 */
	public void open() {
		String awards = task.getAwards();
		UserPrizeSender ss = city.getPrizeSender1();
		ss.send(awards);
		city.getUserCounter().mark(CounterKey.DAILY_TASK_BOX_IS_OPEN,
				task.getId());
		int activityId = task.getModuleId();
		ActivityTemplet temp = ActivityTempletConfig.get(activityId);
		if (Util.Time.isIn(temp.getTime())) {
			ss.send(task.getActivityAwards());
		}

	}

	public City getCity() {
		return city;
	}
}
