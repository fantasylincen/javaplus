package cn.mxz.task;

import java.util.Collection;

import cn.mxz.base.task.Task;
import db.domain.TaskDTO;

public interface TaskAll<T extends TaskDTO> {

	Collection<Task<T>> getAll();

}
