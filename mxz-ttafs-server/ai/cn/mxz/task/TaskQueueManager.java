package cn.mxz.task;

public class TaskQueueManager {

	private static TaskQueue q;

	public static TaskQueue getQueue() {
		if(q == null) {
			q = new TaskQueue();
		}
		return q;
	}

}
