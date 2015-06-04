package cn.mxz.base.task;

/**
 * 任务模板
 * @author 林岑
 *
 */
public interface TaskTemplet {

	/**
	 * 所需达成次数
	 * @return
	 */
	int getMax();

	/**
	 * 玩家等级需求
	 * @return
	 */
	int getUserLevelNeed();

}
