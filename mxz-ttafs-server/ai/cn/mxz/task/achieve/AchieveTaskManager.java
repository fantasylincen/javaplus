package cn.mxz.task.achieve;

import java.util.Collection;
import java.util.List;

import mongo.gen.MongoGen.AchieveTaskDto;
import cn.mxz.base.task.AreadyReceivedReward;
import cn.mxz.base.task.Task;
import cn.mxz.base.task.TaskChecker;
import cn.mxz.base.task.TaskNotFoundException;
import cn.mxz.base.task.TaskUnFinishAllException;
import cn.mxz.task.TaskAll;
import cn.mxz.task.TaskList;

public interface AchieveTaskManager extends TaskChecker , TaskAll<AchieveTaskDto>{

	/**
	 * 归还任务
	 * @param taskId
	 */
	void giveBack(int taskId) throws TaskNotFoundException, AreadyReceivedReward, TaskUnFinishAllException;

	Collection<Task<AchieveTaskDto>> getAllUnGiveBack();

	TaskList<AchieveTaskDto> getTaskList();

	List<Task<AchieveTaskDto>> getTasksByChapterId(int chapterId);
}
