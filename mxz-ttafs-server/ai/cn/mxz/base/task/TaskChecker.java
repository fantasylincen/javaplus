package cn.mxz.base.task;


/**
 *
 * 任务检查器
 * @author 林岑
 * @since	2013年8月21日 13:31:38
 *
 */
public interface TaskChecker {

	/**
	 * 尝试完成任务
	 */
	void tryToFinishTask(Environment environment);

}
