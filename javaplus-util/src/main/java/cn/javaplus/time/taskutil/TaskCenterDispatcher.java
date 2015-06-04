package cn.javaplus.time.taskutil;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务调度器
 */
public class TaskCenterDispatcher {

	private final Map<Object, ITaskCenter> allTaskCenter = new HashMap<Object, ITaskCenter>();

	/**
	 * 根据ID号, 获取特定任务中心的实例
	 * @param id		唯一ID
	 * @param isDaemon	是否是守护线程
	 * @return
	 */
	public ITaskCenter createInstanceById(Object id, boolean isDaemon) {

		ITaskCenter it = allTaskCenter.get(id);

		if(it == null) {

			it = new TaskCenter(id.toString(), isDaemon);

			allTaskCenter.put(id, it);
		}

		return it;
	}

}
