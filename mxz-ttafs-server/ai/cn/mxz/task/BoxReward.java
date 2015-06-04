package cn.mxz.task;

import java.util.ArrayList;
import java.util.List;

import cn.mxz.DailyIntegralTaskTemplet;
import cn.mxz.DailyIntegralTaskTempletConfig;
import cn.mxz.city.City;

import com.google.common.collect.Lists;

public class BoxReward {

	private City city;

	public BoxReward(City city) {
		this.city = city;
	}

	public List<TaskBox> getBoxes() {
		ArrayList<TaskBox> ls = Lists.newArrayList();
		List<DailyIntegralTaskTemplet> all = DailyIntegralTaskTempletConfig.getAll();
		for (DailyIntegralTaskTemplet task : all) {
			ls.add(new TaskBox(task, city, getScore()));
		}
		return ls;
	}

	public int getMaxScore() {
		List<DailyIntegralTaskTemplet> all = DailyIntegralTaskTempletConfig.getAll();
		int max = Integer.MIN_VALUE;
		for (DailyIntegralTaskTemplet task : all) {
			if(task.getIntegral() > max) {
				max = task.getIntegral();
			}
		}
		return max;
	}

	public int getScore() {
		return city.getDailyTaskManager().getScore();
	}

}
