package cn.mxz.task;

import java.util.Collection;

import cn.mxz.base.task.Task;
import db.domain.TaskDTO;

public class TaskUtil {

	public static<T extends TaskDTO> int getTaskNotGiveBackCount(TaskAll<T> manager) {
		Collection<Task<T>> all = manager.getAll();
		int count = 0;
		for (Task<?> task : all) {
			if (task.isFinishAll() && !task.isGiveBack()) {
				count++;
			}
		}
		return count;
	}

}
