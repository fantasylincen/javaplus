package cn.mxz.task.achieve;

import java.util.Collection;
import java.util.Iterator;

import mongo.gen.MongoGen.AchieveTaskDto;
import cn.mxz.base.task.Task;
import cn.mxz.city.City;
import cn.mxz.task.BoxReward;
import cn.mxz.task.TaskList;

public class AchieveTaskListImpl implements TaskList<AchieveTaskDto> {

	private City	city;

	public AchieveTaskListImpl(City city) {
		this.city = city;
	}

	@Override
	public Iterator<Task<AchieveTaskDto>> iterator() {

		Collection<Task<AchieveTaskDto>> all = city.getAchieveTaskManager().getAll();
		return all.iterator();
	}

	@Override
	public BoxReward getBoxReward() {
		return null;
	}

}
