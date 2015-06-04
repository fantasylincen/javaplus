package cn.mxz.task;

import cn.mxz.base.task.Task;
import db.domain.TaskDTO;

public interface TaskList<T extends TaskDTO> extends Iterable<Task<T>>{

	BoxReward getBoxReward();

}
