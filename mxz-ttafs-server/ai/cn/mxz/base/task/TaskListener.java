package cn.mxz.base.task;

import db.domain.TaskDTO;


/**
 * 任务监听器
 * @author 	林岑
 * @time	2013-6-28
 */
public interface TaskListener<T extends TaskDTO> {

	/**
	 * 任务完成时调用
	 * @param task
	 */
	void onFinish(Task<T> task);

	/**
	 * 任务归还时调用
	 * @param task
	 */
	void onGiveBack(Task<T> task);

	/**
	 * 所有任务检测完成时调用
	 */
	void onCheckOver();
}
