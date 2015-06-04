package cn.javaplus.task;

import cn.javaplus.time.taskutil.TaskCenterDispatcher;

/**
 * 漫想族任务执行中心
 * @author Administrator
 *
 */
public class ManXiangTaskCenter extends TaskCenterDispatcher{

	private static ManXiangTaskCenter instance;

	private ManXiangTaskCenter() {
	}

	public static final ManXiangTaskCenter getInstance() {
		if (instance == null) {
			instance = new ManXiangTaskCenter();
		}
		return instance;
	}
}
