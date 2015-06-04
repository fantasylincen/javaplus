package cn.mxz.base.task;

import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.init.SocketManager;
import cn.mxz.task.achieve.AchieveTaskManager;
import cn.mxz.task.dailytask.DailyTaskManager;

public class TaskCheckerImpl implements TaskChecker {

	private World	world;
	private SocketManager	socketManager;
	private City	city;
	private Environment	environment;

	public TaskCheckerImpl() {
		world = WorldFactory.getWorld();
		socketManager = world.getSocketManager();
	}

	@Override
	public void tryToFinishTask(Environment	environment) {

//		new Exception().printStackTrace();

		this.environment = environment;
		if(environment.needCheck()) {
			city = socketManager.getUser(environment.getSocket());

			if(city != null ) {

				DailyTaskManager daily = city.getDailyTaskManager();

				//检查目标任务
				checkTask(daily);

				AchieveTaskManager achieve = city.getAchieveTaskManager();

				//检查成就任务
				checkTask(achieve);

//				//检查Pvp任务
//				checkTask(city.getPVPTask());
			}
		}
	}

	private void checkTask(TaskChecker checker) {

		checker.tryToFinishTask(environment);
	}

}
