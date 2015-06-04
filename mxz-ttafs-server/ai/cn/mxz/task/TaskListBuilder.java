package cn.mxz.task;

import cn.mxz.base.task.Task;
import cn.mxz.protocols.user.task.TaskP.TaskListPro;
import db.domain.TaskDTO;

class TaskListBuilder {

	public TaskListPro build(TaskList<? extends TaskDTO> list) {
		TaskListPro.Builder b = TaskListPro.newBuilder();
		for (Task<?> t : list) {
			b.addTasks(new TaskBuilder().build(t));
		}
		BoxReward reward = list.getBoxReward();
		
		if(reward != null) {
			
			b.setBoxReward(new TaskBoxRewardBuilder().build(reward));
		}
		return b.build();
	}

}
