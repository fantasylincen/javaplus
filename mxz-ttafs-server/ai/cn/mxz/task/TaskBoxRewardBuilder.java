package cn.mxz.task;

import java.util.List;

import cn.mxz.protocols.user.task.TaskP.TaskBoxRewardPro;

public class TaskBoxRewardBuilder {

	public TaskBoxRewardPro build(BoxReward reward) {
		TaskBoxRewardPro.Builder b = TaskBoxRewardPro.newBuilder();
		
		b.setScore(reward.getScore());
		b.setMaxScore(reward.getMaxScore());
		List<TaskBox> bs = reward.getBoxes();
		for (TaskBox bx : bs) {
			b.addBoxes(new TaskBoxBuilder().build(bx));
		}
		return b.build();
	}

}
