package cn.mxz.task.dailytask;

import java.util.Collection;

import mongo.gen.MongoGen.DailyTaskDto;
import cn.mxz.base.task.AreadyReceivedReward;
import cn.mxz.base.task.Task;
import cn.mxz.base.task.TaskChecker;
import cn.mxz.base.task.TaskNotFoundException;
import cn.mxz.base.task.TaskUnFinishAllException;
import cn.mxz.task.TaskAll;
import cn.mxz.task.TaskList;

public interface DailyTaskManager extends TaskChecker, TaskAll<DailyTaskDto>{

	/**
	 * 获取指定任务
	 * @param taskId
	 * @return
	 */

	Task<DailyTaskDto> getTask(int taskId);

	/**
	 * 归还任务
	 * @param taskId
	 */
	void giveBack(int taskId) throws TaskNotFoundException, AreadyReceivedReward, TaskUnFinishAllException;

	Collection<Task<DailyTaskDto>> getAllUnGiveBack();

	DailyTaskPanel getDailyTaskPanel();

	TaskList<DailyTaskDto> getDailyTaskList();

	void addScore(int integral);

	int getScore();
}
