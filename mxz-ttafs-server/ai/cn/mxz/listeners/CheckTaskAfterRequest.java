package cn.mxz.listeners;

import cn.mxz.base.handler.EnvironmentImpl;
import cn.mxz.base.task.Environment;
import cn.mxz.base.task.TaskChecker;
import cn.mxz.base.task.TaskCheckerImpl;
import cn.mxz.city.City;
import cn.mxz.events.AfterRequestSuccessEvent;
import cn.mxz.events.Listener;

/**
 * 用户请求完毕时, 检查用户的任务
 *
 * @author 林岑
 *
 */
//用户请求正常结束后,检查任务完成情况
public class CheckTaskAfterRequest implements Listener<AfterRequestSuccessEvent> {

	@Override
	public void onEvent(AfterRequestSuccessEvent event) {
		City user = event.getUser();

		if (user != null && !user.getTeam().getAll().isEmpty()) {
			// 任务检查器
			TaskChecker checker = new TaskCheckerImpl();
			Environment environment = new EnvironmentImpl(user.getSocket(),
					event.getPacketId());
			checker.tryToFinishTask(environment);
		}
	}
}
