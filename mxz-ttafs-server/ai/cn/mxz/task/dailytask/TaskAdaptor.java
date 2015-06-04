package cn.mxz.task.dailytask;

import cn.mxz.base.task.Task;
import cn.mxz.base.task.TaskListener;

public class TaskAdaptor<T extends  db.domain.TaskDTO>implements TaskListener<T> {

	@Override
	public void onFinish(Task<T> task) {
		
	}

	@Override
	public void onGiveBack(Task<T> task) {
		
	}

	@Override
	public void onCheckOver() {
		
	}

}
