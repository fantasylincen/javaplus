package cn.mxz.task.dailytask;

import java.util.Collection;
import java.util.Iterator;

import mongo.gen.MongoGen.DailyTaskDto;
import cn.mxz.base.task.Task;
import cn.mxz.city.City;
import cn.mxz.task.BoxReward;
import cn.mxz.task.TaskList;

class DailyTaskListImpl implements TaskList<DailyTaskDto> {

	private City	city;
	private BoxReward boxReward;

	DailyTaskListImpl(City city) {
		this.city = city;
		boxReward = new BoxReward(city);
	}

	@Override
	public Iterator<Task<DailyTaskDto>> iterator() {

		Collection<Task<DailyTaskDto>> all = city.getDailyTaskManager().getAll();

//		for (Task<DailyTask> task : all) {
//			Debuger.debug("DailyTaskListImpl.iterator()" + task.getId());
//		}

		return all.iterator();
	}

	@Override
	public BoxReward getBoxReward() {
		return boxReward;
	}


}
