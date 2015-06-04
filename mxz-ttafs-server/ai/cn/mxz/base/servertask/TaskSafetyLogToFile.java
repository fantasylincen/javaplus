package cn.mxz.base.servertask;

import java.util.Collection;

import cn.javaplus.time.taskutil.TaskSafety;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.util.debuger.SystemLog;

public abstract class TaskSafetyLogToFile extends TaskSafety {

	@Override
	protected void print(long timeUsed) {
		SystemLog.debug("ServerTaskFinish", getClass().getSimpleName() + "|" + timeUsed + "ms");
	}

	@Override
	protected final void process(Exception e) {
		SystemLog.error("", e);
	}

	/**
	 * 世界
	 *
	 * @return
	 */
	private World getWorld() {

		final World world = WorldFactory.getWorld();

		return world;
	}

	/**
	 * 最近登陆过的用户城池
	 *
	 * @return
	 */
	protected Collection<City> getNearests() {

		return getWorld().getNearests().values();
	}
}
